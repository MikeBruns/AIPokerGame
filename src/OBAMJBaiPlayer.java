/**
 * CREATED BY: Odero Ajamu & Michael Bruns
 * 
 * Rules 1.) If you have a straight or better discard no cards
 * 
 * 2.) With three of a kind 
 * 		>Check faceUpCards 
 * 		>if both faceUpCards value matchup with both odd cards out, discard both odd cards out 
 * 		>if one faceUpCardsvalue matches up with an odd card out, discard the card that matches up 
 * 		>if no faceUpCard values match up discard the lowest card that is the odd one out
 * 
 * 3.) With two pair 
 * 		>Discard the odd card out
 * 
 * 4.) With one pair 
 * 		>Call miniFlush 
 * 			>if true and faceUpCards do not = desired flush suit discard odd flush card 
 * 			>if true and a faceUpCards does = desired flush suit, move on to next step
 * 
 * 		>Call miniStraight 
 * 			>if true and faceUpCards do not = missing straight value discard odd straight card 
 * 			>if true and a faceUpCards does = missing straightvalue, move on to next step
 * 
 * 		>Check faceUpCards 
 * 			>if one card in ai's hand match up in value w/ faceUpCards discard that card & the lowest card unless apart of our pair
 * 
 * 		>If no match with faceUpCards >Discard 2 lowest cards as long as they are not apart of our pair
 * 
 * 
 * 5.) With only a high card
 * 
 * 		>Call miniFlush 
 * 			>if true discard odd card 
 * 			>Call miniStraight 
 * 			>if true discard odd card
 * 
 * 		>Check faceUpCards 
 * 			>if two cards in ai's hand match up in value w/ faceUpCards discard them both 
 * 			>if there is a pair in the faceUpCards and ai shares a card with the pair discard it and the lowest card 
 * 			>if one card in ai's hand match up in value w/ faceUpCards discard that card & the lowest card
 *
 * 		>If no match with faceUpCards >Discard two lowest cards in hand
 * 
 *
 */

public class OBAMJBaiPlayer extends pokerPlayer {

	public OBAMJBaiPlayer(int s, String N) {
		super(s, N);
	}

