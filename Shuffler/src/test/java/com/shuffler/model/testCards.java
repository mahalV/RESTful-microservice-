package com.shuffler.model;

import static org.junit.Assert.*;


import org.junit.Test;

import com.shuffler.model.Card;

public class testCards {
	// Testing Card class
	@Test
	public void Cardtest() {
		    Card card = new Card("2","heart");
		    assertEquals(card.getCardValue(),"2");
		    assertEquals(card.getSuit(),"heart");
	}

}
