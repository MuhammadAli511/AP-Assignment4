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

public class DiscountController {
	
	public static boolean originalB = false;
	public static boolean discountB = false;
	
	
	@FXML
	public Label original_Price;
	public Label discount;
	public Label final_Price;
	public Label saving;
	public Pane base;
	
		
	public void clearOriginal()
	{
		String empty = "0";
		original_Price.setText(empty);
	}
	
	public void clearDiscount()
	{
		String empty = "0";
		discount.setText(empty);
	}
	
	public void clearFinal()
	{
		String empty = "0";
		final_Price.setText(empty);
	}
	
	public void clearSave()
	{
		String empty = "0";
		saving.setText(empty);
	}
	
	public void displayNumber(String num)
	{
		if (originalB == true)
		{
			if (original_Price.getText().equals("0") == true)
			{
				original_Price.setText("");
			}
			original_Price.setText(original_Price.getText() + num);
		}
		else if (discountB == true)
		{
			if (discount.getText().equals("0") == true)
			{
				discount.setText("");
			}
			discount.setText(discount.getText() + num);
		}
	}
	
	public void calculate()
	{
		Double ori = Double.parseDouble(String.valueOf(original_Price.getText()));
		Double dis = Double.parseDouble(String.valueOf(discount.getText()));
		Double fin = (ori*dis)/100;
		String finS = Double.toString(fin);
		saving.setText(finS);
		
		Double sav = ori - fin;
		String savS = Double.toString(sav);
		final_Price.setText(savS);
	}
	
	public void numberClick(MouseEvent click)
	{
		if (originalB == true || discountB == true)
		{
			Object node = click.getSource();
			Button button = (Button)node;
			String num = button.getText();
			displayNumber(num);
			calculate();
		}
	}
	
	public void originalClick(MouseEvent click)
	{
		original_Price.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: groove");
		discount.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: groove");
		originalB = true;
		discountB = false;
	}
	
	public void discountClick(MouseEvent click)
	{
		
		original_Price.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: groove");
		discount.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: groove");
		discountB = true;
		originalB = false;
	}
	
	public void clearCharacter(MouseEvent click)
	{
		if (originalB == true)
		{
			String original_P = original_Price.getText();
			int len = original_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					original_Price.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += original_P.charAt(i);
					}
					original_Price.setText(update);
				}
			}
		}
		else if (discountB == true)
		{
			String discount_P = discount.getText();
			int len = discount_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					discount.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += discount_P.charAt(i);
					}
					discount.setText(update);
				}
			}
		}
	}
	
	public void backToCalculator() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("base.fxml"));
		Stage window = (Stage) base.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	public void clearScreen(MouseEvent click)
	{
		clearOriginal();
		clearDiscount();
		clearFinal();
		clearSave();
	}
}
