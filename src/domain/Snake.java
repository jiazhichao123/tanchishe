package domain;

import java.util.*;

public class Snake {
    //蛇身子
    private ArrayList<Coordinate> coordinateList;
    //全屏的坐标
    private ArrayList<Coordinate> all;
    //当前方向
    private int fx = 1;
    //下次方向
    private int yfx = 1;
    //长度
    private int size;
    //被吃的点
    private Coordinate dian;

    private int c = 18;

    public Snake(ArrayList<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
        size = coordinateList.size();
        all = getDians();
        createDian();
    }

    public boolean move() {
        int cha = yfx - fx;
        if (cha != -2 && cha != 2) {
            fx = yfx;
        }

        Coordinate cd = coordinateList.get(size - 1);
        Coordinate move = cd.move(fx);
        if (dian.equals(move)) {
            //吃到了点
            size++;
            coordinateList.add(move);
            createDian();
            return true;
        }
        int x = move.getX();
        int y = move.getY();
        coordinateList.remove(0);
        if (coordinateList.contains(move) || x < 0 || x > c || y < 0 || y > c) {
            //吃到了自己 或撞了墙
            return false;
        }
        coordinateList.add(move);
        return true;
    }


    private void createDian() {
        //点生产效率
        if (size < 310) {
            while (true) {
                Coordinate coordinate = all.get((int) (c * c * Math.random()));
                if (!coordinateList.contains(coordinate)) {
                    dian = (Coordinate) coordinate.clone();
                    return;
                }
            }
        } else if (size < c * c) {
            ArrayList<Coordinate> clone = (ArrayList<Coordinate>) all.clone();
            clone.remove(coordinateList);
            Coordinate coordinate = clone.get((int) (clone.size() * Math.random()));
            dian = (Coordinate) coordinate.clone();
            return;
        }
        dian = null;
    }

    private ArrayList<Coordinate> getDians() {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (int x = 0; x < c + 1; x++) {
            for (int y = 0; y < c + 1; y++) {
                coordinates.add(new Coordinate(x, y));
            }

        }
        return coordinates;
    }


    private ArrayList<Coordinate> route;

    public void autoMove() {
        if (route == null || route.size() == 0){
            route();
        }
        Coordinate coordinate = coordinateList.get(size - 1);
        Coordinate coordinate1 = route.get(0);
        setFx(coordinate.getfx(coordinate1));
        route.remove(0);
    }

//    private boolean qie(Coordinate move1, Integer i) {
//        ArrayList<Coordinate> clone = (ArrayList<Coordinate>)coordinateList.clone();
//        if ((i>2?(i-2):i+2) == fx)
//            return false;
//        if (!move1.equals(dian)){
//            clone.remove(0);
//        }
//        if (contains(clone,move1,i)) {
//            //吃到了自己 或撞了墙
//            return false;
//        }
//        setFx(i);
//        return true;
//    }


    public void route(){
        Coordinate coordinate = coordinateList.get(size - 1);
        ArrayList<Coordinate> clone =(ArrayList<Coordinate>) coordinateList.clone();
        clone.remove(size -1);
        int nwfx =fx;
        route = new ArrayList<>();
        while (true){
            if (coordinate.equals(dian))
                break;
            int getfx = coordinate.getfx(dian);
            clone.remove(0);
            if (getfx ==  amend(nwfx+2)){
                nwfx =amend(fx+1);
            }else {
                nwfx =getfx;
            }
            clone.add(coordinate);
            coordinate = coordinate.move(nwfx);
            route.add(coordinate);
        }
    }


    public boolean contains(ArrayList<Coordinate> clone, Coordinate coordinate ,int fxn ){
        Coordinate move1 = coordinate.move(fxn);
        if (!contains(clone,move1)){
            int nfx = fxn==4?1:(fxn+1);
            int nfx1 = fxn==1?4:(fxn-1);
            return contains(clone,move1.move(nfx))&&contains(clone,move1.move(nfx1))&&contains(clone,move1.move(fxn));
        }
        return true;
    }

    public boolean contains(ArrayList<Coordinate> clone, Coordinate move1){
        return (clone.contains(move1)||move1.getX() < 0 || move1.getX() > c || move1.getY() < 0 || move1.getY() > c);
    }
//    public void chose(int i,Coordinate coordinate){
//        if (!coordinateList.contains(coordinate.move(i))){
//            setFx(i);
//            return;
//        }else {
//            int fx = (i+2)>4?(i-4):(i+2);
//            setFx();
//        }
//    }

    // 获取与赋值
    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(ArrayList<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }


    //修正方向超出值
    private int amend(int fx){
        fx = fx > 4?(fx-4):fx;
        fx = fx < 1?(fx+4):fx;
        return fx;
    }

    public int getFx() {
        return fx;
    }

    public void setFx(int fx) {
        this.yfx = amend(fx);
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
