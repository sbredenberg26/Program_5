import java.util.Random;

public class CardTester {
    public static void main(String[] args) {
        Random RandAlThor = new Random();

        //Create 4 cards based on random numbers from 0-51. For each card report the number
        //generated and the name of the card created.
        int c = RandAlThor.nextInt(52);
        Card cardA = new Card(c);
        System.out.println("The card number was " + c + " and the card is the " + cardA.toString());
        c = RandAlThor.nextInt(52);
        Card cardB = new Card(c);
        System.out.println("The card number was " + c + " and the card is the " + cardB.toString());
        c = RandAlThor.nextInt(52);
        Card cardC = new Card(c);
        System.out.println("The card number was " + c + " and the card is the " + cardC.toString());
        c = RandAlThor.nextInt(52);
        Card cardD = new Card(c);
        System.out.println("The card number was " + c + " and the card is the " + cardD.toString());

        System.out.println();

        //Create the 6 of diamonds and 4 of clubs by using the appropriate strings,
        //print both, and verify that they are not equal
        Card cardE = new Card("six", "diamonds");
        System.out.println("The " + cardE.toString() + " has been created.");
        Card cardF = new Card("four", "clubs");
        System.out.println("The " + cardF.toString() + " has been created.");
        System.out.println("Card E and Card F are equal: " + cardE.equals(cardF));

        System.out.println();
        //Now change the 4 of clubs to another 6 of diamonds and check that they are equal
        cardF.setSuit(1);
        cardF.setValue(5);
        System.out.println("Another " + cardF.toString() + " has been created.");
        System.out.println("Card E and Card F are equal: " + cardE.equals(cardF));
        System.out.println();

        //(Try to) create a card for the “fnoop of flark.” Print the result.
        Card nonsense = new Card("fnoop", "flark");
        System.out.print(nonsense.toString() + " is not a real card.");
    }
}
