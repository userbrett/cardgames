/* 
 * CardDeck.java
 * Brett Lee <brett@brettlee.com>
 *
 * Simple implementation of a deck of cards.  Provides the functionalities
 * of shuffling and dealing.  
 */

package com.brettlee.cardgames;

import java.util.ArrayList;
import java.util.Iterator;

public class CardDeck {

	// Instantiate an ArrayList to hold the entire deck of cards
	ArrayList<String> theDeck = new ArrayList<String>();

	// Construct two arrays to hold the card permutations
	String[] suitsArray = { "Diamonds", "Hearts", "Clubs", "Spades" };
	String[] cardsArray = { "Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
			"Queen", "King" };

	/*
	 * newDeck method ---------------------------
	 */
	public void newDeck() {

		// throw the deck away
		theDeck.clear();

		// add cards to the ArrayList to resemble a newly opened deck
		for (String i : suitsArray) {
			for (String j : cardsArray) {
				// System.out.println( "Adding: " + j.concat(" of ").concat(i)
				// ); //debug
				theDeck.add(j.concat(" of ").concat(i));
				// System.out.println( "ArrayList is size: " + theDeck.size() );
				// //debug
			}
		}
		System.out.println("A new deck of cards has been opened.");
	}

	/*
	 * shuffleCards method ---------------------------
	 */
	public void shuffleCards() {

		newDeck(); // populate the deck with 52 cards

		// let the API do the work. :)
		java.util.Collections.shuffle(theDeck);

		System.out.println("Cards Shuffled.");
	}

	/*
	 * dealCards method ---------------------------
	 */
	// Takes a player object and deals that player a card
	public void dealCard(Player thisplayer) {

		// Create a placeholder to keep track of the deck
		Iterator i = theDeck.iterator();

		// Check to see if there is a card in the deck
		if (!i.hasNext()) {
			System.out.println("Not enough cards - time to re-shuffle.");
			shuffleCards();
			// Deck changed - need a new iterator
			i = theDeck.iterator();
		}

		// System.out.println("Card to deal dealt: " + i.next() ); //debug
		thisplayer.cardsInHand.add(i.next().toString());
		// System.out.println( "Playershand size:" +
		// thisplayer.cardsInHand.size() ); //debug
		i.remove();
		// System.out.println( "Deck of cards is size: " + theDeck.size() );
		// //debug
	}

}
