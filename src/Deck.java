import java.util.Random;

public class Deck {
    static Random pick = new Random();
    private static Card[] deck = new Card[52];
    private static int nextToDeal;

    /**
     * This takes two locations in the deck and switches the contents.
     * @param a location 1
     * @param b location 2
     */
    public static void swap(int a, int b) {
        Card tempA = deck[a];
        deck[a] = deck[b];
        deck[b] = tempA;
    }

    /**
     * This shuffles the deck by going through the array and swapping each element with another randomly chosen element.
     */
    public static void shuffle() {
        for(int i = 0; i < 52; i++) {
            swap(i, pick.nextInt(52));
        }
    }

    /**
     * Default constructor for the deck, giving it 52 cards in order and setting nextToDeal to 0
     */
    public Deck(){
        for(int i = 0; i < 52; i++) {
            deck[i] = new Card(i);
        }
        nextToDeal = 0;
    }

    /**Turns the entire deck into one long string
     * @return the deck in terms of cards.
     */
    public String toString(){
        String d = "";
        for(int i = 0; i < 52; i++) {
            d = d + "\n" + deck[i].toString();
        }
        return d;
    }

    /**
     * Retrieves a card from the deck, given its location in the deck
     * @param i location in the deck
     * @return the specified
     */
    public Card getCard(int i) {
        return deck[i];
    }

    /**
     * Deals the next card by returning it and then increments nextToDeal to keep track of which card we're on.
     * @return the card that was just dealt.
     */
    public static Card dealCard() {
        Card c = deck[nextToDeal];
        nextToDeal = (nextToDeal + 1) % 52;
        return c;
    }

    /**
     * Takes an integer for the hand size and then deals that number off the top of the deck.
     * @param handSize the number of cards
     * @return the hand that was dealt
     */
    public Card[] dealHand(int handSize) {
        Card[] hand = new Card[handSize];
        for(int i = 0; i < hand.length; i++) {
                hand[i] = dealCard();
        }
        return hand;
    }

    /**
     * This method analyzes the hand and reports what the best poker hand (that has to do with matching values) is.
     * @param hand the hand being analyzed
     * @return the best poker hand
     */
    public static String analyze (Card[] hand) {
        if(hand.length != 5) {
            return "Invalid hand!";
        } else {
            //An array with the face values has been created, as well as an empty array to record how many cards of suit
            //the hand has
            String[] faces = new String[] {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
            int[] valueCounts = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for(int i = 0; i < hand.length; i++) {
                for(int j = 0; j < 13; j++) {
                    if(hand[i].getValue().equals(faces[j])){
                        valueCounts[j]++;
                        break;
                    }
                }
            }

            //Now that there's a tally, go through the array with the tally and figure out what's the highest number of
            //multiple cards there are.
            int multiple = 0;
            int highest = 0;
            for(int i = 0; i < 13; i++){
                if(valueCounts[i] >= multiple) {
                    multiple = valueCounts[i];
                    highest = i;
                }
            }

            //Once we know what the highest hand is, report
            switch(multiple){
                case 1:
                    return "High card: " + faces[highest] + " - You have a terrible hand; your best thing is your highest card with the value "
                    + faces[highest] + ".";
                case 2:
                    return "Two of a kind: " + faces[highest] + " - You have a fairly common hand; You have a pair of cards with the best value of "
                            + faces[highest] + ".";
                case 3:
                    return "Three of a kind: " + faces[highest] + " - You have an uncommon hand; You have three cards with the value of "
                            + faces[highest] + ".";
                case 4:
                    return "4 of a kind: " + faces[highest] + " - You have a great hand! You have all the cards with the value of "
                            + faces[highest] + ".";
            }
        }
        //This is only here because the compiler was complaining about there not being a return statement
        //even though all scenarios are taken care of, and theoretically it should never even get here.
        return "";
    }
}
