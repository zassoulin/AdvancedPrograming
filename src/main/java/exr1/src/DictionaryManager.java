package exr1.src;


import Game.src.Dictionary;

import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {

    Map<String , Dictionary> dictionaryMap;

    public DictionaryManager() {
        this.dictionaryMap = new HashMap<>();
    }


    public static DictionaryManager get() {
        return new DictionaryManager();
    }

    public boolean query(String ... strings) {
        String word = strings[strings.length - 1];
        String book;
        Boolean Found = false;
        for (int i = 0; i < strings.length -1 ; i++){
            book = strings[i];
            Dictionary dictionary = GetAndAddDict(book);
            if(dictionary.query(word)){
                Found = true;
            }
        }
        return Found;
    }

    public boolean challenge(String ... strings) {
        String word = strings[strings.length - 1];
        String book;
        Boolean Found = false;
        for (int i = 0; i < strings.length -1 ; i++){
            book = strings[i];
            Dictionary dictionary = GetAndAddDict(book);
            if(dictionary.challenge(word)){
                Found = true;
            }
        }
        return Found;
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
