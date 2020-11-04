package com.bridgelabz.censusanalyzer;

public class FileTypeValidator {
	 private static final String REGEX_EXTENSION = ".*.csv$";
	    IPattern iPattern;
	    public FileTypeValidator() {
	        iPattern = ((input, pattern) -> input.matches(pattern));
	    }
	    public boolean validateFileType(String path) throws CensusAnalyserException {
	        return iPattern.patternMatcher(path , REGEX_EXTENSION);
	    }
}
