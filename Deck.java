import java.util.*;

/**
 * ...
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

    public int getNumCardsInDeck() {
        return cardDeck.size();
    }

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
