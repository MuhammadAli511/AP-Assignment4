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

public class MassController {
	public static boolean mass1B = false;
	public static boolean mass2B = false;
	
	ObservableList<String> mass1List = FXCollections.observableArrayList("kg","gram");
	ObservableList<String> mass2List = FXCollections.observableArrayList("kg","gram");
	public ChoiceBox<String> mass1Box;
	public ChoiceBox<String> mass2Box;
	public Label mass1;
	public Label mass2;
	public Label base;
	
	@FXML
	public void initialize()
	{
		mass1Box.setValue("kg");
		mass1Box.setItems(mass1List);
		mass2Box.setValue("gram");
		mass2Box.setItems(mass2List);
	}
	
	public void clearmass1()
	{
		String empty = "0";
		mass1.setText(empty);
	}
	
	public void clearmass2()
	{
		String empty = "0";
		mass2.setText(empty);
	}
	
	public void displayNumber(String num)
	{
		if (mass1B == true)
		{
			if (mass1.getText().equals("0") == true)
			{
				mass1.setText("");
			}
			mass1.setText(mass1.getText() + num);
		}
		else if (mass2B == true)
		{
			if (mass2.getText().equals("0") == true)
			{
				mass2.setText("");
			}
			mass2.setText(mass2.getText() + num);
		}
	}
	
	public void numberClick(MouseEvent click)
	{
		if (mass1B == true || mass2B == true)
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
		String massS1 = mass1Box.getValue();
		String massS2 = mass2Box.getValue();
		if (massS1.equals("kg") == true && massS2.equals("gram") == true)
		{
			if (mass1B == true)
			{
				Double km = Double.valueOf(mass1.getText());
				Double met = km * 1000;
				String accS = Double.toString(met);
				mass2.setText(accS);
			}
			else if (mass2B == true)
			{
				Double m1 = Double.valueOf(mass2.getText());
				Double km = m1 / 1000;
				String accS = Double.toString(km);
				mass1.setText(accS);
			}
		}
		else if (massS1.equals("gram") == true && massS2.equals("kg") == true)
		{
			if (mass1B == true)
			{
				Double m1 = Double.valueOf(mass1.getText());
				Double km = m1 / 1000;
				String accS = Double.toString(km);
				mass2.setText(accS);
			}
			else if (mass2B == true)
			{
				Double km = Double.valueOf(mass2.getText());
				Double m1 = km * 1000;
				String accS = Double.toString(m1);
				mass1.setText(accS);
			}
		}
		else if (massS1.equals("gram") == true && massS2.equals("gram") == true)
		{
			if (mass1B == true)
			{
				mass2.setText(mass1.getText());
			}
			else if (mass2B == true)
			{
				mass1.setText(mass2.getText());
			}
		}
		else if (massS1.equals("kg") == true && massS2.equals("kg") == true)
		{
			if (mass1B == true)
			{
				mass2.setText(mass1.getText());
			}
			else if (mass2B == true)
			{
				mass1.setText(mass2.getText());
			}
		}
	}
	
	
	public void clearCharacter(MouseEvent click)
	{
		if (mass1B == true)
		{
			String mass1_P = mass1.getText();
			int len = mass1_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					mass2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += mass1_P.charAt(i);
					}
					mass1.setText(update);
				}
			}
		}
		else if (mass2B == true)
		{
			String height_P = mass2.getText();
			int len = height_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					mass2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += height_P.charAt(i);
					}
					mass2.setText(update);
				}
			}
		}
	}
	
	public void originalClick(MouseEvent click)
	{
		clearmass1();
		clearmass2();
		mass1.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		mass2.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		mass1B = true;
		mass2B = false;
	}
	
	public void discountClick(MouseEvent click)
	{
		clearmass1();
		clearmass2();
		mass1.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		mass2.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		mass1B = false;
		mass2B = true;
	}
	
	public void backToCalculator() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("base.fxml"));
		Stage window = (Stage) base.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	
	public void clearScreen(MouseEvent click)
	{
		clearmass1();
		clearmass2();
	}
}
