package tests;

import model.Model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sunpil Kim on 2016. 11. 9..
 */
public class junit_sunpil_kim {

    /**
     * The test fixture
     */
    private Model current;

    /**
     * This method is run before each test
     * We know in the Account there exists an account that id: user password: pass
     * We will test it with 4 ways.
     * @throws Exception when it doesn't set up
     */
    @Before
    public void setUp() throws Exception {
        current = Model.getInstance();
    }


    /**
     * test findAccount for null id value
     */
    @Test
    public void testNullIdAccount() {
        Assert.assertNull("Report should be null!",current.findAccount(null,null));
    }

    /**
     * test findAccount with id that doesn't exist
     */
    @Test
    public void testWrongIdAccount() {
        Assert.assertNull("Report should be null", current.findAccount("abcdefg","pass"));
    }

    /**
     * test findAccount with id that exists but wrong password
     */
    @Test
    public void testWrongPasswordAccount() {
        Assert.assertNull("Report should be null", current.findAccount("user","passs"));
    }

    /**
     * test findAccount with correct id and password
     */
    @Test
    public void testCorrectIdPasswordAccount() {
        Assert.assertEquals("wrong id!", "user", current.findAccount("user","pass").getId());
    }


}
