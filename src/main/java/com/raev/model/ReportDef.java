package main.java.com.raev.model;

public class ReportDef {
    private int topPerformersThreshold;
    private boolean useExprienceMultiplier;
    private int periodLimit;

    public ReportDef() {
    }

    public ReportDef(int topPerformersThreshold, boolean useExprienceMultiplier, int periodLimit) {
        this.topPerformersThreshold = topPerformersThreshold;
        this.useExprienceMultiplier = useExprienceMultiplier;
        this.periodLimit = periodLimit;
    }

    public int getTopPerformersThreshold() {
        return topPerformersThreshold;
    }

    public void setTopPerformersThreshold(int topPerformersThreshold) {
        this.topPerformersThreshold = topPerformersThreshold;
    }

    public boolean isUseExprienceMultiplier() {
        return useExprienceMultiplier;
    }

    public void setUseExprienceMultiplier(boolean useExprienceMultiplier) {
        this.useExprienceMultiplier = useExprienceMultiplier;
    }

    public int getPeriodLimit() {
        return periodLimit;
    }

    public void setPeriodLimit(int periodLimit) {
        this.periodLimit = periodLimit;
    }

    @Override
    public String toString() {
        return "ReportDef{" +
                "TopPerformersThreshold=" + topPerformersThreshold +
                ", useExprienceMultiplier=" + useExprienceMultiplier +
                ", periodLimit=" + periodLimit +
                '}';
    }
}
