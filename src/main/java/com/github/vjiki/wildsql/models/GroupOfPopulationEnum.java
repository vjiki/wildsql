package com.github.vjiki.wildsql.models;

public enum GroupOfPopulationEnum {
    OVERPOPULATION(10_000_000),
    ABOVE_NORMAL(5_000_000),
    NORMAL(100_000),
    BELOW_NORMAL(10_000),
    ENDANGERED_TYPE(2_500);

    private int numberOfAnimals;

    GroupOfPopulationEnum(int numberOfAnimals) {
        this.numberOfAnimals = numberOfAnimals;
    }

    public int getNumberOfAnimals() {
        return numberOfAnimals;
    }
}
