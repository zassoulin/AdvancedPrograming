package ap.scrabble.gameclient.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
//                ((StackPane) selectedTile.getParent()).getChildren().clear();
                this.TileRack.getChildren().remove(selectedTile.getParent());
                selectedTile = null;
            }

            // add to grid
            if (temp != null) {
                addTileToGrid(temp);
            }
        }

    }

    private void addTileToGrid(TileImage t){
        t.setOnMouseClicked(this::handleTileOnBoardClick);
        t.setFitHeight(gameGrid.getSquareSize());
        t.setFitWidth(gameGrid.getSquareSize());
        gameGrid.getClickedRect().getChildren().add(t);
        t.setXgrid(gameGrid.getClickedRectX());
        t.setYgrid(gameGrid.getClickedRectY());
//        System.out.println("Letter: " + t.getLetter() + " @ " + gameGrid.getClickedRectX() + "," + gameGrid.getClickedRectY());
        tempPlacedTiles.add(t);
    }

    public void handleTileOnBoardClick(MouseEvent event) {
        TileImage clickedTile = (TileImage) event.getSource();
        tempPlacedTiles.remove(clickedTile);
        ((StackPane) clickedTile.getParent()).getChildren().remove(clickedTile);
        addTileToRack(clickedTile);
    }


    // ------------------------ Tile rack ------------------------
    @FXML
    HBox TileRack;

    char []tiles = new char[]{'t','s','b', 'u','k','a','l',};

    final private int tileW = 80;
    final private int tileH = 90;

    TileImage selectedTile;

    ArrayList<TileImage> tempPlacedTiles = new ArrayList<>();

    private void drawTileStack(char[] tiles){
        try {
            TileImage tileView;
            StackPane sp;
            for (int i = 0; i < tiles.length; i++) {
                addTileToRack(tiles[i]);
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addTileToRack(TileImage t) {
        addTileToRack(t.getLetter());
    }
    private void addTileToRack(char c){
        TileImage tileView;
        StackPane sp;
        // define tile highlight
        Rectangle tileHighlight = new Rectangle(tileW + 10, tileH + 10, Color.TRANSPARENT);
        tileHighlight.setArcWidth(20);
        tileHighlight.setArcHeight(20);

        // define tile image
//        tileView = new TileImage(new Image("./Tiles/" + c + ".png"), c, tileW, tileH, tileHighlight);
        tileView = new TileImage(new Image("C:\\Users\\edent\\Documents\\GitHub\\advancedprograming\\gameClient\\src\\main\\resources\\Tiles\\" + c + ".png"), c, tileW, tileH, tileHighlight);
        tileView.setOnMouseClicked(this::handleTileClick);

        // define stack and add stack to the tile rack
        sp = new StackPane(tileHighlight, tileView);
        TileRack.getChildren().add(sp);
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
//        System.out.println("Card clicked: " + clickedTile.getLetter());
    }

    // ------------------------ Button clicks ------------------------

    public void submit(ActionEvent actionEvent) {
        printDirection();
        if (areCoordinatesInLine() > 0){
            Character[] c = getLettersInLine(areCoordinatesInLine());
            printLettersInLine(c);
        }
    }

    public Character[] getLettersInLine(int direction) {
        // Sort based on direction
        tempPlacedTiles.sort((tile1, tile2) -> {
            if (direction == 1) { // horizontal
                return Integer.compare(tile1.getCoord()[0], tile2.getCoord()[0]); // compare x-coordinates
            } else { // vertical
                return Integer.compare(tile1.getCoord()[1], tile2.getCoord()[1]); // compare y-coordinates
            }
        });

        // Get first and last coordinates
        int[] firstCoordinate = tempPlacedTiles.get(0).getCoord();
        int[] lastCoordinate = tempPlacedTiles.get(tempPlacedTiles.size() - 1).getCoord();

        // Calculate total length and initialize array
        int length = direction == 1 ? lastCoordinate[0] - firstCoordinate[0] + 1 : lastCoordinate[1] - firstCoordinate[1] + 1;
        Character[] letters = new Character[length];

        // Fill array with letters or nulls
        for (int i = 0; i < length; i++) {
            // Calculate current coordinate
            int currentX = direction == 1 ? firstCoordinate[0] + i : firstCoordinate[0];
            int currentY = direction == 1 ? firstCoordinate[1] : firstCoordinate[1] + i;

            // Find tile at current coordinate
            TileImage foundTile = null;
            for (TileImage tile : tempPlacedTiles) {
                if (tile.getCoord()[0] == currentX && tile.getCoord()[1] == currentY) {
                    foundTile = tile;
                    break;
                }
            }

            // Assign letter to array or null if tile not found
            letters[i] = (foundTile != null) ? foundTile.getLetter() : null;
//            letters[i] = (foundTile != null) ? foundTile.getLetter() : '_';
        }

        return letters;
    }

    private void printLettersInLine(Character[] letters) {
        System.out.print("Letters in line: ");
        for (Character letter : letters) {
            if (letter == null) {
                System.out.print("null ");
            } else {
                System.out.print(letter + " ");
            }
        }
        System.out.println();
    }

    private void printDirection() {
        int direction = areCoordinatesInLine();
        switch (direction) {
            case 0:
                System.out.println("not valid");
                break;
            case 1:
                System.out.println("horizontal");
                break;
            case 2:
                System.out.println("vertical");
                break;
            default:
                System.out.println("Unexpected value: " + direction);
                break;
        }
    }



    private int areCoordinatesInLine() {
        if (tempPlacedTiles.size() < 2) {
            return 0; // 0 or 1 points doesn't define a line direction
        }

        // get the coordinates of the first TileImage
        int[] firstCoordinate = tempPlacedTiles.get(0).getCoord();

        // check vertical or horizontal line
        boolean isVertical = tempPlacedTiles.stream().allMatch(tile -> tile.getCoord()[0] == firstCoordinate[0]);
        if (isVertical) return 2;

        boolean isHorizontal = tempPlacedTiles.stream().allMatch(tile -> tile.getCoord()[1] == firstCoordinate[1]);
        if (isHorizontal) return 1;

        return 0; // not in a line
    }


    public void clear(ActionEvent actionEvent) {
        gameGrid.getChildren().clear();
    }

    public void skip(ActionEvent actionEvent) {
        char randomChar = (char) ('A' + Math.random() * ('Z' - 'A' + 1));
        if (Math.random() < 0.5) {
            randomChar += ('a' - 'A');  // Convert to lowercase
        }



        addTileToRack(randomChar);
        System.out.println(this.TileRack.getChildren());
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
        this.TileRack.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.DOTTED, CornerRadii.EMPTY, BorderStroke.MEDIUM)));

    }

    // -------------------------------Init-----------------------------------
}
