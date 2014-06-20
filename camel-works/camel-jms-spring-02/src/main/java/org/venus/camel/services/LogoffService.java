package org.venus.camel.services;

import org.apache.camel.InOnly;
import org.venus.camel.beans.User;


@InOnly
public interface LogoffService {
	
	void doLogoff(User user);

}
