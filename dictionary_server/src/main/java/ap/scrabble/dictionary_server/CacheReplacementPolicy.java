package ap.scrabble.dictionary_server;

public interface CacheReplacementPolicy{
	void add(String word);
	String remove(); 
}
