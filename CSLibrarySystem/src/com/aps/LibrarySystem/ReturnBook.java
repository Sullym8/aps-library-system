package com.aps.LibrarySystem;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.Font;
import java.awt.Window.Type;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.awt.Choice;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;
import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("unused")
public class ReturnBook {
	
	ImageIcon logooutIcon = new ImageIcon(getClass().getResource("/baseline_login_white_24dp.png"));
	
	ArrayList<String> IDPath = new ArrayList<String>();
	Book b1 = new Book();
	Book b2 = new Book();
	Book b3 = new Book();
	Book b4 = new Book();
	int page;
	
	Connection connection = null;
	String bookID;
	String clientID;
	int Fines;
	private JComboBox cmbxType;
	
	String clientNameString;
	String bookName;
	String bookISBN;
	int bookStatus;
	String expectedDate;
	int clientType;
	String clientGrade;
	int Due;
	ImageIcon finalIcon;
	int bookCondition= 0;
	private JTextField txtIssue;
	private JTextField txtDue;
	private JTextField txtFine;
	private JTable table;
	
	private JFrame frmIssueReturn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("");
          UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook window = new ReturnBook();
					window.frmIssueReturn.setUndecorated(true);
					window.frmIssueReturn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public ReturnBook() {
		connection = SqliteConnector.dbConnection();
		page = -1;
		initialize();
		
	}
	
	public String getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyy");
		String date = dt.format(calendar.getTime());
		return date;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIssueReturn = new JFrame();
		frmIssueReturn.setUndecorated(true);
		frmIssueReturn.setResizable(false);
		frmIssueReturn.setTitle("Return Books");
		frmIssueReturn.setBounds(0, 0, 1351, 809);
		frmIssueReturn.setLocationRelativeTo(null);
		frmIssueReturn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/APS.png"));
		ImageIcon closeButton = new ImageIcon(getClass().getResource("/baseline_close_white_18dp.png"));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel pnlSideMenu = new JPanel();
		pnlSideMenu.setBackground(new Color(51, 51, 102));
		
		JLabel lblLibraryText = new JLabel("APS Library");
		lblLibraryText.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryText.setForeground(Color.WHITE);
		lblLibraryText.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JLabel lblLibraryImgLabel = new JLabel("");
		lblLibraryImgLabel.setIcon(icon);
		lblLibraryImgLabel.setOpaque(true);
		lblLibraryImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryImgLabel.setBackground(new Color(51, 51, 102));
		lblLibraryImgLabel.setAlignmentX(0.5f);
		
