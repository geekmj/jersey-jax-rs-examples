package org.geekmj.config;

import javax.ws.rs.ApplicationPath;

import org.geekmj.resource.EmployeeResource;
import org.geekmj.resource.HelloWorldResource;
import org.geekmj.resource.HumansResource;
import org.geekmj.resource.JsonPayloadResource;
import org.geekmj.resource.MatrixUriResource;
import org.geekmj.resource.RequestCookiesResource;
import org.geekmj.resource.RequestHeaderResource;
import org.geekmj.resource.RequestParameterResource;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

	/**
	 * In constructor we can define Jersey Resources & Other Components
	 */
	public JerseyConfig() {
		
		/*
		 * Jersey will automatically register class with @provider, @Component  by scanning
		 * these packages + nested packages
		 */
		packages("in.geekmj.resource", "in.geekmj.config");
		
		/* CustomTypeParamterConsumeResource auto scanned and register */
		//register(CustomTypeParamterConsumeResource.class);
		register(MatrixUriResource.class);
		register(RequestCookiesResource.class);
		register(RequestHeaderResource.class);
		register(RequestParameterResource.class);
		register(EmployeeResource.class);
		register(HumansResource.class);
		register(HelloWorldResource.class);
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
		register(MultiPartFeature.class);
		register(JsonPayloadResource.class);
	}
}