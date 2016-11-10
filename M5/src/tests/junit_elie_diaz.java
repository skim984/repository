package tests;

import model.Model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Phillip on 2016. 11. 9..
 */
public class junit_sunpil_kim {
    /**
     * The test fixture
     *
    /**
     * This method is run before each test
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        Model.getInstance();

    }

    /**
     * Test method for {@link edu.gatech.oad.code.Stack#getCapacity()}.
     */
    @Test
    public void testGetCapacity() {
        Assert.assertEquals("Capacity wrong", 100, theStack.getCapacity());
    }

    /**
     * Test method for {@link edu.gatech.oad.code.Stack#entryCount()}.
     */
    @Test
    public void testEntryCount() {
        Assert.assertEquals("Initial Count Wrong", 0, theStack.entryCount());
        try {
            theStack.push("A");
            theStack.push("B");
            theStack.push("C");
        } catch (StackFullException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail("Should not have thrown exception here");
        }
        Assert.assertEquals("Count wrong after push", 3, theStack.entryCount());
        try {
            theStack.pop();
            theStack.pop();
        } catch (StackEmptyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail("Should not have thrown exception here");
        }
        Assert.assertEquals("Count wrong after pop", 1, theStack.entryCount());
    }

    /**
     * Test method for {@link edu.gatech.oad.code.Stack#top()}.
     */
    @Test
    public void testTop() {
        try {
            theStack.push("A");
            Assert.assertEquals("Top wrong after 1 push", "A", theStack.top());
            theStack.push("B");
            theStack.push("C");
            Assert.assertEquals("Top wrong after 3 push", "C", theStack.top());
            theStack.pop();
            theStack.pop();
            theStack.pop();
        } catch (StackFullException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail("Should not have failed in top test");
        } catch (StackEmptyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail("Should not have failed in top test");
        }

        try {
            theStack.top();
            Assert.fail("Top did not throw an exception on empty");
        } catch (StackEmptyException e) {
            //this is empty because we expect this exception and want a pass
        }


    }

    /**
     * Test method for {@link edu.gatech.oad.code.Stack#pop()}.
     */
    @Test
    public void testPop() {
        try {
            theStack.pop();
            Assert.fail("Pop should have thrown exception");
        } catch (StackEmptyException e) {
            //do nothing as this is expected
        }

        try {
            theStack.push("A");
            Assert.assertEquals("Pop wrong after 1 push",  "A", theStack.pop());
        } catch (StackFullException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail("Pop should not have thrown exception here");
        } catch (StackEmptyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail("Pop should not have thrown exception here");
        }


    }

    /**
     * Test method for {@link edu.gatech.oad.code.Stack#push(java.lang.Object)}.
     */
    @Test
    public void testPush() {
        try {
            theStack.push("A");
            Assert.assertEquals("Count wrong after push", 1, theStack.entryCount());
            Assert.assertEquals("Top element wrong after push", "A", theStack.top());
            theStack.push("B");
            theStack.push("C");
            Assert.assertEquals("Count wrong after 3 push", 3, theStack.entryCount());
            Assert.assertEquals("Top element wrong after 3 push", "C", theStack.top());
            for (int i = 3 ; i < theStack.getCapacity(); i++) {
                theStack.push("AAA" + i);
            }
            Assert.assertEquals("Count wrong after 100 pushes", 100, theStack.entryCount());
            Assert.assertEquals("Top element wrong after 100 pushes", "AAA99", theStack.top());
        } catch (StackFullException e) {
            e.printStackTrace();
            Assert.fail("Push should not throw exception here");
        } catch (StackEmptyException e) {
            Assert.fail("Top should not have thrown exception here");
            e.printStackTrace();
        }

        try {
            theStack.push("FAIL");
            Assert.fail("Failed to throw stack full exception");
        } catch (StackFullException e) {
            //Nothing here, we expect the exception
        }


    }

    /**
     * Test method for {@link edu.gatech.oad.code.Stack#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        Assert.assertTrue("Initial empty stack wrong", theStack.isEmpty());
        try {
            theStack.push("A");
            Assert.assertTrue("isempty after a push wrong", !theStack.isEmpty());
            theStack.pop();
            Assert.assertTrue("is empty after a pop of last element wrong", theStack.isEmpty());
        } catch (StackFullException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail("IsEmpty should not have thrown exception");
        } catch (StackEmptyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail("IsEmpty should not have thrown exception");
        }

    }
}
