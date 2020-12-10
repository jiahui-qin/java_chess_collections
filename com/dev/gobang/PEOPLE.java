package com.dev.gobang;

import java.util.ArrayList;
import java.util.List;

public class PEOPLE {
    private String name;
    private List<CHESS> chessList;
    private String status;

    public PEOPLE(String n){
        name = n;
        chessList = new ArrayList<>();
    }

    public void addChess(CHESS chess){
        chessList.add(chess);
    }

    public List<CHESS> getChess() {
        return chessList;
    }

    public String getName(){return this.name;}

}
