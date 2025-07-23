package learning.kaushik.state_machine_demo.configs;

import learning.kaushik.state_machine_demo.enums.Events;
import learning.kaushik.state_machine_demo.enums.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.EnumState;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;
import java.util.Optional;

/**
 * Statemachine config,
 * configure initial state and state movements
 * configure events when moving from one state to another
 * <p>
 * ref: <a href="https://docs.spring.io/spring-statemachine/docs/current/reference/">
 * State Machine Reference doc</a>
 * ref: https://www.baeldung.com/spring-state-machine
 */
@Configuration
@EnableStateMachine
@Slf4j
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    // state machine configuration
    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .machineId("demoMachine1")
                .listener(demoListener());
    }

    // state configuration
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> config) throws Exception {
        config.withStates()
                .initial(States.START)
                .states(EnumSet.allOf(States.class))
                .end(States.END);
    }

    // transition config
    public void configure(StateMachineTransitionConfigurer<States, Events> config) throws Exception {
        config.withExternal()
                .source(States.START)
                .target(States.STATE_1)
                .event(Events.EVENT_1)
                .and()
                .withExternal()
                .source(States.STATE_1)
                .target(States.STATE_2)
                .event(Events.EVENT_2)
                .and()
                .withExternal()
                .source(States.STATE_2)
                .target(States.END)
                .event(Events.LAST);

    }

    // listener
    private StateMachineListener<States, Events> demoListener() {
        return new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<States, Events> fromState, State<States, Events> toState) {
                if(fromState == null){
                    fromState = new EnumState<>(States.START);
                }
                log.info("state changed from {} to {}", fromState.getId(), toState.getId());
            }
        };
    }

}
