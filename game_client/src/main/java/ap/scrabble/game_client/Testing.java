package ap.scrabble.game_client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Testing extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                double number = i * 2 + j + 1;  // This will give us numbers 1 to 4
                Rectangle rectangle = new Rectangle(100, 100);
                rectangle.setFill(Color.color(1/number,1/number,1/number));
                rectangle.setOnMouseClicked(event -> System.out.println("Square " + number + " clicked"));
                gridPane.add(rectangle, j, i);
            }
        }

        Scene scene = new Scene(gridPane, 200, 200);
        primaryStage.setTitle("Square Tiles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
