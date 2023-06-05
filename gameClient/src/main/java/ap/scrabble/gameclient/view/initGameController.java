package ap.scrabble.gameclient.view;

import ap.scrabble.gameclient.App;
import ap.scrabble.gameclient.viewmodel.MyViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class initGameController {

    private App startingWindow;
    private MyView myView;
    public void setMyView(MyView v){
        this.myView = v;
    }
    public void setStartingWindow(App startingWindow) {
        this.startingWindow = startingWindow;
    }
    @FXML
    static int playerCount = 1;
    @FXML
    private Label startingWindowFXML;
    @FXML
    private Label hostPlayerCount;
    @FXML
    private Label hostLabelIP;
    @FXML
    private TextField hostTextBoxPlayer;
    @FXML
    private Button hostAddPlayerButton;
    @FXML
    private Button hostButtonStartGame;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField JoinGameTextBoxIP;
    @FXML
    private Button JoinGameButtonConnect;
    @FXML
    private Button joinGame;
    @FXML
    private Button hostGame;
    @FXML
    private TextField InitGamePlayerText;

    @FXML
    public void HostButtonsFunction()
    { /* 2 */
        String playerName = InitGamePlayerText.getText();

        /* make sure to enter player name */
        if (playerName.isEmpty())
        { /* 3 */
            errorLabel.setText("Please enter player name.");
            return;
        } /* 3 */

        /* Clear the text field and error label */
        InitGamePlayerText.clear();
        errorLabel.setText("");

        /* sent a request to create game */
        myView.ViewCreateGameRt(playerName);

        /* reveal relevant buttons */
        StartingWindowVisibleButtons(true, false);

        /* show IP address */
        String ipAddress = getIPAddress();
        hostLabelIP.setText("IP Address: " + ipAddress);

        /* optional - adding player text box */
        hostTextBoxPlayer.setText("Enter player name");
        hostTextBoxPlayer.setOnMouseClicked(event -> handleMouseClick(event, hostTextBoxPlayer, "Enter player name"));
        hostTextBoxPlayer.setOnKeyTyped(event -> handleKeyTyped(event, hostTextBoxPlayer, "Enter player name"));
    } /* 2 */

    public void JoinGameButtonFunction()
    { /* 2 */

        String playerName = InitGamePlayerText.getText();

        if (playerName.isEmpty())
        { /* 3 */
            errorLabel.setText("Please enter player name.");
            return;
        } /* 3 */

        // Clear the text field and error label
        InitGamePlayerText.clear();
        errorLabel.setText("");

        /* sent a request to join the game */
        myView.ViewJoinGameRt(playerName);


        StartingWindowVisibleButtons(false, true);
        hostGame.setDisable(true);
        InitGamePlayerText.setVisible(false);

        JoinGameTextBoxIP.setText("Enter IP Address");
        JoinGameTextBoxIP.setOnMouseClicked(event -> handleMouseClick(event, JoinGameTextBoxIP, "Enter IP Address"));
        JoinGameTextBoxIP.setOnKeyTyped(event -> handleKeyTyped(event, JoinGameTextBoxIP, "Enter IP Address"));

    } /* 2 */

    @FXML
    public void addPlayer()
    { /* 2 */

        String playerName = hostTextBoxPlayer.getText();

        if (playerName.isEmpty())
        { /* 3 */
            errorLabel.setText("Please enter a player name.");
            return;
        } /* 3 */

        /* Clear the text field and error label */
        hostTextBoxPlayer.clear();
        errorLabel.setText("");

        /* Save player info somewhere */
        myView.ViewJoinGameRt(playerName);

        if (playerCount < 4)
        { /* 3 */
            playerCount++;
            hostPlayerCount.setText("Connected players: " + playerCount);

            hostButtonStartGame.setDisable(false);

            if(playerCount == 3)
                hostAddPlayerButton.setDisable(true);
        } /* 3 */
    } /* 2 */

    private void StartingWindowVisibleButtons(boolean hostButtonsStt, boolean joinGameButtonsStt)
    { /* 2 */
        InitGamePlayerText.setVisible(false);
        joinGame.setDisable(true);
        hostGame.setDisable(true);

        hostLabelIP.setVisible(hostButtonsStt);
        hostTextBoxPlayer.setVisible(hostButtonsStt);
        hostButtonStartGame.setVisible(hostButtonsStt);
        hostAddPlayerButton.setVisible(hostButtonsStt);
        hostPlayerCount.setVisible(hostButtonsStt);

        JoinGameTextBoxIP.setVisible(joinGameButtonsStt);
        JoinGameButtonConnect.setVisible(joinGameButtonsStt);

        if(hostButtonsStt)
            hostGame.setTextFill(Color.DARKCYAN);

        if(joinGameButtonsStt)
        {
            joinGame.setTextFill(Color.DARKCYAN);
        }
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

    public void startGame()
    {
        myView.ViewStartGameRt();
        myView.ViewMoveToGameWindowRt();
    }
} /* 1 */