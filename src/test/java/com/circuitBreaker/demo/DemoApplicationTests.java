package com.circuitBreaker.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	
	@Test
	void getSuccessState(){
		Random random = new Random();
		Foo foo = new Foo(4);
		for(int i = 0; i < 10; i++) {
			boolean rand = random.nextBoolean();
			if(foo.isServiceAvailable()) {
				foo.reportSuccess();
			}
		}
		assertEquals(State.CLOSED, foo.getState());
	}

	@Test
	void getFailureStateAfterReachingThreshold() {
//		keeping the threshold to 2
//		if failure rate exceeds threshold the circuit is open 
		Foo foo = new Foo(2);
		for(int i = 0; i <= 3; i++) {
			if(foo.isServiceAvailable()) {
				foo.reportFailure();
			}
		}
		assertEquals(State.OPEN, foo.getState());
	}

}
