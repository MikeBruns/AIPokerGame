class pokerHand extends Hand {
    // version 1.0
	
	/**
	 * Method pokerHand
	 */
	
	public pokerHand() {
		super();
	}	
	/**
	 * Method bestHand
	 */
	
	public Hand copy(){
		Hand aHand = new Hand();
		for(int i = 0; i < getCardCount(); i++){
			aHand.addCard(hand.get(i));
		}
		return aHand;
	
		
	}
	public int bestHand(){
		int hVal = 0;
		/* Ratings: 
		 * 4 of a kind
		 * full house
		 * flush
		 * straight
		 * three of a kind 
		 * two pair
		 * one pair
		 * high card
		 */
		if(isFourOfKind() > 0){
			hVal = 80000 + isFourOfKind();
		}	
		else if(isFullHouse() > 0){
			hVal = 70000 + isFullHouse();
		}
		else if(isFlush() > 0){
			hVal = 60000 + isFlush();
		}
		else if(isStraight() > 0){
			hVal = 50000 + isStraight();
		}
		else if(isThreeOfKind() > 0){
			hVal = 40000 + isThreeOfKind();
		}
		else if(hasTwoPair() > 0){
			hVal = 30000 + hasTwoPair();
		}
		else if(hasOnePair() > 0){
			hVal = 20000 + hasOnePair();
		}
		else
			hVal = 10000 + hasHighCard();
		
		return hVal;
		 
	}
	
	public int isMiniFlush() {
		//Checks a hand if it has 4 cards that are similar in suit
		Hand aHand = copy();
		int i = 0;
		aHand.sortBySuit();
		if(aHand.getCard(i).getSuit() == aHand.getCard(i+3).getSuit())
			return (aHand.getCardCount() - 1); //Returns odd card out
		else if (aHand.getCard(i+1).getSuit() == aHand.getCard(aHand.getCardCount() - 1).getSuit())
			return i; //Returns odd card out
		else
			return -1;
	}

	public int isMiniStraight() {
		//Checks a hand if it has 4 cards are in sequence
		Hand aHand = copy();
		int i = 0;
		aHand.sortByValue();
		if(aHand.getCard(i+3).getValue() - aHand.getCard(i).getValue() == 3 &&
				aHand.getCard(i+2).getValue() - aHand.getCard(i+1).getValue() == 1)
			return (aHand.getCardCount() - 1); //Returns odd card out
		else if(aHand.getCard(aHand.getCardCount() - 1).getValue() - aHand.getCard(i+1).getValue() == 3 &&
				aHand.getCard(i+3).getValue() - aHand.getCard(i+2).getValue() == 1)
			return (i); //Returns odd card out)
		else
			return -1;
	}

	
	private int isFullHouse() {
		Hand aHand = copy();
		int i = 0;
		int xVal = 0; int yVal = 0;
		aHand.sortByValue();
		if(aHand.getCard(i).getValue() == aHand.getCard(i+2).getValue() && 
				aHand.getCard(i+3).getValue() == aHand.getCard(aHand.getCardCount() - 1).getValue()){
			
				xVal = (aHand.getCard(i).getValue() * 100);
				yVal = aHand.getCard(aHand.getCardCount() - 1).getValue();
				
				return (xVal + yVal);
		}
		else if(aHand.getCard(i).getValue() == aHand.getCard(i+1).getValue() && 
				aHand.getCard(i+2).getValue() == aHand.getCard(aHand.getCardCount() - 1).getValue()){
			
				yVal = aHand.getCard(i).getValue();
				xVal = (aHand.getCard(aHand.getCardCount() - 1).getValue() * 100);
			
				return (xVal + yVal);
		}
		
		return 0;
		
	}
	
	public int isStraight(){
		Hand aHand = copy();
		int i = 0;
		aHand.sortByValue();
		if(aHand.getCard(aHand.getCardCount()-1 ).getValue() - aHand.getCard(i).getValue() == 4 && 
				aHand.getCard(i+3).getValue() - aHand.getCard(i+1).getValue() == 2 &&
				aHand.getCard(i+3).getValue() - aHand.getCard(i+2).getValue() == 1)
			return (aHand.getCard(aHand.getCardCount()-1 ).getValue() * 100);
		else
			return 0;
	}
	
	public int isFlush(){
		Hand aHand = copy();
		int i = 0;
		aHand.sortBySuit();
		if(aHand.getCard(i).getSuit() == aHand.getCard(aHand.getCardCount() - 1).getSuit())
			return (aHand.getCard(aHand.getCardCount() - 1).getValue() * 100);
		else
			return 0;
	}
	
	private int isThreeOfKind() {
		Hand aHand = copy();
		int i = 0;
		aHand.sortByValue();
		if(aHand.getCard(i).getValue() == aHand.getCard(i+2).getValue())
			return (aHand.getCard(i).getValue() * 100);
		else if(aHand.getCard(i+2).getValue() == aHand.getCard(aHand.getCardCount()-1).getValue())
			return (aHand.getCard(i+2).getValue() * 100);
		else if(aHand.getCard(i+1).getValue() == aHand.getCard(i+3).getValue())
			return (aHand.getCard(i+2).getValue() * 100);
		else
			return 0;
		
	}
	
	private int isFourOfKind() {
		Hand aHand = copy();
		int i = 0;
		aHand.sortByValue();
		if(aHand.getCard(i).getValue() == aHand.getCard(i+3).getValue())
			return (aHand.getCard(i).getValue() * 100);
		else if(aHand.getCard(i+1).getValue() == aHand.getCard(aHand.getCardCount() - 1).getValue())
			return (aHand.getCard(i+1).getValue() * 100);
		else
			return 0;
		
		
	}
	
	private int hasOnePair() {
		Hand aHand = copy();
		int i = 1;
		aHand.sortByValue();
		int xVal = 0;
		boolean hasOnePair = false;
		while(! hasOnePair && i < aHand.getCardCount()) {
			if(aHand.getCard(i -1).getValue() == aHand.getCard(i).getValue()) {
				xVal = (aHand.getCard(i).getValue() * 100);
				
			}
			i++;
			
		}
		return xVal;
		
	}
	
	private int hasTwoPair() {
		Hand aHand = copy();
		int i = 0; int xVal = 0; int yVal = 0;
		aHand.sortByValue();
		if(aHand.getCard(i).getValue() == aHand.getCard(i+1).getValue() &&
				aHand.getCard(i+2).getValue() == aHand.getCard(i+3).getValue()) {
			xVal = aHand.getCard(i+2).getValue() * 100;
			yVal = aHand.getCard(i).getValue();
			return (xVal + yVal);
		}
		else if(aHand.getCard(i).getValue() == aHand.getCard(i+1).getValue() &&
				aHand.getCard(i+3).getValue() == aHand.getCard(aHand.getCardCount() - 1).getValue()) {
			xVal = aHand.getCard(i+3).getValue() * 100;
			yVal = aHand.getCard(i).getValue();
			return (xVal + yVal);
		}
		else if(aHand.getCard(i+1).getValue() == aHand.getCard(i+2).getValue() &&
				aHand.getCard(i+3).getValue() == aHand.getCard(aHand.getCardCount() - 1).getValue()) {
			xVal = aHand.getCard(i+3).getValue() * 100;
			yVal = aHand.getCard(i+1).getValue();
			return (xVal + yVal);
		}
		else
			return 0;
		
	}
	
	private int hasHighCard() {
		Hand aHand = copy();
		aHand.sortByValue();
		return (aHand.getCard(aHand.getCardCount() - 1).getValue() * 100);
	}
}