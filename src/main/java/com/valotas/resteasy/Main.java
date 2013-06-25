package com.valotas.resteasy;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext spring = new AnnotationConfigApplicationContext(RestEasyConfig.class);
		//spring.refresh();
		spring.start();

		Scanner sc = new Scanner(System.in);
		while (sc.hasNext() && !"x".equals(sc.next())) {
			System.out.println("x + enter to terminate: ");
		}

		// at the end close the context:
		sc.close();
		spring.close();
		System.exit(0);
	}
}
