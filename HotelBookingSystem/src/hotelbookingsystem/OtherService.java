package hotelbookingsystem;

import java.awt.EventQueue;

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
import java.sql.SQLException;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OtherService {

	private JFrame frame;
	private JTextField txtQty;
	private JTextField txtRate;
	//private JTextField txtVAT;
	private JTextField txtTotal;
	private JTable table;
	private JTextField txtGuest;
	private JComboBox comRoomNo;
	private JComboBox comServiceType;
	
	private String items;

	

	// Default constructor
	public OtherService() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 827, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);

		// To set the center
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(0, 0, 898, 34);
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(255, 140, 0));
		frame.getContentPane().add(panel_1_1);
		
		JLabel lblRestaurantAndBar = new JLabel("Other Services");
		lblRestaurantAndBar.setForeground(new Color(255, 255, 240));
		lblRestaurantAndBar.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblRestaurantAndBar.setBounds(10, 0, 335, 34);
		panel_1_1.add(lblRestaurantAndBar);
		
		JLabel lblexit = new JLabel("X");
		lblexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
			}
		});
		lblexit.setForeground(new Color(255, 255, 240));
		lblexit.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblexit.setBounds(794, 0, 30, 34);
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
		lblMin.setBounds(764, 4, 20, 21);
		panel_1_1.add(lblMin);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSave.setBackground(new Color(0, 102, 153));
		btnSave.setBounds(574, 200, 85, 36);
		frame.getContentPane().add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Calculation part
				String rate = txtRate.getText();
				String qty = txtQty.getText();
				//String vat = txtVAT.getText();
				String roomNo = (String) comRoomNo.getSelectedItem();
				String service = (String) comServiceType.getSelectedItem();
				
				System.out.println(roomNo);
				
				double rate1 = Double.parseDouble(rate);
				double qty1 = Double.parseDouble(qty);
				//double vat1 = Double.parseDouble(vat);
				
				// to calculate total amount
				double rateQty = (rate1 * qty1);
				
				// to calculate vat 
