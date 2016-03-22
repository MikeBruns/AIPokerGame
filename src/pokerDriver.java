/** Created by: Odero Ajamu & Michael Bruns
 *
 *
 * This is a 5 hand poker game between a you (the human) and an AI player.
 * Each player can see two of the other players cards and their own hand.
 * Each player then can make a decision to discard and pick up, up to
 * two more cards from the deck
 *                       
 */

public class pokerDriver {

	public static void main(String[] args) {

		System.out.println("Let's Play Poker!\n");
		Deck aDeck = new Deck(false); // no jokers
		

		OBAMJBaiPlayer ai = new OBAMJBaiPlayer(50, "MACHINE");
		humPlayer hum = new humPlayer(50, "MAN");

		Card aCard; // just for code readability, deal a card to a player

		for (int i = 1; i <= 5; i++) { // play 5 hands
			aDeck.shuffle();
			Hand aiFaceUpCards = new Hand(); // faceUpCards contain copies of 1st two
						         // cards for ease of display
			Hand humFaceUpCards = new Hand(); // and to pass to the AI for
							  // heuristic processing
			
			// For manually assigning card values to test (Value, Suit)
			
		 /* Card card1 = new Card(2,3); 
			Card card2 = new Card(9,2);
			Card card3 = new Card(4,0);
			Card card4 = new Card(10,1);
			Card card5 = new Card(6,2); 
						
			hum.takeCard(card1);
			hum.takeCard(card2);
			hum.takeCard(card3);
			hum.takeCard(card4);
			hum.takeCard(card5); 
			humFaceUpCards.addCard(card1);
			humFaceUpCards.addCard(card2);
			
			Card card6 = new Card(2,0);
			Card card7 = new Card(7,3);
			Card card8 = new Card(7,0);
			Card card9 = new Card(9,3);
			Card card10 = new Card(4,2); 
						
			ai.takeCard(card6);
			ai.takeCard(card7);
			ai.takeCard(card8);
			ai.takeCard(card9);
			ai.takeCard(card10); 
			
			*/
			

			for (int c = 1; c <= 5; c++) { // deal 5 cards to each player
				aCard = aDeck.dealCard();
				ai.takeCard(aCard);
				if (c <= 2)
					aiFaceUpCards.addCard(aCard);

				aCard = aDeck.dealCard();
				hum.takeCard(aCard);
				if (c <= 2)
					humFaceUpCards.addCard(aCard);
			} 
			
			// Display initial hands
			System.out.println("------ AI Hand ------");
			ai.printVisible();
			System.out.println("---- Human Hand ----");
			hum.printAll();
			System.out.println();

			// ai discards; dealer deals nc new cards
			int nc = ai.discard(ai.bestHand(), humFaceUpCards);
			System.out.println("AI discards " + nc);
			for (int c = 1; c <= nc; c++)
				ai.takeCard(aDeck.dealCard());

			// human discards; dealer deals nc new cards
			nc = hum.discard(0, aiFaceUpCards);
			System.out.println("Human discards " + nc);
			for (int c = 1; c <= nc; c++)
				hum.takeCard(aDeck.dealCard());

			// See who won
			System.out.println("-- AI's Final Hand -- ");
			ai.printAll();
			System.out.println("-- Human's Final Hand --");
			hum.printAll();
			System.out.println();
			System.out.println("****** FINAL VERDICT ******");
			int aibh = ai.bestHand();
			int humbh = hum.bestHand();
			if (aibh == humbh)
				System.out.println("TIE");
			else if (aibh < humbh){
				System.out.println("~ You beat the robot! ~");
				System.out.println("Human score: " + humbh + " | AI score: " + aibh);
			}
			
			else{
				System.out.println("~ Beep boop, I won silly human! ~");
				System.out.println("Human score: " + humbh + " | AI score: " + aibh);
			}
			System.out.println();
			System.out.println("****** NEW GAME ******");
			System.out.println();
			
			ai.reset();
			hum.reset();
		}

	}
}