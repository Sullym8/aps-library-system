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
public class RemoveClientEntry {
	
	Connection connection = null;
	String imgPath;
	ImageIcon finaIcon = new ImageIcon(new ImageIcon(getClass().getResource("/adminFaivcon.png")).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
	private JTextField txtFldClientID;
	private JComboBox cmbxClientType;
	private JSpinner spnClientGrade;
	private JTextField txtFldEmail;
	private JTextField txtFldClientName;
	
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
					RemoveClientEntry window = new RemoveClientEntry();
					window.frmCreateBookEntryPage.setUndecorated(true);
					window.frmCreateBookEntryPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public RemoveClientEntry() {
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
		frmCreateBookEntryPage.setTitle("Remove Client");
		frmCreateBookEntryPage.setBounds(0, 0, 770, 465);
		frmCreateBookEntryPage.setLocationRelativeTo(null);
		frmCreateBookEntryPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/baseline_person_add_white_48dp.png"));
		ImageIcon closeButton = new ImageIcon(getClass().getResource("/baseline_close_white_18dp.png"));
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmCreateBookEntryPage.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		
		JButton btnRemoveClient = new JButton("Remove Client");
		btnRemoveClient.setEnabled(false);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(SystemColor.controlDkShadow);
		btnBack.setForeground(SystemColor.WHITE);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addComponent(btnRemoveClient, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
				.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(btnRemoveClient, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageUsers manageUsers = new ManageUsers();
				frmCreateBookEntryPage.setVisible(false);
				manageUsers.main(null);
				frmCreateBookEntryPage.dispose();
			}
		});
		
		btnRemoveClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String clientID = txtFldClientID.getText();

				try {
					String issueQuery = "SELECT booksdue,totalfines from registeredclientinfo where clientid=?";
					PreparedStatement issueStatement = connection.prepareStatement(issueQuery);
					issueStatement.setString(1, clientID);
					ResultSet r = issueStatement.executeQuery();
					if (r.getInt(1)!=0 || r.getInt(2)!=0) {
						System.out.println("lmao");
						r.close();
						throw new Exception("Issued Books/Due Fines");
					}else {
						r.close();
						String deleteQuery = "delete from RegisteredClientInfo where Clientid=?";
						PreparedStatement updateStatement = connection.prepareStatement(deleteQuery);
						int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this entry?", "Delete Book", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);					
						if (result == 0) {
							updateStatement.setString(1, clientID);
							updateStatement.execute();
							updateStatement.close();
							JOptionPane.showMessageDialog(null, "Client Entry successfully deleted");
						} else {
							updateStatement.close();
						}
					}
					btnRemoveClient.setEnabled(false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				
			}
		});
		
		JLabel lblClientImg = new JLabel("");
		lblClientImg.setHorizontalAlignment(SwingConstants.CENTER);

		lblClientImg.setIcon(finaIcon);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClientImg, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClientImg, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblTitle = new JLabel("Client ID");
		
		txtFldClientID = new JTextField();
		txtFldClientID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Client Type:");
		
		cmbxClientType = new JComboBox();
		cmbxClientType.setEnabled(false);
		cmbxClientType.setModel(new DefaultComboBoxModel(new String[] {"Student", "Teacher"}));
		
		JLabel lblNewLabel_2 = new JLabel("Grade");
		
		spnClientGrade = new JSpinner();
		spnClientGrade.setEnabled(false);
		spnClientGrade.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		
		txtFldEmail = new JTextField();
		txtFldEmail.setEnabled(false);
		txtFldEmail.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Remove Client");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnLoadDetails = new JButton("Load Details");
		
		txtFldClientName = new JTextField();
		txtFldClientName.setEnabled(false);
		txtFldClientName.setColumns(10);
		
		JLabel lblClientName = new JLabel("Client Name");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(txtFldEmail, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(lblClientName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(txtFldClientID, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoadDetails))
						.addComponent(cmbxClientType, Alignment.TRAILING, 0, 372, Short.MAX_VALUE)
						.addComponent(spnClientGrade, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(txtFldClientName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(lblTitle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(lblNewLabel_4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(lblNewLabel_9, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
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
						.addComponent(txtFldClientID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLoadDetails))
					.addGap(4)
					.addComponent(lblClientName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtFldClientName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cmbxClientType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spnClientGrade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		JTextArea txtClientAdd = new JTextArea();
		txtClientAdd.setEnabled(false);
		txtClientAdd.setWrapStyleWord(true);
		txtClientAdd.setLineWrap(true);
		scrollPane.setViewportView(txtClientAdd);
		
		btnLoadDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ClientID = txtFldClientID.getText();
				
				try {
					
					if (ClientID.length()!=5) {
						throw new Exception("Incorrect ID Length (5 char)");
					}
					String queryString = "select ClientID,FullName,ClientType,Grade,Address,email,picturePath from RegisteredClientInfo where ClientID=?";
					String checkString = "select * from registeredclientinfo where clientid=?";
					PreparedStatement pstcheck = connection.prepareStatement(checkString);
//					select BookName,Author,Publisher,Language,ISBN,Category,Synopsis,Picture,Condition,KeyWord from RegisteredBookInfo where BookID=?
					PreparedStatement pst = connection.prepareStatement(queryString);
					pstcheck.setString(1, ClientID);
					pst.setString(1, ClientID);
//					pst.setInt(1, bookID);
					
					ResultSet rst = pst.executeQuery();
					ResultSet rstCheck = pstcheck.executeQuery();
					
					int count = 0;
					while (rstCheck.next()) {
						count++;
					}
					
					if (count == 0) {
						throw new Exception("Non exisitng ID");
					} else {
						btnLoadDetails.setEnabled(false);
						txtFldClientID.setEditable(false);
						
						txtFldClientName.setText(rst.getString("FullName"));
						cmbxClientType.setSelectedIndex(rst.getInt("ClientType"));
						if (rst.getInt("ClientType")==0) {
							spnClientGrade.setValue(rst.getInt("Grade"));
						}
						txtClientAdd.setText(rst.getString("Address"));
						txtFldEmail.setText(rst.getString("Email"));
						
						imgPath = rst.getString("picturePath");
						finaIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
						lblClientImg.setIcon(finaIcon);
						
						btnRemoveClient.setEnabled(true);
						
					}
					rst.close();
					rstCheck.close();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		panel.setLayout(gl_panel);
		frmCreateBookEntryPage.getContentPane().setLayout(groupLayout);
		
		
		
	}
}
