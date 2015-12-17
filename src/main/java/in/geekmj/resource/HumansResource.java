package in.geekmj.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/humans")
public class HumansResource {
	/**
	 * 
	 * @return Response (Doesn't Contain Anything)
	 */
	@GET
	@Produces("text/plain")
	public Response getEmployees() {

		return Response.accepted("Test /humans api called successfully.").build();
	}

	@GET
	@Path("{humanId: [0-9]*}")
	@Produces("text/plain")
	public Response getEmployee(@PathParam("humanId") int humanId) {

		return Response.accepted("Test /humans/" + humanId + " api called successfully.").build();
	}

	@GET
	@Path("{humanId}/addresses")
	@Produces("text/plain")
	public Response getAddresses(@PathParam("humanId") int humanId) {

		return Response.accepted("Test /humans/" + humanId + "/addresses api called successfully.").build();
	}

	@GET
	@Path("{humanId}/addresses/{addressId}")
	@Produces("text/plain")
	public Response getAddress(@PathParam("humanId") int humanId, @PathParam("addressId") int addressId) {

		return Response
				.accepted("Test /humans/" + humanId + "/addresses/addresses/" + addressId + " api called successfully.")
				.build();
	}

}
