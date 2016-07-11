package org.geekmj.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

/**
 * Root resource (exposed at "/api/helloworld" path)
 */
@Component
@Path("/api/helloworld")
public class HelloWorldResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloMessage() {
		return "Hello World Jersey Way!";
	}
}
