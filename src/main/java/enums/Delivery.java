package enums;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

public enum Delivery {
    SELF,
    COURIER;

    @Override
    public String toString() {
        switch (this) {
            case SELF:
                return "self";
            case COURIER:
                return "courier";
            default:
                return "Delivery{}";
        }
    }

    public static Delivery getEnumValue(String s) {
        switch (s){
            case "self":
                return SELF;
            case "courier":
                return COURIER;
            default:
                throw new Error();
        }
    }
}




