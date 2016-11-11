package tests;

import model.Model;
import model.SourceCondition;
import model.SourceReport;
import model.SourceType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by toki on 11/11/16.
 */
public class junit_mcgarry {

    SourceReport improper;
    SourceReport proper;

    @Before
    public void SetUp() throws Exception{

        improper = new SourceReport("Kazakhstan");
        improper.setSc(SourceCondition.PT);
        improper.setSt(SourceType.ST);

        proper = new SourceReport("defaultUser");
        proper.setSc(SourceCondition.TC);
        proper.setSt(SourceType.LK);

    }

    @Test
    public void test01_improperUser() {
        boolean test = false;
        try {
            Model.getInstance().addSourceReport(improper);
        } catch (Exception e) {
            test = true;
        }

        //should be a true value; we shouldnt be able to pass reports with improper users
        assertTrue(test);
    }

    @Test
    public void test02_properUser() {
        boolean test = false;
        try {
            Model.getInstance().addSourceReport(proper);
        } catch (Exception e) {
            test = true;
        }

        //should be false; a valid user should be able to submit a report
        assertFalse(test);
    }

    @Test
    public void test03_badLocationNeg() {
        boolean test = false;
        improper.setLocation(-86, -181);
        try {
            Model.getInstance().addSourceReport(improper);
        } catch (Exception e) {
            test = true;
        }

        //lat < -85, long < -180; should throw an error
        assertTrue(test);
    }

    @Test
    public void test04_badLocationPos() {
        boolean test = false;
        improper.setLocation(86, 181);
        try {
            Model.getInstance().addSourceReport(improper);
        } catch (Exception e) {
            test = true;
        }

        //lat > 85, long > 180; should throw an error
        assertTrue(test);
    }

    @Test
    public void test05_goodLocation() {
        boolean test = false;
        proper.setLocation(25.67,25.77);
        try {
            Model.getInstance().addSourceReport(proper);
        } catch (Exception e) {
            test = true;
        }

        //lat < 85, long < 180; should throw an error
        assertFalse(test);
    }


}
