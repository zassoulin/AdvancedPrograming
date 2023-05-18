module ap.scrabble.gameclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens ap.scrabble.gameclient to javafx.fxml;
    exports ap.scrabble.gameclient;
    exports ap.scrabble.gameclient.view;
    opens ap.scrabble.gameclient.view to javafx.fxml;
}