package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class FindReportByUser {

    public static final int TIMEOUT = 200;
    
    private ArrayList<Double> goodList;
    private ArrayList<Double> emptyList;
    private ArrayList<Double> checkList;
    private ArrayList<Report> testList;
    
    @Before
    public void setUp() throws Exception {
        goodList = new ArrayList<Double>();
        goodList.add(-78.9);
        goodList.add(-111.11);
        goodList.add(69.0);
        goodList.add(69.0);
        goodList.add(0.0);
        goodList.add(0.0);
        emptyList = new ArrayList<Double>();
        checkList = new ArrayList<Double>();
    }

    @Test
    public void FindReportByUserNormTest() {
        testList = (ArrayList<Report>)Model.getInstance().findReportByUser("briank");
        for (Report report:testList) {
            //System.out.println(report.getLocation());
            checkList.add(report.getLocationLatitude());
            checkList.add(report.getLocationLongitude());
        }
        assertEquals(goodList, checkList);
    }
    
    @Test
    public void FindReportByUserNonexistTest() {
        testList = (ArrayList<Report>)Model.getInstance().findReportByUser("DIO");
        for (Report report:testList) {
            //System.out.println(report.getLocation());
            checkList.add(report.getLocationLatitude());
            checkList.add(report.getLocationLongitude());
        }
        assertEquals(emptyList, checkList);
    }
    @Test
    public void FindNULLTest() {
        testList = (ArrayList<Report>)Model.getInstance().findReportByUser(null);
        assertEquals(null, testList);
    }
}
