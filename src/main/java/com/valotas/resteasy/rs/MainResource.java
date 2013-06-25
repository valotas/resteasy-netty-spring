package com.valotas.resteasy.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Controller;

@Controller
@Path("/")
public class MainResource {

	@XmlRootElement
	public static final class MainResponse {
		private String name;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public MainResponse get(@PathParam("name") String name) {
		MainResponse resp = new MainResponse();
		resp.setName(name);
		return resp;
	}
}
