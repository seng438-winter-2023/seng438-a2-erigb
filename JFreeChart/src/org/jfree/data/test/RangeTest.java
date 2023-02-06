package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange;

    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-1, 1);
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
    public void shiftRangeWithoutZeroCrossingPositveDelta() {
    	Range afterShift = Range.shift(exampleRange, 0.5);
    	
    	assertEquals("The lower bound should be 0.5 + -1 = -0.5",
    	        -0.5, afterShift.getLowerBound(), .000000001d);
    	assertEquals("The upper bound should be 0.5 + 1 = 1.5",
    	        1.5, afterShift.getUpperBound(), .000000001d);
    	
    }
    
    @Test
    public void shiftRangeWithoutZeroCrossingNegativeDelta() {
    	Range afterShift = Range.shift(exampleRange, -0.5);
    	
    	assertEquals("The lower bound should be -0.5 + -1 = -1.5",
    	        -1.5, afterShift.getLowerBound(), .000000001d);
    	assertEquals("The upper bound should be -0.5 + 1 = 0.5",
    	        0.5, afterShift.getUpperBound(), .000000001d);
    	
    }

    @Test
    public void shiftShouldNotAllowZeroCrossing() {
    	Range afterShift = Range.shift(exampleRange, 5.0);
    	assertEquals("The upper bound should be 5.0 + 1 = 6.0",
    	        6.0, afterShift.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be 0 since no zero crossing is allowed and -1 + 5 = 4 > 0",
    	        0.0, afterShift.getLowerBound(), .000000001d);
    	
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
