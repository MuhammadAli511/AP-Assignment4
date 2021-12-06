package calculator;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BmiDataController {
	
	public static boolean weightB = false;
	public static boolean heightB = false;
	
	
	@FXML
	ObservableList<String> weightList = FXCollections.observableArrayList("Kg","Pound");
	public ChoiceBox weightBox;
	public Label weight;
	public Label height;
	public Pane base;
	
	@FXML
	public void initialize()
	{
		weightBox.setValue("Kg");
		weightBox.setItems(weightList);
	}
	
	public void clearWeight()
	{
		String empty = "0";
		weight.setText(empty);
	}
	
	public void clearHeight()
	{
		String empty = "0";
		height.setText(empty);
	}
	
	public void displayNumber(String num)
	{
		if (weightB == true)
		{
			if (weight.getText().equals("0") == true)
			{
				weight.setText("");
			}
			weight.setText(weight.getText() + num);
		}
		else if (heightB == true)
		{
			if (height.getText().equals("0") == true)
			{
				height.setText("");
			}
			height.setText(height.getText() + num);
		}
	}
	
	public void numberClick(MouseEvent click)
	{
		if (weightB == true || heightB == true)
		{
			Object node = click.getSource();
			Button button = (Button)node;
			String num = button.getText();
			displayNumber(num);
		}
	}
	
	public void clearCharacter(MouseEvent click)
	{
		if (weightB == true)
		{
			String weight_P = weight.getText();
			int len = weight_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					weight.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += weight_P.charAt(i);
					}
					weight.setText(update);
				}
			}
		}
		else if (heightB == true)
		{
			String height_P = height.getText();
			int len = height_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					height.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += height_P.charAt(i);
					}
					height.setText(update);
				}
			}
		}
	}
	
	public void originalClick(MouseEvent click)
	{
		weight.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: groove");
		height.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: groove");
		weightB = true;
		heightB = false;
	}
	
	public void discountClick(MouseEvent click)
	{
		weight.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: groove");
		height.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: groove");
		weightB = true;
		heightB = false;
	}
	
	public void backToCalculator() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("base.fxml"));
		Stage window = (Stage) base.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	
	public void clearScreen(MouseEvent click)
	{
		clearWeight();
		clearHeight();
	}
}
