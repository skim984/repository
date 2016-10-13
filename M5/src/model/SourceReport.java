package model;

/**
 * Created by nick on 10/11/16.
 */
public class SourceReport extends Report{
    private String loc;
    private SourceType st;
    private SourceCondition sc;

    public SourceReport(String name) {
        super(name);
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public SourceType getSt() {
        return st;
    }

    public void setSt(SourceType st) {
        this.st = st;
    }

    public SourceCondition getSc() {
        return sc;
    }

    public void setSc(SourceCondition sc) {
        this.sc = sc;
    }

    public String getType() {
        return "Source";
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tLocation: " + loc + "\n\tSource Type: " + st + "\n\tSource Condition: " + sc;
    }
}
