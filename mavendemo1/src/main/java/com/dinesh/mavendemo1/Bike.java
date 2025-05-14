package com.dinesh.mavendemo1;

import org.springframework.stereotype.Component;

@Component
public class Bike implements Vehicle{
	public void drive() {
		System.out.println(" i am dirving bike");
	}

}
