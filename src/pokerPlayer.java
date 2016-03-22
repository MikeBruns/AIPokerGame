/** Poker player object that handles players hands */
 
abstract class pokerPlayer {
	
	private int stake;
	private String name;
	protected pokerHand hand;
	
	public pokerPlayer( int s, String N){
		stake = s;  // for future work
		name = N;
		reset();
	}
	
	/** create a new empty hand */
	public void reset() {
		hand = new pokerHand();
	}

	/** Adds the passed Card to the player's hand */
	public void takeCard(Card aCard) {
		hand.addCard(aCard);
	}

	/** Dealing with bets (not yet implemented) */
	public int getStake() {

		return stake;
	}
	
	public void setStake(int val) {

		stake = val;
	}

	/** Print all the cards in the hand */
	public void printAll() {
		
		int cc = hand.getCardCount();
		for (int i = 0; i < cc; i++) {
			System.out.println( hand.getCard(i).toString());
		}
	}
	
	/** Prints 2 visible cards in the AI's hand*/
	public void printVisible() {
		
		int cc = hand.getCardCount();
		for (int i = 0; i < cc; i++) {
			if ( i <=1)
				System.out.println( hand.getCard(i).toString());
			else
				System.out.println( "***** Hidden *******");
		}
	}
	/** TODO: override in subclasses
	 *       add appropriate logic for sub-class type
	 *       may alter original ordering of cards
	 * return # of cards discarded
	 * handCode: the value of the hand 
	 * faceUpCards:  the other players visible cards
	 */
	public abstract int discard(int handCode, Hand faceUpCards);
	
	/** To see face up cards for AI */
	public Card getFaceUpCard(int i) {
	    if ( i <= 1) 
		    return hand.getCard(i);
		else 
		    throw new RuntimeException("no peeking");
	}	
	
	
	public String getName() {
		return name;
	}
	
	/** See what your best hand is */
	public int bestHand() { 
		return hand.bestHand(); 
	}
	
	/** Create and return a copy of the current hand
	    allows processing of the hand without altering the original version/ordering
	  */
	public pokerHand getHand() {
		pokerHand  phCopy = new pokerHand();
		
		for (int i=0; i < hand.getCardCount(); i++) {
			phCopy.addCard(hand.getCard(i));
		}
		return phCopy;
	}
}