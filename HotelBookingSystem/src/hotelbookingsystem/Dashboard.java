package hotelbookingsystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;

public class Dashboard {

	private JFrame frame;

	
	// Main method to run the application
//	public static void main(String[] args) {
//		
//			Dashboard window = new Dashboard();
//			window.frame.setVisible(true);
//			
//	}

	
	// default constructor to initialize the contents of the frame
	public Dashboard() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\login.png"));
		frame.getContentPane().setBounds(new Rectangle(100, 100, 1604, 882));
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 153, 51));
		panel_1.setBounds(-537, 0, 2116, 70);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblHotelOnlineBooking = new JLabel("Hotel Online Booking And Management System");
		lblHotelOnlineBooking.setBounds(959, 18, 991, 42);
		lblHotelOnlineBooking.setForeground(new Color(255, 255, 240));
		lblHotelOnlineBooking.setFont(new Font("Tahoma", Font.BOLD, 35));
		panel_1.add(lblHotelOnlineBooking);
		
		JLabel lblNewLabel_1 = new JLabel("Log Out");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				Welcomepage we = new Welcomepage();
				LoginForm lo = new LoginForm();
			}
		});
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(1820, 21, 991, 42);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(254, 126, 158, 98);
		frame.getContentPane().add(panel_2);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Restaurant & Bar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBackground(SystemColor.info);
		panel_2.setLayout(null);
				
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(0, 472, 158, 98);
		panel_2.add(panel_2_1);
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "External Service", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2_1.setBackground(SystemColor.info);
		
		JButton btnExternalService = new JButton("ES- Charge");
		btnExternalService.setBackground(new Color(102, 51, 153));
		btnExternalService.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExternalService.setBounds(0, 30, 148, 50);
		panel_2_1.add(btnExternalService);
		
		btnExternalService.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OtherService other = new OtherService();
				other.startShowTable();
			}
			
		});
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 69, 158, 254);
		panel_2.add(panel);
		panel.setBackground(SystemColor.info);
		panel.setBorder(new TitledBorder(null, "Receptionist", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		
		JButton btnCheckin = new JButton("Check-In");
		btnCheckin.setBackground(new Color(255, 204, 0));
		btnCheckin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCheckin.setBounds(0, 55, 148, 50);
		panel.add(btnCheckin);
		
		
		// button check in 
		btnCheckin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				CheckInForm checkin = new CheckInForm();
				checkin.startShowTable();
				//frame.setVisible(false);
				
			}
			
		});		
				
				
		JButton btnCheckout = new JButton("Check-Out");
		btnCheckout.setBackground(new Color(255, 0, 0));
		btnCheckout.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCheckout.setBounds(0, 151, 148, 50);
		panel.add(btnCheckout);		
				
				
		btnCheckout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CheckedOutForm checkout = new CheckedOutForm();
				checkout.showTable();
			}
			
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 383, 158, 87);
		panel_3.setBorder(new TitledBorder(null, "Restaurant & Bar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(SystemColor.info);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnChargeItemTo = new JButton("Charge Item ");
		btnChargeItemTo.setBackground(new Color(0, 102, 153));
		btnChargeItemTo.setBounds(0, 20, 149, 53);
		panel_3.add(btnChargeItemTo);
		btnChargeItemTo.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				
		btnChargeItemTo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RestaurantBar res = new RestaurantBar();
				res.startShowTable();
			}
			
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\hotel.jpg"));
		lblNewLabel.setBounds(157, -53, 1389, 859);
		panel_2.add(lblNewLabel);
		
		frame.setVisible(true);		
				
				
				
	}

}
