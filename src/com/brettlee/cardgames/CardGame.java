/* 
 * CardGame.java
 * Brett Lee <brett@brettlee.com>
 *
 * An abstract class.
 */

package com.brettlee.cardgames;

import java.util.ArrayList;

public abstract class CardGame implements Playable {

	public void dealInitialCards(CardDeck deck, ArrayList<Player> player) {
		System.out.println("\nIn your game class, override the method dealInitialCards!");
	};

	public void dealRemainingCards(CardDeck deck, ArrayList<Player> player) {
		System.out.println("\nIn your game class, override the method dealRemainingCards!");
	};

	public void findWinner(ArrayList<Player> player) {
		System.out.println("\nIn your game class, override the method findWinner!");
	}

}
