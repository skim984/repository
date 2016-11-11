package tests;

import model.Model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Elie Diaz on 11/9/2016.
 */
public class junit_elie_diazz {
    /**
     * The test things
     */
    private Model current;

    /**
     * This method is run before each test
     * We know in the report there exists a report where id: 962704 reportername: jiacino3
     * We will test it with 4 ways
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        current = Model.getInstance();
    }


    /**
     * test findreport for null id and user value
     */
    @Test
    public void testNullReport() {
        Assert.assertNull("Report should be null!",current.findReport(null, 0));
    }

    /**
     * test findReport with id that doesn't exist
     */
    @Test
    public void testWrongIdNumber() {
        Assert.assertNull("Report should be null", current.findReport("jiacino3",1234));
    }

    /**
     * test findAccount with id that doesn't exist
     */
    @Test
    public void testWrongReportName() {
        Assert.assertNull("Report should be null", current.findReport("user",962704));
    }

    /**
     * test findAccount with id and user that do exist
     */
    @Test
    public void testCorrectIdUserReport() {
        Assert.assertEquals("Existing report not found!", "33.85, -88.39",
                current.findReport("jiacino3", 962704).getLocation());
    }

}
