package in.geekmj.resource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import in.geekmj.model.ConstructorDateParamModel;
import in.geekmj.model.DateParamModel;
import in.geekmj.model.StaticMethodDateParamModel;

@Path("/custom-type-for-parameters")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
public class CustomTypeParamterConsumeResource {

	@QueryParam("datesAsStrings")
	private List<DateParamModel> dateParamModels;

	@CookieParam("dateParamModelAsCookie")
	private StaticMethodDateParamModel cookieDateParamModel;

	@HeaderParam("dateParamModelAsHeader")
	private ConstructorDateParamModel headerDateParamModel;

	@GET
	public Response getCustomType(@QueryParam("dateAsString") DateParamModel dateParamModel) {

		Map<String, Object> result = new HashMap<String, Object>();

		boolean error = false;

		if (dateParamModel != null && dateParamModel.getDate() != null) {
			result.put("dateAsString", dateParamModel.getDate());
		} else if (dateParamModel != null) {
			error = true;
		}

		if (headerDateParamModel != null && headerDateParamModel.getDate() != null) {
			result.put("dateParamModelAsHeader", headerDateParamModel.getDate());
		} else if (headerDateParamModel != null) {
			error = true;
		}

		if (cookieDateParamModel != null && cookieDateParamModel.getDate() != null) {
			result.put("dateParamModelAsCookie", cookieDateParamModel.getDate());
		} else if (cookieDateParamModel != null) {
			error = true;
		}

		if (dateParamModels != null && dateParamModels.size() > 0) {
			List<LocalDateTime> localDateTimes = new ArrayList<LocalDateTime>();

			for (DateParamModel dateParamModelTemp : dateParamModels) {
				if (dateParamModelTemp.getDate() != null) {
					localDateTimes.add(dateParamModelTemp.getDate());
				} else {
					error = true;
				}
			}

			result.put("datesAsStrings", localDateTimes);
		}

		return error ? Response.status(Status.BAD_REQUEST)
				.entity("{'error:'Expected dateAsString in ISO DATE format''}").build()
				: Response.ok().entity(result).build();
	}

}
