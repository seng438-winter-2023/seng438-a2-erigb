package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

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
    
    /***
	 * This test will test intersects by using two ranges that are the same (fully overlapping).
	 * Expected output is True.
	 */
    @Test
    public void intersectsRangeFullyOverlapsSpecifiedRange() {
    	assertTrue("The Range (-1, 1) intersects the current range (-1, 1). Contains should return true",
    	exampleRange.intersects(-1, 1));
    }
    
    /***
	 * This test will test intersects by using a specified range that slightly overlaps with the example range.
	 * Expected output is True.
	 */
    @Test
    public void intersectsRangePartiallyOverlapingSpecifiedRange() {
    	assertTrue("The Range (-1, 1) intersects the current range (0, 2). Contains should return true",
    	exampleRange.intersects(0, 2));
    }
    
    /***
	 * This test will test intersects by using a specified range that fully encompasses the example range.
	 * Expected output is True.
	 */
    @Test
    public void intersectsRangeFullyCoveredBySpecifiedRange() {
    	assertTrue("The Range (-1, 1) intersects the current range (0, 1). Contains should return true",
    	exampleRange.intersects(-5, 5));
    }
    
    /***
	 * This test will test intersects by using a specified range is fully covered by the example range.
	 * Expected output is True.
	 */
    @Test
    public void intersectsSpecifiedRangeFullyCoveredByRange() {
    	assertTrue("The Range (-1, 1) intersects the current range (0, 1). Contains should return true",
    	exampleRange.intersects(0, 1));
    }
    
    /***
	 * This test will test intersects by using a specified range that does not overlap with the example range.
	 * Expected output is False.
	 */
    @Test
    public void intersectsRangeDoesNotOverlapSpecifiedRange() {
    	assertFalse("The Range (-1, 1) does not intersects the current range (-1, 1). Contains should return false",
    	exampleRange.intersects(2, 3));
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
    
    @Test
    public void expandToIncludeWithinRange() {
    	Range actual = Range.expandToInclude(exampleRange, 0.8);
    	assertEquals("The upper bound should be [-1, 1].",
    			1, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be [-1, 1].",
    			-1, actual.getLowerBound(), .000000001d);
    }
    
    @Test
    public void expandToIncludeOutsideUpperRange() {
    	Range actual = Range.expandToInclude(exampleRange, 1.5);
    	assertEquals("The upper bound should be [-1, 1.01].",
    			1.01, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be [-1, 1.01].",
    			-1, actual.getLowerBound(), .000000001d);
    }
    
    @Test
    public void expandToIncludeOutsideLowerRange() {
    	Range actual = Range.expandToInclude(exampleRange, -1.5);
    	assertEquals("The upper bound should be [-1.01, 1].",
    			1, actual.getUpperBound(), .000000001d);
    	assertEquals("The lower bound should be [-1.01, 1].",
    			-1.01, actual.getLowerBound(), .000000001d);
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
