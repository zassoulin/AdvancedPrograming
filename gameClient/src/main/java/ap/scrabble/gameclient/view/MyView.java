package ap.scrabble.gameclient.view;

import java.util.Observable;
import java.util.Observer;

import ap.scrabble.gameclient.viewmodel.MyViewModel;
import javafx.event.ActionEvent;

public class MyView implements View, Observer {
	// Implement the View interface
	private MyViewModel viewModel;
	private BoardController boardController;

	public void init(MyViewModel viewModel, BoardController bc) {
		this.viewModel = viewModel;
		viewModel.addObserver(this);
		this.boardController = bc;
		bc.setMyView(this);
		menuController.setMyView(this)
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	public void submitLetters(char[] letters, int x, int y, boolean vertical){
		this.viewModel.receiveSubmittedWord(letters,x,y,vertical);
	}

    public void hostGame(ActionEvent actionEvent) {
    }

	public void joinGame(ActionEvent actionEvent) {
	}


}
