package com.aps.LibrarySystem;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CalculateFines {
	
	String queryString = "select clientid,amount from Fines where clientID=?";
	int DaysSince = 0;
	Connection connection = SqliteConnector.dbConnection();
	
	static int Fine(int Days) {
		if (Days==1) {
			return 10;
		} else {
			return(Days*(Fine(1))+Fine(Days-1));
		}
	}
	
	public int FineProcess(String BookID, String ClientID, String issueDate) {
		TimeUnit timeUnit = null;
		int Fine=0;
		String dueDateQuery = "select EXPECTEDDATE from RegisteredBookInfo where BookID=?";
		
		try {
			PreparedStatement pStatement1 = connection.prepareStatement(dueDateQuery);
			pStatement1.setString(1, BookID);
			ResultSet rSet1 = pStatement1.executeQuery();
			
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyy");
			String dueDate = rSet1.getString(1);
			
			java.util.Date due = dt.parse(dueDate);
			java.util.Date currentDate = calendar.getTime();
			
			long DateDiff = due.getTime()-currentDate.getTime();
			int daysTo = (int) timeUnit.DAYS.convert(DateDiff, TimeUnit.MILLISECONDS) + 1;
			rSet1.close();
			if (daysTo < 0) {
				Fine = Fine(Math.abs(daysTo));
				try {			
						String addFines = "update fines set amount=? where clientID=? and bookid=? and issued=?";
						PreparedStatement pstadd = connection.prepareStatement(addFines);
						pstadd.setInt(1, Fine);
						pstadd.setString(2, ClientID);
						pstadd.setString(3, BookID);
						pstadd.setString(4, issueDate);
						pstadd.execute();
						pstadd.close();
						String bookStat = "update registeredbookinfo set status=2 where bookID=?";
						PreparedStatement p = connection.prepareStatement(bookStat);
						p.setString(1, BookID);
						p.execute();
						p.close();
					System.out.println(Fine);
					JOptionPane.showMessageDialog(null, "Not Returned, fine:" + Fine);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				Fine = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Fine;
	}
}
