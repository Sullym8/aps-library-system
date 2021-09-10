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
import java.awt.Choice;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("unused")
public class RemoveBookEntry {

	Connection connection = null;
	String imgPath;
	ImageIcon finaIcon = new ImageIcon(new ImageIcon(getClass().getResource("/Moleskine-Blank-Book-icon.png")).getImage().getScaledInstance(316, 375, Image.SCALE_SMOOTH));
	private JTextField txtBookID;
	private JTextField txtFldAuthor;
	private JTextField txtFldPublisher;
	private JTextField txtFldLanguage;
	private JTextField txtFldISBN;
	private JTextField txtFldCategory;
	private JComboBox cmbxCondition;
	private JTextField txtFldBookName;
	private JTextField txtFldKW;
	
	private JFrame frmRemoveBookEntryPage;

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
					RemoveBookEntry window = new RemoveBookEntry();
					window.frmRemoveBookEntryPage.setUndecorated(true);
					window.frmRemoveBookEntryPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public RemoveBookEntry() {
		connection = SqliteConnector.dbConnection();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRemoveBookEntryPage = new JFrame();
		frmRemoveBookEntryPage.setUndecorated(true);
		frmRemoveBookEntryPage.setResizable(false);
		frmRemoveBookEntryPage.setTitle("Remove Book Entry");
		frmRemoveBookEntryPage.setBounds(0, 0, 770, 650);
		frmRemoveBookEntryPage.setLocationRelativeTo(null);
		frmRemoveBookEntryPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/baseline_person_add_white_48dp.png"));
		ImageIcon closeButton = new ImageIcon(getClass().getResource("/baseline_close_white_18dp.png"));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBorder(null);
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmRemoveBookEntryPage.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		
		JButton btnRemoveBook = new JButton("Remove Book");
		btnRemoveBook.setEnabled(false);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBackground(SystemColor.controlDkShadow);
		btnNewButton_1.setForeground(SystemColor.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageBooks manageBooks = new ManageBooks();
				frmRemoveBookEntryPage.setVisible(false);
				manageBooks.main(null);
				frmRemoveBookEntryPage.dispose();
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(btnNewButton_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
				.addComponent(btnRemoveBook, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(btnRemoveBook, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblBookImg = new JLabel("");
		lblBookImg.setHorizontalAlignment(SwingConstants.CENTER);

		lblBookImg.setIcon(finaIcon);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBookImg, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBookImg, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblTitle = new JLabel("Book ID:");
		
		txtBookID = new JTextField();
		txtBookID.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Book Publisher:");
		
		txtFldAuthor = new JTextField();
		txtFldAuthor.setEditable(false);
		txtFldAuthor.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Language");
		
		txtFldPublisher = new JTextField();
		txtFldPublisher.setEditable(false);
		txtFldPublisher.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ISBN Number:");
		
		txtFldLanguage = new JTextField();
		txtFldLanguage.setEditable(false);
		txtFldLanguage.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Category:");
		
		txtFldISBN = new JTextField();
		txtFldISBN.setEditable(false);
		txtFldISBN.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Key Words:");
		
		txtFldCategory = new JTextField();
		txtFldCategory.setEditable(false);
		txtFldCategory.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Condition:");
		
		cmbxCondition = new JComboBox();
		cmbxCondition.setModel(new DefaultComboBoxModel(new String[] {"New", "Lightly Used", "Modertately Used", "Thoroughly Used", "Damaged"}));
		cmbxCondition.setEnabled(false);
		
		JLabel lblNewLabel_7 = new JLabel("Book Title:");
		
		txtFldBookName = new JTextField();
		txtFldBookName.setEditable(false);
		txtFldBookName.setColumns(10);
		
		JButton btnLoadDet = new JButton("Load Details");
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_9 = new JLabel("Remove Book");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNewLabel_7_1 = new JLabel("Author");
		
		txtFldKW = new JTextField();
		txtFldKW.setEditable(false);
		txtFldKW.setColumns(10);
		
		JLabel lblSynopsis = new JLabel("Synopsis");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
						.addComponent(lblNewLabel_7_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, Alignment.LEADING)
						.addComponent(txtFldKW, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6, Alignment.LEADING)
						.addComponent(lblNewLabel, Alignment.LEADING)
						.addComponent(cmbxCondition, 0, 373, Short.MAX_VALUE)
						.addComponent(lblSynopsis, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4, Alignment.LEADING)
						.addComponent(lblNewLabel_3, Alignment.LEADING)
						.addComponent(lblNewLabel_7, Alignment.LEADING)
						.addComponent(txtFldBookName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(txtBookID, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoadDet, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
						.addComponent(txtFldAuthor, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
						.addComponent(txtFldPublisher, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
						.addComponent(txtFldLanguage, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
						.addComponent(txtFldISBN, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
						.addComponent(txtFldCategory, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
						.addComponent(lblNewLabel_5, Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
						.addComponent(lblTitle, Alignment.LEADING))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_9)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtBookID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLoadDet))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_7)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFldBookName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_7_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFldAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFldPublisher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFldLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFldISBN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFldCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFldKW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cmbxCondition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSynopsis)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		JTextArea txtAreaSynopsis = new JTextArea();
		txtAreaSynopsis.setEditable(false);
		txtAreaSynopsis.setLineWrap(true);
		txtAreaSynopsis.setWrapStyleWord(true);
		scrollPane.setViewportView(txtAreaSynopsis);
		
		btnLoadDet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bookID = txtBookID.getText();
				System.out.println(bookID);
				
				try {
					if (bookID.length()!=13) {
						throw new Exception("Incorrect ID Length (13 char)");
					}
					String queryString = "select BookName,Author,Publisher,Language,ISBN,Category,Synopsis,Picture,Condition,KeyWord from RegisteredBookInfo where BookID=?";
					String checkString = "select * from registeredbookinfo where bookid=?";
					PreparedStatement pstcheck = connection.prepareStatement(checkString);
//					select BookName,Author,Publisher,Language,ISBN,Category,Synopsis,Picture,Condition,KeyWord from RegisteredBookInfo where BookID=?
					PreparedStatement pst = connection.prepareStatement(queryString);
					pstcheck.setString(1, bookID);
					pst.setString(1, bookID);
//					pst.setInt(1, bookID);
					
					ResultSet rst = pst.executeQuery();
					ResultSet rstCheck = pstcheck.executeQuery();
					
					int count = 0;
					while (rstCheck.next()) {
						count++;
					}
//					rst.previous();
					System.out.println(count);
					if (count == 0) {
						throw new Exception("Non-existing ID");
					} else {
						
						btnLoadDet.setEnabled(false);
						txtBookID.setEditable(false);
						
						txtFldBookName.setEditable(true);
						txtFldAuthor.setEditable(true);
						txtFldCategory.setEditable(true);
						txtFldPublisher.setEditable(true);
						txtFldLanguage.setEditable(true);
						txtFldISBN.setEditable(true);
						txtAreaSynopsis.setEditable(true);
						cmbxCondition.setEnabled(true);
						txtFldKW.setEditable(true);
						
						
						System.out.println(rst.getString("BookName"));
						txtFldBookName.setText(rst.getString(1));
						txtFldAuthor.setText(rst.getString(2));
						txtFldCategory.setText(rst.getString(6));
						txtFldPublisher.setText(rst.getString(3));
						txtFldLanguage.setText(rst.getString(4));
						txtFldISBN.setText(rst.getString(5));
						imgPath = rst.getString(8);
						cmbxCondition.setSelectedIndex(rst.getInt(9));
						txtFldKW.setText(rst.getString(10));
						txtAreaSynopsis.setText(rst.getString(7));
						
						finaIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(316, 450, Image.SCALE_SMOOTH));
						lblBookImg.setIcon(finaIcon);
						
						rstCheck.close();
						pstcheck.close();
						
						btnRemoveBook.setEnabled(true);
					}
					rst.close();
					pst.close();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
				
			}
		});
		
		btnRemoveBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bookID = txtBookID.getText();
				try {
					String issueQuery = "SELECT status from registeredbookinfo where bookid=?";
					PreparedStatement issueStatement = connection.prepareStatement(issueQuery);
					issueStatement.setString(1, bookID);
					ResultSet r = issueStatement.executeQuery();
					if (r.getInt(1)!=0) {
						r.close();
						throw new Exception("Issued Book");
					}else {
						r.close();
						String deleteQuery = "delete from RegisteredBookInfo where Bookid=?";
						/*Deletion of record indicated by User through input of BookID
						 * Note: Currently Issued and Lost Book Records cannot be removed*/
						PreparedStatement updateStatement = connection.prepareStatement(deleteQuery);
						
						int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this entry?", "Delete Book", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

						if (result == 0) {
							updateStatement.setString(1, bookID);
							updateStatement.execute();
							updateStatement.close();
							JOptionPane.showMessageDialog(null, "Book Entry successfully deleted");
						} else {
							updateStatement.close();
						}
					}
					btnRemoveBook.setEnabled(false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				
			}
		});
		panel.setLayout(gl_panel);
		frmRemoveBookEntryPage.getContentPane().setLayout(groupLayout);
	}
}
