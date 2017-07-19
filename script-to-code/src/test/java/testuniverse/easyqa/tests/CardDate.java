package testuniverse.easyqa.tests;

public class CardDate {
    private final String cardName;
    private final String cardDescription;

    public CardDate(String cardName, String cardDescription) {
        this.cardName = cardName;
        this.cardDescription = cardDescription;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardDescription() {
        return cardDescription;
    }
}
