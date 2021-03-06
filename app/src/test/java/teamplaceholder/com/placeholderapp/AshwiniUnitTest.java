package teamplaceholder.com.placeholderapp;

import org.junit.Test;

import java.util.Date;

import teamplaceholder.com.placeholderapp.model.WaterSourceReport;

import static org.junit.Assert.*;
/**
 * Created by ashwiniiyer on 4/8/17.
 */
public class AshwiniUnitTest {

/*
    @Test
    public void testGetterWorks() throws Exception{
        WaterSourceReport report = new WaterSourceReport(d, "name", 0, 1.001, 1.879, wt,condition);
        assertEquals(1.001,report.getLatitude(),0);
    }
*/
    @Test
    public void treatableClearSufficient(){
        WaterSourceReport report1 = new WaterSourceReport(new Date(), "name", 0, 1.001, 1.879, WaterSourceReport.WaterType.STREAM,
                WaterSourceReport.Condition.TREATABLECLEAR);
        assertEquals(true,report1.isWaterQualitySufficient());
    }

    @Test
    public void treatableClearOtherSufficient(){
        WaterSourceReport report2 = new WaterSourceReport(new Date(), "name", 0, 1.001, 1.879, WaterSourceReport.WaterType.OTHER,
                WaterSourceReport.Condition.TREATABLECLEAR);
        assertEquals(false,report2.isWaterQualitySufficient());
    }
    @Test
    public void treatableMuddyStreamSufficient(){
        WaterSourceReport report3 = new WaterSourceReport(new Date(), "name", 0, 1.001, 1.879, WaterSourceReport.WaterType.STREAM,
                WaterSourceReport.Condition.TREATABLEMUDDY);
        assertEquals(true,report3.isWaterQualitySufficient());
    }
    @Test
    public void treatableMuddySufficient(){
        WaterSourceReport report4 = new WaterSourceReport(new Date(), "name", 0, 1.001, 1.879, WaterSourceReport.WaterType.STREAM,
                WaterSourceReport.Condition.TREATABLEMUDDY);
        assertEquals(true,report4.isWaterQualitySufficient());
    }
    @Test
    public void treatableMuddySpringSufficient(){
        WaterSourceReport report5 = new WaterSourceReport(new Date(), "name", 0, 1.001, 1.879, WaterSourceReport.WaterType.SPRING,
                WaterSourceReport.Condition.TREATABLEMUDDY);
        assertEquals(true,report5.isWaterQualitySufficient());
    }
    @Test
    public void treatableMuddyLakeSufficient(){
        WaterSourceReport report6 = new WaterSourceReport(new Date(), "name", 0, 1.001, 1.879, WaterSourceReport.WaterType.LAKE,
                WaterSourceReport.Condition.TREATABLEMUDDY);
        assertEquals(false,report6.isWaterQualitySufficient());
    }
    @Test
    public void wasteSufficient(){
        WaterSourceReport report7 = new WaterSourceReport(new Date(), "name", 0, 1.001, 1.879, WaterSourceReport.WaterType.LAKE,
                WaterSourceReport.Condition.WASTE);
        assertEquals(false,report7.isWaterQualitySufficient());
    }
    @Test
    public void potableSufficient(){
        WaterSourceReport report8 = new WaterSourceReport(new Date(), "name", 0, 1.001, 1.879, WaterSourceReport.WaterType.STREAM,
                WaterSourceReport.Condition.POTABLE);
        assertEquals(true,report8.isWaterQualitySufficient());
    }
    @Test
    public void potableOtherSufficient(){
        WaterSourceReport report9 = new WaterSourceReport(new Date(), "name", 0, 1.001, 1.879, WaterSourceReport.WaterType.OTHER,
                WaterSourceReport.Condition.POTABLE);
        assertEquals(false,report9.isWaterQualitySufficient());
    }
/*
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    */
}
