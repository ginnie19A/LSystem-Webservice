package controller;

import java.sql.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import domain.Customer;
import service.CustomerService;
import service.CustomerServiceImpl;

@Path("/Customer")
public class CustomerController {
	private CustomerService CustomerService;

	public CustomerController() {
		this.CustomerService = new CustomerServiceImpl();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getCustomer(
			@QueryParam("FNAME") String FNAME, 
			@QueryParam("MNAME") String MNAME,
	        @QueryParam("LNAME") String LNAME,
	        @QueryParam("GENDER") String GENDER,
	        @QueryParam("BIRTHDATE") Date BIRTHDATE, 
	        @QueryParam("EXPDATE") Date EXPDATE,
	        @QueryParam("TOTALBAL") Float TOTALBAL){

		try {
			List<Customer> Customer;
			
			if (StringUtils.isAllBlank(FNAME, MNAME, LNAME)) {
				Customer = CustomerService.findAll();
			} else {
				Customer = CustomerService.findByName(FNAME, MNAME, LNAME);
			}
						
			return Customer;
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@GET
	@Path("{CUSTID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("CUSTID") String CUSTID) {

		try {
			int IntId = Integer.parseInt(CUSTID);
			Customer Customer = CustomerService.find(IntId);
			return Customer;
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCustomer(Customer Customer) {

		System.out.println("hello world");
		try {
			CustomerService.add(Customer);
			String result = "Customer saved : " + Customer.getFNAME() + " " + Customer.getMNAME() + " "+ Customer.getLNAME() + " " + Customer.getGENDER() + " " + Customer.getBIRTHDATE() + " " + Customer.getEXPDATE() + " " + Customer.getTOTALBAL();
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(e);
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(Customer Customer) {

		try {
			CustomerService.upsert(Customer);
			String result = "Customer updated : " + Customer.getFNAME() + " " + Customer.getMNAME() + " " + Customer.getLNAME() + " " + Customer.getGENDER() + " " + Customer.getBIRTHDATE() + " " + Customer.getEXPDATE() + " " + Customer.getTOTALBAL();
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}
	
	@PUT
	@Path("/updateBal")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBal(Customer Customer) {
       System.out.println("hello");
		try {
			CustomerService.updateBal(Customer);
			return Response.status(200).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(e);
		}

	}
	
	@PUT
	@Path("/updateBal1")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBal1(Customer Customer) {

		try {
			CustomerService.updateBal1(Customer);
			return Response.status(200).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	
}
