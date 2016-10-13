package model;

/**
 * Created by nick on 10/11/16.
 */
public enum SourceCondition {
    WS ("Waste", "WS"),
    TC ("Treatable-Clear", "TC"),
    TM ("Treatable-Muddy", "TM"),
    PT ("Potable", "PT");

    private final String type;
    private final String code;


    /**
     * Constructor for the enumeration
     *
     * @param tp   full name of the type
     * @param cd   letter code / abbreviation for the type
     */
    SourceCondition(String tp, String cd) {
        type = tp;
        code = cd;
    }

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
