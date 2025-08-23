import java.util.ArrayList;

public class Customer {
    private int customerId;
    private String name;
    private String phone;
    private String address;
    private String subscriptionType;

    public Customer(int customerId, String name, String phone, String address, String subscriptionType) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.subscriptionType = subscriptionType;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + name + ", Phone: " + phone +
                ", Address: " + address + ", Subscription: " + subscriptionType;
    }
}
