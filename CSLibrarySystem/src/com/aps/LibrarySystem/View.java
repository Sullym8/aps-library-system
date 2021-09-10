package com.aps.LibrarySystem;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.Icon;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class View {

	private JFrame frmViewPageFrame;
	ImageIcon icon = new ImageIcon(getClass().getResource("/baseline_account_balance_white_48dp.png"));
	ImageIcon logooutIcon = new ImageIcon(getClass().getResource("/baseline_login_white_24dp.png"));
	ImageIcon arrowIcon = new ImageIcon(getClass().getResource("/baseline_play_arrow_white_18dp.png"));
	ImageIcon userIcon = new ImageIcon(getClass().getResource("/adminFaivcon.png"));
	private JLabel txtCdcd;
	private JTable table;

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
					View window = new View();
					window.frmViewPageFrame.setUndecorated(true);
					window.frmViewPageFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	Connection connection = null;
	private JTextField txtDue;
	private JTextField txtRemain;
	private JTextField txtOverdue;
	private JTable table_1;
	String bookID;
	String clientID;
	String issueDate;
	/**
	 * Create the application.
	 */
	public View() {
		connection = SqliteConnector.dbConnection();
		initialize();
	}

	/**
	 * Initialize the contents of the frmViewPageFrame.
	 */
	private void initialize() {
		frmViewPageFrame = new JFrame();
		frmViewPageFrame.setTitle("View Records");
		frmViewPageFrame.setBounds(0, 0, 1366, 814);
		frmViewPageFrame.setLocationRelativeTo(null);
		frmViewPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewPageFrame.getContentPane().setMinimumSize(new Dimension(1366, 768));
		
		JPanel pnlSideMenu = new JPanel();
		pnlSideMenu.setBackground(new Color(51, 51, 102));
		
		JLabel lblLibraryText = new JLabel("APS Library");
		lblLibraryText.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryText.setForeground(Color.WHITE);
		lblLibraryText.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JLabel lblLibraryImgLabel = new JLabel("");
		lblLibraryImgLabel.setIcon(new ImageIcon(getClass().getResource("/APS.png")));
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
		lblView.setIcon(new ImageIcon(View.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlay.png")));
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
		
		JLabel lblAddUser_1_2_1_1 = new JLabel("View Overdue");
		lblAddUser_1_2_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddUser_1_2_1_1.setForeground(Color.WHITE);
		lblAddUser_1_2_1_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
		
		JLabel lblAddUser_1_2 = new JLabel("View Books");
		lblAddUser_1_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddUser_1_2.setForeground(Color.WHITE);
		lblAddUser_1_2.setFont(new Font("Tahoma", Font.ITALIC, 15));
		
		JLabel lblAddUser_1_2_1 = new JLabel("View Currently Issued");
		lblAddUser_1_2_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddUser_1_2_1.setForeground(Color.WHITE);
		lblAddUser_1_2_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDashboard uDB = new UserDashboard();
				frmViewPageFrame.setVisible(false);
				uDB.main(null);
				frmViewPageFrame.dispose();
			}
		});
		lblDashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDashboard.setForeground(Color.WHITE);
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblReturnBooks = new JLabel("Return Books");
		lblReturnBooks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblReturnBooks.setForeground(Color.WHITE);
		lblReturnBooks.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JSeparator separator_1_1_2 = new JSeparator();
		
		JLabel lblAddUser_1_2_1_1_1 = new JLabel("Export Transactions");
		lblAddUser_1_2_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ExportTransactions eT = new ExportTransactions();
				frmViewPageFrame.setVisible(false);
				eT.main(null);
				frmViewPageFrame.dispose();
			}
		});
		lblAddUser_1_2_1_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddUser_1_2_1_1_1.setForeground(Color.WHITE);
		lblAddUser_1_2_1_1_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
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
					.addGroup(gl_pnlSideMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlSideMenu.createSequentialGroup()
							.addComponent(lblView, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 137, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlSideMenu.createSequentialGroup()
							.addGroup(gl_pnlSideMenu.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlSideMenu.createSequentialGroup()
									.addGroup(gl_pnlSideMenu.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblAddUser_1_2_1_1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAddUser_1_2, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 30, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblAddUser_1_2_1, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
							.addGap(107)))
					.addGap(16))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlSideMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlSideMenu.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_pnlSideMenu.createSequentialGroup()
								.addComponent(lblBookSearch, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 137, GroupLayout.PREFERRED_SIZE))
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlSideMenu.createSequentialGroup()
							.addComponent(lblIssueBooks, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 137, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlSideMenu.createSequentialGroup()
							.addComponent(lblReturnBooks, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 137, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlSideMenu.createSequentialGroup()
							.addComponent(separator_1_1_2, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 2, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(16, Short.MAX_VALUE))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAddUser_1_2_1_1_1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
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
					.addComponent(lblAddUser_1_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAddUser_1_2_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAddUser_1_2_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAddUser_1_2_1_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBookSearch, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblIssueBooks, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(lblReturnBooks, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1_1_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(297, Short.MAX_VALUE))
		);
		pnlSideMenu.setLayout(gl_pnlSideMenu);
		
		JLabel lblManageUsers_1 = new JLabel("View");
		lblManageUsers_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JButton btnLogout = new JButton(logooutIcon);
		btnLogout.setBackground(SystemColor.controlDkShadow);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				frmViewPageFrame.setVisible(false);
				loginPage.main(null);
				frmViewPageFrame.dispose();
			}
		});
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmViewPageFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pnlSideMenu, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblManageUsers_1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 722, Short.MAX_VALUE)
									.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 954, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlSideMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblManageUsers_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
		);
		
		JLabel lblNewLabel_1 = new JLabel("Currently Issued");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		
		JLabel lblName = new JLabel("Issued To:");
		
		JLabel lblImg = new JLabel("");
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon finaIcon = new ImageIcon(new ImageIcon(getClass().getResource("/adminFaivcon.png")).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
		lblImg.setIcon(finaIcon);
		lblImg.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		txtCdcd = new JLabel();
		txtCdcd.setText("Due Date:");
		
		JLabel lblDaysRemaining = new JLabel();
		lblDaysRemaining.setText("Days Remaining:");
		
		JLabel lblNewLabel_2 = new JLabel("Days Overdue:");
		
		JButton btnProcess = new JButton("Process");
		
		btnProcess.setEnabled(false);
		
		txtDue = new JTextField();
		txtDue.setEditable(false);
		txtDue.setColumns(10);
		
		txtRemain = new JTextField();
		txtRemain.setEditable(false);
		txtRemain.setColumns(10);
		
		txtOverdue = new JTextField();
		txtOverdue.setEditable(false);
		txtOverdue.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblImg, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnProcess, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addComponent(txtCdcd, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addComponent(lblDaysRemaining, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2)
						.addComponent(txtDue, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addComponent(txtRemain, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addComponent(txtOverdue, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblImg, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCdcd)
							.addGap(1)
							.addComponent(txtDue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDaysRemaining)
							.addGap(4)
							.addComponent(txtRemain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtOverdue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnProcess))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		table = new JTable();
		
		scrollPane_1.setViewportView(table);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("All Books");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnLoad = new JButton("Load Details");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnLoad))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		panel.setLayout(gl_panel);
		frmViewPageFrame.getContentPane().setLayout(groupLayout);
		
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String issuedBook = "SELECT UserBookHistory.BookID, UserBookHistory.ClientID, RegisteredBookInfo.BookName, " +
						"RegisteredClientInfo.FullName, UserBookHistory.DateIssued FROM UserBookHistory " + 
						"INNER JOIN RegisteredBookInfo ON UserBookHistory.BookID = RegisteredBookInfo.BookID " + 
						"INNER JOIN RegisteredClientInfo ON UserBookHistory.ClientID = RegisteredClientInfo.ClientID " + 
						"WHERE UserBookHistory.DateReturned ISNULL";
				/*INNER JOIN explained in Complexity 8*/
				
				
				String allBooks = "Select bookname,author,publisher,Language,ISBN from RegisteredBookInfo";
				try {
					PreparedStatement statement = connection.prepareStatement(issuedBook);
					ResultSet resultSet = statement.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					resultSet.close();
					statement.close();
					PreparedStatement statement2 = connection.prepareStatement(allBooks);
					ResultSet resultSet2 = statement2.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(resultSet2));
					resultSet2.close();
					statement2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TimeUnit timeUnit = null;
				try {
					int row = table.getSelectedRow();
					String tableClickBookID = table.getModel().getValueAt(row, 0).toString();
					String tableClickClientID = table.getModel().getValueAt(row, 1).toString();
					String tableClickClient = "select PicturePath, FullName from RegisteredClientInfo where ClientID=?";
					String tableClickBook = "select EXPECTEDDATE from RegisteredBookInfo where BookID=?";
					
					PreparedStatement pStatement = connection.prepareStatement(tableClickClient);
					pStatement.setString(1, tableClickClientID);
					clientID = tableClickClientID;
					
					ResultSet rSet = pStatement.executeQuery();
					String imgPath = rSet.getString(1);
					ImageIcon finaIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
					lblImg.setIcon(finaIcon);
					String nameString = rSet.getString(2);
					lblName.setText("Issued To: "+ nameString);
					
					rSet.close();
					
					
					
					PreparedStatement pStatement1 = connection.prepareStatement(tableClickBook);
					
					pStatement1.setString(1, tableClickBookID);
					bookID = tableClickBookID;
					
					ResultSet rSet1 = pStatement1.executeQuery();
					
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyy");
					
					java.util.Date currentDate = calendar.getTime();
					String dueDate = rSet1.getString(1);
					java.util.Date due = dt.parse(dueDate);
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(due);
					cal.add(Calendar.DATE, -7);
					issueDate = dt.format(cal.getTime());
					 
					
					long DateDiff = due.getTime()-currentDate.getTime();
					int daysTo = (int) timeUnit.DAYS.convert(DateDiff, TimeUnit.MILLISECONDS) + 1;
					long overdueDiff = currentDate.getTime()-due.getTime();
					
					txtDue.setText(dueDate);
					
					rSet1.close();
					
					if (daysTo < 0) {
						int overDueDaysSince = (int) timeUnit.DAYS.convert(overdueDiff, TimeUnit.MILLISECONDS);
						txtRemain.setText("Overdue");
						txtOverdue.setText(String.valueOf(overDueDaysSince));
						btnProcess.setEnabled(true);
					} else {
						btnProcess.setEnabled(false);
						txtRemain.setText(String.valueOf(daysTo));
						txtOverdue.setText("N/A");
					}

					
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CalculateFines cf = new CalculateFines();
				int DaysSince = Integer.parseInt(txtOverdue.getText());
				
//				cf.FineCalc(DaysSince, clientID, bookID, issueDate);
//					this.FineCalc(Math.abs(daysTo), ClientID, BookID, issueDate);
					
					int Fine = cf.Fine(Math.abs(DaysSince));
					try {			
							String addFines = "update fines set amount=? where clientID=? and bookid=? and issued=?";
							PreparedStatement pstadd = connection.prepareStatement(addFines);
						
							pstadd.setInt(1, Fine);
							pstadd.setString(2, clientID);
							pstadd.setString(3, bookID);
							pstadd.setString(4, issueDate);
							
							pstadd.execute();
							pstadd.close();
							
							String bookStat = "update registeredbookinfo set status=2 where bookID=?";
							PreparedStatement p = connection.prepareStatement(bookStat);
							p.setString(1, bookID);
							p.execute();
							p.close();

						System.out.println(Fine);
						JOptionPane.showMessageDialog(null, "Not Returned, fine:" + Fine);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				btnProcess.setEnabled(false);
				
			}
		});
		
		lblBookSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchBooks sB = new SearchBooks();
				frmViewPageFrame.setVisible(false);
				sB.main(null);
				frmViewPageFrame.dispose();
			}
		});
		lblManageUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageUsers mU = new ManageUsers();
				frmViewPageFrame.setVisible(false);
				mU.main(null);
				frmViewPageFrame.dispose();
			}
		});
		lblManageBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageBooks mB = new ManageBooks();
				frmViewPageFrame.setVisible(false);
				mB.main(null);
				frmViewPageFrame.dispose();
			}
		});
		lblView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				View v = new View();
				frmViewPageFrame.setVisible(false);
				v.main(null);
				frmViewPageFrame.dispose();
			}
		});
		lblIssueBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IssueBooks iB = new IssueBooks();
				frmViewPageFrame.setVisible(false);
				iB.main(null);
				frmViewPageFrame.dispose();
			}
		});
		lblDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDashboard uDB = new UserDashboard();
				frmViewPageFrame.setVisible(false);
				uDB.main(null);
				frmViewPageFrame.dispose();
			}
		});
		lblReturnBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReturnBook rB = new ReturnBook();
				frmViewPageFrame.setVisible(false);
				rB.main(null);
				frmViewPageFrame.dispose();
			}
		});
		
		System.out.println(System.getProperty("user.home"));
		
		btnLoad.doClick();
	}
}