	public int discard(int handCode, Hand faceUpCards) {
		int nc = 0;

		// If ai has a straight or better discard no cards
		if (handCode > 50000)
			return nc;

		// AI situation for 3 of a kind
		if (handCode > 40000 && handCode < 50000) {
			int i = 0;
			hand.sortByValue();
			
			// First 3 cards are the same
			if (hand.getCard(i).getValue() == hand.getCard(i + 2).getValue()) { 

				i = hand.getCardCount() - 2;
				// If either of the last two cards match with faceUpCards,
				// discard them
				if ((hand.getCard(i).getValue() == faceUpCards.getCard(i)
						.getValue() || hand.getCard(i).getValue() == faceUpCards
						.getCard(i + 1).getValue())
						&& (hand.getCard(i - 1).getValue() == faceUpCards
								.getCard(i).getValue() || hand.getCard(i - 1)
								.getValue() == faceUpCards.getCard(i + 1)
								.getValue())) {

					nc = 2;
					hand.removeCard(i + 1);
					hand.removeCard(i);
					return nc;
				}
				// if only the third card matches any faceUpCards, discard it
				else if (hand.getCard(i).getValue() == faceUpCards.getCard(i)
						.getValue()
						|| hand.getCard(i).getValue() == faceUpCards.getCard(
								i + 1).getValue()) {

					nc = 1;
					hand.removeCard(i);
					return nc;
				}
				// if only the fourth card matches any faceUpCards, discard it
				else if (hand.getCard(i + 1).getValue() == faceUpCards.getCard(
						i).getValue()
						|| hand.getCard(i + 1).getValue() == faceUpCards
								.getCard(i + 1).getValue()) {

					nc = 1;
					hand.removeCard(i + 1);
					return nc;
				}
				// if no cards match with faceUpCards, discard the lowest odd
				// card out
				else {
					nc = 1;
					hand.removeCard(i);
					return nc;
				}

			}

			else if (hand.getCard(i + 2).getValue() == hand.getCard(i + 4)
					.getValue()) {// Last 3 cards are the same
				i = 0;
				if ((hand.getCard(i).getValue() == faceUpCards.getCard(i)
						.getValue() || hand.getCard(i).getValue() == faceUpCards
						.getCard(i + 1).getValue())
						&& (hand.getCard(i + 1).getValue() == faceUpCards
								.getCard(i).getValue() || hand.getCard(i + 2)
								.getValue() == faceUpCards.getCard(i + 1)
								.getValue())) {

					nc = 2;
					hand.removeCard(i);
					hand.removeCard(i + 1);
				}

				else if (hand.getCard(i).getValue() == faceUpCards.getCard(i)
						.getValue()
						|| hand.getCard(i).getValue() == faceUpCards.getCard(
								i + 1).getValue()) {

					nc = 1;
					hand.removeCard(i);
				} else if (hand.getCard(i + 1).getValue() == faceUpCards
						.getCard(i).getValue()
						|| hand.getCard(i + 1).getValue() == faceUpCards
								.getCard(i + 1).getValue()) {

					nc = 1;
					hand.removeCard(i + 1);
				} else {
					nc = 1;
					hand.removeCard(i);
				}

				return nc;
			}

			else if (hand.getCard(i + 1).getValue() == hand.getCard(i + 3)
					.getValue()) {// Middle three cards are the same
				i = 0;
				if ((hand.getCard(i).getValue() == faceUpCards.getCard(i)
						.getValue() || hand.getCard(i).getValue() == faceUpCards
						.getCard(i + 1).getValue())
						&& (hand.getCard(i + 4).getValue() == faceUpCards
								.getCard(i).getValue() || hand.getCard(i + 2)
								.getValue() == faceUpCards.getCard(i + 4)
								.getValue())) {

					nc = 2;
					hand.removeCard(i + 4);
					hand.removeCard(i);
				}

				else if (hand.getCard(i).getValue() == faceUpCards.getCard(i)
						.getValue()
						|| hand.getCard(i).getValue() == faceUpCards.getCard(
								i + 1).getValue()) {

					nc = 1;
					hand.removeCard(i);
				} else if (hand.getCard(i + 4).getValue() == faceUpCards
						.getCard(i).getValue()
						|| hand.getCard(i + 4).getValue() == faceUpCards
								.getCard(i + 1).getValue()) {

					nc = 1;
					hand.removeCard(i + 4);
				} else {
					nc = 1;
					hand.removeCard(i);
				}

				return nc;
			}

		} // End of 3 of a kind situation

		// AI situation for two pair
		if (handCode > 30000 && handCode < 40000) {
			int i = 0;
			nc = 1;
			hand.sortByValue();
			if (hand.getCard(i).getValue() == hand.getCard(i + 1).getValue()
					&& hand.getCard(i + 2).getValue() == hand.getCard(i + 3).getValue()) { // Last card is the odd card

				hand.removeCard(i + 4);
			} else if (hand.getCard(i).getValue() == hand.getCard(i + 1).getValue()
					&& hand.getCard(i + 3).getValue() == hand.getCard(i + 4).getValue()) { // Middle card is the odd card

				hand.removeCard(i + 2);
			} else if (hand.getCard(i + 1).getValue() == hand.getCard(i + 2).getValue()
					&& hand.getCard(i + 3).getValue() == hand.getCard(i + 4).getValue()) { // First card is the odd card

				hand.removeCard(i);
			}

			return nc;

		} // End of two pair situation

		// AI situation for one pair

		if (handCode > 20000 && handCode < 30000) {

			int i = 0;
			nc = 0;
			if (hand.isMiniFlush() != -1) { // if there is a miniFlush discard
											// the odd card out
				hand.sortBySuit();
				hand.removeCard(hand.isMiniFlush());
				nc=1;
			} else if (hand.isMiniStraight() != -1) { // if there is a
														// miniStraight discard
														// the odd card out
				hand.sortByValue();
				hand.removeCard(hand.isMiniStraight());
				nc=1;

			}

			int temp1 = 0;		//Temp positions hold positions
			int temp2 = 0;		// of cards that have a pair
			
			for (i = 0; i < hand.getCardCount() - 1; i++) {
				if (hand.getCard(i).getValue() == hand.getCard(i + 1)
						.getValue()) {
					temp1 = i;
					temp2 = i + 1;
				}
			}
	
	  
			i=0;
			hand.sortByValue();
			do {		 
					
					if (hand.getCard(i).getValue() != hand.getCard(temp1).getValue()
							&& hand.getCard(i).getValue() != hand.getCard(temp2).getValue()) {

						hand.removeCard(i);
						nc++;
					}
					else
						i++;
					
				
			} while (nc < 2);
			return nc;

		} // End of one pair situation

		// AI situation for high card
		if (handCode > 10000 && handCode < 20000) {
			int i = 0;
			nc = 0;
			hand.sortBySuit();
			if (hand.isMiniFlush() != -1) { // if there is a miniFlush discard
											// the odd card out
				hand.sortBySuit();
				hand.removeCard(hand.isMiniFlush());
			} else if (hand.isMiniStraight() != -1) { // if there is a
														// miniStraight discard
														// the odd card out
				hand.sortByValue();
				hand.removeCard(hand.isMiniStraight());
			}
			// Check if any cards in ai hand match up with faceUpCards, if they
			// do discard them
			
			int temp1 = 0;		//Temp positions hold positions
			int temp2 = 0;		// of cards that have a pair
			
			for (i = 0; i < hand.getCardCount() - 1; i++) {
				if (hand.getCard(i).getValue() == hand.getCard(i + 1)
						.getValue()) {
					temp1 = i;
					temp2 = i + 1;
				}
			}
					
			int c = 0;
			for (i = hand.getCardCount() - 1; i > -1; i--) {
				if ((hand.getCard(i).getValue() != hand.getCard(temp1).getValue() || hand.getCard(i) != hand.getCard(temp2))
						&& ((hand.getCard(i).getValue() == faceUpCards.getCard(c).getValue()) || hand.getCard(i)
								.getValue() == faceUpCards.getCard(c + 1)
								.getValue())) {

					hand.removeCard(i);
					nc++;
				}
			}
			
            i=0;
            hand.sortByValue();
			do { // if ai has not yet discarded two cards discard the lowest
					// card or cards
				
				hand.removeCard(i);
				nc++;
				
			} while (nc < 1);

			return nc;

		} // End of high card situation

		return nc;

	} // End of Discard


	
}// End of aiPlayer class
	