package org.geekmj.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/request-parameters")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RequestParameterResource {

	/*
	 * We can inject request parameters as instance variables using @QueryParam
	 */

	@QueryParam("email")
	private String email;

	/* We can inject request parameters as method s using @QueryParam */

	@GET
	public Map<String, String> getRequestParameters(@QueryParam("name") String name,
			@DefaultValue("18") @QueryParam("age") int age) {

		Map<String, String> requestParametersAndValues = new HashMap<String, String>();
		requestParametersAndValues.put("name", name);
		requestParametersAndValues.put("age", String.valueOf(age));
		requestParametersAndValues.put("email", email);

		return requestParametersAndValues;
	}

	/*
	 * We can get a map of all request parameters name and value using UriInfo
	 * context injection
	 */

	@GET
	@Path("/all")
	public Map<String, List<String>> getRequestParametersUsingContext(@Context UriInfo ui) {

		return ui.getQueryParameters();
	}
}
