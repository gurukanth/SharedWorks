package org.apache.camel.client;

import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelClient {
	
	public static void main(String[] args){
		
		System.out.println("The Camel Server should already be running for this client........");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("camel-client.xml");
		ProducerTemplate camelTemplate = context.getBean("camelTemplate", ProducerTemplate.class);
		
		System.out.println("invoking the multiplier with 3.............");
		
		int response = (Integer) camelTemplate.sendBody("jms:queue:numbers", ExchangePattern.InOut, 3);
		
		System.out.println("the result is ........." + response);
		
		System.exit(0);
	}

}
