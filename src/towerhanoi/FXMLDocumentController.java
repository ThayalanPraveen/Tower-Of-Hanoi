/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerhanoi;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Random;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Stack;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 *
 * @author thayalanpraveen
 */
public class FXMLDocumentController implements Initializable {
    
    // array to store the y location of disks for arrow pointer
    public int[] Disky_Array = {0,0,0,0,0,0,0,0,0};
    public int moves = 0;
    public boolean unlimited = false;
    // Array For Button Fx
    public String[] Fx_Array = {"-fx-background-color: linear-gradient(to right top, #5c5758, #4b4243, #3a2d30, #2a1a1e, #1c0108);background-image: linear-gradient(to right top, #5c5758, #4b4243, #3a2d30, #2a1a1e, #1c0108);; background-image: linear-gradient(to bottom, #29eded, #7eefdf, #abf0d9, #cdf1db, #e6f2e6); background-image: linear-gradient(to bottom, #dee549, #ff9927, #fe3d53, #c40088, #2b25ac);","-fx-background-color: linear-gradient(to right top, #f97295, #f65f81, #f14a6d, #eb3157, #e30841);background-image: linear-gradient(to right top, #f97295, #f65f81, #f14a6d, #eb3157, #e30841);; background-image: linear-gradient(to bottom, #edc229, #dbd562, #d1e393, #d4ecc0, #e6f2e6); background-image: linear-gradient(to bottom, #ed296d, #ec7754, #dcac6b, #d2d5a6, #e6f2e6); background-image: linear-gradient(to bottom, #43ed29, #36ce21, #29b11a, #1c9413, #10780b); background-image: linear-gradient(to bottom, #e549a1, #c635a5, #a128a9, #7324ab, #2b25ac); background-image: linear-gradient(to bottom, #15f4a6, #00cdd0, #00a0ef, #006ce8, #2b25ac); background-image: linear-gradient(to bottom, #28f415, #22ce1b, #1ea91d, #1b861b, #186418);",
    "-fx-background-color: linear-gradient(to right top, #f6eb07, #f5d300, #f2bb00, #eca400, #e38e08);background-image: linear-gradient(to right top, #f6eb07, #f5d300, #f2bb00, #eca400, #e38e08);; background-image: linear-gradient(to bottom, #43ed29, #77f069, #9ef397, #c2f3c0, #e6f2e6); background-image: linear-gradient(to bottom, #ed8f29, #f0782c, #f16034, #ee443e, #e91e4a); background-image: linear-gradient(to bottom, #29ede4, #32f2c3, #69f495, #9ff160, #d7e91e); background-image: linear-gradient(to bottom, #15c4f4, #00a2ee, #007de3, #0056ce, #2b25ac); background-image: linear-gradient(to bottom, #15baf4, #0096eb, #0071dc, #0048c5, #280fa1); background-image: linear-gradient(to bottom, #f4c415, #e6ce00, #d5d800, #c0e200, #a8eb12); background-image: linear-gradient(to right top, #f4154f, #ff5b24, #f59100, #d8c000, #a8eb12);",
    "-fx-background-color: linear-gradient(to right top, #d1ed1a, #c7e31d, #bdd91e, #b3d020, #a9c621);background-image: linear-gradient(to right top, #d1ed1a, #c7e31d, #bdd91e, #b3d020, #a9c621);; background-image: linear-gradient(to bottom, #43ed29, #77f069, #9ef397, #c2f3c0, #e6f2e6); background-image: linear-gradient(to bottom, #ed8f29, #f0782c, #f16034, #ee443e, #e91e4a); background-image: linear-gradient(to bottom, #29ede4, #32f2c3, #69f495, #9ff160, #d7e91e); background-image: linear-gradient(to bottom, #15c4f4, #00a2ee, #007de3, #0056ce, #2b25ac); background-image: linear-gradient(to bottom, #15baf4, #0096eb, #0071dc, #0048c5, #280fa1); background-image: linear-gradient(to bottom, #f4c415, #e6ce00, #d5d800, #c0e200, #a8eb12); background-image: linear-gradient(to right top, #f4154f, #ff5b24, #f59100, #d8c000, #a8eb12);",
    "-fx-background-color: linear-gradient(to right top, #7ae09d, #63cc85, #4cb96d, #33a555, #10923d);background-image: linear-gradient(to right top, #7ae09d, #63cc85, #4cb96d, #33a555, #10923d);; background-image: linear-gradient(to bottom, #43ed29, #77f069, #9ef397, #c2f3c0, #e6f2e6); background-image: linear-gradient(to bottom, #ed8f29, #f0782c, #f16034, #ee443e, #e91e4a); background-image: linear-gradient(to bottom, #29ede4, #32f2c3, #69f495, #9ff160, #d7e91e); background-image: linear-gradient(to bottom, #15c4f4, #00a2ee, #007de3, #0056ce, #2b25ac); background-image: linear-gradient(to bottom, #15baf4, #0096eb, #0071dc, #0048c5, #280fa1); background-image: linear-gradient(to bottom, #f4c415, #e6ce00, #d5d800, #c0e200, #a8eb12); background-image: linear-gradient(to right top, #f4154f, #ff5b24, #f59100, #d8c000, #a8eb12);",
    "-fx-background-color: linear-gradient(to right top, #7aa6e0, #5e8fd8, #4477cf, #2e5fc4, #1e46b7);background-image: linear-gradient(to right top, #7aa6e0, #5e8fd8, #4477cf, #2e5fc4, #1e46b7);; background-image: linear-gradient(to bottom, #43ed29, #77f069, #9ef397, #c2f3c0, #e6f2e6); background-image: linear-gradient(to bottom, #ed8f29, #f0782c, #f16034, #ee443e, #e91e4a); background-image: linear-gradient(to bottom, #29ede4, #32f2c3, #69f495, #9ff160, #d7e91e); background-image: linear-gradient(to bottom, #15c4f4, #00a2ee, #007de3, #0056ce, #2b25ac); background-image: linear-gradient(to bottom, #15baf4, #0096eb, #0071dc, #0048c5, #280fa1); background-image: linear-gradient(to bottom, #f4c415, #e6ce00, #d5d800, #c0e200, #a8eb12); background-image: linear-gradient(to right top, #f4154f, #ff5b24, #f59100, #d8c000, #a8eb12);",
    "-fx-background-color: linear-gradient(to right top, #7469e7, #6253d3, #513ebf, #3f27ac, #2b0d98);background-image: linear-gradient(to right top, #7469e7, #6253d3, #513ebf, #3f27ac, #2b0d98);; background-image: linear-gradient(to bottom, #43ed29, #77f069, #9ef397, #c2f3c0, #e6f2e6); background-image: linear-gradient(to bottom, #ed8f29, #f0782c, #f16034, #ee443e, #e91e4a); background-image: linear-gradient(to bottom, #29ede4, #32f2c3, #69f495, #9ff160, #d7e91e); background-image: linear-gradient(to bottom, #15c4f4, #00a2ee, #007de3, #0056ce, #2b25ac); background-image: linear-gradient(to bottom, #15baf4, #0096eb, #0071dc, #0048c5, #280fa1); background-image: linear-gradient(to bottom, #f4c415, #e6ce00, #d5d800, #c0e200, #a8eb12); background-image: linear-gradient(to right top, #f4154f, #ff5b24, #f59100, #d8c000, #a8eb12);",
    "-fx-background-color: linear-gradient(to right top, #c189ed, #a86cd7, #8e4fc2, #7532ad, #5b0d98);background-image: linear-gradient(to right top, #c189ed, #a86cd7, #8e4fc2, #7532ad, #5b0d98);; background-image: linear-gradient(to bottom, #43ed29, #77f069, #9ef397, #c2f3c0, #e6f2e6); background-image: linear-gradient(to bottom, #ed8f29, #f0782c, #f16034, #ee443e, #e91e4a); background-image: linear-gradient(to bottom, #29ede4, #32f2c3, #69f495, #9ff160, #d7e91e); background-image: linear-gradient(to bottom, #15c4f4, #00a2ee, #007de3, #0056ce, #2b25ac); background-image: linear-gradient(to bottom, #15baf4, #0096eb, #0071dc, #0048c5, #280fa1); background-image: linear-gradient(to bottom, #f4c415, #e6ce00, #d5d800, #c0e200, #a8eb12); background-image: linear-gradient(to right top, #f4154f, #ff5b24, #f59100, #d8c000, #a8eb12);" };

