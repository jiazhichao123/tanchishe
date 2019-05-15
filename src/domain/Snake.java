package domain;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private ArrayList<Coordinate> coordinateList;
    private ArrayList<Coordinate> all;
    private int fx = 1;
    private int size;
    private Coordinate dian;

    private int c = 18;

    public Snake(ArrayList<Coordinate> coordinateList){
        this.coordinateList = coordinateList;
        size = coordinateList.size();
        all = getDians();
        createDian();
    }

    public boolean move(){
        Coordinate cd = coordinateList.get(size - 1);
        Coordinate move = cd.move(fx);
        if (dian.equals(move)){
            //吃到了点
            size++;
            coordinateList.add(move);
            createDian();
            return true;
        }
        int x = move.getX();
        int y = move.getY();
        coordinateList.remove(0);
        if(coordinateList.contains(move)||x <0||x>c||y<0||y>c){
            //吃到了自己 或撞了墙
            return false;
        }
        coordinateList.add(move);
        return true;
    }


    private void createDian(){
        //点生产效率
        if (size < 310){
            while (true){
                Coordinate coordinate = all.get((int) (c * c * Math.random()));
                if (!coordinateList.contains(coordinate)){
                    dian = (Coordinate) coordinate.clone();
                    return;
                }
            }
        }else if (size <c*c){
            ArrayList<Coordinate> clone = (ArrayList<Coordinate>) all.clone();
            clone.remove(coordinateList);
            Coordinate coordinate = clone.get((int) (clone.size() * Math.random()));
            dian = (Coordinate) coordinate.clone();
            return;
        }
        dian = null;
    }
   private ArrayList<Coordinate> getDians(){
       ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (int x=0;x<c+1;x++){
            for (int y=0;y<c+1;y++){
                coordinates.add(new Coordinate(x, y));
            }

        }
        return coordinates;
   }



    // 获取与赋值
    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(ArrayList<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    public int getFx() {
        return fx;
    }

    public void setFx(int fx) {
        int cha = this.fx -fx;
        if (cha == -2 || cha == 2)
            return;
        this.fx = fx;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Coordinate getDian() {
        return dian;
    }

    public void setDian(Coordinate dian) {
        this.dian = dian;
    }
}
