package calculator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

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

public class AgeController {
	ObservableList<String> birthdayDateList = FXCollections.observableArrayList();
	public ChoiceBox<String> birthdayDateBox;
	ObservableList<String> birthdayMonthList = FXCollections.observableArrayList();
	public ChoiceBox<String> birthdayMonthBox;
	ObservableList<String> birthdayYearList = FXCollections.observableArrayList();
	public ChoiceBox<String> birthdayYearBox;
	
	public Label base;
	
	public Label basicYear;
	public Label basicMonth;
	public Label basicDay;
	
	public Label ye;
	public Label mo;
	public Label we;
	public Label da;
	public Label ho;
	public Label mi;
	
	@FXML
	public void initialize()
	{
		for (int i = 1900 ; i <= 2021 ; i++)
		{
			String year = Integer.toString(i);
			birthdayYearList.add(year);
		}
		for (int i = 1 ; i <= 12 ; i++)
		{
			String month = Integer.toString(i);
			birthdayMonthList.add(month);
		}
		for (int i = 1 ; i <= 31 ; i++)
		{
			String day = Integer.toString(i);
			birthdayDateList.add(day);
		}
		birthdayMonthBox.setItems(birthdayMonthList);
		birthdayYearBox.setItems(birthdayYearList);
		birthdayDateBox.setItems(birthdayDateList);
	}
	
	public void calculate(MouseEvent click)
	{
		String yearS = birthdayYearBox.getValue();
		String monthS = birthdayMonthBox.getValue();
		String dateS = birthdayDateBox.getValue();
		int yearI = Integer.valueOf(yearS);
		if (monthS.equals("4") == true || monthS.equals("6") == true  || monthS.equals("9") == true || monthS.equals("11") == true)
		{
			if (dateS.equals("31") == true)
			{
				birthdayDateBox.setValue("30");
			}
		}
		else if (monthS.equals("2") == true)
		{
			if (((yearI % 4 == 0) && (yearI % 100!= 0)) || (yearI % 400 == 0))
			{
				if (dateS.equals("31") == true)
				{
					birthdayDateBox.setValue("30");
				}
			}
			else
			{
				if (dateS.equals("31") == true || dateS.equals("30") == true)
				{
					birthdayDateBox.setValue("29");
				}
			}
		}
		dateS = birthdayDateBox.getValue();
		int monthI = Integer.valueOf(monthS);
		int dateI = Integer.valueOf(dateS);
		
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(yearI, monthI, dateI);
		Period period = Period.between(birthday, today);
		
		String d1 = Integer.toString(period.getDays());
		String y1 = Integer.toString(period.getYears());
		String m1 = Integer.toString(period.getMonths());
		
		int year = period.getYears();
		int month = period.getMonths();
		int days = period.getDays();
		int temp = 0;
		String tempS;
		ye.setText(y1);
		
		temp = (year*12)+month;
		tempS = Integer.toString(temp);
		mo.setText(tempS);
		
		temp = (month*30)+days;
		tempS = Integer.toString(temp);
		da.setText(tempS);
		
		int temp2  = temp / 7;
		tempS = Integer.toString(temp2);
		we.setText(tempS);
		
		temp = temp * 24;
		tempS = Integer.toString(temp);
		ho.setText(tempS);
		
		temp = temp * 60;
		tempS = Integer.toString(temp);
		mi.setText(tempS);
		
		basicYear.setText(y1);
		basicMonth.setText(m1);
		basicDay.setText(d1);

	}
	public void backToCalculator() throws IOException
	{
		Parent discountPage = FXMLLoader.load(getClass().getResource("base.fxml"));
		Stage window = (Stage) base.getScene().getWindow();
		window.setScene(new Scene(discountPage,335,600));
	}
	
	
}
