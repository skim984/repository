package tests;

import model.Model;
import model.Report;
import model.PurityReport;
import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jacob on 11/11/2016.
 * Test the addPurityReport method
 */
public class junit_jacob_iacino {

    private Model currModel;

    @Before
    /**
     * This method is run prior to each test to setup conditions by attaining
     * the model to perform these tests on.
     */
    public void setup() {
        currModel = Model.getInstance();
    }

    /**
     * test if the report actually gets added to the backing list
     */
    @Test
    public void testAdditionToList() {
        PurityReport report = new PurityReport("testUser");
        report.setID(1);
        currModel.addPurityReport(report);
        Assert.assertNotNull(currModel.findReport("testUser", 1));
    }

    /**
     * test the incrementation of report numbers to ensure duplicate numbers do not happen
     */
    @Test
    public void testReportNumberIncrementation() {
        PurityReport report = new PurityReport("testUser");
        report.setID(1);
        currModel.addPurityReport(report);
        Assert.assertNotNull(currModel.findReport("testUser", 2));
    }
}
