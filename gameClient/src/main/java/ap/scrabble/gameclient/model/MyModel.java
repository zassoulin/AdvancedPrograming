package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;

public class MyModel extends Model {
	// Implement the Model facade

    DictionaryServerConfig dictionaryServerConfig;

    public MyModel(DictionaryServerConfig dictionaryServerConfig) {
        this.dictionaryServerConfig = dictionaryServerConfig;
    }
}
