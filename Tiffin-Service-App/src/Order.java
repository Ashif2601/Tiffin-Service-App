public class Order {
    private int orderId;
    private int customerId;
    private int menuId;
    private String deliveryStatus;

    public Order(int orderId, int customerId, int menuId, String deliveryStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.menuId = menuId;
        this.deliveryStatus = deliveryStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer ID: " + customerId + ", Menu ID: " + menuId +
                ", Delivery Status: " + deliveryStatus;
    }
}
