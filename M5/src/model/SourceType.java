package model;

/**
 * Created by nick on 10/11/16.
 */
public enum SourceType {
    BT ("Bottled", "BT"),
    WL ("Well", "WL"),
    ST ("Stream", "ST"),
    LK ("Lake", "LK"),
    SP ("Spring", "SP"),
    OTH ("Other", "OTH");
    private String type;

    private String code;

    /**
     * Constructor for the enumeration
     *
     * @param type   full name of the type
     * @param code   letter code / abbreviation for the type
     */
    SourceType(String type, String code) {
        this.type = type;
        this.code = code;
    }

    /**
     *
     * @return the full type name
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return the abbreviation for the type
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @return the display string representation of the type
     */
    public String toString() { return type; }
}
