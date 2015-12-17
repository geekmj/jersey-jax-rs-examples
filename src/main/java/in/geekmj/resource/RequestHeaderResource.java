package in.geekmj.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Path("/request-header")
@Produces(MediaType.APPLICATION_JSON)
public class RequestHeaderResource {

	/*
	 * We can inject request header as an instance variables using @HeaderParam
	 */

	@HeaderParam("token")
	private String token;

	/* We can inject request header values in method using @HeaderParam */
	@GET
	public Map<String, String> getRequestHeaders(@HeaderParam("content-type") String contentType) {

		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("token", token);
		requestHeaders.put("contentType", contentType);
		return requestHeaders;
	}

	/*
	 * We can get a map of all request headers name and value using HttpHeaders
	 * context injection
	 */

	@SuppressWarnings("rawtypes")
	@GET
	@Path("/all")
	public Map getAllRequestHeadersUsingContext(@Context HttpHeaders headers) {
		return headers.getRequestHeaders();
	}
}
