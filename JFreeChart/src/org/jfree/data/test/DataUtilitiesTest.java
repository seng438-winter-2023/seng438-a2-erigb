package org.jfree.data.test;

import static org.junit.Assert.*;


import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataUtilitiesTest {
	Values2D values;
	KeyedValues keyedValuesList;
	KeyedValues negativeValuesList;

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
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(0));
	             one(values).getValue(0, 10);
	             will(returnValue(0));
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

	  @Test 
	 public void getCumulativePercentagesForAllPositiveDataObject() {
		 Mockery keyedMock = new Mockery();
    	 keyedValuesList = keyedMock.mock(KeyedValues.class);
    	 keyedMock.checking(new Expectations(){
    		 {
    			 atLeast(1).of(keyedValuesList).getItemCount();
	             will(returnValue(4));

	             atLeast(1).of(keyedValuesList).getValue(0);
	             will(returnValue(5));
	             atLeast(1).of(keyedValuesList).getValue(1);
	             will(returnValue(9));
	             atLeast(1).of(keyedValuesList).getValue(2);
	             will(returnValue(2));
	             atLeast(1).of(keyedValuesList).getValue(3);
	             will(returnValue(4));
	             one(keyedValuesList).getKey(0);
	             will(returnValue(1));
	             one(keyedValuesList).getKey(1);
	             will(returnValue(2));
	             one(keyedValuesList).getKey(2);
	             will(returnValue(15));
	             one(keyedValuesList).getKey(3);
	             will(returnValue(18));
	            
	         }
    	 });
    	 
		 KeyedValues resultValues = DataUtilities.getCumulativePercentages(keyedValuesList);
		 assertEquals("The key in index \"0\" is 1", 1, resultValues.getKey(0));
		 assertEquals("The key in index \"1\" is 2", 2 , resultValues.getKey(1));
		 assertEquals("The key in index \"2\" is 15", 15, resultValues.getKey(2));
		 assertEquals("The key in index \"3\" is 18", 18, resultValues.getKey(3));
		 assertEquals("The percentage for index \"0\" is 5/20 = 0.25", 0.25, resultValues.getValue(0).doubleValue(), .000000001d);
		 assertEquals("The percentage for index \"1\" is (5+9)/20 = 0.7 ", 0.7, resultValues.getValue(1).doubleValue(), .000000001d);
		 assertEquals("The percentage for index \"2\" is (5+9+2)/20 = 0.8", 0.8, resultValues.getValue(2).doubleValue(), .000000001d);
		 assertEquals("The percentage for index \"3\" is (5+9+2+4)/20 = 1.0", 1.0, resultValues.getValue(2).doubleValue(), .000000001d);
	 }

	 @Test 
	 public void getCumulativePercentagesForNegativeDataObject() {

    	 Mockery negativeKeyedMock = new Mockery();
    	 negativeValuesList = negativeKeyedMock.mock(KeyedValues.class);
    	 negativeKeyedMock.checking(new Expectations(){
    		 {
    			 atLeast(1).of(negativeValuesList).getItemCount();
	             will(returnValue(3));

	             atLeast(1).of(negativeValuesList).getValue(0);
	             will(returnValue(-5));
	             atLeast(1).of(negativeValuesList).getValue(1);
	             will(returnValue(-9));
	             atLeast(1).of(negativeValuesList).getValue(2);
	             will(returnValue(-2));
	             one(negativeValuesList).getKey(0);
	             will(returnValue(1));
	             one(negativeValuesList).getKey(1);
	             will(returnValue(2));
	             one(negativeValuesList).getKey(2);
	             will(returnValue(15));
	            
	         }
    	 });
		 
		 KeyedValues negativeResultValues = DataUtilities.getCumulativePercentages(negativeValuesList);
		 assertEquals("The key in index \"0\" is 1", 1, negativeResultValues.getKey(0));
		 assertEquals("The key in index \"1\" is 2", 2 , negativeResultValues.getKey(1));
		 assertEquals("The key in index \"2\" is 15", 15, negativeResultValues.getKey(2));
		 assertEquals("The percentage for index \"0\" is |-5/16| = 0.3125", 0.3125, negativeResultValues.getValue(0).doubleValue(), .000000001d);
		 assertEquals("The percentage for index \"1\" is |-5+-9/16| = 0.875 ", 0.875 , negativeResultValues.getValue(1).doubleValue(), .000000001d);
		 assertEquals("The percentage for index \"2\" is |-5+-9+-2/16| = 1.0", 1.0, negativeResultValues.getValue(2).doubleValue(), .000000001d);
	 }
}
