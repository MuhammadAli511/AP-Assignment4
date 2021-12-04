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
	
	public int precedence(char val)
	{
		if (val == '+' || val == '-')
		{	
			return 1;
		}
		else if (val == '*' || val == '/')
		{	
			return 2;
		}
        return -1;
    }
	
	public void infinixToPostfix(String ex)
	{
		String result = "";
		int len = ex.length();
		Stack<Character> st1 = new Stack<Character>();
		for (int i = 0; i < len ; i++)
		{
			if(precedence(ex.charAt(i)) > 0)
			{
                while(st1.isEmpty()==false && precedence(st1.peek())>=precedence(ex.charAt(i)))
                {
                    result += st1.pop();
                }
                st1.push(ex.charAt(i));
            }
			else
            {
                result += ex.charAt(i);
            }
        }
        for (int i = 0; i <= st1.size() ; i++)
        {
            result += st1.pop();
        }
        System.out.println(result);
	}
	
	public void calculate()
	{
		String exp = value.getText();
		int length = exp.length();
		if (exp.charAt(length-1) != '/' && exp.charAt(length-1) != '*' && exp.charAt(length-1) != '+' && exp.charAt(length-1) != '-')
		{
			infinixToPostfix(exp);
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
