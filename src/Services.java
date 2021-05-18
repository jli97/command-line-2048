/**
 * @File: Services.java
 * @Author: Jonathan Li
 * @Date: April 4 2021
 * @Description: Useful methods used in other modules
 */

package src;

public class Services {
    /**
     * @brief generate a random integer between two numbers
     * @param min represents the minimum it can return
     * @param max represents the non-inclusive max it can return
     * @details an integer x such that min <= x < max
     */
    static int generateRandomInt(int min, int max){
        return (int) Math.floor(Math.random() * (max - min) + min);
    }

    /**
     * @brief generate either a 2 or 4
     * @details 2 has a probaility of 90% while 4 has a probaility of 10%
     */
    static int generateTwoFour(){
        double res = Math.random();
        if(res <= 0.1){
            return 4;
        }
        else{
            return 2;
        }
    }

    /**
     * @brief checks whether a string is an integer
     * @param input is the string to be checked
     * @return a boolean representing whether the input is an integer
     */
    static boolean isInteger(String input) throws NumberFormatException{
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * @brief transposes the board 
     * @param cols is a seq of Col representing the board
     * @return a seq of Col representing the transposed board
     */
    static Col[] transposeCol(Col[] cols){
        Col[] rows = new Col[cols.length];
        for(int i = 0; i < cols.length; i++){
            rows[i] = new Col(i, new int[cols[i].getContent().length]);
        }

        for(Col c : cols){
            int[] content = c.getContent(); 
    
            for(int row = 0; row < cols.length; row++){
                int[] row_content = rows[row].getContent();
                row_content[c.getId()] = content[row];
            }
        }

        return rows;
    }

    /**
     * @brief print n blank spaces to the terminal
     * @param n is the number of spaces to print
     */
    static void printNSpaces(int n){
        for(int i = 0; i < n; i++){
            System.out.print(" ");
        }

    }
}


