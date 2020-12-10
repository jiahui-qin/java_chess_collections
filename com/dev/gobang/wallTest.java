package com.dev.gobang;

import java.util.*;

public class wallTest {
    public static void main(String[] args) {
        WALL wall = new WALL(20,20);
        Scanner startPlay = new Scanner(System.in);
        System.out.println("请输入第一个玩家的名字");
        PEOPLE player1 = new PEOPLE(startPlay.next());
        System.out.println("请输入第二个玩家的名字");
        PEOPLE player2 = new PEOPLE(startPlay.next());
        while (true){
            System.out.println("现在由玩家 "+ player1.getName() +" 落子");
            int i1 = startPlay.nextInt();
            int j1 = startPlay.nextInt();
            while(!chessLimit(new CHESS(i1,j1),wall,player1,player2)){
                System.out.println("落子失败，请重新落子");
                i1 = startPlay.nextInt();
                j1 = startPlay.nextInt();
            }
            if(win(new CHESS(i1,j1), player1.getChess())){
                System.out.println("player1 " + player1.getName() + " win!");
                System.exit(1);
            }
            player1.addChess(new CHESS(i1,j1));
            System.out.println(wall.drawWall(player1,player2));

            System.out.println("现在由玩家 "+ player2.getName() +" 落子");
            int i2 = startPlay.nextInt();
            int j2 = startPlay.nextInt();
            while(!chessLimit(new CHESS(i2,j2),wall,player1,player2)){
                System.out.println("落子失败，请重新落子");
                i2 = startPlay.nextInt();
                j2 = startPlay.nextInt();
            }
            if(win(new CHESS(i2,j2), player2.getChess())){
                System.out.println("player2 " + player2.getName() + " win!");
                System.exit(1);
            }
            player2.addChess(new CHESS(i2,j2));
            System.out.println(wall.drawWall(player1,player2));
        }

    }

    private static boolean chessLimit(CHESS chess, WALL wall, PEOPLE people1, PEOPLE people2){
        //1. 不能重复落子
        if (wall.containChess(people1.getChess(), chess) || wall.containChess(people2.getChess(), chess)){
            return false;
        }
        //2. 落子不得超出棋盘
        if(chess.getX_axis() >= wall.getLength()  || chess.getY_axis() >= wall.getWidth() || chess.getX_axis() < 0 || chess.getY_axis() < 0){
            return false;
        }
        return true;
    }
    private static boolean checkContinuityChess(CHESS newChess, List<Integer> xList) {
        xList.add(newChess.getX_axis());
        Collections.sort(xList);
        if(xList.size() > 4) {
            for (int i = 0; i < xList.size() - 4; i++) {
                if (xList.get(i + 1) - xList.get(i) == 1 && xList.get(i + 2) - xList.get(i + 1) == 1 && xList.get(i + 3) - xList.get(i + 2) == 1 && xList.get(i + 4) - xList.get(i + 3) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean win(CHESS newChess, List<CHESS> chessList){
        //判断加入新棋子之后，player people是否获胜
        //1. 横着有连续5个棋子存在：
        List<Integer> xList = new ArrayList<>();
        for (CHESS chess:chessList) {
            if (chess.getX_axis() == newChess.getX_axis()){
                xList.add(chess.getY_axis());
            }
        }
        xList.add(newChess.getY_axis());
        if (checkContinuityChess(newChess, xList)) return true;
        //2. 判断竖着又连续5个棋子存在
        xList.clear();
        for (CHESS chess:chessList) {
            if (chess.getY_axis() == newChess.getY_axis()){
                xList.add(chess.getX_axis());
            }
        }
        xList.add(newChess.getX_axis());
        if (checkContinuityChess(newChess, xList)) return true;

        //3. 判断倾斜方向 -- 正45°，x+y和都相等
        xList.clear();
        for (CHESS chess:chessList) {
            if (chess.getX_axis()+chess.getY_axis() == newChess.getY_axis()+ newChess.getX_axis()){xList.add(chess.getX_axis());}
        }
        xList.add(newChess.getX_axis());
        if (checkContinuityChess(newChess, xList)) return true;
        //4.判断 负45°方向
        xList.clear();
        for(CHESS chess:chessList){
            if(chess.getX_axis() - newChess.getX_axis() == chess.getY_axis() - newChess.getY_axis()){xList.add(chess.getX_axis());}
        }
        xList.add(newChess.getX_axis());
        if (checkContinuityChess(newChess, xList)) return true;
        return false;
    }


}
