package continuas_effective_managment;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;


public class GUI extends Application implements EventHandler<ActionEvent>{
	private Button submit,backToQuestion,printPdf,producePdf;
	private Stage window;
	private Scene questionScene,outPutScene,pdfSetup;
	private TextField typeTruckInput,noVehInput,noHrsInput,pdfFileName;
	private int noVeh;
	private int noHrs;
	private String managReturn;

    public static void main(String[] args) throws Exception {
    	//PdfOutput print = new PdfOutput();
    	//print.createManagementPdf("printout.pdf");
    	launch(args);
    }

	/**
	* This method will create  GridPane which will be made and returned to better shorten the re-use of the code
	* @return GridPane a new pane created is returned from the mehtod.*/
	public GridPane createNode(){
		GridPane tempBoard = new GridPane();
	    	tempBoard.setPadding(new Insets(5));
	    	tempBoard.setHgap(5);
	    	tempBoard.setVgap(5);
	    	ColumnConstraints column1 = new ColumnConstraints(500);
	    	ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
	    	column2.setHgrow(Priority.ALWAYS);
	    	tempBoard.getColumnConstraints().addAll(column1, column2);
	    	return tempBoard;
	}

	private Scene runQuestionWindow() {
		GridPane node = this.createNode();
		Label typeTruck = new Label(" What type of truck, Either write 'HGV' for Heavy \n Goods Vehicle Or 'PSV' for Public Service Vehicle.");
		node.add(typeTruck,0,1);
		typeTruckInput = new TextField();
		typeTruckInput.setMaxWidth(200);
	        node.add(typeTruckInput, 0, 2);
	        Label noVeh = new Label(" How many trucks do you have.");
		node.add(noVeh,0,3);
		noVehInput = new TextField();
		noVehInput.setMaxWidth(200);
	        node.add(noVehInput, 0, 4);

		Label noHrs = new Label(" How many hours do you spend on managment.");
		node.add(noHrs,0,5);
		noHrsInput = new TextField();
		noHrsInput.setMaxWidth(200);
	        node.add(noHrsInput, 0, 6);

	        submit = new Button("Submit");
		submit.setOnAction(this);
		node.add(submit,0,7);

		return questionScene = new Scene(node, 350, 250, Color.WHITE);
	}

	private Scene runOutputWindow(){
		GridPane node = this.createNode();
		noVeh = Integer.parseInt(noVehInput.getText());
		noHrs = Integer.parseInt(noHrsInput.getText());
		AppDriver test1 = new AppDriver(noVeh,noHrs);
		test1.addDataToLookup();
		if(noHrs > test1.getNoVehicles()){
			noHrs = test1.getNoVehicles();
		}
		managReturn =  test1.run(noVeh,noHrs);
		Text output = new Text(10, 50, managReturn);
		output.setFont(new Font(12));
		output.setWrappingWidth(344);
		output.setTextAlignment(TextAlignment.JUSTIFY);
		node.add(output,0,1);
		backToQuestion = new Button("Back");
		backToQuestion.setOnAction(this);
		node.add(backToQuestion,0,2);
		printPdf = new Button("Print PDF");// open new gui to print pdf
		printPdf.setOnAction(this);
		node.add(printPdf,0,3);
		return outPutScene = new Scene(node, 350, 250, Color.WHITE);
	}

	private Scene pdfSetupWindow() {
		GridPane node = this.createNode();
		Text enterFileName = new Text("Pleas Enter The file Name.");
		enterFileName.setFont(Font.font ("TIMES_ROMAN", 16));
		node.add(enterFileName,0,2);
		pdfFileName = new TextField();
		pdfFileName.setMaxWidth(200);
        	node.add(pdfFileName, 0, 3);

        	producePdf = new Button("Make PDF");// open new gui to print pdf
        	producePdf.setOnAction(this);
		node.add(producePdf,0,4);
		node.add(backToQuestion,0,5);

	        return pdfSetup = new Scene(node, 350, 250, Color.WHITE);
	}


	private void makePdf(){
		PdfOutput pdf = new PdfOutput();
		try {
			pdf.createManagementPdf(pdfFileName.getText()+".pdf",managReturn);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    public void start(Stage primaryStage) {
		window = primaryStage;
		window.setTitle("Managment");
		window.setScene(this.runQuestionWindow());
		window.show();
    }

    @Override
	public void handle(ActionEvent event) {
		if(event.getSource() == submit) {
			window.setScene(this.runOutputWindow());

		}
		else if(event.getSource() == backToQuestion) {
			window.setScene(questionScene);
		}
		else if(event.getSource() == printPdf) {
			window.setScene(this.pdfSetupWindow());
		}
		else if(event.getSource() == producePdf) {
			this.makePdf();
		}
	}
}
