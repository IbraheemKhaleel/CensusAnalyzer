package com.bridgelabz.censusanalyzer;

import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyzerTest {
	private static final String INDIAN_CENSUS_CSV_FILE_PATH = "C:\\Users\\Ibrahim Khaleel\\eclipse-workspace\\CensusAnalyzer\\src\\test\\resources\\IndiaStateCensusData.csv" ;
	@Test
	public void given() {
		StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
		int count =	censusAnalyser.loadCensus(INDIAN_CENSUS_CSV_FILE_PATH);
		Assert.assertEquals(29, count);
	}
}
