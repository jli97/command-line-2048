/**
 * @File: Controller.java
 * @Author: Jonathan Li
 * @Date: April 4 2021
 * @Description: Controller module that handles inputs and links model and view
 */

package src;

import java.util.Scanner;
import java.lang.Integer;

public class Controller {
    /**
     * @brief Initializes the game and handles the top-level logic of 2048
     */
    static void startGame(){
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter Size of Board (Size x Size) *Must be Integer and Min 2");

        String input = scanner.nextLine(); 
        
        while(!Services.isInteger(input)){
            System.out.println("Integers Only!");
            input = scanner.nextLine();
        }
        int boardSize = Integer.parseInt(input);
        Model model = new Model(boardSize);
        model.createNewTile();
        model.createNewTile();

        String message = "";
        while(model.getStatus() == 0){
            View.setMessage(message);
            View.clearScreen();
            View.updateView(model);
            View.show();
            
            System.out.println("Enter your next move (WASD) *type \"exit\" to exit");
            input = scanner.nextLine();
            
            if(input.equals("w") || input.equals("W")){
                boolean res = model.moveUp();
                if(res){
                    model.createNewTile();
                    message = "";
                } 
                else{
                    message = "Can't move up";
                }
            }
            else if(input.equals("s") || input.equals("S")){
                boolean res = model.moveDown();
                if(res){
                    model.createNewTile();
                    message = "";
                }
                else{
                    message = "Can't move down";
                }
            }
            else if(input.equals("a") || input.equals("A")){
                boolean res = model.moveLeft();
                if(res){
                    model.createNewTile();
                    message = "";
                }
                else{
                    message = "Can't move left";
                }
            }
            else if(input.equals("d") || input.equals("D")){
                boolean res = model.moveRight();
                if(res){
                    model.createNewTile();
                    message = "";
                }
                else{
                    message = "Can't move right";
                }
            }
            else if(input.equals("exit")){
                break;
            }
            model.checkStatus();
            
        }
        
        scanner.close();
        View.clearScreen();

        if(model.getStatus() == 1){
            System.out.println("Game over, Please try again");
        }
        else if(model.getStatus() == 2){
            System.out.println("Congrats 2048!");
        }

        View.updateView(model);
        View.show();
    }
}
