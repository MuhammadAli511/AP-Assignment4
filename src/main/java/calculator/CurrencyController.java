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

public class CurrencyController {
	public static boolean currency1B = false;
	public static boolean currency2B = false;
	
	ObservableList<String> currency1List = FXCollections.observableArrayList("pkr","dollar");
	ObservableList<String> currency2List = FXCollections.observableArrayList("pkr","dollar");
	public ChoiceBox<String> currency1Box;
	public ChoiceBox<String> currency2Box;
	public Label currency1;
	public Label currency2;
	public Label base;
	
	@FXML
	public void initialize()
	{
		currency1Box.setValue("pkr");
		currency1Box.setItems(currency1List);
		currency2Box.setValue("dollar");
		currency2Box.setItems(currency2List);
	}
	
	public void clearcurrency1()
	{
		String empty = "0";
		currency1.setText(empty);
	}
	
	public void clearcurrency2()
	{
		String empty = "0";
		currency2.setText(empty);
	}
	
	public void displayNumber(String num)
	{
		if (currency1B == true)
		{
			if (currency1.getText().equals("0") == true)
			{
				currency1.setText("");
			}
			currency1.setText(currency1.getText() + num);
		}
		else if (currency2B == true)
		{
			if (currency2.getText().equals("0") == true)
			{
				currency2.setText("");
			}
			currency2.setText(currency2.getText() + num);
		}
	}
	
	public void numberClick(MouseEvent click)
	{
		if (currency1B == true || currency2B == true)
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
		String currencyS1 = currency1Box.getValue();
		String currencyS2 = currency2Box.getValue();
		if (currencyS1.equals("pkr") == true && currencyS2.equals("dollar") == true)
		{
			if (currency1B == true)
			{
				Double km = Double.valueOf(currency1.getText());
				Double met = km / 176.20;
				String accS = Double.toString(met);
				currency2.setText(accS);
			}
			else if (currency2B == true)
			{
				Double m1 = Double.valueOf(currency2.getText());
				Double km = m1 * 176.20;
				String accS = Double.toString(km);
				currency1.setText(accS);
			}
		}
		else if (currencyS1.equals("dollar") == true && currencyS2.equals("pkr") == true)
		{
			if (currency1B == true)
			{
				Double m1 = Double.valueOf(currency1.getText());
				Double km = m1 * 176.20;
				String accS = Double.toString(km);
				currency2.setText(accS);
			}
			else if (currency2B == true)
			{
				Double km = Double.valueOf(currency2.getText());
				Double m1 = km / 176.20;
				String accS = Double.toString(m1);
				currency1.setText(accS);
			}
		}
		else if (currencyS1.equals("dollar") == true && currencyS2.equals("dollar") == true)
		{
			if (currency1B == true)
			{
				currency2.setText(currency1.getText());
			}
			else if (currency2B == true)
			{
				currency1.setText(currency2.getText());
			}
		}
		else if (currencyS1.equals("pkr") == true && currencyS2.equals("pkr") == true)
		{
			if (currency1B == true)
			{
				currency2.setText(currency1.getText());
			}
			else if (currency2B == true)
			{
				currency1.setText(currency2.getText());
			}
		}
	}
	
	
	public void clearCharacter(MouseEvent click)
	{
		if (currency1B == true)
		{
			String currency1_P = currency1.getText();
			int len = currency1_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					currency2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += currency1_P.charAt(i);
					}
					currency1.setText(update);
				}
			}
		}
		else if (currency2B == true)
		{
			String height_P = currency2.getText();
			int len = height_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					currency2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += height_P.charAt(i);
					}
					currency2.setText(update);
				}
			}
		}
	}
	
	public void originalClick(MouseEvent click)
	{
		clearcurrency1();
		clearcurrency2();
		currency1.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		currency2.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		currency1B = true;
		currency2B = false;
	}
	
	public void discountClick(MouseEvent click)
	{
		clearcurrency1();
		clearcurrency2();
		currency1.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		currency2.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		currency1B = false;
		currency2B = true;
	}
	
	public void backToCalculator() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("base.fxml"));
		Stage window = (Stage) base.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	
	public void clearScreen(MouseEvent click)
	{
		clearcurrency1();
		clearcurrency2();
	}
}
