package com.rovicorp.compare_rm_images_service.resources;

import com.rovicorp.compare_rm_images_service.services.GetImageSize;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.rovicorp.compare_rm_images_service.services.GetNewImageData;
import com.rovicorp.compare_rm_images_service.services.ReturnRandomImageData;
import com.rovicorp.compare_rm_images_service.services.ReturnRecentImageData;
import com.rovicorp.compare_rm_images_service.services.ReturnSpecificImageData;


@ApplicationPath("/compare_rm_images_service")
public class CompareRmImagesApplication extends Application{	
	@Override
	public Set<Class<?>> getClasses() {
            HashSet<Class<?>> classes = new HashSet<Class<?>>();
            classes.add(ReturnRandomImageData.class);
            classes.add(ReturnRecentImageData.class);
            classes.add(ReturnSpecificImageData.class);
            classes.add(GetNewImageData.class);
            classes.add(GetImageSize.class);
            return classes;
	}
}

