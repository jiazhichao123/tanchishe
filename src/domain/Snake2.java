package domain;

import java.util.*;
//第二版本贪吃蛇 路线规划 寻路 未完成 仅开头
public class Snake2 extends SnakeParent{

    public Snake2(ArrayList<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
        size = coordinateList.size();
        all = getDians();
        createDian();
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

}