    // Play Music
    public Media click = new Media(getClass().getResource("Click.mp3").toExternalForm());
    public MediaPlayer clickPlayer = new MediaPlayer(click);
    
    public String fileName = "filename.txt";
    //Making stack for Rods
    public Stack<Integer> rA = new Stack<>();
    public Stack<Integer> rB = new Stack<>();
    public Stack<Integer> rC = new Stack<>();
   
    public int minimum_moves;
    public int disk_v = 1;
        
    public String MyString ="";
    // Initialization for button size and location
    public static int width = 300;
    public static int Ly = 580;
    public static int Lx = 90;
    
    // Array For Recording Which Disk is pressed
    public static int[] Disk_Array = {0,0,0,0,0,0,0,0,0};
    public static int[] Empty_Array = {0,0,0,0,0,0,0,0,0};
    public static int[] Top_Array = {0,7,7};
    public static int[] Steps= new int[256];
    
    public static int[][] Full_Array = new int[8][3];
    public static int[][] Steps_Array = new int[8][3];
    
    
    // Name variable to store user Name
    public static String Name = " ";
    
    // Variable to check if a radio button is selected
    public static boolean radio_click = false;
    
    // check if disk is pressed
    public static boolean rodA_clicked = false;
    public static boolean rodB_clicked = false;
    public static boolean rodC_clicked = false;
    
    // Boolean for undo function
    public static boolean undo = false;
    
    public Duration duration = Duration.millis(50);
    
    
    
