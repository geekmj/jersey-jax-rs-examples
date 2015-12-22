package in.geekmj.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
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
		
		register(MatrixUriResource.class);
		register(RequestCookiesResource.class);
		register(RequestHeaderResource.class);
		register(RequestParameterResource.class);
		register(EmployeeResource.class);
		register(HumansResource.class);
		register(HelloWorldResource.class);
	}
}