/* 
 * PlayGames.java
 * Brett Lee <brett@brettlee.com>
 *
 * Offers the selection of card games to play.
 */

package com.brettlee.cardgames;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PlayGames {

	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		PlayGames cards = new PlayGames();
		cards.go();
	}

	public void go() {

		Dealer dealer = new Dealer();
		int i = 0;

		while (true) {

			System.out.println("\nWhich game would you like to play?\n");
			System.out.println("  1. Blackjack");
			System.out.println("  2. Exit");
			System.out.print("\nSelection:  ");

			try {
				i = Integer.parseInt(in.readLine());
			} catch (Exception e) {
				// System.out.println( "Error:" + e );
			}

			// See if a valid selection was made
			switch (i) {
			case 1:
				System.out.println("You Selected Blackjack...");
				Dealer.Games gametype = Dealer.Games.BLACKJACK;
				dealer.init(gametype, dealer);
				break;
			case 2:
				System.out.println("Exiting.");
				System.exit(0);
			default:
				System.out.println("Bad Selection - Please try again.");
				break;
			}
		}
	}
}
