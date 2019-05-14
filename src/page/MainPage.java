package page;

import domain.Coordinate;
import domain.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends JFrame implements MouseListener, ActionListener {
    JPanel mb1, mb2, mb3, mb4, mb5, mb6, mb7, mb8, mb9, mb10, mb11, mb12;
    JLabel px1, px2, px3;
    JTextField tx1, tx2, tx3;
    JButton bt1, bt2, bt3, bt4;
    SnakeJpane snakeJpane;
    public MainPage(){

        bt1 = new JButton("初始化");
        bt2 = new JButton("手动开始");
        bt3 = new JButton("自动开始");
        bt4 = new JButton("暂停");

        px1 = new JLabel("长度");
        px2 = new JLabel("0");

        mb2 = new JPanel();
        mb2.add(bt1);
        mb2.add(bt2);

        mb3 = new JPanel();
        mb3.add(bt3);
        mb3.add(bt4);

        mb4 = new JPanel();
        mb4.add(px1);
        mb4.add(px2);

        mb5 = new JPanel();
        mb5.setLayout(new BorderLayout());
        mb5.add(mb3,BorderLayout.NORTH);
        mb5.add(mb4);

        mb1 = new JPanel();
        mb1.setBackground(Color.blue);
        mb1.setLayout(new BorderLayout());
        mb1.add(mb2,BorderLayout.NORTH);
        mb1.add(mb5);
//        mb1.add(mb4);


        snakeJpane = new SnakeJpane(getSnake());

        this.add(snakeJpane,BorderLayout.CENTER);
        this.add(mb1,BorderLayout.EAST);
        this.setTitle("贪吃蛇");
        this.setSize(1000, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    private Snake getSnake(){
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(2,2));
        coordinates.add(new Coordinate(3,2));
        coordinates.add(new Coordinate(4,2));
        return new Snake(coordinates);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
