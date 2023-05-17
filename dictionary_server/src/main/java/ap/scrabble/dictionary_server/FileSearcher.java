package ap.scrabble.dictionary_server;

public interface FileSearcher {
	public boolean search(String word, String...fileNames);	
	public void stop();

}