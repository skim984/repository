package model;

/**
 * Created by nick on 10/11/16.
 */
public class SourceReport extends Report{
    private SourceType st;
    private SourceCondition sc;

    public SourceReport(String name) {
        super(name);
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
        return super.toString() +
                "\n\tLocation: " + getLocation() +
                "\n\tSource Type: " + getSt() +
                "\n\tSource Condition: " + getSc();
    }
}
