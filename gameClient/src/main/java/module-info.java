module com.advancedprograming.gameclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.advancedprograming.gameclient to javafx.fxml;
    exports com.advancedprograming.gameclient;
    exports com.advancedprograming.gameclient.view;
    opens com.advancedprograming.gameclient.view to javafx.fxml;
}