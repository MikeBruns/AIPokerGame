public class humPlayer extends pokerPlayer {
	
	

	public humPlayer(int s, String N) {
		super(s, N);
	}
	
	public int discard(int handCode, Hand faceUpCards){
		int nc = 0;
		System.out.println("Human, you may discard up to 2 cards, "
				+ "how many would you like to discard? (0-2)");
		nc = TextIO.getlnInt();
		while(nc < 0 || nc > 2){
			System.out.println("Sorry human.. please pick a number that 0, 1, or 2");
			nc = TextIO.getlnInt();
		}
		if(nc == 0)
			return nc;
		
		if(nc == 1){ // Handles discarding 1 card
			System.out.println("Which card would you like to discard? (Enter card"
					+ " in the form: 'Value of Suit')..  eg.) 2 of Hearts");
			
			String disCard = TextIO.getlnString();
			
			for(int i = hand.getCardCount() - 1; i > -1; i--) {
				if(hand.getCard(i).toString().equals(disCard)){
						hand.removeCard(i);
				}
			}
		}
		
		if(nc == 2){ //Handles discarding 2 cards
			
			System.out.println("Enter the first card you would you like to discard? (Enter card"
					+ " in the form: 'Value of Suit')..  eg.) 2 of Hearts");
			String disCard = TextIO.getlnString();
			
			System.out.println("Enter the second card you would you like to discard? (Enter card"
					+ " in the form: 'Value of Suit')..  eg.) 2 of Hearts");
			String disCard2 = TextIO.getlnString();
			
			
			while(disCard.equalsIgnoreCase(disCard2)){
				System.out.println("You cannot enter the same card for both, you only have one to give, please try again");
				disCard2 = TextIO.getlnString();
			}
			
			// Discard both cards
			for(int i = hand.getCardCount() - 1; i > -1; i--) {
				if(hand.getCard(i).toString().equals(disCard)){
						hand.removeCard(i);
				}
			}
			for(int i = hand.getCardCount() - 1; i > -1; i--) {
				if(hand.getCard(i).toString().equals(disCard2)){
						hand.removeCard(i);
				}
			}
		}
		
		return nc;
	}

} // End of humPlayer class
