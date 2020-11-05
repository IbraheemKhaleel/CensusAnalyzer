package com.bridgelabz.censusanalyzer;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCsvBuilder {
	public <E> Iterator<E> getCSVFileIterator(Reader reader , Class<E> csvClass) throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch(IllegalStateException e) 
		{
			throw new CensusAnalyserException(e.getMessage(),
	                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
		}


}