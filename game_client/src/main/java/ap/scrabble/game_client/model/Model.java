package ap.scrabble.game_client.model;

import java.util.Observable;

// The Model's facade - only expose necessary functionality.
// Using abstract class instead of an interface so we can inherit Observable as part of the facade.
public abstract class Model extends Observable {
	// Define the Model's facade here (only ViewModel uses it)
}
