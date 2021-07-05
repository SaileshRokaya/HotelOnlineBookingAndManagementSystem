package hotelbookingsystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Welcomepage {

	private JFrame frame;

	
	// default constructor to initialize the contents of the frame
	public Welcomepage() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\login.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
		
		// To set the center
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width/2, screenSize.height/2);
				
	    //Full screen
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		//frame.setUndecorated(true);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-21, 69, 1500, 700);
		lblNewLabel.setIcon(new ImageIcon("img\\reception.jpg"));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 0));
		panel.setBounds(-106, 0, 1813, 30);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblWelcomeToHotel = new JLabel("Welcome To Hotel Luton");
		lblWelcomeToHotel.setBounds(751, 2, 309, 27);
		panel.add(lblWelcomeToHotel);
		lblWelcomeToHotel.setForeground(new Color(255, 255, 240));
		lblWelcomeToHotel.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		JLabel lblMin = new JLabel("-");
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});
		lblMin.setBounds(1575, 2, 20, 21);
		lblMin.setForeground(new Color(255, 255, 240));
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 40));
		panel.add(lblMin);
		
		JLabel lblexit = new JLabel("X");
		lblexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblexit.setBounds(1605, -2, 30, 34);
		lblexit.setForeground(new Color(255, 255, 240));
		lblexit.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel.add(lblexit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 153, 51));
		panel_1.setBounds(-537, 28, 2116, 42);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHotelOnlineBooking = new JLabel("Hotel Online Booking And Management System");
		lblHotelOnlineBooking.setBounds(998, 3, 628, 32);
		lblHotelOnlineBooking.setForeground(new Color(255, 255, 240));
		lblHotelOnlineBooking.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel_1.add(lblHotelOnlineBooking);
		
		JLabel lblLogin = new JLabel("Sign In");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Load login Page");
				
				LoginForm login = new LoginForm();
				
			}
		});
		lblLogin.setForeground(new Color(255, 255, 240));
		lblLogin.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblLogin.setBounds(550, 7, 104, 27);
		panel_1.add(lblLogin);
		
				
     frame.setVisible(true);
		  
	}

	
	// Main method to run the application
	public static void main(String[] args) {
			
		Welcomepage window = new Welcomepage();
				
	}
}
