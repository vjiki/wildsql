package com.github.vjiki.wildsql.models;


public enum AnimalClass {
    AGNATHA,
    CHRONDRICHTYES,
    OSTEICHTHYES,
    AMPHIBIA,
    REPTILIA,
    AVES,
    MAMMALIA
    ;

    /*AGNATHA("AGNATHA"),
    CHRONDRICHTYES("Chrondrichtyes (cartilaginous fish)"),
    OSTEICHTHYES("Osteichthyes (bony fish)"),
    AMPHIBIA("Amphibia (amphibians)"),
    REPTILIA("Reptilia (reptiles)"),
    AVES("Aves  (birds)"),
    MAMMALIA("Mammalia (mammals)")
    ;

    private final String name;

    AnimalClass(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public static final String[] STRINGS = Arrays.stream(AnimalClass.values())
            .map(Enum::name)
            .collect(Collectors.toList())
            .toArray(new String[0]);

     */

}
