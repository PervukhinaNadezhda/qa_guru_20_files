package qa.guru.model;

import java.util.List;

public class Model {
    private String name;
    private int fruitQuantity;
    private List<String> fruits;

    public String getName() {
        return name;
    }

    public int getFruitQuantity() {
        return fruitQuantity;
    }

    public List<String> getFruits() {
        return fruits;
    }
}