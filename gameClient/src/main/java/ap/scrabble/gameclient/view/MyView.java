package ap.scrabble.gameclient.view;

import java.util.Observable;
import java.util.Observer;

import ap.scrabble.gameclient.viewmodel.MyViewModel;
import javafx.event.ActionEvent;

public class MyView implements View, Observer {
	// Implement the View interface
	private MyViewModel viewModel;
	private BoardController boardController;

	private initGameController gameController;

//	public void init(MyViewModel viewModel, BoardController bc) {
//		this.viewModel = viewModel;
//		viewModel.addObserver(this);
//		this.boardController = bc;
//		bc.setMyView(this);
//		//menuController.setMyView(this);
//	}

	//OR - MY CHANGES
	public void init(MyViewModel viewModel, initGameController gc) {
		this.viewModel = viewModel;
		viewModel.addObserver(this);
		this.gameController = gc;
		gc.setMyView(this);
		//menuController.setMyView(this);
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


	public void addPlayerCountRt()
	{
		//this.viewModel.
	}
	public void ViewCreateGameRt(String playerName)
	{
		this.viewModel.createGameRt(playerName);
		addPlayerCountRt();;
	}
	public void ViewSetPlayerNameRt(String playerName){this.viewModel.setPlayerNameRt(playerName);}
	public void ViewJoinGameRt(String playerName){this.viewModel.joinGameRt(playerName);}
	public void ViewStartGameRt(){this.viewModel.startGameRt();}

}
