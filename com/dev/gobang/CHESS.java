package com.dev.gobang;


import java.util.ArrayList;
import java.util.List;

public class CHESS {
    private int x_axis;
    private int y_axis;

    public CHESS(int x, int y){
        x_axis = x;
        y_axis = y;
    }

    public List<Integer> getChess(){
        List chessList = new ArrayList();
        chessList.add(x_axis);
        chessList.add(y_axis);
        return chessList;
    }

    public boolean equal(CHESS newChess){
        if (this.x_axis == newChess.x_axis & this.y_axis == newChess.y_axis){
            return true;
        }
        return false;
    }

    public int getX_axis(){
        return this.x_axis;
    }
    public int getY_axis(){
        return this.y_axis;
    }

}
