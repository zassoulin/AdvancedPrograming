package ap.scrabble.gameclient.viewmodel;

import java.util.Observable;
import java.util.Observer;

import ap.scrabble.gameclient.model.Model;

public class MyViewModel extends ViewModel {
	// Implement the ViewModel facade

	private Model model;

	public MyViewModel(Model model) {
		this.model = model;
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {}
}
