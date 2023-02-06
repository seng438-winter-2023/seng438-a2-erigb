package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataUtilitiesTest {
	Values2D values;
	KeyedValues keyedValuesList;
	
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
	             one(values).getValue(0, 10);;
	             will(returnValue(0));
	             one(values).getValue(1, 10);;
	             will(returnValue(0));
	         }
    	 });
    	 Mockery keyedMock = new Mockery();
    	 keyedValuesList = keyedMock.mock(KeyedValues.class);
    	 keyedMock.checking(new Expectations(){
    		 {
    			 atLeast(1).of(keyedValuesList).getItemCount();
	             will(returnValue(3));

	             atLeast(1).of(keyedValuesList).getValue(0);
	             will(returnValue(5));
	             atLeast(1).of(keyedValuesList).getValue(1);
	             will(returnValue(9));
	             atLeast(1).of(keyedValuesList).getValue(2);
	             will(returnValue(2));
	             one(keyedValuesList).getKey(0);
	             will(returnValue(1));
	             one(keyedValuesList).getKey(1);
	             will(returnValue(2));
	             one(keyedValuesList).getKey(2);
	             will(returnValue(15));
	            
	         }
    	 });
    }
	 @Test
	 public void calculateColumnTotalForTwoValues() {
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertEquals("The total for the column containing 2.5 and 7.5 is 10.0",10.0, result, .000000001d);

	 }

	 @Test
	 public void calculateColumnTotalReturnsZeroForInvalidValue() {
		 double result = DataUtilities.calculateColumnTotal(values, 10);
		 assertEquals(0, result, .000000001d);
	 }
	 
	 @Test 
	 public void getCumulativePercentagesForValidDataObject() {
		 KeyedValues resultValues = DataUtilities.getCumulativePercentages(keyedValuesList);
		 assertEquals("The key in index \"0\" is 1", 1, resultValues.getKey(0));
		 assertEquals("The key in index \"1\" is 2", 2 , resultValues.getKey(1));
		 assertEquals("The key in index \"2\" is 15", 15, resultValues.getKey(2));
		 assertEquals("The percentage for key \"1\" is 5/16 = 0.3125", 0.3125, resultValues.getValue(0).doubleValue(), 000000001d);
		 assertEquals("The percentage for key \"1\" is (5+9)/16 = 0.875 ", 0.875 , resultValues.getValue(1).doubleValue(), 000000001d);
		 assertEquals("The percentage for key \"1\" is (5+9+2)/16 = 1.0", 1.0, resultValues.getValue(2).doubleValue(), 000000001d);
	 }

}
