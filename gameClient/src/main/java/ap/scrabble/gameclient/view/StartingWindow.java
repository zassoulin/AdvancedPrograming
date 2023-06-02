package ap.scrabble.gameclient.view;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;


public class StartingWindow
//        extends Application
{ /* 1 */
    private Stage primaryStage; // Reference to the main stage
    @FXML
    private Label playerCountLabel;


//    @Override
//    public void start(Stage stage) throws IOException
//    { /* 2 */
//        FXMLLoader fxmlLoader = new FXMLLoader(StartingWindow.class.getResource("startingWindow.fxml"));
//        Parent root = fxmlLoader.load();
//
//        /* Get the initGameController instance */
//        initGameController controller = fxmlLoader.getController();
//        controller.setStartingWindow(this);
//
//        /* show first window */
//        Scene scene = new Scene(root, 350, 350);
//        stage.setTitle("Starting Window");
//        stage.setScene(scene);
//        stage.show();
//    } /* 2 */

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPlayerCountLabel(Label playerCountLabel) {
        this.playerCountLabel = playerCountLabel;
    }

//    public static void main(String[] args) {
//        launch();
//    }
} /* 1 */