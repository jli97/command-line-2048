/**
 * @File: Model.java
 * @Author: Jonathan Li
 * @Date: April 4 2021
 * @Description: Module reponsible for displaying the game to the user
 */

package src;

import java.util.Arrays;

public class View{

    private static int[][] board;
    private static int points;
    private static String message;
    
    /**
     * @brief updates the board with the given model
     * @param model is a Model object with the data representing the state of the game
     */
    static void updateView(Model model){
        int boardSize = model.getBoardSize();
        board = new int[boardSize][boardSize];
        points = model.getPoints();
        for(Col c : model.getCols()){
            int[] content = c.getContent();
            for(int i = 0; i < boardSize; i++){
                board[i][c.getId()] = content[i];
            }
        }
    }

    /**
     * @brief prints the number of points, the board in matrix representation and a message bar
     */
    static void show(){
        System.out.println("Points: " + points);
        for(int i = board.length - 1; i >= 0; i--){
            for(int j = 0; j < board[0].length; j++){
                System.out.print("|");
                if(board[i][j] == 0){
                    Services.printNSpaces(4);
                } 
                else{
                    System.out.print(board[i][j]);
                    int length = (int) (Math.log10(board[i][j]) + 1);
                    Services.printNSpaces(4-length);
                }
                if (j == board[0].length - 1){
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        System.out.println("Message: "+ message);
    }

    /**
     * @brief clears all output currently on the screen
     */
    static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 
    
    /**
     * @brief setter for message
     * @param m is the message to be set
     */
    static void setMessage(String m){
        message = m;
    }
}

