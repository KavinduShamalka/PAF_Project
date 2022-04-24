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

import model.MaintainUser;

@Path("/user")
public class MaintainUserService {

	MaintainUser itemObj = new MaintainUser();
	

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	
	public String readUserDetails() 
	{ 
		return itemObj.readUserDetails(); 
	
	}
	
	
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 

	public String insertUserDetails(@FormParam("f_Name") String f_Name, 
						@FormParam("l_Name") String l_Name, 
						@FormParam("address") String address, 
						@FormParam("phone") String phone,
						@FormParam("mail") String mail, 
						@FormParam("city") String city )
	
	{ 
	
		String output = itemObj.insertUserDetails(f_Name, l_Name, address, phone, mail, city); 
		return output; 
	}








	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateUserDetails(String itemData)
	{

		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();

		String u_id  = itemObject.get("u_id").getAsString();
		String f_name    = itemObject.get("f_name").getAsString();
		String l_name    = itemObject.get("l_name").getAsString();
		String address   = itemObject.get("address").getAsString();
		String phone     = itemObject.get("phone").getAsString();
		String mail      = itemObject.get("mail").getAsString();
		String city      = itemObject.get("city").getAsString();
	
		String output    = itemObj.updateUserDetails(u_id, f_name, l_name, address, phone, mail, city );
		
		return output;
		
		}
			
		










	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String deleteUserDetails(String itemData)
	{

		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		String user_id = doc.select("user_id").text();
	
		String output = itemObj.deleteUserDetails(user_id);
	
		return output;
	}
}