		JLabel lblManageUsers = new JLabel("Manage Users");
		lblManageUsers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblManageUsers.setForeground(Color.WHITE);
		lblManageUsers.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblManageBooks = new JLabel("Manage Books");
		lblManageBooks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblManageBooks.setForeground(Color.WHITE);
		lblManageBooks.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblView = new JLabel("View");
		lblView.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblView.setForeground(Color.WHITE);
		lblView.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblIssueBooks = new JLabel("Issue Books");
		lblIssueBooks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIssueBooks.setForeground(Color.WHITE);
		lblIssueBooks.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblBookSearch = new JLabel("Book Search");
		lblBookSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBookSearch.setForeground(Color.WHITE);
		lblBookSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDashboard uDB = new UserDashboard();
				frmIssueReturn.setVisible(false);
				uDB.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblDashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDashboard.setForeground(Color.WHITE);
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblReturnBooks = new JLabel("Return Books");
		lblReturnBooks.setIcon(new ImageIcon(ReturnBook.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlay.png")));
		lblReturnBooks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblReturnBooks.setForeground(Color.WHITE);
		lblReturnBooks.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JSeparator separator_1_1_2 = new JSeparator();
		GroupLayout gl_pnlSideMenu = new GroupLayout(pnlSideMenu);
		gl_pnlSideMenu.setHorizontalGroup(
			gl_pnlSideMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addGap(93)
					.addGroup(gl_pnlSideMenu.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLibraryText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
						.addComponent(lblLibraryImgLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
					.addGap(92))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblManageUsers, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addGap(15)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDashboard, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblManageBooks, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblView, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlSideMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlSideMenu.createSequentialGroup()
							.addComponent(lblBookSearch, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 137, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE))
					.addGap(16))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIssueBooks, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblReturnBooks, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator_1_1_2, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_pnlSideMenu.setVerticalGroup(
			gl_pnlSideMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLibraryImgLabel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLibraryText, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDashboard, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblManageUsers, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblManageBooks, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblView, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBookSearch, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblIssueBooks, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblReturnBooks, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1_1_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(460, Short.MAX_VALUE))
		);
		pnlSideMenu.setLayout(gl_pnlSideMenu);
		
		JLabel lblManageUsers_1 = new JLabel("Return and Issue History");
		lblManageUsers_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JButton btnLogout = new JButton(logooutIcon);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				frmIssueReturn.setVisible(false);
				loginPage.main(null);
				frmIssueReturn.dispose();
			}
		});
		btnLogout.setBackground(SystemColor.controlDkShadow);
		GroupLayout groupLayout = new GroupLayout(frmIssueReturn.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pnlSideMenu, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblManageUsers_1, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
							.addGap(466)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblManageUsers_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 703, GroupLayout.PREFERRED_SIZE)
					.addGap(50))
				.addComponent(pnlSideMenu, GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
		);
		
		cmbxType = new JComboBox();
		cmbxType.setModel(new DefaultComboBoxModel(new String[] {"To Be Returned", "Previous Records"}));
		cmbxType.setEditable(false);
		
		
		JLabel lblNewLabel_9 = new JLabel("History");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JButton btnLoadTable = new JButton("Load Details");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JComboBox cmbxReturnType = new JComboBox();
		cmbxReturnType.setEnabled(false);
		cmbxReturnType.setModel(new DefaultComboBoxModel(new String[] {"Regular Return", "Return Damaged Book", "Report Lost"}));
		
		JButton btnRet = new JButton("Return/Report");
		btnRet.setEnabled(false);
		
		
		JButton btnProcess = new JButton("Process");
		btnProcess.setEnabled(false);
		
		
		JLabel lblNewLabel = new JLabel("Date Issued:");
		
		txtIssue = new JTextField();
		txtIssue.setEditable(false);
		txtIssue.setColumns(10);
		
		JLabel lblBookNae = new JLabel("Date Expected:");
		
		txtDue = new JTextField();
		txtDue.setEditable(false);
		txtDue.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fine Amount:");
		
		txtFine = new JTextField();
		txtFine.setEditable(false);
		txtFine.setColumns(10);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
						.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(cmbxType, GroupLayout.PREFERRED_SIZE, 744, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoadTable, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(cmbxReturnType, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRet, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
						.addComponent(txtIssue, GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
						.addComponent(lblBookNae, GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
						.addComponent(txtDue, GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
						.addComponent(btnProcess, GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
						.addComponent(txtFine, GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_9)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLoadTable))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtIssue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBookNae)
					.addGap(4)
					.addComponent(txtDue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnProcess, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtFine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnRet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cmbxReturnType, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cmbxType.getSelectedIndex()==1) {
					
				} else {
					try {
						int row = table.getSelectedRow();
						bookID = table.getModel().getValueAt(row, 0).toString();
						clientID = table.getModel().getValueAt(row, 1).toString();
						
						String expect = "select Issuedate, EXPECTEDDATE from RegisteredBookInfo where BookID=?";
						PreparedStatement preparedStatement = connection.prepareStatement(expect);
						preparedStatement.setString(1, bookID);
						
						ResultSet r = preparedStatement.executeQuery();
						txtDue.setText(r.getString(2));
						txtIssue.setText(r.getString(1));
						r.close();
						preparedStatement.close();
						
						btnProcess.setEnabled(true);
						
					} catch (Exception e2) {
						// TODO: handle exception
					}

				}
								
			}
		});
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		frmIssueReturn.getContentPane().setLayout(groupLayout);
		
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cmbxType.getSelectedIndex()==0) {
					try {
						String issueQuery = "select BookID, ClientID, DateIssued from UserBookHistory where ConditionReturned is NULL";
						PreparedStatement statement = connection.prepareStatement(issueQuery);
						ResultSet resultSet = statement.executeQuery();
						
						table.setModel(DbUtils.resultSetToTableModel(resultSet));
						resultSet.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else {
					try {
						String issueQuery = "select BookID, ClientID, DateIssued,DateReturned from UserBookHistory where ConditionReturned is not NULL";
						PreparedStatement statement = connection.prepareStatement(issueQuery);
						ResultSet resultSet = statement.executeQuery();
						
						table.setModel(DbUtils.resultSetToTableModel(resultSet));
						resultSet.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateFines cf = new CalculateFines();
				String statement = "select amount from fines where clientid=?";
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(statement);
					preparedStatement.setString(1, clientID);
					ResultSet resultSet = preparedStatement.executeQuery();
					Fines = resultSet.getInt(1);
					if (Fines==0) {
						int f = cf.FineProcess(bookID, clientID, txtIssue.getText());
						txtFine.setText(String.valueOf(f));
					}else {
						txtFine.setText(String.valueOf(Fines));
					}
					
					resultSet.close();
					preparedStatement.close();
					
					btnRet.setEnabled(true);
					cmbxReturnType.setEnabled(true);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		btnRet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retType = cmbxReturnType.getSelectedIndex();
				int x=0;
				int cond=0;
				String issueDate;
				
				if (retType==0) {
					x = 0;
					cond = 0;
					//update totalfinestable += fines
				}else if (retType==1) {
					x  = 100;
					cond = 1;
					//Totalfnes +=x
					//update fines.amount = x
				}else if (retType==2) {
					x = 250;
					cond =2;
					//Totalfines += x
					//update fines.amt = x
				}			
				try {
					String clientCheckQuery = "select bookdue1,bookdue2,bookdue3,bookdue4,bookdue5, booksdue"
							+ " from registeredclientinfo where clientID=?";
					String bookCheckQuery = "select status,issuedate from registeredbookinfo where bookid=?";
					String historyCheckQuery = "select bookid, clientID from userbookhistory where clientID=? and bookid=?";
					
					PreparedStatement check1pst = connection.prepareStatement(clientCheckQuery);
					check1pst.setString(1, clientID);
					PreparedStatement check2pst = connection.prepareStatement(bookCheckQuery);
					check2pst.setString(1, bookID);
					PreparedStatement check3pst = connection.prepareStatement(historyCheckQuery);
					check3pst.setString(1, clientID);
					check3pst.setString(2, bookID);
					
					ResultSet clientrst = check1pst.executeQuery();
					System.out.println(clientID);
					System.out.println(bookID);
					String val1 = clientrst.getString(1);
					String val2 = clientrst.getString(2);
					String val3 = clientrst.getString(3);
					String val4 = clientrst.getString(4);
					String val5 = clientrst.getString(5);
					Due = clientrst.getInt(6);
					List<String> valList = Arrays.asList(val1,val2,val3,val4,val5);
					
					if (valList.contains(bookID)) {
						int val = valList.indexOf(bookID)+1;
						/*Determination of BookDueN, where N is the Nth book Issued
						 * valList contains bookIDs in the same manner as they are stored
						 * However, index in valList starts from 0 while in table from 1*/
						String clientQuery = "update registeredclientinfo set booksdue=?,bookdue"+val+"=?where clientID=?";
						/*Updating client record in RegisteredClientInfo where clientID is that of
						 * client who is returning the book.
						 * Books Due must be decremented by 1 and BooKDueN is made NULL*/
						PreparedStatement clientStatement = connection.prepareStatement(clientQuery);
						
						
						
						
						
						clientStatement.setInt(1, Due-1);
						Due = Due -1;
						clientStatement.setString(2, "");
						clientStatement.setString(3, clientID);
						clientStatement.execute();
						clientrst.close();
						
						clientQuery = "select Totalfines from registeredclientinfo where clientID=?";
						/*Selection of TotalFines for a given client*/
						clientStatement = connection.prepareStatement(clientQuery);
						
						clientStatement.setString(1, clientID);
						clientrst = clientStatement.executeQuery();
						
						int exist = clientrst.getInt(1);
						clientrst.close();
						clientStatement.close();
						
						String recordFines = "update RegisteredClientInfo set totalfines=? where clientID=?";
						/*Updates Total fines of a client to compound existing fines and current
						 * book Fine into a single integer amount.
						 * Value is reflected in ManageUsers*/
						PreparedStatement preparedStatement = connection.prepareStatement(recordFines);
						
						preparedStatement.setString(1, String.valueOf(Fines+x+exist));
						preparedStatement.setString(2, clientID);
						
						preparedStatement.execute();
						preparedStatement.close();
						
					}
					ResultSet bookrst = check2pst.executeQuery();
					issueDate=bookrst.getString(2);
					if (bookrst.getInt(1)!=0) {
						/*Checks if booked being returned was actually issued in the first place.
						 * Unlikely to occur in program but should Database be modified, it may 
						 * pose a security threat, hence check required*/
						String bookQuery = "update registeredbookinfo set Status=?,Condition=?,IssueDate=?,ExpectedDate=? where bookID=?";
						/*Updating Book record in RegisteredBookInfo where bookID is that of
						 * book being returned.
						 * Books Status must be set to 0 to indicate availability
						 * Condition depends on condition book was returned in
						 * IssueDate and ExpectedDate are made NULL*/
						PreparedStatement bookStatement = connection.prepareStatement(bookQuery);
						
						
						bookStatement.setInt(1, 0);
						bookStatement.setInt(2, cond);
						bookStatement.setString(3, "");
						bookStatement.setString(4, "");
						bookStatement.setString(5, bookID);
						
						bookStatement.execute();
						
						bookrst.close();
					}
					ResultSet historyrst = check3pst.executeQuery();
					System.out.println(issueDate);
					
					if (historyrst.getString(1).equals(bookID) && historyrst.getString(2).equals(clientID)) {
						/*Checks if correct record is being accessed by a given client and book ID*/
						String historyQuery = "update userbookhistory set DateReturned=?,ConditionReturned=? where bookID=? and clientID=?";
						/*Updating permanent transaction record in UserBookHistory where clientID is that of
						 * client who is returning the book and bookID is corresponding ID for returned book.
						 * DateReturned is set to Date of Return, calculated by algorithm
						 * ConditionReturned depends on condition book was returned in*/
						PreparedStatement historyStatement = connection.prepareStatement(historyQuery);
						
						
						historyStatement.setString(1, getCurrentDate());
						historyStatement.setInt(2, cond);
						historyStatement.setString(3, bookID);
						historyStatement.setString(4, clientID);
						
						historyStatement.execute();
						
						historyrst.close();
					}
					
					String deleteQuery = "delete from fines where clientid=? AND issued=? AND bookid=?";
					/*Used to clear out record of returned book from Temporary fine table FINES*/
					PreparedStatement updateStatement = connection.prepareStatement(deleteQuery);
					updateStatement.setString(1, clientID);
					updateStatement.setString(2, issueDate);
					updateStatement.setString(3, bookID);
					updateStatement.execute();
					
					JOptionPane.showMessageDialog(null, "Successfully Returned!");
//					btnLoadDetClient.doClick();
//					btnLoadDetBooks.doClick();
					btnLoadTable.doClick();
					btnProcess.setEnabled(false);
					btnRet.setEnabled(false);
					
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
		});
		
		lblBookSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchBooks sB = new SearchBooks();
				frmIssueReturn.setVisible(false);
				sB.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblManageUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageUsers mU = new ManageUsers();
				frmIssueReturn.setVisible(false);
				mU.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblManageBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageBooks mB = new ManageBooks();
				frmIssueReturn.setVisible(false);
				mB.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				View v = new View();
				frmIssueReturn.setVisible(false);
				v.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblIssueBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IssueBooks iB = new IssueBooks();
				frmIssueReturn.setVisible(false);
				iB.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDashboard uDB = new UserDashboard();
				frmIssueReturn.setVisible(false);
				uDB.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblReturnBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReturnBook rB = new ReturnBook();
				frmIssueReturn.setVisible(false);
				rB.main(null);
				frmIssueReturn.dispose();
			}
		});
		
		btnLoadTable.doClick();
		
	}
}
