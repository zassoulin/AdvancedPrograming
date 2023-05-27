package ap.scrabble.gameclient.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddLocalPlayerController
{ /* 1 */
    private initGameController gameController;
    @FXML
    private TextField playerNameTextField;
    @FXML
    private Button addButton;
    @FXML
    private Label errorLabel;
    @FXML
    private VBox addPlayerRoot; // Reference to the root element of the Add Player window
    public void setGameController(initGameController gameController) {
        this.gameController = gameController;
    }

    @FXML
    private void addPlayer()
    { /* 2 */
        String playerName = playerNameTextField.getText();

        if (playerName.isEmpty())
        { /* 3 */
            errorLabel.setText("Please enter a player name.");
        } /* 3 */
        else
        { /* 3 */
            // Call the addPlayer() method on the gameController instance
            if (gameController != null)
            { /* 4 */
                gameController.addPlayer();
            } /* 4 */

            // Clear the text field and error label
            playerNameTextField.clear();
            errorLabel.setText("");

            // Close the Add Player window
            Stage currentStage = (Stage) addPlayerRoot.getScene().getWindow();
            currentStage.close();
        } /* 3 */
    } /* 2 */
} /* 1 */