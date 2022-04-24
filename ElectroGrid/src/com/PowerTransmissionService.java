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

import model.PowerTransmission;



@Path("/power")

public class PowerTransmissionService {
	
	PowerTransmission power = new PowerTransmission();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	
	public String readPowerTransmission() 
	{ 
		return power.readPowerTransmission(); 
	
	}
	
	
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 

	public String insertPowerTransmission(@FormParam("t_ID") String t_ID, 
						@FormParam("t_Acommercial") String t_Acommercial, 
						@FormParam("t_Aresidential") String t_Aresidential, 
						@FormParam("t_Aagriculture") String t_Aagriculture,
						@FormParam("t_date") String t_date)
	
	{ 
	
		String output = power.insertPowerTransmission(t_ID, t_Acommercial, t_Aresidential, t_Aagriculture, t_date); 
		return output; 

	}

	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String itemData)
	{

		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();

		String t_ID  = itemObject.get("t_ID").getAsString();
		String t_Acommercial    = itemObject.get("t_Acommercial").getAsString();
		String t_Aresidential    = itemObject.get("t_Aresidential").getAsString();
		String t_Aagriculture   = itemObject.get("t_Aagriculture").getAsString();
		String t_date       = itemObject.get("t_date").getAsString();
		
	
		String output    = power.updatePowerTransmission(t_ID, t_Acommercial, t_Aresidential, t_Aagriculture, t_date );

		return output;
		
}





	
}
