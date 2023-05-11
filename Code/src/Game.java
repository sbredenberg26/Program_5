import java.util.*;

/**
 * The game's inner workings
 */

public class Game {

    // The initial card deck and draw pile
    private Deck cardDeck;

    // Holds the main seven piles
    private Pile[] piles;

    // Holds the four foundations piles
    private String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    private FoundationPile[] foundations;

    private boolean quitMove;
    private boolean quit;

    private boolean movesRemaining;
    private boolean won;

    private boolean playAgain;

    private int moveNum;

    public Game() {
        playAgain();
    }

    /**
     * Continues to loop until user quits, no valid moves remain, or player wins.
     */
    public void gameLoop() {
        Scanner scnr = new Scanner(System.in);
        String userInput;

        System.out.println(
                " ____         __  __         " + "\n" +
                "/ ___/ ____  / (_) /_____,_(_)_______" + "\n" +
                "\\___ \\/ __ \\/ / / __/ __  / / __/ __ \\" + "\n" +
                " __/ / /_/ / / / /_/ /_/ / / / /  ___/" + "\n" +
                "/___/\\____/_/_/\\__/\\__,_/_/_/  \\____/"
        );

        while (true) {

            if (playAgain) {
                playAgain();
            }

            printBoard();
            System.out.print("Welcome to Solitaire! ");

            quit = false;

            movesRemaining = true;
            won = false;

            playAgain = false;

            while (true) {
                System.out.println("What's your next move? Enter 'o' for options"
                        + " or 'q' to quit.");

                while (true) {
                    try {
                        quitMove = false;
                        userInput = scnr.nextLine();
                        checkPlayerMove(userInput); // Also runs player move
                        break;
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                won = hasWon();
                movesRemaining = movesRemaining();

                if (quit || won || !movesRemaining) {
                    break;
                }

                if (quitMove) {
                    System.out.print("But the game's not over yet. ");
                }

                else {
                    printBoard();
                }

            }

            if (quit) {
                System.out.println("\nLame.\n");
            }

            else if (!movesRemaining) {
                System.out.println("\nYou lost to yourself - congratulations!\n");
            }

            else {
                System.out.println("\nYou won! Nice job.\n");
            }

            System.out.println("Would you like to play again? Enter 'y' for yes, or any"
                    + " other character for no.");
            userInput = scnr.nextLine();

            if (!userInput.equals("y")) {
                break;
            }
            else {
                playAgain = true;
            }

        }

    }

    /**
     * Ensures that the user's next move is, in fact, a move
     * @param userInput what the user wants to do
     * @throws Exception if it's not a valid move
     */
    public void checkPlayerMove(String userInput) throws Exception {
        Scanner scanInput = new Scanner(userInput);

        if (scanInput.hasNextInt()) {
            moveNum = scanInput.nextInt();

            if (moveNum < 1 || moveNum > 2) {
                throw new Exception("That is not a valid move number, you must enter either"
                        + " 1 or 2. Try again.");
            }

            runPlayerMove(moveNum);
        }
        else if (userInput.equals("o")) {
            System.out.println("You can choose 1 or 2 to do the following: ");
            System.out.println("     1) Flip over a new card");
            System.out.println("     2) Move an existing card");
            throw new Exception("If you want to quit, enter 'q'. So, what will it be?");
        }
        else if (userInput.equals("q")) {
            quit = true;
        }
        else {
            throw new Exception("Not even an integer. Try again.");
        }
    }

    /**
     * Draws a new card or prepares to move a card, as appropriate
     * @param moveNum the number of the move desired
     */
    public void runPlayerMove(int moveNum) {
        if (moveNum == 1) {
            drawCard();
        }
        else if (moveNum == 2) {
            moveCard();
        }
    }

    /**
     * Plays a new card from the deck, prompts the user where to put it
     */
    public void drawCard() {
        Scanner scnr = new Scanner(System.in);
        String userInput;

        Card c = new Card (cardDeck.playCard());

        System.out.print("You drew the " + c);

        System.out.println(". Where do you want to put it? As always,"
                + " enter 'o' for options.");
        while (true) {
            try {
                userInput = scnr.nextLine();
                checkMoveTo(c, userInput);
                break;
            }
            catch (Exception e) {
                System.out.println(e. getMessage());
            }
        }

        if (quitMove) {
            System.out.print("Well then, discarded. ");
        }
    }

    /**
     * Prompts the user through a series of questions to ultimately moves a card or stack of
     * cards to a new pile
     */
    public void moveCard() {
        Scanner scnr = new Scanner(System.in);
        String userInput;

        System.out.println("Which pile contains the card you want to move?");

        while (true) {
            try {
                userInput = scnr.nextLine();
                checkPileNum(userInput); // Check that user enters proper pile number
                break;
            }
            catch (Exception e) {
                System.out.println(e. getMessage());
            }
        }

        if (quitMove) {
            System.out.print("Okay - move cancelled. ");
        }

        else {
            Scanner scanInput = new Scanner(userInput);
            int fromPileNum = scanInput.nextInt() - 1;

            System.out.println("Which card is it? As always, enter 'o' for options.");

            while (true) {
                try {
                    userInput = scnr.nextLine();
                    checkCardSelection(userInput, fromPileNum); // check that card index is appropriate
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            if (quitMove) {
                System.out.print("Okay - move cancelled. ");
            }

            else {
                scanInput = new Scanner(userInput);
                int cardNum = scanInput.nextInt() - 1;

                System.out.println("Where do you want to move it to?");

                while (true) {
                    try {
                        userInput = scnr.nextLine();

                        if (cardNum == 0) { // Only one card
                            Card c = new Card(piles[fromPileNum].getCard(cardNum));
                            checkMoveTo(c, userInput);
                        }

                        else { // Multiple linked cards
                            LinkedList<Card> stack = new LinkedList<>();
                            for (int i = 0; i < cardNum + 1; i++) {
                                stack.addLast(piles[fromPileNum].getCard(i));
                            }

                            //We think this is a test statement...
                            //System.out.println("\n" + stack + "\n");

                            checkMoveTo(stack, userInput);
                        }

                        break;
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                if (quitMove) {
                    System.out.print("Okay - move cancelled. ");
                }

                else {
                    for (int i = 0; i < cardNum + 1; i++) {
                        piles[fromPileNum].removeFirstCard();
                    }
                }

            }
        }
    }

    /**
     * Ensures that the user inputs an appropriate pile number
     * @param userInput is the user's response
     * @throws Exception is their input is invalid
     */
    public void checkPileNum(String userInput) throws Exception {
        Scanner scanInput = new Scanner(userInput);

        if (scanInput.hasNextInt()) {
            int pileNum = scanInput.nextInt() - 1;

            if (pileNum < 0 || pileNum > 6) {
                throw new Exception("The pile number you entered was invalid. Try again.");
            }

            else if (piles[pileNum].getNumCardsInPile() == 0) {
                throw new Exception("There aren't any cards in that pile. Try again.");
            }

        }

        else if (userInput.equals("o")) {
            System.out.print("Enter the desired pile number, 1, 2, ... 7, or 'q' to quit. ");
            throw new Exception("So, your move?");
        }

        else if (userInput.equals("q")) {
            quitMove = true;
        }

        else {
            throw new Exception("That wasn't even an integer - try again.");
        }

    }

    /**
     * Ensures that the card in question can be moved to the selected pile
     * @param card the card in question
     * @param userInput the users response
     * @throws Exception if they say bad things
     */
    public void checkMoveTo(Card card, String userInput) throws Exception {
        Scanner scanInput = new Scanner(userInput);

        if (scanInput.hasNextInt()) {
            int toPileNum = scanInput.nextInt() - 1;

            if (toPileNum < 0 || toPileNum > 6) {
                throw new Exception("The pile number you entered was invalid. Try again.");
            }

            try {
                piles[toPileNum].addCard(card);
            }
            catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            System.out.println("Added the " + card + " to pile " + (toPileNum + 1) + "");
        }

        else if (userInput.equals("o")) {
            System.out.print("Enter the desired pile number, 1, 2, ... 7, "
                    + "or add the card to a foundation by entering 'F1', 'F2', 'F3', or 'F4'."
                    + " These values correspond respectively to spades, hearts, diamonds, and clubs. ");
            if (moveNum == 1) {
                System.out.print("You may also choose to discard the card by inputting 'd', or "
                        + " quit using 'q'. ");
            }
            throw new Exception("So, your move?");
        }

        else if (userInput.equals("q")) {
            quitMove = true;
        }

        else if(userInput.equals("d") && moveNum == 1) {
            System.out.println("Discarded. ");
            cardDeck.addCard(card);
        }

        else if (userInput.charAt(0) == 'F') {
            scanInput.useDelimiter("");
            scanInput.next();

            if (!scanInput.hasNextInt()) {
                throw new Exception("That's not a valid foundation. Try again.");
            }

            int foundNum = scanInput.nextInt() - 1;

            if (foundNum < 0 || foundNum > 3) {
                throw new Exception("That's not a valid foundation. Try again.");
            }

            try {
                foundations[foundNum].addCard(card);
            }
            catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            System.out.println("Added the " + card.toString() + " to the "
                    + foundations[foundNum].getSuit().toLowerCase() + " foundation.");
        }

        else {
            throw new Exception("That was neither a pile number nor a foundation call. Try again.");
        }
    }

    /**
     * Moves a stack of cards from one pile to another
     * @param cardStack the cards up to the one they moved
     * @param userInput user's input
     * @throws Exception if bad things
     */
    public void checkMoveTo(LinkedList<Card> cardStack, String userInput) throws Exception {
        Scanner scanInput = new Scanner(userInput);
        int cardNum = cardStack.size() - 1;

        if (scanInput.hasNextInt()) {
            int toPileNum = scanInput.nextInt() - 1;

            if (toPileNum < 0 || toPileNum > 6) {
                throw new Exception("The pile number you entered was invalid. Try again.");
            }

            Iterator<Card> cardStackIt = cardStack.descendingIterator();

            try {
                while (cardStackIt.hasNext()) {
                    piles[toPileNum].addCard(cardStackIt.next());
                }
            }
            catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            System.out.println("Added the stack beginning with the "
                    + piles[toPileNum].getCard(cardNum).toString().toLowerCase() + " to pile "
                    + (toPileNum + 1) + "");
        }

        else if (userInput.equals("o")) {
            System.out.println("Enter the desired pile number, i.e., 1, 2, ... 7. If you want to"
                    + " quit, enter 'q'.");
            throw new Exception("So, your move?");
        }

        else if (userInput.equals("q")) {
            quitMove = true;
        }

        else if (userInput.charAt(0) == 'F') {
           throw new Exception("You can't add a stack of cards to a foundation. Try again.");
        }

        else {
            throw new Exception("The move you are trying to make is invalid. Try again.");
        }
    }

    /**
     * Ensures that the user enters a valid card selection, and loads the appropriate stack.
     * @param userInput users input
     * @param fromPileNum the pile that originally held the cards
     * @throws Exception if bad things
     */
    public void checkCardSelection(String userInput, int fromPileNum) throws Exception {
        Scanner scanInput = new Scanner(userInput);

        if (scanInput.hasNextInt()) {
            int cardNum = scanInput.nextInt() - 1;

            if (cardNum < 0 || cardNum + 1 > piles[fromPileNum].getNumCardsInPile()) {
                throw new Exception("The card number you entered is invalid. Try again.");
            }

            Card c = new Card(piles[fromPileNum].getCard(cardNum));

            if (!c.getVisibility()) {
                throw new Exception("You do not have access to this card yet. Try again.");
            }

        }

        else if (userInput.equals("o")) {
            String s = "";
            for (int i = 0; i < piles[fromPileNum].getNumCardsInPile(); i++) {
                s += "     " + (i + 1) + ") " + piles[fromPileNum].getCard(i) + "\n";
            }
            s = s.stripTrailing();
            System.out.println("Enter the desired card number, given that\n" + s);
            System.out.print("You may also choose to quit by entering 'q'. ");
            throw new Exception("So... your move?");
        }

        else if (userInput.equals("q")) {
            quitMove = true;
        }

        else {
            throw new Exception("That wasn't even an integer - try again.");
        }

    }

    /**
     * Returns whether any valid moves remain
     * @return true if at least one exists
     */
    public boolean movesRemaining() {
        return true; // STUB
    }

    /**
     * Returns whether the user has won the game or not
     * @return true if they have
     */
    public boolean hasWon() {
        for (FoundationPile fp : foundations) {
            if (fp.getNumCardsInPile() != 13) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sets all the class variables to their default
     */
    public void playAgain() {
        cardDeck = new Deck();
        cardDeck.shuffle();

        piles = new Pile[7];
        for (int i = 0; i < 7; i++) {
            InitialPile p = new InitialPile(i + 1, cardDeck);
            piles[i] = p;
        }

        foundations = new FoundationPile[4];
        for (int i = 0; i < 4; i++) {
            FoundationPile fp = new FoundationPile(suits[i], i);
            foundations[i] = fp;
        }
    }

    /**
     * Prints the 'board' as it stands
     */
    public void printBoard() {
        System.out.println();

        /*
        for (Pile p : piles) {
            System.out.println(p);
        }

        for (FoundationPile fp : foundations) {
            System.out.println(fp);
        }

         */

        LinkedList<String> printPiles = new LinkedList<>();

        int maxNumCards = piles[0].getNumCardsInPile();

        for(Pile p : piles) {
            System.out.print("   [" + p.getPileNum() + "]   ");

            if (p.getNumCardsInPile() > maxNumCards) {
                maxNumCards = p.getNumCardsInPile();
            }

        }
        System.out.println();

        for (int i = 0; i < maxNumCards + 4; i++) {
            printPiles.add("");
        }

        for (Pile p : piles) {
            Iterator<Card> throughPile = p.getPileCards().descendingIterator();

            if (p.getNumCardsInPile() != 0) {
                printPiles.set(0, printPiles.get(0) + "  _____  ");
            }
            else {
                printPiles.set(0, printPiles.get(0) + "         ");
            }

            for (int i = 1; i < maxNumCards + 1; i++) {
                try {
                    printPiles.set(i, printPiles.get(i) + throughPile.next().printBoard());
                }
                catch (Exception e) {
                    printPiles.set(i, printPiles.get(i) + "         ");
                }
            }

        }

        for (String lines : printPiles) {
            System.out.println(lines);
        }
        System.out.println();

        for (FoundationPile fp : foundations) {
            System.out.println(fp);
        }
        System.out.println();

    }
}
