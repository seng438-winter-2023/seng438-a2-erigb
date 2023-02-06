package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    private Range nullRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-1, 1);
    	nullRange = null;
    }


    @Test
    public void lengthShouldBeTwo() {
        assertEquals("The length of Range from -1 to 1 should be 2",
        		2, exampleRange.getLength(), .000000001d);
    }
    
    @Test
    public void containsShouldBeTrue() {
    	assertTrue("The Range does contain value 0.5. Contains should return true",
    	exampleRange.contains(0.5));
    }
    
    @Test
    public void containsShouldBeFalse() {
    	assertFalse("The Range does contain value 0.5. Contains should return true",
    	exampleRange.contains(-7));
    }
    
    
    @Test
    public void intersectsRangeOverlapsSpecifiedRange() {
    	assertTrue("The Range intersects the current range (-1, 1). Contains should return true",
    	exampleRange.intersects(-1, 1));
    }
    
    @Test
    public void intersectsRangeDoesNotOverlapsSpecifiedRange() {
    	assertFalse("The Range does not intersects the current range (-1, 1). Contains should return false",
    	exampleRange.intersects(2, 3));
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
