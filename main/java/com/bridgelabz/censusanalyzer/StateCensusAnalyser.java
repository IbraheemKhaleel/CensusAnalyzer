package com.bridgelabz.censusanalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.Pattern;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	public int loadCensus(String csvFilePath) throws CensusAnalyserException {
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
			throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
		return recordCounter;	
	}
	public boolean patternMatching(String givenPattern, String givenMatch) throws CensusAnalyserException
    {
    	Pattern pattern = Pattern.compile(givenPattern);
    	try {
        	if (pattern.matcher(givenMatch).matches())
        		return true ;
        }catch(Exception e) {
        	throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.WRONG_TYPE );
        }
        		return false;
    }
}
