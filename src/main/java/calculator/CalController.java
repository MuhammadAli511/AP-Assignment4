package calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CalController {

	
	@FXML
	public Label value;
	
	
	public static boolean opClick = false;
	public static boolean numClick = false;
	public static boolean eligible = false;
	
	public void displayNumber(String num)
	{
		value.setText(value.getText() + num);
	}
	
	public void calculate()
	{
		String expression = value.getText();
		int length = expression.length();
		if (expression.charAt(length-1) != '/' && expression.charAt(length-1) != '*' && expression.charAt(length-1) != '+' && expression.charAt(length-1) != '-')
		{
			
		}
	}
	
	public void numberClick(MouseEvent click)
	{
		numClick = true;
		Object node = click.getSource();
		Button button = (Button)node;
		String num = button.getText();
		displayNumber(num);
		if (opClick = true)
		{
			calculate();
		}
	}
	
	public void operationClick(MouseEvent click)
	{
		if (numClick == true)
		{
			opClick = true;
			Object node = click.getSource();
			Button button = (Button)node;
			String operation = button.getText();
			displayNumber(operation);
		}
	}
	
}
