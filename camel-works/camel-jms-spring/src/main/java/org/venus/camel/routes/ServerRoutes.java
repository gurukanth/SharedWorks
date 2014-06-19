package org.venus.camel.routes;

import org.apache.camel.builder.RouteBuilder;

public class ServerRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("jms:queue:numbers").to("multiplier");
	}

}
