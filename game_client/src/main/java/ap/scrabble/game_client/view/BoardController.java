package ap.scrabble.game_client.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    // ------------------------ Game grid ------------------------
    @FXML
    GameGrid gameGrid;

    final private byte dl = 1;    // double letter
    final private byte tl = 2;    // triple letter
    final private byte dw = 3;    // double word
    final private byte tw = 4;    // triple word

    private byte[][] boardLayout = {
            {tw, 0, 0, dl, 0, 0, 0, tw, 0, 0, 0, dl, 0, 0, tw},
            {0, dw, 0, 0, 0, tl, 0, 0, 0, tl, 0, 0, 0, dw, 0},
            {0, 0, dw, 0, 0, 0, dl, 0, dl, 0, 0, 0, dw, 0, 0},
            {dl, 0, 0, dw, 0, 0, 0, dl, 0, 0, 0, dw, 0, 0, dl},
            {0, 0, 0, 0, dw, 0, 0, 0, 0, 0, dw, 0, 0, 0, 0},
            {0, tl, 0, 0, 0, tl, 0, 0, 0, tl, 0, 0, 0, tl, 0},
            {0, 0, dl, 0, 0, 0, dl, 0, dl, 0, 0, 0, dl, 0, 0},
            {tw, 0, 0, dl, 0, 0, 0, dw, 0, 0, 0, dl, 0, 0, tw},
            {0, 0, dl, 0, 0, 0, dl, 0, dl, 0, 0, 0, dl, 0, 0},
            {0, tl, 0, 0, 0, tl, 0, 0, 0, tl, 0, 0, 0, tl, 0},
            {0, 0, 0, 0, dw, 0, 0, 0, 0, 0, dw, 0, 0, 0, 0},
            {dl, 0, 0, dw, 0, 0, 0, dl, 0, 0, 0, dw, 0, 0, dl},
            {0, 0, dw, 0, 0, 0, dl, 0, dl, 0, 0, 0, dw, 0, 0},
            {0, dw, 0, 0, 0, tl, 0, 0, 0, tl, 0, 0, 0, dw, 0},
            {tw, 0, 0, dl, 0, 0, 0, tw, 0, 0, 0, dl, 0, 0, tw}
    };

    public void handleBoardClick(MouseEvent event) {
        // check if turn is valid, check if tile is not taken already
        if (gameGrid.getClickedRect().getChildren().stream().noneMatch(child -> child instanceof TileImage)) {


            TileImage temp = null;
            if (selectedTile != null) {
                temp = selectedTile;
                // remove from rack
                ((StackPane) selectedTile.getParent()).getChildren().clear();
                selectedTile = null;
            }

            // add to grid
            if (temp != null) {
                temp.setOnMouseClicked(null);
                temp.setFitHeight(gameGrid.getSquareSize());
                temp.setFitWidth(gameGrid.getSquareSize());
                gameGrid.getClickedRect().getChildren().add(temp);
            }
        }

    }


    // ------------------------ Tile rack ------------------------
    @FXML
    HBox TileRack;

    char []tiles = new char[]{'t','s','b', 'u','k','a','l',};

    final private int tileW = 80;
    final private int tileH = 90;

    TileImage selectedTile;

    private void drawTileStack(char[] tiles){
        try {
            TileImage tileView;
            StackPane sp;
            for (int i = 0; i < tiles.length; i++) {
                // define tile highlight
                Rectangle tileHighlight = new Rectangle(tileW + 10, tileH + 10, Color.TRANSPARENT);
                tileHighlight.setArcWidth(20);
                tileHighlight.setArcHeight(20);

                // define tile image
                tileView = new TileImage(new Image("./Tiles/" + tiles[i] + ".png"), tiles[i], tileW, tileH, tileHighlight);
                tileView.setOnMouseClicked(this::handleTileClick);

                // define stack and add stack to the tile rack
                sp = new StackPane(tileHighlight, tileView);
                TileRack.getChildren().add(sp);
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public void handleTileClick(MouseEvent event) {
        TileImage clickedTile = (TileImage) event.getSource();
        // toggle highlight character
        clickedTile.toggleHighlight();
        if (selectedTile != null) {
            selectedTile.toggleHighlight();
        }
        selectedTile = clickedTile;

        // delete tile
//        ((StackPane) selectedTile.getParent()).getChildren().clear();

        // get letter
        System.out.println("Card clicked: " + clickedTile.getLetter());
    }

    // ------------------------ Button clicks ------------------------

    public void submit(ActionEvent actionEvent) {
        Random r = new Random();
        int l = r.nextInt(200);
        int w = r.nextInt(200);
        int x = r.nextInt(200);
        int y = r.nextInt(200);
        double number = r.nextInt(100);
        Rectangle rectangle = new Rectangle(l, w);
        rectangle.setFill(Color.color(1/number,1/number,1/number));
        gameGrid.add(rectangle, x, y);
    }

    public void clear(ActionEvent actionEvent) {
        gameGrid.getChildren().clear();
    }

    public void skip(ActionEvent actionEvent) {
    }

    // -------------------------------Init-----------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gameGrid.setBoardLayout(this.boardLayout);
        this.gameGrid.setOnMouseClicked(mouseEvent -> {
//            System.out.println("clicked on board");
            handleBoardClick(mouseEvent);
        });
        drawTileStack(this.tiles);
    }

    // -------------------------------Init-----------------------------------
}
