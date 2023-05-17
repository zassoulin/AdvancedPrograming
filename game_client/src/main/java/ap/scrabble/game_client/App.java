package ap.scrabble.game_client;

import java.io.IOException;

import ap.scrabble.game_client.model.MyModel;
import ap.scrabble.game_client.view.MyView;
import ap.scrabble.game_client.view_model.MyViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxl = new FXMLLoader();
		Parent root = fxl.load(getClass().getResource("App.fxml").openStream());

		MyModel model = new MyModel();
		MyViewModel viewModel = new MyViewModel(model);
		MyView view = (MyView)fxl.getController();
		view.init(viewModel);

		scene = new Scene(root, 640, 480);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}