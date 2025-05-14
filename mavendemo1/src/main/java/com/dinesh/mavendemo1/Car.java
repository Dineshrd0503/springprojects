package com.dinesh.mavendemo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {
	@Autowired
	private Tyre t;
	public Tyre gettyre() {
		return t;
	}
	public void setyre() {
		this.t=t;
	}
    public void drive() {
    	System.out.println("i amdriving car wiht tyres "+t);
    }
}

