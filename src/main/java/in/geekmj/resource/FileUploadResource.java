package in.geekmj.resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;

/**
 * 
 * @author geekmj Three ways to get Form data in Jersey
 */
@Path("/upload")

@Component
public class FileUploadResource {

	@Path("/file")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@DefaultValue("") @FormDataParam("tags") String tags, 
				@FormDataParam("file") InputStream file,
				@FormDataParam("file") FormDataContentDisposition fileDisposition) {

		String fileName = fileDisposition.getFileName();
		
		saveFile(file, fileName);
		
		String fileDetails = "File saved at /Volumes/Drive2/temp/file/" + fileName + " with tags "+ tags;

		System.out.println(fileDetails);

		return Response.ok(fileDetails).build();
	}
	
	@Path("/files")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFiles2(@DefaultValue("") @FormDataParam("tags") String tags, 
			@FormDataParam("file") List<FormDataBodyPart> bodyParts,
			@FormDataParam("file") FormDataContentDisposition fileDispositions) {
		
		StringBuffer fileDetails = new StringBuffer("");
		
		for( int i = 0 ; i < bodyParts.size(); i++ ) {
			 BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyParts.get(i).getEntity();
			String fileName = bodyParts.get(i).getContentDisposition().getFileName();	
			
			saveFile(bodyPartEntity.getInputStream(), fileName);
			
			fileDetails.append("File saved at /Volumes/Drive2/temp/file/" + fileName + " with tags "+ tags + "\n");
		}

		System.out.println(fileDetails);

		return Response.ok(fileDetails.toString()).build();
	}
	
	@Path("/files2")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFiles(final FormDataMultiPart multiPart) {
		
		List<FormDataBodyPart> bodyParts = multiPart.getFields("file");
		
		StringBuffer fileDetails = new StringBuffer("");
		
		for( int i = 0 ; i < bodyParts.size(); i++ ) {
			 BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyParts.get(i).getEntity();
			
			String fileName = bodyParts.get(i).getContentDisposition().getFileName();	
			
			saveFile(bodyPartEntity.getInputStream(), fileName);
			
			fileDetails.append("File saved at /Volumes/Drive2/temp/file/" + fileName + " with tags "+ multiPart.getField("tags").getValue() + "\n");
		}

		System.out.println(fileDetails);

		return Response.ok(fileDetails.toString()).build();
	}

	private void saveFile(InputStream file, String name) {
		try {
			/* Change directory path */
			java.nio.file.Path path = FileSystems.getDefault().getPath("/Volumes/Drive2/temp/file/" + name); 
			/* Save InputStream as file */
			Files.copy(file, path);
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

}