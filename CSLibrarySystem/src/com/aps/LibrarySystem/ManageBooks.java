package com.aps.LibrarySystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import java.awt.Cursor;

public class ManageBooks {

	private JFrame frmManageBooksFrame;
	ImageIcon icon = new ImageIcon(getClass().getResource("/baseline_account_balance_white_48dp.png"));
	ImageIcon logooutIcon = new ImageIcon(getClass().getResource("/baseline_login_white_24dp.png"));
	ImageIcon arrowIcon = new ImageIcon(getClass().getResource("/baseline_play_arrow_white_18dp.png"));
	ImageIcon bookIcon = new ImageIcon(getClass().getResource("/Moleskine-Blank-Book-icon.png"));
	private JTextField txtFldAuthor;
	private JTextField txtFldTitle;
	private JTextField txtFldISBN;

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
					ManageBooks window = new ManageBooks();
					window.frmManageBooksFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	String BookID;
	private JTable table;
	private JTextField txtFldPublisher;
	private JTextField txtFldCategory;
	/**
	 * Create the application.
	 */
	public ManageBooks() {
		connection = SqliteConnector.dbConnection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManageBooksFrame = new JFrame();
		frmManageBooksFrame.setBounds(0, 0, 1366, 835);
		frmManageBooksFrame.setLocationRelativeTo(null);
		frmManageBooksFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManageBooksFrame.setTitle("Manage Books");
		
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
		lblManageBooks.setIcon(new ImageIcon(ManageBooks.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlay.png")));
		lblManageBooks.setForeground(Color.WHITE);
		lblManageBooks.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblView = new JLabel("View");
		lblView.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblView.setForeground(Color.WHITE);
		lblView.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblManageBooks_1_1_1 = new JLabel("Issue Books");
		lblManageBooks_1_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblManageBooks_1_1_1.setForeground(Color.WHITE);
		lblManageBooks_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblManageBooks_1_1 = new JLabel("Book Search");
		lblManageBooks_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblManageBooks_1_1.setForeground(Color.WHITE);
		lblManageBooks_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblAddUser_1_1_1 = new JLabel("Update Book");
		lblAddUser_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ModifyBookEntry updateBook = new ModifyBookEntry();
				frmManageBooksFrame.setVisible(false);
				updateBook.main(null);
				frmManageBooksFrame.dispose();
			}
		});
		lblAddUser_1_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddUser_1_1_1.setForeground(Color.WHITE);
		lblAddUser_1_1_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
		
