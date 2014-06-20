package org.venus.camel.config;

import java.util.Properties;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.naming.NamingException;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.CamelContextFactoryBean;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.venus.camel.routes.ServiceRoute;
import org.venus.camel.services.LoginService;
import org.venus.camel.services.LogoffService;
import org.venus.camel.services.impl.LoginServiceImpl;
import org.venus.camel.services.impl.LogoffServiceImpl;

@Configuration
public class SpringConfig {
	
	@Inject
	private ApplicationContext applicationContext;
	
	@Bean
	public JndiTemplate jndi() throws Exception {
		Properties props = new Properties();
		props.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
		props.put("java.naming.provider.url", "jnp://localhost:1099");
		
		return new JndiTemplate(props);
	}
	
	@Bean
	public ConnectionFactory cf() throws NamingException, Exception {
		
		return (ConnectionFactory) jndi().lookup("/ConnectionFactory");
	}
	
	@Bean
	public JmsComponent jms() throws Exception {
		JmsComponent jms = new JmsComponent();
		jms.setConnectionFactory(cf());
		return jms;
	}
	
	@Bean
	public LoginService loginService() throws Exception {
		 return new LoginServiceImpl();
	}
	
	@Bean
	public LogoffService logoffService() throws Exception {
		return new LogoffServiceImpl();
	}
	
	@Bean
	public RouteBuilder serviceRoute() throws Exception {
		return new ServiceRoute();
	}
	
	@Bean
	public CamelContext camelContext() throws Exception {
		CamelContextFactoryBean fb = new CamelContextFactoryBean();
		fb.setApplicationContext(applicationContext);
		fb.setId("camel-server");
		SpringCamelContext camelContext = fb.getContext();
		serviceRoute().addRoutesToCamelContext(camelContext);
		return camelContext;
	}
	
	
	
}
