/* 
 * Blackjack.java
 * Brett Lee <brett@brettlee.com>
 *
 * Provides the game of Blackjack.
 */

package com.brettlee.cardgames;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Blackjack extends CardGame {

	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public void dealInitialCards(CardDeck deck, ArrayList<Player> player) {

		System.out.println("Dealing initial round of cards...");
		System.out.println("Two to each player.");

		// deal two cards to each player
		for (int j = 0; j < 2; j++) {
			for (int k = 0; k < player.size(); k++) {
				deck.dealCard(player.get(k));
			}
		}
	}

	public void dealRemainingCards(CardDeck deck, ArrayList<Player> player) {

		// See if the dealer has a blackjack
		if (valueOfHand(player.get(player.size() - 1).cardsInHand) == 21) {

			System.out.println("DEALER HAS BLACKJACK !!!");

		} else {

			System.out.println("Round Two: Dealing to each player individually...");

			for (int k = 0; k < player.size(); k++) {

				// check for Blackjack
				if (valueOfHand(player.get(k).cardsInHand) == 21) {

					System.out.println("\nPLAYER " + (k + 1) + " has BLACKJACK !!!");
					showCards(player.get(k).cardsInHand);

				} else {

					// loop for each player, checking to see if they want
					// another card
					boolean anothercard = true;
					while (anothercard) {

						// is hand greater than 21
						if (valueOfHand(player.get(k).cardsInHand) > 21) {
							System.out.println("BUST !!!");
							break;
						}

						if ((k + 1) == player.size()) {

							System.out.println("\nDealer has: ");
						} else {

							System.out.println("\nDealer is showing: " + player.get(player.size() - 1).cardsInHand
									.get((player.get(player.size() - 1).cardsInHand.size() - 1)));
							System.out.println("\nPlayer " + (k + 1) + " you have: ");
						}

						showCards(player.get(k).cardsInHand);
						System.out.print("Another card? (1 = YES, 0 = NO) "); // Keep
																				// trailing
																				// space.

						int response = 2;
						try {
							response = Integer.parseInt(in.readLine());
						} catch (Exception e) {
							System.out.println("Error:" + e);
						}

						// See if a valid selection was made
						if (response == 0) {
							System.out.println("Standing pat.");
							anothercard = false;
						} else if (response == 1) {
							deck.dealCard(player.get(k));
							System.out.println("Your card: "
									+ player.get(k).cardsInHand.get(player.get(k).cardsInHand.size() - 1));
						} else {
							System.out.println("Bad Selection - Please try again.");
						}
					}
				}
			}
		}
	};

	public void findWinner(ArrayList<Player> player) {

		int dealercards = 0;
		int playercards = 0;

		System.out.println("\nFinding the winner...");

		// Check to see if the dealer bust
		if ((dealercards = valueOfHand(player.get(player.size() - 1).cardsInHand)) > 21) {

			System.out.println("DEALER BUSTED !!!");

			for (int k = 0; k < player.size() - 1; k++) {

				if (valueOfHand(player.get(k).cardsInHand) < 21) {
					System.out.println("Player " + (k + 1) + " is a WINNER!");
				} else {
					System.out.println("Player " + (k + 1) + " : push.  Try again.");
				}
			}

			// See if the dealer has a blackjack
		} else if ((valueOfHand(player.get(player.size() - 1).cardsInHand) == 21)
				&& (player.get(player.size() - 1).cardsInHand.size() == 2)) {

			for (int k = 0; k < player.size() - 1; k++) {

				if (valueOfHand(player.get(k).cardsInHand) == 21) {
					System.out.println("Player " + (k + 1) + " also has BLACKJACK! push");
				} else {
					System.out.println("Player " + (k + 1) + " loses.");
				}
			}

			// See if the dealer has TWENTY-ONE
		} else if (valueOfHand(player.get(player.size() - 1).cardsInHand) == 21) {

			for (int k = 0; k < player.size() - 1; k++) {
				System.out.println("DEALER has TWENTY-ONE.");
				if (valueOfHand(player.get(k).cardsInHand) == 21) {
					System.out.println("Player " + (k + 1) + " also has TWENTY-ONE! push");
				} else {
					System.out.println("Player " + (k + 1) + " loses.");
				}
			}

			// Default case - dealerpays X
		} else {

			System.out.println("\nDealer pays: " + (dealercards + 1));

			for (int k = 0; k < player.size() - 1; k++) {

				playercards = valueOfHand(player.get(k).cardsInHand);

				if (playercards < 22) {
					if (playercards > dealercards) {
						System.out.println("Player " + (k + 1) + " is a WINNER!");

					} else if (playercards == dealercards) {
						System.out.println("Player " + (k + 1) + " : push.  Try again.");
					} else {
						System.out.println("Player " + (k + 1) + " loses.");
					}
				} else {
					System.out.println("Player " + (k + 1) + " loses.");
				}
			}
		}
	}

	public void showCards(ArrayList<String> cardsinhand) {

		Iterator i = cardsinhand.iterator();
		while (i.hasNext()) {
			System.out.println("  - " + i.next());
		}
	}

	public int valueOfHand(ArrayList<String> thishand) {

		Collections.sort(thishand, Collections.reverseOrder());
		Iterator i = thishand.iterator();
		String[] splithand = null;
		int j = 0;

		while (i.hasNext()) {

			splithand = i.next().toString().split(" ");

			if (splithand[0].equals("Deuce")) {
				j += 2;
			} else if (splithand[0].equals("Three")) {
				j += 3;
			} else if (splithand[0].equals("Four")) {
				j += 4;
			} else if (splithand[0].equals("Five")) {
				j += 5;
			} else if (splithand[0].equals("Six")) {
				j += 6;
			} else if (splithand[0].equals("Seven")) {
				j += 7;
			} else if (splithand[0].equals("Eight")) {
				j += 8;
			} else if (splithand[0].equals("Nine")) {
				j += 9;
			} else if (splithand[0].equals("Ten")) {
				j += 10;
			} else if (splithand[0].equals("Jack")) {
				j += 10;
			} else if (splithand[0].equals("Queen")) {
				j += 10;
			} else if (splithand[0].equals("King")) {
				j += 10;
			} else if (splithand[0].equals("Ace")) {
				if ((j += 11) > 21) {
					// System.out.println("Over 21 with a Ace as ELEVEN: " + j
					// ); // debug
					j -= 10;
					// System.out.println("Ace is now a ONE: " + j ); // debug
				}
			} else {
				System.out.println("Error in checking cards :" + splithand[0]);
			}
		}
		return j;
	}
}
