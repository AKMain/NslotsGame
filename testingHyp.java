import java.util.Arrays;
import java.util.Random;

public class testingHyp {

    public static boolean placeNumber(double[] board, int slots) {
        double value = Math.random();
        int pos = (int)Math.floor(value * slots);
//        System.out.println("Number: " + value + "\tPOS: " + pos);

        if (board[pos] == -1) {

            boolean validPlacement = true;

            for (int i = pos - 1; i >= 0; i--) {
                if (board[i] != -1 && board[i] > value) {
                    validPlacement = false;
                    break;
                }
            }

            for (int i = pos + 1; i < slots; i++) {
                if (board[i] != -1 && board[i] < value) {
                    validPlacement = false;
                    break;
                }
            }

            if (validPlacement) {
                board[pos] = value;
                return false;
            }
        }

        for (int i = 0; i < slots; i++) {
            if (board[i] == -1) {
                boolean validPlacement = true;

                for (int j = 0; j < i; j++) {
                    if (board[j] != -1 && board[j] > value) {
                        validPlacement = false;
                        break;
                    }
                }

                for (int j = i + 1; j < slots; j++) {
                    if (board[j] != -1 && board[j] < value) {
                        validPlacement = false;
                        break;
                    }
                }

                if (validPlacement) {
                    board[i] = value;
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int win, n = 200000000;

        boolean lost;
        for (int slots = 2; slots <= 20; slots++) {

            double board[] = new double[slots];
            win =0;


            for (int j = 0; j < n; j++) {
                lost = false;
                Arrays.fill(board, -1);

                for (int i = 0; i < slots; i++) {

                    if (placeNumber(board, slots)) {
                        lost = true;
                        break;
                    }

                }

                if (!lost) {
                    win++;
                }

//            if (lost) {
//                loss++;
//            } else {
//                win++;
//                for (int i = 0; i < slots; i++) {
//                    System.out.print((int)Math.floor(1000*board[i])+" ");
//                }
//                System.out.println();
//            }

            }
            double output =  win;
            //System.out.println(output);
            output = 100.0*(output / n);
            //System.out.println(output);
            System.out.println("Wins: " + win + "\tLosses: " + (n-win) + "\tP(Win) = " + String.format("%.5f", output) + "%");
        }
    }
}