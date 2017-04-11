package teamplaceholder.com.placeholderapp.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Tanya on 3/23/2017.
 *
 * Holds the information for Water Quality Reports for
 * Workers/Managers to create/edit
 */

public class WaterQualityReport {
    private final Date dateCreated;
    private final int reportNumber;
    private final String workerName;
    private double latitude;
    private double longitude;
    private OverallCondition condition;
    private double virusPPM;
    private double contaminantPPM;

    public static final List<WaterQualityReport.OverallCondition> WaterOverallConditionList =
            Arrays.asList(WaterQualityReport.OverallCondition.values());

    public enum OverallCondition {
        SAFE("Safe"),
        TREATABLE("Treatable"),
        UNSAFE("Unsafe");

        private final String cond;
        OverallCondition(String cond) {
            this.cond = cond;
        }
        public String toString() {
            return this.cond;
        }
    }

    public WaterQualityReport(Date dateCreated, String workerName, int reportNumber,
                             double latitude, double longitude, WaterQualityReport.OverallCondition condition,
                              double virusPPM, double contaminantPPM) {
        this.dateCreated = dateCreated;
        this.reportNumber = reportNumber;
        this.workerName = workerName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.condition = condition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }

    public WaterQualityReport(String workerName, int reportNumber, double latitude, double longitude,
                              WaterQualityReport.OverallCondition condition, double virusPPM,
                              double contaminantPPM) {
        this(new Date(), workerName, reportNumber, latitude, longitude, condition, virusPPM,
                contaminantPPM);
    }

    public boolean isCorrectlyClassified() {
        if ((virusPPM <= 1.0 && virusPPM >= 0) && (contaminantPPM <= 1.5 && contaminantPPM >= 0)) {
            if (condition.toString().equals(OverallCondition.SAFE.toString())) {
                return true;
            }
            return false;
        }

        if ((virusPPM > 1.0 && virusPPM <= 3.3) && (contaminantPPM > 1.5 && contaminantPPM <= 4.7)) {
            if (condition.toString().equals(OverallCondition.TREATABLE.toString())) {
                return true;
            }
            return false;
        }
        if (virusPPM > 3.3 && contaminantPPM  > 4.7) {
            if (condition.toString().equals(OverallCondition.UNSAFE.toString())) {
                return true;
            }
            return false;
        }
        throw new IllegalStateException("Water Quality Report is not correctly written");
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

    public String getWorkerName() {
        return workerName;
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

    public WaterQualityReport.OverallCondition getCondition() {
        return condition;
    }

    public void setCondition(WaterQualityReport.OverallCondition condition) {
        this.condition = condition;
    }

    public double getVirusPPM() {
        return virusPPM;
    }

    public void setVirusPPM(int virusPPM) {
        this.virusPPM = virusPPM;
    }

    public double getContaminantPPM() {
        return contaminantPPM;
    }

    public void setContaminantPPM(int contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }
}
