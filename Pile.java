import java.util.LinkedList;

public class Pile {

    protected LinkedList<Card> pileCards;
    protected int pileNum;

    public Pile(int pileNum) {
        pileCards = new LinkedList<>();
        this.pileNum = pileNum;
    }

    public Pile(Pile p) {
        this.pileNum = p.pileNum;
        this.pileCards = new LinkedList<>();

        for (Card card : p.pileCards) {
            Card c = new Card(card);
            this.pileCards.add(c);
        }

    }

    public int getPileNum() {
        return pileNum;
    }

    public int getNumCardsInPile() {
        return pileCards.size();
    }

    public Card getCard(int index) {
        return pileCards.get(index);
    }

    public Card getFirstCard() {
        return pileCards.getFirst();
    }

    public LinkedList<Card> getPileCards() {
        return pileCards;
    }

    public void addCard(Card c) throws Exception {
        if (getNumCardsInPile() == 0) {
            c.setVisibility(true);
            pileCards.addFirst(c);
        }
        else if (getFirstCard().getRank() == 0) {
            throw new Exception("This is not the next card in the sequence. Try again.");
        }
        else if (c.getColor() == getFirstCard().getColor()) {
            throw new Exception("Incompatible colors... try again.");
        }
        else if (getFirstCard().getRank() - 1 == c.getRank()) {
            c.setVisibility(true);
            pileCards.addFirst(c);
        }
        else {
            throw new Exception("This is not the next card in the sequence. Try again.");
        }
    }

    public void removeFirstCard() {
        pileCards.removeFirst();
        if (pileCards.size() > 0) {
            pileCards.getFirst().setVisibility(true);
        }
    }

    @Override
    public String toString() {
        String s = "[" + pileNum + "] ";
        for (int i = pileCards.size() - 1; i >= 0; i--) {
            s += pileCards.get(i).toString() + " ";
        }
        return s + "\n";
    }
}
