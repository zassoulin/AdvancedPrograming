package ap.scrabble.gameclient;

import java.io.IOException;

import ap.scrabble.gameclient.model.MyModel;
import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import ap.scrabble.gameclient.view.BoardController;
import ap.scrabble.gameclient.view.MyView;
import ap.scrabble.gameclient.view.initGameController;
import ap.scrabble.gameclient.viewmodel.MyViewModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * JavaFX App
 **/
public class App extends Application {

	private static Scene scene;
	private Stage primaryStage; // Reference to the main stage
	@FXML
	private Label playerCountLabel;

	@Override
	public void start(Stage stage) throws IOException {
		/* First Window */
		FXMLLoader fxl = new FXMLLoader();
		Parent root = fxl.load(getClass().getResource("startingWindow.fxml").openStream());

        /* Get the initGameController instance */
        initGameController controller = fxl.getController();
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		/* Second Window */
		FXMLLoader fxlBoard = new FXMLLoader();
		Parent BoardRoot = fxlBoard.load(getClass().getResource("GameWindow.fxml").openStream());

		/* Get the BoardController instance */
		BoardController controllerBoard = fxlBoard.getController();
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		MyModel model = new MyModel(new DictionaryServerConfig("dictionary_server.ini"),new HostServerConfig("host_server.ini"));
		MyViewModel viewModel = new MyViewModel(model);
		MyView view = new MyView();
		view.init(viewModel,fxl.getController(),fxlBoard.getController(), BoardRoot, stage);

		/* show first window */
        stage.setTitle("Starting Window");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPlayerCountLabel(Label playerCountLabel) {
		this.playerCountLabel = playerCountLabel;
	}

	public static void main(String[] args) {
		launch();
	}

}