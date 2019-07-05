package domain;

import java.util.ArrayList;
import java.util.List;

//第三版本贪吃蛇 暴力满屏版 暴力美学 能吃满
public class Snake3 extends SnakeParent{

    public Snake3(ArrayList<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
        size = coordinateList.size();
        all = getDians();
        createDian();
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

}
