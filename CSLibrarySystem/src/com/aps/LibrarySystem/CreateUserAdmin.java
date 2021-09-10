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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Choice;

@SuppressWarnings("unused")
public class CreateUserAdmin {

	private JFrame frmCreateUserPage;
	private JTextField paswordNew;
	private JTextField txtFldSecQAns;
	private JTextField passwordConfirm;
	private JTextField txtFldInputName;
	private JTextField txtFldInputID;
	private JTextField txtFldUserName;

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
					CreateUserAdmin window = new CreateUserAdmin();
					window.frmCreateUserPage.setUndecorated(true);
					window.frmCreateUserPage.setVisible(true);
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
	public CreateUserAdmin() {
		initialize();
		connection = SqliteConnector.dbConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreateUserPage = new JFrame();
		frmCreateUserPage.setUndecorated(true);
		frmCreateUserPage.setResizable(false);
		frmCreateUserPage.setTitle("Create Admin Account");
		frmCreateUserPage.setBounds(0, 0, 422, 793);
		frmCreateUserPage.setLocationRelativeTo(null);
		frmCreateUserPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateUserPage.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmCreateUserPage.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		ImageIcon icon = new ImageIcon(getClass().getResource("/baseline_person_add_white_48dp.png"));
		ImageIcon closeButton = new ImageIcon(getClass().getResource("/baseline_close_white_18dp.png"));
		
		JLabel lblImage = new JLabel("");
		lblImage.setBackground(new Color(51, 51, 102));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblImage.setOpaque(true);
		lblImage.setBounds(0, 0, 422, 327);
		lblImage.setIcon(icon);
		panel.add(lblImage);
		
		JLabel lblNewLabel = new JLabel("Full Name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(59, 394, 79, 27);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Please choose a Security Question");
		lblNewLabel_1.setBounds(59, 470, 304, 27);
		panel.add(lblNewLabel_1);
		
		String[] comboBoxQuestionStrings = {
				"What is your nickname?",
				"What was the name of your school?",
				"What is the name of your pet?",
				"What is the name of your favorite movie?"
		};
		
		JComboBox cmbxSecQ = new JComboBox(comboBoxQuestionStrings);
		cmbxSecQ.setBorder(new LineBorder(new Color(171, 173, 179)));
		cmbxSecQ.setBounds(59, 501, 304, 27);
		panel.add(cmbxSecQ);
		
		paswordNew = new JTextField();
		paswordNew.setBounds(59, 612, 304, 27);
		panel.add(paswordNew);
		paswordNew.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New Password:");
		lblNewLabel_2.setBounds(59, 577, 304, 27);
		panel.add(lblNewLabel_2);
		
		txtFldSecQAns = new JTextField();
		txtFldSecQAns.setBounds(59, 539, 304, 27);
		panel.add(txtFldSecQAns);
		txtFldSecQAns.setColumns(10);
		
		passwordConfirm = new JTextField();
		passwordConfirm.setBounds(59, 676, 304, 27);
		panel.add(passwordConfirm);
		passwordConfirm.setColumns(10);
		
		JLabel lblUserDetail = new JLabel("Admin ID:");
		lblUserDetail.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserDetail.setBounds(59, 435, 79, 27);
		panel.add(lblUserDetail);
		
		JLabel lblNewLabel_2_1 = new JLabel("Confirm Password:");
		lblNewLabel_2_1.setBounds(59, 650, 304, 27);
		panel.add(lblNewLabel_2_1);
		
		txtFldInputName = new JTextField();
		txtFldInputName.setColumns(10);
		txtFldInputName.setBounds(148, 397, 215, 27);
		panel.add(txtFldInputName);
		
		txtFldInputID = new JTextField();
		txtFldInputID.setColumns(10);
		txtFldInputID.setBounds(148, 435, 215, 27);
		panel.add(txtFldInputID);
		
		txtFldUserName = new JTextField();
		txtFldUserName.setColumns(10);
		txtFldUserName.setBounds(148, 353, 215, 27);
		panel.add(txtFldUserName);
		
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userNameString = txtFldUserName.getText();
				String nameString = txtFldInputName.getText();
				String IDString  = txtFldInputID.getText();
				String secQAnsString = txtFldSecQAns.getText(); 
				int SecQSelection = cmbxSecQ.getSelectedIndex();
				String newpassworString = paswordNew.getText();
				String confirmpasswordString = passwordConfirm.getText();
				try {
					String queryString = "insert into AdminLoginInfo (UserID,LibrarianFullName,Username,Password,SecQChoice,SecQAns) values (?,?,?,?,?,?)";
					/*Add new record into Adminlogininfo for a new Admin with credentials
					 * UserID, LibrarianFullName,Username,Password,SecQChoise,SecQAns
					 * following error-free validation and verification checks*/
					PreparedStatement statement = connection.prepareStatement(queryString);
					
					
					
					String queryCheckString = "select * from adminlogininfo where username=?";
					PreparedStatement checkStatement = connection.prepareStatement(queryCheckString);
					
					if (userNameString.equals("")||IDString.equals("") || 
						newpassworString.equals("")||confirmpasswordString.equals("") ||
						secQAnsString.equals("")) {
						throw new Exception("Missing Fields");
					} else if (!newpassworString.equals(confirmpasswordString)) {
						throw new Exception("Password Mismatch");
					} else if (IDString.length()!=5) {
						throw new Exception("Incorrect ID Length (5 char)");
					} else {
						checkStatement.setString(1, userNameString);
						ResultSet resultSet = checkStatement.executeQuery();
						int count = 0;
						while (resultSet.next()) {
							count++;
						}
						if (count >= 1 ) {
							throw new Exception("Existing User");
						} else {
							statement.setString(1, IDString);
							statement.setString(2, nameString);
							statement.setString(3, userNameString);
							statement.setString(4, newpassworString);
							statement.setInt(5, SecQSelection);
							statement.setString(6, secQAnsString);

							statement.execute();
							JOptionPane.showMessageDialog(null, "User Created!");
						}
						resultSet.close();
					}
					statement.close();
					checkStatement.close();
					btnCreateUser.setEnabled(false);
//					connection.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
					
				}
			}
		});
		btnCreateUser.setBounds(59, 714, 186, 35);
		panel.add(btnCreateUser);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				frmCreateUserPage.setVisible(false);
				loginPage.main(null);
				frmCreateUserPage.dispose();
			}
		});
		btnBack.setBounds(251, 714, 112, 35);
		panel.add(btnBack);
		
		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserName.setBounds(59, 353, 79, 27);
		panel.add(lblUserName);
		
		
		
	}
}
