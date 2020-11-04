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
		try {
			FileTypeValidator fileTypeValidator = new FileTypeValidator();
			boolean result = fileTypeValidator.validateFileType(csvFilePath);
			if(!result) {
				throw new CensusAnalyserException("Enter correct file type", CensusAnalyserException.ExceptionType.WRONG_TYPE);
			}
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			Iterator<IndianStateCensusData> censusCSVIterator = this.getCSVFileIteratorCensus(reader, IndianStateCensusData.class);
			Iterable<IndianStateCensusData> csvIterable = () -> censusCSVIterator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException("Please enter correct path",
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}catch (RuntimeException e) {
            throw new CensusAnalyserException("Please select correct csv file  ",
                    CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	
	}
	
		public int loadStateCode(String csvFilePath) throws CensusAnalyserException {
		try {
			FileTypeValidator fileTypeValidator = new FileTypeValidator();
			boolean result = fileTypeValidator.validateFileType(csvFilePath);
			if(!result) {
				throw new CensusAnalyserException("Enter correct file type", CensusAnalyserException.ExceptionType.WRONG_TYPE);
			}
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			Iterator<IndianStateCode> stateCodeCSVIterator = this.getCSVFileIteratorIndiaCode(reader, IndianStateCode.class);
			Iterable<IndianStateCode> stateCodeCSVIterable = () -> stateCodeCSVIterator ;
			int numOfEntries = (int) StreamSupport.stream(stateCodeCSVIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException("Please enter correct path",
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}catch (RuntimeException e) {
            throw new CensusAnalyserException("Please select correct csv file  ",
                    CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
			
	}
		private Iterator<IndianStateCensusData> getCSVFileIteratorCensus(Reader reader , Class<IndianStateCensusData> class1) throws CensusAnalyserException {
			try {
			CsvToBeanBuilder<IndianStateCensusData> csvToBeanBuilder = new CsvToBeanBuilder<IndianStateCensusData>(reader);
			csvToBeanBuilder.withType(IndianStateCensusData.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndianStateCensusData> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
			} catch(IllegalStateException e) {
				throw new CensusAnalyserException(e.getMessage(),
	                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
			}
			
		}
		private Iterator<IndianStateCode> getCSVFileIteratorIndiaCode(Reader reader , Class<IndianStateCode> class1) throws CensusAnalyserException {
			try {
			CsvToBeanBuilder<IndianStateCode> csvToBeanBuilder = new CsvToBeanBuilder<IndianStateCode>(reader);
			csvToBeanBuilder.withType(IndianStateCode.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndianStateCode> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
			} catch(IllegalStateException e) {
				throw new CensusAnalyserException(e.getMessage(),
	                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
			}
			
		}
}
