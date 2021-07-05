package hotelbookingsystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.SystemColor;
import java.awt.Toolkit;

import com.toedter.calendar.JDateChooser;
import javax.swing.border.TitledBorder;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MonthlyPay extends JFrame {
	private JTextField txtCredit;
	private int bid;

	
	// Get customer id from email
	int id = DatabaseConnectionUtil.getCustomerID(Global.current_email);
	
	// Main method to run the class
//	public static void main(String[] args) {
//		new MonthlyPay();
//	}

	
	// default constructor to initialize the contents of the frame
	public MonthlyPay() {
		getContentPane().setBackground(SystemColor.activeCaption);
		setBounds(100, 100, 467, 262);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setUndecorated(true);
		// To set the center
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
		
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(255, 140, 0));
		panel_1_1.setBounds(0, 0, 594, 34);
		getContentPane().add(panel_1_1);
		
		JLabel lblBilling = new JLabel("Monthly Payment");
		lblBilling.setForeground(new Color(255, 255, 240));
		lblBilling.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblBilling.setBounds(10, 0, 335, 34);
		panel_1_1.add(lblBilling);
		
		JLabel lblexit = new JLabel("X");
		lblexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Close the frame
				setVisible(false);
			}
		});
		lblexit.setForeground(new Color(255, 255, 240));
		lblexit.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblexit.setBounds(438, 0, 30, 34);
		panel_1_1.add(lblexit);
		
		JLabel lblMin = new JLabel("-");
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				Minimize the frame
				setState(Frame.ICONIFIED);
			}
		});
		lblMin.setForeground(new Color(255, 255, 240));
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblMin.setBounds(408, 4, 20, 21);
		panel_1_1.add(lblMin);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(4, 35, 459, 87);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JTextField dateFrom = new JTextField();
		//dateFrom.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 18));
		dateFrom.setBounds(89, 31, 126, 33);
		panel.add(dateFrom);
		
		
		JTextField dateTo = new JTextField();
		dateTo.setBounds(282, 31, 126, 33);
		panel.add(dateTo);
		
		JLabel lblNewLabel = new JLabel("From");
		lblNewLabel.setBounds(18, 30, 69, 34);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(237, 30, 35, 34);
		panel.add(lblTo);
		lblTo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel lblSearch = new JLabel("");
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Filter credit amt in date range");
			}
		});
		lblSearch.setIcon(new ImageIcon("D:\\BSCCSSE\\Java\\TestCRUD\\img\\search.png"));
		lblSearch.setBounds(426, 27, 38, 39);
		panel.add(lblSearch);
		
		JLabel lblTotalCreditAmount = new JLabel("Total Credit Amount");
		lblTotalCreditAmount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTotalCreditAmount.setBounds(24, 132, 177, 34);
		getContentPane().add(lblTotalCreditAmount);
		
	
		txtCredit = new JTextField();
		txtCredit.setEditable(false);
		txtCredit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtCredit.setBounds(211, 132, 109, 29);
		getContentPane().add(txtCredit);
		txtCredit.setColumns(10);
		
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Payment code
			}
		});
		
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPay.setBackground(new Color(50, 205, 50));
		btnPay.setBounds(180, 180, 150, 32);
		getContentPane().add(btnPay);
		
		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					// Create connection object
					Connection con = DatabaseConnectionUtil.ConnectDB();
					
					// query to update payment status
					String q1 = "update Receipt set PaymentStatus=? where BookingID='"+bid+"'";
					PreparedStatement pst4 = con.prepareStatement(q1);
					pst4.setString(1, "Cleared");
					pst4.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Payment clear successfully");
					
					txtCredit.setText("");
					dateFrom.setText("");
					dateTo.setText("");
					
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
				
			}
			
		});
		
		
		// try-catch block
		try {
			
			System.out.println("The customer id is: " + id);
			
			// Create connection object
			Connection con = DatabaseConnectionUtil.ConnectDB();
			
			//Query to get arrival date
			String queryArrival = "select BookingID, ArrivalDate from Booking where CustomerID='"+id+"'";
			PreparedStatement pst1 = con.prepareStatement(queryArrival);
			ResultSet rst1 = pst1.executeQuery();
			
			String arrival = "";
			
			
			while(rst1.next()) {
				bid = rst1.getInt(1);
				arrival = rst1.getString(2);
			}
			
			System.out.println("The booking id is: " + bid);
			
			System.out.println("The arrival date is: " + arrival);
			
			// Date and Time format
			DateTime dt = new DateTime(arrival);
			
			DateTime dt1 = dt.plusDays(30);
			
			DateTimeFormatter dateformat = DateTimeFormat.forPattern("yyyy-MM-dd");
			System.out.println("Today date: " + dt1.toString(dateformat));
			
			dateFrom.setText(arrival);
			dateTo.setText(dt1.toString(dateformat));
			
			// Query to get credit amount
			String query2 = "select GrandTotal \r\n"
					+ "from Booking b\r\n"
					+ "inner join Customer c\r\n"
					+ "on b.CustomerID=c.CustomerID\r\n"
					+ "inner join Receipt r\r\n"
					+ "on r.BookingID=b.BookingID\r\n"
					+ "where c.CustomerID='"+id+"' and r.PaymentStatus='Credit'";
			PreparedStatement pst2 = con.prepareStatement(query2);
			ResultSet rst2 = pst2.executeQuery();
			
			
			float amt = 0.0f;
			
			while(rst2.next()) {
				amt = rst2.getFloat(1);
			}
			
			System.out.println("The amount is: " + amt);
			
			String at = Float.toString(amt);
			txtCredit.setText(at);
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error");
		}
			
		setVisible(true);
	}
}
