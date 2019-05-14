package domain;

import java.util.List;

public class Snake {
    private List<Coordinate> coordinateList;
    private int fx = 1;
    private int size;

    public Snake(List<Coordinate> coordinateList){
        this.coordinateList = coordinateList;
        size = coordinateList.size();
    }

    public boolean move(Coordinate coordinate){
        Coordinate cd = coordinateList.get(size - 1);
        Coordinate move = cd.move(fx);
        if (coordinate.equals(move)){
            //吃到了点
            size++;
            coordinateList.add(move);
            return true;
        }
        int x = move.getX();
        int y = move.getY();
        coordinateList.remove(0);
        if(coordinateList.contains(move)||x <0||x>18||y<0||y>18){
            //吃到了自己 或撞了墙
            return false;
        }
        coordinateList.add(move);
        return true;
    }





    // 获取与赋值
    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    public int getFx() {
        return fx;
    }

    public void setFx(int fx) {
        this.fx = fx;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
