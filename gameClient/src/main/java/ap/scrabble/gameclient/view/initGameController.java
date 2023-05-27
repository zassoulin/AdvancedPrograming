package ap.scrabble.gameclient.view;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class initGameController {
    private StartingWindow startingWindow;
    public void setStartingWindow(StartingWindow startingWindow) {
        this.startingWindow = startingWindow;
    }
    @FXML
    static int playerCount = 0;
    @FXML
    private Label startingWindowFXML;
    @FXML
    private Label hostPlayerCount;
    @FXML
    private Label hostLabelIP;
    @FXML
    private TextField hostTextBoxPlayer;
    @FXML
    private Button hostButtonAddPlayer;
    @FXML
    private Button hostButtonStartServer;
    @FXML
    private TextField JoinGameTextBoxIP;
    @FXML
    private TextField JoinGameTextBoxPlayer;
    @FXML
    private Button JoinGameButtonConnect;

    @FXML
    public void HostButtonsVisible()
    { /* 2 */
        StartingWindowVisibleButtons(true, false);

        String ipAddress = getIPAddress();
        hostLabelIP.setText("IP Address: " + ipAddress);

        hostTextBoxPlayer.setText("Enter player name");
        hostTextBoxPlayer.setOnMouseClicked(event -> handleMouseClick(event, hostTextBoxPlayer, "Enter player name"));
        hostTextBoxPlayer.setOnKeyTyped(event -> handleKeyTyped(event, hostTextBoxPlayer, "Enter player name"));
    } /* 2 */

    public void JoinGameButtonsVisible()
    { /* 2 */
        StartingWindowVisibleButtons(false, true);

        JoinGameTextBoxIP.setText("Enter IP Address");
        JoinGameTextBoxIP.setOnMouseClicked(event -> handleMouseClick(event, JoinGameTextBoxIP, "Enter IP Address"));
        JoinGameTextBoxIP.setOnKeyTyped(event -> handleKeyTyped(event, JoinGameTextBoxIP, "Enter IP Address"));

        JoinGameTextBoxPlayer.setText("Enter player name");
        JoinGameTextBoxPlayer.setOnMouseClicked(event -> handleMouseClick(event, JoinGameTextBoxPlayer, "Enter player name"));
        JoinGameTextBoxPlayer.setOnKeyTyped(event -> handleKeyTyped(event, JoinGameTextBoxPlayer, "Enter player name"));
    } /* 2 */

    public void addLocalPlayer() throws IOException
    { /* 2 */
        if (playerCount < 3)
            openWindow("AddLocalPlayer.fxml", "Add Player", startingWindow.getPrimaryStage(), false);

    } /* 2 */

    @FXML
    public void addPlayer()
    { /* 2 */
        // Save player info somewhere..

        playerCount++;
        hostPlayerCount.setText("Player count: " + playerCount);
    } /* 2 */

    private void StartingWindowVisibleButtons(boolean hostButtonsStt, boolean joinGameButtonsStt)
    { /* 2 */
        hostLabelIP.setVisible(hostButtonsStt);
        hostTextBoxPlayer.setVisible(hostButtonsStt);
        hostButtonAddPlayer.setVisible(hostButtonsStt);
        hostButtonStartServer.setVisible(hostButtonsStt);
        hostPlayerCount.setVisible(hostButtonsStt);

        JoinGameTextBoxIP.setVisible(joinGameButtonsStt);
        JoinGameTextBoxPlayer.setVisible(joinGameButtonsStt);
        JoinGameButtonConnect.setVisible(joinGameButtonsStt);
    } /* 2 */

    private String getIPAddress()
    { /* 2 */
        try
        { /* 3 */
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } /* 3 */
        catch (UnknownHostException e)
        { /* 3 */
            e.printStackTrace();
        } /* 3 */
        return null;
    } /* 2 */

    private void handleMouseClick(MouseEvent event, TextField textField, String placeholder)
    { /* 2 */
        if (textField.getText().equals(placeholder))
            textField.setText("");

    } /* 2 */

    private void handleKeyTyped(KeyEvent event, TextField textField, String placeholder)
    { /* 2 */
        if (textField.getText().equals(placeholder))
            textField.setText("");

    } /* 2 */

    private void openWindow(String fxmlFile, String title, Stage parentStage, boolean resizable) throws IOException
    { /* 2 */
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initOwner(parentStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(resizable);

        Parent root = loader.load();
        AddLocalPlayerController addPlayerController = loader.getController();
        addPlayerController.setGameController(this); // Set the gameController instance

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } /* 2 */
} /* 1 */