package crypto3;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Window extends Application {  
	
    private Stage primaryStage;
    private Scene scene;
    private GridPane grid;
    private Text title;
    private HBox hbTitle, hbUserTextField, hbPW, hbBtn, hbBtn1, hbBtn2,hbBtn3,hbBtn4,hbBtn5,hbparameterp,hbparameterq,hbparameterg,hbparametery,hbparameterx,hbfilekey;
    private Label userName, pw;
    private Button btn, btn1, btn2,btn3,btn4,btn5;
    private TextField userTextField, pwBox, parameterp, parameterq,parameterg,parametery,parameterx, filekey;
    File test,test1;
    DSA Part1;
    String test2,test3,p,q,g,y;
    
    private void prepareScene(){
        
        grid = new GridPane();
        grid.setId("gridPane");
        
        title = new Text("217123");
        title.setId("title");
        
        hbTitle = new HBox();
        hbTitle.setId("hbTitle");
        hbTitle.getChildren().add(title);

        grid.add(hbTitle, 0, 0, 2, 1); 
        
        userName = new Label("File Path:");
        userName.setId("userName");
        grid.add(userName, 0, 1);
        
        userTextField = new TextField();
        userTextField.setId("userTextField");
       
        hbUserTextField = new HBox();
        hbUserTextField.setId("hbUserTextField");
        hbUserTextField.getChildren().add(userTextField);
        grid.add(hbUserTextField, 1, 1);
        
        userName = new Label("Key Path:");
        userName.setId("userName");
        grid.add(userName, 0, 7);
        
        filekey = new TextField();
        filekey.setId("userTextField");
       
        hbfilekey = new HBox();
        hbfilekey.setId("filekey");
        hbfilekey.getChildren().add(filekey);
        grid.add(hbfilekey, 1, 7);
  
         
        btn = new Button("Sign the file");
        btn.setId("btnSing");
        
        hbBtn = new HBox(10);
        hbBtn.setId("hbBtn");
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 12, 2, 1);
        
        btn3 = new Button("Generate Key ");
        btn3.setId("btnSing");
        
        hbBtn3 = new HBox(10);
        hbBtn.setId("hbBtn");
        hbBtn3.getChildren().add(btn3);
        grid.add(hbBtn3, 0, 9, 2, 1);
        
        btn1 = new Button("Save Public Key");
        btn1.setId("btnSing");
        
        hbBtn1 = new HBox(10);
        hbBtn.setId("hbBtn");
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 1, 9, 2, 1);
        
        btn4 = new Button("Choose Public Key");
        btn4.setId("btnSing");
        
        hbBtn4 = new HBox(10);
        hbBtn4.setId("hbBtn");
        hbBtn4.getChildren().add(btn4);
        grid.add(hbBtn4, 0, 10, 1, 1);
        
        btn5 = new Button("Verification");
        btn5.setId("btnSing");
        
        hbBtn5 = new HBox(10);
        hbBtn.setId("hbBtn");
        hbBtn5.getChildren().add(btn5);
        grid.add(hbBtn5, 1, 13, 2, 1);
        
        pw = new Label("P:");
        pw.setId("userName");
        grid.add(pw, 0, 2);
        
        parameterp = new TextField(); 
        parameterp.setId("userTextField");
        
        hbparameterp = new HBox();
        hbparameterp.setId("parameterp");
        hbparameterp.getChildren().add(parameterp);
        grid.add(hbparameterp, 1, 2);
        
        pw = new Label ("Q: ");
        pw.setId("userName");
        grid.add(pw, 0, 3);
        
        parameterq = new TextField(); 
        parameterq.setId("userTextField");
        
        hbparameterq = new HBox();
        hbparameterq.setId("parameterq");
        hbparameterq.getChildren().add(parameterq);
        grid.add(hbparameterq, 1, 3);
        
        
        pw = new Label ("G: ");
        pw.setId("userName");
        grid.add(pw, 0, 4);
        
        parameterg = new TextField(); 
        parameterg.setId("userTextField");
        
        hbparameterg = new HBox();
        hbparameterg.setId("parameterg");
        hbparameterg.getChildren().add(parameterg);
        grid.add(hbparameterg, 1, 4);
        
        pw = new Label ("Y: ");
        pw.setId("userName");
        grid.add(pw, 0, 5);
        
        parametery = new TextField(); 
        parametery.setId("userTextField");
        
        hbparametery = new HBox();
        hbparametery.setId("parametery");
        hbparametery.getChildren().add(parametery);
        grid.add(hbparametery, 1, 5);
        
        pw = new Label ("X: ");
        pw.setId("userName");
        grid.add(pw, 0, 6);
        
        parameterx = new TextField(); 
        parameterx.setId("userTextField");
        
        hbparameterx = new HBox();
        hbparameterx.setId("parameterx");
        hbparameterx.getChildren().add(parameterx);
        grid.add(hbparameterx, 1, 6);
       
        btn3.setOnAction(new EventHandler<ActionEvent>() {
           
            
            
            public void handle(ActionEvent event) {
            	Part1 = new DSA(userTextField.getText());
            	
            	try {
					
					Part1.InitKey();
					Part1.GetNumberKey();
					
					parameterp.setText(Part1.getPubKeyP());
					parameterq.setText(Part1.getPubKeyQ());
					parameterg.setText(Part1.getPubKeyG());
					parametery.setText(Part1.getPubKeyY());
					parameterx.setText(Part1.getPrivKey());
					
					
					
				} 
				 catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchProviderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
            	
               
            	   
            	  btn1.setOnAction(new EventHandler<ActionEvent>() {
                       @Override
                       public void handle(ActionEvent event2) {
                    	   try {
                    		   
                    		   
							Part1.SavePubKey();
							
							Alert alert = new Alert(AlertType.INFORMATION);
        					alert.setTitle("Information Dialog");
        					alert.setHeaderText(null);
        					alert.setContentText("Klucz publiczny zapisany na tej samej œcie¿ce folderu z projektem.");
        					alert.showAndWait();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
                    	   
                       }
            		
            
            	} );
            	  
            	  
            	  
            	  btn.setOnAction(new EventHandler<ActionEvent>() {
                      

					@Override
                      public void handle(ActionEvent event3) {
                     
                    	  try {
							Part1.ReadData(userTextField.getText());
							test2=Part1.podpis;
							//Part1.SingDataFile();
							//Part1.SaveSignature();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvalidKeyException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SignatureException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchProviderException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
                          
           		
                      }
           	} );
            	  
            	  
            	  
            }
            
            }
            
        );
         
        //--------------------
 
        
        btn5.setOnAction(new EventHandler<ActionEvent>() {
        
            @Override
            public void handle(ActionEvent event) {
            
            	DSAVerify Part2 = new DSAVerify(userTextField.getText(),filekey.getText());
            	try {
            		//Part2.jeden = Part1;
            		//Part2.setTest(Part1.dupa);
            		System.out.println(userTextField.getText());
            		System.out.println(filekey.getText());
            		System.out.println(test2);
            		Part2.setTest(test2);
            		System.out.println(Part2.FILE_NAME_SIG);
					Part2.ReadDATA();
					Part2.TakeKey();
					//Part2.CheckSig();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
				
					if(Part2.CheckSig()) {
						alert.setContentText("True");
						alert.showAndWait();
					}
					else {
						alert.setContentText("False");
						alert.showAndWait();
					}
					Part2.data = null;
					Part2.sigBytes = null;
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchProviderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SignatureException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	
            		
            	}

			
            	
            } );
        //--------------------
           
            
        btn2 = new Button("Choose a file");
        btn2.setId("btnSing");
        
        hbBtn2 = new HBox(10);
        hbBtn2.setId("hbBtn");
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 1, 10, 2, 1);
        
        btn4.setOnAction(new EventHandler<ActionEvent>() {
        	
        
            public void handle(ActionEvent event) {
            	FileChooser fileChooser = new FileChooser();
            	fileChooser.setTitle("Open Resource File");
            	test1 = fileChooser.showOpenDialog(null);
            	filekey.setText(test1.getAbsolutePath());
            }
        });
        //--------------------
        
  btn2.setOnAction(new EventHandler<ActionEvent>() {
        	
        	
            
            public void handle(ActionEvent event) {
            	FileChooser fileChooser = new FileChooser();
            	fileChooser.setTitle("Open Resource File");
            	test = fileChooser.showOpenDialog(null);
            	userTextField.setText(test.getAbsolutePath());
            }
        });
        
        
        scene = new Scene(grid);
        
        scene.getStylesheets().add(Window.class.getResource("Login.css").toExternalForm());
    }
    
    
    public void start(Stage stage) {
        
        primaryStage = new Stage();
        
        prepareScene();
        
        primaryStage.setTitle("Cryptograpfy DSA");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
	        
	   public static void main(String args[]){           
	      launch(args);      
	   } 
	} 