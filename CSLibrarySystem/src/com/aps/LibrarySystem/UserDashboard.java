package com.aps.LibrarySystem;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserDashboard {

	private JFrame frmUserdashboard;
	Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
	ImageIcon icon = new ImageIcon(getClass().getResource("/APS.png"));
	ImageIcon logooutIcon = new ImageIcon(getClass().getResource("/baseline_login_white_24dp.png"));
	ImageIcon arrowIcon = new ImageIcon(getClass().getResource("/baseline_play_arrow_white_18dp.png"));
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		try {
			com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("");
          UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDashboard window = new UserDashboard();
					window.frmUserdashboard.setUndecorated(true);
					window.frmUserdashboard.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection=null;
	String userName;
	/**
	 * Create the application.
	 */
	public UserDashboard() {
		connection = SqliteConnector.dbConnection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUserdashboard = new JFrame();
		frmUserdashboard.getContentPane().setMinimumSize(new Dimension(1366, 768));
		frmUserdashboard.setTitle("Admin Dashboard");
		frmUserdashboard.setBounds(100, 100, 1366, 768);
		frmUserdashboard.setLocationRelativeTo(null);
//		frmUserdashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmUserdashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUserdashboard.setUndecorated(false);
		
		JPanel pnlSideMenu = new JPanel();
		pnlSideMenu.setBackground(new Color(51, 51, 102));
		
		JPanel pnlOverview = new JPanel();
		
		JLabel lblWelcome = new JLabel("Welcome Back!");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JButton btnLogout = new JButton(logooutIcon);
		btnLogout.setBackground(SystemColor.controlDkShadow);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				frmUserdashboard.setVisible(false);
				loginPage.main(null);
				frmUserdashboard.dispose();
			}
		});
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnLogout.setBackground((Color.RED));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogout.setBackground((Color.DARK_GRAY));
			}
		});
		
		JPanel pnlTableDetail = new JPanel();
		
		
		
		GroupLayout groupLayout = new GroupLayout(frmUserdashboard.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pnlSideMenu, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(26)
							.addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 436, Short.MAX_VALUE)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(23))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(pnlTableDetail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
								.addComponent(pnlOverview, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(pnlOverview, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pnlTableDetail, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
							.addGap(10))
						.addComponent(pnlSideMenu, GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE))
					.addGap(0))
		);
		
		JScrollPane scrollPaneTable2 = new JScrollPane();
		
		JButton btnSpecial = new JButton("Load Information");
		
		
		JLabel lblNewLabel = new JLabel("Help- Starting Instructions");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		JLabel lblAdminInfo = new JLabel("Admin Information");
		lblAdminInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_pnlTableDetail = new GroupLayout(pnlTableDetail);
		gl_pnlTableDetail.setHorizontalGroup(
			gl_pnlTableDetail.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlTableDetail.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTableDetail.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_pnlTableDetail.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneTable2, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
						.addComponent(btnSpecial, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
						.addComponent(lblAdminInfo, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_pnlTableDetail.setVerticalGroup(
			gl_pnlTableDetail.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlTableDetail.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTableDetail.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblNewLabel)
						.addComponent(lblAdminInfo, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlTableDetail.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTableDetail.createSequentialGroup()
							.addComponent(scrollPaneTable2, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSpecial, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, 0, 0, Short.MAX_VALUE))
					.addGap(1229))
		);
		
		JTextArea txtrloremIpsumDolor = new JTextArea();
		txtrloremIpsumDolor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrloremIpsumDolor.setWrapStyleWord(true);
		txtrloremIpsumDolor.setText("FAQ_TEXT");
		txtrloremIpsumDolor.setLineWrap(true);
		txtrloremIpsumDolor.setEditable(false);
		txtrloremIpsumDolor.setBackground(Color.WHITE);
		scrollPane.setViewportView(txtrloremIpsumDolor);
		
		table_1 = new JTable();
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPaneTable2.setViewportView(table_1);
		pnlTableDetail.setLayout(gl_pnlTableDetail);
		
		JPanel pnlRed = new JPanel();
		pnlRed.setBorder(null);
		pnlRed.setBackground(new Color(51, 51, 102));
		pnlRed.setBounds(new Rectangle(0, 0, 250, 170));
		
		JPanel pnlYellow = new JPanel();
		pnlYellow.setBackground(new Color(51, 51, 102));
		pnlYellow.setBounds(new Rectangle(0, 0, 250, 170));
		
		JPanel pnlGreen = new JPanel();
		pnlGreen.setBackground(new Color(51, 51, 102));
		pnlGreen.setBounds(new Rectangle(0, 0, 250, 170));
		
		JPanel pnlBlue = new JPanel();
		pnlBlue.setBackground(new Color(51, 51, 102));
		pnlBlue.setBounds(new Rectangle(0, 0, 250, 170));
		GroupLayout gl_pnlOverview = new GroupLayout(pnlOverview);
		gl_pnlOverview.setHorizontalGroup(
			gl_pnlOverview.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOverview.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlRed, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlYellow, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlGreen, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlBlue, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_pnlOverview.setVerticalGroup(
			gl_pnlOverview.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOverview.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlOverview.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(pnlBlue, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlGreen, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlYellow, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlRed, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
					.addGap(17))
		);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Total Transactions");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		JLabel lblTT = new JLabel("----");
		lblTT.setHorizontalAlignment(SwingConstants.CENTER);
		lblTT.setForeground(Color.WHITE);
		lblTT.setFont(new Font("Tahoma", Font.BOLD, 35));
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("");
		lblNewLabel_3_1_1_1.setIcon(new ImageIcon(getClass().getResource("/baseline_receipt_white_48dp.png")));
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_pnlBlue = new GroupLayout(pnlBlue);
		gl_pnlBlue.setHorizontalGroup(
			gl_pnlBlue.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBlue.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlBlue.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlBlue.createSequentialGroup()
							.addComponent(lblTT, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_3_1_1_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_pnlBlue.setVerticalGroup(
			gl_pnlBlue.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBlue.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_pnlBlue.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3_1_1_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTT, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlBlue.setLayout(gl_pnlBlue);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Books Issued");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		JLabel lblBI = new JLabel("---");
		lblBI.setHorizontalAlignment(SwingConstants.CENTER);
		lblBI.setForeground(Color.WHITE);
		lblBI.setFont(new Font("Tahoma", Font.BOLD, 35));
		
		JLabel lblNewLabel_3_1_1 = new JLabel("");
		lblNewLabel_3_1_1.setIcon(new ImageIcon(getClass().getResource("/baseline_alarm_white_48dp.png")));
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_pnlGreen = new GroupLayout(pnlGreen);
		gl_pnlGreen.setHorizontalGroup(
			gl_pnlGreen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGreen.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlGreen.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlGreen.createSequentialGroup()
							.addComponent(lblBI, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_3_1_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_pnlGreen.setVerticalGroup(
			gl_pnlGreen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGreen.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_pnlGreen.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3_1_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBI, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlGreen.setLayout(gl_pnlGreen);
		
		JLabel lblNewLabel_1_1 = new JLabel("Registered Books");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		JLabel lblRB = new JLabel("---");
		lblRB.setHorizontalAlignment(SwingConstants.CENTER);
		lblRB.setForeground(Color.WHITE);
		lblRB.setFont(new Font("Tahoma", Font.BOLD, 35));
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(getClass().getResource("/baseline_library_books_white_48dp.png")));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_pnlYellow = new GroupLayout(pnlYellow);
		gl_pnlYellow.setHorizontalGroup(
			gl_pnlYellow.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlYellow.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlYellow.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlYellow.createSequentialGroup()
							.addComponent(lblRB, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_pnlYellow.setVerticalGroup(
			gl_pnlYellow.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlYellow.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_pnlYellow.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRB, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlYellow.setLayout(gl_pnlYellow);
		
		JLabel lblNewLabel_1 = new JLabel("Registered Users");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setForeground(SystemColor.text);
		
		JLabel lblRU = new JLabel("---");
		lblRU.setHorizontalAlignment(SwingConstants.CENTER);
		lblRU.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblRU.setForeground(SystemColor.text);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(getClass().getResource("/baseline_people_white_48dp.png")));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_pnlRed = new GroupLayout(pnlRed);
		gl_pnlRed.setHorizontalGroup(
			gl_pnlRed.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRed.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_pnlRed.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlRed.createSequentialGroup()
							.addComponent(lblRU, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_pnlRed.setVerticalGroup(
			gl_pnlRed.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRed.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlRed.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRU, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		pnlRed.setLayout(gl_pnlRed);
		pnlOverview.setLayout(gl_pnlOverview);
		
		JLabel lblLibraryImgLabel = new JLabel("");
		lblLibraryImgLabel.setBackground(new Color(51, 51, 102));
		lblLibraryImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryImgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLibraryImgLabel.setOpaque(true);
//		lblNewLabel.setBounds(0, 0, 422, 327);
//		lblNewLabel.setIcon(icon);
		lblLibraryImgLabel.setIcon(icon);
		
		JLabel lblLibraryText = new JLabel("APS Library");
		lblLibraryText.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblLibraryText.setForeground(SystemColor.window);
		lblLibraryText.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setIcon(new ImageIcon(UserDashboard.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlay.png")));
		lblDashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDashboard.setForeground(Color.WHITE);
		
		JLabel lblManageUsers = new JLabel("Manage Users");
		lblManageUsers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblManageUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ManageUsers manageUsers = new ManageUsers();
				frmUserdashboard.setVisible(false);
				frmUserdashboard.dispose();
				manageUsers.main(null);
				
			}
		});
		lblManageUsers.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblManageUsers.setForeground(Color.WHITE);
		
		JLabel lblManageBooks = new JLabel("Manage Books");
		lblManageBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageBooks manageBooks = new ManageBooks();
				frmUserdashboard.setVisible(false);
				frmUserdashboard.dispose();
				manageBooks.main(null);
			}
		});
		lblManageBooks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblManageBooks.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblManageBooks.setForeground(Color.WHITE);
		
		JLabel lblView = new JLabel("View");
		lblView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				View view = new View();
				frmUserdashboard.setVisible(false);
				frmUserdashboard.dispose();
				view.main(null);
			}
		});
		lblView.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblView.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblView.setForeground(Color.WHITE);
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblBookSearch = new JLabel("Book Search");
		lblBookSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SearchBooks searchBooks = new SearchBooks();
				frmUserdashboard.setVisible(false);
				searchBooks.main(null);
				frmUserdashboard.dispose();
			}
		});
		lblBookSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBookSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBookSearch.setForeground(Color.WHITE);
		
		JLabel lblBookIssue = new JLabel("Issue Books");
		lblBookIssue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IssueBooks issueReturnBook = new IssueBooks();
				frmUserdashboard.setVisible(false);
				issueReturnBook.main(null);
				frmUserdashboard.dispose();
				
			}
		});
		lblBookIssue.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBookIssue.setFont(new Font("Tahoma", Font.BOLD, 15));	
		lblBookIssue.setForeground(Color.WHITE);
		
		JLabel lblReturnBooks = new JLabel("Return Books");
		lblReturnBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ReturnBook returnBook = new ReturnBook();
				frmUserdashboard.setVisible(false);
				returnBook.main(null);
				frmUserdashboard.dispose();
			}
		});
		lblReturnBooks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblReturnBooks.setForeground(Color.WHITE);
		lblReturnBooks.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JSeparator separator_1_1_1_1 = new JSeparator();
		separator_1_1_1_1.setForeground(Color.WHITE);
