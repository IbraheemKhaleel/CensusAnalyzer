package com.bridgelabz.censusanalyzer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyzerTest {
	private static final String INDIAN_CENSUS_CSV_FILE_PATH = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\test\\resources\\IndiaStateCensusData.csv" ;
	private static final String WRONG_CSV_FILE_PATH = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\main\\resources\\IndiaStateCensusData.csv" ;
	private static final String WRONG_TYPE = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\test\\resources\\IndiaStateCensusData.pdf" ;
	private static final String EXTENSION_FORMAT = "^[a-z:\\ A-Z987]{1,}[.][c][s][v]$";
	private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\test\\resources\\WrongDelimiter.csv";
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
	public void givenIndianCensusCSV_WhenWrongFlie_ShouldThrowException()  {
		try {
		StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
		ExpectedException exceptionRule = ExpectedException.none();
		exceptionRule.expect(CensusAnalyserException.class);
		censusAnalyser.loadCensus(WRONG_CSV_FILE_PATH);
		} catch(CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
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
	
		public void givenWrongCSVFileType_WhenChecked_ShouldThrowException() {
			StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
			try {
				censusAnalyser.patternMatching(EXTENSION_FORMAT, "WRONG_TYPE");
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_TYPE, e.type);
		}	
	}	
}
