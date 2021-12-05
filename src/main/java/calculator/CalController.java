package calculator;

import javafx.fxml.FXML;
import java.util.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CalController {

	
	@FXML
	public Label value;
	public Label resultLab;
	
	
	public static boolean opClick = false;
	public static boolean numClick = false;
	public static boolean eligible = false;
	public static boolean equalEligibilty = false;
	public String transferVal = "";
	
	public void displayNumber(String num)
	{
		value.setText(value.getText() + num);
	}
	
	public void displayRes(String num)
	{
		resultLab.setText(num);
	}
	
	public void clearRes()
	{
		String empty = "";
		value.setText(empty);
	}
	
	public void clearNumber()
	{
		String empty = "";
		resultLab.setText(empty);
	}
	
	public int checkPrecedence(char val)
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
	
	public String infinixToPostfix(String ex)
	{
		String result = "";
		int len = ex.length();
		Stack<Character> st1 = new Stack<Character>();
		for (int i = 0; i < len ; i++)
		{
			if(checkPrecedence(ex.charAt(i)) > 0)
			{
                while(st1.isEmpty()==false && checkPrecedence(st1.peek()) >= checkPrecedence(ex.charAt(i)) )
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
        return result;
	}
	
	public String calculateExp(String exp)
	{
		String result = "";
		Stack<String> st1 = new Stack<String>();
		for (int i = 0 ; i < exp.length() ; i++)
		{
			char ch = exp.charAt(i);
			if (ch != '+' && ch != '-' && ch != '*' && ch != '/')
			{
				String ch1 = Character.toString(ch);
				st1.push(ch1);
			}
			else
			{
				String val1 = st1.pop();
				String val2 = st1.pop();
				int val1i = Integer.parseInt(String.valueOf(val1));
				int val2i = Integer.parseInt(String.valueOf(val2));
				int value = 0;
				if (ch == '+')
				{
					value = val1i + val2i;
				}
				else if (ch == '-')
				{
					value = val1i - val2i;
				}
				else if (ch == '*')
				{
					value = val1i * val2i;
				}
				else if (ch == '/')
				{
					value = val1i / val2i;
				}
				String st2 = Integer.toString(value);
				st1.push(st2);
			}
		}
		result = st1.pop();
		return result;
	}
	
	public void calculate()
	{
		String exp = value.getText();
		int length = exp.length();
		if (exp.charAt(length-1) != '/' && exp.charAt(length-1) != '*' && exp.charAt(length-1) != '+' && exp.charAt(length-1) != '-')
		{
			equalEligibilty = true;
			String postfixExp  = infinixToPostfix(exp);
			String finalResult = calculateExp(postfixExp);
			transferVal = finalResult;
			displayRes(finalResult);
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
	
	public void equalClick(MouseEvent click)
	{
		if (equalEligibilty == true)
		{
			Object node = click.getSource();
			Button button = (Button)node;
			String operation = button.getText();
			clearRes();
			clearNumber();
			displayNumber(transferVal);
		}
	}
	
}
