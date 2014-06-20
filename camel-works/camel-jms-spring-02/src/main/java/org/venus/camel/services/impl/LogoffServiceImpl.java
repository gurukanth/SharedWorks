package org.venus.camel.services.impl;

import org.venus.camel.beans.User;
import org.venus.camel.services.LogoffService;

public class LogoffServiceImpl implements LogoffService {

	@Override
	public void doLogoff(User user) {
		
		System.out.println("log off");
	}

}
