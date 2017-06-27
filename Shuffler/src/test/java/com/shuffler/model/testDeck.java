package com.shuffler.model;

import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

import com.shuffler.model.Card;
import com.shuffler.model.Deck;

public class testDeck {
	//Testing the Deck class
	@Test
	public void TestingSetup(){
		List<Card> llist = setupDeck();
		Deck<Card> deck = new Deck<Card>(llist);
	    assertEquals(deck.getDeck().size(),52);
	}
	@Test
	public void Test_handShuffle(){
		List<Card> llist = setupDeck();
		Deck<Card> deck = new Deck<Card>(llist,"handShuffle");
	    deck.shuffleDeck();
	    assertEquals(deck.getDeck().size(),52);
	}
	
	@Test
	public void Test_mongeanShuffle(){
		List<Card> llist = setupDeck();
		Deck<Card> deck = new Deck<Card>(llist,"mongeanShuffle");
		deck.shuffleDeck();
		assertEquals(deck.getDeck().size(),52);
	}
	
	@Test
	public void Test_default_Shuffle(){
		List<Card> llist = setupDeck();
		Deck<Card> deck = new Deck<Card>(llist,"");
	    deck.shuffleDeck();
	    assertEquals(deck.getDeck().size(),52);
	}
	
	private List<Card> setupDeck(){
		List<Card> llist = new LinkedList<Card>();
		String[] faceCards = {"J","Q","K","A"};
		String[] suits = {"spades","clubs","diamonds","hearts"};
		for(int i=2; i<=10; i++){
			for(int j=1; j<=4; j++){
				llist.add(new Card(Integer.toString(i), suits[j-1]));
			}
		}
		for(int k=1; k<=4; k++){
			for(int l=1; l<=4; l++){
				llist.add(new Card(faceCards[k-1],suits[l-1]));
			}
		}
		return llist; 
	}
		  
	
}
