/** Creates a deck object with 52 cards and an optional 54 (with jokers)*/
public class Deck {

    private Card[] deck;
    private int cardsUsed;

    /** Equivalent to "new Deck(false)".) */
    public Deck() {
        this(false);  // Just call the other constructor in this class.
    }

    /** Constructs a poker deck of playing cards with or without jokers based on passed in value */
    public Deck(boolean includeJokers) {
        if (includeJokers)
            deck = new Card[54];
        else
            deck = new Card[52];
        int cardCt = 0; // How many cards have been created so far.
        for ( int suit = 0; suit <= 3; suit++ ) {
            for ( int value = 1; value <= 13; value++ ) {
                deck[cardCt] = new Card(value,suit);
                cardCt++;
            }
        }
        if (includeJokers) {
            deck[52] = new Card(1,Card.JOKER);
            deck[53] = new Card(2,Card.JOKER);
        }
        cardsUsed = 0;
    }

    /** Put used cards back into deck and randomize cards */
    public void shuffle() {
        for ( int i = deck.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        cardsUsed = 0;
    }

    /** Number of cards left in deck*/
    public int cardsLeft() {
        return deck.length - cardsUsed;
    }

    /** Removes the next card from the deck */
    public Card dealCard() {
        if (cardsUsed == deck.length)
            throw new IllegalStateException("No cards are left in the deck.");
        cardsUsed++;
        return deck[cardsUsed - 1]; 
        
        //Card stays in deck array though
    }

    /** Does the deck have jokers? */
    public boolean hasJokers() {
        return (deck.length == 54);
    }

} // end class Deck