package org.geekmj.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/matrix-uri")
@Produces(MediaType.APPLICATION_JSON)
public class MatrixUriResource {

	/*
	 * We can inject matrix URI parameter as an instance variables
	 * using @MatrixParam
	 */

	@MatrixParam("lat")
	private Integer latitude;

	@MatrixParam("long")
	private Integer longitude;

	/* We can inject matrix URI parameter method using @MatrixParam */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GET
	public Map getRequestCookie(@MatrixParam("scale") Integer scale, @MatrixParam("type") String type) {

		Map matrixParams = new HashMap();
		matrixParams.put("latitude", latitude);
		matrixParams.put("longitude", longitude);
		matrixParams.put("scale", scale);
		matrixParams.put("type", type);
		return matrixParams;
	}

}
