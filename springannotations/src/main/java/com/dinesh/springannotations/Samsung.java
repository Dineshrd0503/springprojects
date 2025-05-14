package com.dinesh.springannotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class Samsung {
	@Autowired
	@Qualifier("mediaTek")
	Processor p;
	
	public Processor getP() {
		return p;
	}

	public void setP(Processor p) {
		this.p = p;
	}

	public void config() {
		System.out.println("Exynos 2300,50mp cam");
		p.process();
	}

}
