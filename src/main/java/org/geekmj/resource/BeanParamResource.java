package org.geekmj.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.geekmj.model.BeanParamModel;
import org.springframework.stereotype.Component;

@Path("/bean-param")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
public class BeanParamResource {

	@GET
	@Path("{path-value}")
	public Response getResponse(@BeanParam BeanParamModel beanParam) {

		Map<String, String> parametersValues = new HashMap<String, String>();

		parametersValues.put("header-value", beanParam.getHeaderValue());
		parametersValues.put("cookie-value", beanParam.getCookieValue());
		parametersValues.put("path-value", beanParam.getPathValue());
		parametersValues.put("param1", beanParam.getParam1());

		return Response.ok(parametersValues).build();
	}
}