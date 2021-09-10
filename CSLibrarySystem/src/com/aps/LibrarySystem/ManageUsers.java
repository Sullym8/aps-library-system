package com.aps.LibrarySystem;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
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
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class ManageUsers {

	private JFrame frmManagUsers;
	ImageIcon icon = new ImageIcon(getClass().getResource("/baseline_account_balance_white_48dp.png"));
	ImageIcon logooutIcon = new ImageIcon(getClass().getResource("/baseline_login_white_24dp.png"));
	ImageIcon arrowIcon = new ImageIcon(getClass().getResource("/baseline_play_arrow_white_18dp.png"));
	ImageIcon userIcon = new ImageIcon(getClass().getResource("/adminFaivcon.png"));
	ImageIcon defaultIcon = new ImageIcon(new ImageIcon(getClass().getResource("/adminFaivcon.png")).getImage().getScaledInstance(179, 193, Image.SCALE_SMOOTH));
	
	private JTextField txtSearchField;
	private JSpinner spnGrade;
	private JTextField txtFldID;
	private JTextField txtFldName;
	private JTextField txtFldEmail;
	private JTable table;
	private JTextArea txtAreaAdd;
	private JComboBox cmbxType;
	private JSpinner spnDue;
	private JSpinner spnFines;
	private JLabel lblClientImg;
	
	String clientNameString;
	String clientID;
	String clientAddress;
	int clientType;
	String clientEmail;
	int clientGrade;
	int Due;
	int Fines;
	String imgPath;
	ImageIcon finalIcon;
	Connection connection = null;
	
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
					ManageUsers window = new ManageUsers();
					window.frmManagUsers.setUndecorated(true);
					window.frmManagUsers.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public ManageUsers() {
		connection = SqliteConnector.dbConnection();
		initialize();
	}
	
	public int loadDet(String keyID) {
		int done = 0;
		try {
			String tableClickQueryString = "select * from RegisteredClientInfo where ClientID=?";
			PreparedStatement pStatement = connection.prepareStatement(tableClickQueryString);
			
			pStatement.setString(1, keyID);
			ResultSet rSet = pStatement.executeQuery();
			
			if (rSet.next()) {
				clientNameString = rSet.getString(2);
				txtFldName.setText(clientNameString);
				clientID = rSet.getString(1);
				txtFldID.setText(clientID);
				clientAddress = rSet.getString(5);
				txtAreaAdd.setText(clientAddress);
				clientType = rSet.getInt(3);
				cmbxType.setSelectedIndex(clientType);
				clientEmail = rSet.getString(6);
				txtFldEmail.setText(clientEmail);
				if (clientType==0) {
					clientGrade = rSet.getInt(4);
					spnGrade.setValue(clientGrade);
				}
				Due = rSet.getInt(8);
				spnDue.setValue(Due);
				Fines = rSet.getInt(14);
				spnFines.setValue(Fines);
				imgPath = rSet.getString(7);
				System.out.println(imgPath);
				finalIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(179, 193, Image.SCALE_SMOOTH));
				lblClientImg.setIcon(finalIcon);
				rSet.close();
				
				done = 1;
			} else {
				done = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManagUsers = new JFrame();
//		frmManagUsers.getContentPane().setMinimumSize(new Dimension(1366, 768));
		frmManagUsers.setBounds(0, 0, 1366, 800);
		frmManagUsers.setLocationRelativeTo(null);
		frmManagUsers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManagUsers.setTitle("Manage Users");
		
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
		lblManageUsers.setIcon(new ImageIcon(ManageUsers.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlay.png")));
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
		
		JLabel lblUpdateUserInfo = new JLabel("Update User Info");
		lblUpdateUserInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ModifyClientEntry modifyClientEntry = new ModifyClientEntry();
				frmManagUsers.setVisible(false);
				modifyClientEntry.main(null);
				frmManagUsers.dispose();

			}
		});
		lblUpdateUserInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUpdateUserInfo.setForeground(Color.WHITE);
		lblUpdateUserInfo.setFont(new Font("Tahoma", Font.ITALIC, 15));
		
		JLabel lblRemoveUser = new JLabel("Remove User");
		lblRemoveUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RemoveClientEntry removeClientEntry = new RemoveClientEntry();
				frmManagUsers.setVisible(false);
				removeClientEntry.main(null);
				frmManagUsers.dispose();
			}
		});
		lblRemoveUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRemoveUser.setForeground(Color.WHITE);
		lblRemoveUser.setFont(new Font("Tahoma", Font.ITALIC, 15));
		
		JLabel lblAddUser = new JLabel("Add User");
		lblAddUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CreateClientEntry addClient = new CreateClientEntry();
				frmManagUsers.setVisible(false);
				addClient.main(null);
				frmManagUsers.dispose();
			}
		});
		lblAddUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddUser.setForeground(Color.WHITE);
		lblAddUser.setFont(new Font("Tahoma", Font.ITALIC, 15));
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDashboard uDB = new UserDashboard();
				frmManagUsers.setVisible(false);
				uDB.main(null);
				frmManagUsers.dispose();
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
					.addContainerGap()
					.addComponent(lblManageBooks, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
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
					.addGap(29)
					.addGroup(gl_pnlSideMenu.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUpdateUserInfo, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRemoveUser, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAddUser, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(134, Short.MAX_VALUE))
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
					.addComponent(lblAddUser, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRemoveUser, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUpdateUserInfo, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
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
					.addContainerGap(315, Short.MAX_VALUE))
		);
		pnlSideMenu.setLayout(gl_pnlSideMenu);
		
		JLabel lblManageUsers_1 = new JLabel("Manage Users");
		lblManageUsers_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JButton btnLogout = new JButton(new ImageIcon(getClass().getResource("/baseline_login_white_24dp.png")));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				frmManagUsers.setVisible(false);
				loginPage.main(null);
				frmManagUsers.dispose();
			}
		});
		btnLogout.setBackground(SystemColor.controlDkShadow);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout groupLayout = new GroupLayout(frmManagUsers.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pnlSideMenu, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblManageUsers_1, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 449, Short.MAX_VALUE)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE))
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pnlSideMenu, GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
					.addGap(0))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblManageUsers_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		lblClientImg = new JLabel("");
		lblClientImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientImg.setIcon(defaultIcon);
		
		JLabel lblNewLabel_2 = new JLabel("User Details");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblClientImg, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClientImg, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))))
		);
		
		JLabel lblNewLabel_8 = new JLabel("User Type");
		
		JButton btnLoadDet = new JButton("Load Details");
		
		
		cmbxType = new JComboBox();
		cmbxType.setModel(new DefaultComboBoxModel(new String[] {"Student", "Teacher"}));
		cmbxType.setEnabled(false);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		
		txtFldEmail = new JTextField();
		txtFldEmail.setEnabled(false);
		txtFldEmail.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Books Due:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		JLabel lblNewLabel_7_1 = new JLabel("Total Fines:");
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		spnDue = new JSpinner();
		spnDue.setEnabled(false);
		
		spnFines = new JSpinner();
		spnFines.setEnabled(false);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(48)
					.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spnDue)
					.addGap(34)
					.addComponent(lblNewLabel_7_1, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spnFines, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnLoadDet, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_8, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(txtFldEmail, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
								.addComponent(cmbxType, 0, 261, Short.MAX_VALUE))))
					.addGap(0))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8)
						.addComponent(cmbxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(txtFldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7)
						.addComponent(spnDue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spnFines, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7_1))
					.addGap(18)
					.addComponent(btnLoadDet, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblNewLabel_6 = new JLabel("Student Address");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblNewLabel_4 = new JLabel("ID Number");
		lblNewLabel_4.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel lblNewLabel_5 = new JLabel("Grade:");
		
		JLabel lblNewLabel_4_1 = new JLabel("Name:");
		lblNewLabel_4_1.setVerticalAlignment(SwingConstants.BOTTOM);
		
		spnGrade = new JSpinner();
		spnGrade.setEnabled(false);
		
		txtFldID = new JTextField();
		txtFldID.setEnabled(false);
		txtFldID.setColumns(10);
		
		txtFldName = new JTextField();
		txtFldName.setEnabled(false);
		txtFldName.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_4_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(spnGrade, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
								.addComponent(txtFldID, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
								.addComponent(txtFldName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))))
					.addGap(0))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(txtFldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(6)
							.addComponent(lblNewLabel_4_1)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(txtFldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(6)
							.addComponent(lblNewLabel_4)))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(14)
							.addComponent(lblNewLabel_5))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(8)
							.addComponent(spnGrade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(12)
					.addComponent(lblNewLabel_6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		txtAreaAdd = new JTextArea();
		txtAreaAdd.setWrapStyleWord(true);
		txtAreaAdd.setEnabled(false);
		txtAreaAdd.setLineWrap(true);
		scrollPane_1.setViewportView(txtAreaAdd);
		panel_2.setLayout(gl_panel_2);
		panel_1.setLayout(gl_panel_1);
		
		txtSearchField = new JTextField();
		txtSearchField.setToolTipText("Enter a name");
		txtSearchField.setColumns(10);
		
		JButton btnSearch = new JButton("User Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String rawString = txtSearchField.getText();
				String nameToIDQuery = "select ClientID from RegisteredClientInfo where Fullname=?";
				String IDQuery = "select ClientID from RegisteredClientInfo where ClientID=?";
				boolean notName = false;
				try {
					PreparedStatement pStatement = connection.prepareStatement(nameToIDQuery);
					pStatement.setString(1, rawString);
					ResultSet rst = pStatement.executeQuery();
					
					if (!rst.isBeforeFirst()) {
						rst.close();
						pStatement = connection.prepareStatement(IDQuery);
						pStatement.setString(1, rawString);
						rst = pStatement.executeQuery();
						if (!rst.isBeforeFirst()) {
							rst.close();
							throw new Exception("No such Client Exists");
						} else {
							String ID = rst.getString("ClientID");
							loadDet(ID);
							rst.close();
						}
					} else {
						String ID = rst.getString("ClientID");
						loadDet(ID);
						rst.close();
					}					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("User Table");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(txtSearchField, GroupLayout.PREFERRED_SIZE, 703, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSearchField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				String tableClickString = table.getModel().getValueAt(row, 0).toString();
				loadDet(tableClickString);
			}
		});
		scrollPane.setViewportView(table);
		
		btnLoadDet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String userQueryString = "select clientid,fullname,email from RegisteredClientInfo";
					/*Select details about every client relevant to Librarian from RegisteredClient Info
					 * No validation or verification of data necessary*/
					PreparedStatement statement = connection.prepareStatement(userQueryString);
					ResultSet resultSet = statement.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLoadDet.doClick();
		
		lblBookSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchBooks sB = new SearchBooks();
				frmManagUsers.setVisible(false);
				sB.main(null);
				frmManagUsers.dispose();
			}
		});
		lblManageUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageUsers mU = new ManageUsers();
				frmManagUsers.setVisible(false);
				mU.main(null);
				frmManagUsers.dispose();
			}
		});
		lblManageBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageBooks mB = new ManageBooks();
				frmManagUsers.setVisible(false);
				mB.main(null);
				frmManagUsers.dispose();
			}
		});
		lblView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				View v = new View();
				frmManagUsers.setVisible(false);
				v.main(null);
				frmManagUsers.dispose();
			}
		});
		lblIssueBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IssueBooks iB = new IssueBooks();
				frmManagUsers.setVisible(false);
				iB.main(null);
				frmManagUsers.dispose();
			}
		});
		lblDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDashboard uDB = new UserDashboard();
				frmManagUsers.setVisible(false);
				uDB.main(null);
				frmManagUsers.dispose();
			}
		});
		lblReturnBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReturnBook rB = new ReturnBook();
				frmManagUsers.setVisible(false);
				rB.main(null);
				frmManagUsers.dispose();
			}
		});
		
		panel.setLayout(gl_panel);
		frmManagUsers.getContentPane().setLayout(groupLayout);
		frmManagUsers.setTitle("Manage Users");
//		frmManagUsers.setBounds(0, 0, 1366, 768);
		frmManagUsers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
