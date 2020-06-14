package com.star.sud;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;

import com.star.sud.statemachine.BookEvents;
import com.star.sud.statemachine.BookStates;
import com.star.sud.statemachine.LoggingUtils;

@SpringBootApplication
@EnableStateMachine
public class SpringStateMachineApplication implements CommandLineRunner {

	private static Logger logger = LoggingUtils.LOGGER;

	private StateMachine<BookStates, BookEvents> stateMachine;

	@Autowired
	public SpringStateMachineApplication(StateMachine<BookStates, BookEvents> stateMachine) {
		this.stateMachine = stateMachine;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringStateMachineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean returnAccepted = stateMachine.sendEvent(BookEvents.RETURN);
		logger.info("return accepted: " + returnAccepted);
		boolean borrowAccepted = stateMachine.sendEvent(BookEvents.BORROW);
		logger.info("borrow accepted: " + borrowAccepted);
		returnAccepted = stateMachine.sendEvent(BookEvents.RETURN);
		logger.info("return accepted: " + returnAccepted);

	}

}
