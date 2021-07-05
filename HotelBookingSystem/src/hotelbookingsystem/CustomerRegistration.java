package hotelbookingsystem;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.Window.Type;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerRegistration {

	private JFrame frame;
	private JTextField txtFullName;
	private JTextField txtCountry;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JTextField txtphone;
	private JPasswordField txtPassword;
	private ButtonGroup customerGenGp;
	private JTextField txtCardDetails;
	
	private String name;
	private String address;
	private String customerType;
	private String phone;
	private String gender;
	private String email;
	private String password;
	private String cardDetail;

//	Launch the application.
//	public static void main(String[] args) {
//		
//		CustomerRegistration window = new CustomerRegistration();
//			
//				}
	
	//default constructor
	public CustomerRegistration() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 756, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Customer Registration Form");
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
//		   Full screen
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);

		// To set the center
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registrations Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 44, 735, 315);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCustomerRegistrationsForm = new JLabel("Full Name");
		lblCustomerRegistrationsForm.setBounds(20, 10, 135, 61);
		panel.add(lblCustomerRegistrationsForm);
		lblCustomerRegistrationsForm.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblType.setBounds(20, 70, 135, 61);
		panel.add(lblType);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCountry.setBounds(20, 130, 135, 61);
		panel.add(lblCountry);
		
		JLabel lblEmailId = new JLabel("Email Id");
		lblEmailId.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEmailId.setBounds(20, 190, 135, 61);
		panel.add(lblEmailId);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblGender.setBounds(378, 10, 135, 61);
		panel.add(lblGender);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAddress.setBounds(378, 70, 135, 61);
		panel.add(lblAddress);
		
		JLabel lblPhoneNo = new JLabel("Phone No");
		lblPhoneNo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPhoneNo.setBounds(378, 130, 135, 61);
		panel.add(lblPhoneNo);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPassword.setBounds(378, 190, 135, 61);
		panel.add(lblPassword);
		
		txtFullName = new JTextField();
		txtFullName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtFullName.setBounds(121, 27, 222, 27);
		panel.add(txtFullName);
		txtFullName.setColumns(10);
		
		JComboBox comType = new JComboBox();
		comType.setModel(new DefaultComboBoxModel(new String[] {"Normal Customer", "Corporate Customer"}));
		comType.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		comType.setBounds(121, 87, 222, 32);
		panel.add(comType);
		
		txtCountry = new JTextField();
		txtCountry.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtCountry.setColumns(10);
		txtCountry.setBounds(121, 152, 222, 27);
		panel.add(txtCountry);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtEmail.setColumns(10);
		txtEmail.setBounds(121, 212, 222, 27);
		panel.add(txtEmail);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtAddress.setColumns(10);
		txtAddress.setBounds(483, 87, 222, 27);
		panel.add(txtAddress);
		
		txtphone = new JTextField();
		txtphone.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtphone.setColumns(10);
		txtphone.setBounds(483, 147, 222, 27);
		panel.add(txtphone);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtPassword.setBounds(483, 207, 222, 31);
		panel.add(txtPassword);
		
		JRadioButton rdbtnmale = new JRadioButton("Male");
		rdbtnmale.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		rdbtnmale.setSelected(true);
		rdbtnmale.setBounds(483, 23, 103, 39);
		panel.add(rdbtnmale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		rdbtnFemale.setSelected(false);
		rdbtnFemale.setBounds(602, 23, 103, 39);
		panel.add(rdbtnFemale);
		
		customerGenGp = new ButtonGroup();
        customerGenGp.add(rdbtnmale);
        customerGenGp.add(rdbtnFemale);
        
        JButton btnSave = new JButton("Submit");
        btnSave.setBackground(new Color(0, 102, 153));
        btnSave.setHorizontalAlignment(SwingConstants.LEFT);
        btnSave.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnSave.setBounds(500, 261, 100, 36);
        panel.add(btnSave);
        
        // button action listener
        btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// convert to tezt
				name = txtFullName.getText();
				address = txtAddress.getText();
				customerType = comType.getSelectedItem().toString();
				phone = txtphone.getText();
				gender = customerGenGp.getSelection().getActionCommand();
				email = txtEmail.getText();
				password = txtPassword.getText();
				cardDetail = txtCardDetails.getText();
				
				// Try- catch block
				try {
					// To create connection object
					Connection con = DatabaseConnectionUtil.ConnectDB();
					
					// insert query to insert data in customer
					String query = "insert into Customer(Fullname, Address, CustomerType, PhoneNumber, Gender, Email, Password, CardNumber) values"
							+ "(?,?,?,?,?,?,?,?)";
					
					//Create PreparedStatement
					PreparedStatement pst = null;
					 pst = con.prepareStatement(query);
					
					// Regex to check email validation
					String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
					
					// Regex to check password validation
					String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,15}$";
					
					// select query to display customer email
					String queryEmail = "select * from Customer";
					PreparedStatement pst2 = con.prepareStatement(queryEmail);
					ResultSet rst = pst2.executeQuery();
					
					String dataEmail = "";
					
					while(rst.next()) {
						dataEmail = rst.getString(7);
						
					}
					
					// Check some validation
					if(name.length()==0 || address.length()==0 || phone.length()==0 ||
							email.length()==0 || password.length()==0) {
						
						JOptionPane.showMessageDialog(null, "All fields required to fill");
					}
					else if(email.equals(dataEmail)) {
						JOptionPane.showMessageDialog(null, "Email already exist");
					}
					else if (!email.matches(emailRegex)) {
						JOptionPane.showMessageDialog(null, "Email not valid");
					}
					else if (!password.matches(passwordRegex)) {
						JOptionPane.showMessageDialog(null, "Password must contain at least 5 character, one uppercase, one lowercase, one digit, one special character");
					}
					else {
						
						pst.setString(1, name);
						pst.setString(2, address);
						pst.setString(3, customerType);
					    pst.setString(4, phone);
					    
					    String gender1 = null;
					   
					   // Checking condition to store gender value in database
					   if (rdbtnmale.isSelected()) {
						     gender1 = "Male"; 
						} else {
							 gender1 = "Female"; 
							 }
						
						pst.setString(5, gender1);
						pst.setString(6, email);
						pst.setString(7, password);
						pst.setString(8, cardDetail);
						
						if (pst.executeUpdate() >0) {
							
							JOptionPane.showMessageDialog(null, "Registration Successfull");
							new LoginForm();
							frame.dispose();
							
						    String def = "";
							
							// Clearing fields
						    txtFullName.setText(def);
						    txtAddress.setText(def);
						    comType.setSelectedIndex(-1);
						    txtphone.setText(def);
						    txtEmail.setText(def);
						    txtPassword.setText(def);
						    txtCardDetails.setText(def);
				            

						}
						
						
					}
					
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
				
				
			}
        	
        });
        
        
        JButton btnDelete = new JButton("Exit");
        btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnDelete.setBackground(new Color(165, 42, 42));
        btnDelete.setBounds(622, 261, 92, 36);
        panel.add(btnDelete);
        
        btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginForm login = new LoginForm();
				frame.setVisible(false);
				
			}
        	
        });
        
   
        txtCardDetails = new JTextField();
        txtCardDetails.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        txtCardDetails.setColumns(10);
        txtCardDetails.setBounds(121, 266, 222, 27);
        panel.add(txtCardDetails);
        
        JLabel lblCreditCardDetails = new JLabel("Card Details");
        lblCreditCardDetails.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblCreditCardDetails.setBounds(20, 249, 135, 61);
        panel.add(lblCreditCardDetails);
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(255, 140, 0));
        panel_1.setBounds(0, 0, 790, 34);
        frame.getContentPane().add(panel_1);
        
        JLabel lblLoginForm = new JLabel("Customer Registrations");
        lblLoginForm.setForeground(new Color(255, 255, 240));
        lblLoginForm.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblLoginForm.setBounds(10, 0, 265, 34);
        panel_1.add(lblLoginForm);
        
        JLabel lblexit = new JLabel("X");
        // Close the form while click the x label.
        lblexit.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.setVisible(false);
        	}
        });
        lblexit.setForeground(new Color(255, 255, 240));
        lblexit.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblexit.setBounds(730, 0, 30, 34);
        panel_1.add(lblexit);
        
        JLabel lblMin = new JLabel("-");
        // Minimize the form while click the - label.
        lblMin.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.setState(Frame.ICONIFIED);
        	}
        });
        
        lblMin.setForeground(new Color(255, 255, 240));
        lblMin.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblMin.setBounds(700, 4, 20, 21);
        panel_1.add(lblMin);
		
		
		
		frame.setVisible(true);
	}

}
