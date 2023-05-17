package ap.scrabble.dictionary_server;


import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {

	Map<String , Dictionary> dictionaryMap;
	private static DictionaryManager dictionaryManager = null;

	public DictionaryManager() {
		this.dictionaryMap = new HashMap<>();
	}


	public static DictionaryManager get() {
		if(dictionaryManager == null){
			dictionaryManager = new DictionaryManager();
		}
		return dictionaryManager;
	}

	public boolean query(String ... strings) {
		if (strings.length < 2)
			return false;
		String word = strings[strings.length - 1];
		String book;
		boolean found = false;
		for (int i = 0; i < strings.length -1 ; i++){
			book = strings[i];
			Dictionary dictionary = GetAndAddDict(book);
			if(dictionary.query(word)){
				found = true;
			}
		}
		return found;
	}

	public boolean challenge(String ... strings) {
		if (strings.length < 2)
			return false;
		String word = strings[strings.length - 1];
		String book;
		boolean found = false;
		for (int i = 0; i < strings.length -1 ; i++){
			book = strings[i];
			Dictionary dictionary = GetAndAddDict(book);
			if(dictionary.challenge(word)){
				found = true;
			}
		}
		return found;
	}

	public int getSize() {
		return dictionaryMap.size();
	}
	//function gets dict from amp and adds it if it does not exist
	private Dictionary GetAndAddDict(String book){
		if(dictionaryMap.containsKey(book)){
			return dictionaryMap.get(book);
		}
		Dictionary dictionary = new Dictionary(book);
		dictionaryMap.put(book,dictionary);
		return dictionary;
	}
}
