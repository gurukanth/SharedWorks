package org.venus.camel.routes;

import org.apache.camel.builder.RouteBuilder;


public class ServiceRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("jms:queue:loginQueue").to("loginService");
		from("jms:queue:logoffQueue").to("logoffService");
	}
	
	

}
