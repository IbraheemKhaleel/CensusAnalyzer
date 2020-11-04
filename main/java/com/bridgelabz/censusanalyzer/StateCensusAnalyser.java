package com.bridgelabz.censusanalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	public int loadCensus(String csvFilePath) throws CensusAnalyserException {
		int recordCounter = 0;
		try {
			FileTypeValidator fileTypeValidator = new FileTypeValidator();
			boolean result = fileTypeValidator.validateFileType(csvFilePath);
			if(!result) {
				throw new CensusAnalyserException("Enter correct file type", CensusAnalyserException.ExceptionType.WRONG_TYPE);
			}
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
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
			throw new CensusAnalyserException("Please enter correct path",
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}catch (RuntimeException e) {
            throw new CensusAnalyserException("Please select correct csv file  ",
                    CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
		return recordCounter;	
	}
	
public int loadStateCode(String indianStateCodeCsvFilePath) throws CensusAnalyserException {
		
		try (Reader reader = Files.newBufferedReader(Paths.get(indianStateCodeCsvFilePath))){
			CsvToBeanBuilder<IndianStateCode> csvToBeanBuilder = new CsvToBeanBuilder<IndianStateCode>(reader);
			csvToBeanBuilder.withType( IndianStateCode.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndianStateCode> csvToBean = csvToBeanBuilder.build();
			Iterator<IndianStateCode> censusCSVIterator = csvToBean.iterator();
			Iterable<IndianStateCode> csvIterable = () -> censusCSVIterator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count() ;
			return numOfEntries ;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
			
	}	
}
