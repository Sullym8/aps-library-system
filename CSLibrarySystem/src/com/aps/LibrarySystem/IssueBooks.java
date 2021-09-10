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

@SuppressWarnings("unused")
public class IssueBooks {
	
	private JTextField txtClientID;
	private JComboBox cmbxType;
	private JComboBox comboBoxcond;
	private JTextField spnGrade;
	private JSpinner spnDue;
	private JTextField txtFldBookName;
	private JComboBox cmbxStatus;
	private JTextField txtFldClientName;
	private JTextField txtFldISBN;
	private JTextField txtFldBookID;
	private JTextField txtFldExpectedDate;
	private JTextField txtFines;
	private JFrame frmIssueReturn;

	List<Book> catalogList = new ArrayList<Book>();
	Book b1 = new Book();
	Book b2 = new Book();
	Book b3 = new Book();
	Book b4 = new Book();
	int page;
	
	Connection connection = null;
	String imgPath;
	String bookID;
	String clientID;	
	String clientNameString;
	String bookName;
	String bookISBN;
	int bookStatus;
	String expectedDate;
	int clientType;
	String clientGrade;
	int Due;
	int Fines;
	ImageIcon finalIcon;
	int bookCondition= 0;
	ImageIcon logooutIcon = new ImageIcon(getClass().getResource("/baseline_login_white_24dp.png"));
	
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
			@Override
			public void run() {
				try {
					IssueBooks window = new IssueBooks();
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
	public IssueBooks() {
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
	
	public void Issue() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyy");
		String issueDate = dt.format(calendar.getTime());
//		System.out.println(issueDate);
		calendar.add(Calendar.DATE, 7);
		String dueDate = dt.format(calendar.getTime());
		
		int clientDueNo = Due+1;
		
		
		
		try {
			/*All of these Statements are subject to execution only if inputted Data passes Validation and Verification*/
			
			String clientUpdateQueries = "update registeredclientinfo set booksdue=?,bookdue"+clientDueNo+"=? where clientID=?";
			/*Updates the issuing client's record in RegisteredClientInfo to increment the amount of books due and 
			 * The value of BookDueN, where N is the Nth book issued by the client*/
			String bookUpdateQueries = "update registeredbookinfo set status=?,issuedate=?, expecteddate=? where bookID=?";
			/*Updates Issued Book's record in RegisteredBookInfo where it's status is set to 1 (i.e. Issued)
			 * Issue Date is set to the day book is issued (dd-mm-yyyy format)
			 * Expected Date is set to 1 week from isssue date (dd-mm-yyyy format)*/
			String historyQuery = "insert into userbookhistory (bookid,clientid,DateIssued) values (?,?,?)";
			/*Adds new record into the permanent transaction table, UserBookHistory with issuing ClientID,
			 * issued BookID, DateIssued but leaving DateReturned=NULL to indicate currently issued*/
			String fineQuery = "insert into fines (clientID,bookid,issued,Amount) values (?,?,?,?)";
			/*Adds a new record into the temporary fine table with issuing ClientID, issuedBookID,
			 * Date of Issue and Amount=0
			 * When the book is overdue, Fine Amount will be written
			 * Note: This specific record is deleted when the issued book is returned in any condition or
			 * reported lost*/
			
			PreparedStatement clientStatement = connection.prepareStatement(clientUpdateQueries);
			PreparedStatement bookStatement = connection.prepareStatement(bookUpdateQueries);
			PreparedStatement historyStatement = connection.prepareStatement(historyQuery);
			PreparedStatement fineStatement = connection.prepareStatement(fineQuery);
			
			clientStatement.setInt(1, clientDueNo);
			clientStatement.setString(2, bookID);
			clientStatement.setString(3, clientID);
			bookStatement.setInt(1, 1);
			bookStatement.setString(2, issueDate);
			bookStatement.setString(3, dueDate);
			bookStatement.setString(4, bookID);
			historyStatement.setString(1, bookID);
			historyStatement.setString(2, clientID);
			historyStatement.setString(3, issueDate);
			fineStatement.setString(1, clientID);
			fineStatement.setString(2, bookID);
			fineStatement.setString(3, issueDate);
			fineStatement.setInt(4, 0);
			
			Due++;
			bookStatus=1;
			
			clientStatement.execute();
			bookStatement.execute();
			historyStatement.execute();
			fineStatement.execute();
			
			clientStatement.close();
			bookStatement.close();
			historyStatement.close();
			fineStatement.close();
			
			JOptionPane.showMessageDialog(null, "Issued");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void loadCatalog() {
		String query = "select BookID, Picture from registeredbookinfo where rowid >"+(page*4)+" limit 4";
		try {
			PreparedStatement p = connection.prepareStatement(query);
			ResultSet resultSet = p.executeQuery();
			
			resultSet.next();
			b1.setBookID(resultSet.getString(1));
			b1.setBookPath(resultSet.getString(2));
			resultSet.next();
			b2.setBookID(resultSet.getString(1));
			b2.setBookPath(resultSet.getString(2));
			resultSet.next();
			b3.setBookID(resultSet.getString(1));
			b3.setBookPath(resultSet.getString(2));
			resultSet.next();
			b4.setBookID(resultSet.getString(1));
			b4.setBookPath(resultSet.getString(2));
			
			catalogList.add(b1);
			catalogList.add(b2);
			catalogList.add(b3);
			catalogList.add(b4);
			
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int loadDet(String keyID, String bookOrClient) {
		spnDue.setValue(0);
		txtFldExpectedDate.setText("");
		int done = 0;
			String clientQuery = "select * from RegisteredClientInfo where ClientID=?";
			String bookQuery = "select * from RegisteredBookInfo where BookID=?";
			
			if (bookOrClient == "Client") {
				try {
					if (keyID.equals("")) {
						throw new Exception("Missing Fields");
					}else if (keyID.length()!=5) {
						throw new Exception("Incorrect ID length (5 char)");
					}
					PreparedStatement pStatement = connection.prepareStatement(clientQuery);
					pStatement.setString(1, keyID);
					ResultSet rSet = pStatement.executeQuery();
					
					if (rSet.next()) {
						clientID = rSet.getString("ClientID");
						clientNameString = rSet.getString("Fullname");
						txtFldClientName.setText(clientNameString);
						clientType = rSet.getInt("clienttype");
						cmbxType.setSelectedIndex(clientType);
						if (clientType==0) {
							clientGrade = rSet.getString("grade");
							spnGrade.setText(clientGrade);
						} else {
							spnGrade.setText("N/A");
						}
						Due = rSet.getInt("BooksDue");
						spnDue.setValue(Due);
						Fines = rSet.getInt("TotalFines");
						txtFines.setText(String.valueOf(Fines));
						imgPath = rSet.getString("picturepath");
						finalIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH));
						
						done = 1;
					} else {
						done = 0;
						throw new Exception("Non exisiting Client");
					}
					rSet.close();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if (bookOrClient == "Book") {
				try {
					if (keyID.equals("")) {
						throw new Exception("Missing Fields");
					}else if (keyID.length()!=13) {
						throw new Exception("Incorrect ID length (13 char)");
					}
					PreparedStatement pStatement = connection.prepareStatement(bookQuery);
					pStatement.setString(1, keyID);
					ResultSet rSet = pStatement.executeQuery();
//					System.out.println(bookOrClient);
					if (rSet.next()) {
//						System.out.println(42);
						bookID = rSet.getString("BookID");
						bookName = rSet.getString("bookname");
						txtFldBookName.setText(bookName);
						bookISBN = rSet.getString("ISBN");
						txtFldISBN.setText(bookISBN);
						
						bookCondition=rSet.getInt("Condition");
						comboBoxcond.setSelectedIndex(bookCondition);
						bookStatus = rSet.getInt("Status");
						if (bookCondition!=2) {
							cmbxStatus.setSelectedIndex(bookStatus);
						}else {
							cmbxStatus.setSelectedIndex(3);
						}
						if (bookStatus == 1) {
							expectedDate = rSet.getString("ExpectedDate");
							txtFldExpectedDate.setText(expectedDate);
						}
						
						imgPath = rSet.getString("Picture");
						finalIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(220, 230, Image.SCALE_SMOOTH));
						System.out.println(done);
						done = 1;
					} else {
						done = 0;
						throw new Exception("Non exisiting Book");
					}
					rSet.close();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		return done;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIssueReturn = new JFrame();
		frmIssueReturn.setUndecorated(true);
		frmIssueReturn.setResizable(false);
		frmIssueReturn.setTitle("Issue Books");
		frmIssueReturn.setBounds(100, 100, 1334, 768);
		frmIssueReturn.setLocationRelativeTo(null);
		frmIssueReturn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/APS.png"));
		ImageIcon closeButton = new ImageIcon(getClass().getResource("/baseline_close_white_18dp.png"));
		
		JPanel panel = new JPanel();
		JPanel panel_1 = new JPanel();
		JPanel pnlSideMenu = new JPanel();
		JLabel lblLibraryText = new JLabel("APS Library");
		JLabel lblLibraryImgLabel = new JLabel("");
		JLabel lblManageUsers = new JLabel("Manage Users");
		JLabel lblManageBooks = new JLabel("Manage Books");
		JLabel lblView = new JLabel("View");
		JLabel lblManageBooks_1_1_1 = new JLabel("Issue Books");
		JSeparator separator = new JSeparator();
		JLabel lblManageBooks_1_1 = new JLabel("Book Search");
		JSeparator separator_1 = new JSeparator();
		JLabel lblDashboard = new JLabel("Dashboard");
		JSeparator separator_1_1_2 = new JSeparator();
		JLabel lblManageBooks_1_1_2 = new JLabel("Return Books");
		JLabel lblManageUsers_1 = new JLabel("Issue and Book Catalog");
		JButton btnLogout = new JButton(logooutIcon);
		JPanel panel_2_2 = new JPanel();
		JButton btnLess = new JButton("Previous Page");
		JButton btnMore = new JButton("Next Page");
		JButton btnLoadDetBooks = new JButton("Load Details");
		JLabel bookImg4 = new JLabel("");
		JLabel bookImg3 = new JLabel("");
		JLabel bookImg2 = new JLabel("");
		JLabel bookImg1 = new JLabel("");
		JLabel lblTitle = new JLabel("User ID:");
		JLabel lblNewLabel_2 = new JLabel("Grade:");
		JLabel lblNewLabel_3 = new JLabel("Books Due:");
		JLabel lblNewLabel_4 = new JLabel("Book ID:");
		JLabel lblNewLabel_5 = new JLabel("Book Title:");
		JLabel lblNewLabel_6 = new JLabel("ISBN:");
		JLabel lblNewLabel = new JLabel("Status:");
		JLabel lblNewLabel_7 = new JLabel("Client Name");
		JButton btnIssue = new JButton("Issue Book");
		JButton btnLoadDetClient = new JButton("Load Details");	
		JLabel lblNewLabel_7_1 = new JLabel("Client Type");
		JLabel lblSynopsis = new JLabel("Expected Date:");
		JPanel panel_3 = new JPanel();
		JPanel panel_4 = new JPanel();
		JLabel lblNewLabel_9 = new JLabel("Issue Books");
		JLabel lblNewLabel_1 = new JLabel("Total Fines:");
		JLabel lblNewLabel_8 = new JLabel("Condition:");
		
		panel.setBorder(null);
		
		pnlSideMenu.setBackground(new Color(51, 51, 102));
		
		lblLibraryText.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryText.setForeground(Color.WHITE);
		lblLibraryText.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		
		lblLibraryImgLabel.setIcon(icon);
		lblLibraryImgLabel.setOpaque(true);
		lblLibraryImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryImgLabel.setBackground(new Color(51, 51, 102));
		lblLibraryImgLabel.setAlignmentX(0.5f);
		
		
		
		lblManageUsers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblManageUsers.setForeground(Color.WHITE);
		lblManageUsers.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		
		lblManageBooks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblManageBooks.setForeground(Color.WHITE);
		lblManageBooks.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		
		lblView.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblView.setForeground(Color.WHITE);
		lblView.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		
		lblManageBooks_1_1_1.setIcon(new ImageIcon(IssueBooks.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlay.png")));
		lblManageBooks_1_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblManageBooks_1_1_1.setForeground(Color.WHITE);
		lblManageBooks_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		lblManageBooks_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchBooks sB = new SearchBooks();
				frmIssueReturn.setVisible(false);
				SearchBooks.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblManageBooks_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblManageBooks_1_1.setForeground(Color.WHITE);
		lblManageBooks_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		
		lblDashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDashboard.setForeground(Color.WHITE);
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		lblManageBooks_1_1_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblManageBooks_1_1_2.setForeground(Color.WHITE);
		lblManageBooks_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
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
							.addComponent(lblManageBooks_1_1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 137, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE))
					.addGap(16))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblManageBooks_1_1_1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblManageBooks_1_1_2, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
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
					.addComponent(lblManageBooks_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblManageBooks_1_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblManageBooks_1_1_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1_1_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(460, Short.MAX_VALUE))
		);
		pnlSideMenu.setLayout(gl_pnlSideMenu);
		
		
		lblManageUsers_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				frmIssueReturn.setVisible(false);
				LoginPage.main(null);
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 469, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 461, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblManageUsers_1, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(lblManageUsers_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE))
					.addGap(103))
				.addComponent(pnlSideMenu, GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
		);
		
		
		panel_2_2.setBackground(SystemColor.menu);
		panel_2_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		
		
		
		JPanel panel_2_2_1 = new JPanel();
		panel_2_2_1.setBackground(SystemColor.menu);
		panel_2_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_2_2_2 = new JPanel();
		panel_2_2_2.setBackground(SystemColor.menu);
		panel_2_2_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_2_2_2_1 = new JPanel();
		panel_2_2_2_1.setBackground(SystemColor.menu);
		panel_2_2_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(panel_2_2, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_2_2_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnLess, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_2_2_2, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnMore, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_2_2_2_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2_2, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2_2_1, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2_2_2, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2_2_2_1, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLess)
						.addComponent(btnMore))
					.addContainerGap())
		);
		panel_2_2_2_1.setLayout(new CardLayout(0, 0));
		
		
		bookImg4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookImg4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtFldBookID.setText(catalogList.get(3).getBookID());
				btnLoadDetBooks.doClick();
			}
		});
		bookImg4.setBackground(SystemColor.menu);
		bookImg4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_2_2_1.add(bookImg4, "name_357528837293600");
		panel_2_2_2.setLayout(new CardLayout(0, 0));
		
		
		
		bookImg3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookImg3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtFldBookID.setText(catalogList.get(2).getBookID());
				btnLoadDetBooks.doClick();
			}
		});
		bookImg3.setBackground(SystemColor.menu);
		bookImg3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_2_2.add(bookImg3, "name_357524137125400");
		panel_2_2_1.setLayout(new CardLayout(0, 0));
		
		
		bookImg2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookImg2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtFldBookID.setText(catalogList.get(1).getBookID());
				btnLoadDetBooks.doClick();
			}
		});
		bookImg2.setBackground(SystemColor.menu);
		bookImg2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_2_1.add(bookImg2, "name_357519701323000");
		panel_2_2.setLayout(new CardLayout(0, 0));
		
		
		bookImg1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookImg1.setBackground(SystemColor.menu);
		bookImg1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtFldBookID.setText(catalogList.get(0).getBookID());
				btnLoadDetBooks.doClick();
			}
		});
		bookImg1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_2.add(bookImg1, "name_357467862474300");
		panel_1.setLayout(gl_panel_1);
		
		
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtClientID = new JTextField();
		txtClientID.setColumns(10);
		
		cmbxType = new JComboBox();
		cmbxType.setModel(new DefaultComboBoxModel(new String[] {"Student", "Teacher"}));
		cmbxType.setEditable(false);
		
		spnGrade = new JTextField();
		spnGrade.setEditable(false);
		
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		spnDue = new JSpinner();
		spnDue.setEnabled(false);
		
		txtFldBookName = new JTextField();
		txtFldBookName.setEditable(false);
		txtFldBookName.setColumns(10);
		
		cmbxStatus = new JComboBox();
		cmbxStatus.setModel(new DefaultComboBoxModel(new String[] {"Available", "Issued", "Over Due", "-"}));
		
		
		
		txtFldClientName = new JTextField();
		txtFldClientName.setEditable(false);
		txtFldClientName.setColumns(10);
		
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		txtFldISBN = new JTextField();
		txtFldISBN.setEditable(false);
		txtFldISBN.setColumns(10);
		
		txtFldBookID = new JTextField();
		txtFldBookID.setColumns(10);
		
		panel_3.setBackground(SystemColor.menu);
		
		txtFldExpectedDate = new JTextField();
		txtFldExpectedDate.setEditable(false);
		txtFldExpectedDate.setColumns(10);
		
		panel_4.setBackground(SystemColor.menu);
		
		txtFines = new JTextField();
		txtFines.setHorizontalAlignment(SwingConstants.TRAILING);
		txtFines.setEditable(false);
		txtFines.setColumns(10);		
		
		comboBoxcond = new JComboBox();
		comboBoxcond.setModel(new DefaultComboBoxModel(new String[] {"New", "Damaged", "Lost"}));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnIssue, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
						.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
						.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtFldClientName, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
								.addComponent(lblNewLabel_7_1, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
								.addComponent(cmbxType, 0, 216, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
								.addComponent(spnGrade, 203, 216, Short.MAX_VALUE)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
								.addComponent(spnDue, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
								.addComponent(txtClientID, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
								.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLoadDetClient, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFines, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
						.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cmbxStatus, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtFldISBN)
								.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtFldBookName)
								.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtFldBookID, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(lblNewLabel_8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtFldExpectedDate)
								.addComponent(lblSynopsis, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBoxcond, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(14)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLoadDetBooks, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_9)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtClientID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLoadDetClient))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(lblNewLabel_7)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFldClientName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_7_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cmbxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spnGrade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spnDue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(16)
							.addComponent(panel_3, 0, 0, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtFines, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFldBookID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLoadDetBooks))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFldBookName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_6)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFldISBN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cmbxStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_8)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(comboBoxcond, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSynopsis)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFldExpectedDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(btnIssue, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(16))
		);
		
		JLabel lblBookImg = new JLabel("");
		lblBookImg.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/bookIco.png")).getImage().getScaledInstance(175, 175, Image.SCALE_SMOOTH)));
		lblBookImg.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblBookImg.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addComponent(lblBookImg, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblBookImg, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblClientImg = new JLabel("");
		lblClientImg.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblClientImg.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/adminFaivcon.png")).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		lblClientImg.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblClientImg, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(lblClientImg, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
		);
		panel_3.setLayout(gl_panel_3);
		
		btnLoadDetClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String clientID = txtClientID.getText();
				int Confirm = loadDet(clientID, "Client");
				if (Confirm == 1) {
					lblClientImg.setIcon(finalIcon);
				}
			}
		});
		
		btnLoadDetBooks.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String bookID = txtFldBookID.getText();
				int Confirm = loadDet(bookID, "Book");
				if (Confirm == 1) {
					lblBookImg.setIcon(finalIcon);
				}
			}
		});
		
		btnIssue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				btnLoadDetClient.doClick();
				btnLoadDetBooks.doClick();
				
				if (clientType==0&&!txtClientID.getText().isEmpty()){
					if (Due < 3) {
						if (bookStatus==0 && bookCondition!=2&&!txtFldBookID.getText().isEmpty()) {
							Issue();
							btnLoadDetClient.doClick();
							btnLoadDetBooks.doClick();
						} else {
							JOptionPane.showMessageDialog(null, "Not Available");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Max Issue Limit Reached");
					}
				} else if (clientType== 1&&!txtClientID.getText().isEmpty()) {
					if (Due < 5) {
						if (bookStatus==0 && bookCondition!=2&&!txtFldBookID.getText().isEmpty()) {
							Issue();
							btnLoadDetClient.doClick();
							btnLoadDetBooks.doClick();
						} else {
							JOptionPane.showMessageDialog(null, "Not Available");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Max Issue Limit Reached");
					}
				}
//				btnLoadTable.doClick();
			}
		});
		
		btnLess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				page--;
				System.out.println(page);
				loadCatalog();
				
					ImageIcon i1 = new ImageIcon(new ImageIcon(catalogList.get(0).getBookPath()).getImage().getScaledInstance(213, 290, Image.SCALE_SMOOTH));
					bookImg1.setIcon(i1);
					
					ImageIcon i2 = new ImageIcon(new ImageIcon(catalogList.get(1).getBookPath()).getImage().getScaledInstance(213, 290, Image.SCALE_SMOOTH));
					bookImg2.setIcon(i2);
					
					ImageIcon i3 = new ImageIcon(new ImageIcon(catalogList.get(2).getBookPath()).getImage().getScaledInstance(213, 290, Image.SCALE_SMOOTH));
					bookImg3.setIcon(i3);
					
					ImageIcon i4 = new ImageIcon(new ImageIcon(catalogList.get(3).getBookPath()).getImage().getScaledInstance(213, 290, Image.SCALE_SMOOTH));
					bookImg4.setIcon(i4);
			}
		});
		
		btnMore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				page++;
				System.out.println(page);
				loadCatalog();
				ImageIcon i1 = new ImageIcon(new ImageIcon(catalogList.get(0).getBookPath()).getImage().getScaledInstance(213, 290, Image.SCALE_SMOOTH));
				bookImg1.setIcon(i1);
				
				ImageIcon i2 = new ImageIcon(new ImageIcon(catalogList.get(1).getBookPath()).getImage().getScaledInstance(213, 290, Image.SCALE_SMOOTH));
				bookImg2.setIcon(i2);
				
				ImageIcon i3 = new ImageIcon(new ImageIcon(catalogList.get(2).getBookPath()).getImage().getScaledInstance(213, 290, Image.SCALE_SMOOTH));
				bookImg3.setIcon(i3);
				
				ImageIcon i4 = new ImageIcon(new ImageIcon(catalogList.get(3).getBookPath()).getImage().getScaledInstance(213, 290, Image.SCALE_SMOOTH));
				bookImg4.setIcon(i4);
			}
		});
		
		lblManageBooks_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchBooks sB = new SearchBooks();
				frmIssueReturn.setVisible(false);
				SearchBooks.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblManageUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageUsers mU = new ManageUsers();
				frmIssueReturn.setVisible(false);
				ManageUsers.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblManageBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageBooks mB = new ManageBooks();
				frmIssueReturn.setVisible(false);
				ManageBooks.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				View v = new View();
				frmIssueReturn.setVisible(false);
				View.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblManageBooks_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IssueBooks iB = new IssueBooks();
				frmIssueReturn.setVisible(false);
				IssueBooks.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDashboard uDB = new UserDashboard();
				frmIssueReturn.setVisible(false);
				UserDashboard.main(null);
				frmIssueReturn.dispose();
			}
		});
		lblManageBooks_1_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReturnBook rB = new ReturnBook();
				frmIssueReturn.setVisible(false);
				ReturnBook.main(null);
				frmIssueReturn.dispose();
			}
		});
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				frmIssueReturn.setVisible(false);
				LoginPage.main(null);
				frmIssueReturn.dispose();
			}
		});
		
		btnMore.doClick();
		panel.setLayout(gl_panel);
		frmIssueReturn.getContentPane().setLayout(groupLayout);
	}
}
