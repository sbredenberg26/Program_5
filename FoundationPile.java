import java.util.Map;

/**
 * ...
 */

public class FoundationPile extends Pile {

    private String suit;

    public FoundationPile(String suit, int pileNum) {
        super(pileNum);
        this.suit = suit;
    }

    public FoundationPile(FoundationPile fp) {
        super(fp.pileNum);
        this.suit = fp.suit;

        for (Card card : fp.pileCards) {
            Card c = new Card(card);
            this.pileCards.addFirst(c);
        }

    }

    public String getSuit() {
        return suit;
    }

    @Override
    public void addCard(Card c) throws Exception {
        if (!suit.equals(c.getSuit())) {
            throw new Exception("The " + c.toString().toLowerCase() + " does not belong in the "
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
            throw new Exception("This is not the next card in the sequence. Try again.");
        }
    }

    @Override
    public String toString() {
        if (getNumCardsInPile() == 0) {
            return " [" + suit +  "] ";
        }
        return getFirstCard().printBoard();
    }
}
