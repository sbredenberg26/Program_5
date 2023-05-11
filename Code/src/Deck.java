import java.util.*;

/**
 * This is the class that creates a deck of cards
 */
public class Deck {

    private Queue<Card> cardDeck;

    /**
     * Creates a default card deck.
     */
    public Deck() {
        cardDeck = new LinkedList<>();

        for (int i = 0; i < 52; i++) {
            Card c = new Card(i);
            c.setVisibility(true);
            cardDeck.add(c);
        }

    }

    /**
     * getter for number of cards in a deck
     * @return cardDeck.size() int of how large the cardDeck is
     */
    public int getNumCardsInDeck() {
        return cardDeck.size();
    }

    /**
     * method to play a card off a pile
     * @return top card off the deck
     */
    public Card playCard() {
        return cardDeck.poll(); // returns null if empty
    }

    public void addCard(Card c) {
        cardDeck.add(c);
    }

    /**
     * Shuffles the card deck.
     */
    public void shuffle() {
        Random rand = new Random();

        ArrayList<Card> shuffled = new ArrayList<>(cardDeck);

        for (int i = 0; i < shuffled.size(); i++) {
            int x = rand.nextInt(0, 52);

            if (i != x) {
                Card placeHolderX = new Card(shuffled.get(x));
                Card placeHolderI = new Card(shuffled.get(i));

                shuffled.remove(i);
                shuffled.add(i, placeHolderX);

                shuffled.remove(x);
                shuffled.add(x, placeHolderI);
            }

        }

        cardDeck = new LinkedList<>(shuffled);
    }

    /**
     * Our toString method
     * @return string of all the cards left in the deck
     */
    @Override
    public String toString() {
        String s = "";
        Iterator<Card> it = cardDeck.iterator();
        while(it.hasNext()) {
            s += it.next() + ", ";
        }
        return s;
    }

}
