package domain;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private ArrayList<Coordinate> coordinateList;
    private ArrayList<Coordinate> all;
    private int fx = 1;
    private int yfx = 1;
    private int size;
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


    public void autoMove() {
        Coordinate coordinate = coordinateList.get(size - 1);

        if (fengbi())
            return;
        if (dian.getX() > coordinate.getX()) {
            if (qie(coordinate.move(1), 1)) {
                return;
            }
        } else if (dian.getX() < coordinate.getX()) {
            if (qie(coordinate.move(3), 3)) {
                return;
            }
        }
        if (dian.getY() > coordinate.getY()) {
            if (qie(coordinate.move(2), 2)) {
                return;
            }
        } else if (dian.getY() < coordinate.getY()) {
            if (qie(coordinate.move(4), 4)) {
                return;
            }
        }
        if (qie(coordinate.move(1), 1)) {
            return;
        }
        if (qie(coordinate.move(2), 2)) {
            return;
        }
        if (qie(coordinate.move(3), 3)) {
            return;
        }
        qie(coordinate.move(4), 4);

    }

    private boolean qie(Coordinate move1, int i) {
        ArrayList<Coordinate> clone = (ArrayList<Coordinate>)coordinateList.clone();
        if ((i>2?(i-2):i+2) == fx)
            return false;
        if (!move1.equals(dian)){
            clone.remove(0);
        }
        if (clone.contains(move1)||move1.getX() < 0 || move1.getX() > c || move1.getY() < 0 || move1.getY() > c) {
            //吃到了自己 或撞了墙
            return false;
        }
        setFx(i);
        return true;
    }


    private boolean fengbi(){
        ArrayList<Coordinate> clone = (ArrayList<Coordinate>)coordinateList.clone();
        Coordinate coordinate = clone.get(size - 1);
        Coordinate move1 = coordinate.move(fx);
        int n;
        if ((n = clone.indexOf(move1))!=-1){
            int nfx = (fx+1)>4?(fx-3):(fx+1);
            Coordinate move = move1.move(nfx);
            int i = clone.indexOf(move);
            int tfx =nfx>2?(nfx-2):(nfx+2);
            if (i != -1){
                if (i>n&&!clone.contains(coordinate.move(tfx))){
                    nfx = tfx;
                }
            }else {
                move = move1.move(tfx);
                i = clone.indexOf(move);
                if (i<n&&!clone.contains(coordinate.move(tfx))){
                    nfx = tfx;
                }
            }
            setFx(nfx);
            return true;
        }
        return false;
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

    public int getFx() {
        return fx;
    }

    public void setFx(int fx) {
        this.yfx = fx;
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
