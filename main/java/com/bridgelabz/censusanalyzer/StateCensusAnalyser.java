package com.bridgelabz.censusanalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	public int loadCensus(String csvFilePath) {
		int recordCounter = 0;
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
			CsvToBeanBuilder<IndianStateCensusData> csvToBeanBuilder = new CsvToBeanBuilder<IndianStateCensusData>(reader);
			csvToBeanBuilder.withType( IndianStateCensusData.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndianStateCensusData> csvToBean = csvToBeanBuilder.build();
			Iterator<IndianStateCensusData> stateCodeIterator = csvToBean.iterator();
			while(stateCodeIterator.hasNext()) {
				recordCounter++;
				stateCodeIterator.next();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return recordCounter;	
	}
}
