package com.dinesh.mavendemo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
	ApplicationContext context = new ClassPathXmlApplicationContext("com/dinesh/mavendemo1/spring.xml");
    System.out.println( "Hello World!" );
//        Vehicle c=(Vehicle)context.getBean("bike");
//        c.drive();
    	Car c=(Car) context.getBean("car");
    	c.drive();
    }
}
