package page;

import domain.Coordinate;
import domain.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainPage extends JFrame implements KeyListener, ActionListener {
    JPanel mb1, mb2, mb3, mb4, mb5, mb6, mb7, mb8, mb9, mb10, mb11, mb12;
    JLabel px1, px2, px3;
    JTextField tx1, tx2, tx3;
    JButton bt1, bt2, bt3, bt4;
    SnakeJpane snakeJpane;
    boolean auto;
    public MainPage(){

        bt1 = new JButton("初始化");
        bt2 = new JButton("手动开始");
        bt3 = new JButton("自动开始");
        bt4 = new JButton("暂停");

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        bt4.addActionListener(this);

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

        this.addKeyListener(this);
        this.setFocusable(true);

        this.add(snakeJpane,BorderLayout.CENTER);
        this.add(mb1,BorderLayout.EAST);
        this.setTitle("贪吃蛇");
        this.setSize(1000, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    private Snake getSnake(){
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(2,2));
        coordinates.add(new Coordinate(3,2));
        coordinates.add(new Coordinate(4,2));
        return new Snake(coordinates);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bt2){
           Thread thread = new Thread(()->{
               while (true){
                   snakeJpane.getSnake().move();
                   snakeJpane.repaint();
                   try {
                       Thread.sleep(200);
                   } catch (InterruptedException e1) {
                       e1.printStackTrace();
                   }
               }
           });
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (auto)
            return;
       switch (e.getKeyCode()){
           case 39: snakeJpane.getSnake().setFx(1);break;
           case 40:snakeJpane.getSnake().setFx(2);break;
           case 37:snakeJpane.getSnake().setFx(3);break;
           case 38:snakeJpane.getSnake().setFx(4);break;
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
