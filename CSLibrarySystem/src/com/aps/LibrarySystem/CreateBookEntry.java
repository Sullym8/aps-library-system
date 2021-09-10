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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
public class CreateBookEntry {

	private JFrame frmCreateBookEntryPage;

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
					CreateBookEntry window = new CreateBookEntry();
					window.frmCreateBookEntryPage.setUndecorated(true);
					window.frmCreateBookEntryPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	Connection connection = null;
	String imgPath="NaN";
	int bookID;
	ImageIcon finaIcon = new ImageIcon(new ImageIcon(getClass().getResource("/Moleskine-Blank-Book-icon.png")).getImage().getScaledInstance(316, 375, Image.SCALE_SMOOTH));
	private JTextField txtFldBookName;
	private JTextField txtFldAuthor;
	private JTextField txtFldPublisher;
	private JTextField txtFldLanguage;
	private JTextField txtFldISBN;
	private JTextField txtFldCategory;
	private JTextField txtFldKW;
	private JComboBox cmbxCondition;
	private JTextField txtBookID;
	/**
	 * Create the application.
	 */
	public CreateBookEntry() {
		initialize();
		connection = SqliteConnector.dbConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreateBookEntryPage = new JFrame();
		frmCreateBookEntryPage.setUndecorated(true);
		frmCreateBookEntryPage.setResizable(false);
		frmCreateBookEntryPage.setTitle("Add New Book");
		frmCreateBookEntryPage.setBounds(0, 0, 770, 650);
		frmCreateBookEntryPage.setLocationRelativeTo(null);
		frmCreateBookEntryPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/baseline_person_add_white_48dp.png"));
		ImageIcon closeButton = new ImageIcon(getClass().getResource("/baseline_close_white_18dp.png"));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBorder(null);
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmCreateBookEntryPage.getContentPane());
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
		
		JButton btnAddImg = new JButton("Add Image");
		btnAddImg.setEnabled(false);
		
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.setEnabled(false);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBackground(SystemColor.controlDkShadow);
		btnNewButton_1.setForeground(SystemColor.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageBooks manageBooks = new ManageBooks();
				frmCreateBookEntryPage.setVisible(false);
				manageBooks.main(null);
				frmCreateBookEntryPage.dispose();
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
				.addComponent(btnAddImg, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
				.addComponent(btnAddBook, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(btnAddImg, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAddBook, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
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
		
		JLabel lblTitle = new JLabel("Book Title:");
		
		txtFldBookName = new JTextField();
		txtFldBookName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Author");
		
		txtFldAuthor = new JTextField();
		txtFldAuthor.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Book Publisher");
		
		txtFldPublisher = new JTextField();
		txtFldPublisher.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Language");
		
		txtFldLanguage = new JTextField();
		txtFldLanguage.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ISBN Number");
		
		txtFldISBN = new JTextField();
		txtFldISBN.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Category");
		
		txtFldCategory = new JTextField();
		txtFldCategory.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Key Words");
		
		txtFldKW = new JTextField();
		txtFldKW.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Condition");
		
		cmbxCondition = new JComboBox();
		cmbxCondition.setModel(new DefaultComboBoxModel(new String[] {"New", "Damaged"}));
		
		JLabel lblNewLabel_7 = new JLabel("Book ID");
		
		txtBookID = new JTextField();
		txtBookID.setEnabled(false);
		txtBookID.setEditable(false);
		txtBookID.setToolTipText("Recommended that it is the same as the ISBN Number");
		txtBookID.setColumns(10);
		
		JButton btnGenID = new JButton("Generate ID");
		btnGenID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String ID = txtFldISBN.getText();
					if (ID.equals("")||ID.length()!=13) {
						throw new Exception("ISBN-ID Generation Error");
					} else {
						txtBookID.setText(ID);
						btnAddImg.setEnabled(true);
						btnAddBook.setEnabled(true);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JLabel lblNewLabel_8 = new JLabel("Synopsis");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_9 = new JLabel("Add New Books");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
						.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(txtFldAuthor, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(txtFldPublisher, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(txtFldLanguage, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(txtFldISBN, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(txtFldCategory, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(cmbxCondition, 0, 370, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(txtBookID, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGenID, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
						.addComponent(txtFldKW, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(scrollPane)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFldBookName, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
						.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
						.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
						.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
						.addComponent(lblNewLabel_8, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_9)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFldBookName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTitle))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
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
					.addComponent(lblNewLabel_7)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtBookID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGenID))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_8)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		btnAddImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
					
					btnAddBook.setEnabled(true);
					
				} catch (IOException e) {
					imgPath="NaN";
					e.printStackTrace();
				}
				
				System.out.println(f.getName());
				System.out.println(f.getAbsolutePath());
			}
		});
		
		JTextArea txtAreaSynopsis = new JTextArea();
		txtAreaSynopsis.setLineWrap(true);
		txtAreaSynopsis.setWrapStyleWord(true);
		scrollPane.setViewportView(txtAreaSynopsis);
		
		btnAddBook.addActionListener(new ActionListener() {
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
					String queryString = "insert into RegisteredBookInfo (BookID,BookName,Author,Publisher,Language,"
							+ "ISBN,Category,Synopsis,Picture,Condition,KeyWord,Status) values (?,?,?,?,?,?,?,?,?,?,?,0)";
					/*Addition of new book record to RegisteredBookInfo following validation and verification of
					 * field provided by user and determination of Image Path*/
					PreparedStatement statement = connection.prepareStatement(queryString);
					
					
					String checkQueryString = "select BookID,BookName,ISBN from registeredbookinfo where bookid=?";
					PreparedStatement checkStatement = connection.prepareStatement(checkQueryString);
					
					if (bookTitle.equals("")||author.equals("")||publisher.equals("")||language.equals("")
							||ISBN.equals("")||category.equals("")||kW.equals("")||imgPath.equals("")||txtBookID.getText().equals("")) {
						throw new Exception("Missing Fields");
					} else if (bookID.length()!=13||ISBN.length()!=13) {
						throw new Exception("Incorrect ID Length (13 char)");
					} else {
						checkStatement.setString(1, bookID);
						ResultSet checkSet = checkStatement.executeQuery();
						int count = 0;
						while (checkSet.next()) {
							count++;
						}
						if (count != 0) {
							throw new Exception("Existing Book");
						} else {
							statement.setString(1, bookID);
							statement.setString(2, bookTitle);
							statement.setString(3, author);
							statement.setString(4, publisher);
							statement.setString(5, language);
							statement.setString(6, ISBN);
							statement.setString(7, category);
							statement.setString(8, synopsis);
							statement.setString(9, path);
							statement.setInt(10, condition);
							statement.setString(11, kW);
							
							statement.execute();
							JOptionPane.showMessageDialog(null, "Book entry added!");
							btnAddBook.setEnabled(false);
							btnAddImg.setEnabled(false);
						}
						
						checkSet.close();
					}
					statement.close();
					checkStatement.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				
			}
		});
		
		
		panel.setLayout(gl_panel);
		frmCreateBookEntryPage.getContentPane().setLayout(groupLayout);
		
		
		
	}
}
