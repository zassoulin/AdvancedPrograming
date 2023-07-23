@echo off

java --module-path "%PATH_TO_FX%" --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media -cp "target/gameClient-1.0.jar" ap.scrabble.gameclient.App