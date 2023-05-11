import java.util.LinkedList;

/**
 * The Pile class creates and manages each of the 7 piles
 */
public class Pile {

    protected LinkedList<Card> pileCards;
    protected int pileNum;

    /**
     * Creates a pile
     * @param pileNum indicates which of the 7 piles it is
     */
    public Pile(int pileNum) {
        pileCards = new LinkedList<>();
        this.pileNum = pileNum;
    }

    /**
     * Copy constructor
     * @param p the pile being coppied
     */
    public Pile(Pile p) {
        this.pileNum = p.pileNum;
        this.pileCards = new LinkedList<>();

        for (Card card : p.pileCards) {
            Card c = new Card(card);
            this.pileCards.add(c);
        }

    }

    /**
     * Getter for the pile number
     * @return pileNum the number of the pile
     */
    public int getPileNum() {
        return pileNum;
    }

    /**
     * Getter for the pile size
     * @return the size of the pile
     */
    public int getNumCardsInPile() {
        return pileCards.size();
    }

    /**
     * Getter for a card at a given index
     * @param index position of the card in the pile
     * @return Gets the card at the given index
     */
    public Card getCard(int index) {
        return pileCards.get(index);
    }

    /**
     * Getter for the top card
     * @return the top card
     */
    public Card getFirstCard() {
        return pileCards.getFirst();
    }

    /**
     * Getter for the pile
     * @return pile
     */
    public LinkedList<Card> getPileCards() {
        return pileCards;
    }

    /**
     * Adds a card to the pile
     * @param c card being added
     * @throws Exception for if adding the card is not a legitimate move
     */
    public void addCard(Card c) throws Exception {
        if (getNumCardsInPile() == 0) {
            c.setVisibility(true);
            pileCards.addFirst(c);
        }
        else if (getFirstCard().getRank() == 0) {
            throw new Exception("Can you even count? This is not the next card in the sequence. Try again.");
        }
        else if (c.getColor() == getFirstCard().getColor()) {
            throw new Exception("Are you colorblind? You can't stack cards of the same color directly on each other...try again.");
        }
        else if (getFirstCard().getRank() - 1 == c.getRank()) {
            c.setVisibility(true);
            pileCards.addFirst(c);
        }
        else {
            throw new Exception("What are you doing? This is not the next card in the sequence. Try again.");
        }
    }

    /**
     * removes the top card off the pile and makes sure the one underneath it is visible
     */
    public void removeFirstCard() {
        pileCards.removeFirst();
        if (pileCards.size() > 0) {
            pileCards.getFirst().setVisibility(true);
        }
    }

    /**
     * Our version of the toString method
     * @return string version of the pile
     */
    @Override
    public String toString() {
        String s = "[" + pileNum + "] ";
        for (int i = pileCards.size() - 1; i >= 0; i--) {
            s += pileCards.get(i).toString() + " ";
        }
        return s + "\n";
    }
}
