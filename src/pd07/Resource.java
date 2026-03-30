package pd07;

import lombok.Data;

@Data

public abstract sealed class Resource implements Comparable<Resource> permits Laptop, Console {
    private final String id;
    private String name;
    private double price;
    private Type type;
    public static int numberOfResources = 0;

    public Resource(String id, String name, double price, Type type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        numberOfResources++;
    }

    public abstract double getRentalCost();

    @Override
    public int compareTo(Resource resource) {
        return Double.compare(this.price, resource.price);
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", price: " + price + ", type: " + type;
    }
}


