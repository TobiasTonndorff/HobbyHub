package ENUM;

public enum Category {


    GERNEREL("Generel"),
    EDUCATIONAL("Educational hobbies"),
    SAMLINGER("Samler hobbyer"),
    KONKURRENCE("Konkurrence"),
    OBSERVATION("Observation");


    private final String categoryString;

    private Category(String categoryString) {
        this.categoryString = categoryString;
    }
}
