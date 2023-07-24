package ap.scrabble.gameclient.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


import java.io.IOException;
import java.io.Serializable;

public class GameGrid extends GridPane implements Serializable {


    private byte[][] boardLayout;

    public StackPane[][] getBoardGrid() {
        return boardGrid;
    }

    private StackPane[][] boardGrid = new StackPane[15][15];
    private StackPane clickedRect;

    private int clickedRectX;

    public int getClickedRectX() {
        return clickedRectX;
    }

    public int getClickedRectY() {
        return clickedRectY;
    }

    private int clickedRectY;

    private final int squareSize =  48;

    public GameGrid() {
        super();
//        Setting the controller in runtime
//        board = new BoardController();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
//        loader.setController(board);
//        try {
//            Node b = loader.load();
//            this.getChildren().add(b);
//        } catch (IOException e) {}
        //drawBoard();
    }
    public int getSquareSize() {
        return squareSize;
    }

    public void setBoardLayout(byte[][] board){
        this.boardLayout = board;
        drawBoard();
    }

    private void drawBoard() {
        Rectangle rectangle;
        StackPane stackPane;

        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout[i].length; j++) {
                stackPane = new StackPane();
                rectangle = new Rectangle(squareSize,squareSize);
                switch (boardLayout[i][j]) {
                    case 0:
                        rectangle.setFill(Color.GREEN);
                        break;
                    case 1:
                        rectangle.setFill(Color.TURQUOISE);
                        break;
                    case 2:
                        rectangle.setFill(Color.BLUE);
                        break;
                    case 3:
                        rectangle.setFill(Color.YELLOW);
                        break;
                    case 4:
                        rectangle.setFill(Color.RED);
                        break;
                }

                rectangle.setStroke(Color.BLACK);
                stackPane.getChildren().addAll(rectangle);
                boardGrid[i][j] = stackPane;
                add(stackPane, i, j);
                int finalI = i;
                int finalJ = j;
                StackPane finalStackPane = stackPane;
                rectangle.setOnMouseClicked(mouseEvent -> {
//                    System.out.println(finalI + ", " + finalJ);
                    this.clickedRectX = finalI;
                    this.clickedRectY = finalJ;
                    this.clickedRect = finalStackPane;
                });
            }
        }
    }

    public StackPane getClickedRect() {
        return clickedRect;
    }

}
