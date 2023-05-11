import java.util.HashMap;
import java.util.Map;

/**
 * ...
 */
public class Card {

    private String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    private char[] unicodeSuits = {'\u2664', '\u2661', '\u2662', '\u2667'};
    Map<String, Character> abrevSuits;

    private String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King"};
    private String[] abrevValues = {"A ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10",
            "J ", "Q ", "K "};

    private String suit;
    private String value;
    private int rank;

    private boolean isVisible;

    /**
     * ...
     * @param cardNum
     */
    public Card(int cardNum) {
        abrevSuits = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            abrevSuits.put(suits[i], unicodeSuits[i]);
        }

        setRank(cardNum % 13);
        setValue(values[rank]);

        if ((cardNum / 13) == 0) {
            setSuit(suits[0]); // For Hearts
        }
        else if ((cardNum / 13) == 1) {
            setSuit(suits[1]); // For Diamonds
        }
        else if ((cardNum / 13) == 2) {
            setSuit(suits[2]); // For Clubs
        }
        else if ((cardNum / 13) == 3) {
            setSuit(suits[3]); // For Spades
        }

        setVisibility(true);
    }

    /**
     * Copy constructor
     * @param c is the card being copied
     */
    public Card(Card c) {
        abrevSuits = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            abrevSuits.put(suits[i], unicodeSuits[i]);
        }

        setSuit(c.suit);
        setValue(c.value);
        setRank(c.rank);
        setVisibility(c.isVisible);
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public int getRank() {
        return rank;
    }

    public char getColor() {
        if (suit.equals("Hearts") || suit.equals("Diamonds")) {
            return 'R';
        }
        else {
            return 'B';
        }
    }

    public boolean getVisibility() {
        return isVisible;
    }

    public String printBoard() {
        if (isVisible) {
            return " |" + abrevValues[rank] + " " +  abrevSuits.get(suit) + "| ";
        }
        return " |_____| ";
    }

    @Override
    public String toString() {
        if (isVisible) {
            return " |" + abrevValues[rank] + " " +  abrevSuits.get(suit) + "| ";
        }
        return " |_____| ";
    }
}
