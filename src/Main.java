
import java.util.Scanner;
import java.util.InputMismatchException;


public class Main {

    public static void main(String[] args) {
        String board = "         ";
        char XO;
        int state = 0;
        char[] arr = board.toCharArray();
        printBoard(arr);
        for (int i = 0; i < 9 ; i++) {
            XO = i%2==0? 'X' :'O';
            getInput(arr, XO);
            printBoard(arr);
            state = checkRowCol(arr);
            if (state == 1 || state == 2) break;
            state = checkDiagonally(arr);
            if (state == 1 || state == 2) break;
        }
        if(state==2) System.out.println("X wins");
        else if (state==1) System.out.println("O wins");
        else System.out.println("Draw");
    }

    static void printBoard(char[] arr){
        System.out.println("---------");
        for (int i = 0; i < 9; i+=3) {
            System.out.printf("| %c %c %c |\n",arr[i],arr[i+1],arr[i+2]);
        }
        System.out.print("---------");
    }

    static int checkDiagonally(char[] arr){
        int X_row_0 = 0;
        int X_row_1 = 0;
        int O_row_0 = 0;
        int O_row_1 = 0;
        for (int i = 2; i <= 6 ; i+=2) {
            if(arr[i]=='X') X_row_0++;
            else if (arr[i]=='O') O_row_0++;
            if(arr[(i-2)*2]=='X') X_row_1++;
            else if (arr[(i-2)*2]=='O') O_row_1++;
        }
        if(O_row_0==3||O_row_1==3) return 1;
        else if (X_row_0==3||X_row_1==3)  return 2;
        return 0;
    }

    static int checkRowCol(char[] arr){
        int j=0;
        for (int i = 0; i < 9; i+=3) {
            if(arr[i]=='X'&&arr[i+1]=='X'&&arr[i+2]=='X') return 2;
            else if(arr[i]=='O'&&arr[i+1]=='O'&&arr[i+2]=='O') return 1;
            if(arr[j]=='X'&&arr[j+3]=='X'&&arr[j+6]=='X') return 2;
            else if(arr[j]=='O'&&arr[j+3]=='O'&&arr[j+6]=='O') return 1;
            j++;
        }
        return 0;
    }

    /*    static int checkPossibility(char[] arr){
            int X = 0;
            int O = 0;
            int Space = 0;
            for (int i = 0; i < 9 ; i++) {
                switch (arr[i]){
                    case 'X' -> X++;
                    case 'O' -> O++;
                    case ' ' -> Space++;
                }
            }
            if (Math.abs(X-O)>=2) return -1; //game not finished
            else if (Space==0) return 3; //draw
            return 0;
        }*/
    static void getInput(char[] board,char c){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the coordinates:");
        int x;
        int y;
        int conversion;
        try {
            x = sc.nextInt();
            y = sc.nextInt();
            conversion = 3*(x-1)+y-1;
            if((x<-1||x>3)||(y<-1||y>3)){
                System.out.println("Coordinates should be from 1 to 3!");
                printBoard(board);
                getInput(board, c); return;
            }
            else if(board[conversion]=='X'||board[conversion]=='O'){
                System.out.println("This cell is occupied! Choose another one!");
                printBoard(board);
                getInput(board, c); return;
            }
        } catch (InputMismatchException e){
            System.out.println("You should enter numbers!");
            printBoard(board);
            getInput(board, c); return;
        }
        board[conversion] = c;
    }
}
