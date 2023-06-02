package ap.scrabble.gameclient;

import java.io.IOException;

import ap.scrabble.gameclient.model.MyModel;
import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import ap.scrabble.gameclient.view.MyView;
import ap.scrabble.gameclient.viewmodel.MyViewModel;
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
		Parent root = fxl.load(getClass().getResource("startingWindow.fxml").openStream());

//		MyModel model = new MyModel(new DictionaryServerConfig("dictionary_server.ini"),new HostServerConfig("host_server.ini"));
//		MyViewModel viewModel = new MyViewModel(model);
//		MyView view = (MyView)fxl.getController();
//		view.init(viewModel);


//		MyModel model = new MyModel(new DictionaryServerConfig("dictionary_server.ini"),new HostServerConfig("host_server.ini"));
//		MyViewModel viewModel = new MyViewModel(model);
//		MyView view = (MyView)fxl.getController();
//		view.init(viewModel);

		scene = new Scene(root);//, 640, 480);
		stage.setScene(scene);
		stage.show();
	}


	public static void main(String[] args) {
		launch();
	}

}