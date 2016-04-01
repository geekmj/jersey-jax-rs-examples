package in.geekmj.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

/**
 * 
 * @author geekmj Three ways to get Form data in Jersey
 */
@Path("/form-data")
@Produces(MediaType.TEXT_HTML)
@Component
public class FormParamResource {

	/**
	 * Using @FormParam inject form data in method arguments
	 * 
	 * @param name
	 * @param phoneNumber
	 * @return
	 */
	@POST
	@Path("/form-param")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getFormDataUsingFormParam(@FormParam("name") String name, @FormParam("phone") String phoneNumber) {
		
		return Response.ok(getHtmlResponse(name, phoneNumber)).build();
	}

	/**
	 * Using Multivalued Map inject form data in method single map argument
	 * Note: It may not work if another Servlet is processing the request before Jersey Servlet or filter
	 * @param name
	 * @param phoneNumber
	 * @return
	 */
	@POST
	@Path("/multivalued-map")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getFormDataUsingMultivaluedMap(MultivaluedMap<String, String> formParams) {
		
		//Same as formValues.get("name").get(0);
		String name = formParams.getFirst("name");
		String phoneNumber = formParams.getFirst("phone");
		return Response.ok(getHtmlResponse(name, phoneNumber)).build();
	}

	/**
	 * Using Multivalued Map inject form data in method single map argument
	 * Note: It may not work if another Servlet is processing the request before Jersey Servlet or filter
	 * @param name
	 * @param phoneNumber
	 * @return
	 */
	@POST
	@Path("/context")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getFormDataUsingContext(@Context UriInfo ui) {
		
		MultivaluedMap<String, String> formValues = ui.getQueryParameters();
		
		//Same as formValues.get("name").get(0);
		String name = formValues.getFirst("name");
		String phoneNumber = formValues.getFirst("phone");
		return Response.ok(getHtmlResponse(name, phoneNumber)).build();
	}

	private String getHtmlResponse(String name, String phoneNumber) {
		StringBuilder responseStr = new StringBuilder("<html><head><title>Form Data</title></head><body>");
		responseStr.append("<h2>Submitted form data</h2><div><span>Name : "+name+"</span><br/><span>Phone : "+phoneNumber+"</span></div></body>");
		return responseStr.toString();
	}
}