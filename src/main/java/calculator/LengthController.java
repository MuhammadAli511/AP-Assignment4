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

public class LengthController {

	public static boolean length1B = false;
	public static boolean length2B = false;
	
	ObservableList<String> length1List = FXCollections.observableArrayList("km","meter");
	ObservableList<String> length2List = FXCollections.observableArrayList("km","meter");
	public ChoiceBox<String> length1Box;
	public ChoiceBox<String> length2Box;
	public Label length1;
	public Label length2;
	public Label base;
	
	@FXML
	public void initialize()
	{
		length1Box.setValue("km");
		length1Box.setItems(length1List);
		length2Box.setValue("meter");
		length2Box.setItems(length2List);
	}
	
	public void clearLength1()
	{
		String empty = "0";
		length1.setText(empty);
	}
	
	public void clearLength2()
	{
		String empty = "0";
		length2.setText(empty);
	}
	
	public void displayNumber(String num)
	{
		if (length1B == true)
		{
			if (length1.getText().equals("0") == true)
			{
				length1.setText("");
			}
			length1.setText(length1.getText() + num);
		}
		else if (length2B == true)
		{
			if (length2.getText().equals("0") == true)
			{
				length2.setText("");
			}
			length2.setText(length2.getText() + num);
		}
	}
	
	public void numberClick(MouseEvent click)
	{
		if (length1B == true || length2B == true)
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
		String lengthS1 = length1Box.getValue();
		String lengthS2 = length2Box.getValue();
		if (lengthS1.equals("km") == true && lengthS2.equals("meter") == true)
		{
			if (length1B == true)
			{
				Double km = Double.valueOf(length1.getText());
				Double met = km * 1000;
				String accS = Double.toString(met);
				length2.setText(accS);
			}
			else if (length2B == true)
			{
				Double m1 = Double.valueOf(length2.getText());
				Double km = m1 / 1000;
				String accS = Double.toString(km);
				length1.setText(accS);
			}
		}
		else if (lengthS1.equals("meter") == true && lengthS2.equals("km") == true)
		{
			if (length1B == true)
			{
				Double m1 = Double.valueOf(length1.getText());
				Double km = m1 / 1000;
				String accS = Double.toString(km);
				length2.setText(accS);
			}
			else if (length2B == true)
			{
				Double km = Double.valueOf(length2.getText());
				Double m1 = km * 1000;
				String accS = Double.toString(m1);
				length1.setText(accS);
			}
		}
		else if (lengthS1.equals("meter") == true && lengthS2.equals("meter") == true)
		{
			if (length1B == true)
			{
				length2.setText(length1.getText());
			}
			else if (length2B == true)
			{
				length1.setText(length2.getText());
			}
		}
		else if (lengthS1.equals("km") == true && lengthS2.equals("km") == true)
		{
			if (length1B == true)
			{
				length2.setText(length1.getText());
			}
			else if (length2B == true)
			{
				length1.setText(length2.getText());
			}
		}
	}
	
	
	public void clearCharacter(MouseEvent click)
	{
		if (length1B == true)
		{
			String length1_P = length1.getText();
			int len = length1_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					length2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += length1_P.charAt(i);
					}
					length1.setText(update);
				}
			}
		}
		else if (length2B == true)
		{
			String height_P = length2.getText();
			int len = height_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					length2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += height_P.charAt(i);
					}
					length2.setText(update);
				}
			}
		}
	}
	
	public void originalClick(MouseEvent click)
	{
		clearLength1();
		clearLength2();
		length1.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		length2.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		length1B = true;
		length2B = false;
	}
	
	public void discountClick(MouseEvent click)
	{
		clearLength1();
		clearLength2();
		length1.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		length2.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		length1B = false;
		length2B = true;
	}
	
	public void backToCalculator() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("base.fxml"));
		Stage window = (Stage) base.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	
	public void clearScreen(MouseEvent click)
	{
		clearLength1();
		clearLength2();
	}
}
