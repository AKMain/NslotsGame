import java.util.Scanner;

public class Main {
    public static void printBoard(int [] board){
        for (int i = 0; i < 20; i++) {
            if (board[i]==0){
                System.out.println((i+1)+": ____");
            }else{
                System.out.println((i+1)+": "+board[i]);
            }
        }
        System.out.println("\n");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int [] board = new int[20];
        boolean win = true;
        int i,k,rand,input,min;

        do {

            for (i = 0; i < 20; i++) {
                rand = (int) Math.round(Math.random() * 1000 + 0.5);
                printBoard(board);
                System.out.println("Choose Slot for the number: " + rand);
                input = scanner.nextInt() - 1;

                while (0 != board[input]) {
                    System.out.println("Choose Slot for the number: " + rand);
                    input = scanner.nextInt() - 1;
                }

                board[input] = rand;
                System.out.println("\n\n");

                min = 1;
                for (k = 0; k < 20; k++) {
                    if (board[k] != 0 && min > board[k]) {
                        board[input] = 0;
                        printBoard(board);
                        System.out.println("\nLost on move " + (i) + " nice try.");
                        win = false;
                        break;
                    } else if (board[k] != 0 && min <= board[k]) {
                        min = board[k];
                    }
                }
                if (!win) {
                    break;
                }

            }

            if (win) {
                System.out.println("You won.");
            }
            System.out.println("\nPress 1 to play again\n\n\n");
        }while(scanner.next().equals("1"));
    }

}