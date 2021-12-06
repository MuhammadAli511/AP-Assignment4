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

public class VolumeController {
	public static boolean volume1B = false;
	public static boolean volume2B = false;
	
	ObservableList<String> volume1List = FXCollections.observableArrayList("gallon","liter");
	ObservableList<String> volume2List = FXCollections.observableArrayList("gallon","liter");
	public ChoiceBox<String> volume1Box;
	public ChoiceBox<String> volume2Box;
	public Label volume1;
	public Label volume2;
	public Label base;
	
	@FXML
	public void initialize()
	{
		volume1Box.setValue("gallon");
		volume1Box.setItems(volume1List);
		volume2Box.setValue("liter");
		volume2Box.setItems(volume2List);
	}
	
	public void clearvolume1()
	{
		String empty = "0";
		volume1.setText(empty);
	}
	
	public void clearvolume2()
	{
		String empty = "0";
		volume2.setText(empty);
	}
	
	public void displayNumber(String num)
	{
		if (volume1B == true)
		{
			if (volume1.getText().equals("0") == true)
			{
				volume1.setText("");
			}
			volume1.setText(volume1.getText() + num);
		}
		else if (volume2B == true)
		{
			if (volume2.getText().equals("0") == true)
			{
				volume2.setText("");
			}
			volume2.setText(volume2.getText() + num);
		}
	}
	
	public void numberClick(MouseEvent click)
	{
		if (volume1B == true || volume2B == true)
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
		String volumeS1 = volume1Box.getValue();
		String volumeS2 = volume2Box.getValue();
		if (volumeS1.equals("gallon") == true && volumeS2.equals("liter") == true)
		{
			if (volume1B == true)
			{
				Double km = Double.valueOf(volume1.getText());
				Double met = km * 3.785;
				String accS = Double.toString(met);
				volume2.setText(accS);
			}
			else if (volume2B == true)
			{
				Double m1 = Double.valueOf(volume2.getText());
				Double km = m1 / 3.785;
				String accS = Double.toString(km);
				volume1.setText(accS);
			}
		}
		else if (volumeS1.equals("liter") == true && volumeS2.equals("gallon") == true)
		{
			if (volume1B == true)
			{
				Double m1 = Double.valueOf(volume1.getText());
				Double km = m1 / 3.785;
				String accS = Double.toString(km);
				volume2.setText(accS);
			}
			else if (volume2B == true)
			{
				Double km = Double.valueOf(volume2.getText());
				Double m1 = km * 3.785;
				String accS = Double.toString(m1);
				volume1.setText(accS);
			}
		}
		else if (volumeS1.equals("liter") == true && volumeS2.equals("liter") == true)
		{
			if (volume1B == true)
			{
				volume2.setText(volume1.getText());
			}
			else if (volume2B == true)
			{
				volume1.setText(volume2.getText());
			}
		}
		else if (volumeS1.equals("gallon") == true && volumeS2.equals("gallon") == true)
		{
			if (volume1B == true)
			{
				volume2.setText(volume1.getText());
			}
			else if (volume2B == true)
			{
				volume1.setText(volume2.getText());
			}
		}
	}
	
	
	public void clearCharacter(MouseEvent click)
	{
		if (volume1B == true)
		{
			String volume1_P = volume1.getText();
			int len = volume1_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					volume2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += volume1_P.charAt(i);
					}
					volume1.setText(update);
				}
			}
		}
		else if (volume2B == true)
		{
			String height_P = volume2.getText();
			int len = height_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					volume2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += height_P.charAt(i);
					}
					volume2.setText(update);
				}
			}
		}
	}
	
	public void originalClick(MouseEvent click)
	{
		clearvolume1();
		clearvolume2();
		volume1.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		volume2.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		volume1B = true;
		volume2B = false;
	}
	
	public void discountClick(MouseEvent click)
	{
		clearvolume1();
		clearvolume2();
		volume1.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		volume2.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		volume1B = false;
		volume2B = true;
	}
	
	public void backToCalculator() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("base.fxml"));
		Stage window = (Stage) base.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	
	public void clearScreen(MouseEvent click)
	{
		clearvolume1();
		clearvolume2();
	}
}
