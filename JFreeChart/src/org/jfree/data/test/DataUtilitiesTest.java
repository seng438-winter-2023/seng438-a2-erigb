package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataUtilitiesTest {
	Values2D values;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
    @Before
    public void setUp() throws Exception { 
    	 Mockery mockObject = new Mockery();
    	 values = mockObject.mock(Values2D.class);
    	 mockObject.checking(new Expectations() {
    		 {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(1, 0);
	             will(returnValue(2.5));
	         }
    	 });
    }

	 @Test
	 public void calculateColumnTotalForTwoValues() {
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertEquals(result, 10.0, .000000001d);

	 }

//	 @Test
//	 public void invalidParameterExceptionIsThrown() {
//		 exception.expect(InvalidParameterException.class);
//		 DataUtilities.calculateColumnTotal(values, 10);
//	 }

}
