package teamplaceholder.com.placeholderapp;

import org.junit.Test;

import java.util.Date;

import teamplaceholder.com.placeholderapp.model.WaterQualityReport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tanya on 4/9/2017.
 */

public class TanyaJUnitTest {

    @Test(expected = IllegalStateException.class)
    public void testMinEdgeCases() {
        WaterQualityReport w1 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.SAFE, Integer.MIN_VALUE, 0.5);
        w1.isCorrectlyClassified();

        WaterQualityReport w2 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.SAFE, 0.5, Integer.MIN_VALUE);
        w2.isCorrectlyClassified();
    }

    @Test
    public void testMaxEdgeCaseCorrect() {
        WaterQualityReport w1 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.UNSAFE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertTrue(w1.isCorrectlyClassified());
    }

    @Test
    public void testMaxEdgeCaseIncorrect() {
        WaterQualityReport w1 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.SAFE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(false, w1.isCorrectlyClassified());
        WaterQualityReport w2 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.TREATABLE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(false, w2.isCorrectlyClassified());
    }

    @Test
    public void testSafeBorderCasesCorrect() {
        WaterQualityReport w1 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.SAFE, 0, 1.5);
        assertTrue(w1.isCorrectlyClassified());

        WaterQualityReport w2 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.SAFE, 1.0, 0);
        assertTrue(w2.isCorrectlyClassified());

        WaterQualityReport w3 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.SAFE, 1.0, 1.5);
        assertTrue(w3.isCorrectlyClassified());
    }

    @Test
    public void testSafeBorderCasesIncorrect() {
        WaterQualityReport w1 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.TREATABLE, 0, 1.5);
        assertEquals(false, w1.isCorrectlyClassified());

        WaterQualityReport w2 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.TREATABLE, 1.0, 0);
        assertEquals(false,w2.isCorrectlyClassified());

        WaterQualityReport w3 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.TREATABLE, 1.0, 1.5);
        assertEquals(false, w3.isCorrectlyClassified());

        WaterQualityReport w4 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.UNSAFE, 0, 1.5);
        assertEquals(false, w4.isCorrectlyClassified());

        WaterQualityReport w5 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.UNSAFE, 1.0, 0);
        assertEquals(false,w5.isCorrectlyClassified());

        WaterQualityReport w6 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.UNSAFE, 1.0, 1.5);
        assertEquals(false, w6.isCorrectlyClassified());
    }

    @Test
    public void testWithinRangeCasesCorrect() {
        WaterQualityReport w1 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.TREATABLE, 2.1, 2.1);
        assertTrue(w1.isCorrectlyClassified());
    }

    @Test
    public void testWithinRangeCasesIncorrect() {
        WaterQualityReport w1 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.SAFE, 2.1, 2.1);
        assertEquals(false, w1.isCorrectlyClassified());

        WaterQualityReport w2 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.UNSAFE, 2.1, 2.1);
        assertEquals(false, w2.isCorrectlyClassified());
    }

    @Test
    public void testTreatableBorderCasesCorrect() {
        WaterQualityReport w1 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.TREATABLE, 1.1, 4.7);
        assertTrue(w1.isCorrectlyClassified());

        WaterQualityReport w2 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.TREATABLE, 3.3, 1.6);
        assertTrue(w2.isCorrectlyClassified());

        WaterQualityReport w3 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.TREATABLE, 3.3, 4.7);
        assertTrue(w3.isCorrectlyClassified());
    }

    @Test
    public void testTreatableBorderCasesIncorrect() {
        WaterQualityReport w1 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.SAFE, 1.1, 4.7);
        assertEquals(false, w1.isCorrectlyClassified());

        WaterQualityReport w2 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.SAFE, 3.3, 1.6);
        assertEquals(false, w2.isCorrectlyClassified());

        WaterQualityReport w3 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.SAFE, 3.3, 4.7);
        assertEquals(false, w3.isCorrectlyClassified());

        WaterQualityReport w4 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.UNSAFE, 1.1, 4.7);
        assertEquals(false, w4.isCorrectlyClassified());

        WaterQualityReport w5 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.UNSAFE, 3.3, 1.6);
        assertEquals(false,w5.isCorrectlyClassified());

        WaterQualityReport w6 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.UNSAFE, 3.3, 4.7);
        assertEquals(false, w6.isCorrectlyClassified());
    }

    @Test
    public void testUnsafeCasesCorrect() {
        WaterQualityReport w1 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.UNSAFE, 3.7, 5.0);
        assertTrue(w1.isCorrectlyClassified());
    }

    @Test
    public void testUnsafeCasesIncorrect() {
        WaterQualityReport w1 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.SAFE, 3.7, 5.0);
        assertEquals(false, w1.isCorrectlyClassified());

        WaterQualityReport w2 = new WaterQualityReport("r1", 111, 2.0, 3.0,
                WaterQualityReport.OverallCondition.TREATABLE, 3.7, 5.0);
        assertEquals(false, w2.isCorrectlyClassified());
    }
}
