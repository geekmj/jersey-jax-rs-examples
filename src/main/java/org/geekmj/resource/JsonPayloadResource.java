package org.geekmj.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.geekmj.domain.Movie;

@Path("/json-payload")
public class JsonPayloadResource {

	/**
	 * This Resource method takes HTTP entity pay-load in JSON format. 
	 * This Resource method gives back HTTP entity pay-load in JSON format. 
	 * Jackson is the default JSON Entity provider for Spring Boot + Jersey application.
	 * It convert JSON in the pay-load to Java Object. 
	 * It also convert Java Object to JSON.
	 */

	@POST
	@Path("/movie")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Movie takeAndGiveMovie(final Movie movie) {

		System.out.println("Movie Title " + movie.getTitle());
		
		/* Appending (Checked) to title, It will show in response */
		movie.setTitle(movie.getTitle().concat("(Checked)"));
		
		return movie;

	}
}
