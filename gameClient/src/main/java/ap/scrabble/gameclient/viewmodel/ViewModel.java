package ap.scrabble.gameclient.viewmodel;

import java.util.Observable;
import java.util.Observer;

// The "tunnel" between the View and the Model.
// Using an abstract class instead of an interface so we can inherit Observable and define Properties as part of the facade.
public abstract class ViewModel extends Observable implements Observer {
	// Define the ViewModel's facade here (only View uses it)
}
