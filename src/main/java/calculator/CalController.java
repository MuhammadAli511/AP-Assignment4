package calculator;

import javafx.fxml.FXML;
import java.util.*;
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
			Stack<Integer> number_Stack = new Stack<Integer>();
			Stack<Character> operator_Stack = new Stack<Character>();
			String temp_NumHolder = "";
			for (int i = 0 ; i < length ; i++)
			{
				if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9')
				{
					temp_NumHolder += expression.charAt(i);
				}
				else if (expression.charAt(i) == '/' || expression.charAt(i) == '*' || expression.charAt(i) == '+' || expression.charAt(i) == '-')
				{
					number_Stack.push(Integer.parseInt(temp_NumHolder));
					temp_NumHolder = "";
					operator_Stack.push(expression.charAt(i));
				}
			}
			if (temp_NumHolder.compareTo("") != 0)
			{
				number_Stack.push(Integer.parseInt(temp_NumHolder));
			}
			System.out.println(number_Stack);
			System.out.println(operator_Stack);
		}
	}
	
	public void numberClick(MouseEvent click)
	{
		numClick = true;
		Object node = click.getSource();
		Button button = (Button)node;
		String num = button.getText();
		displayNumber(num);
		if (opClick == true)
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