//				double vat2 = (13 * rateQty) / 100;
//				String vat3 = Double.toString(vat2);
				
				// Total sum including vat
				double total = 0;
				total = (rateQty);
				
				String total1 = Double.toString(total);
				//txtVAT.setText(vat3);
				txtTotal.setText(total1);
				
				try {
					
					Connection con = DatabaseConnectionUtil.ConnectDB();
					
					// Query to retrieve room id
		    		String query1 = "select RoomID from RoomInfo where RoomNo='"+roomNo+"' and RoomStatus='Occupied'";
		    		PreparedStatement pst1 = con.prepareStatement(query1);
		    		ResultSet rst1 = pst1.executeQuery();
		    		
		    	    int rid = 0;
		    		while(rst1.next()) {
		    			rid = rst1.getInt(1);
		    			System.out.println("The room id is" + rid);
		    		}
		    		
		    		
		    		// Query to retrieve booking id
		    		String query2 = "select BookingID from Booking where RoomID='"+rid+"' and Status='Checked In'";
		    		PreparedStatement pst2 = con.prepareStatement(query2);
		    		ResultSet rst2 = pst2.executeQuery();
		    		
		    	    int bid = 0;
		    		while(rst2.next()) {
		    			bid = rst2.getInt(1);
		    			System.out.println("The booking id is " + bid);
		    		}
		    		
					
					// Query to insert data into other_service table;
					String query = "insert Service(BookingID, ServiceType, Quantity, Rate, Total) values(?,?,?,?,?)";
					PreparedStatement pst = con.prepareStatement(query);
					
					pst.setInt(1, bid);
					pst.setString(2, service);
					pst.setString(3, qty);
					pst.setString(4, rate);
					//pst.setString(5, vat3);
		            pst.setString(5, total1);
		            
		            pst.execute();
		            
		            startShowTable();
		            JOptionPane.showMessageDialog(null, "Service added successfully");
					
					
					String def = "";
					
					//txtVAT.setText(def);
					txtTotal.setText(def);
					txtRate.setText(def);
					txtQty.setText(def);
					comRoomNo.setSelectedItem(-1);
					
					
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
			}
			
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 246, 798, 294);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setHorizontalAlignment(SwingConstants.LEFT);
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnExit.setBackground(new Color(165, 42, 42));
		btnExit.setBounds(688, 200, 87, 36);
		frame.getContentPane().add(btnExit);
		
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
			}
			
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Services Assign to Guest", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(8, 33, 799, 208);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		comServiceType = new JComboBox();
		comServiceType.setBounds(565, 23, 206, 34);
		panel.add(comServiceType);
		comServiceType.setModel(new DefaultComboBoxModel(new String[] {"Laundary Items","Minibar Items","Telephone Charge"}));
		comServiceType.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		comServiceType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				items = (String) comServiceType.getSelectedItem();
				System.out.println("The selected item is: " + items);
				
			}
			
		});
		
		comRoomNo = new JComboBox();
		comRoomNo.setBounds(159, 23, 206, 34);
		panel.add(comRoomNo);
		comRoomNo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		// Try-catch block for room number combo
		try {
			
			Connection con = DatabaseConnectionUtil.ConnectDB();
			
			// Query to display all room number whose status are checked in
			String query1 = "select r.RoomNo from RoomInfo r\r\n"
					+ "inner join Booking b\r\n"
					+ "on r.RoomID=b.RoomID\r\n"
					+ "where r.RoomStatus='Occupied' and b.Status='Checked In'";
			
			PreparedStatement pst1 = con.prepareStatement(query1);
			ResultSet rst1 = pst1.executeQuery();
			
			String roomNo = "";
			while(rst1.next()) {
				comRoomNo.addItem(rst1.getString("RoomNo"));
			}
			
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		
		
		// Action listener event for room number combo box
		comRoomNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String roomNumber = (String) comRoomNo.getSelectedItem();
				
				// try-catch block
				try {
		    		Connection con = DatabaseConnectionUtil.ConnectDB();
		    		
		    		// Query to select RoomType from Room Info table
		    		String query1 = "select RoomID from RoomInfo where RoomNo='"+roomNumber+"' and RoomStatus='Occupied'";
		    		PreparedStatement pst1 = con.prepareStatement(query1);
		    		ResultSet rst1 = pst1.executeQuery();
		    		
		    	    int rid = 0;
		    		while(rst1.next()) {
		    			rid = rst1.getInt(1);
		    			System.out.println(rid);
		    		}
		    		
		    		
		    		// Query to select Customer name 
		    		String query2 = "select c.Fullname \r\n"
		    				+ "from Customer c \r\n"
		    				+ "inner join Booking b \r\n"
		    				+ "on c.CustomerID = b.CustomerID \r\n"
		    				+ "where b.RoomID='"+rid+"' and b.Status='Checked In'";
		    		
		    		PreparedStatement ps2 = con.prepareStatement(query2);
		    		ResultSet res2 = ps2.executeQuery();
		    		
		    		while(res2.next()) {
		    			String data = res2.getString(1);
		    			System.out.println("The room number is:" + data);
		    			txtGuest.setText(res2.getString("Fullname"));
		    		}
		    	}
		    	catch(Exception ex) {
		    		ex.printStackTrace();
		    	}
				
			}
			
		});
		
				
		
		JLabel lblMenuItem = new JLabel("Service Type");
		lblMenuItem.setBounds(424, 20, 147, 40);
		panel.add(lblMenuItem);
		lblMenuItem.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblNewLabel = new JLabel("Room Number");
		lblNewLabel.setBounds(10, 22, 147, 40);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		

		txtRate = new JTextField();
		txtRate.setBounds(159, 114, 206, 34);
		panel.add(txtRate);
		txtRate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtRate.setColumns(10);
		
		JLabel lblRate = new JLabel("Rate");
		lblRate.setBounds(10, 114, 128, 40);
		panel.add(lblRate);
		lblRate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblTotalQty = new JLabel("Total Qty");
		lblTotalQty.setBounds(424, 69, 147, 40);
		panel.add(lblTotalQty);
		lblTotalQty.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		txtQty = new JTextField();
		txtQty.setBounds(565, 72, 206, 34);
		panel.add(txtQty);
		txtQty.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtQty.setColumns(10);
		
		JLabel lblGuestName = new JLabel("Guest Name");
		lblGuestName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblGuestName.setBounds(10, 64, 147, 40);
		panel.add(lblGuestName);
		
		txtGuest = new JTextField();
		txtGuest.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtGuest.setEnabled(false);
		txtGuest.setColumns(10);
		txtGuest.setBounds(159, 67, 206, 34);
		txtGuest.setForeground(Color.GREEN);
		panel.add(txtGuest);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 155, 147, 40);
		panel.add(lblTotal);
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		txtTotal = new JTextField();
		txtTotal.setBounds(159, 158, 206, 34);
		panel.add(txtTotal);
		txtTotal.setEnabled(false);
		txtTotal.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtTotal.setColumns(10);
		
		
		// to set the frame visible
		frame.setVisible(true);
	}
	
	
	//Method to show data in jtable
	public void startShowTable() {
		try {
			
			comRoomNo.setSelectedItem(-1);
			
			// To create connection object
			Connection con = DatabaseConnectionUtil.ConnectDB();
			String roomNo = (String) comRoomNo.getSelectedItem();
			
			// Query to retrieve room id
    		String query1 = "select RoomID from RoomInfo where RoomNo='"+roomNo+"' and RoomStatus='Occupied'";
    		PreparedStatement pst1 = con.prepareStatement(query1);
    		ResultSet rst1 = pst1.executeQuery();
    		
    	    int rid = 0;
    		while(rst1.next()) {
    			rid = rst1.getInt(1);
    			System.out.println("The room id is" + rid);
    		}
    		
    		
    		// Query to retrieve booking id  and customer id
    		String query2 = "select BookingID, CustomerID from Booking where RoomID='"+rid+"' and Status='Checked In'";
    		PreparedStatement pst2 = con.prepareStatement(query2);
    		ResultSet rst2 = pst2.executeQuery();
    		
    	    int bid = 0;
    	    int cid = 0;
    	    
    		while(rst2.next()) {
    			bid = rst2.getInt(1);
    			cid = rst2.getInt(2);
    			System.out.println("The booking id is " + bid + " customer id is " + cid);
    		}
    		
    		
    		// Query to get customer email
    		String query3 = "select Email from Customer where CustomerID='"+cid+"'";
    		PreparedStatement pst3 = con.prepareStatement(query3);
    		ResultSet rst3 = pst3.executeQuery();
    		
    		String email = "";
    		
    		while(rst3.next()) {
    			email = rst3.getString(1);
    		}
    		
    		
    			
    			// Make prepared Statement
    			String query = "select c.Fullname, r.RoomNo, s.ServiceType, s.Quantity, s.Rate, s.Total \r\n"
    					+ "from Booking b\r\n"
    					+ "left join Customer c\r\n"
    					+ "on b.CustomerID = c.CustomerID\r\n"
    					+ "left join RoomInfo r\r\n"
    					+ "on b.RoomID = r.RoomID\r\n"
    					+ "left join Service s\r\n"
    					+ "on b.BookingID = s.BookingID\r\n"
    			+ " where s.ServiceType='Laundary Items' or s.ServiceType='Minibar Items' or s.ServiceType='Telephone Charge'";
    					
    			
    			PreparedStatement pst = con.prepareStatement(query);
    			ResultSet rs = pst.executeQuery();
    			
    			table.setModel(DbUtils.resultSetToTableModel(rs));
    			
    			pst.close();
    			con.close();
    		
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// Main method to run the application
//		public static void main(String[] args) {
//			
//			OtherService window = new OtherService();
//			window.startShowTable();
//		}
	


}