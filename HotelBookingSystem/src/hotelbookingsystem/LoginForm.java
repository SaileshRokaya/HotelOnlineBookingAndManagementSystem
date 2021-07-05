package hotelbookingsystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm {

	private JFrame frame;
	private JTextField txtEmail;
	private JPasswordField txtPassword;


	// Default constructor
	public LoginForm() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 466, 298);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		   
//		   Full screen
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		   
		 //To set the center
		 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		  
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(null, "Login System", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 54, 440, 228);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(10, 26, 116, 42);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(119, 26, 287, 42);
		panel.add(txtEmail);
		
		//Placeholder for email text field
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtEmail.getText().equals("Enter your Email..")){
					txtEmail.setText("");
				}
				txtEmail.setForeground(Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtEmail.getText().equals("")){
					txtEmail.setText("Enter your Email..");
				}
				txtEmail.setForeground(new Color(153, 153, 153));
			}
		});
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(119, 78, 287, 42);
		panel.add(txtPassword);
		
		//Placeholder for password field
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				    txtPassword.setEchoChar('*');
				    String password = String.valueOf(txtPassword.getPassword());
				    
				    if(password.toLowerCase().equals("password"))
				    {
				    	txtPassword.setText("");
				    	txtPassword.setForeground(Color.black);
				    }
			}
			@Override
			public void focusLost(FocusEvent e) {
				String password = String.valueOf(txtPassword.getPassword());
			    
			    
			    if(password.toLowerCase().equals("password") || password.toLowerCase().equals("") )
			    {
			    	txtPassword.setText("Password");
			    	txtPassword.setEchoChar((char)0);
			    	txtPassword.setForeground(new Color(153, 153, 153));
			    }
			}
		});
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 78, 116, 42);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(0, 153, 255));
		btnLogin.setForeground(SystemColor.textHighlightText);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnLogin.setBounds(119, 130, 287, 47);
		panel.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String email = txtEmail.getText();
				String password = txtPassword.getText();
				
				try {
 					// create connection object
 					Connection con = DatabaseConnectionUtil.ConnectDB();
 					
 					// query for insert values into the table
 					String query = "SELECT Email, Password FROM Customer WHERE Email=? AND Password = ?";
 					
 					// prepared statement
 					PreparedStatement ps = con.prepareStatement(query);
 					
 					ps.setString(1, email);
 					ps.setString(2, password);
 		
 					ResultSet rs = ps.executeQuery();
 					
 					if(email.length()==0 || password.length()==0) {
 						JOptionPane.showMessageDialog(null, "Fields are requried to fill");
 					}
 					
 					if(email.equals("admin@gmail.com") && password.equals("admin")) {
 						JOptionPane.showMessageDialog(null, "Hotel staff login successfully");
 						Dashboard dash = new Dashboard();
 						frame.setVisible(false);
 					}
 					
 					else if(rs.next()){
 						JOptionPane.showMessageDialog(null, "Login Successfully");
 						Global.current_email =email; 
 						BookingForm booking = new BookingForm();
                        booking.table_load();
 						frame.dispose();
 						
 					}
 					
 				    else {
                         JOptionPane.showMessageDialog(null, "Wrong Username & Password");
                     }
 					
                    }catch (SQLException sqlException) {
                        sqlException.printStackTrace();
 				}
 			}
 		});
				
			
		JLabel lblRegister = new JLabel(">> No Account? Create one!");
		// Action listener for staff	
		lblRegister.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerRegistration register = new CustomerRegistration();
				
				frame.dispose();
                
			}
			
		}); 
		
		lblRegister.setForeground(new Color(255, 0, 0));
		lblRegister.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblRegister.setBounds(159, 187, 207, 28);
		panel.add(lblRegister);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 0, 484, 34);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblLoginForm = new JLabel("Login Form");
		lblLoginForm.setForeground(new Color(255, 255, 240));
		lblLoginForm.setBounds(10, 0, 186, 34);
		panel_1.add(lblLoginForm);
		lblLoginForm.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		// Close the JFrame while click the X label.
		JLabel lblexit = new JLabel("X");
		lblexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		            frame.setVisible(false);
		            
			}
		});
		lblexit.setForeground(new Color(255, 255, 240));
		lblexit.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblexit.setBounds(439, 0, 30, 34);
		panel_1.add(lblexit);
		
		JLabel lblMin = new JLabel("-");
		// Minimize the JFrame while click the - label.
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});
		lblMin.setForeground(new Color(255, 255, 240));
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblMin.setBounds(409, 4, 20, 21);
		panel_1.add(lblMin);
		
		// to set the frame visible
		frame.setVisible(true);
	}
	

	// To run the application
//		public static void main(String[] args) {
//			
//				LoginForm window = new LoginForm();
//					
//		}
	
}