    @FXML
    private AnchorPane Pane1;
    @FXML 
    private javafx.scene.control.Button mainstart;
    @FXML 
    private javafx.scene.control.Button startgamebutton;
    @FXML
    private ToggleGroup group1;
    @FXML
    private ToggleGroup group2;
    @FXML
    private TextField TextField;
    
    
    @FXML
    private void scores(ActionEvent event11) {
        Stage newWindow5 = new Stage();
        try 
        {
            AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("scores.fxml"));
            newWindow5.setResizable(false);
            newWindow5.initModality(Modality.APPLICATION_MODAL);
            try (Scanner scanner = new Scanner(new File("filename.txt"))) 
            {
                int x = 15;
                while (scanner.hasNext()){
                    MyString = scanner.next();
                    try 
                    {
                        int foo = Integer.parseInt(MyString);
                        Label label3 = new Label(MyString);
                        label3.setScaleX(2);
                        label3.setScaleY(2);
                        label3.setLayoutX(400);
                        label3.setLayoutY(100+ x);
                        x = x+ 30 ;
                        root.getChildren().add(label3);
                     }
                     catch (Exception e)
                     {
                        Label label2 = new Label(MyString);
                        label2.setScaleX(2);
                        label2.setScaleY(2);
                        label2.setLayoutX(100);
                        label2.setLayoutY(100 + x);
                        root.getChildren().add(label2);
                     }
                }

            } catch (FileNotFoundException e) 
            {
                e.printStackTrace();
            }
            Scene scene = new Scene(root, 500, 500);
            newWindow5.setScene(scene);
            newWindow5.initModality(Modality.APPLICATION_MODAL);
            newWindow5.show();
        }
        catch (IOException e) 
        {
        }        
    }
    @FXML
    private void Start2(ActionEvent event10) {
        undo = false;
        width = 300;
        Ly = 580;
        Lx = 90;
        for (int i =0 ; i < 8 ;i++){
            for (int j =0 ; j < 3 ;j++){
                Steps_Array[i][j] = 0;
                Full_Array[i][j] = 0;
            }
        }
        for (int i =0 ; i < rA.size()-1 ;i++){
            rA.pop();
        }
        for (int i =0 ; i < rB.size()-1 ;i++){
            rB.pop();
        }
        for (int i =0 ; i < rC.size()-1 ;i++){
            rC.pop();
        }
        for (int i =0 ; i < Disk_Array.length -1 ;i++){
            Disk_Array[i] = 0;
            Empty_Array[i] = 0;
        }
        for (int i =0 ; i < Steps.length -1 ;i++){
            Steps[i] = 0;
        }
        Top_Array[0] = 0;
        Top_Array[1] = 7;
        Top_Array[2] = 7;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Stage newWindow5 = new Stage();
        newWindow5.setResizable(false);
        Parent root2 = null;
        try 
        {
            root2 = fxmlLoader.load();
            Scene scene = new Scene(root2, 600, 400);

            newWindow5.setScene(scene);
            newWindow5.initModality(Modality.APPLICATION_MODAL);
            newWindow5.show();

            Window window =   ((Node)(event10.getSource())).getScene().getWindow(); 
                if (window instanceof Stage){
                    ((Stage) window).close();
                }
        }
        catch (IOException e) 
        {
        }
    }
    // Method to start login
    
    @FXML
    private void Start(ActionEvent event) {
        System.out.println(rA);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Stage newWindow = new Stage();
        newWindow.setResizable(false);
        Parent root2 = null;
        try 
        {
            root2 = fxmlLoader.load();
            Scene scene = new Scene(root2, 600, 400);

            newWindow.setScene(scene);
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.show();

            Stage stage = (Stage) mainstart.getScene().getWindow();
            stage.close();
        }
        catch (IOException e)
        {
        }
    }
    
    // Method to start game
    
    @FXML
    private void ins(ActionEvent event) {
        Stage newWindow2 = new Stage();
            newWindow2.setResizable(false);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("instructions.fxml"));

            Parent root3 = null;
            try {
                root3 = fxmlLoader2.load();
                Scene scene = new Scene(root3, 650, 400);
                newWindow2.setScene(scene);
                newWindow2.initModality(Modality.APPLICATION_MODAL);
                newWindow2.show();
            }
            catch (IOException e) {
            }
    }
    @FXML
    private void startgame(ActionEvent event) {
        // Radio button group creation for Difficulty and Disk No
        RadioButton selectedDifficulty = (RadioButton) group1.getSelectedToggle();
        RadioButton selectedDiskNo = (RadioButton) group2.getSelectedToggle();
        
        // Getting Name From User Input
        Name = TextField.getText();
        if (Name.length() < 2  || selectedDifficulty == null || selectedDiskNo == null ){
            Stage newWindow2 = new Stage();
            newWindow2.setResizable(false);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("Validate.fxml"));

            Parent root3 = null;
            try {
                root3 = fxmlLoader2.load();
                Scene scene = new Scene(root3, 300, 300);
                newWindow2.setScene(scene);
                newWindow2.initModality(Modality.APPLICATION_MODAL);
                newWindow2.show();
            }
            catch (IOException e) {
            }
        }
        
        else{
            // Closing the Window
            Stage stage1 = (Stage) startgamebutton.getScene().getWindow();
            stage1.close();
            
            Stage newWindow2 = new Stage();
            try {
                AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("GameScreen.fxml"));
                newWindow2.setResizable(false);
                
                // making an arrow pointer
                Button arrow = new Button();
                arrow.setId("arrow");
                arrow.setPrefWidth(30);
                arrow.setPrefHeight(20);
                arrow.setLayoutX(20);
                arrow.setLayoutY(0);
                root.getChildren().add(arrow);
                
                // Converting Disk ID to Disk No integer
                String Temp = selectedDiskNo.getId();
                int DiskNo = Integer.parseInt(Temp);
                
                // Array For Button ID
                String[] Buttons_Array = {"1", "2", "3", "4", "5" ,"6","7","8"};
                rA.push(10);
                rB.push(10);
                rC.push(10);
                for(int k = 0 ; k < DiskNo ; k++){
                    rA.push(k+1);
                }
                // Loop to make disks as buttons
                minimum_moves = (int) (Math.pow(2,DiskNo) -1);
                Label label1 = new Label("");
                Label label6 = new Label("");
                Label label5 = new Label("Moves : " + moves + " / " + minimum_moves);
                
                if (null == selectedDifficulty.getId()){
                    label1.setText("");
                }
                else switch (selectedDifficulty.getId()) {
                    case "r1":
                        label1.setText("You have Unlimited Moves!");
                        label6.setText("Beginner Mode");
                        unlimited = true;
                        break;
                    case "r2": 
                        minimum_moves = minimum_moves + 3;
                        label1.setText("You have " + minimum_moves + " Moves!");
                        label6.setText("Intermediate Mode");
                        break;
                    default:
                        label1.setText("You have " + minimum_moves + " Moves!");
                        label6.setText("Expert Mode");
                        break;
                }
                // Generating the list of right moves
                for ( int i = 0 ; i < 8 ; i++){
                    Steps_Array[i][0] = i+1;
                } 
                
                
                boolean complete = false;
                int k = 0;
                while (complete == false){
                    if (DiskNo%2 == 1){
                        
                        if ( (Steps_Array[Top_Array[0]][0] < Steps_Array[Top_Array[2]][2] || Steps_Array[Top_Array[2]][2] == 0) && (Steps_Array[Top_Array[0]][0] !=0) ){
                            if (Steps_Array[Top_Array[2]][2] == 0){
                                Steps_Array[Top_Array[2]][2] = Steps_Array[Top_Array[0]][0] ;
                            }
                            else{
                                Steps_Array[Top_Array[2]-1][2] = Steps_Array[Top_Array[0]][0] ;
                                Top_Array[2] = Top_Array[2] - 1 ;
                            }
                            // make steps array
                            Steps[k] = (Steps_Array[Top_Array[0]][0] * 10 ) + 3;
                            
                            Steps_Array[Top_Array[0]][0] = 0;
                            if ( Top_Array[0] != 7 ){
                                Top_Array[0] = Top_Array[0] + 1 ;
                            }
                            
                            k = k +1;
                            if ( k == minimum_moves){
                                complete = true;
                                break;
                            }
                               
                        }else{
                            if (Steps_Array[Top_Array[0]][0] == 0){
                                Steps_Array[Top_Array[0]][0] = Steps_Array[Top_Array[2]][2] ;
                            }
                            else{
                                Steps_Array[Top_Array[0]-1][0] = Steps_Array[Top_Array[2]][2] ;
                                Top_Array[0] = Top_Array[0] - 1 ;
                            }
                            // make steps array
                            Steps[k] = (Steps_Array[Top_Array[2]][2] * 10 ) + 1;
                            Steps_Array[Top_Array[2]][2] = 0;
                            if ( Top_Array[2] != 7 ){
                                Top_Array[2] = Top_Array[2] + 1 ;
                            }                         
                            k = k +1;
                            if ( k == minimum_moves){
                                complete = true;
                                break;
                            }                           
                        }
                        if ( (Steps_Array[Top_Array[0]][0] < Steps_Array[Top_Array[1]][1] || Steps_Array[Top_Array[1]][1] == 0) && (Steps_Array[Top_Array[0]][0] !=0) ){
                            if (Steps_Array[Top_Array[1]][1] == 0){
                                Steps_Array[Top_Array[1]][1] = Steps_Array[Top_Array[0]][0] ;
                            }
                            else{
                                Steps_Array[Top_Array[1]-1][1] = Steps_Array[Top_Array[0]][0] ;
                                Top_Array[1] = Top_Array[1] - 1 ;
                            }
                            // make steps array
                            Steps[k] = (Steps_Array[Top_Array[0]][0] * 10 ) + 2;
                            Steps_Array[Top_Array[0]][0] = 0;
                            if ( Top_Array[0] != 7 ){
                                Top_Array[0] = Top_Array[0] + 1 ;
                            }
                            
                            k = k +1;
                            if ( k == minimum_moves){
                                complete = true;
                                break;
                            }
                               
                        }else{
                            if (Steps_Array[Top_Array[0]][0] == 0){
                                Steps_Array[Top_Array[0]][0] = Steps_Array[Top_Array[1]][1] ;
                            }
                            else{
                                Steps_Array[Top_Array[0]-1][0] = Steps_Array[Top_Array[1]][1] ;
                                Top_Array[0] = Top_Array[0] - 1 ;
                            }
                            // make steps array
                            Steps[k] = (Steps_Array[Top_Array[1]][1] * 10 ) + 1;
                            Steps_Array[Top_Array[1]][1] = 0;
                            if ( Top_Array[1] != 7 ){
                                Top_Array[1] = Top_Array[1] + 1 ;
                            }                         
                            k = k +1;
                            if ( k == minimum_moves){
                                complete = true;
                                break;
                            }                           
                        } 
                        if ( (Steps_Array[Top_Array[1]][1] < Steps_Array[Top_Array[2]][2] || Steps_Array[Top_Array[2]][2] == 0) && (Steps_Array[Top_Array[1]][1] !=0) ){
                            if (Steps_Array[Top_Array[2]][2] == 0){
                                Steps_Array[Top_Array[2]][2] = Steps_Array[Top_Array[1]][1] ;
                            }
                            else{
                                Steps_Array[Top_Array[2]-1][2] = Steps_Array[Top_Array[1]][1] ;
                                Top_Array[2] = Top_Array[2] - 1 ;
                            }
                            // make steps array
                            Steps[k] = (Steps_Array[Top_Array[1]][1] * 10 ) + 3;
                            Steps_Array[Top_Array[1]][1] = 0;
                            if ( Top_Array[1] != 7 ){
                                Top_Array[1] = Top_Array[1] + 1 ;
                            }
                            
                            k = k +1;
                            if ( k == minimum_moves){
                                complete = true;
                                break;
                            }
                               
                        }else{
                            if (Steps_Array[Top_Array[1]][1] == 0){
                                Steps_Array[Top_Array[1]][1] = Steps_Array[Top_Array[2]][2] ;
                            }
                            else{
                                Steps_Array[Top_Array[1]-1][1] = Steps_Array[Top_Array[2]][2] ;
                                Top_Array[1] = Top_Array[1] - 1 ;
                            }
                            // make steps array
                            Steps[k] = (Steps_Array[Top_Array[2]][2] * 10 ) + 2;
                            Steps_Array[Top_Array[2]][2] = 0;
                            if ( Top_Array[2] != 7 ){
                                Top_Array[2] = Top_Array[2] + 1 ;
                            }                         
                            k = k +1;
                            if ( k == minimum_moves){
                                complete = true;
                                break;
                            }                           
                        }
                    }
                    
                    // even even even even
                    
                    else{
                        if ((Steps_Array[Top_Array[0]][0] < Steps_Array[Top_Array[1]][1] || Steps_Array[Top_Array[1]][1] == 0) && (Steps_Array[Top_Array[0]][0] !=0) ){
                            if (Steps_Array[Top_Array[1]][1] == 0){
                                Steps_Array[Top_Array[1]][1] = Steps_Array[Top_Array[0]][0] ;
                            }
                            else{
                                Steps_Array[Top_Array[1]-1][1] = Steps_Array[Top_Array[0]][0] ;
                                Top_Array[1] = Top_Array[1] - 1 ;
                            }
                            // make steps array
                            Steps[k] = (Steps_Array[Top_Array[0]][0] * 10 ) + 2;
                            Steps_Array[Top_Array[0]][0] = 0;
                            if ( Top_Array[0] != 7 ){
                                Top_Array[0] = Top_Array[0] + 1 ;
                            }
                            
                            k = k +1;
                            if ( k == minimum_moves){
                                complete = true;
                                break;
                            }
                               
                        }else{
                            if (Steps_Array[Top_Array[0]][0] == 0){
                                Steps_Array[Top_Array[0]][0] = Steps_Array[Top_Array[1]][1] ;
                            }
                            else{
                                Steps_Array[Top_Array[0]-1][0] = Steps_Array[Top_Array[1]][1] ;
                                Top_Array[0] = Top_Array[0] - 1 ;
                            }
                            // make steps array
                            Steps[k] = (Steps_Array[Top_Array[1]][1] * 10 ) + 1;
                            Steps_Array[Top_Array[1]][1] = 0;
                            if ( Top_Array[1] != 7 ){
                                Top_Array[1] = Top_Array[1] + 1 ;
                            }                         
                            k = k +1;
                            if ( k == minimum_moves){
                                complete = true;
                                break;
                            }                           
                        }                          
                        if ( (Steps_Array[Top_Array[0]][0] < Steps_Array[Top_Array[2]][2] || Steps_Array[Top_Array[2]][2] == 0) && (Steps_Array[Top_Array[0]][0] !=0) ){
                            if (Steps_Array[Top_Array[2]][2] == 0){
                                Steps_Array[Top_Array[2]][2] = Steps_Array[Top_Array[0]][0] ;
                            }
                            else{
                                Steps_Array[Top_Array[2]-1][2] = Steps_Array[Top_Array[0]][0] ;
                                Top_Array[2] = Top_Array[2] - 1 ;
                            }
                            // make steps array
                            Steps[k] = (Steps_Array[Top_Array[0]][0] * 10 ) + 3;
                            Steps_Array[Top_Array[0]][0] = 0;
                            if ( Top_Array[0] != 7 ){
                                Top_Array[0] = Top_Array[0] + 1 ;
                            }
                            
                            k = k +1;
                            if ( k == minimum_moves){
                                complete = true;
                                break;
                            }
                               
                        }else{
                            if (Steps_Array[Top_Array[0]][0] == 0){
                                Steps_Array[Top_Array[0]][0] = Steps_Array[Top_Array[2]][2] ;
                            }
                            else{
                                Steps_Array[Top_Array[0]-1][0] = Steps_Array[Top_Array[2]][2] ;
                                Top_Array[0] = Top_Array[0] - 1 ;
                            }
                            // make steps array
                            Steps[k] = (Steps_Array[Top_Array[2]][2] * 10 ) + 1;
                            Steps_Array[Top_Array[2]][2] = 0;
                            if ( Top_Array[2] != 7 ){
                                Top_Array[2] = Top_Array[2] + 1 ;
                            }                         
                            k = k +1;
                            if ( k == minimum_moves){
                                complete = true;
                                break;
                            }                           
                        } 
                        if ( (Steps_Array[Top_Array[1]][1] < Steps_Array[Top_Array[2]][2] || Steps_Array[Top_Array[2]][2] == 0) && (Steps_Array[Top_Array[1]][1] !=0) ){
                            if (Steps_Array[Top_Array[2]][2] == 0){
                                Steps_Array[Top_Array[2]][2] = Steps_Array[Top_Array[1]][1] ;
                            }
                            else{
                                Steps_Array[Top_Array[2]-1][2] = Steps_Array[Top_Array[1]][1] ;
                                Top_Array[2] = Top_Array[2] - 1 ;
                            }
                            // make steps array
                            Steps[k] = (Steps_Array[Top_Array[1]][1] * 10 ) + 3;
                            Steps_Array[Top_Array[1]][1] = 0;
                            if ( Top_Array[1] != 7 ){
                                Top_Array[1] = Top_Array[1] + 1 ;
                            }
                            
                            k = k +1;
                            if ( k == minimum_moves){
                                complete = true;
                                break;
                            }
                               
                        }else{
                            if (Steps_Array[Top_Array[1]][1] == 0){
                                Steps_Array[Top_Array[1]][1] = Steps_Array[Top_Array[2]][2] ;
                            }
                            else{
                                Steps_Array[Top_Array[1]-1][1] = Steps_Array[Top_Array[2]][2] ;
                                Top_Array[1] = Top_Array[1] - 1 ;
                            }
                            // make steps array
                            Steps[k] = (Steps_Array[Top_Array[2]][2] * 10 ) + 2;
                            Steps_Array[Top_Array[2]][2] = 0;
                            if ( Top_Array[2] != 7 ){
                                Top_Array[2] = Top_Array[2] + 1 ;
                            }                         
                            k = k +1;
                            if ( k == minimum_moves){
                                complete = true;
                                break;
                            }                           
                        }
                     
                    }
                }

                label1.setScaleX(2);
                label1.setScaleY(2);
                label1.setLayoutX(585);
                label1.setLayoutY(180);
                
                label5.setScaleX(2);
                label5.setScaleY(2);
                label5.setLayoutX(1100);
                label5.setLayoutY(180);
                
                label6.setScaleX(2);
                label6.setScaleY(2);
                label6.setLayoutX(1100);
                label6.setLayoutY(210);
                
                
                root.getChildren().add(label1);
                root.getChildren().add(label5);
                root.getChildren().add(label6);
                
                
                for(int i = 0 ; i < DiskNo ; i++){
                    Button b = new Button();
                    
                    Full_Array[i][0] = 1;
                    //Update disky array for disk y location
                    Disky_Array[i] = Ly;
                    b.setId(Buttons_Array[i]);
                    b.setStyle(Fx_Array[i]);
                    b.setPrefWidth(width);
                    
                    // Reduce Width of consecutive disks
                    width = width - 30;
                    b.setPrefHeight(40);
                    b.setLayoutX(Lx);
                    b.setLayoutY(Ly);
                    
                    // Change Y and x location of the new buttons
                    Ly = Ly - 40;
                    Lx = Lx + 15;                   
                    b.setOnAction((var event1) -> {
                        // instructions executed when the button is clicked
                                     
                        // Play click sound
                        clickPlayer.play();
                        clickPlayer.stop();
                        
                        // Convert button id to Disk 
                        String Temp2 = b.getId();
                        int Disk = Integer.parseInt(Temp2);
                        // Location change of arrow button when disk is clicked
                        //arrow.setLayoutY(Disky_Array[Disk-1]+ 105*(Disk*0.1 + 0.2) + 30);
                        arrow.setLayoutY(Disky_Array[Disk-1]);
                        arrow.setLayoutX(30);
                        arrow.setStyle(Fx_Array[Disk-1]);
                        
                        // Resetting the Disk Array
                        for(int j = 0 ; j<8 ; j++){
                            Disk_Array[j] = 0;
                        }
                        // Updating Disk Array to check which disk is pressed
                        Disk_Array[Disk] = 1;
                        // If conditions to move the disks to the correct rod x and y values
                        if (Full_Array[Disk-1][0] == 1)
                        {
                            // check if its the top disk and so valid move
                            if (rA.peek() == Disk){
                                if ( rodB_clicked == true){   
                                    if (rB.peek() < Disk || rB.peek() == 10 ){
                                        
                                        
                                        if ("r3".equals(selectedDifficulty.getId()) && (Steps[moves] != ((DiskNo +1 -Disk )*10 + 2)) ){
                                            label1.setText("Wrong Move! Try Again");
                                        }
                                        else{
                                            label1.setText("Nice!");
                                            int length = rB.size();
                                            rB.push(Disk);
                                            
                                           
                                            int y = (int) b.getLayoutY();
                                            int x = (int) b.getLayoutX();
                                            int disk = Disky_Array[Disk-1];
                                            String style = arrow.getStyle();
                                            //Create new translate transition;
                                            TranslateTransition transition = new TranslateTransition(duration,b);
                                            transition.setByX(400);
                                            b.setLayoutY(260 + (9-length)*40);
                                            arrow.setLayoutY(260 + (9-length)*40);
                                            Disky_Array[Disk-1] = 260 + (9-length)*40 ;
                                            transition.play();

                                            Button u = new Button();
                                            
                                            u.setScaleX(2);
                                            u.setScaleY(2);
                                            u.setPrefWidth(100);
                                            u.setText("UNDO");
                                            u.setLayoutX(200);
                                            u.setLayoutY(180);
                                            u.setOnAction((var event2) -> {
                                                 
                                                undo = true;
                                                TranslateTransition transition2 = new TranslateTransition(duration,b);
                                                transition2.setByX(-400);
                                                b.setLayoutY(y);
                                                arrow.setLayoutY(y);
                                                arrow.setStyle(style);
                                                Disky_Array[Disk-1] = disk ;
                                                rB.pop();
                                                rA.push(Disk);
                                                Full_Array[Disk-1][0] = 1;
                                                Full_Array[Disk-1][1] = 0;
                                                moves = moves - 1;
                                                label5.setText("Moves : " + moves);  
                                                u.setVisible(false);
                                                transition2.play();
                                            });
                                            root.getChildren().add(u);
                                            Full_Array[Disk-1][0] = 0;
                                            Full_Array[Disk-1][1] = 1;
                                            rA.pop();
                                            rodB_clicked = false;
                                            moves = moves +1;
                                            if (rC.size() == DiskNo +1 ) {
                                                System.out.println("Game Over");
                                                try {
                                                FileWriter fw = new FileWriter(fileName, true);
                                                    BufferedWriter bw = new BufferedWriter(fw);
                                                    bw.write(Name + " " + moves);
                                                    bw.newLine();
                                                    bw.close();
                                                } catch (IOException e) {
                                                System.out.println("An error occurred.");
                                                e.printStackTrace();
                                              }
                                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restart.fxml"));
                                                Stage newWindow = new Stage();
                                                newWindow.setResizable(false);
                                                Parent root2 = null;
                                                try {

                                                root2 = fxmlLoader.load();
                                                Scene scene = new Scene(root2, 600, 400);

                                                newWindow.setScene(scene);
                                                newWindow.initModality(Modality.APPLICATION_MODAL);
                                                newWindow.show();

                                                newWindow2.close();
                                                }
                                                catch (IOException e) {
                                                }
                                                
                                            }
                                            else if ( unlimited = false && moves == minimum_moves){
                                               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restart.fxml"));
                                                Stage newWindow = new Stage();
                                                newWindow.setResizable(false);
                                                Parent root2 = null;
                                                try {

                                                root2 = fxmlLoader.load();
                                                Scene scene = new Scene(root2, 600, 400);

                                                newWindow.setScene(scene);
                                                newWindow.initModality(Modality.APPLICATION_MODAL);
                                                newWindow.show();

                                                newWindow2.close();
                                                }
                                                catch (IOException e) {
                                                } 
                                                
                                            }
                                        }
                                    }
                                    else{
                                        label1.setText("disk on rod B is smaller than disk to be moved");
                                        System.out.println("disk on rod B is smaller than disk to be moved");
                                    }
                                }        
                                else if ( rodC_clicked == true){
                                    if (rC.peek() < Disk || rC.peek() == 10){
                                        if ("r3".equals(selectedDifficulty.getId()) && (Steps[moves] != ((DiskNo +1 -Disk)*10 + 3)) ){
                                            label1.setText("Wrong Move! Try Again");
                                        }
                                        else{
                                            label1.setText("Nice!");
                                            int length = rC.size();
                                            rC.push(Disk);
                                            int y = (int) b.getLayoutY();
                                            int disk = Disky_Array[Disk-1];
                                            String style = arrow.getStyle();
                                            //Create new translate transition;
                                            TranslateTransition transition = new TranslateTransition(duration,b);
                                            transition.setByX(810);
                                            b.setLayoutY(200);
                                            b.setLayoutY(260 + (9-length)*40);
                                            arrow.setLayoutY(260 + (9-length)*40);
                                            
                                            Disky_Array[Disk-1] = 260 + (9-length)*40 ;
                                            transition.play();
                                            
                                            Button u = new Button();
                                            u.setScaleX(2);
                                            u.setScaleY(2);
                                            u.setPrefWidth(100);
                                            u.setText("UNDO");
                                            u.setLayoutX(200);
                                            u.setLayoutY(180);
                                            u.setOnAction((var event2) -> {
                                                
                                                undo = true;
                                                TranslateTransition transition2 = new TranslateTransition(duration,b);
                                                transition2.setByX(-810);
                                                b.setLayoutY(y);
                                                arrow.setLayoutY(y);
                                                arrow.setStyle(style);
                                                Disky_Array[Disk-1] = disk ;
                                                rC.pop();
                                                rA.push(Disk);
                                                Full_Array[Disk-1][0] = 1;
                                                Full_Array[Disk-1][2] = 0;
                                                moves = moves - 1;
                                                label5.setText("Moves : " + moves);  
                                                u.setVisible(false);
                                                transition2.play();
                                            });
                                            root.getChildren().add(u);
                                            Full_Array[Disk-1][0] = 0;
                                            Full_Array[Disk-1][2] = 1;
                                            rA.pop();
                                            rodC_clicked = false;
                                            moves = moves +1;
                                            if (rC.size() == DiskNo +1) {
                                                label1.setText("Game Over");
                                                try {
                                                FileWriter fw = new FileWriter(fileName, true);
                                                    BufferedWriter bw = new BufferedWriter(fw);
                                                    bw.write(Name + " " + moves);
                                                    bw.newLine();
                                                    bw.close();
                                                } catch (IOException e) {
                                                System.out.println("An error occurred.");
                                                e.printStackTrace();
                                              }
                                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restart.fxml"));
                                                Stage newWindow = new Stage();
                                                newWindow.setResizable(false);
                                                Parent root2 = null;
                                                try {

                                                root2 = fxmlLoader.load();
                                                Scene scene = new Scene(root2, 600, 400);

                                                newWindow.setScene(scene);
                                                newWindow.initModality(Modality.APPLICATION_MODAL);
                                                newWindow.show();

                                                newWindow2.close();
                                                }
                                                catch (IOException e) {
                                                }
                                            }
                                            else if ( unlimited = false && moves == minimum_moves){
                                               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restart.fxml"));
                                                Stage newWindow = new Stage();
                                                newWindow.setResizable(false);
                                                Parent root2 = null;
                                                try {

                                                root2 = fxmlLoader.load();
                                                Scene scene = new Scene(root2, 600, 400);

                                                newWindow.setScene(scene);
                                                newWindow.initModality(Modality.APPLICATION_MODAL);
                                                newWindow.show();

                                                newWindow2.close();
                                                }
                                                catch (IOException e) {
                                                } 
                                                
                                            }
                                        }
                                    }
                                    else{
                                        label1.setText("disk on rod C is smaller than disk to be moved");
                                        System.out.println("disk on rod C is smaller than disk to be moved");
                                    }
                                }
                                else{
                                    label1.setText("Please select a rod first");
                                    System.out.println("Please select a rod first");
                                }
                            }else{
                                label1.setText("disk is not top of the stack");
                                System.out.println("disk is not top of the stack");
                            }
                        }
                        else if (Full_Array[Disk-1][1] == 1)
                        {
                            if (rB.peek() == Disk){
                                if ( rodA_clicked == true){ 
                                    if (rA.peek() < Disk ||  rA.peek() == 10){
                                        if ("r3".equals(selectedDifficulty.getId()) && (Steps[moves] != ((DiskNo+1 -Disk)*10 + 1)) ){
                                            label1.setText("Wrong Move! Try Again");
                                        }
                                        else{
                                            label1.setText("Nice!");
                                            int length = rA.size();
                                            rA.push(Disk);
                                            int y = (int) b.getLayoutY();
                                            int disk = Disky_Array[Disk-1];
                                            String style = arrow.getStyle();
                                            //Create new translate transition;
                                            TranslateTransition transition = new TranslateTransition(duration,b);
                                            transition.setByX(-400);
                                            b.setLayoutY(200);
                                            b.setLayoutY(260 + (9-length)*40);
                                            arrow.setLayoutY(260 + (9-length)*40);
                                            Disky_Array[Disk-1] = 260 + (9-length)*40 ;
                                            transition.play();
                                            
                                            Button u = new Button();
                                            u.setScaleX(2);
                                            u.setScaleY(2);
                                            u.setPrefWidth(100);
                                            u.setText("UNDO");
                                            u.setLayoutX(200);
                                            u.setLayoutY(180);
                                            u.setOnAction((var event2) -> {
                                                 
                                                undo = true;
                                                TranslateTransition transition2 = new TranslateTransition(duration,b);
                                                transition2.setByX(+400);
                                                b.setLayoutY(y);
                                                arrow.setLayoutY(y);
                                                arrow.setStyle(style);
                                                Disky_Array[Disk-1] = disk ;
                                                rA.pop();
                                                rB.push(Disk);
                                                Full_Array[Disk-1][1] = 1;
                                                Full_Array[Disk-1][0] = 0;
                                                moves = moves - 1;
                                                label5.setText("Moves : " + moves);  
                                                u.setVisible(false);
                                                transition2.play();
                                            });
                                            root.getChildren().add(u);
                                            Full_Array[Disk-1][1] = 0;
                                            Full_Array[Disk-1][0] = 1;
                                            rB.pop();
                                            rodA_clicked = false;
                                            moves = moves +1;
                                            if (rC.size() == DiskNo +1) {
                                                label1.setText("Game Over");
                                                try {
                                                FileWriter fw = new FileWriter(fileName, true);
                                                    BufferedWriter bw = new BufferedWriter(fw);
                                                    bw.write(Name + " " + moves);
                                                    bw.newLine();
                                                    bw.close();
                                                } catch (IOException e) {
                                                System.out.println("An error occurred.");
                                                e.printStackTrace();
                                              }
                                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restart.fxml"));
                                                Stage newWindow = new Stage();
                                                newWindow.setResizable(false);
                                                Parent root2 = null;
                                                try {

                                                root2 = fxmlLoader.load();
                                                Scene scene = new Scene(root2, 600, 400);

                                                newWindow.setScene(scene);
                                                newWindow.initModality(Modality.APPLICATION_MODAL);
                                                newWindow.show();

                                                newWindow2.close();
                                                }
                                                catch (IOException e) {
                                                }
                                            }
                                            else if ( unlimited = false && moves == minimum_moves){
                                               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restart.fxml"));
                                                Stage newWindow = new Stage();
                                                newWindow.setResizable(false);
                                                Parent root2 = null;
                                                try {

                                                root2 = fxmlLoader.load();
                                                Scene scene = new Scene(root2, 600, 400);

                                                newWindow.setScene(scene);
                                                newWindow.initModality(Modality.APPLICATION_MODAL);
                                                newWindow.show();

                                                newWindow2.close();
                                                }
                                                catch (IOException e) {
                                                } 
                                                
                                            }
                                        }
                                    }
                                    else{
                                        label1.setText("disk on rod A is smaller than disk to be moved");
                                        System.out.println("disk on rod A is smaller than disk to be moved");
                                    }
                                }
                                else if ( rodC_clicked == true){
                                    if (rC.peek() < Disk || rC.peek() == 10){
                                        if ("r3".equals(selectedDifficulty.getId()) && (Steps[moves] != ((DiskNo+1 -Disk)*10 + 3) )){
                                            label1.setText("Wrong Move! Try Again");
                                        }
                                        else{
                                            label1.setText("Nice!");
                                            int length = rC.size();
                                            rC.push(Disk);
                                            int y = (int) b.getLayoutY();
                                            int disk = Disky_Array[Disk-1];
                                            String style = arrow.getStyle();
                                            //Create new translate transition;
                                            TranslateTransition transition = new TranslateTransition(duration,b);
                                            transition.setByX(410);
                                            b.setLayoutY(200);
                                            b.setLayoutY(260 + (9-length)*40);
                                            arrow.setLayoutY(260 + (9-length)*40);
                                            Disky_Array[Disk-1] = 260 + (9-length)*40 ;
                                            transition.play();
                                            
                                            Button u = new Button();
                                            u.setScaleX(2);
                                            u.setScaleY(2);
                                            u.setPrefWidth(100);
                                            u.setText("UNDO");
                                            u.setLayoutX(200);
                                            u.setLayoutY(180);
                                            u.setOnAction((var event2) -> {
                                                 
                                                undo = true;
                                                TranslateTransition transition2 = new TranslateTransition(duration,b);
                                                transition2.setByX(-410);
                                                b.setLayoutY(y);
                                                arrow.setLayoutY(y);
                                                arrow.setStyle(style);
                                                Disky_Array[Disk-1] = disk ;
                                                rC.pop();
                                                rB.push(Disk);
                                                Full_Array[Disk-1][1] = 1;
                                                Full_Array[Disk-1][2] = 0;
                                                moves = moves - 1;
                                                label5.setText("Moves : " + moves);  
                                                u.setVisible(false);
                                                transition2.play();
                                            });
                                            root.getChildren().add(u);
                                            Full_Array[Disk-1][1] = 0;
                                            Full_Array[Disk-1][2] = 1;
                                            rB.pop();
                                            rodC_clicked = false;
                                            moves = moves +1;
                                            if (rC.size() == DiskNo +1) {
                                                label1.setText("Game Over");
                                                try {
                                                FileWriter fw = new FileWriter(fileName, true);
                                                    BufferedWriter bw = new BufferedWriter(fw);
                                                    bw.write(Name + " " + moves);
                                                    bw.newLine();
                                                    bw.close();
                                                } catch (IOException e) {
                                                System.out.println("An error occurred.");
                                                e.printStackTrace();
                                              }
                                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restart.fxml"));
                                                Stage newWindow = new Stage();
                                                newWindow.setResizable(false);
                                                Parent root2 = null;
                                                try {

                                                root2 = fxmlLoader.load();
                                                Scene scene = new Scene(root2, 600, 400);

                                                newWindow.setScene(scene);
                                                newWindow.initModality(Modality.APPLICATION_MODAL);
                                                newWindow.show();

                                                newWindow2.close();
                                                }
                                                catch (IOException e) {
                                                }
                                            }
                                            else if ( unlimited = false && moves == minimum_moves){
                                               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restart.fxml"));
                                                Stage newWindow = new Stage();
                                                newWindow.setResizable(false);
                                                Parent root2 = null;
                                                try {

                                                root2 = fxmlLoader.load();
                                                Scene scene = new Scene(root2, 600, 400);

                                                newWindow.setScene(scene);
                                                newWindow.initModality(Modality.APPLICATION_MODAL);
                                                newWindow.show();

                                                newWindow2.close();
                                                }
                                                catch (IOException e) {
                                                } 
                                                
                                            }
                                        }
                                    }
                                    else{
                                        label1.setText("disk on rod C is smaller than disk to be moved");
                                        System.out.println("disk on rod C is smaller than disk to be moved");
                                    }
                                }
                                else{
                                    label1.setText("Please select a rod first");
                                    System.out.println("Please select a rod first");
                                }
                            }
                            else{
                                label1.setText("disk is not top of the stack");
                                System.out.println("disk is not top of the stack");
                            }
                        }
                        else
                        {
                            if (rC.peek() == Disk){
                                if ( rodA_clicked == true){ 
                                    if (rA.peek() < Disk || rA.peek() == 10){
                                        if ("r3".equals(selectedDifficulty.getId()) && (Steps[moves] != ((DiskNo+1-Disk)*10 + 1)) ){
                                            label1.setText("Wrong Move! Try Again");
                                        }
                                        else{
                                            label1.setText("Nice!");
                                            int length = rA.size();
                                            rA.push(Disk);
                                            
                                            
                                            int y = (int) b.getLayoutY();
                                            int disk = Disky_Array[Disk-1];
                                            String style = arrow.getStyle();
                                            //Create new translate transition;
                                            TranslateTransition transition = new TranslateTransition(duration,b);
                                            transition.setByX(-810);
                                            b.setLayoutY(200);
                                            b.setLayoutY(260 + (9-length)*40);
                                            arrow.setLayoutY(260 + (9-length)*40);
                                            Disky_Array[Disk-1] = 260 + (9-length)*40 ;
                                            transition.play();

                                            Button u = new Button();
                                            u.setScaleX(2);
                                            u.setScaleY(2);
                                            u.setPrefWidth(100);
                                            u.setText("UNDO");
                                            u.setLayoutX(200);
                                            u.setLayoutY(180);
                                            u.setOnAction((var event2) -> {
                                                 
                                                undo = true;
                                                TranslateTransition transition2 = new TranslateTransition(duration,b);
                                                transition2.setByX(+810);
                                                b.setLayoutY(y);
                                                arrow.setLayoutY(y);
                                                arrow.setStyle(style);
                                                Disky_Array[Disk-1] = disk ;
                                                rA.pop();
                                                rC.push(Disk);
                                                Full_Array[Disk-1][2] = 1;
                                                Full_Array[Disk-1][0] = 0;
                                                moves = moves - 1;
                                                label5.setText("Moves : " + moves);  
                                                u.setVisible(false);
                                                transition2.play();
                                            });
                                            root.getChildren().add(u);
                                            Full_Array[Disk-1][2] = 0;
                                            Full_Array[Disk-1][0] = 1;
                                            rC.pop();
                                            rodA_clicked = false;
                                            moves = moves +1;
                                            if (rC.size() == DiskNo +1) {
                                                label1.setText("Game Over");
                                                try {
                                                    FileWriter fw = new FileWriter(fileName, true);
                                                    BufferedWriter bw = new BufferedWriter(fw);
                                                    bw.write(Name + " " + moves);
                                                    bw.newLine();
                                                    bw.close();

                                                    
                                                    } catch (IOException e) {
                                                System.out.println("An error occurred.");
                                                e.printStackTrace();
                                              }
                                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restart.fxml"));
                                                Stage newWindow = new Stage();
                                                newWindow.setResizable(false);
                                                Parent root2 = null;
                                                try {

                                                root2 = fxmlLoader.load();
                                                Scene scene = new Scene(root2, 600, 400);

                                                newWindow.setScene(scene);
                                                newWindow.initModality(Modality.APPLICATION_MODAL);
                                                newWindow.show();

                                                newWindow2.close();
                                                }
                                                catch (IOException e) {
                                                }
                                            }
                                            else if ( unlimited = false && moves == minimum_moves){
                                               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restart.fxml"));
                                                Stage newWindow = new Stage();
                                                newWindow.setResizable(false);
                                                Parent root2 = null;
                                                try {

                                                root2 = fxmlLoader.load();
                                                Scene scene = new Scene(root2, 600, 400);

                                                newWindow.setScene(scene);
                                                newWindow.initModality(Modality.APPLICATION_MODAL);
                                                newWindow.show();

                                                newWindow2.close();
                                                }
                                                catch (IOException e) {
                                                } 
                                                
                                            }
                                        }
                                    }
                                    else{
                                        label1.setText("disk on rod A is smaller than disk to be moved");
                                        System.out.println("disk on rod A is smaller than disk to be moved");
                                    }
                                }
                                else if ( rodB_clicked == true){
                                    label1.setText("Nice!");
                                    if (rB.peek()< Disk || rB.peek() == 10 ){
                                        if ("r3".equals(selectedDifficulty.getId()) && (Steps[moves] != ((DiskNo+1-Disk)*10 + 2)) ){
                                            label1.setText("Wrong Move! Try Again");
                                        }
                                        else{
                                            int length = rB.size();
                                            rB.push(Disk);
                                            int y = (int) b.getLayoutY();
                                            int disk = Disky_Array[Disk-1];
                                            String style = arrow.getStyle();
                                            //Create new translate transition;
                                            TranslateTransition transition = new TranslateTransition(duration,b);
                                            transition.setByX(-410);
                                            b.setLayoutY(200);
                                            b.setLayoutY(260 + (9-length)*40);
                                            arrow.setLayoutY(260 + (9-length)*40);
                                            Disky_Array[Disk-1] = 260 + (9-length)*40 ;
                                            transition.play();

                                            Button u = new Button();
                                            u.setScaleX(2);
                                            u.setScaleY(2);
                                            u.setPrefWidth(100);
                                            u.setText("UNDO");
                                            u.setLayoutX(200);
                                            u.setLayoutY(180);
                                            u.setOnAction((var event2) -> {
                                                
                                                undo = true;
                                                TranslateTransition transition2 = new TranslateTransition(duration,b);
                                                transition2.setByX(+410);
                                                b.setLayoutY(y);
                                                arrow.setLayoutY(y);
                                                arrow.setStyle(style);
                                                Disky_Array[Disk-1] = disk ;
                                                rB.pop();
                                                rC.push(Disk);
                                                Full_Array[Disk-1][2] = 1;
                                                Full_Array[Disk-1][1] = 0;
                                                moves = moves - 1;
                                                label5.setText("Moves : " + moves);  
                                                u.setVisible(false);
                                                transition2.play();
                                            });
                                            root.getChildren().add(u);
                                            Full_Array[Disk-1][2] = 0;
                                            Full_Array[Disk-1][1] = 1;
                                            rC.pop();
                                            rodB_clicked = false;
                                            moves = moves +1;
                                            if (rC.size() == DiskNo +1 ) {
                                                label1.setText("Game Over");
                                                try {
                                                FileWriter fw = new FileWriter(fileName, true);
                                                    BufferedWriter bw = new BufferedWriter(fw);
                                                    bw.write(Name + " " + moves);
                                                    bw.newLine();
                                                    bw.close();
                                                } catch (IOException e) {
                                                System.out.println("An error occurred.");
                                                e.printStackTrace();
                                              }
                                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restart.fxml"));
                                                Stage newWindow = new Stage();
                                                newWindow.setResizable(false);
                                                Parent root2 = null;
                                                try {

                                                root2 = fxmlLoader.load();
                                                Scene scene = new Scene(root2, 600, 400);

                                                newWindow.setScene(scene);
                                                newWindow.initModality(Modality.APPLICATION_MODAL);
                                                newWindow.show();

                                                newWindow2.close();
                                                }
                                                catch (IOException e) {
                                                }
                                            }
                                            else if ( unlimited = false && moves == minimum_moves){
                                               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restart.fxml"));
                                                Stage newWindow = new Stage();
                                                newWindow.setResizable(false);
                                                Parent root2 = null;
                                                try {

                                                root2 = fxmlLoader.load();
                                                Scene scene = new Scene(root2, 600, 400);

                                                newWindow.setScene(scene);
                                                newWindow.initModality(Modality.APPLICATION_MODAL);
                                                newWindow.show();

                                                newWindow2.close();
                                                }
                                                catch (IOException e) {
                                                } 
                                                
                                            }
                                        }
                                    }
                                    else{
                                        label1.setText("disk on rod B is smaller than disk to be moved");
                                        System.out.println("disk on rod B is smaller than disk to be moved");
                                    }
                                }
                                else{
                                    label1.setText("Please select a rod first");
                                    System.out.println("Please select a rod first");
                                }
                            }
                            else{
                                label1.setText("disk is not top of the stack");
                                System.out.println("disk is not top of the stack");
                            }
                        }
                    label5.setText("Moves : " + moves + " / " + minimum_moves);    
                        
                    });
                    root.getChildren().add(b);
   
                }
                Scene scene = new Scene(root, 1280, 720);
                newWindow2.setScene(scene);
                newWindow2.initModality(Modality.APPLICATION_MODAL);
                newWindow2.show();
                
            }
            catch (IOException e) {
            }
            }
           
        }
    
    
    @FXML
    private void rodA(ActionEvent event) {
        rodB_clicked = false;
        rodA_clicked = true;
        rodC_clicked = false;
    }
    @FXML
    private void rodB(ActionEvent event) {
        rodB_clicked = true;
        rodA_clicked = false;
        rodC_clicked = false;
    }
    @FXML
    private void rodC(ActionEvent event) {
        rodB_clicked = false;
        rodA_clicked = false;
        rodC_clicked = true;
    }
    
    @FXML
    private void secret(ActionEvent event) {
        Random rand = new Random();
        Pane1.setStyle(Fx_Array[rand.nextInt(8)]);   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
