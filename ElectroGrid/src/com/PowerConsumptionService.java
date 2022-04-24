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
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePowerConsumption(String itemData)
	{

		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();

		String pc_ID  = itemObject.get("pc_ID").getAsString();
		String c_ID    = itemObject.get("c_ID").getAsString();
		String c_commercial    = itemObject.get("c_commercial").getAsString();
		String c_agriculture   = itemObject.get("c_agriculture").getAsString();
		String c_residential       = itemObject.get("c_residential").getAsString();
		String c_date     = itemObject.get("c_date").getAsString();
	
		String output    = consu.updatePowerConsumption(pc_ID, c_ID, c_commercial, c_agriculture, c_residential, c_date );

		return output;		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String deletePowerConsumption(String itemData)
	{

		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		String pc_ID = doc.select("pc_ID").text();
	
		String output = consu.deletePowerConsumption(pc_ID);
	
		return output;
	}
}