package domain;

import java.util.ArrayList;

public class Snake extends SnakeParent {
    public Snake(ArrayList<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
        size = coordinateList.size();
        all = getDians();
        createDian();
    }

    @Override
    public void autoMove() {

    }

    //死了 返回true
    public boolean contains(ArrayList<Coordinate> clone, Coordinate move1){
        return (clone.contains(move1)||move1.getX() < 0 || move1.getX() > c || move1.getY() < 0 || move1.getY() > c);
    }
}
