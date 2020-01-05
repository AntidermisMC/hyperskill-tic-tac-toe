package tictactoe;

import java.util.Scanner;

public class Main {

    static void printBoard(char[][] board){
        System.out.println("---------");
        for (int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println('|');
        }
        System.out.println("---------");
    }

    static char checkDiagonalLeft(char[][] board){
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]){
            return board[0][2];
        }
        else {
            return '_';
        }
    }

    static char checkDiagonalRight(char[][] board){
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]){
            return board[0][0];
        }
        else {
            return '_';
        }
    }

    static char checkAllDiagonals(char[][] board){
        char diag1 = checkDiagonalLeft(board);
        char diag2 = checkDiagonalRight(board);
        if (diag1 == diag2){
            return diag1;
        }
        else {
            if (diag1 == '_'){
                return diag2;
            }
            else if (diag2 == '_'){
                return diag1;
            }
            else {
                return '?';
            }
        }
    }

    static char checkLine(char[][] board, int line){
        if (board[line][0] == board[line][1] && board[line][0] == board[line][2]){
            return board[line][0];
        }
        else {
            return '_';
        }
    }

    static char checkCol(char[][] board, int col){
        if (board[0][col] == board[1][col] && board[0][col] == board[2][col]){
            return board[0][col];
        }
        else {
            return '_';
        }
    }

    static boolean checkNumber(char[][] board){
        int x = 0;
        int o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X'){
                    x++;
                }
                else if (board[i][j] == 'O'){
                    o++;
                }
            }
        }
        return x == o || x == o + 1 || x == o - 1;
    }

    static char checkAllCols(char[][] board){
        char Col0 = checkCol(board, 0);
        char Col1 = checkCol(board, 1);
        char Col2 = checkCol(board, 2);
        if (Col0 == '_' && Col1 == '_'){
            return Col2;
        }
        else if (Col0 == '_' && Col2 == '_'){
            return Col1;
        }
        else if (Col2 == '_' && Col1 == '_'){
            return Col0;
        }
        else {
            return '?';
        }
    }

    static char checkAllLines(char[][] board){
        char Line0 = checkLine(board, 0);
        char Line1 = checkLine(board, 1);
        char Line2 = checkLine(board, 2);
        if (Line0 == '_' && Line1 == '_'){
            return Line2;
        }
        else if (Line0 == '_' && Line2 == '_'){
            return Line1;
        }
        else if (Line2 == '_' && Line1 == '_'){
            return Line0;
        }
        else {
            return '?';
        }
    }

    static boolean checkFinished(char[][] board){
        boolean full = true;
        for (int i = 0; i < 3 && full; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_'){
                    full = false;
                    break;
                }
            }
        }
        return full;
    }

    static char checkWin(char[][] board){
        boolean full = checkFinished(board);
        char lines = checkAllLines(board);
        char cols = checkAllCols(board);
        char diags = checkAllDiagonals(board);
        if (lines == '?' || cols == '?' || diags == '?'){
            return '?';
        }
        if (lines == cols){
            if (lines == diags){
                if (full && lines == '_'){
                    return '-';
                }
                else {
                    return lines;
                }
            }
            else{
                if (lines == '_'){
                    if (full){
                        return '-';
                    }
                    else {
                        return diags;
                    }
                }
                else if (diags == '_'){
                    if (full){
                        return '-';
                    }
                    else {
                        return lines;
                    }
                }
                else {
                    return '?';
                }
            }
        }
        else if (cols == diags){
            if (lines == '_'){
                if (full){
                    return '-';
                }
                else {
                    return cols;
                }
            }
            else if (cols == '_'){
                if (full){
                    return '-';
                }
                else {
                    return lines;
                }
            }
            else {
                return '?';
            }
        }
        else if (lines == diags){
            if (cols == '_'){
                if (full){
                    return '-';
                }
                else {
                    return lines;
                }
            }
            else if (lines == '_'){
                if (full){
                    return '-';
                }
                else {
                    return cols;
                }
            }
            else {
                return '?';
            }
        }
        else {
            return '?';
        }
    }

    static void printState(char[][] board){
        if (!checkNumber(board)){
            System.out.println("Impossible");
            return;
        }
        char win = checkWin(board);
        if (win == '-') {
            System.out.println("Draw");
        }
        else if (win == '_'){
            System.out.println("Game not finished");
        }
        else if (win == '?'){
            System.out.println("Impossible");
        }
        else {
            System.out.println("\n" + win + " wins");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = '_';
            }
        }
        char currChar = 'X';
        while (checkWin(board) == '_'){
            printBoard(board);
            int x, y;
            boolean ok = false;
            do {
                System.out.print("Enter the coordinates: ");
                y = scanner.nextInt();
                x = scanner.nextInt();
                if (x < 1 || y < 1 || x > 3 || y > 3){
                    System.out.println("Coordinates should be from 1 to 3!");
                }
                else {
                    y--;
                    x = -1 * (x - 3);
                    if (board[x][y] != '_'){
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                    else {
                        board[x][y] = currChar;
                        ok = true;
                        if (currChar == 'X'){
                            currChar = 'O';
                        }
                        else {
                            currChar = 'X';
                        }
                    }
                }
            } while (!ok);
        }
        printBoard(board);
        printState(board);
    }
}
