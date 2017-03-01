package teamplaceholder.com.placeholderapp.Model;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Tanya on 2/28/2017.
 * Class that stores the information for a Water Source Report
 * written by an Account Holder of type User
 */

public class WaterSourceReport {
    private Date dateCreated;
    private int reportNumber;
    private String reporterName;
    private String location;
    private WaterType type;
    private Condition condition;
    private boolean isValidReport;

    private enum WaterType {
        BOTTLED, WELL, STREAM, LAKE, SPRING, OTHER;
    }

    private enum Condition {
        WASTE, TREATABLECLEAR, TREATABLEMUDDY, POTABLE;
    }

    public WaterSourceReport(String name, String location, WaterType type, Condition condition) {
        this(name, ((int) ((Math.random() * 3000) + 100)), location, type, condition);
    }

    public WaterSourceReport(String name, int reportNumber, String location, WaterType type, Condition condition) {
        this.dateCreated = new Date();
        this.reportNumber = reportNumber;
        this.reporterName = name;
        this.location = location;
        this.type = type;
        this.condition = condition;
        this.isValidReport = false; //changed to true by Worker
    }

    public String getDateandTime() {
        return dateCreated.toString();
    }

    public int getReportNumber() {
        return reportNumber;
    }

    public String getReporterName() {
        return reporterName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public WaterType getWaterType() {
        return type;
    }

    public void setWaterType(WaterType type) {
        this.type = type;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
