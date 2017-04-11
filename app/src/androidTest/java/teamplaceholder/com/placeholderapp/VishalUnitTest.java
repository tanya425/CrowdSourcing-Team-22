package teamplaceholder.com.placeholderapp;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import teamplaceholder.com.placeholderapp.data.DBWaterSourceReportHandler;
import teamplaceholder.com.placeholderapp.model.WaterSourceReport;

/**
 * Created by vishal_tummalapalli on 4/11/17.
 */

@RunWith(AndroidJUnit4.class)
public class VishalUnitTest {

    private DBWaterSourceReportHandler dataBase;

    @Before
    public void setup() {
        dataBase = new DBWaterSourceReportHandler(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void testEmpty() {
        assertEquals(0, dataBase.getMaxId());
    }

    @Test
    public void testOne() {
        dataBase.addWaterSourceReport(new WaterSourceReport("Joe", 1, 10, 20,
                WaterSourceReport.WaterType.WELL, WaterSourceReport.Condition.WASTE));
        assertEquals(1, dataBase.getMaxId());
    }

    @Test
    public void testMultiple() {
        dataBase.addWaterSourceReport(new WaterSourceReport("Joe", 1, 10, 20,
                WaterSourceReport.WaterType.WELL, WaterSourceReport.Condition.WASTE));
        dataBase.addWaterSourceReport(new WaterSourceReport("Sam", 2, 10, -10,
                WaterSourceReport.WaterType.BOTTLED, WaterSourceReport.Condition.TREATABLEMUDDY));
        dataBase.addWaterSourceReport(new WaterSourceReport("Joe", 3, 20, 70,
                WaterSourceReport.WaterType.BOTTLED, WaterSourceReport.Condition.WASTE));
        assertEquals(3, dataBase.getMaxId());
    }

    @Test
    public void testDuplicate() {
        dataBase.addWaterSourceReport(new WaterSourceReport("Joe", 1, 10, 20,
                WaterSourceReport.WaterType.WELL, WaterSourceReport.Condition.WASTE));
        dataBase.addWaterSourceReport(new WaterSourceReport("Joe", 1, 10, 20,
                WaterSourceReport.WaterType.WELL, WaterSourceReport.Condition.WASTE));
        assertEquals(1, dataBase.getMaxId());
    }
}
