package page;

import domain.Coordinate;
import domain.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class SnakeJpane extends JPanel{

    private Snake snake;


    public SnakeJpane(Snake snake){
        this.snake =snake;
    }

    @Override
    public void paint(Graphics g) {
        List<Coordinate> coordinateList = snake.getCoordinateList();
        //画背景
        g.setColor(Color.white);
        g.fillRect(0,0,800,800);

        g.setColor(Color.black);
        g.fillRect(20,20,760,760);
        drawSnake(g);

        g.setColor(Color.yellow);
        g.fillRoundRect(snake.getDian().getX()*40+20,snake.getDian().getY()*40+20,40,40,40,40);
    }
    //画蛇
   private void drawSnake(Graphics g){
        List<Coordinate> coordinateList = snake.getCoordinateList();
        g.setColor(Color.green);
        int size = coordinateList.size();
       Coordinate coordinate = coordinateList.get(size-1);
       g.fillRoundRect(coordinate.getX()*40+20,coordinate.getY()*40+20,40,40,40,40);
       switch (snake.getFx()){
           case 1:g.fillRect(coordinate.getX()*40+20,coordinate.getY()*40+20,20,40);break;
           case 2:g.fillRect(coordinate.getX()*40+20,coordinate.getY()*40+20,40,20);break;
           case 3:g.fillRect(coordinate.getX()*40+40,coordinate.getY()*40+20,20,40);break;
           case 4:g.fillRect(coordinate.getX()*40+20,coordinate.getY()*40+40,40,20);break;
       }
        for (int i =0;i<size-1;i++){
            Coordinate coordinate1 = coordinateList.get(i);
            g.fillRect(coordinate1.getX()*40+20,coordinate1.getY()*40+20,40,40);
            g.setColor(Color.white);
            g.drawRect(coordinate1.getX()*40+20,coordinate1.getY()*40+20,40,40);
            g.setColor(Color.green);
        }

    }


    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

}
