package domain;

import java.util.ArrayList;
import java.util.List;

//暴力满屏版 暴力美学
public class Snake3 {
    private ArrayList<Coordinate> coordinateList;
    private ArrayList<Coordinate> all;
    private int fx = 1;
    private int yfx = 1;
    private int size;
    private Coordinate dian;

    private int c = 18;

    public Snake3(ArrayList<Coordinate> coordinateList) {
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
        } else{
            ArrayList<Coordinate> clone = (ArrayList<Coordinate>) all.clone();
            clone.removeAll(coordinateList);
            Coordinate coordinate = clone.get((int) (clone.size() * Math.random()));
            dian = (Coordinate) coordinate.clone();
        }
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


    boolean one = true;

    public void autoMove() {
        Coordinate coordinate = coordinateList.get(size - 1);
        int x = coordinate.getX();
        int y = coordinate.getY();
        if (y == 0 && x == c) {
            setFx(3);
            return;
        }
        if (x == c) {
            setFx(4);
            return;
        }
        if (y < c - 1) {
            if (y % 2 == 0 && fx != 1) {
                if (x == 0) {
                    setFx(2);
                    return;
                }
                setFx(3);
                return;
            }
            if (y % 2 == 1 && fx != 3) {
                if (x == c - 1) {
                    setFx(2);
                    return;
                }
                setFx(1);
                return;
            }
            setFx(2);
        } else {
            if (x < c - 1) {
                if (x % 2 == 0) {
                    if (y == c - 1)
                        setFx(2);
                    else
                        setFx(1);
                } else {
                    if (y == c - 1)
                        setFx(1);
                    else
                        setFx(4);
                }
            } else {
                if (x == c - 1 && y == c)
                    if (one) {
                        one=false;
                        setFx(1);
                        return;
                    } else {
                        one=true;
                        setFx(4);
                        return;
                    }
                setFx(1);
            }
        }
    }

    // 获取与赋值
    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(ArrayList<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }


    //修正方向超出值
    private int amend(int fx) {
        fx = fx > 4 ? (fx - 4) : fx;
        fx = fx < 1 ? (fx + 4) : fx;
        return fx;
    }

    public int getFx() {
        return fx;
    }

    public void setFx(int fx) {
        int amend = amend(fx);
        this.yfx = amend;
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
