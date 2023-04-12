package Game.src;

public interface CacheReplacementPolicy{
	void add(String word);
	String remove(); 
}
