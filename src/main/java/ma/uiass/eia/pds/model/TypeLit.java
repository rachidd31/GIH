package ma.uiass.eia.pds.model;

public enum TypeLit {
    STANDARD("standard"),
    ELECTRIC("électrique");
    String name;

    TypeLit(String name){
        this.name = name;
    }
    public static TypeLit fromString(String s){
        TypeLit typeLit = STANDARD;
        if (s.equalsIgnoreCase("électrique"))
            typeLit = ELECTRIC;
        return typeLit;
    }
}
