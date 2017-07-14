/* 
 * Dealer.java
 * Brett Lee <brett@brettlee.com>
 *
 * An implementation of the card dealer.
 */

package com.brettlee.cardgames;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dealer extends Player {

	ArrayList<Player> player = new ArrayList<Player>();
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	enum Games {
		BLACKJACK
	};

	public void init(Games thisgame, Player dealer) {

		CardGame game = null;
		int numPlayers = 0;

		// Keep trying until get a valid response
		while (numPlayers < 2) {

			System.out.print("\nHow many players (including the Dealer)? :  ");

			try {
				numPlayers = Integer.parseInt(in.readLine());
			} catch (Exception e) {
				// System.out.println( "Error:" + e );
				numPlayers = 0;
			}
		}

		// Create the players
		for (int i = 0; i < numPlayers - 1; i++) {
			// System.out.println( "Creating player number: " + (i+1) ); //debug
			player.add(new Player());
			// System.out.println( "Arraylist<Player> size:" + player.size() );
			// //debug
		}
		player.add(dealer); // Dealer is a player, too

		// Start the game
		switch (thisgame) {
		case BLACKJACK:
			System.out.println("Starting Blackjack...");
			game = new Blackjack();
			break;
		default:
			System.out.println("Internal Error - Did not understand game type.");
			break;
		}

		// Open a new deck of cards
		CardDeck deck = new CardDeck();

		// Shuffle the deck
		deck.shuffleCards();

		// Play the game
		game.dealInitialCards(deck, player);
		game.dealRemainingCards(deck, player);
		game.findWinner(player);

		// Clean up
		player.clear(); // Remove all players
		dealer.cardsInHand.clear(); // Clear cards from dealers hand
	}
}
