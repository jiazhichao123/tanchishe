package domain;

import java.util.ArrayList;
import java.util.List;
/**
 * 第一个自动吃失败的版本 纪念用 无任何作用  最长长度110
 */
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
            if (qie(coordinate, 1)) {
                return;
            }
        } else if (dian.getX() < coordinate.getX()) {
            if (qie(coordinate, 3)) {
                return;
            }
        }
        if (dian.getY() > coordinate.getY()) {
            if (qie(coordinate, 2)) {
                return;
            }
        } else if (dian.getY() < coordinate.getY()) {
            if (qie(coordinate, 4)) {
                return;
            }
        }
        int newFx= dian.getX()>9?3:1;
        int newFy= dian.getY()>9?4:2;
        if (qie(coordinate, newFx)) {
            return;
        }
        if (qie(coordinate, newFy)) {
            return;
        }
        if (qie(coordinate, newFx>2?(newFx-2):(newFx+2))) {
            return;
        }
        qie(coordinate, newFy>2?(newFy-2):(newFy+2));

    }

    private boolean qie(Coordinate move1, Integer i) {
        ArrayList<Coordinate> clone = (ArrayList<Coordinate>)coordinateList.clone();
        i = amend(i);
        if ((i>2?(i-2):i+2) == fx)
            return false;
        if (!move1.equals(dian)){
            clone.remove(0);
        }
        if (contains(clone,move1,i)) {
            //吃到了自己 或撞了墙
            return false;
        }
        setFx(i);
        return true;
    }


    //封闭环境判定
    private boolean fengbi(){
        Coordinate coordinate = coordinateList.get(size - 1);
        if (size < 5)
            return false;
        Coordinate move = coordinate.move(fx);
        int x = coordinate.getX();
        int y = coordinate.getY();
        if (move.getX()<0||move.getX()>c){
            boolean a =false;
            for (int i = 0;i<size-4;i++){
                Coordinate coor = coordinateList.get(i);
                if (coor.hasX(x)||coor.hasY(0)||coor.hasY(c)){
                   if (compute(coordinateList.subList(i, size))){
                      return true;
                   }
                }
            }
        }else if (move.getY()<0||move.getY()>c){
            for (int i = 0;i<size-4;i++){
                Coordinate coor = coordinateList.get(i);
                if (coor.hasY(y)||coor.hasX(0)||coor.hasX(c)){
                    if (compute(coordinateList.subList(i, size))){
                        return true;
                    }
                }
            }
        }
        if (size < 7)
            return false;
        int i = coordinateList.indexOf(move);
        if (i>0&&i<size-6){
           return  compute(coordinateList.subList(i, size));
        }
        int ui = coordinateList.indexOf(move.move(fx+1));
        int di = coordinateList.indexOf(move.move(fx-1));
        if (ui>0&&di>0&&ui<size-7&&di<size-7){
            int max = ui>di?ui:di;
            return compute(coordinateList.subList(max, size));
        }
        if (ui>0&&ui<size-7){
            return compute(coordinateList.subList(ui, size));
        }
        if (di>0&&di<size-7){
            return compute(coordinateList.subList(di, size));
        }
        return false;
//        Coordinate move1 = coordinate.move(fx);
//        int n;
//        if ((n = clone.indexOf(move1))!=-1){
//            int nfx = (fx+1)>4?(fx-3):(fx+1);
//            Coordinate move = move1.move(nfx);
//            int i = clone.indexOf(move);
//            int tfx =nfx>2?(nfx-2):(nfx+2);
//            if (i != -1){
//                if (i>n&&!contains(clone,coordinate,tfx)){
//                    nfx = tfx;
//                }
//            }
//            else {
//                move = move1.move(tfx);
//                i = clone.indexOf(move);
//                if (i<n&&!contains(clone,coordinate,tfx)){
//                    nfx = tfx;
//                }
//            }
//            if (contains(clone,coordinate,nfx)){
//                setFx(tfx);
//            }else {
//                setFx(nfx);
//            }

//            return true;
//        }

    }
//    private boolean fengbiCheck( ArrayList<Coordinate> clone,Coordinate coordinate){
//
//    }


    //算封闭环的面积
    private Boolean compute(List<Coordinate> clone){
        int minX = c;
        int maxX = 0;
        int minY = c;
        int maxY = 0;
        for (int i=0;i<clone.size();i++){
            Coordinate coordinate = clone.get(i);
            int x = coordinate.getX();
            int y = coordinate.getY();
            minX = minX > x?x:minX;
            maxX = maxX < x?x:maxX;
            minY = minY > y?y:minY;
            maxY = maxY < y?y:maxY;
        }
        List<Coordinate> list = new ArrayList<>();
        for (int x = minX;x<=maxX;x++){
            for (int y = minY;y<=maxY;y++){
                list.add(new Coordinate(x,y));
            }
        }
        if (minX !=0){
            for (int y = minY;y<=maxY;y++){
                for (int x = minX;x<=maxX;x++){
                    Coordinate coordinate = new Coordinate(x, y);
                    if (clone.contains(coordinate))
                        break;
                    list.remove(coordinate);
                }
            }
        }
        if (maxX !=c){
            for (int y = minY;y<=maxY;y++){
                for (int x = maxX;x>=maxY;x--){
                    Coordinate coordinate = new Coordinate(x, y);
                    if (clone.contains(coordinate))
                        break;
                    list.remove(coordinate);
                }
            }
        }
        if (minY !=0){
            for (int x = minX;x<=maxX;x++){
                for (int y = minY;y<=maxY;y++){
                    Coordinate coordinate = new Coordinate(x, y);
                    if (clone.contains(coordinate))
                        break;
                    list.remove(coordinate);
                }
            }
        }
        if (maxY !=c){
            for (int x = minX;x<=maxX;x++){
                for (int y = maxY;y>=minY;y--){
                    Coordinate coordinate = new Coordinate(x, y);
                    if (clone.contains(coordinate))
                        break;
                    list.remove(coordinate);
                }
            }
        }
//        if ( list.size()>size)
//            return false;

        //切换方向
        Coordinate coordinate = clone.get(clone.size() - 1);
        if ((!list.contains(coordinate.move(fx)))&&qie(coordinate,fx))
            return true;
        if ((!list.contains(coordinate.move(fx+1)))&&qie(coordinate,fx+1))
            return true;
        return (!list.contains(coordinate.move(fx - 1))) && qie(coordinate, fx - 1);
    }

//    public boolean contains(){
//
//    }

    //死了 返回true
    public boolean contains(ArrayList<Coordinate> clone, Coordinate coordinate ,int fxn ){
        Coordinate move1 = coordinate.move(fxn);
        if (!contains(clone,move1)){
            if (!move1.equals(dian)){
                clone.remove(0);
            }
            int nfx = fxn==4?1:(fxn+1);
            int nfx1 = fxn==1?4:(fxn-1);
            return contains(clone,move1.move(nfx))&&contains(clone,move1.move(nfx1))&&contains(clone,move1.move(fxn));
        }
        return true;
    }



    //死了 返回true
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
