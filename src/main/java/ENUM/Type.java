package ENUM;

public enum Type {

   INDENDØRS("Indendørs"),
    KONKURRENCE("Konkurrence"),
    KOMBI("Idendørs/Udendørs"),
    OBSERVATION("Observation"),
    SAMLER("Samler hobbyer"),
    UDENDØRS("Udendørs"),
    NA("---");

    private final String typeString;

    private Type(String typeString) {
        this.typeString = typeString;
    }
}
