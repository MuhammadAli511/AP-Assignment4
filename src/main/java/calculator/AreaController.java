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


public class AreaController {
	public static boolean area1B = false;
	public static boolean area2B = false;
	
	ObservableList<String> area1List = FXCollections.observableArrayList("(m)^2","(feet)^2");
	ObservableList<String> area2List = FXCollections.observableArrayList("(m)^2","(feet)^2");
	public ChoiceBox<String> area1Box;
	public ChoiceBox<String> area2Box;
	public Label area1;
	public Label area2;
	public Label base;
	
	@FXML
	public void initialize()
	{
		area1Box.setValue("(m)^2");
		area1Box.setItems(area1List);
		area2Box.setValue("(feet)^2");
		area2Box.setItems(area2List);
	}
	
	public void cleararea1()
	{
		String empty = "0";
		area1.setText(empty);
	}
	
	public void cleararea2()
	{
		String empty = "0";
		area2.setText(empty);
	}
	
	public void displayNumber(String num)
	{
		if (area1B == true)
		{
			if (area1.getText().equals("0") == true)
			{
				area1.setText("");
			}
			area1.setText(area1.getText() + num);
		}
		else if (area2B == true)
		{
			if (area2.getText().equals("0") == true)
			{
				area2.setText("");
			}
			area2.setText(area2.getText() + num);
		}
	}
	
	public void numberClick(MouseEvent click)
	{
		if (area1B == true || area2B == true)
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
		String areaS1 = area1Box.getValue();
		String areaS2 = area2Box.getValue();
		if (areaS1.equals("(m)^2") == true && areaS2.equals("(feet)^2") == true)
		{
			if (area1B == true)
			{
				Double km = Double.valueOf(area1.getText());
				Double met = km * 10.764;
				String accS = Double.toString(met);
				area2.setText(accS);
			}
			else if (area2B == true)
			{
				Double m1 = Double.valueOf(area2.getText());
				Double km = m1 / 10.764;
				String accS = Double.toString(km);
				area1.setText(accS);
			}
		}
		else if (areaS1.equals("(feet)^2") == true && areaS2.equals("(m)^2") == true)
		{
			if (area1B == true)
			{
				Double m1 = Double.valueOf(area1.getText());
				Double km = m1 / 10.764;
				String accS = Double.toString(km);
				area2.setText(accS);
			}
			else if (area2B == true)
			{
				Double km = Double.valueOf(area2.getText());
				Double m1 = km * 10.764;
				String accS = Double.toString(m1);
				area1.setText(accS);
			}
		}
		else if (areaS1.equals("(feet)^2") == true && areaS2.equals("(feet)^2") == true)
		{
			if (area1B == true)
			{
				area2.setText(area1.getText());
			}
			else if (area2B == true)
			{
				area1.setText(area2.getText());
			}
		}
		else if (areaS1.equals("(m)^2") == true && areaS2.equals("(m)^2") == true)
		{
			if (area1B == true)
			{
				area2.setText(area1.getText());
			}
			else if (area2B == true)
			{
				area1.setText(area2.getText());
			}
		}
	}
	
	
	public void clearCharacter(MouseEvent click)
	{
		if (area1B == true)
		{
			String area1_P = area1.getText();
			int len = area1_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					area2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += area1_P.charAt(i);
					}
					area1.setText(update);
				}
			}
		}
		else if (area2B == true)
		{
			String height_P = area2.getText();
			int len = height_P.length();
			if (len != 0)
			{
				String update = "";
				if (len == 1)
				{
					area2.setText(update);
				}
				else
				{
					for (int i = 0 ; i < (len-1) ; i++)
					{
						update += height_P.charAt(i);
					}
					area2.setText(update);
				}
			}
		}
	}
	
	public void originalClick(MouseEvent click)
	{
		cleararea1();
		cleararea2();
		area1.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		area2.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		area1B = true;
		area2B = false;
	}
	
	public void discountClick(MouseEvent click)
	{
		cleararea1();
		cleararea2();
		area1.setStyle("-fx-font-size: 18;-fx-text-fill: #000000;-fx-border-style: dotted");
		area2.setStyle("-fx-font-size: 18;-fx-text-fill: #FF6600;-fx-border-style: dotted");
		area1B = false;
		area2B = true;
	}
	
	public void backToCalculator() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("base.fxml"));
		Stage window = (Stage) base.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	
	public void clearScreen(MouseEvent click)
	{
		cleararea1();
		cleararea2();
	}
}
