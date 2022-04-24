package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.PowerConsumption;



@Path("/powerconsumption")

public class PowerConsumptionService {

	PowerConsumption consu = new PowerConsumption();
	

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	
	public String readPowerConsumption() 
	{ 
		return consu.readPowerConsumption(); 
	
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 

	public String insertPowerConsumption(@FormParam("c_ID") String c_ID, 
						@FormParam("c_commercial") String c_commercial, 
						@FormParam("c_agriculture") String c_agriculture, 
						@FormParam("c_residential") String c_residential,
						@FormParam("c_date") String c_date )
	
	{ 
	
		String output = consu.insertPowerConsumption( c_ID, c_commercial, c_agriculture, c_residential, c_date ); 
		return output; 

	}
}