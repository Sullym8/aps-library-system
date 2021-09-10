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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.Choice;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

@SuppressWarnings("unused")
public class ExportTransactions {
	
	Connection connection = null;
	String imgPath;
	ImageIcon finaIcon = new ImageIcon(new ImageIcon(getClass().getResource("/Moleskine-Blank-Book-icon.png")).getImage().getScaledInstance(316, 375, Image.SCALE_SMOOTH));
	
	Calendar calendar = Calendar.getInstance();
	java.util.Date currentDate = calendar.getTime();
	
	private JTable table;
	private JFrame frmSearchBooks;
	private JTextField txtDate;

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
					ExportTransactions window = new ExportTransactions();
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
	public ExportTransactions() {
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
		
		JLabel lblNewLabel_9 = new JLabel("Export Transactions");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_2 = new JPanel();
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserDashboard uDB = new UserDashboard();
				frmSearchBooks.setVisible(false);
				uDB.main(null);
				frmSearchBooks.dispose();
			}
		});
		
		JButton btnExport = new JButton("Export to .txt");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valueAt=null;
				String path = new String(System.getProperty("user.home")+"\\Documents\\LMS\\Exported.txt");
				File txtFile = new File(path);
				try {
					FileWriter txtWriter = new FileWriter(txtFile);
					BufferedWriter txtBufferedWriter = new BufferedWriter(txtWriter);
					int Rows = table.getRowCount();
					int Columns = table.getColumnCount();
					txtBufferedWriter.write("--------------------------------------------------------------------");
					txtBufferedWriter.newLine();
					txtBufferedWriter.write("  Book	Client	IssueDate	ReturnDate	Condition");
					txtBufferedWriter.newLine();
					txtBufferedWriter.write("--------------------------------------------------------------------");
					txtBufferedWriter.newLine();
					for (int i = 0; i < Rows; i++) {
						for (int j = 0; j < Columns; j++) {
							if (table.getValueAt(i, j)==null) {
								valueAt = "";
							} else {
								valueAt = table.getValueAt(i, j).toString();
							}
							System.out.println(valueAt);
							if (j==Columns-1) {
								if (valueAt=="") {
									txtBufferedWriter.write("");
								}else if (Integer.parseInt(valueAt)==0) {
									txtBufferedWriter.write("New");
								} else if (Integer.parseInt(valueAt)==1) {
									txtBufferedWriter.write("Damaged");
								}else if (Integer.parseInt(valueAt)==2) {
									txtBufferedWriter.write("Lost"); 
								}
							}else {
								txtBufferedWriter.write(valueAt+"	");
							}	
						}
						txtBufferedWriter.newLine();
					}
					txtBufferedWriter.newLine();
					txtBufferedWriter.write(String.valueOf(currentDate));
					txtDate.setText(String.valueOf(currentDate));
					JOptionPane.showMessageDialog(null, "Successfully exported to: " + path);
					txtBufferedWriter.close();
					txtWriter.close();
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "A critical error occured", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnParse = new JButton("Parse Exported Transactions");
		
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
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnParse, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_9)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
								.addComponent(btnExport, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(btnParse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_4 = new JLabel((String) null);
		
		JLabel lblNewLabel = new JLabel("Export Date:");
		
		txtDate = new JTextField();
		txtDate.setColumns(20);
		txtDate.setText(String.valueOf(currentDate));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addComponent(lblNewLabel_4)
					.addContainerGap(515, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_4)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(37))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		panel_2.setLayout(gl_panel_2);
		
		JButton btnLoad = new JButton("Load Current Transactions from DB");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				String transactionQuery= "select * from UserBookHistory";
				/*Selects all historical transactions from UserBookHistory*/
				PreparedStatement statement = connection.prepareStatement(transactionQuery);
				
				ResultSet resultSet = statement.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(resultSet));
				
				resultSet.close();
				statement.close();
				btnExport.setEnabled(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		});
		
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnExport.setEnabled(false);
				
				String path = new String(System.getProperty("user.home")+"\\Documents\\LMS\\Exported.txt");
				File txtFile = new File(path);
				
				try {
					BufferedReader b = new BufferedReader(new FileReader(txtFile));
					b.readLine();
					String firstLine = b.readLine().trim();
					b.readLine();
					String[] columns = firstLine.split("	");
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					m.setRowCount(0);
					m.setColumnIdentifiers(columns);
					
					Object[] tableLineData = b.lines().toArray();
					
					for (int i = 0; i < tableLineData.length; i++) {
						String line = tableLineData[i].toString().trim();
						if(line != null && !line.isEmpty() && !line.contains(":")) {
					           String[] dataRow = line.split("	");
					           m.addRow(dataRow);
					     }else if (line.contains(":")) {
							txtDate.setText(line);
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLoad, GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(135, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		
		panel.setLayout(gl_panel);
		frmSearchBooks.getContentPane().setLayout(groupLayout);
		btnLoad.doClick();
		
		
	}
}
