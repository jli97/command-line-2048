/**
 * @File: Model.java
 * @Author: Jonathan Li
 * @Date: April 4 2021
 * @Description: A module to store the state and status of the game
 */

package src;

import java.util.*;


public class Model {
    private Col[] cols; //Left to right, Bottom to Top (0,0) == Bottom Left (3,3) == Top right
    private int boardSize; // boardSize x boardSize 
    private int status; // 0 = Available move, 1 = loss, 2 = win
    private int points;

    /**
     * @brief constructor
     * @param boardSize is an integer representing the size of the board (boardSize x boardSize)
     * @details generates a an empty board with the given boardSize
     */
    Model(int boardSize) throws IllegalArgumentException{
        if(boardSize < 2){
            throw new IllegalArgumentException();
        }
        this.cols = new Col[boardSize];   
        this.boardSize = boardSize;
        this.status = 0;
        this.points = 0;
        //Populate col and map
        for(int i = 0 ; i < boardSize; i++){
            cols[i] = new Col(i, new int[boardSize]);
        }

    }
    /**
     * @brief getter for cols
     * @return returns the seq of Col representing the board
     */
    public Col[] getCols() {
        return this.cols;
    }
    /**
     * @brief getter for points
     * @return returns the the points of the game
     */
    public int getPoints(){
        return this.points;
    }
    /**
     * @brief getter for status
     * @return returns the the status of the game
     */
    public int getStatus(){
        return this.status;
    }
    /**
     * @brief getter for boardSize
     * @return returns the length of one side of the square board
     */
    public int getBoardSize(){
        return this.boardSize;
    }
    /**
     * @brief setter for a tile on the board
     * @param row represents the y coordiante
     * @param col represents the x coordinate 
     * @param value represents the value to place at the position
     * @details places a value on the board given coordinates
     */
    public void setTile(int row, int col, int value){
        this.cols[col].setContent(row, value);
    }

    /**
     * @brief randomly creates a new tile on the board
     * @details the value has a chance 10% chance to be a 4 and a 90% chance to be a 2
     */
    void createNewTile(){
        ArrayList<Integer[]> emptyPos = new ArrayList<Integer[]>();
        for(Col c : this.cols){ 
            int[] content = c.getContent();
            for(int i = 0; i < content.length; i++){
                if(content[i] == 0){
                    emptyPos.add(new Integer[]{i, c.getId()});
                }
            }
        }

        Integer[] pos = emptyPos.get(Services.generateRandomInt(0, emptyPos.size()));
        this.setTile(pos[0], pos[1], Services.generateTwoFour());
    }

    /**
     * @brief checks the status of the game and updates the state variable status
     * @details Game is lost when there is no valid move, game is won if there exists a 2048 tile, else game can continue
     */
    void checkStatus(){
        int[][] board = new int[this.boardSize][this.boardSize];
        
        //Create matrix
        for(Col c : this.cols){
            int[] content = c.getContent();
            for(int row = 0; row < content.length; row++){
                board[row][c.getId()] = content[row];
            }
        }

        int[][] directions = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        for(int i = 0; i < this.boardSize; i++){
            for(int j = 0; j < this.boardSize; j++){
                for(int[] dir : directions){
                    int x = dir[0] + i;
                    int y = dir[1] + j;
                    if(x >= 0 && x < this.boardSize && y >= 0 && y < this.boardSize){
                        if(board[i][j] == 0 || board[i][j] == board[x][y]){
                            this.status = 0;
                            return;
                        }
                        if(board[i][j] == 2048){
                            this.status = 2;
                            return;
                        }
                    }
                    
                }
            }
        }
        this.status = 1;
    }
    /**
     * @brief shifts all values up in the board and merges whenever possible
     * @details priority for tile merging starts from the top to bottom
     */
    boolean moveUp(){
        Col[] temp = Arrays.copyOf(this.cols, this.boardSize);

        boolean moveable = false;
        for(Col c : temp){
            Col before = c.copy();
            c.shiftRight();
            int[] content = c.getContent();

            for(int i = content.length - 1; i > 0; i--){
                if(content[i] == content[i - 1]){
                    content[i] *= 2;
                    this.points += content[i];
                    content[i - 1] = 0;
                }
            }

            c.setContent(content);
            c.shiftRight();

            //Checks for moveable
            if(moveable == false && !before.equals(c)){
                moveable = true;
            }
        }
        if(moveable){
            this.cols = temp;
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * @brief shifts all values down in the board and merges whenever possible
     * @details priority for tile merging starts from the bottom to top
     */
    boolean moveDown(){
        Col[] temp = Arrays.copyOf(this.cols, this.boardSize);
    
        boolean moveable = false;
        for(Col c : temp){
            Col before = c.copy();
            c.shiftLeft();
            int[] content = c.getContent();
            for(int i = 0; i < this.boardSize - 1 ; i++){
                if(content[i] == content[i + 1]){
                    content[i] *= 2;
                    this.points += content[i];
                    content[i + 1] = 0;
                }
            }

            c.setContent(content);
            c.shiftLeft();
            //Checks for moveable
            if(moveable == false && !before.equals(c)){
                moveable = true;
            }
        }
        if(moveable){
            this.cols = temp;
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * @brief shifts all values left in the board and merges whenever possible
     * @details priority for tile merging starts from the left to right
     */
    boolean moveLeft(){
        Col[] rows = Services.transposeCol(this.cols);

        Col[] temp = Arrays.copyOf(rows, this.boardSize);
    
        boolean moveable = false;
        for(Col c : temp){
            Col before = c.copy();
            c.shiftLeft();
            int[] content = c.getContent();
            for(int i = 0; i < this.boardSize - 1 ; i++){
                if(content[i] == content[i + 1]){
                    content[i] *= 2;
                    this.points += content[i];
                    content[i + 1] = 0;
                }
            }

            c.setContent(content);
            c.shiftLeft();
            //Checks for moveable
            if(moveable == false && !before.equals(c)){
                moveable = true;
            }
        }
        if(moveable){
            this.cols = Services.transposeCol(temp); //Transpose it back into columns
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * @brief shifts all values right in the board and merges whenever possible
     * @details priority for tile merging starts from the right to left
     */
    boolean moveRight(){
        Col[] rows = Services.transposeCol(this.cols);

        Col[] temp = Arrays.copyOf(rows, this.boardSize);

        boolean moveable = false;
        for(Col c : temp){
            Col before = c.copy();
            c.shiftRight();
            int[] content = c.getContent();

            for(int i = content.length - 1; i > 0; i--){
                if(content[i] == content[i - 1]){
                    content[i] *= 2;
                    this.points += content[i];
                    content[i - 1] = 0;
                }
            }

            c.setContent(content);
            c.shiftRight();

            //Checks for moveable
            if(moveable == false && !before.equals(c)){
                moveable = true;
            }
        }
        if(moveable){
            this.cols = Services.transposeCol(temp); //Transpose it back into columns
            return true;
        }
        else{
            return false;
        }
    }
}
