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
public class ModifyBookEntry {
	
	Connection connection = null;
	String imgPath = "NaN";
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
	private JFrame frmModifyBookEntryPage;

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
					ModifyBookEntry window = new ModifyBookEntry();
					window.frmModifyBookEntryPage.setUndecorated(true);
					window.frmModifyBookEntryPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public ModifyBookEntry() {
		connection = SqliteConnector.dbConnection();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModifyBookEntryPage = new JFrame();
		frmModifyBookEntryPage.setUndecorated(true);
		frmModifyBookEntryPage.setResizable(false);
		frmModifyBookEntryPage.setTitle("Modify Book Entry");
		frmModifyBookEntryPage.setBounds(0, 0, 770, 650);
		frmModifyBookEntryPage.setLocationRelativeTo(null);
		frmModifyBookEntryPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/baseline_person_add_white_48dp.png"));
		ImageIcon closeButton = new ImageIcon(getClass().getResource("/baseline_close_white_18dp.png"));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBorder(null);
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmModifyBookEntryPage.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		JButton btnUpdateImg = new JButton("Update Image");
		btnUpdateImg.setEnabled(false);
		
		
		JButton btnUpdateBook = new JButton("Update Book");
		btnUpdateBook.setEnabled(false);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBackground(SystemColor.controlDkShadow);
		btnNewButton_1.setForeground(SystemColor.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageBooks manageBooks = new ManageBooks();
				frmModifyBookEntryPage.setVisible(false);
				manageBooks.main(null);
				frmModifyBookEntryPage.dispose();
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
				.addComponent(btnUpdateImg, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
				.addComponent(btnUpdateBook, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(btnUpdateImg, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnUpdateBook, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
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
					.addComponent(lblBookImg, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBookImg, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
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
		
		JLabel lblNewLabel_9 = new JLabel("Update Book");
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
		
		btnUpdateImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ImageIcon finaIcon = new ImageIcon(new ImageIcon(getClass().getResource("/Moleskine-Blank-Book-icon.png")).getImage().getScaledInstance(316, 375, Image.SCALE_SMOOTH));
				
//				String bookPathString = ("C:\\Users\\Samar Khan\\Desktop\\CSIA\\ProjFiles\\BookImg\\" + txtBookID.getText() +".jpg");
				String bookPathString = (System.getProperty("user.home")+"\\Documents\\LMS\\BookImg\\"+ txtBookID.getText() + ".jpg");
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				File outputImgFile = new File(bookPathString);
				imgPath = bookPathString;
				try {
					Files.copy(f.toPath(), outputImgFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					finaIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(316, 375, Image.SCALE_SMOOTH));
					lblBookImg.setIcon(finaIcon);
				} catch (Exception e) {
					imgPath="NaN";
					e.printStackTrace();
				}
				
				System.out.println(f.getName());
				System.out.println(f.getAbsolutePath());
			}
		});
		
		
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
					if (bookID.equals("")) {
						throw new Exception("Missing Fields");
					}else if (bookID.length()!=13) {
						throw new Exception("Incorrect ID length (13 char)");
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
						
						finaIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(316, 375, Image.SCALE_SMOOTH));
						lblBookImg.setIcon(finaIcon);
						
						rstCheck.close();
						pstcheck.close();
						
						btnUpdateImg.setEnabled(true);
						btnUpdateBook.setEnabled(true);
					}
					rst.close();
					pst.close();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				
			}
		});
		
		btnUpdateBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bookTitle = txtFldBookName.getText();
				String author = txtFldAuthor.getText();
				String publisher = txtFldPublisher.getText();
				String language = txtFldLanguage.getText();
				String ISBN = txtFldISBN.getText();
				String category = txtFldCategory.getText();
				String kW = txtFldKW.getText();
				int condition = cmbxCondition.getSelectedIndex();
				String bookID = txtBookID.getText();
				String synopsis = txtAreaSynopsis.getText();
				String path = imgPath;
				
				try {
					String updateQuery = "update RegisteredBookInfo set BookName=?,"
							+ "Author=?,"
							+ "Publisher=?,"
							+ "Language=?,"
							+ "ISBN=?,"
							+ "Category=?,"
							+ "Synopsis=?,"
							+ "Picture=?,"
							+ "Condition=?,"
							+ "KeyWord=?"
							+ "where bookid=?";
					/*Update all field of record regardless of changes made or not by user
					 * Note: Any data that is to be edited must go through existing validation
					 * and verification checks before execution of statement*/
					PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
					
					
					
					if (bookTitle.equals("")||author.equals("")||publisher.equals("")||language.equals("")
							||ISBN.equals("")||category.equals("")||kW.equals("")||imgPath.equals("")||txtFldBookName.getText().equals("")) {
						throw new Exception("Missing Fields");
					} else {
							updateStatement.setString(1, bookTitle);
							updateStatement.setString(2, author);
							updateStatement.setString(3, publisher);
							updateStatement.setString(4, language);
							updateStatement.setString(5, ISBN);
							updateStatement.setString(6, category);
							updateStatement.setString(7, synopsis);
							updateStatement.setString(8, path);
							updateStatement.setInt(9, condition);
							updateStatement.setString(10, kW);
							updateStatement.setString(11, bookID);
							
							updateStatement.execute();
							JOptionPane.showMessageDialog(null, "Book entry updated!");
							btnUpdateImg.setEnabled(false);
							btnUpdateBook.setEnabled(false);
						}
						
//					checkSet.close();
					updateStatement.close();
//					checkStatement.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error: " + e);
					e.printStackTrace();
				}
				
			}
		});
		
		panel.setLayout(gl_panel);
		frmModifyBookEntryPage.getContentPane().setLayout(groupLayout);
		
		
		
	}
}
