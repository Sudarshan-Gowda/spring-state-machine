package com.star.sud.statemachine;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
class MachineConfiguration extends EnumStateMachineConfigurerAdapter<BookStates, BookEvents> {

	@Override
	public void configure(StateMachineStateConfigurer<BookStates, BookEvents> states) throws Exception {
		states.withStates()
		.initial(BookStates.AVAILABLE)
		.states(EnumSet.allOf(BookStates.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<BookStates, BookEvents> transitions) throws Exception {
		transitions
		.withExternal()
		.source(BookStates.AVAILABLE)
		.target(BookStates.BORROWED)
		.event(BookEvents.BORROW)
		.and().withExternal()
		.source(BookStates.BORROWED)
		.target(BookStates.AVAILABLE)
		.event(BookEvents.RETURN)
		.and()
		.withExternal()
		.source(BookStates.AVAILABLE)
		.target(BookStates.IN_REPAIR)
		.event(BookEvents.START_REPAIR).and()
		.withExternal()
		.source(BookStates.IN_REPAIR)
		.target(BookStates.AVAILABLE)
		.event(BookEvents.END_REPAIR);
	}

	@Override
	public void configure(StateMachineConfigurationConfigurer<BookStates, BookEvents> config) throws Exception {
		config.withConfiguration().autoStartup(true).listener(new LoggingMashineListener());
	}
	 

}