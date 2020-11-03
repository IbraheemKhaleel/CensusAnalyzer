package com.bridgelabz.censusanalyzer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyzerTest {
	private static final String INDIAN_CENSUS_CSV_FILE_PATH = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\test\\resources\\IndiaStateCensusData.csv" ;
	private static final String WRONG_CSV_FILE_PATH = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\main\\resources\\IndiaStateCensusData.csv" ;
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
	public void givenIndianCensusCSC_WhenWrongFlie_ShouldThrowException()  {
		try {
		StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
		ExpectedException exceptionRule = ExpectedException.none();
		exceptionRule.expect(CensusAnalyserException.class);
		censusAnalyser.loadCensus(WRONG_CSV_FILE_PATH);
		} catch(CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}
	
}
