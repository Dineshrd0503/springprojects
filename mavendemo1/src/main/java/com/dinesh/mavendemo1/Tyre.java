package com.dinesh.mavendemo1;

import org.springframework.stereotype.Component;

@Component
public class Tyre {
	private String brand;
	
	public String getbrand() {
		return brand;
	}
	public void setbrand(String brand) {
		this.brand=brand;
	}
	@Override
	public String toString() {
		return "Tyre [brand=" + brand + "]";
	}

}
