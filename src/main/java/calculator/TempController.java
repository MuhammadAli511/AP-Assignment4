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

public class TempController {

	public static boolean temp1B = false;
	public static boolean temp2B = false;
	
	ObservableList<String> temp1List = FXCollections.observableArrayList("celsius","fahrenheit");
	ObservableList<String> temp2List = FXCollections.observableArrayList("celsius","fahrenheit");
	public ChoiceBox<String> temp1Box;
	public ChoiceBox<String> temp2Box;
	public Label temp1;
	public Label temp2;
	public Label base;
	
	@FXML
	public void initialize()
	{
		temp1Box.setValue("celsius");
		temp1Box.setItems(temp1List);
		temp2Box.setValue("fahrenheit");
		temp2Box.setItems(temp2List);
	}
	
	public void cleartemp1()
	{
		String empty = "0";
		temp1.setText(empty);
	}
	
	public void cleartemp2()
	{
		String empty = "0";
		temp2.setText(empty);
	}
	
	public void displayNumber(String num)
	{
		if (temp1B == true)
		{
			if (temp1.getText().equals("0") == true)
			{
				temp1.setText("");
			}
			temp1.setText(temp1.getText() + num);
		}
		else if (temp2B == true)
		{
			if (temp2.getText().equals("0") == true)
			{
				temp2.setText("");
			}
			temp2.setText(temp2.getText() + num);
		}
	}
	
	public void numberClick(MouseEvent click)
	{
		if (temp1B == true || temp2B == true)
		{
			Object node = click.getSource();
			Button button = (Button)node;
			String num = button.getText();
			displayNumber(num);
			calculate();
		}
	}
	
	public void calculate()
	{
		String tempS1 = temp1Box.getValue();
		String tempS2 = temp2Box.getValue();
		if (tempS1.equals("celsius") == true && tempS2.equals("fahrenheit") == true)
		{
			if (temp1B == true)
			{
				Double km = Double.valueOf(temp1.getText());
				Double met = (km * (9/5)) +32;
				String accS = Double.toString(met);
				temp2.setText(accS);
			}
			else if (temp2B == true)
			{
				Double m1 = Double.valueOf(temp2.getText());
				Double km = (m1 - 32)*(5/9);
				String accS = Double.toString(km);
				temp1.setText(accS);
			}
		}
		else if (tempS1.equals("fahrenheit") == true && tempS2.equals("celsius") == true)
		{
			if (temp1B == true)
			{
				Double m1 = Double.valueOf(temp1.getText());
				Double km = (m1 - 32)*(5/9);
				String accS = Double.toString(km);
				temp2.setText(accS);
			}
			else if (temp2B == true)
			{
				Double km = Double.valueOf(temp2.getText());
				Double m1 = (km * (9/5)) +32;
				String accS = Double.toString(m1);
				temp1.setText(accS);
			}
		}
		else if (tempS1.equals("fahrenheit") == true && tempS2.equals("fahrenheit") == true)
		{
			if (temp1B == true)
			{
				temp2.setText(temp1.getText());
			}
			else if (temp2B == true)
			{
				temp1.setText(temp2.getText());
			}
		}
		else if (tempS1.equals("celsius") == true && tempS2.equals("celsius") == true)
		{
			if (temp1B == true)
			{
				temp2.setText(temp1.getText());
			}
			else if (temp2B == true)
			{
				temp1.setText(temp2.getText());
			}
		}
	}
	
	
	public void clearCharacter(MouseEvent click)
	{
		if (temp1B == true)
		{
			String temp1_P = temp1.getText();
			int len = temp1_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					temp2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += temp1_P.charAt(i);
					}
					temp1.setText(update);
				}
			}
		}
		else if (temp2B == true)
		{
			String height_P = temp2.getText();
			int len = height_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					temp2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += height_P.charAt(i);
					}
					temp2.setText(update);
				}
			}
		}
	}
	
	public void originalClick(MouseEvent click)
	{
		cleartemp1();
		cleartemp2();
		temp1.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		temp2.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		temp1B = true;
		temp2B = false;
	}
	
	public void discountClick(MouseEvent click)
	{
		cleartemp1();
		cleartemp2();
		temp1.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		temp2.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		temp1B = false;
		temp2B = true;
	}
	
	public void backToCalculator() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("base.fxml"));
		Stage window = (Stage) base.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	
	public void clearScreen(MouseEvent click)
	{
		cleartemp1();
		cleartemp2();
	}
}
