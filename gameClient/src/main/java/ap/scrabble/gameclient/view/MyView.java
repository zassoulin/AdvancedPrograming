package ap.scrabble.gameclient.view;

import java.util.Observable;
import java.util.Observer;

import ap.scrabble.gameclient.viewmodel.MyViewModel;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyView implements View, Observer {
	// Implement the View interface

	private static Scene scene;
	private static Stage stage;
	private Parent BoardRoot;
	private MyViewModel viewModel;
	private BoardController boardController;
	private initGameController gameController;

	public void init(MyViewModel viewModel, initGameController gc, BoardController bc, Parent BoardRoot, Stage stage) {
		this.viewModel = viewModel;
		viewModel.addObserver(this);
		this.gameController = gc;
		this.boardController = bc;
		gc.setMyView(this);
		bc.setMyView(this);
		this.BoardRoot = BoardRoot;
		this.stage = stage;
	}

	@Override
	public void update(Observable o, Object arg) {}

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
	public void ViewMoveToGameWindowRt()
	{
		stage.setTitle("Game Window");
		scene = new Scene(this.BoardRoot);
		stage.setScene(scene);
	}

}
