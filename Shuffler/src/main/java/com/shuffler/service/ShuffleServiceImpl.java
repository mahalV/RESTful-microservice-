package com.shuffler.service;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shuffler.model.Card;
import com.shuffler.model.Deck;

public class ShuffleServiceImpl implements ShuffleService{
	
	/*
	  ****************************
	   Finds the Deck by deckname
	  ****************************
	*/
	@Override
	public Response getDeckByName(String deckName, HttpServletRequest request){
		ServletContext ctx = request.getSession().getServletContext();
		  if(ctx != null){
		    Deck<Card> deck = (Deck<Card>)ctx.getAttribute("deck_" + deckName); 
		    if(deck != null){
		    	String output = convertToJson(deck) + "\n";
		    	return Response.status(200).entity(output).build();
		    }else{
		    	return Response.status(404).entity("Couldn't find deck -- " + deckName).build();
		    }
		  }else{
			  return Response.status(404).entity("Couldn't find or instantiate context.").build();
		  }
	}
	
	/*
	  ****************************
	   Find all of the Decks
	  ****************************
	*/
	@Override
	public Response getAllDecks(HttpServletRequest request) {
		  ServletContext ctx = request.getSession().getServletContext();
		  if(ctx != null){
			  String output = "";
			  for (Enumeration<String> e = ctx.getAttributeNames(); e.hasMoreElements() ;) {
				  String deckString = e.nextElement().toString();
				  if(deckString.contains("deck")){
					  output = output + deckString + ":\n" + convertToJson((Deck<Card>)ctx.getAttribute(deckString)) + "\n\n";
				  }
			  }
			  return Response.status(200).entity(output).build();
		  }else{
		    return Response.status(404).entity("Couldn't find or instantiate context.").build();
		  }
	}

	/*
	  ****************************
	   Creates the initial Deck 
	  ****************************
	*/
	@Override
	public Response createDeck(String deckName, HttpServletRequest request) {
		  ServletContext ctx = request.getSession().getServletContext();
		  if(ctx != null){
		    if(ctx.getAttribute("deck_" + deckName) == null){
		    	int rndAlg = (int)(Math.random()*2 + 1);       	//gets integer number either 1 or 2 for shuffleAlgorithm
				String shuffleAlgorithm = (String)ctx.getAttribute("shuffleAlgorithm"+rndAlg); 	//gets algorithm name
				Deck<Card> deck = new Deck<Card>(setupDeck(), shuffleAlgorithm);
		       	deck.setDeckName(deckName);
		    	ctx.setAttribute("deck_" + deckName, deck);
		    	String output = convertToJson(deck) + "\n"; 
		    	return Response.status(201).entity(output).build();
	        }else {
		    	return Response.status(404).entity(deckName + " --  deck already exists. Try another name").build();
		    }
		  }else{
		    return Response.status(404).entity("Couldn't find servlet context.").build();
		  }
	}

	/*
	  ****************************
	   Shuffles the specified Deck 
	  ****************************
	*/
	@Override
	public Response shuffleDeck(String deckName, HttpServletRequest request) {
		  ServletContext ctx = request.getSession().getServletContext();
		  if(ctx != null){
			  Deck<Card> deck = (Deck<Card>)ctx.getAttribute("deck_" + deckName);
			  if (deck != null){
				  deck.shuffleDeck();
				  ctx.setAttribute("deck_" + deckName,deck);
				  String output = convertToJson(deck); 
				  return Response.status(200).entity(output).build();
			  }else{
				  return Response.status(404).entity("Couldn't find deck name--"+deckName).build();
			  }
		  	}else{
		  		return Response.status(404).entity("Couldn't find servlet context.").build();
		  }
		  //return Response.status(200).entity(output).build();
	}

	/*
	  ****************************
	   Deletes the specified Deck 
	  ****************************
	*/
	@Override
	public Response deleteDeck(String deckName, HttpServletRequest request) {
		  ServletContext ctx = request.getSession().getServletContext();
		  if(ctx != null){
			  if (ctx.getAttribute("deck_" + deckName) != null){
				  ctx.removeAttribute("deck_" + deckName);
				  String output = "Removed deck -- " + deckName + ".\n";
				  return Response.status(200).entity(output).build();
			  }else{
				  return Response.status(404).entity(deckName + " --- deck is not available.").build();
			  }
		  }else{
		    return Response.status(404).entity("Couldn't find servlet context.").build();
		  }
		 
	}
	
	/*
	  *******************
	   Convert to JSON
	  *******************
	*/
	private String convertToJson(Deck<Card> deck){
	  JSONObject jsonObj = new JSONObject();
	  JSONArray jArray = new JSONArray();
	  List<Card> cards = deck.getDeck();
	  List<String> parsedCards = new LinkedList<String>();
	  int i = 1;
	  for(Card card: cards){
	    parsedCards.add(card.getCardValue() + "-" +  card.getSuit());
	    i++;
	  }
	
	  try {
		  jsonObj.put("deck", parsedCards);
		  jArray = jsonObj.getJSONArray("deck");
		
	  } catch (JSONException e) {
		e.printStackTrace();
	  }
	  	return jArray.toString();
	}
	
	/*
	  ********************
	   Setup Initial Deck
	  ********************
	*/
	private List<Card> setupDeck(){
	  List<Card> llist = new LinkedList<Card>();
	  String[] faceCards = {"J","Q","K","A"};
	  String[] suits = {"spades","clubs","diamonds","hearts"};
	 
	    for(int i=1; i<=4; i++){
	     for(int j=2; j<=10; j++){
	      llist.add(new Card(Integer.toString(j), suits[i-1]));
	     }
	     for(int k=1; k<=4; k++){
	      llist.add(new Card(faceCards[k-1], suits[i-1])); 
	     }
	  }
	  return llist;
	}

}
