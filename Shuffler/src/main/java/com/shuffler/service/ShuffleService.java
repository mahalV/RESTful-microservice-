package com.shuffler.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

public interface ShuffleService {
	public Response getDeckByName(String deckName, HttpServletRequest request);
	public Response getAllDecks(HttpServletRequest request);
	public Response createDeck(String deckName, HttpServletRequest request);
	public Response shuffleDeck(String deckName, HttpServletRequest request);
	public Response deleteDeck(String deckName, HttpServletRequest request);
}
