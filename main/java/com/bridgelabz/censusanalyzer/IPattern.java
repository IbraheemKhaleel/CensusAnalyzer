package com.bridgelabz.censusanalyzer;

@FunctionalInterface
public interface IPattern {
	
	boolean patternMatcher(String pattern, String name) ;

}
