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
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWin(char sym){
        for (int i=0; i<SIZE_DESK; i++){
            if ((desk[i][0] == sym) && (sym == desk[i][2]) && (desk[i][1] == sym)){
                return true;
            }
        }
        for (int i=0; i<SIZE_DESK; i++){
            if ((desk[0][i] == sym) && (sym == desk[2][i]) && (desk[1][i] == sym)){
                return true;
            }
        }
        if ((desk[0][0] == sym) && (desk[1][1] == sym) && (desk[2][2] == sym)){
            return true;
        }
        if ((desk[2][0] == sym) && (sym == desk[0][2]) && (desk[1][1] == sym)){
            return true;
        }
        return false;
    }

    public static void botTurn(char sym1, char sym2){
        int x2, y2;
        if (desk[1][1] != sym1 && (desk[1][1] != sym2)){
            desk[1][1] = sym2;
            //System.out.println("Центр");
        }else {
            for (int i = 0; i < SIZE_DESK; i++) {
                for (int j = 0; j < SIZE_DESK; j++) {
                    if ((desk[i][j] != sym1) && (desk[i][j] != sym2)) {
                        desk[i][j] = sym2;
                        //System.out.println("Замена sym2");
                        if (checkWin(sym2)) {
                            return;
                        } else {
                            desk[i][j] = ' ';
                            //System.out.println("Замена на пробел1");
                        }
                        desk[i][j] = sym1;
                        //System.out.println("Замена на sym1");
                        if (checkWin(sym1)) {
                            desk[i][j] = sym2;
                            return;
                        } else {
                            desk[i][j] = ' ';
                            //System.out.println("Замена на пробел2");
                        }
                    }
                }
            }
            while (true){
                x2 = (int)(Math.random()*3);
                y2 = (int)(Math.random()*3);
                if ((desk[x2][y2] != sym1) && (desk[x2][y2] != sym2)){
                    desk[x2][y2] = sym2;
                    //System.out.println("Рандом");
                    break;
                }
                if (checkDraw()){
                    break;
                }
            }
        }

    }

    public static boolean firstMove(){
        //Scanner inp = new Scanner(System.in);
        System.out.println("Будете ходить первым?");
        System.out.println("Y - \"Yes\", N - \"No\"");
        while (true){
            String scan = SCANNER.nextLine();
            if (scan.equals("Y")){
                return true;
            } else if (scan.equals("N")){
                return false;
            } else{
                System.out.println("Введите Y или N");
            }
        }
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

    public static void playerTurn(char sym){
        //Scanner s = new Scanner(System.in);
        System.out.println("Введите координату поля");
        int coordinate = parseInt(SCANNER.nextLine().replace(" ", ""));
        //System.out.println(coordinate);
        int x1 = coordinate / 10;
        int y1 = coordinate % 10;
        desk[3-x1][y1-1] = sym;

    }
    public static void main(String[] args) {
        char symPlayer = 'X';
        char symBot = '0';
        fillingDesk();
        while(true) {
            playerTurn(symPlayer);
            if (checkWin(symPlayer)) {
                System.out.println("Победил игрок");
                break;
            }
            botTurn(symPlayer, symBot);
            printDesk();
            if (checkWin(symBot)) {
                System.out.println("Победил компьютер");
                break;
            }
            if (checkDraw()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра окончена");
        printDesk();
    }
}
