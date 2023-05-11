import java.util.Map;

/**
 * Creates and manages each of the foundation piles
 */

public class FoundationPile extends Pile {

    private String suit;

    /**
     * Constructor for the foundation pile
     * @param suit to set the suit of the pile
     * @param pileNum sets the number of the foundation pile
     */
    public FoundationPile(String suit, int pileNum) {
        super(pileNum);
        this.suit = suit;
    }

    /**
     * Copy constructor
     * @param fp foundation pile being copied
     */
    public FoundationPile(FoundationPile fp) {
        super(fp.pileNum);
        this.suit = fp.suit;

        for (Card card : fp.pileCards) {
            Card c = new Card(card);
            this.pileCards.addFirst(c);
        }

    }

    /**
     * Getter for suit
     * @return suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Overrides the Pile's method for adding cards because of the foundation's specific rules
     * @param c card being added
     * @throws Exception if adding the card is not a legal move
     */
    @Override
    public void addCard(Card c) throws Exception {
        if (!suit.equals(c.getSuit())) {
            throw new Exception("That doesn't look like a " + suit.toLowerCase() + " to me. The " + c.toString() + " does not belong in the "
                    + suit.toLowerCase() + " foundation pile. Try again.");
        }
        else if (c.getRank() == 0 && getNumCardsInPile() == 0) {
            c.setVisibility(true);
            pileCards.addFirst(c);
        }
        else if (c.getRank() != 0 && getNumCardsInPile() == 0) {
            throw new Exception("This is not the next card in the sequence. Try again.");
        }
        else if (c.getRank() - 1 == getFirstCard().getRank()) {
            c.setVisibility(true);
            pileCards.addFirst(c);
        }
        else {
            throw new Exception("Um no. This is not the next card in the sequence. Try again.");
        }
    }

    /**
     * Override version of the toString method
     * @return the string of what needs to be printed to represent the pile on the board
     */
    @Override
    public String toString() {
        if (getNumCardsInPile() == 0) {
            return " [" + suit +  "] ";
        }
        return getFirstCard().printBoard();
    }
}
