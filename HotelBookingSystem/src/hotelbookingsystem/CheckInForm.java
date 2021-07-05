package hotelbookingsystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class CheckInForm {

	private JFrame frame;
	private JTable table;
	private JTextField checkInText;
	private JTextField txtRoomType;
	private String email;
	private JComboBox comRoomNo;
	private String rooms;
	private String chooseRoomNo1;
	
	
	// To get instance date
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	Date date = new Date();  
	String currentDate = dateFormat.format(date);
    
	// Get customer id from email
	int id = DatabaseConnectionUtil.getCustomerID(Global.current_email);
	private JTextField txtCardDts;
	
	// Default constructor
	public CheckInForm() {
		
		// To get instance date
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    Date date = new Date();  
		String currentDate = dateFormat.format(date);
		
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 755, 597);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);

		// To set the center
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(0, 0, 755, 34);
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(255, 140, 0));
		frame.getContentPane().add(panel_1_1);
		
		JLabel lblGuestCheckedin = new JLabel("Today's CheckedIn");
		lblGuestCheckedin.setForeground(new Color(255, 255, 240));
		lblGuestCheckedin.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblGuestCheckedin.setBounds(10, 0, 242, 34);
		panel_1_1.add(lblGuestCheckedin);
		
		checkInText = new JTextField();
		checkInText.setBounds(570, 60, 151, 30);
		checkInText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		checkInText.setColumns(10);
		frame.getContentPane().add(checkInText);
		
		checkInText.setText(currentDate);
		checkInText.setEditable(false);
		
		
		JLabel lblexit = new JLabel("X");
		lblexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				//System.exit (0);
			}
		});
		lblexit.setForeground(new Color(255, 255, 240));
		lblexit.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblexit.setBounds(715, 0, 30, 34);
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
		lblMin.setBounds(685, 4, 20, 21);
		panel_1_1.add(lblMin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 229, 733, 357);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Room Allocate", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 40, 733, 178);
		frame.getContentPane().add(panel);
		
		JLabel lblArrivalDate = new JLabel("Room No");
		lblArrivalDate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblArrivalDate.setBounds(20, 10, 151, 61);
		panel.add(lblArrivalDate);
		
		JLabel lblRoomType = new JLabel("Room Type");
		lblRoomType.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblRoomType.setBounds(20, 66, 135, 61);
		panel.add(lblRoomType);
		
		JDateChooser dateDepartureDate = new JDateChooser();
		dateDepartureDate.setBounds(260, 240, 260, 20);
		panel.add(dateDepartureDate);
		
		comRoomNo = new JComboBox();
		comRoomNo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		comRoomNo.setBounds(181, 24, 94, 32);
		panel.add(comRoomNo);
		
		// combo box listener
		comRoomNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = DatabaseConnectionUtil.ConnectDB();
					
					if (comRoomNo.getSelectedIndex() != -1) {
						chooseRoomNo1 = comRoomNo.getSelectedItem().toString();
						
						//Query to get booking id and customer id
						String roomType = "select b.BookingID, c.CustomerID, c.CardNumber, b.Status\r\n"
								+ "from Booking b inner join Customer c\r\n"
								+ "on b.CustomerID = c.CustomerID\r\n"
								+ "where c.Email='"+email+"' and  b.Status='Pending'";
						
						PreparedStatement pst2 = con.prepareStatement(roomType);
						ResultSet rst2 = pst2.executeQuery();
						
						int bid = 0;
						int cid = 0;
						String stat = "";
						
						while(rst2.next()) {
							bid = rst2.getInt(1);
							cid = rst2.getInt(2);
							stat = rst2.getString(3);
						}
						
					
						// Getting room number from combo box
						String idQuery = "select RoomID from RoomInfo where RoomNo='"+chooseRoomNo1+"' and RoomStatus='Vacant'";
						PreparedStatement pst4 = con.prepareStatement(idQuery);
						ResultSet rst4 = pst4.executeQuery();
						
						int rid2 = 0;
						while(rst4.next()) {
							rid2 = rst4.getInt(1);
						}
						System.out.println("The selected room id for check in is " + rid2);
							
						
						//Query to update room id
						String roomIDUpdate = "Update Booking set RoomID=? where BookingID='"+bid+"' and CustomerID='"+cid+"'";
						PreparedStatement pst5 = con.prepareStatement(roomIDUpdate);
						pst5.setInt(1, rid2);
						pst5.execute();	
						
						
					}
					}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
				
			}
			
		});
		
		
		JButton btnCheckedIn = new JButton("Checked-In");
		btnCheckedIn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnCheckedIn.setBackground(new Color(218, 165, 32));
		btnCheckedIn.setBounds(577, 78, 135, 36);
		panel.add(btnCheckedIn);
		
		btnCheckedIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String getCardNumber = txtCardDts.getText();
				
					try {
						
						// Create connection object
						Connection con = DatabaseConnectionUtil.ConnectDB();
						
						System.out.println("The email is " + email);
						
						
						// Query to retrieve customer id
						String queryID = "select CustomerID, CardNumber, CustomerType from Customer where Email='"+email+"'";
						PreparedStatement ps = con.prepareStatement(queryID);
						ResultSet rs = ps.executeQuery();
						
						int cid = 0;
						String card = "";
						String type = "";
						
						while(rs.next()) {
							cid = rs.getInt(1);
							card = rs.getString(2);
							type = rs.getString(3);
						}
						
						
						
						if(card.equals("") && type.equals("Normal Customer")) {
							JOptionPane.showMessageDialog(null, "Booking confirm not successfull!!!");
							
							// Query to insert card number in customer detail
							String queryCard = "update Customer set CardNumber=? where Email='"+email+"'";
							PreparedStatement pst5 = con.prepareStatement(queryCard);
							pst5.setString(1, getCardNumber);
							pst5.executeUpdate();
							
						}
						else {
							// Query to create update booking status
							String query = "Update Booking set Status=? where ArrivalDate='"+currentDate+"' and Status='Pending' and CustomerID='"+cid+"'";
							PreparedStatement pst1 = con.prepareStatement(query);
							pst1.setString(1, "Checked In");
							pst1.execute();
							
							
							// Query to select booking id
							String bookingID = "select BookingID, RoomID from Booking where CustomerID='"+cid+"' and Status='Checked In'";
							PreparedStatement pst2 = con.prepareStatement(bookingID);
							ResultSet rst2 = pst2.executeQuery();
							
							int bid = 0;
							int rid = 0;
							
							while(rst2.next()) {
								bid = rst2.getInt(1);
								rid = rst2.getInt(2);
							}
							
							
							//Query to update room status
							String roomStatus = "update RoomInfo set RoomStatus='Occupied' where RoomID='"+rid+"' and RoomStatus='Vacant'";
							PreparedStatement pst3 = con.prepareStatement(roomStatus);
							pst3.execute();
							
							JOptionPane.showMessageDialog(null, "Customer Checked In");
							comRoomNo.removeAllItems();
							startShowTable();
						}
						
					}
					catch(Exception ex) {
						ex.printStackTrace();
					}
				
			}
			
		});
		
		
		txtRoomType = new JTextField();
		txtRoomType.setEnabled(false);
		txtRoomType.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtRoomType.setColumns(10);
		txtRoomType.setBounds(181, 84, 105, 27);
		panel.add(txtRoomType);
		
		JLabel lblTodayCheckedin = new JLabel("Today Checked-In");
		lblTodayCheckedin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTodayCheckedin.setBounds(380, 20, 151, 30);
		panel.add(lblTodayCheckedin);
		
		JLabel lblCardDetails = new JLabel("Card Details");
		lblCardDetails.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCardDetails.setBounds(20, 117, 151, 61);
		panel.add(lblCardDetails);
		
		txtCardDts = new JTextField();
		txtCardDts.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtCardDts.setEnabled(true);
		txtCardDts.setColumns(10);
		txtCardDts.setBounds(181, 135, 170, 27);
		panel.add(txtCardDts);
		
		JButton btnView = new JButton("View");
		btnView.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnView.setBackground(Color.CYAN);
		btnView.setBounds(405, 78, 135, 36);
		panel.add(btnView);
		
		// button view action listener
		btnView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				CheckInList checkin = new CheckInList();
				checkin.startShowTable();
				
			}
			
		});
		
	    table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(true);
        table.addMouseListener(new MouseAdapter() {	
        	
        	@Override
			public void mouseClicked(MouseEvent e) {
        	
        	int index = table.getSelectedRow();
      
			// try-catch block             
            try {
            	
            	// Get email from the table
			    email = table.getModel().getValueAt(index, 1).toString();
			    
			    rooms = table.getModel().getValueAt(index, 5).toString();
			    
            	txtRoomType.setText(rooms);
            	
            	
            	// To create Connection object
				Connection con = DatabaseConnectionUtil.ConnectDB();
				
			    System.out.println("The selected room type is: " + rooms);
			    
				    
				// Query to find room type 
				String roomType = "select b.BookingID, c.CustomerID, c.CardNumber, b.Status\r\n"
						+ "from Booking b inner join Customer c\r\n"
						+ "on b.CustomerID = c.CustomerID\r\n"
						+ "where c.Email='"+email+"' and  b.Status='Pending'";
				
				PreparedStatement pst2 = con.prepareStatement(roomType);
				ResultSet rst2 = pst2.executeQuery();
				
				int bid = 0;
				int cid = 0;
				String card = "";
				String stat = "";
				
				while(rst2.next()) {
					bid = rst2.getInt(1);
					cid = rst2.getInt(2);
					card = rst2.getString(3);
					stat = rst2.getString(4);
				}
				
				// Card detail display in text
				txtCardDts.setText(card);
				
				// Query to find room number
				String roomNos = "select RoomNo from RoomInfo where RoomType='"+rooms+"' and RoomStatus='Vacant'";
				PreparedStatement pst3 = con.prepareStatement(roomNos);
				ResultSet rst3 = pst3.executeQuery();
				
				
				while(rst3.next()) {
					comRoomNo.addItem(rst3.getString(1));
				}
				
				
				
				
				
        				
            }catch(Exception ex) {
        			JOptionPane.showMessageDialog(null, ex);
            }
        	
        	}			
        });
	
    	frame.setVisible(true);
	}
	
	
	// Method to show the table
	public void startShowTable() {
		try {
			
			// To create connection object
			Connection conn = DatabaseConnectionUtil.ConnectDB();
			
			// Make prepared Statement
			String query = "select c.Fullname, c.Email, c.Address, b.ArrivalDate, DepartureDate, b.RoomType, r.RoomNo, Status\r\n"
					+ "from Booking b inner join Customer c\r\n"
					+ "on b.CustomerID = c.CustomerID\r\n"
					+ "left join RoomInfo r\r\n"
					+ "on b.RoomID = r.RoomID\r\n"
					+ "where b.ArrivalDate='"+currentDate+"' and Status='Pending'";
			
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	// Main method to run the application
//		public static void main(String[] args) {
//			
//			CheckInForm window = new CheckInForm();
//			window.startShowTable();
//		}
}
