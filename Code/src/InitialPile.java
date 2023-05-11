import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Creates the initial 7 piles in a convenient way (inherits everything it needs from Pile class)
 */
public class InitialPile extends Pile {

    /**
     * Creates each initial pile based on the column number
     * @param colNum the number of the column
     * @param cardDeck deck that the cards are coming from
     */
    public InitialPile(int colNum, Deck cardDeck) {
        super(colNum);

        for (int i = 0; i < colNum; i++) {
            Card c = new Card(cardDeck.playCard());

            if (i == colNum - 1) {
                c.setVisibility(true);
            }
            else {
                c.setVisibility(false);
            }

            pileCards.addFirst(c);
        }
    }

}
