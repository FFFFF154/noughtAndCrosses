package ru.alexseev.tictactae;//import java.security.cert.TrustAnchor;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Test {
    public static final int SIZE_DESK = 3;
    public static final Scanner SCANNER = new Scanner(System.in);
    public static char[][] desk = new char[SIZE_DESK][SIZE_DESK];
    // char[][] desk 'X', 'O'

    public static void fillingDesk(){
        for (int i =0; i<SIZE_DESK; i++){
            for (int j =0; j<SIZE_DESK; j++){
                desk[i][j] = ' ';
            }
        }
    }

    public static boolean checkDraw(){
        for (int i=0;i<SIZE_DESK;i++){
            for (int j=0;j<SIZE_DESK;j++){
                if (desk[i][j] == ' '){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean botTurnDefense(){
        for (int i=0; i<SIZE_DESK-1;i++){
            for (int j=0; j<SIZE_DESK-1; j++){
                if (desk[i][j] == desk[i][j+1] && j == 0 && desk[i][j] != ' '){
                    desk[i][j+2] = '0';
                    return true;
                } else if (desk[i][j] == desk[i][j+1] && j == 1 && desk[i][j] != ' '){
                    desk[i][j-1] = '0';
                    return true;
                }
                if (desk[i][j] == desk[i+1][j] && i == 0 && desk[i][j] != ' '){
                    desk[i+2][j] = '0';
                    return true;
                } else if(desk[i][j] == desk[i+1][j] && i == 1 && desk[i][j] != ' '){
                    desk[i-1][j] = '0';
                    return true;
                }
                if (desk[i][i] == desk[i+1][i+1] && i == 0 && desk[i][i] != ' '){
                    desk[i+2][i+2] = '0';
                    return true;
                } else if(desk[i][i] == desk[i+1][i+1] && i == 1 && desk[i][j] != ' '){
                    desk[i-1][i-1] = '0';
                    return true;
                }
                if (desk[0][2] == desk[1][1] && desk[0][2] != ' '){
                    desk[2][0] = '0';
                    return true;
                } else if(desk[1][1] == desk[2][0] && desk[1][1] != ' '){
                    desk[0][2] = '0';
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean botTurnCorners(){
        int x2, y2;
        if (desk[1][1] != 'X' && (desk[1][1] != '0')){
            desk[1][1] = '0';
        }else {
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
                if (desk[x2][y2] != 'X' && desk[x2][y2] != '0'){
                    desk[x2][y2] = '0';
                    return true;
                } else {
                    break;
                }

            }
        }
        return false;
    }

    public static boolean checkWin(){
        for (int i=0; i<SIZE_DESK; i++){
            if ((desk[i][0] == desk[i][1]) && (desk[i][0] == desk[i][2]) && (desk[i][0] != ' ')){
                return false;
            }
        }
        for (int i=0; i<SIZE_DESK; i++){
            if ((desk[0][i] == desk[1][i]) && (desk[0][i] == desk[2][i]) && (desk[0][i] != ' ')){
                return false;
            }
        }
        if (desk[0][0] == desk[1][1] && (desk[0][0] == desk[2][2]) && (desk[0][0] != ' ')){
            return false;
        }
        if (desk[2][0] == desk[1][1] && (desk[2][0] == desk[0][2]) && (desk[2][0] != ' ')){
            return false;
        }
        return true;
    }

    public static void botTurn(){
        if (!botTurnDefense()){
            botTurnCorners();
        }
        printDesk();
    }

    public static boolean firstMove(){
        boolean flag;
        //Scanner inp = new Scanner(System.in);
        System.out.println("Будете ходить первым?");
        System.out.println("Y - \"Yes\", N - \"No\"");
        while (true){
            String scan = SCANNER.nextLine();
            if (scan.equals("Y")){
                flag = true;
                break;
            } else if (scan.equals("N")){
                flag = false;
                break;
            } else{
                System.out.println("Введите Y или N");
            }
        }

        return flag;
    }

    public static void printDesk(){
        for (int i=0; i<SIZE_DESK; i++){
            for (int j=0; j<SIZE_DESK; j++){
                System.out.print("|" + desk[i][j] + "|");
            }
            System.out.println("\n");
        }
        System.out.println("---------");
    }

    public static void playerTurn(){
        //Scanner s = new Scanner(System.in);
        System.out.println("Введите координату поля");
        int coordinate = parseInt(SCANNER.nextLine().replace(" ", ""));
        //System.out.println(coordinate);
        int x1 = coordinate / 10;
        int y1 = coordinate % 10;
        desk[3-x1][y1-1] = 'X';

    }
    public static void main(String[] args) {
        //boolean flag1 = firstMove();
        //int ord = 1;
        fillingDesk();
        while (checkWin()){
            playerTurn();
            botTurn();
            if (!checkDraw()){
                System.out.println("Ничья");
                break;
            }
        }
        //TODO вывод победителя
        System.out.println("GAME OVER!");
        printDesk();
    }
}
