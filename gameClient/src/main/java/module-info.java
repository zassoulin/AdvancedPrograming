module com.advancedprograming.gameclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens ap.scrabble.game_client to javafx.fxml;
    exports ap.scrabble.game_client;
    exports ap.scrabble.game_client.view;
    opens ap.scrabble.game_client.view to javafx.fxml;
}