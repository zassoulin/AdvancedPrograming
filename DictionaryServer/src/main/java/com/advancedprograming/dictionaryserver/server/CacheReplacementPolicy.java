package com.advancedprograming.dictionaryserver.server;

public interface CacheReplacementPolicy{
	void add(String word);
	String remove(); 
}
