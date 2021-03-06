package teamplaceholder.com.placeholderapp.model;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Tanya on 2/28/2017.
 * Class that stores the information for a Water Source Report
 * written by an Account Holder of type User
 */

public class WaterSourceReport {
    private final Date dateCreated;
    private final int reportNumber;
    private final String reporterName;
    private double latitude;
    private double longitude;
    private WaterType type;
    private Condition condition;

    public static final List<WaterType> WaterTypeList = Arrays.asList(WaterType.values());
    public static final List<Condition> WaterConditionList = Arrays.asList(Condition.values());

    public enum WaterType {
        BOTTLED("Bottled"),
        WELL("Well"),
        STREAM("Stream"),
        LAKE("Lake"),
        SPRING("Spring"),
        OTHER("Other");

        private final String type;
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

        private final String cond;
        Condition(String cond) {
            this.cond = cond;
        }
        public String toString() {
            return this.cond;
        }
    }

    public WaterSourceReport(Date dateCreated, String reporterName, int reportNumber,
                             double latitude, double longitude, WaterType type, Condition condition) {
        this.dateCreated = dateCreated;
        this.reportNumber = reportNumber;
        this.reporterName = reporterName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.condition = condition;
        //boolean isValidReport = false;
    }

    public WaterSourceReport(String reporterName, int reportNumber, double latitude, double longitude,
                             WaterType type, Condition condition) {
        this(new Date(), reporterName, reportNumber, latitude, longitude, type, condition);
    }

    /*
    Tests to see whether the water quality is clean and sufficient
     */

    public boolean isWaterQualitySufficient() {
        if (condition.toString().equals(Condition.POTABLE.toString())) {
            return !type.toString().equals(WaterType.OTHER.toString());
        }
        if (condition.toString().equals(Condition.TREATABLECLEAR.toString())) {
            return !type.toString().equals(WaterType.OTHER.toString());
        }
        if (condition.toString().equals(Condition.TREATABLEMUDDY.toString())) {
            return type.toString().equals(WaterType.STREAM.toString()) || type.toString().equals(WaterType.SPRING.toString()) || type.toString().equals(WaterType.LAKE.toString());
        }
        if (condition.toString().equals(Condition.WASTE.toString())) {
            return false;
        }
        return false;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getDateString() {
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
