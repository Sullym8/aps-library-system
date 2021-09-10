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
public class ModifyClientEntry {
	
	Connection connection = null;
	String imgPath = "NaN";
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
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyClientEntry window = new ModifyClientEntry();
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
	public ModifyClientEntry() {
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
		frmCreateBookEntryPage.setTitle("Modify Client Entry");
		frmCreateBookEntryPage.setBounds(0, 0, 770, 552);
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		
		JButton btnUpdateImage = new JButton("Update Image");
		btnUpdateImage.setEnabled(false);
		JButton btnUpdateClient = new JButton("Update Client");
		btnUpdateClient.setEnabled(false);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(SystemColor.controlDkShadow);
		btnBack.setForeground(SystemColor.WHITE);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageUsers manageUsers = new ManageUsers();
				frmCreateBookEntryPage.setVisible(false);
				manageUsers.main(null);
				frmCreateBookEntryPage.dispose();
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(btnUpdateClient, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnUpdateImage, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUpdateImage, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(btnUpdateClient, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		panel_2.setLayout(gl_panel_2);
		
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
		cmbxClientType.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (cmbxClientType.getSelectedIndex()==1) {
					spnClientGrade.setValue(0);
					spnClientGrade.setEnabled(false);
				} else {
					spnClientGrade.setEnabled(true);
				}
			}
			public void focusGained(FocusEvent e) {
				if (cmbxClientType.getSelectedIndex()==1) {
					spnClientGrade.setValue(0);
					spnClientGrade.setEnabled(false);
				} else {
					spnClientGrade.setEnabled(true);
				}
			}
		});
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
		
		JLabel lblNewLabel_9 = new JLabel("Update Client");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnLoadDetails = new JButton("Load Details");
		
		txtFldClientName = new JTextField();
		txtFldClientName.setEnabled(false);
		txtFldClientName.setColumns(10);
		
		JLabel lblClientName = new JLabel("Client Name");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblClientName, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(txtFldClientID, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoadDetails, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtFldEmail, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(cmbxClientType, 0, 374, Short.MAX_VALUE)
						.addComponent(spnClientGrade, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(scrollPane)
						.addComponent(txtFldClientName, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblClientName)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JTextArea txtClientAdd = new JTextArea();
		txtClientAdd.setEnabled(false);
		txtClientAdd.setWrapStyleWord(true);
		txtClientAdd.setLineWrap(true);
		scrollPane.setViewportView(txtClientAdd);
		
		btnUpdateImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				String clientPathString = ("C:\\Users\\Samar Khan\\Desktop\\CSIA\\ProjFiles\\ClientImg\\" + txtFldClientID.getText() + ".jpg");
				String clientPathString = (System.getProperty("user.home")+"\\Documents\\LMS\\ClientImg\\"+ txtFldClientID.getText() + ".jpg");
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				File outputImgFile = new File(clientPathString);
				imgPath = clientPathString;
				try {
					Files.copy(f.toPath(), outputImgFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					finaIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(316, 375, Image.SCALE_SMOOTH));
					lblClientImg.setIcon(finaIcon);
					
					btnUpdateClient.setEnabled(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
//				System.out.println(f.getName());
//				System.out.println(f.getAbsolutePath());
				
			}
		});
		
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
					PreparedStatement pst = connection.prepareStatement(queryString);
					pstcheck.setString(1, ClientID);
					pst.setString(1, ClientID);
					
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
						txtFldClientName.setEnabled(true);
						cmbxClientType.setEnabled(true);
						spnClientGrade.setEnabled(true);
						txtClientAdd.setEnabled(true);
						txtFldEmail.setEnabled(true);
						btnUpdateClient.setEnabled(true);
						btnUpdateImage.setEnabled(true);
						
						txtFldClientName.setText(rst.getString("FullName"));
						cmbxClientType.setSelectedIndex(rst.getInt("ClientType"));
						if (rst.getInt("ClientType")==0) {
							spnClientGrade.setValue(rst.getInt("Grade"));
						}
						txtClientAdd.setText(rst.getString("Address"));
						txtFldEmail.setText(rst.getString("Email"));
						
						imgPath = rst.getString("picturePath");
						if (!imgPath.equals("NaN")) {
							finaIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(316, 375, Image.SCALE_SMOOTH));
							lblClientImg.setIcon(finaIcon);
						}
						
						
					}
					
					
					
					rst.close();
					rstCheck.close();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				
			}
		});
		
		btnUpdateClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String clientName = txtFldClientName.getText();
				int clientType = cmbxClientType.getSelectedIndex();
				int clientGrade = (int) spnClientGrade.getValue();
				String clientAddress = txtClientAdd.getText();
				String clientEmail = txtFldEmail.getText();
				String path = imgPath;
				String clientID = txtFldClientID.getText();
				
				try {
					String updateQuery = "update RegisteredClientInfo set Fullname=?,"
							+ "ClientType=?,"
							+ "Grade=?,"
							+ "Address=?,"
							+ "Email=?,"
							+ "PicturePath=?"
							+ "where clientID=?";
					PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
					
					if (clientName.equals("")||clientAddress.equals("")
							||clientEmail.equals("")||imgPath.equals("")) {
						throw new Exception("Missing Fields");
					} else {
						updateStatement.setString(1, clientName);
						updateStatement.setInt(2, clientType);
						updateStatement.setInt(3, clientGrade);
						updateStatement.setString(4, clientAddress);
						updateStatement.setString(5, clientEmail);
						updateStatement.setString(6, path);
						updateStatement.setString(7, clientID);
						
						updateStatement.execute();
						JOptionPane.showMessageDialog(null, "Client Entry Updated!");
					}
					
					updateStatement.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
				
			}
		});
		
		
		panel.setLayout(gl_panel);
		frmCreateBookEntryPage.getContentPane().setLayout(groupLayout);
		
		
		
	}
}
