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

public class DataController {

	public static boolean data1B = false;
	public static boolean data2B = false;
	
	ObservableList<String> data1List = FXCollections.observableArrayList("gb","mb");
	ObservableList<String> data2List = FXCollections.observableArrayList("gb","mb");
	public ChoiceBox<String> data1Box;
	public ChoiceBox<String> data2Box;
	public Label data1;
	public Label data2;
	public Label base;
	
	@FXML
	public void initialize()
	{
		data1Box.setValue("gb");
		data1Box.setItems(data1List);
		data2Box.setValue("mb");
		data2Box.setItems(data2List);
	}
	
	public void cleardata1()
	{
		String empty = "0";
		data1.setText(empty);
	}
	
	public void cleardata2()
	{
		String empty = "0";
		data2.setText(empty);
	}
	
	public void displayNumber(String num)
	{
		if (data1B == true)
		{
			if (data1.getText().equals("0") == true)
			{
				data1.setText("");
			}
			data1.setText(data1.getText() + num);
		}
		else if (data2B == true)
		{
			if (data2.getText().equals("0") == true)
			{
				data2.setText("");
			}
			data2.setText(data2.getText() + num);
		}
	}
	
	public void numberClick(MouseEvent click)
	{
		if (data1B == true || data2B == true)
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
		String dataS1 = data1Box.getValue();
		String dataS2 = data2Box.getValue();
		if (dataS1.equals("gb") == true && dataS2.equals("mb") == true)
		{
			if (data1B == true)
			{
				Double km = Double.valueOf(data1.getText());
				Double met = km * 1024;
				String accS = Double.toString(met);
				data2.setText(accS);
			}
			else if (data2B == true)
			{
				Double m1 = Double.valueOf(data2.getText());
				Double km = m1 / 1024;
				String accS = Double.toString(km);
				data1.setText(accS);
			}
		}
		else if (dataS1.equals("mb") == true && dataS2.equals("gb") == true)
		{
			if (data1B == true)
			{
				Double m1 = Double.valueOf(data1.getText());
				Double km = m1 / 1024;
				String accS = Double.toString(km);
				data2.setText(accS);
			}
			else if (data2B == true)
			{
				Double km = Double.valueOf(data2.getText());
				Double m1 = km * 1024;
				String accS = Double.toString(m1);
				data1.setText(accS);
			}
		}
		else if (dataS1.equals("mb") == true && dataS2.equals("mb") == true)
		{
			if (data1B == true)
			{
				data2.setText(data1.getText());
			}
			else if (data2B == true)
			{
				data1.setText(data2.getText());
			}
		}
		else if (dataS1.equals("gb") == true && dataS2.equals("gb") == true)
		{
			if (data1B == true)
			{
				data2.setText(data1.getText());
			}
			else if (data2B == true)
			{
				data1.setText(data2.getText());
			}
		}
	}
	
	
	public void clearCharacter(MouseEvent click)
	{
		if (data1B == true)
		{
			String data1_P = data1.getText();
			int len = data1_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					data2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += data1_P.charAt(i);
					}
					data1.setText(update);
				}
			}
		}
		else if (data2B == true)
		{
			String height_P = data2.getText();
			int len = height_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					data2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += height_P.charAt(i);
					}
					data2.setText(update);
				}
			}
		}
	}
	
	public void originalClick(MouseEvent click)
	{
		cleardata1();
		cleardata2();
		data1.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		data2.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		data1B = true;
		data2B = false;
	}
	
	public void discountClick(MouseEvent click)
	{
		cleardata1();
		cleardata2();
		data1.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		data2.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		data1B = false;
		data2B = true;
	}
	
	public void backToCalculator() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("base.fxml"));
		Stage window = (Stage) base.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	
	public void clearScreen(MouseEvent click)
	{
		cleardata1();
		cleardata2();
	}
}
