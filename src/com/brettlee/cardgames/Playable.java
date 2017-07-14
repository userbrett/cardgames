/* 
 * Playable.java
 * Brett Lee <brett@brettlee.com>
 *
 * An interface.
 */

package com.brettlee.cardgames;

import java.util.ArrayList;

public interface Playable {

	public void dealInitialCards(CardDeck deck, ArrayList<Player> player);

	public void dealRemainingCards(CardDeck deck, ArrayList<Player> player);

	public void findWinner(ArrayList<Player> player);
}
