package in.geekmj.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Path("/request-cookie")
@Produces(MediaType.APPLICATION_JSON)
public class RequestCookiesResource {

	/*
	 * We can inject request cookie as an instance variables using @CookieParam
	 */

	@CookieParam("token")
	private String token;

	/* We can inject request cookie values in method using @CookieParam */
	@GET
	public Map<String, String> getRequestCookie(@CookieParam("content-type") String contentType) {

		Map<String, String> requestCookies = new HashMap<String, String>();
		requestCookies.put("token", token);
		requestCookies.put("contentType", contentType);
		return requestCookies;
	}

	/*
	 * We can get a map of all request headers name and value using HttpHeaders
	 * context injection
	 */

	@SuppressWarnings("rawtypes")
	@GET
	@Path("/all")
	public Map getAllRequestCookiesUsingContext(@Context HttpHeaders headers) {
		return headers.getCookies();
	}
}
