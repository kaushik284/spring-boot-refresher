package learning.kaushik.state_machine_demo.service;

import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RetryDemoService {
    @Autowired
    RetryRegistry retryRegistry;

    @Retry(name = "retry_demo", fallbackMethod = "fallbackRetryDemoMethod")
    public String retryDemoMethod(String message) {
        long numberOfFailedCallsWithRetryAttempt = retryRegistry.getAllRetries().stream().filter(retry -> retry.getName().equals("retry_demo"))
                .findFirst().orElse(io.github.resilience4j.retry.Retry.ofDefaults("default"))
                .getMetrics().getNumberOfFailedCallsWithRetryAttempt();
        log.warn("Number of failed calls with retry attempt: {}", numberOfFailedCallsWithRetryAttempt);
        retryRegistry.getAllRetries().forEach(action -> log.info("NumberOfTotalCalls: {}", action.getMetrics().getNumberOfTotalCalls()));
        log.info("testing retry demo method {}", message);

        if ("exception".equalsIgnoreCase(message)) {
            throw new RuntimeException("test exception to check retry");
        }
        return "success";
    }

    // signature does not require the message parameter, only the throwable or specific exception
    public String fallbackRetryDemoMethod(RuntimeException t) {
        log.error("fallback method called because: {}", t.getMessage(), t);
        throw new RuntimeException(t.getMessage(), t);
    }
}
