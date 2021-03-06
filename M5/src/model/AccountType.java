package model;

/**
 * Created by Sunpil Kim on 10/1/2016.
 */
public enum AccountType {
    UR ("USER", "UR"),
    WR ("WORKER", "WR"),
    MR ("MANAGER", "MR"),
    AD ("ADMINISTRATOR", "AD");

    private String type;

    private String code;

    /**
     * Constructor for the enumeration
     *
     * @param type   full name of the type
     * @param code   letter code / abbreviation for the type
     */
    AccountType(String type, String code) {
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
    public String toString() { return this.getType(); }


}
