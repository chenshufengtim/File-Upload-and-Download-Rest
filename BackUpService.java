package com.tim.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.IOUtils;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.tim.utils.CommonUtils;

@Path("/file")
public class BackUpService {
	String uploadFinalDir;
	CommonUtils ct;

	@PostConstruct
	public void init() throws IOException {
		ct = new CommonUtils();
		uploadFinalDir = "C:\\Users\\TChen\\Documents\\Desktop\\2\\Automation\\RXDW Automation\\Upload Documents";
		ct.createFile(uploadFinalDir, "DIR");

	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response moveFiles(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
		String fileName = fileDetail.getFileName();
		String finalFilePath = uploadFinalDir + "\\" + fileName;
		ct.createFile(finalFilePath, "FILE");
		OutputStream out = new FileOutputStream(new File(finalFilePath));
		IOUtils.copy(uploadedInputStream, out);
		uploadedInputStream.close();
		out.close();
		String output = "File uploaded to : " + finalFilePath;
		return Response.status(200).entity(output).build();

	}

	@GET
	@Path("/Download/{FileName}")
	@Produces("application/vnd.ms-excel")
	public Response downloadExcel(@PathParam("FileName") String FileName) {
		File file = new File(uploadFinalDir + "\\" + FileName);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=" + file.getName());
		return response.build();

	}

}
