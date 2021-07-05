package hotelbookingsystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BookingForm {

	private JFrame frame;
	private JTextField roomPrice;
	private JScrollPane scrollPane;
	private JTable table;
	private JComboBox roomCombo;
	private JDateChooser arrivalDate;
	private JDateChooser departureDate;
    
	//Values for combobox
	private String room[] = {"Single", "Double", "Twin"};
	
	// Create connection object
	Connection conn = DatabaseConnectionUtil.ConnectDB();
	
	// Get customer id from email
	int id = DatabaseConnectionUtil.getCustomerID(Global.current_email);
	
	
	// default constructor to initialize the contents of the frame
	public BookingForm() {
		frame = new JFrame();
		frame.setTitle("BookingForm");
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 771, 578);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 61, 735, 193);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "New Booking", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(panel);
		
		JLabel lblArrivalDate = new JLabel("Arrival Date");
		lblArrivalDate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblArrivalDate.setBounds(20, 10, 135, 61);
		panel.add(lblArrivalDate);
		
		JLabel lblRoomType = new JLabel("Room Type");
		lblRoomType.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblRoomType.setBounds(20, 70, 135, 61);
		panel.add(lblRoomType);
		
		JLabel lblDepartureDate = new JLabel("Departure Date");
		lblDepartureDate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDepartureDate.setBounds(378, 10, 135, 61);
		panel.add(lblDepartureDate);
        

		JLabel lblRoomPrice = new JLabel("Room Price");
		lblRoomPrice.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblRoomPrice.setBounds(378, 70, 135, 61);
		panel.add(lblRoomPrice);
		
		roomCombo = new JComboBox(room);
		roomCombo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		roomCombo.setBounds(137, 87, 179, 32);
		panel.add(roomCombo);
		
		roomPrice = new JTextField();
		roomPrice.setEditable(false);
		roomPrice.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		roomPrice.setColumns(10);
		roomPrice.setBounds(519, 87, 186, 27);
		panel.add(roomPrice);
		
		JButton btnSave = new JButton("Save");
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSave.setBackground(new Color(0, 102, 153));
		btnSave.setBounds(168, 141, 85, 36);
		panel.add(btnSave);
		
		JButton btnPay = new JButton("Pay");
		btnPay.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnPay.setBackground(new Color(218, 165, 32));
		btnPay.setBounds(263, 141, 85, 36);
		panel.add(btnPay);
		
		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// try-catch block
				try {
					
					//Query to get customer type
					String query1 = "select CustomerType from Customer where CustomerID='"+id+"'";
					PreparedStatement pst1 = conn.prepareStatement(query1);
					ResultSet rst1 = pst1.executeQuery();
					
					String type = "";
					while(rst1.next()) {
						type = rst1.getString(1);
					}
					
					System.out.println("The customer type is: " + type);
					
					if(type.equals("Normal Customer")) {
						JOptionPane.showMessageDialog(null, "Action denied");
					}
					else {
						MonthlyPay pay = new MonthlyPay();
						//frame.setVisible(false);
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid");
				}
				
			}
			
		});
		
		
		JButton close = new JButton("Close");
		close.setFont(new Font("Times New Roman", Font.BOLD, 20));
		close.setBackground(new Color(178, 34, 34));
		close.setBounds(620, 141, 85, 36);
		panel.add(close);
		
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					// Close current frame
					frame.setVisible(false);
					conn.close();
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
			}
			
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnDelete.setBackground(new Color(165, 42, 42));
		btnDelete.setBounds(488, 141, 103, 36);
		panel.add(btnDelete);
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Try-catch block
				  try {    
	                	
					    // To retrieve Booking Status from the table
					    String query2 = "select Status from Booking where CustomerID='"+id+"'";
					    PreparedStatement pst2 = conn.prepareStatement(query2);
					    ResultSet rst2 = pst2.executeQuery();
					    
					    String bStatus = "";
					    while(rst2.next()) {
					    	bStatus = rst2.getString("Status");
					    }
					    
					    System.out.println("The booking status is " + bStatus);
					    
					    if(bStatus.equals("Pending")) {
					    	// create connection object
							conn = DatabaseConnectionUtil.ConnectDB();
							
							// mysql insert statement
							String query1 = "delete from Booking where CustomerID=?";
							
							// create the mysql insert prepared statement
							PreparedStatement pstat = conn.prepareStatement(query1);
							pstat.setInt(1, id);
							pstat.execute();
							
							table_load();
							JOptionPane.showMessageDialog(null, "Deleted Successfully");
							
							
					    }
					    else {
					    	btnDelete.setEnabled(false);
					    }
					    	
				}
	            catch (Exception ex) {
	                JOptionPane.showMessageDialog(null,  ex.getMessage());
	            }
				}
	        });
				
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnUpdate.setBackground(SystemColor.textHighlight);
		btnUpdate.setBounds(368, 141, 103, 36);
		panel.add(btnUpdate);
		
		// Action listener event for update button
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					// Store the value of room type choose by the customer
					String roomType = (String) roomCombo.getSelectedItem();
					
					System.out.println("The selected room type is: " + roomType);
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String arrival = format.format(arrivalDate.getDate());
					String departure = format.format(departureDate.getDate());
					
					// Query to update room type
					String query1 = "update Booking set RoomType=?, ArrivalDate=?, DepartureDate=? where CustomerID='"+id+"' and Status='Pending'";
					PreparedStatement pst1 = conn.prepareStatement(query1);
					pst1.setString(1, roomType);
					pst1.setString(2,  arrival);
					pst1.setString(3, departure);
					
					pst1.execute();
					
					table_load();
					JOptionPane.showMessageDialog(null, "Update Successfull");
					
					
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
			
		});
		
		
		arrivalDate = new JDateChooser();
		arrivalDate.setBounds(137, 27, 179, 33);
		panel.add(arrivalDate);
		arrivalDate.setFont(new Font("Arial", Font.PLAIN, 16));
		
		departureDate = new JDateChooser();
		departureDate.setBounds(519, 27, 186, 33);
		panel.add(departureDate);
		departureDate.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}	
		});
		scrollPane.setBounds(10, 264, 735, 262);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		table.setEnabled(true);
		
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
			
			int index = table.getSelectedRow();
			System.out.println("The index is: " + index);
			
            try {
				Date format = new SimpleDateFormat("yyyy-MM-dd").parse((String) table.getModel().getValueAt(index, 2));
				arrivalDate.setDate(format);
				
				Date format1 = new SimpleDateFormat("yyyy-MM-dd").parse((String) table.getModel().getValueAt(index, 3));
				departureDate.setDate(format1); 
				
				String rooms = table.getModel().getValueAt(index, 4).toString();
				       switch(rooms) {
				       case "Single":
				    	   roomCombo.setSelectedIndex(0);
				    	   break;
				       
				       case "Double":
				    	   roomCombo.setSelectedIndex(1);
				    	   break;
				    	   
				       case "Twin":
				    	   roomCombo.setSelectedIndex(2);
				    	   break;	   
				       }
				
			} catch (ParseException e1) {
				
				e1.printStackTrace();
			}
		}
			
           });
	
		
		scrollPane.setViewportView(table);
		
		JLabel lblBookingForm = new JLabel("Booking Form");
		lblBookingForm.setBounds(272, 0, 193, 61);
		lblBookingForm.setFont(new Font("Times New Roman", Font.BOLD, 30));
		frame.getContentPane().add(lblBookingForm);
		
		
		// JDateChooser convert to string
		String arrivalDateTxt = ((JTextField) arrivalDate.getDateEditor().getUiComponent()).getText();
		String departureDateTxt = ((JTextField) departureDate.getDateEditor().getUiComponent()).getText();
		
		// combobox action performed
	    roomCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String rType = (String) roomCombo.getSelectedItem();
				System.out.println(rType);
				
				try {
		    		Connection con = DatabaseConnectionUtil.ConnectDB();
		    		String query = "select RoomPrice from RoomInfo where RoomStatus='Vacant' and RoomType='"+rType+"'";
		    		PreparedStatement ps = con.prepareStatement(query);
		    		ResultSet res = ps.executeQuery();
		    		
		    		
		    		while(res.next()) {
		    			String rPrice = res.getString(1);
		    			System.out.println("The room number is:" + rPrice);
		    			roomPrice.setText(res.getString(1));
		    		}
		    	}
		    	catch(Exception ex) {
		    		JOptionPane.showMessageDialog(null, e);
		    	}
				
			}
	    	
	    });
		
		
		// Save button Action listener
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// try-catch block 
				try {
					
					table_load();
					
					// To store room type values
					String roomType = (String) roomCombo.getSelectedItem();
					
					// query to select roomtype and id from the room info table
					String roomQuery = "select RoomID from RoomInfo where RoomStatus='Vacant' and RoomType='"+roomType+"'";
					PreparedStatement pst2 = conn.prepareStatement(roomQuery);
					ResultSet rst2 = pst2.executeQuery();
					
					int roomId = 0;
					while(rst2.next()) {
						roomId = rst2.getInt(1);
					}
					System.out.println("The Room id is: " + roomId);
					
					// query to insert data into the booking table
					String query = "insert into Booking(CustomerID, ArrivalDate, DepartureDate, RoomType) values(?,?,?,?)";
					
					// create a prepared statement
					PreparedStatement ps1 = conn.prepareStatement(query);
					
				
					// Set the values in database
					ps1.setInt(1, id);
					//ps1.setInt(2, 0);
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String arrival = format.format(arrivalDate.getDate());
					String departure = format.format(departureDate.getDate());
					
					System.out.println("The arrival date is: " + arrival);
					System.out.println("The departure date is: " + departure);
					
					ps1.setString(2, arrival);
					ps1.setString(3, departure);
					ps1.setString(4, roomType);
								
					if (ps1.executeUpdate() >0) {
						        table_load();
								JOptionPane.showMessageDialog(null, "Booking Successfull");
									
					       
					}		
				}		
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		
		
		frame.setVisible(true);
		}
	
	
	
	public void table_load() {
		try {
		    Connection con = DatabaseConnectionUtil.ConnectDB();
			
			String stat = "Select Status from Booking where CustomerID='"+id+"'";
			PreparedStatement pst1 = con.prepareStatement(stat);
			ResultSet rst1 = pst1.executeQuery();
			
			String status = "";
			while(rst1.next()) {
				rst1.getString("Status");
			}
			

				String query = "select c.Fullname, c.Address, b.ArrivalDate, b.DepartureDate, b.RoomType, RoomNo, Status\r\n"
						+ "from Booking b inner join Customer c\r\n"
						+ "on b.CustomerID = c.CustomerID\r\n"
						+ "left join RoomInfo r\r\n"
						+ "on b.RoomID = r.RoomID\r\n"
						+ "where c.CustomerID='"+id+"' and b.Status='Pending'";
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				
				table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// To run the application
//	public static void main(String[] args) {
//		BookingForm booking = new BookingForm();
//	}
	
	}

	
  
		
	
	   
	    
	

