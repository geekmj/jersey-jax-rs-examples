package in.geekmj.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import in.geekmj.resource.EmployeeResource;
import in.geekmj.resource.HelloWorldResource;
import in.geekmj.resource.HumansResource;
import in.geekmj.resource.MatrixUriResource;
import in.geekmj.resource.RequestCookiesResource;
import in.geekmj.resource.RequestHeaderResource;
import in.geekmj.resource.RequestParameterResource;

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
	}
}