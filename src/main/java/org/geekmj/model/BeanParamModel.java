package org.geekmj.model;

import javax.ws.rs.CookieParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.jvnet.hk2.annotations.Optional;

public class BeanParamModel {

	@HeaderParam(value = "header-value")
	private String headerValue;

	@CookieParam(value = "cookie-value")
	private String cookieValue;

	private String pathValue;

	private String param1;

	public BeanParamModel(@PathParam("path-value") @Optional String pathValue,
			@QueryParam("param1") @Optional String param1) {
		this.pathValue = pathValue;
		this.param1 = param1;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public String getCookieValue() {
		return cookieValue;
	}

	public void setCookieValue(String cookieValue) {
		this.cookieValue = cookieValue;
	}

	public String getPathValue() {
		return pathValue;
	}

	public void setPathValue(String pathValue) {
		this.pathValue = pathValue;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

}
