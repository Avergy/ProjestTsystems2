package enums;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

public enum StatusOrder {
    WAITING_FOR_PAYMENT,
    WAITING_FOR_SHIPMENT,
    SHIPPED,
    DELIVERED;

    @Override
    public String toString() {
        switch (this) {
            case WAITING_FOR_PAYMENT:
                return "waiting for payment";
            case WAITING_FOR_SHIPMENT:
                return "waiting for shipment";
            case SHIPPED:
                return "shipped";
            case DELIVERED:
                return "delivered";
            default:
                return "StatusOrder{}";
        }
    }

    public static StatusOrder getEnumValue(String s) {
        switch (s){
            case "waiting for payment":
                return WAITING_FOR_PAYMENT;
            case "waiting for shipment":
                return WAITING_FOR_SHIPMENT;
            case "shipped":
                return SHIPPED;
            case "delivered":
                return DELIVERED;
            default:
                throw new Error();
        }
    }
}
