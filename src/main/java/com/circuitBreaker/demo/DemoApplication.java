package com.circuitBreaker.demo;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DemoApplication.class, args);
		Foo foo =new Foo(2);
        Random random = new Random();

        while (true) {
            if (foo.isServiceAvailable()) {
                boolean success = random.nextBoolean();
                if (success) {
                    foo.reportSuccess();
                    System.out.println("Request succeeded, state: " + foo.getState());
                } else {
                    foo.reportFailure();
                    System.out.println("Request failed, state: " + foo.getState());
                }
            } else {
                System.out.println("Service unavailable, state: " + foo.getState());
            }

            Thread.sleep(1000);
        }
	}

}
