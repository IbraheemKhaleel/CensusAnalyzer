package com.bridgelabz.censusanalyzer;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCsvBuilder<E> implements ICSVBuilder<E>{
	public  Iterator<E> getCSVFileIterator(Reader reader , Class csvClass) throws CensusAnalyserException {
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
