package org.jfree.data.test;

import static org.junit.Assert.*;

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
    }

	 @Test
	 public void calculateColumnTotalForTwoValues() {
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
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertEquals("The total for the column containing 2.5 and 7.5 is 10.0",10.0, result, .000000001d);

	 }
	 
	 @Test
	 public void calculateColumnTotalReturnsZeroForInvalidValue() {
		 Mockery mockObject = new Mockery();
    	 values = mockObject.mock(Values2D.class);
    	 mockObject.checking(new Expectations() {
    		 {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(0));
	             one(values).getValue(1, 0);
	             will(returnValue(0));
	         }
    	 });
		 double result = DataUtilities.calculateColumnTotal(values, 0);
		 assertEquals(0, result, .000000001d);
	 }
	 
	 @Test
	 public void calculateColumnTotalReturnsZeroForEmptyValue2D() {
		 Mockery mockObject = new Mockery();
    	 values = mockObject.mock(Values2D.class);
    	 mockObject.checking(new Expectations() {
    		 {
	             one(values).getRowCount();
	             will(returnValue(0));
	         }
    	 });
		 double result = DataUtilities.calculateColumnTotal(values, 0);
		 assertEquals(0, result, .000000001d);
	 }
	 
	 @Test
	 public void calculateRowTotalForTwoValues() {
		 Mockery mockObject = new Mockery();
    	 values = mockObject.mock(Values2D.class);
    	 mockObject.checking(new Expectations() {
    		 {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(0, 1);
	             will(returnValue(2.5));
	         }
    	 });
	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals("The total for the row containing 2.5 and 7.5 is 10.0",7.5, result, .000000001d);
	 }
	 
	 @Test
	 public void calculateRowTotalReturnsZeroForInvalidValue() {
		 Mockery mockObject = new Mockery();
    	 values = mockObject.mock(Values2D.class);
    	 mockObject.checking(new Expectations() {
    		 {
	             one(values).getColumnCount();
	             will(returnValue(1));
	             one(values).getValue(0, 0);
	             will(returnValue(10));
	             one(values).getValue(0, 10);
	             will(throwException(new IndexOutOfBoundsException("Index 10 is out of bound for Column Count of 1")));
	         }
    	 });
	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals("The total for the row should be 0 with invalid values", 0, result, .000000001d);
	 }
	 
	 @Test
	 public void calculateRowTotalReturnsZeroForEmptyValue2D() {
		 Mockery mockObject = new Mockery();
    	 values = mockObject.mock(Values2D.class);
    	 mockObject.checking(new Expectations() {
    		 {
	             one(values).getColumnCount();
	             will(returnValue(0));
	         }
    	 });
	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals("The total for the row should be 0 with invalid values", 0, result, .000000001d);
	 }
}