//		lblNewLabel.setBackground(Color.BLACK);
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
					.addComponent(lblBookIssue, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblReturnBooks, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator_1_1_1_1, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
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
					.addComponent(lblBookIssue, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblReturnBooks, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1572, Short.MAX_VALUE))
		);
		btnSpecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String queryString = "select UserID, LibrarianFullName, Username from AdminLoginInfo";
					PreparedStatement statement = connection.prepareStatement(queryString);
					ResultSet resultSet = statement.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					statement.close();
					resultSet.close();
					
					String RUQuery = "select count(*) from RegisteredClientInfo";
					PreparedStatement s = connection.prepareStatement(RUQuery);
					ResultSet r = s.executeQuery();
					lblRU.setText(r.getString(1));
					r.close();
					s.close();
					
					String RBQuery = "select count(*) from RegisteredBookInfo";
					s = connection.prepareStatement(RBQuery);
					r = s.executeQuery();
					lblRB.setText(r.getString(1));
					r.close();
					s.close();
					
					String BIQuery = "SELECT count(*) FROM UserBookHistory " + 
							"INNER JOIN RegisteredBookInfo ON UserBookHistory.BookID = RegisteredBookInfo.BookID " + 
							"INNER JOIN RegisteredClientInfo ON UserBookHistory.ClientID = RegisteredClientInfo.ClientID " + 
							"WHERE UserBookHistory.DateReturned ISNULL";
					s = connection.prepareStatement(BIQuery);
					r = s.executeQuery();
					lblBI.setText(r.getString(1));
					r.close();
					s.close();
					
					String TTQuery = "select count(*) from UserBookHistory";
					s = connection.prepareStatement(TTQuery);
					r = s.executeQuery();
					lblTT.setText(r.getString(1));
					r.close();
					s.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnSpecial.doClick();
		
		pnlSideMenu.setLayout(gl_pnlSideMenu);
		frmUserdashboard.getContentPane().setLayout(groupLayout);
	}
}
