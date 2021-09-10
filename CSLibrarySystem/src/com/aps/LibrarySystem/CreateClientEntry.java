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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("unused")
public class CreateClientEntry {

	private JFrame frmCreateBookEntryPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("");
//			select the Look and Feel
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateClientEntry window = new CreateClientEntry();
					window.frmCreateBookEntryPage.setUndecorated(true);
					window.frmCreateBookEntryPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	String imgPath = "NaN";
	ImageIcon finaIcon = new ImageIcon(new ImageIcon(getClass().getResource("/adminFaivcon.png")).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
	private JTextField txtFldClientName;
	private JComboBox cmbxClientType;
	private JSpinner spnClientGrade;
	private JTextField txtFldEmail;
	private JTextField txtClientID;
	/**
	 * Create the application.
	 */
	public CreateClientEntry() {
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
		frmCreateBookEntryPage.setTitle("Create New Client");
		frmCreateBookEntryPage.setBounds(0, 0, 791, 547);
		frmCreateBookEntryPage.setLocationRelativeTo(null);
		frmCreateBookEntryPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/baseline_person_add_white_48dp.png"));
		ImageIcon closeButton = new ImageIcon(getClass().getResource("/baseline_close_white_18dp.png"));
				
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_3 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmCreateBookEntryPage.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(51))
		);
		
		JButton btnAddImage = new JButton("Add Image");
		btnAddImage.setEnabled(false);
		
		JButton btnAddClient = new JButton("Add Client");
		btnAddClient.setEnabled(false);
		
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
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(btnAddImage, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnAddClient, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddImage, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddClient, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
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
		
		JLabel lblTitle = new JLabel("Client Name:");
		
		txtFldClientName = new JTextField();
		txtFldClientName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Client Type:");
		
		cmbxClientType = new JComboBox();
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
		spnClientGrade.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		
		txtFldEmail = new JTextField();
		txtFldEmail.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("School ID");
		
		txtClientID = new JTextField();
		txtClientID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!txtClientID.getText().isEmpty()) {
					btnAddImage.setEnabled(true);
					btnAddClient.setEnabled(true);
				}
			}
		});
		
		
		txtClientID.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Add New Client");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(scrollPane)
						.addComponent(cmbxClientType, 0, 370, Short.MAX_VALUE)
						.addComponent(spnClientGrade, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFldClientName, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
						.addComponent(txtFldEmail, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(txtClientID, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_9)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFldClientName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTitle))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cmbxClientType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spnClientGrade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblNewLabel_7)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtClientID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JTextArea txtClientAdd = new JTextArea();
		txtClientAdd.setWrapStyleWord(true);
		txtClientAdd.setLineWrap(true);
		scrollPane.setViewportView(txtClientAdd);
		
		panel.setLayout(gl_panel);
		frmCreateBookEntryPage.getContentPane().setLayout(groupLayout);
		
		btnAddImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				String bookPathString = ("C:\\Users\\Samar Khan\\Desktop\\CSIA\\ProjFiles\\ClientImg\\" + txtClientID.getText() +".jpg");
				String bookPathString = (System.getProperty("user.home")+"\\Documents\\LMS\\ClientImg\\"+ txtClientID.getText() + ".jpg");
//				String bookPathString = (jarDirFinder.jarPath()+"ClientImg/" + txtClientID.getText() +".jpg");
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				File outputImgFile = new File(bookPathString);
				imgPath = bookPathString;
				try {
					Files.copy(f.toPath(), outputImgFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					finaIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
					lblClientImg.setIcon(finaIcon);
//					btnAddClient.setEnabled(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.println(f.getName());
				System.out.println(f.getAbsolutePath());
			}
		});
		
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = txtFldClientName.getText();
				int type = cmbxClientType.getSelectedIndex();
				int grade = (int) spnClientGrade.getValue();
				String address = txtClientAdd.getText();
				String email = txtFldEmail.getText();
				String clientID = txtClientID.getText();
				String path = imgPath;
				
				try {
					String queryString = "insert into RegisteredClientInfo (ClientID,FullName,ClientType,Grade,Address,email,picturePath,BooksDue,TotalFInes) values (?,?,?,?,?,?,?,0,0)";
					PreparedStatement statement = connection.prepareStatement(queryString);
					String checkQueryString = "select fullname,email from RegisteredClientInfo where clientid=? or fullname=? or email=?";
					PreparedStatement checkStatement = connection.prepareStatement(checkQueryString);
					
					if (name.equals("")||address.equals("")||email.equals("")||path.equals("")
							||txtClientID.getText().equals("")) {
						throw new Exception("Missing Fields");
					} else if (txtClientID.getText().length()!=5) {
						throw new Exception("Incorrect ID Length (5 char)");
					} else {
						checkStatement.setString(1, clientID);
						checkStatement.setString(2, name);
						checkStatement.setString(3, email);
						ResultSet checkSet = checkStatement.executeQuery();
						int count = 0;
						while (checkSet.next()) {
							count++;
						}
						if (count != 0) {
							throw new Exception("Existing Client");
						} else {
							statement.setString(1, clientID);
							statement.setString(2, name);
							statement.setInt(3, type);
							if (type == 0) {
								statement.setInt(4, grade);
							} else {
								statement.setInt(4, 0);
							}
							statement.setString(5, address);
							statement.setString(6, email);
							statement.setString(7, path);
							
							statement.execute();
							
							JOptionPane.showMessageDialog(null, "Client entry added!");
							
						}
						
						checkSet.close();
					}
					statement.close();
					checkStatement.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				
			}
		});
		
	}
}
