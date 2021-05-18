/**
 * @File: Col.java
 * @Author: Jonathan Li
 * @Date: April 4 2021
 * @Description: A module to represent a column in the board
 */


package src;

import java.util.Arrays;

public class Col {
    private int id;
    private int[] content;

    /**
     * @brief constructor
     * @param id represents the column number if the column were represented in a matrix
     * @param content is an integer array with the values in the column. Each index corresponds to the respective row
     * @details Example: Col[2].content[3] represents the coordinates (3, 2) on the board
     */
    Col(int id, int[] content){
        this.id = id;
        this.content = content;
    }
    /**
     * @brief getter for id
     * @returns integer representing the id of the column
     */
    int getId(){
        return this.id;
    }

    /**
     * @brief getter for content
     * @returns integer array representing the tiles in the column
     */
    int[] getContent(){
        return this.content;
    }

    /**
     * @brief setter for content
     * @param content represents the integer array to replace the exisitng content array
     */
    void setContent(int[] content){
        this.content = content;
    }

    /**
     * @brief setter for content
     * @param pos represents the position in content to replace
     * @param value represents the value to place at pos
     */
    void setContent(int pos, int value){
        this.content[pos] = value;
    }
    /**
     * @brief shifts all values content right
     * @details shift all non-zero integers in the content array to the right keeping the existing order of non-zero integers. At the end, all zeros should only exist on the left of non-zero integers.
     */
    void shiftRight(){
        int[] aux = new int[content.length];
        int idx = content.length - 1;

        for(int i = this.content.length - 1; i >= 0; i--){
            if(this.content[i] != 0){
                aux[idx] = this.content[i];
                idx--;
            }
        }
        this.content = aux;

    }
    /**
     * @brief shifts all values content left
     * @details shift all non-zero integers in the content array to the left keeping the existing order of non-zero integers. At the end, all zeros should only exist on the right of non-zero integers.
     */
    void shiftLeft(){
        int[] aux = new int[content.length];
        int idx = 0;

        for(int i = 0; i < this.content.length; i++){
            if(this.content[i] != 0){
                aux[idx] = this.content[i];
                idx++;
            }
        }
        this.content = aux;
    }
    /**
     * @brief creates a copy of the Col
     * @return a copy of the Col
     */
    Col copy(){
        return new Col(this.id, this.content);
    }

    /**
     * @brief checks for euqality for a given Col
     * @param c is a Col object to be compared
     * @return whether the two Cols are equal
     * @details two Cols are considered equal if they have the same id and contents
     */
    Boolean equals(Col c){
        return (this.id == c.getId() && Arrays.equals(this.content, c.getContent()));
    }
}
