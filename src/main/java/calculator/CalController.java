package calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.*;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class CalController {

	
	@FXML
	public Label value;
	public Label resultLab;
	public Pane discount_Page;
	public Pane bmi_Page;
	public Pane length_Page;
	public Pane temp_Page;
	public Pane mbgb_Page;
	public Pane area_Page;
	public Pane volume_Page;
	public Pane speed_Page;
	public Pane mass_Page;
	public Pane age_Page;
	public Pane currency_Page;
	
	
	public static boolean opClick = false;
	public static boolean numClick = false;
	public static boolean eligible = false;
	public static boolean equalEligibilty = false;
	public int operatorCount = 0;
	public int digitCount = 0;
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
	
	public int checkPrecedence(String val)
	{
		if (val.equals("+") == true || val.equals("-") == true)
		{	
			return 1;
		}
		else if (val.equals("*") == true || val.equals("/") == true)
		{	
			return 2;
		}
        return -1;
    }
	
	public String infinixToPostfix(String ex)
	{
		String result = "";
		int len = ex.length();
		Stack<String> st1 = new Stack<String>();
		int count = 0;
		for (int i = 0; i < len ; i++)
		{
			if (ex.charAt(i) == '+' || ex.charAt(i) == '/' || ex.charAt(i) == '*' || ex.charAt(i) == '-')
			{
				count++;
			}
        }
        count = (count*2)+1;
		Vector<String> vec1 = new Vector<String>(count);
		for (int i = 0; i < count ; i++)
		{
			vec1.add("");
        }
		int count2 = 0;
		for (int i = 0; i < len ; i++)
		{
			if (ex.charAt(i) >= '0' && ex.charAt(i) <= '9')
			{
				String temp = "" + ex.charAt(i);
				String temp2 = vec1.get(count2) + temp;
				vec1.set(count2, temp2);
			}
			else
			{
				count2++;
				String temp = "" + ex.charAt(i);
				vec1.set(count2, temp);
				count2++;
			}
        }
		for (int i = 0; i < vec1.size() ; i++)
		{
			int pre = checkPrecedence(vec1.get(i));
			if(pre > 0)
			{
				// It is operator
                while(st1.isEmpty()==false && checkPrecedence(st1.peek()) >= checkPrecedence(vec1.get(i)) )
                {
                    result += st1.pop();
                }
                st1.push(vec1.get(i));
            }
			else
            {
				// It is number
				result += "(";
                result += vec1.get(i);
                result += ")";
            }
        }
        while (st1.isEmpty() != true)
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
				if (ch == '(')
				{
					String ch1 = "";
					String temp = "";
					while(true)
					{
						i++;
						temp = Character.toString(exp.charAt(i));
						if (temp.equals(")"))
						{
							break;
						}
						else
						{
							ch1 += temp;
						}
					}
					st1.push(ch1);
				}
			}
			else
			{
				String val1 = st1.pop();
				String val2 = st1.pop();
				Double val1i = Double.parseDouble(String.valueOf(val1));
				Double val2i = Double.parseDouble(String.valueOf(val2));
				Double value = 0.0;
				if (ch == '+')
				{
					value = val2i + val1i;
				}
				else if (ch == '-')
				{
					value = val2i - val1i;
				}
				else if (ch == '*')
				{
					value = val2i * val1i;
				}
				else if (ch == '/')
				{
					value = val2i / val1i;
				}
				String st2 = Double.toString(value);
				st1.push(st2);
			}
		}
		result = st1.pop();
		return result;
	}
	
	public void calculate(String exp)
	{
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
		digitCount++;
		numClick = true;
		Object node = click.getSource();
		Button button = (Button)node;
		String num = button.getText();
		displayNumber(num);
		if (opClick == true)
		{
			String exp = value.getText();
			calculate(exp);
		}
	}
	
	public void operationClick(MouseEvent click)
	{
		if (numClick == true)
		{
			operatorCount++;
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
	
	public void clearCharacter(MouseEvent click)
	{
		String labelBox = value.getText();
		if (labelBox.length() != 0)
		{
			String updated = "";
			for (int i = 0 ; i < (labelBox.length() - 1) ; i++)
			{
				updated += labelBox.charAt(i);
			}
			value.setText(updated);
			if ((labelBox.length() - 1) != 0)
			{
				int le = labelBox.length()-1;
				if (labelBox.charAt(le) == '+' || labelBox.charAt(le) == '-' || labelBox.charAt(le) == '*' || labelBox.charAt(le) == '/')
				{
					if (operatorCount == 1)
					{
						opClick = false;
					}
					operatorCount--;
				}
				else
				{
					digitCount--;
					int le1 = labelBox.length()-2;
					if (operatorCount > 1)
					{
						if (labelBox.charAt(le1) == '+' || labelBox.charAt(le1) == '-' || labelBox.charAt(le1) == '*' || labelBox.charAt(le1) == '/')
						{
							String exp = value.getText();
							String updated2 = "";
							for (int i = 0 ; i < (exp.length() - 1) ; i++)
							{
								updated2 += exp.charAt(i);
							}
							calculate(updated2);
						}
					}
				}
			}
		}
	}
	
	public void clearScreen(MouseEvent click)
	{
		opClick = false;
		numClick = false;
		eligible = false;
		equalEligibilty = false;
		clearRes();
		clearNumber();
	}
	
	public void loadDiscountPage() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("discount.fxml"));
		Stage window = (Stage) discount_Page.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	public void loadBmiPage() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("bmiData.fxml"));
		Stage window = (Stage) bmi_Page.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	public void loadLengthPage() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("length.fxml"));
		Stage window = (Stage) length_Page.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	public void loadTempPage() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("temper.fxml"));
		Stage window = (Stage) temp_Page.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	public void loadmbgbPage() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("data.fxml"));
		Stage window = (Stage) mbgb_Page.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	public void loadAreaPage() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("area.fxml"));
		Stage window = (Stage) area_Page.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	public void loadVolumePage() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("volume.fxml"));
		Stage window = (Stage) volume_Page.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	public void loadSpeedPage() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("speed.fxml"));
		Stage window = (Stage) speed_Page.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	public void loadMassPage() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("mass.fxml"));
		Stage window = (Stage) mass_Page.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	public void loadAgePage() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("age.fxml"));
		Stage window = (Stage) age_Page.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	public void loadCurrencyPage() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("currency.fxml"));
		Stage window = (Stage) currency_Page.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
}
