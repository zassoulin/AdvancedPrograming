module com.advancedprograming.gameclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.advancedprograming.gameclient to javafx.fxml;
    exports com.advancedprograming.gameclient;
}