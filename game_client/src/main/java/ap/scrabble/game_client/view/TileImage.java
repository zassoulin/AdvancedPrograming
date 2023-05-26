package ap.scrabble.game_client.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileImage extends ImageView {


    private Rectangle border;
    private char letter;

    public TileImage(Image image, char l, int w, int h, Rectangle border){
        super(image);
        this.letter = l;
        this.setFitWidth(w);
        this.setFitHeight(h);
        this.border = border;
    }

    public char getLetter() {
        return letter;
    }


    public Rectangle getBorder() {
        return border;
    }

    public void toggleHighlight(){
        // highlight character
        if (this.getBorder().getFill() != Color.TRANSPARENT)
            this.getBorder().setFill(Color.TRANSPARENT);
        else
            this.getBorder().setFill(Color.ORANGE);
    }
}