package com.shuffler.model;

public class Card{

	private String cardValue;
	private String suit;
	
	public Card(String cardValue, String suit){
	  this.cardValue = cardValue;
	  this.suit = suit;
	}
	
	  public void setCardValue(String cardValue){
	    this.cardValue = cardValue;
	  }
	  public String  getCardValue(){
	    return this.cardValue;
	  }
	  public void  setSuit(String suit){
	    this.suit = suit;
	  }
	  public String  getSuit(){
	    return this.suit;
	  }
	
}
