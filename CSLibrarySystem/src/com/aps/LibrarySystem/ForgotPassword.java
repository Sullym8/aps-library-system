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
import java.awt.Toolkit;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Choice;

@SuppressWarnings("unused")
public class ForgotPassword {

	private JFrame frmForgotPassPage;
	private JTextField txtFldUserName;
	private JTextField txtFldSecQ;
	private JPasswordField pwdNew;
	private JPasswordField pwdConfirm;
	boolean Auth = false;
	
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	int x = (int) (dimension.getWidth()/2);
	int y = (int) (dimension.getHeight()/2);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("");
          // select the Look and Feel
          UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel"); 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword window = new ForgotPassword();
					window.frmForgotPassPage.setUndecorated(true);
					window.frmForgotPassPage.setVisible(true);
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
	public ForgotPassword() {
		initialize();
		connection = SqliteConnector.dbConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmForgotPassPage = new JFrame();
//		frmForgotPassPage.setUndecorated(true);
		frmForgotPassPage.setResizable(false);
		frmForgotPassPage.setTitle("Forgot Password");
		frmForgotPassPage.setBounds(0, 0, 422, 824);
		frmForgotPassPage.setLocationRelativeTo(null);
		frmForgotPassPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmForgotPassPage.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		frmForgotPassPage.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		ImageIcon icon = new ImageIcon(getClass().getResource("/baseline_vpn_key_white_48dp.png"));
		ImageIcon closeButton = new ImageIcon(getClass().getResource("/baseline_close_white_18dp.png"));
		
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
		txtFldUserName.setBounds(59, 395, 304, 27);
		panel.add(txtFldUserName);
		txtFldUserName.setColumns(10);
		
		JLabel lblImage = new JLabel("");
		lblImage.setBackground(new Color(51, 51, 102));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblImage.setOpaque(true);
		lblImage.setBounds(0, 0, 416, 327);
		lblImage.setIcon(icon);
		panel.add(lblImage);
		
		JLabel lblSecQIndicator = new JLabel("Security Question:");
		lblSecQIndicator.setBounds(59, 502, 106, 27);
		panel.add(lblSecQIndicator);
		
		String[] comboBoxQuestionStrings = {
				"What is your nickname?",
				"What was the name of your school?",
				"What is the name of your pet?",
				"What is the name of your favorite movie?"
		};
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(59, 433, 304, 27);
		panel.add(lblName);
		
		JLabel lblNewPassIndicator = new JLabel("New Password");
		lblNewPassIndicator.setBounds(59, 611, 304, 27);
		panel.add(lblNewPassIndicator);
		
		txtFldSecQ = new JTextField();
		txtFldSecQ.setBounds(59, 538, 304, 27);
		panel.add(txtFldSecQ);
		txtFldSecQ.setColumns(10);
		
		JLabel lblConfirmPassIndicator = new JLabel("Confirm Password");
		lblConfirmPassIndicator.setBounds(59, 668, 304, 27);
		panel.add(lblConfirmPassIndicator);
		
		pwdNew = new JPasswordField();
		pwdNew.setBounds(59, 639, 304, 27);
		panel.add(pwdNew);
		
		pwdConfirm = new JPasswordField();
		pwdConfirm.setBounds(59, 694, 304, 27);
		panel.add(pwdConfirm);
		
		JButton btnReset = new JButton("Reset Password");
		btnReset.setEnabled(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String pwdString = new String(pwdNew.getPassword());
					String pwdUpdateQueryString = "update AdminLoginInfo set Password=? where username=?";
					/*Update the password and username entries of an Admin user following validation and
					 * verification that intended user has access to this functionality*/
					
					PreparedStatement pwdUpdateStatement = connection.prepareStatement(pwdUpdateQueryString);
					
					if (pwdNew.getText().equals("")||pwdConfirm.getText().equals("")) {
						throw new Exception("Missing Fields");
					} else if (!pwdNew.getText().equals(pwdConfirm.getText())) {
						throw new Exception("Password Mismatch");
						
					}
					
					pwdUpdateStatement.setString(1, pwdString);
					pwdUpdateStatement.setString(2, txtFldUserName.getText());
					pwdUpdateStatement.execute();
					Auth = false;
					JOptionPane.showMessageDialog(null, "The password has been reset");
					pwdUpdateStatement.close();
					btnReset.setEnabled(false);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnReset.setBounds(59, 738, 155, 35);
		panel.add(btnReset);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				frmForgotPassPage.setVisible(false);
				loginPage.main(null);
			}
		});
		btnBack.setBounds(224, 738, 139, 35);
		panel.add(btnBack);
		
		
		JComboBox cmbxSecQ = new JComboBox(comboBoxQuestionStrings);
		cmbxSecQ.setBounds(175, 503, 188, 24);
		panel.add(cmbxSecQ);
		
		JLabel lblStudentId = new JLabel("ID");
		lblStudentId.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentId.setBounds(59, 464, 304, 27);
		panel.add(lblStudentId);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setBounds(59, 368, 304, 27);
		panel.add(lblUsername);
		
		JButton btnNewButton = new JButton("Lookup Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String nameQueryString = "select LibrarianFullName,UserID from AdminLoginInfo where username=?";
					String secQString = "select SecQAns from AdminLoginInfo where SecQChoice=? and username=?";
					PreparedStatement nameStatement = connection.prepareStatement(nameQueryString);
					PreparedStatement secQStatement = connection.prepareStatement(secQString);
					
					if (txtFldUserName.getText().equals("")||txtFldSecQ.getText().equals("")) {
						throw new Exception("Missing Fields");
					}
					secQStatement.setInt(1, cmbxSecQ.getSelectedIndex());
					secQStatement.setString(2, txtFldUserName.getText());
					ResultSet secQResultSet = secQStatement.executeQuery();
					if (secQResultSet.next()) {
						if (secQResultSet.getString(1).equals(txtFldSecQ.getText()) ) {
							JOptionPane.showMessageDialog(null, "Found User!");
							btnReset.setEnabled(true);
							txtFldUserName.setEnabled(false);
							txtFldSecQ.setEnabled(false);
							Auth = true;

							nameStatement.setString(1,txtFldUserName.getText());
							ResultSet nameResultSet = nameStatement.executeQuery();

							lblName.setText("Name: " + nameResultSet.getString(1));
							lblStudentId.setText("ID: " + nameResultSet.getString(2));

							nameResultSet.close();

						} else {
							JOptionPane.showMessageDialog(null, "Incorrect Security Answer");
						}

					}else {
						throw new Exception("Non-existing User/ Incorrect Security Question");
					}
					secQResultSet.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		try {
			String decodedPath = URLDecoder.decode(path, "UTF-8");
			System.out.println(decodedPath);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNewButton.setBounds(59, 576, 304, 35);
		panel.add(btnNewButton);
	}
}
