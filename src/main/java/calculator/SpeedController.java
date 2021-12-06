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

public class SpeedController {
	public static boolean speed1B = false;
	public static boolean speed2B = false;
	
	ObservableList<String> speed1List = FXCollections.observableArrayList("m/s","k/h");
	ObservableList<String> speed2List = FXCollections.observableArrayList("m/s","k/h");
	public ChoiceBox<String> speed1Box;
	public ChoiceBox<String> speed2Box;
	public Label speed1;
	public Label speed2;
	public Label base;
	
	@FXML
	public void initialize()
	{
		speed1Box.setValue("m/s");
		speed1Box.setItems(speed1List);
		speed2Box.setValue("k/h");
		speed2Box.setItems(speed2List);
	}
	
	public void clearspeed1()
	{
		String empty = "0";
		speed1.setText(empty);
	}
	
	public void clearspeed2()
	{
		String empty = "0";
		speed2.setText(empty);
	}
	
	public void displayNumber(String num)
	{
		if (speed1B == true)
		{
			if (speed1.getText().equals("0") == true)
			{
				speed1.setText("");
			}
			speed1.setText(speed1.getText() + num);
		}
		else if (speed2B == true)
		{
			if (speed2.getText().equals("0") == true)
			{
				speed2.setText("");
			}
			speed2.setText(speed2.getText() + num);
		}
	}
	
	public void numberClick(MouseEvent click)
	{
		if (speed1B == true || speed2B == true)
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
		String speedS1 = speed1Box.getValue();
		String speedS2 = speed2Box.getValue();
		if (speedS1.equals("m/s") == true && speedS2.equals("k/h") == true)
		{
			if (speed1B == true)
			{
				Double km = Double.valueOf(speed1.getText());
				Double met = km * 3.6;
				String accS = Double.toString(met);
				speed2.setText(accS);
			}
			else if (speed2B == true)
			{
				Double m1 = Double.valueOf(speed2.getText());
				Double km = m1 / 3.6;
				String accS = Double.toString(km);
				speed1.setText(accS);
			}
		}
		else if (speedS1.equals("k/h") == true && speedS2.equals("m/s") == true)
		{
			if (speed1B == true)
			{
				Double m1 = Double.valueOf(speed1.getText());
				Double km = m1 / 3.6;
				String accS = Double.toString(km);
				speed2.setText(accS);
			}
			else if (speed2B == true)
			{
				Double km = Double.valueOf(speed2.getText());
				Double m1 = km * 3.6;
				String accS = Double.toString(m1);
				speed1.setText(accS);
			}
		}
		else if (speedS1.equals("k/h") == true && speedS2.equals("k/h") == true)
		{
			if (speed1B == true)
			{
				speed2.setText(speed1.getText());
			}
			else if (speed2B == true)
			{
				speed1.setText(speed2.getText());
			}
		}
		else if (speedS1.equals("m/s") == true && speedS2.equals("m/s") == true)
		{
			if (speed1B == true)
			{
				speed2.setText(speed1.getText());
			}
			else if (speed2B == true)
			{
				speed1.setText(speed2.getText());
			}
		}
	}
	
	
	public void clearCharacter(MouseEvent click)
	{
		if (speed1B == true)
		{
			String speed1_P = speed1.getText();
			int len = speed1_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					speed2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += speed1_P.charAt(i);
					}
					speed1.setText(update);
				}
			}
		}
		else if (speed2B == true)
		{
			String height_P = speed2.getText();
			int len = height_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					speed2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += height_P.charAt(i);
					}
					speed2.setText(update);
				}
			}
		}
	}
	
	public void originalClick(MouseEvent click)
	{
		clearspeed1();
		clearspeed2();
		speed1.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		speed2.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		speed1B = true;
		speed2B = false;
	}
	
	public void discountClick(MouseEvent click)
	{
		clearspeed1();
		clearspeed2();
		speed1.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		speed2.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		speed1B = false;
		speed2B = true;
	}
	
	public void backToCalculator() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("base.fxml"));
		Stage window = (Stage) base.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	
	public void clearScreen(MouseEvent click)
	{
		clearspeed1();
		clearspeed2();
	}
}
