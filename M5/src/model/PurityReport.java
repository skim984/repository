package model;

/**
 * Created by nick on 10/11/16.
 */
public class PurityReport extends Report{
    public PurityReport(String name) {
        super(name);
    }

    private String loc;
    private int VPPM;
    private int CPPM;
    private PurityCondition pCond;


    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public int getVPPM() {
        return VPPM;
    }

    public void setVPPM(int VPPM) {
        this.VPPM = VPPM;
    }

    public int getCPPM() {
        return CPPM;
    }

    public void setCPPM(int CPPM) {
        this.CPPM = CPPM;
    }

    public PurityCondition getpCond() {
        return pCond;
    }

    public void setpCond(PurityCondition pCond) {
        this.pCond = pCond;
    }

    @Override
    public String getType() {
        return "Purity";
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tLocation: " + loc + "\n\tVirus PPM: " + VPPM
                + "\n\tContaminant PPM: " + CPPM + "\n\tCondition " + pCond;
    }
}
