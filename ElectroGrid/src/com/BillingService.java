package com;

import model.Billing;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

@Path("/Billings")
public class BillingService {

	Billing billing = new Billing(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readBillings() { 
			return billing.readBillings(); 
	 }
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String inserBillings(@FormParam("customerName") String customerName,
	 @FormParam("amount") float amount, 
	 @FormParam("units") int units)
	{ 
	 String output = billing.insertBilling(customerName,amount,units); 
	return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateBilling(String updateBilling) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject updateBillingArgs = new JsonParser().parse(updateBilling).getAsJsonObject(); 
	//Read the values from the JSON object
	 int id = updateBillingArgs.get("id").getAsInt();
	 String customerName = updateBillingArgs.get("customerName").getAsString();
	 int units = updateBillingArgs.get("units").getAsInt(); 
	 float amount = updateBillingArgs.get("amount").getAsFloat(); 
	 
	 String output = billing.updateBilling( id,customerName,amount,units); 
	return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String deleteBilling) 
	{ 
	//Convert the input string to an XML document
		 JsonObject deleteBillingArgs = new JsonParser().parse(deleteBilling).getAsJsonObject(); 
	 
	//Read the value from the element <orderID>
		 int id = deleteBillingArgs.get("id").getAsInt();
	 String output = billing.deleteBilling( id); 
	return output; 
	}
}