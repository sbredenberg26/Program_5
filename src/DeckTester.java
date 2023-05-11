public class DeckTester {
    public static void main(String[] args) {
        //Create a deck, shuffle and print it out
        Deck play = new Deck();
        play.shuffle();
        System.out.println(play.toString());
        System.out.println("");

        //Create a hand, print it, and analyze for the best hand
        Card[] hand = play.dealHand(5);
        System.out.println("This is your hand:");
        for(int i = 0; i < hand.length; i++) {
            System.out.println(hand[i].toString());
        }
        System.out.println(Deck.analyze(hand));
        System.out.println();

        //Create another hand, print it, and analyze for the best hand
        hand = play.dealHand(5);
        System.out.println("This is your hand:");
        for(int i = 0; i < hand.length; i++) {
            System.out.println(hand[i].toString());
        }
        System.out.println(Deck.analyze(hand));

        System.out.println();

        /*
        //Rig my hand! (To test that the rarer hands don't break the code)
        hand[0].setValue(12);
        hand[0].setSuit(0);
        hand[1].setValue(0);
        hand[1].setSuit(0);
        hand[2].setValue(0);
        hand[2].setSuit(1);
        hand[3].setValue(0);
        hand[3].setSuit(2);
        hand[4].setValue(0);
        hand[4].setSuit(3);

        System.out.println("This is your rigged hand:");
        for(int i = 0; i < hand.length; i++) {
            System.out.println(hand[i].toString());
        }

        System.out.println(Deck.analyze(hand));
         */
    }
}
