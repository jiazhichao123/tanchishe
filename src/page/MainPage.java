package page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainPage extends JFrame implements MouseListener, ActionListener {
    JPanel mb1, mb2, mb3, mb4, mb5, mb6, mb7, mb8, mb9, mb10, mb11, mb12;
    JLabel px1, px2, px3;
    JTextField tx1, tx2, tx3;
    JButton bt1, bt2, bt3, bt4, bt5, bt6;
    public MainPage(){
        mb1 = new JPanel();
        mb1.setBackground(Color.blue);


        this.add(mb1,BorderLayout.EAST);
        this.setTitle("贪吃蛇");
        this.setSize(1200, 900);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
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
