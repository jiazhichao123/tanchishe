package page;

import domain.Coordinate;
import domain.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SnakeJpane extends JPanel {

    private Snake snake;

    public SnakeJpane(Snake snake){
        this.snake =snake;
    }

    @Override
    public void paint(Graphics g) {
        List<Coordinate> coordinateList = snake.getCoordinateList();
        //画背景
        g.setColor(Color.black);
        g.fillRect(0,0,800,800);

    }
}
