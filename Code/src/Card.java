import java.util.HashMap;
import java.util.Map;

/**
 * This is the card class. It takes care of creating and managing individual cards.
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
     * Creates cards with suits and values based on its number
     * @param cardNum int indicating what the card's number is
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

    /**
     * sets the suit of the card
     * @param suit string indicating its suit
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    /**
     * sets the value of the card
     * @param value string indicating its value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * sets the rank of the card
     * @param rank int of what its value is (from 1-13, within the suit)
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * sets whether the card is visible to the player or not
     * @param isVisible boolean indicating whether it's visible or not
     */
    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * gets and returns the suit
     * @return suit String of the suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * gets and returns the value
     * @return value string of its value
     */
    public String getValue() {
        return value;
    }

    /**
     * gets and returns the rank
     * @return rank int indicating its rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * gets and returns the color
     * @return color char of whether it's red (R) or black (B)
     */
    public char getColor() {
        if (suit.equals("Hearts") || suit.equals("Diamonds")) {
            return 'R';
        }
        else {
            return 'B';
        }
    }

    /**
     * gets and returns whether it is visible to the player or not
     * @return isVisible boolean indicating if it's visible
     */
    public boolean getVisibility() {
        return isVisible;
    }

    /**
     * Sets what the card is supposed to look like when printed to the board
     * @return what the card should look like
     */
    public String printBoard() {
        if (isVisible) {
            return " |" + abrevValues[rank] + " " +  abrevSuits.get(suit) + "| ";
        }
        return " |_____| ";
    }

    /**
     * This is our version of the toString
     * @return the string that's supposed to be printed
     */
    @Override
    public String toString() {
        if (isVisible) {
            return " |" + abrevValues[rank] + " " +  abrevSuits.get(suit) + "| ";
        }
        return " |_____| ";
    }
}
