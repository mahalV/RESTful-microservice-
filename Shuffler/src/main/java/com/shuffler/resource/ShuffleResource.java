package com.shuffler.resource;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.shuffler.service.*;

@Path("/decks")
public class ShuffleResource {
	
	ShuffleService shuffleServ = new ShuffleServiceImpl();
	
	//GET a named deck in its current sorted/shuffled order.
	@GET
	@Path("/{deckName}")
	public Response getDeck(@PathParam("deckName") String deckName, @Context HttpServletRequest request){
		return shuffleServ.getDeckByName(deckName, request);	
	}
	
	// GET a list of the current decks persisted in the service.
	@GET
	public Response getDecks(@Context HttpServletRequest request){
		return shuffleServ.getAllDecks(request);
	}
	
	// New deck is created in some initial sorted order. 
	@PUT 
	@Path("/{deckName}")
	public Response putDeck(@PathParam("deckName") String deckName, @Context HttpServletRequest request){
		return shuffleServ.createDeck(deckName, request);
	}
	
	// Shuffles an existing named deck.
	@POST
	@Path("/{deckName}")
	public Response shuffleDeck(@PathParam("deckName") String deckName, @Context HttpServletRequest request){
		return shuffleServ.shuffleDeck(deckName, request);
	}
	
	// DELETE a named deck.
	@DELETE
	@Path("/{deckName}")
	public Response removeDeck(@PathParam("deckName") String deckName, @Context HttpServletRequest request){
		return shuffleServ.deleteDeck(deckName, request);
	}
}
