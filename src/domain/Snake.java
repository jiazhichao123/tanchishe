package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 第四个自动吃版本 避开最近死亡 第一个版本升级版
 */
public class Snake extends SnakeParent {
    public Snake(ArrayList<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
        size = coordinateList.size();
        all = getDians();
        createDian();
    }

    @Override
    public void autoMove() {
        Coordinate coordinate = coordinateList.get(size - 1);
        if (fengbi())
            return;
        if (dian.getX() > coordinate.getX()) {
            if (setRunfx(1)) {
                return;
            }
        } else if (dian.getX() < coordinate.getX()) {
            if (setRunfx(3)) {
                return;
            }
        }
        if (dian.getY() > coordinate.getY()) {
            if (setRunfx(2)) {
                return;
            }
        } else if (dian.getY() < coordinate.getY()) {
            if (setRunfx(4)) {
                return;
            }
        }
        int newFx= dian.getX()>9?3:1;
        int newFy= dian.getY()>9?4:2;
        if (setRunfx(newFx)) {
            return;
        }
        if (setRunfx(newFy)) {
            return;
        }
        if (setRunfx(newFx>2?(newFx-2):(newFx+2))) {
            return;
        }
        setRunfx(newFy>2?(newFy-2):(newFy+2));
    }

    private boolean setRunfx(Integer nfx){
        int one = nfx -fx;
        if (one == 2 || one == -2)
            return false;
        if (run(nfx,coordinateList,1))
            return false;
        setFx(nfx);
        return true;
    }

    //死了 返回true
    private boolean run(int fxn,ArrayList<Coordinate> coordinates,Integer time){
        ArrayList<Coordinate> clone = (ArrayList<Coordinate>)coordinates.clone();
        Coordinate coordinate = clone.get(clone.size() - 1);
        Coordinate move1 = coordinate.move(fxn);
        if (!contains(clone,move1)){
            if (!move1.equals(dian)){
                clone.remove(0);
            }
            int nfx = fxn==4?1:(fxn+1);
            int nfx1 = fxn==1?4:(fxn-1);
            if (time < 20){
                clone.add(move1);
                return run(nfx,clone,time+1)&&run(nfx1,clone,time+1)&&run(fxn,clone,time+1);
            }else {
                return contains(clone,move1.move(nfx1))&&contains(clone,move1.move(nfx1))&&contains(clone,move1.move(fxn));

            }
        }
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
        if ( list.size()>c*c-size- list.size())
            return false;

        //切换方向
        Coordinate coordinate = clone.get(clone.size() - 1);
        if ((!list.contains(coordinate.move(fx)))&&setRunfx(fx))
            return true;
        if ((!list.contains(coordinate.move(fx+1)))&&setRunfx(fx+1))
            return true;
        return (!list.contains(coordinate.move(fx - 1))) && setRunfx( fx - 1);
    }
    //死了 返回true
    public boolean contains(ArrayList<Coordinate> clone, Coordinate move1){
//        if ((move1.getX() == 0 || move1.getX() == c )&& (move1.getY() != 0 || move1.getY() !=c)){
//            if (clone.contains(new Coordinate(c-move1.getX(),move1.getY())))
//                return true;
//        }else if (move1.getY() == 0 || move1.getY() == c){
//            if (clone.contains(new Coordinate(move1.getX(),c-move1.getY())))
//                return true;
//        }
        return (clone.contains(move1)||move1.getX() < 0 || move1.getX() > c || move1.getY() < 0 || move1.getY() > c);
    }
}
