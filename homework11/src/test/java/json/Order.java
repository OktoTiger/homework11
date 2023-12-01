package json;

public class Order {

    private String name;
    private String address;
    private String flat;

    private GroceryOrder grocery;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getFlat() {
        return flat;
    }

    public GroceryOrder getGrocery() {
        return grocery;
    }
}
