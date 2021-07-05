package hotelbookingsystem;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckedOutForm {

	private JFrame frame;
	private JTextField txtSearch;
	private JTable table;
	private JTextField textField;
    private String email;
	
	// Instance variable
	String roomNo;
	static String roomNoValue;

	
	// To get instance date
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	Date date = new Date();  
	String currentDate = dateFormat.format(date);
	
	
	// Get customer id from email
		int id = DatabaseConnectionUtil.getCustomerID(Global.current_email);
	
	// Main method to run the application
//	public static void main(String[] args) {
//		
//		    CheckedOutForm window = new CheckedOutForm();
//			window.showTable();
//			
//	}

	
	// Default constructor to initialze the contents of the frame
	public CheckedOutForm() {
		
		// To get instance date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = new Date();  
		String currentDate = dateFormat.format(date);
		
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 1065, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		   Full screen
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);

		// To set the center
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(255, 140, 0));
		panel_1_1.setBounds(0, 0, 1065, 34);
		frame.getContentPane().add(panel_1_1);
		
		JLabel lblBilling = new JLabel("Check-Out Page");
		lblBilling.setForeground(new Color(255, 255, 240));
		lblBilling.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblBilling.setBounds(10, 0, 335, 34);
		panel_1_1.add(lblBilling);
		
		JLabel lblexit = new JLabel("X");
		lblexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                   frame.setVisible(false);
				
			}
		});
		lblexit.setForeground(new Color(255, 255, 240));
		lblexit.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblexit.setBounds(1011, 0, 50, 34);
		panel_1_1.add(lblexit);
		
		JLabel lblMin = new JLabel("-");
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});
		lblMin.setForeground(new Color(255, 255, 240));
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblMin.setBounds(969, 5, 31, 21);
		panel_1_1.add(lblMin);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Today's Chekout", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 38, 1060, 60);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton view = new JButton("View");
		view.setFont(new Font("Times New Roman", Font.BOLD, 20));
		view.setBackground(new Color(0, 250, 154));
		view.setBounds(517, 17, 101, 33);
		panel.add(view);
		
		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CheckOutList out = new CheckOutList();
				out.showTable();
				
			}
			
		});
		
		
		JButton btnCheckout = new JButton("Check-Out");
		btnCheckout.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnCheckout.setBackground(new Color(128, 128, 0));
		btnCheckout.setBounds(350, 17, 146, 33);
		panel.add(btnCheckout);
		
		btnCheckout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//roomNo = txtSearch.getText();
				
				try {
					
					// Create connection object
					Connection con = DatabaseConnectionUtil.ConnectDB();
					
					String emailQuery = "select CustomerID from Customer where Email='"+email+"'";
					PreparedStatement psEmail = con.prepareStatement(emailQuery);
					ResultSet rsEmail = psEmail.executeQuery();
					
					int cid = 0;
					while(rsEmail.next()) {
						cid = rsEmail.getInt(1);
					}
					
					System.out.println("The customer id is: " + cid);
					
					// Query to get RoomID
					String query1 = "select BookingID, RoomID from Booking where CustomerID='"+cid+"' and Status='Checked In'";
					//String query1 = "select BookingID, RoomID from Booking where CustomerID='"+cid+"'";
					PreparedStatement pst1 = con.prepareStatement(query1);
					ResultSet rst1 = pst1.executeQuery();
					
					int bid = 0;
					int rid = 0;
					
					while(rst1.next()) {
						bid = rst1.getInt(1);
						rid = rst1.getInt(2);
					}
					System.out.println("The room id is: " + rid + " and booking id is: " + bid);
					
					
					// Query to create update booking status
					String query3 = "Update Booking set Status=? where DepartureDate='"+currentDate+"' and BookingID='"+bid+"' and CustomerID='"+cid+"'";
					PreparedStatement pst3 = con.prepareStatement(query3);
					pst3.setString(1, "Checked-Out");
					pst3.execute();
					
					JOptionPane.showMessageDialog(null, "Customer Check-Out Successfully");
					
					
					//Query to update room status
					String roomStatus = "update RoomInfo set RoomStatus=? where RoomID='"+rid+"' and RoomStatus='Occupied'";
					PreparedStatement pst4 = con.prepareStatement(roomStatus);
					pst4.setString(1, "Vacant");
					pst4.execute();
					
					showTable();
					
					con.close();
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
				
				
		}
	});
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnGenerate.setBackground(new Color(0, 128, 0));
		btnGenerate.setBounds(180, 17, 146, 33);
		panel.add(btnGenerate);
		
		btnGenerate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Invoice invoice = new Invoice();
				//frame.setVisible(false);
				
			}
			
		});
		
		
		JLabel lblTodayCheckout = new JLabel("Today Check-Out");
		lblTodayCheckout.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTodayCheckout.setBounds(750, 13, 188, 40);
		panel.add(lblTodayCheckout);
		
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(900, 21, 144, 27);
		panel.add(textField);
		
		textField.setText(currentDate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 108, 1040, 386);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(true);
		
		// table action listener part
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = table.getSelectedRow();
				System.out.println("The selected row for checked out table is: " + index);
				
				email = (String) table.getValueAt(index, 1);
				System.out.println("The customer email is: " + email);
				
				roomNoValue = (String) table.getValueAt(index, 6);
				System.out.println("The room number is " + roomNoValue);
				
			}
		});
		
		JButton btnProvisional = new JButton("Provisional");
		btnProvisional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Invoice invoi = new Invoice();
				//showTable();
			}
		});
		btnProvisional.setBounds(20, 17, 146, 33);
		btnProvisional.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnProvisional.setBackground(new Color(218, 165, 32));
		panel.add(btnProvisional);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnClose.setBackground(Color.RED);
		btnClose.setBounds(638, 17, 101, 33);
		panel.add(btnClose);
		
		
		frame.setVisible(true);
	}
	
	public void showTable() {
		try {
			
			Connection con = DatabaseConnectionUtil.ConnectDB();
			
			String query1 = "select c.Fullname, c.Email, c.Address, b.ArrivalDate, b.DepartureDate, r.RoomType, r.RoomNo ,\r\n"
					+ " sum(coalesce(s.Total, 0))+ r.RoomPrice   AS TotalAmount, b.Status\r\n"
					+ "	from Booking b\r\n"
					+ "	inner join Customer c\r\n"
					+ "	on b.CustomerID=c.CustomerID\r\n"
					+ "	inner join RoomInfo r\r\n"
					+ "	on r.RoomID = b.RoomID\r\n"
					+ "	left join Service s\r\n"
					+ "	on s.BookingID = b.BookingID\r\n"
					+ "	where b.DepartureDate='"+currentDate+"' and b.Status='Checked In'\r\n"
					+ " group by c.Fullname";
					
			
			PreparedStatement pst1 = con.prepareStatement(query1);
			ResultSet rst1 = pst1.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rst1));
			con.close();
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	
	}
}
