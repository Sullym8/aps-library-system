package com.aps.LibrarySystem;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.Font;
import java.awt.Window.Type;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Color;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class LoginPage {

	private JFrame frmLoginPage;
	private JTextField txtFldUserName;
	private JPasswordField pwdFldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String HomeDir = new String(System.getProperty("user.home")+"\\Documents\\LMS");
		File dir = new File(HomeDir);
		dir.mkdir();
		HomeDir = new String(System.getProperty("user.home")+"\\Documents\\LMS\\BookImg");
		dir = new File(HomeDir);
		dir.mkdir();
		HomeDir = new String(System.getProperty("user.home")+"\\Documents\\LMS\\ClientImg");
		dir = new File(HomeDir);
		dir.mkdir(); 
		
		File dbFile = new File(System.getProperty("user.home")+"\\Documents\\LMS\\LibraryDB.db");
		if (!dbFile.exists()) {
			JOptionPane.showMessageDialog(null, "Please go to \\Documents\\LMS and paste(or replace) LibraryDB.db in the root folder");
		}
		
		try {
			com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("");
//			select the Look and Feel
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frmLoginPage.setUndecorated(true);
					window.frmLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	
	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
		connection = SqliteConnector.dbConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginPage = new JFrame();
		frmLoginPage.setUndecorated(true);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.setResizable(false);
		frmLoginPage.setTitle("Login");
		frmLoginPage.setBounds(0, 0, 422, 568);
		frmLoginPage.setLocationRelativeTo(null);
//		frmLoginPage.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmLoginPage.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		ImageIcon icon = new ImageIcon(getClass().getResource("/APS.png"));
		ImageIcon closeButton = new ImageIcon(getClass().getResource("/baseline_close_white_18dp.png"));
		
		JLabel lblBtnForgotPwd = new JLabel("Forgot Password?");
		lblBtnForgotPwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ForgotPassword forgotPassword = new ForgotPassword();
				frmLoginPage.setVisible(false);
				forgotPassword.main(null);
				frmLoginPage.dispose();
			}
		});
		
		lblBtnForgotPwd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnForgotPwd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBtnForgotPwd.setBounds(238, 509, 125, 14);
		panel.add(lblBtnForgotPwd);
		
		JLabel lblBtnCreateAcc = new JLabel("Create Account");
		lblBtnCreateAcc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CreateUserAdmin createUser = new CreateUserAdmin();
				frmLoginPage.setVisible(false);;
				createUser.main(null);
				frmLoginPage.dispose();
			}
		});
		lblBtnCreateAcc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnCreateAcc.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblBtnCreateAcc.setHorizontalAlignment(SwingConstants.LEFT);
		lblBtnCreateAcc.setBounds(59, 509, 125, 14);
		panel.add(lblBtnCreateAcc);
		
		pwdFldPassword = new JPasswordField();
		pwdFldPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String pwdString = new String(pwdFldPassword.getPassword());
				if (pwdString.equals("")) {
					System.out.println("empty");
					pwdFldPassword.setEchoChar((char) 0);
					pwdFldPassword.setText("Enter your Password here!");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				String pwdString = new String(pwdFldPassword.getPassword());
				if (pwdString.equals("Enter your Password here!")) {
					System.out.println("present");
					pwdFldPassword.setEchoChar('*');
					pwdFldPassword.setText("");
				}
			}
		});
		pwdFldPassword.setBounds(59, 421, 218, 27);
		panel.add(pwdFldPassword);
		
		txtFldUserName = new JTextField();
		txtFldUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String txtString = txtFldUserName.getText();
				if (txtString.equals("")) {
					txtFldUserName.setText("Enter your username here!");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				String txtFieldString = txtFldUserName.getText();
				if (txtFieldString.equals("Enter your username here!")) {
					txtFldUserName.setText("");
				}
			}
		});
		txtFldUserName.setBounds(59, 372, 304, 27);
		panel.add(txtFldUserName);
		txtFldUserName.setColumns(10);
		
		JLabel lblImage = new JLabel("");
		lblImage.setBackground(new Color(51, 51, 102));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblImage.setOpaque(true);
		lblImage.setBounds(0, 0, 422, 327);
		lblImage.setIcon(icon);
		panel.add(lblImage);
		
		JCheckBox chckbxShowPass = new JCheckBox("Show");
		chckbxShowPass.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxShowPass.setBounds(283, 424, 80, 23);
		panel.add(chckbxShowPass);	
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String pwdString = new String(pwdFldPassword.getPassword());
					String txtString = txtFldUserName.getText();
					if (txtString.equals("")|| pwdString.equals("") ) {
						throw new Exception("Missing Fields");
					}
					String queryString = "select * from adminlogininfo where username=? and password=? ";
					PreparedStatement statement = connection.prepareStatement(queryString);
					/*selects all data points from adminlogin info where username and password are those
					 * determined by userinputs in the login page*/
					
					
					statement.setString(1, txtString);
					statement.setString(2, pwdString);
					ResultSet resultSet = statement.executeQuery();
					int count = 0;
						
					while (resultSet.next()) {
						count++;
					}
					if (count ==1 ) {
						JOptionPane.showMessageDialog(null, "Successfully logged in!");
						UserDashboard uDB = new UserDashboard();
						uDB.userName=txtFldUserName.getText();
						frmLoginPage.setVisible(false);
						System.out.println(txtFldUserName.getText());
						uDB.main(null);
						frmLoginPage.dispose();
					} else if (count>1) {
						throw new Exception("Database Error");
					} else {
						throw new Exception("Username and password combination does not exist");
					}
					resultSet.close();
					statement.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} 
				
			}
		});
		
		btnLogin.setBounds(59, 463, 304, 35);
		panel.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("User ID:");
		lblNewLabel.setBounds(59, 353, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(59, 402, 304, 14);
		panel.add(lblNewLabel_1);
		
		chckbxShowPass.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxShowPass.isSelected()) {
					String passString = new String(pwdFldPassword.getPassword());
					pwdFldPassword.setEchoChar((char) 0);
					pwdFldPassword.setText(passString);
				} else {
					pwdFldPassword.setEchoChar('*');
				}
			}
		});
		
		
		
	}
}