		JLabel lblAddUser_1_1 = new JLabel("Remove Book");
		lblAddUser_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RemoveBookEntry removeBook = new RemoveBookEntry();
				frmManageBooksFrame.setVisible(false);
				removeBook.main(null);
				frmManageBooksFrame.dispose();
			}
		});
		lblAddUser_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddUser_1_1.setForeground(Color.WHITE);
		lblAddUser_1_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
		
		JLabel lblAddBookr = new JLabel("Add Book");
		lblAddBookr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CreateBookEntry addBook = new CreateBookEntry();
				frmManageBooksFrame.setVisible(false);
				addBook.main(null);
				frmManageBooksFrame.dispose();
			}
		});
		lblAddBookr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddBookr.setForeground(Color.WHITE);
		lblAddBookr.setFont(new Font("Tahoma", Font.ITALIC, 15));
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDashboard uDB = new UserDashboard();
				frmManageBooksFrame.setVisible(false);
				uDB.main(null);
				frmManageBooksFrame.dispose();
				
			}
		});
		lblDashboard.setForeground(Color.WHITE);
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblManageBooks_1_1_2 = new JLabel("Return Books");
		lblManageBooks_1_1_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblManageBooks_1_1_2.setForeground(Color.WHITE);
		lblManageBooks_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
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
						.addComponent(lblManageBooks_1_1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE))
					.addGap(16))
				.addGroup(gl_pnlSideMenu.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_pnlSideMenu.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAddUser_1_1_1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAddUser_1_1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAddBookr, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(135, Short.MAX_VALUE))
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
					.addComponent(lblAddBookr, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAddUser_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAddUser_1_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
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
					.addContainerGap(350, Short.MAX_VALUE))
		);
		pnlSideMenu.setLayout(gl_pnlSideMenu);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblNewLabel_2 = new JLabel("Book Details");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		JLabel lblBookImg = new JLabel("");
		lblBookImg.setBackground(Color.BLACK);
		lblBookImg.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblBookImg.setIcon(new ImageIcon(getClass().getResource("/bookIco.png")));
		
		JPanel panel_2 = new JPanel();
		
		JLabel lblNewLabel_6 = new JLabel("Synopsis");
		
		JLabel lblNewLabel_5 = new JLabel("Category");
		
		JLabel lblNewLabel_4_1 = new JLabel("Title");
		lblNewLabel_4_1.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel lblNewLabel_4 = new JLabel("Author");
		lblNewLabel_4.setVerticalAlignment(SwingConstants.BOTTOM);
		
		txtFldAuthor = new JTextField();
		txtFldAuthor.setEditable(false);
		txtFldAuthor.setColumns(10);
		
		txtFldTitle = new JTextField();
		txtFldTitle.setEditable(false);
		txtFldTitle.setColumns(10);
		
		txtFldCategory = new JTextField();
		txtFldCategory.setEditable(false);
		txtFldCategory.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
									.addGap(18))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblNewLabel_4_1)
									.addGap(36))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(11)))
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(txtFldCategory, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
								.addComponent(txtFldTitle, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
								.addComponent(txtFldAuthor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)))
						.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFldTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4_1))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFldAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFldCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5))
					.addGap(18)
					.addComponent(lblNewLabel_6)
					.addGap(4))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		
		JLabel lblNewLabel_7 = new JLabel("ISBN");
		
		JLabel lblNewLabel_8 = new JLabel("Publisher");
		
		txtFldISBN = new JTextField();
		txtFldISBN.setEditable(false);
		txtFldISBN.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Condition");
		
		JComboBox comboBoxCondition = new JComboBox();
		comboBoxCondition.setModel(new DefaultComboBoxModel(new String[] {"New", "Damaged", "Lost"}));
		
		txtFldPublisher = new JTextField();
		txtFldPublisher.setEditable(false);
		txtFldPublisher.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Status");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Available", "Issued", "Overdue", "-"}));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
							.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBoxCondition, 0, 283, Short.MAX_VALUE)
						.addComponent(comboBox, Alignment.LEADING, 0, 283, Short.MAX_VALUE)
						.addComponent(txtFldISBN, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
						.addComponent(txtFldPublisher, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFldISBN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7))
					.addGap(14)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8)
						.addComponent(txtFldPublisher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_9)
						.addComponent(comboBoxCondition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JTextArea txtAreaSynopsis = new JTextArea();
		txtAreaSynopsis.setWrapStyleWord(true);
		txtAreaSynopsis.setEditable(false);
		txtAreaSynopsis.setLineWrap(true);
		scrollPane_1.setViewportView(txtAreaSynopsis);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 726, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblBookImg, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBookImg, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel_2, 0, 0, Short.MAX_VALUE)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 124, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		
		JLabel lblNewLabel_1 = new JLabel("Books Table");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnLoadDet = new JButton("Load Books");
		btnLoadDet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String bookQueryString = "select bookid,bookname,author,publisher,Language,ISBN from RegisteredBookInfo";
					/*Select details about every book relevant to Librarian from RegisteredBookInfo
					 * No validation or verification of data necessary*/
					PreparedStatement statement = connection.prepareStatement(bookQueryString);
					ResultSet resultSet = statement.executeQuery();
					
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLoadDet, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(btnLoadDet))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					int row = table.getSelectedRow();
					String tableClickString = table.getModel().getValueAt(row, 0).toString();
					String tableClickQueryString = "select * from RegisteredBookInfo where BookID=?";
					PreparedStatement pStatement = connection.prepareStatement(tableClickQueryString);
					
					pStatement.setString(1, tableClickString);
					ResultSet rSet = pStatement.executeQuery();
					
					if (rSet.next()) {
						String bookName = rSet.getString(2);
						txtFldTitle.setText(bookName);
						String authorName = rSet.getString(3);
						txtFldAuthor.setText(authorName);
						String ISBN = rSet.getString(6);
						txtFldISBN.setText(ISBN);
						String Synopsis = rSet.getString(8);
						txtAreaSynopsis.setText(Synopsis);
						String Publisher = rSet.getString(4);
						txtFldPublisher.setText(Publisher);
						int Cond = rSet.getInt(13);
						comboBoxCondition.setSelectedIndex(Cond);
						int Stat = rSet.getInt(10);
						if (Cond!=2) {
							comboBox.setSelectedIndex(Stat);
						}else {
							comboBox.setSelectedIndex(3);
						}
						String Category = rSet.getString(7);
						txtFldCategory.setText(Category);
						String imgPath = rSet.getString(9);
						
						ImageIcon finaIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(144, 200, Image.SCALE_SMOOTH));
						
						lblBookImg.setIcon(finaIcon);
						
						BookID = rSet.getString(1);
						rSet.close();
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		
		JLabel lblManageUsers_1 = new JLabel("Manage Books");
		lblManageUsers_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		lblManageBooks_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchBooks sB = new SearchBooks();
				frmManageBooksFrame.setVisible(false);
				sB.main(null);
				frmManageBooksFrame.dispose();
			}
		});
		lblManageUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageUsers mU = new ManageUsers();
				frmManageBooksFrame.setVisible(false);
				mU.main(null);
				frmManageBooksFrame.dispose();
			}
		});
		lblManageBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageBooks mB = new ManageBooks();
				frmManageBooksFrame.setVisible(false);
				mB.main(null);
				frmManageBooksFrame.dispose();
			}
		});
		lblView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				View v = new View();
				frmManageBooksFrame.setVisible(false);
				v.main(null);
				frmManageBooksFrame.dispose();
			}
		});
		lblManageBooks_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IssueBooks iB = new IssueBooks();
				frmManageBooksFrame.setVisible(false);
				iB.main(null);
				frmManageBooksFrame.dispose();
			}
		});
		lblDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDashboard uDB = new UserDashboard();
				frmManageBooksFrame.setVisible(false);
				uDB.main(null);
				frmManageBooksFrame.dispose();
			}
		});
		lblManageBooks_1_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReturnBook rB = new ReturnBook();
				frmManageBooksFrame.setVisible(false);
				rB.main(null);
				frmManageBooksFrame.dispose();
			}
		});
		
		JButton btnLogout = new JButton(new ImageIcon(getClass().getResource("/baseline_login_white_24dp.png")));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				frmManageBooksFrame.setVisible(false);
				loginPage.main(null);
				frmManageBooksFrame.dispose();
			}
		});
		btnLogout.setBackground(SystemColor.controlDkShadow);
		GroupLayout groupLayout = new GroupLayout(frmManageBooksFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pnlSideMenu, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblManageUsers_1, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 449, Short.MAX_VALUE)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlSideMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(24)
									.addComponent(lblManageUsers_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(16)
									.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 202, Short.MAX_VALUE)
							.addGap(15)))
					.addGap(0))
		);
		frmManageBooksFrame.getContentPane().setLayout(groupLayout);
		btnLoadDet.doClick();
		
	}
}
