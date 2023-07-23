package ap.scrabble.gameclient.view;

import java.util.List;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import ap.scrabble.gameclient.util.Message;
import ap.scrabble.gameclient.viewmodel.MyViewModel;
import javafx.application.Platform;
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

	boolean gameStarted = false;


	public void init(MyViewModel viewModel, initGameController gc, BoardController bc, Parent BoardRoot, Stage stage) {
		this.viewModel = viewModel;
		viewModel.addObserver(this);
		this.gameController = gc;
		gc.hostPlayerCount.textProperty().bind(viewModel.playerCountProperty());
		this.boardController = bc;
		gc.setMyView(this);
		bc.setMyView(this);
		this.BoardRoot = BoardRoot;
		this.stage = stage;
	}



	public void submitLetters(char[] letters, int x, int y, boolean vertical){
		this.viewModel.sendSubmittedWord(letters,x,y,vertical);
//		this.viewModel.getBoard();
	}


	public void hostGame(ActionEvent actionEvent) {

	}

	public void joinGame(String playerName) {
		this.viewModel.joinGameRt(playerName);
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
//	public void ViewSetPlayerNameRt(String playerName){this.viewModel.setPlayerNameRt(playerName);}
	public void ViewJoinGameRt(String playerName){this.viewModel.joinGameRt(playerName);}
	public void ViewStartGameRt(){
//		boardController.setPlayerNames(viewModel.getPlayerList());
		this.viewModel.startGameRt();
		// wait to get GAME_STARTED message
//		ViewMoveToGameWindowRt(); // in handleMessage
	}
	public void ViewMoveToGameWindowRt() {
		stage.setTitle("Game Window");
		scene = new Scene(this.BoardRoot);
		stage.setScene(scene);
		stage.setResizable(false);
//		this.boardController.setBoardWindowNames();
	}

	public List<String> ViewGetPlayerNames()
	{
		gameStarted = true;
		return this.viewModel.getPlayerNames();
	}

	public int ViewGetScore(String playerName)
	{
		return this.viewModel.getPlayerScore(playerName);
	}

	public void addPlayer(String playerName) {
		viewModel.addPlayer(playerName);
		addLocalPlayer(playerName);
	}


	private void updatePlayerScores(Map<String, Integer> playersScores) {
		this.boardController.updatePlayerScores(playersScores);
	}

	@Override
	public void update(Observable o, Object arg) {
		Message message = (Message) arg;
		System.out.println(MessageFormat.format("View Received message of type {0} with Value: {1}", message.type , message.arg));
		HandleMessage(message);
	}

	private void HandleMessage(Message message) {
		if (message.type == "GAME_STARTED") {
			if (!gameStarted) {
				boardController.setBoardWindowNames();
				Platform.runLater(() -> {
					ViewMoveToGameWindowRt();

				});
			}
		}
		else if (message.type == "PLAYER_TILES"){
			char[] c = (char[])message.arg;
			Platform.runLater(() -> {
				boardController.updatePlayerTiles(c);
			});
		}
		else if (message.type == "PLAYER_SCORES"){
			Map<String,Integer> playersScores = (Map<String,Integer>)message.arg;
			Platform.runLater(() -> {
				updatePlayerScores(playersScores);
			});
		}
		else if (message.type == "CURRENT_PLAYER"){
			boardController.togglePlayerTurnHighlight((String)message.arg);
			boardController.clearCache();
			boardController.toggleSubmitButton();
		}
		else if (message.type == "PLAYER_ADDED"){
			gameController.playerAdded();
		}
	}

	public void setHost(){
		viewModel.setHost();
	}


	public void saveGame() {
		viewModel.saveGame();
	}

	public void loadGame() {
		viewModel.loadGame();
	}

	public void setGuestName(String playerName) {
		viewModel.setRemotePlayerName(playerName);
	}

	public boolean getIsHost() {
		return viewModel.getIsHost();
	}

	public String getRemotePlayerName() {
		return viewModel.getRemotePlayerName();
	}

	public String getCurrentPlayer() {
		return viewModel.getCurrentPlayer();
	}

	public void addLocalPlayer(String playerName) {
		viewModel.addLocalPlayer(playerName);
	}

	public List<String> getLocalPlayers() {
		return viewModel.getLocalPlayers();
	}
}
