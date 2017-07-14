
/*
 *  DeckStuff.java
 *  Brett Lee <brett@brettlee.com>
 *  
 *  Provides an implementation of a card deck.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class DeckStuff {

	// Instantiate an ArrayList to hold the entire deck of cards
	ArrayList<String> theDeck = new ArrayList<String>();

	// Construct two arrays to hold the card permutations
	String[] suitsArray = { "Diamonds", "Hearts", "Clubs", "Spades" };
	String[] cardsArray = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
			"Queen", "King" };

	// We'll need to read from STDIN
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	// Use the "main" function to kick off the processing
	public static void main(String[] args) {
		DeckStuff deck = new DeckStuff();
		deck.go();
	}

	/*
	 * "go" method --------------------------- First method initializes the
	 * environment by: - opening a new deck of cards - presenting a menu of
	 * choices for the enamored participant(s)
	 */
	public void go() {
		newDeck();
		menu();
	}

	/*
	 * menu method --------------------------- Display a menu to centralize flow
	 * of the program. Provides the options for a new deck, shuffle, deal and
	 * quit.
	 */
	public void menu() {

		String s = null;
		int i = 0;

		try {
			while (true) {

				System.out.println("\nPlease select one of the following:");
				System.out.println("  1. Fresh deck of cards please.");
				System.out.println("  2. Shuffle the deck.");
				System.out.println("  3. Deal 'em.");
				System.out.println("  4. Gotta Go.\n");
				System.out.print("Selection:  ");

				// Read from the keyboard (as a String)
				s = in.readLine();

				// Convert String -> Integer
				try {
					i = Integer.parseInt(s);
				} catch (Exception e) {
					System.out.println("Error:" + e);
				}

				// See if a selection was made
				switch (i) {
				case 1:
					System.out.println("Getting a new deck...");
					newDeck();
					break;
				case 2:
					System.out.println("Shuffling cards...");
					shuffleCards();
					break;
				case 3:
					System.out.println("Dealing cards...");
					dealCards();
					break;
				case 4:
					System.out.println("Exiting.");
					System.exit(0);
				default:
					System.out.println("Bad Selection - Please try again.");
				}
			}
		} catch (IOException ioe) {
			System.out.println("IO error:" + ioe);
		}
	}

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

		// repopulate the deck with 52 cards
		newDeck();

		// let the API do the work. :)
		java.util.Collections.shuffle(theDeck);

		System.out.println("Cards Shuffled.");
	}

	/*
	 * dealCards method ---------------------------
	 */
	public void dealCards() {

		int numDealt = 0;
		int numToDeal = 0;
		String s = null;

		Iterator i = theDeck.iterator();

		try {
			System.out.print("\nHow many cards should be dealt?  ");

			// Read from the keyboard (as a String)
			s = in.readLine();

		} catch (IOException ioe) {
			System.out.println("IO error:" + ioe);
		}

		// Convert String -> Integer
		try {
			numToDeal = Integer.parseInt(s);

		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		// Check to see if enough cards are still in the deck
		if (numToDeal > theDeck.size()) {

			System.out.println("Not enough cards - time to re-shuffle.");

		} else {

			while ((i.hasNext()) && (numDealt < numToDeal)) {
				System.out.println(i.next());
				numDealt++;
				i.remove();
				// System.out.println( "ArrayList is size: " + theDeck.size() );
				// //debug
			}
		}
	}
}
