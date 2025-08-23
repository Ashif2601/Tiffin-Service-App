import java.util.ArrayList;

public class Menu {
    private int menuId;
    private String itemName;
    private double price;

    public Menu(int menuId, String itemName, double price) {
        this.menuId = menuId;
        this.itemName = itemName;
        this.price = price;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Menu ID: " + menuId + ", Item: " + itemName + ", Price: " + price;
    }
}
