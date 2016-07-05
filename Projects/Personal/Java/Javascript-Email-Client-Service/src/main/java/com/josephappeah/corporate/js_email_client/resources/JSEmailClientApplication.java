package com.josephappeah.corporate.js_email_client.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.josephappeah.corporate.js_email_client.service.JSEmailClientService;

@ApplicationPath("/v1")
public class JSEmailClientApplication extends Application{
	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(JSEmailClientService.class);
		return classes;
	}
}