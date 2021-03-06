package com.bridgelabz.censusanalyzer;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bridgelabz.csvreader.CensusAnalyserException;
import com.google.gson.Gson;

public class CensusAnalyzerTest {
	private static final String INDIAN_CENSUS_CSV_FILE_PATH = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\test\\resources\\IndiaStateCensusData.csv" ;
	private static final String WRONG_INDIAN_CENSUS_CSV_FILE_PATH = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\main\\resources\\IndiaStateCensusData.csv" ;
	private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\test\\resources\\WrongDelimiter.csv";
	private static final String INDIAN_CENSUS_CSV_MISSING_HEADER ="C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\test\\resources\\MissingHeader.csv" ;

	private static final String INDIAN_STATE_CODE_CSV_FILE_PATH = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\test\\resources\\IndiaStateCode.csv";
	private static final String WRONG_INDIAN_CENSUS_FILE_TYPE = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\test\\resources\\IndiaStateCensusData.ppt" ;
	private static final String WRONG_INDIAN_STATE_CODE_CSV_FILE_PATH =  "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\main\\resources\\IndiaStateCode.csv";
	private static final String WRONG__INDIAN_CODE_FILE_TYPE = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\test\\resources\\IndiaStateCode.ppt";
	private static final String INDIAN_STATE_CODE_CSV_WRONG_DELIMITER = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\test\\resources\\WrongStateCodeDelimiter.csv";

	@Test
	public void givenIndianCensusCSC_WhenChecked_ShouldReturnCorrectRecords() {
		StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = censusAnalyser.loadCensus(INDIAN_CENSUS_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
		}
		Assert.assertEquals(29, count);
	}
	@Test
	public void givenIndianCensusCSV_WhenWrongFile_ShouldThrowException()  {
		try {
		StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
		ExpectedException exceptionRule = ExpectedException.none();
		exceptionRule.expect(CensusAnalyserException.class);
		censusAnalyser.loadCensus(WRONG_INDIAN_CENSUS_CSV_FILE_PATH );
		} catch(CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}
	@Test
	public void givenWrongFileType_WhenChecked_ShouldThrowException()  {
		try {
		StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
		ExpectedException exceptionRule = ExpectedException.none();
		exceptionRule.expect(CensusAnalyserException.class);
		censusAnalyser.loadCensus(WRONG_INDIAN_CENSUS_FILE_TYPE);
		} catch(CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_TYPE, e.type);
		}
	}
	@Test
	public void givenWrongDelimiter_InIndiaCensusData_ShouldReturnCustomExceptionType() {
	        try {
	            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
	            censusAnalyser.loadCensus(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
	        } catch (CensusAnalyserException e) {
	            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
	        }
	 }
	 @Test
	 public void givenMissingHeader_InIndiaCensusData_ShouldReturnCustomExceptionType() {
	        try {
	            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
	            censusAnalyser.loadCensus(INDIAN_CENSUS_CSV_MISSING_HEADER);
	        } catch (CensusAnalyserException e) {
	            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
	        }
	 }

	    @Test
	    public void givenStateCensusCSVFile_ShouldMatchNumberOfRecordsInFile() {
	        try {
	            StateCensusAnalyser codeAnalyser = new StateCensusAnalyser();
	            int count = codeAnalyser.loadStateCode(INDIAN_STATE_CODE_CSV_FILE_PATH);
	            Assert.assertEquals(37 , count);
	        }catch (CensusAnalyserException e) {
	        }
	    }

	    @Test
	    public void givenStateCensusCSVFile_WhenPathIsIncorrect_ShouldThrowException() {
	        try {
	            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
	            ExpectedException exceptionRule = ExpectedException.none();
	            exceptionRule.expect(CensusAnalyserException.class);
	            censusAnalyser.loadStateCode(WRONG_INDIAN_STATE_CODE_CSV_FILE_PATH);
	        } catch (CensusAnalyserException e) {
	            System.out.println(e.getMessage());
	            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM , e.type);
	        }
	    }

	    @Test
		public void givenWrongIndianCodeFileType_WhenChecked_ShouldThrowException()  {
			try {
			StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadStateCode(WRONG__INDIAN_CODE_FILE_TYPE);
			} catch(CensusAnalyserException e) {
				Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_TYPE, e.type);
			}
		}
		@Test
		public void givenWrongDelimiter_InIndianCode_ShouldReturnCustomExceptionType() {
		        try {
		            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
		            censusAnalyser.loadStateCode(INDIAN_STATE_CODE_CSV_WRONG_DELIMITER);
		        } catch (CensusAnalyserException e) {
		            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		        }
		 }
		 @Test
		 public void givenMissingHeader_InIndianCodeData_ShouldReturnCustomExceptionType() {
		        try {
		        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
					ExpectedException exceptionRule = ExpectedException.none();
					exceptionRule.expect(CensusAnalyserException.class);
					censusAnalyser.loadStateCode(INDIAN_CENSUS_CSV_MISSING_HEADER);
					} catch(CensusAnalyserException e) {
						Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES , e.type);
					}
		 }

		 @Test
		 public void givenDetails_WhenSorted_ShouldReturnFirstState()
		 {
			 try {
		        		StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
					String sortedCensusData = censusAnalyser.givenSortedDetails(INDIAN_STATE_CODE_CSV_FILE_PATH);
					IndianStateCode[] censusCSV = new Gson().fromJson( sortedCensusData, IndianStateCode[].class);
		            		Assert.assertEquals("Andhra Pradesh",censusCSV[0].stateName);
		        } catch (CensusAnalyserException e) {
		          	e.printStackTrace();
		        } catch (IOException e) 
			{
				e.printStackTrace();
			}
		 }
}
