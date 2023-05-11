public class Card {
    private static String[] suits = new String[] {"hearts", "diamonds", "clubs", "spades"};
    private static String[] faceValues = new String[] {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private String suit;
    private String value;

    /**
     * Retrieves the suit of a card.
     * @return suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Retrieves the suit of a card.
     * @return face value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the suit of the card.
     * @param s is the number corresponding to where the suit is in the suits array.
     */
    public void setSuit(int s) {
        if(s >= 0 && s <= 3) {
            suit = suits[s];
        } else {
            suit = "";
        }
    }

    /**
     * Sets the face value of the card.
     * @param v is the number corresponding to where the face value is in the face value array.
     */
    public void setValue(int v) {
        if(v >= 0 && v <= 12) {
            value = faceValues[v];
        } else {
            value = "";
        }
    }

    /**
     * Default constructor that creates a card with no suit or face value.
     */
    public Card() {
        suit = "";
        value = "";
    }

    /**
     * A constructor that assigns a suit and a face value based on the card's number.
     * @param c is the card's number in the overall deck.
     */
    public Card(int c) {
        if (c >= 0 && c <= 51) {
           setValue(c % 13);
           setSuit(c / 13);
        } else {
            setSuit(-1);
            setValue(-1);
        }
    }

    /**
     * A constructor that assigns a suit and a face value based on the two strings given.
     * @param v is the number corresponding to where the face value is in the face value array.
     * @param s is the number corresponding to where the suit is in the suits array.
     */
    public Card(String v, String s) {
        //This is to track if we got any bad values
        boolean vc = false;
        boolean sc = false;

        //This is to handle spelled out numbers as well as the version found in the array.
        switch(v){
            case "two": case "Two":
                v = "2";
                break;
            case "three": case "Three":
                v = "3";
                break;
            case "four": case "Four":
                v = "4";
                break;
            case "five": case "Five":
                v = "5";
                break;
            case "six": case "Six":
                v = "6";
                break;
            case "seven": case "Seven":
                v = "7";
                break;
            case "eight": case "Eight":
                v = "8";
                break;
            case "nine": case "Nine":
                v = "9";
                break;
            case "ten": case "Ten":
                v = "10";
                break;
        }

        for (int i = 0; i < 13; i++){
            if (v.equals(faceValues[i])) {
                setValue(i);
                vc = true;
                break;
            }
        }

        for (int i = 0; i < 4; i++){
            if (s.equals(suits[i])) {
                setSuit(i);
                sc = true;
                break;
            }
        }
        if(!sc || !vc) {
            suit = "";
            value = "";
        }
    }

    /**
     * A better toString method for cards than the default one.
     * @return the full name of the card
     */
    public String toString() {
        return(value + " of " + suit);
    }

    /**
     * An equals() method for comparing two cards.
     * @param x the card that is being compared to the one that the method is called for.
     * @return A boolean indicative of whether the two cards are equal or not.
     */
    public boolean equals(Card x) {
        return (this.suit.equals(x.getSuit()) && this.value.equals(x.getValue()));
    }
}