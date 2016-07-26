package enums;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

public enum Payment {
    CASH,
    CARD;

    @Override
    public String toString() {
        switch (this) {
            case CARD:
                return "card";
            case CASH:
                return "cash";
            default:
                return "Payment{}";
        }
    }

    public static Payment getEnumValue(String s) {
        switch (s){
            case "card":
                return CARD;
            case "cash":
                return CASH;
            default:
                throw new Error();
        }
    }
}
