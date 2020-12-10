package com.dev.gobang;

import java.util.List;

public class WALL {

    private int length;
    private int width;

    public WALL(int l, int w) {
        length = l;
        width = w;
    }
    public int getLength(){return this.length;}
    public int getWidth(){return this.width;}

    public String drawWall(PEOPLE white, PEOPLE black){
        List<CHESS> whiteChess=  white.getChess();
        List<CHESS> blackChess = black.getChess();
        StringBuffer wall  = new StringBuffer();
        for (int i = 0; i<length; i++){
           for(int j = 0; j<width; j++){
               if (containChess(whiteChess, new CHESS(i, j))){
                   wall.append("B");
               }else if(containChess(blackChess, new CHESS(i, j))){
                   wall.append("W");
               }else {
                   wall.append("+");
               }
           }
           wall.append("\n\r");
        }
        return wall.toString();
    }

    public boolean containChess(List<CHESS> chessList, CHESS newChess){
        for (CHESS chess: chessList) {
            if (chess.getX_axis() == newChess.getX_axis() & chess.getY_axis() == newChess.getY_axis()){
                return true;
            }
        }
        return false;
    }

}
