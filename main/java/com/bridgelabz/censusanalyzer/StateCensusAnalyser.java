package com.bridgelabz.censusanalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.StreamSupport;

import com.bridgelabz.csvreader.CSVBuilderFactory;
import com.bridgelabz.csvreader.CensusAnalyserException;
import com.bridgelabz.csvreader.ICSVBuilder;
import com.google.gson.Gson;


public class StateCensusAnalyser {
	List<IndianStateCode> CSVList = null ;
	
	public StateCensusAnalyser() 
	{
		this.CSVList = new ArrayList<IndianStateCode>() ;
	}
	public int loadCensus(String csvFilePath) throws CensusAnalyserException 
	{
		FileTypeValidator fileTypeValidator = new FileTypeValidator();
		boolean result = fileTypeValidator.validateFileType(csvFilePath);
		if(!result) {
			throw new CensusAnalyserException("Enter correct file type", CensusAnalyserException.ExceptionType.WRONG_TYPE);
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
			ICSVBuilder csvBuilderFactory = CSVBuilderFactory.createCSVBuilder();
			List<IndianStateCensusData> censusCSVList = csvBuilderFactory.getCSVFileList(reader, IndianStateCensusData.class) ;
			return censusCSVList.size() ;
		} catch (IOException e) {
			throw new CensusAnalyserException("Please enter correct path",
                    					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}catch (RuntimeException e) {
            		throw new CensusAnalyserException("Please select correct csv file  ",
                    					CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}

	}

	public int loadStateCode(String csvFilePath) throws CensusAnalyserException {
		FileTypeValidator fileTypeValidator = new FileTypeValidator();
		boolean result = fileTypeValidator.validateFileType(csvFilePath);
		if(!result) {
			throw new CensusAnalyserException("Enter correct file type", CensusAnalyserException.ExceptionType.WRONG_TYPE);
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
			ICSVBuilder csvBuilderFactory = CSVBuilderFactory.createCSVBuilder();
			Iterator<IndianStateCode> stateCodeCSVIterator = csvBuilderFactory.getCSVFileIterator(reader, IndianStateCode.class);
			return getCount(stateCodeCSVIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException("Please enter correct path",
                    					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}catch (RuntimeException e) {
            		throw new CensusAnalyserException("Please select correct csv file  ",
                    					CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}
		private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> stateCodeCSVIterable = () -> iterator ;
		int numOfEntries = (int) StreamSupport.stream(stateCodeCSVIterable.spliterator(), false).count();
		return numOfEntries;
	}

		public String givenSortedDetails(String csvFilePath) throws CensusAnalyserException, IOException {
			try {
			 	loadStateCode(csvFilePath);
		        if (CSVList == null || CSVList.size() == 0) {
		            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		        }			
				Comparator<IndianStateCode> censusComparator = Comparator.comparing(census -> census.stateName) ;
				this.sort(censusComparator);
			    String sortedStateCensusJson = new Gson().toJson(this.CSVList);
			    return sortedStateCensusJson;
				
			} catch (RuntimeException e) {
	            		throw new CensusAnalyserException("Please select correct csv file  ",
	                    					CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
			}	
			
		}

		private void sort(Comparator<IndianStateCode> censusComparator) {
			for (int i = 0; i < CSVList.size(); i++) {
	            for (int j = 0; j < CSVList.size() - i - 1; j++) {
	                IndianStateCode census1 = CSVList.get(j);
	                IndianStateCode census2 = CSVList.get(j + 1);
	                if (censusComparator.compare(census1, census2) > 0) {
	                    CSVList.set(j, census2);
	                    CSVList.set(j + 1, census1);
	                }

	            }

	        }
			
		}
}
