package com.rovicorp.compare_rm_images.applicationserver;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.rovicorp.compare_rm_images.resource.Get;


@ApplicationPath("/image")
public class CompareRmImagesApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(Get.class);
		return classes;
	}
}

