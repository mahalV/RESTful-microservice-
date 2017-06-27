package com.shuffler.model;

import java.util.LinkedList;
import java.util.List;

public class Deck<T>{
	
	private List<T> deck;
	private String  shuffleAlgorithm = "";
	private String  deckName;
	
	//Constructors
	public Deck(List<T> deck){
	   this.deck = deck;
	}
	public Deck(List<T> deck, String shuffleAlgorithm){
	   this.deck = deck;
	   this.shuffleAlgorithm = shuffleAlgorithm;
	}
	
	//Accessors/mutators
	public List<T> getDeck(){
	   return this.deck;
	}
	public void setDeck(List<T> deck){
	   this.deck = deck;
	}
	public String getShuffleAlgorithm(){
	   return this.shuffleAlgorithm;
	}
	public void setShuffleAlgorithm(String shuffleAlgorithm){
	  this.shuffleAlgorithm = shuffleAlgorithm;
	}
	public String getDeckName(){
	   return this.deckName;
	}
	public void setDeckName(String deckName){
	   this.deckName = deckName;
	}
	  /*
	  =====================
	  Shuffle Functionality
	  =====================
	 */
	public List<T> shuffleDeck(){
	  switch(shuffleAlgorithm){
	    case "mongeanShuffle":
	      this.deck = mongeanShuffle(this.deck);
	      break;
	    case "complexHandShuffle":
	       int randomItr = (int)(Math.random()*5 + 1); //Random int between 1 and 5 to determine no of times shuffling
	       for(int i=0; i<randomItr; i++)
	       {
	       this.deck = handShuffle(this.deck);
	       break;
	       }
	    default:
	       this.deck = quarterShuffle(this.deck);
	  }
	  return this.deck;
	}
	/*
	Algorithm:
	The Mongean shuffle, or Monge's shuffle, is performed as follows (by a right-handed person): 
	Start with the unshuffled deck in the left hand and transfer the top card to the right. Then 
	repeatedly take the top card from the left hand and transfer it to the right, putting the 
	second card at the top of the new deck, the third at the bottom, the fourth at the top, the 
	fifth at the bottom, etc. The result, if one started with cards numbered consecutively
	{1,2,3,4,5,6,.....,2n} would be a deck with the cards in the following order: 
	{ 2n,2n-2,2n-4,...,4,2,1,3,.....,2n-3,2n-1}
	*/
	private List<T> mongeanShuffle(List<T> deck){
	  LinkedList<T> shuffledDeck = new LinkedList<T>();
	  for(int i=1; i<=deck.size(); i++){
	    if((i%2)==0){
	    	shuffledDeck.addLast(deck.get(i-1)); //Add to top of deck
	    }else{
	    	shuffledDeck.addFirst(deck.get(i-1)); //Add to bottom of deck
	    }
	  }
	  return shuffledDeck;
	}
	
	
    /*
	Algorithm:
	Hand-shuffling performed as follows: Split deck into two halves and then interleave two halves,
	*/
	private List<T> handShuffle(List<T> deck){
	   	  List<T> halfDeck_1   = new LinkedList<T>();
		  List<T> halfDeck_2   = new LinkedList<T>();
		  List<T> shuffledDeck = new LinkedList<T>();
		  for(int i=0; i < deck.size(); i++){
			  if(i<(deck.size()/2)){
				  halfDeck_1.add(deck.get(i));
			  }else{
				  halfDeck_2.add(deck.get(i));
			  }
		  }
		  //Assume there are two equal halves
		  if(halfDeck_1.size()==halfDeck_2.size()){
			  int randomHalf = (int)(Math.random()*2 + 1); // Random int between 1 and 2 to determine start of first or second half of the deck. 
			  for(int j=0; j < halfDeck_1.size(); j++){
				  if(randomHalf == 1){
					  shuffledDeck.add(halfDeck_1.get(j));
					  shuffledDeck.add(halfDeck_2.get(j));
				  }else{
					  shuffledDeck.add(halfDeck_2.get(j));
					  shuffledDeck.add(halfDeck_1.get(j));
				  }
	      
			  }
		  }else{
			  shuffledDeck=deck;
		  }
		  return shuffledDeck;
	}
	
	/*
	Algorithm:
	Split deck into quarters.
	Add a card from each quarter to shuffleddeck and repeat 
	*/
	private List<T> quarterShuffle(List<T> deck){
	  List<T> shuffledDeck = new LinkedList<T>();
	
	  List<T> quarterDeck_1   = new LinkedList<T>();
	  List<T> quarterDeck_2   = new LinkedList<T>();
	  List<T> quarterDeck_3   = new LinkedList<T>();
	  List<T> quarterDeck_4   = new LinkedList<T>();
	
	  //Assume that deck can be divided into quarters
	  if((deck.size()%4) == 0){
	    int quarterNum = deck.size()/4; 
	    for(int i=0; i<quarterNum; i++){
	      quarterDeck_1.add(deck.get(i));
	      quarterDeck_2.add(deck.get(i + quarterNum));
	      quarterDeck_3.add(deck.get(i + (2*quarterNum)));
	      quarterDeck_4.add(deck.get(i + (3*quarterNum)));
	    }
	    for(int k=0; k<quarterNum; k++){
	      shuffledDeck.add(quarterDeck_1.get(k));
	      shuffledDeck.add(quarterDeck_2.get(k));
	      shuffledDeck.add(quarterDeck_3.get(k));
	      shuffledDeck.add(quarterDeck_4.get(k));
	    }
	  }
	  return shuffledDeck;
	}

}
