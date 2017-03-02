package teamplaceholder.com.placeholderapp.Model;


import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import java.util.Arrays;
import java.util.List;
/**
 * Created by Tanya on 2/28/2017.
 * Class that stores the information for a Water Source Report
 * written by an Account Holder of type User
 */

public class WaterSourceReport {
    private Date dateCreated;
    private int reportNumber;
    private String reporterName;
    private double latitude;
    private double longitude;
    private WaterType type;
    private Condition condition;
    private boolean isValidReport;

    public static List<WaterType> WaterTypeList = Arrays.asList(WaterType.values());
    public static List<Condition> WaterConditionList = Arrays.asList(Condition.values());

    public enum WaterType {
        BOTTLED("Bottled"),
        WELL("Well"),
        STREAM("Stream"),
        LAKE("Lake"),
        SPRING("Spring"),
        OTHER("Other");

        private String type;
        WaterType(String type) {
            this.type = type;
        }
        public String toString() {
            return this.type;
        }

    }

    public enum Condition {
        WASTE("Waste"),
        TREATABLECLEAR("Treatable Clear"),
        TREATABLEMUDDY("Treatable Muddy"),
        POTABLE("Potable");

        private String cond;
        Condition(String cond) {
            this.cond = cond;
        }
        public String toString() {
            return this.cond;
        }
    }

    public WaterSourceReport(String name, int reportNumber, double latitude, double longitude,
                             WaterType type, Condition condition) {
        this.dateCreated = new Date();
        this.reportNumber = reportNumber;
        this.reporterName = name;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getLatitude() {
        return this.latitude;
    }

    public void setLocation(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
