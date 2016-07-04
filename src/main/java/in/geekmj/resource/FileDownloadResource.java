package in.geekmj.resource;

import java.io.File;
import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * @author geekmj
 * 
 *         File download example
 */
@Path("/download")
@Component
public class FileDownloadResource {

	@Autowired
	private ResourceLoader resourceLoader;

	@Path("/any-file-type/{type}")
	@GET
	public Response anyFileType(@PathParam("type") String fileType) {

		/*
		 * We are loading file kept in resources/static/ folder using Spring
		 * ResourceLoader classpath mechanism. We have following files: 1.
		 * sample.doc 2. sample.jpg 3. sample.pdf 4. sample.png Based on type
		 * passed, we load sample.<type> from resources folder. We are using
		 * Spring ResourceLoader to load file.
		 */
		File file = null;
		String mimeType;
		try {

			file = resourceLoader.getResource("classpath:static/sample." + fileType).getFile();

			/*
			 * We can also use normal File constructor to load a file. For e.g.
			 * File file = new File("C:\sample.pdf");
			 */

			if (!file.exists()) {
				throw new WebApplicationException(404);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		/* Finding MIME type for explicitly setting MIME */
		mimeType = new MimetypesFileTypeMap().getContentType(file);

		ResponseBuilder rb = Response.ok(file, mimeType);

		/*
		 * Setting file name and making sure file download instead of showing in
		 * browser
		 */
		rb.header("Content-Disposition", "inline; filename=sample." + fileType);

		return rb.build();
	}

	/*
	 * Setting MIME type explicitly using @Produces annotation. Content will
	 * displayed in browser if possible.
	 */
	@Path("/png-file/")
	@GET
	@Produces("image/png")
	public Response pngFileType() {
		File file = null;
		try {

			file = resourceLoader.getResource("classpath:static/sample.png").getFile();

			if (!file.exists()) {
				throw new WebApplicationException(404);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		ResponseBuilder rb = Response.ok(file);

		/* Setting file name */
		rb.header("Content-Disposition", "attachment; filename=sample.png");

		return rb.build();
	}

}