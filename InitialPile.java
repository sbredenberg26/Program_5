import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class InitialPile extends Pile {

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
