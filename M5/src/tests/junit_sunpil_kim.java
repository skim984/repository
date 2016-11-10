package tests;

import model.Account;
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
    Model current;
    Account account;

    /**
     * This method is run before each test
     * We know in the Account there exists an account that id: user password: pass
     * We will test it with 5 ways.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        current = Model.getInstance();
        account = current.findAccount("user","pass");
    }


    /**
     * test findAccount for null id value
     */
    @Test
    public void testNullIdAccount() {
        Assert.assertNull(current.findAccount(null,null));
    }

    /**
     * test findAccount with id that doesn't exist
     */
    @Test
    public void testWrongIdAccount() {
        Account temp = current.findAccount("abcdefg","pass");
        Assert.assertNotEquals(account, temp);
    }

    /**
     * test findAccount with id that doesn't exist
     */
    @Test
    public void testWrongPasswordAccount() {
        Account temp = current.findAccount("user","passs");
        Assert.assertNotEquals(account, temp);
    }

    /**
     * test findAccount with id that doesn't exist
     */
    @Test
    public void testCorrectIdPasswordAccount() {
        Account temp = current.findAccount("user","pass");
        Assert.assertEquals(account, temp);
    }


}
