package learning.kaushik.state_machine_demo;

import learning.kaushik.state_machine_demo.enums.Events;
import learning.kaushik.state_machine_demo.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class StateMachineDemoApplication implements CommandLineRunner {

	@Autowired
	private StateMachine<States, Events> stateMachine;

	public static void main(String[] args) {
		SpringApplication.run(StateMachineDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		stateMachine.startReactively().block();
		stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.EVENT_1).build())).blockLast();
		stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.EVENT_2).build())).blockLast();
		stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.LAST).build())).blockLast();
	}
}
