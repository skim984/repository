package tests;

import model.Model;
import model.Report;
import model.PurityReport;
import model.SourceReport;
import model.PurityCondition;
import model.SourceCondition;
import model.SourceType;
import static org.junit.Assert.assertEquals;

import org.junit.After;
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
     * test if the purity report actually gets added to the backing list
     */
    @Test
    public void testAdditionToListPurityReport() {
        PurityReport report = new PurityReport("testUser");
        report.setID(710);
        currModel.addPurityReport(report);
        Assert.assertNotNull(currModel.findReport("testUser", 710));
        currModel.removeReport(report);
    }

    /**
     * test if the source report actually gets added to the backing list
     */
    @Test
    public void testAdditionToListSourceReport() {
        SourceReport report = new SourceReport("testUser");
        report.setID(725);
        currModel.addSourceReport(report);
        Assert.assertNotNull(currModel.findReport("testUser", 725));
        currModel.removeReport(report);
    }

}
