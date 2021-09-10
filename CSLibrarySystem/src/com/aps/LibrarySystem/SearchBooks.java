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
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

@SuppressWarnings("unused")
public class SearchBooks {
	
	Connection connection = null;
	String imgPath;
	ImageIcon finaIcon = new ImageIcon(new ImageIcon(getClass().getResource("/Moleskine-Blank-Book-icon.png")).getImage().getScaledInstance(316, 375, Image.SCALE_SMOOTH));
	private JTextField txtFldSearch;
	private JComboBox cmbxFilter;
	private JTable table;
	private JTextField txtDate;
	
	private JFrame frmSearchBooks;

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
					SearchBooks window = new SearchBooks();
					window.frmSearchBooks.setUndecorated(true);
					window.frmSearchBooks.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public SearchBooks() {
		connection = SqliteConnector.dbConnection();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSearchBooks = new JFrame();
		frmSearchBooks.setUndecorated(true);
		frmSearchBooks.setResizable(false);
		frmSearchBooks.setTitle("Search Books");
		frmSearchBooks.setBounds(00, 0, 770, 605);
		frmSearchBooks.setLocationRelativeTo(null);
		frmSearchBooks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/baseline_person_add_white_48dp.png"));
		ImageIcon closeButton = new ImageIcon(getClass().getResource("/baseline_close_white_18dp.png"));
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmSearchBooks.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_9 = new JLabel("Book Search");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_2 = new JPanel();
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserDashboard uDB = new UserDashboard();
				frmSearchBooks.setVisible(false);
				uDB.main(null);
				frmSearchBooks.dispose();
			}
		});
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtDate.setText("DD-MM-YYYY");
		txtDate.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
						.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDate, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_9)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_4 = new JLabel((String) null);
		
		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/bookIco.png")).getImage().getScaledInstance(163, 163, Image.SCALE_SMOOTH)));
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(215)
							.addComponent(lblNewLabel_4))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblImg, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblImg, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(lblNewLabel_4)
					.addGap(37))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int row = table.getSelectedRow();
					String tableClickString = table.getModel().getValueAt(row, 0).toString();
//					sou
					String tableClickQueryString = "select Picture,ExpectedDate from RegisteredBookInfo where BookID=?";
					PreparedStatement pStatement = connection.prepareStatement(tableClickQueryString);
					
					pStatement.setString(1, tableClickString);
					ResultSet rSet = pStatement.executeQuery();
					
					if (rSet.next()) {
						String imgPath = rSet.getString(1);
						ImageIcon finaIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(163, 233, Image.SCALE_SMOOTH));
						lblImg.setIcon(finaIcon);
						String Date = rSet.getString(2);
						System.out.println(Date);
						
						if (Date==null){
							txtDate.setText("Available Now");
						} else if (Date.isEmpty()) {
							txtDate.setText("Available Now");
						} else if (Date.contains("-")) {
							txtDate.setText("Available on: "+ Date);
						}
						
						
						
						rSet.close();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		scrollPane.setViewportView(table);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNewLabel = new JLabel("Search Term (leave blank to view all):");
		
		JLabel lblNewLabel_1 = new JLabel("Search Filter");
		
		txtFldSearch = new JTextField();
		txtFldSearch.setColumns(10);
		
		cmbxFilter = new JComboBox();
		cmbxFilter.setModel(new DefaultComboBoxModel(new String[] {"Book Title", "Key Word", "Author", "Category"}));
		
		JButton btnLoad = new JButton("Search");
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
						.addComponent(txtFldSearch, GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
						.addComponent(cmbxFilter, 0, 706, Short.MAX_VALUE)
						.addComponent(btnLoad, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFldSearch, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cmbxFilter, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Search = txtFldSearch.getText();
				int Index = cmbxFilter.getSelectedIndex();
				String bookQueryString = null;
				
				System.out.println(Index);
				switch (Index) {
				case 0:
					bookQueryString = "select bookid,bookname,author,category from RegisteredBookInfo where bookname like ?";
					//Searches based on BookName provided by user
					break;
				case 1:
					bookQueryString = "select bookid,bookname,author,category from RegisteredBookInfo where keyword like ?";
					//Searches based on KeyWord provided by user
					break;
				case 2:
					bookQueryString = "select bookid,bookname,author,category from RegisteredBookInfo where author like ?";
					//Searches based on Author provided by user
					break;
				case 3:
					bookQueryString = "select bookid,bookname,author,category from RegisteredBookInfo where category like ?";
					//Searches based on Category provided by user
					break;
				default:
					break;
				}
				if (Search.isEmpty()) {
					bookQueryString = "select bookid,bookname,author,category from RegisteredBookInfo";
					/*Returns all records of book for browsing purposes, 
					 * only executed when search term is left blank*/
				}
				try {
					PreparedStatement statement = connection.prepareStatement(bookQueryString);
					if (!Search.isEmpty()) {
						/*Searches for inputed string 
						 * only when string is present*/
						statement.setString(1, "%"+Search+"%");
					}
					ResultSet resultSet = statement.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		panel.setLayout(gl_panel);
		frmSearchBooks.getContentPane().setLayout(groupLayout);
		btnLoad.doClick();
		
		
	}
}
