//import java.security.cert.TrustAnchor;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class test  {
    public static final int SIZE_DESK = 3;
    public static int[][] desk = new int[SIZE_DESK][SIZE_DESK];

    public static boolean checkWin(){
        boolean flag = true;
        for (int i=0; i<SIZE_DESK; i++){
            if ((desk[i][0] == desk[i][1]) && (desk[i][0] == desk[i][2]) && (desk[i][0] != 0)){
                flag = false;
            }
        }
        for (int i=0; i<SIZE_DESK; i++){
            if ((desk[0][i] == desk[1][i]) && (desk[0][i] == desk[2][i]) && (desk[0][i] != 0)){
                flag = false;
            }
        }
        if (desk[0][0] == desk[1][1] && (desk[0][0] == desk[2][2]) && (desk[0][0] != 0)){
            flag = false;
        }
        if (desk[2][0] == desk[1][1] && (desk[2][0] == desk[0][2]) && (desk[2][0] != 0)){
            flag = false;
        }
        return flag;
    }

    public static void botTurn(){
        int x2, y2;
        for (int i=0; i<SIZE_DESK-1;i++){
            for (int j=0; j<SIZE_DESK-1; j++){
                if (desk[i][j] == desk[i][j+1] && j == 0 && desk[i][j] != 0){
                    desk[i][j+2] = 2;
                    break;
                } else if (desk[i][j] == desk[i][j+1] && j == 1 && desk[i][j] != 0){
                    desk[i][j-1] = 2;
                    break;
                }
                if (desk[i][j] == desk[i+1][j] && i == 0 && desk[i][j] != 0){
                    desk[i+2][j] = 2;
                    break;
                } else if(desk[i][j] == desk[i+1][j] && i == 1 && desk[i][j] != 0){
                    desk[i-1][j] = 2;
                    break;
                }
            }
        }

        if (desk[1][1] != 1 && (desk[1][1] != 2)){
            desk[1][1] = 2;
        } else {
            while (true){
                if (Math.random()*2 > 1){
                    x2 = 2;
                } else{
                    x2 = 0;
                }
                if (Math.random()*2 > 1){
                    y2 = 2;
                } else{
                    y2 = 0;
                }
                if (desk[x2][y2] != 1 && desk[x2][y2] != 2){
                    desk[x2][y2] = 2;
                    break;
                }

            }
        }
        printDesk();
    }

    public static boolean firstMove(){
        boolean flag;
        Scanner inp = new Scanner(System.in);
        System.out.println("Будете ходить первым?");
        System.out.println("Y - \"Yes\", N - \"No\"");
        while (true){
            String ans = inp.nextLine();
            if (ans.equals("Y")){
                flag = true;
                break;
            } else if (ans.equals("N")){
                flag = false;
                break;
            } else{
                System.out.println("Введите Y или N");
            }
        }

        return flag;
    }

    public static void printDesk(){
        for (int[] arg:desk) {
            for (int arg1:arg){
                System.out.print("|" + arg1 + "|");
            }
            System.out.println("\n");
        }
        System.out.println("---------");
    }

    public static void playerTurn(){
        Scanner s = new Scanner(System.in);
        System.out.println("Введите координату поля");
        int coordinate = parseInt(s.nextLine().replace(" ", ""));
        //System.out.println(coordinate);
        int x1 = coordinate / 10;
        int y1 = coordinate % 10;
        desk[3-x1][y1-1] = 1;

    }
    public static void main(String[] args) {
        //boolean flag1 = firstMove();
        int ord = 1;
        while (checkWin()){
            playerTurn();
            botTurn();
        }
        System.out.println("GAME OVER!");
        printDesk();
    }
}
