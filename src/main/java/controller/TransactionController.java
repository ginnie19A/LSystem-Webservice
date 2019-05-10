package controller;

import java.sql.Date;
import java.util.List;
import javax.ws.rs.Consumes;
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
import domain.Transaction;
import service.TransactionService;
import service.TransactionServiceImpl;

@Path("/Transaction")
public class TransactionController {

	private TransactionService TransactionService;

	public TransactionController() {
		this.TransactionService = new TransactionServiceImpl();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getTransaction(
			@QueryParam("TRANSDESC") String TRANSDESC, 
			@QueryParam("POINTSAMT") Float POINTSAMT,
	        @QueryParam("TRANSDATE") Date TRANSDATE,
	        @QueryParam("CUSTID") String CUSTID){
		try {
			List<Transaction> Transaction;
			
			if (StringUtils.isAllBlank(TRANSDESC, CUSTID)) {
				Transaction = TransactionService.findAll();
			} else {
				Transaction = TransactionService.findByName(TRANSDESC, CUSTID);
			}
						
			return Transaction;
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@GET
	@Path("{TRANSID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction getTransaction(@PathParam("TRANSID") String TRANSID) {

		try {
			int IntId = Integer.parseInt(TRANSID);
			Transaction Transaction = TransactionService.find(IntId);
			return Transaction;
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTransaction(Transaction Transaction) {

		try {
			TransactionService.add(Transaction);
			String result = "Transaction saved : " + Transaction.getTRANSDESC() + " " + Transaction.getPOINTSAMT() + " "+ Transaction.getTRANSDATE() + " " + Transaction.getCUSTID();
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTransaction(Transaction Transaction) {

		try {
			TransactionService.upsert(Transaction);
			String result = "Transaction updated : " + Transaction.getTRANSDESC() + " " + Transaction.getPOINTSAMT() + " "+ Transaction.getTRANSDATE() + " " + Transaction.getCUSTID();
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	
}
