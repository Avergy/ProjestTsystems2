package enums;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

public enum StatusPayment {
    WAITING_FOR_PAYMENT,
    PAID;

    @Override
    public String toString() {
        switch (this) {
            case WAITING_FOR_PAYMENT:
                return "waiting for payment";
            case PAID:
                return "paid";
            default:
                return "StatusPayment{}";
        }
    }

    public static StatusPayment getEnumValue(String s) {
        switch (s){
            case "waiting for payment":
                return WAITING_FOR_PAYMENT;
            case "paid":
                return PAID;
            default:
                throw new Error();
        }
    }
}
