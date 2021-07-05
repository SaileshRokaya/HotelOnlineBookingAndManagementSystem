package hotelbookingsystem;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Invoice extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtArr;
	private JTextField txtDepDate;
	private JTextField txtInvoice;
	private JTextField txtBilldate;
	private JTextField dxtDate;
	private JTextField txtDesc;
	private JTextField txtRate;
	private JTextField txtSC;
	private JTextField txtVAt;
	private JTextField txtTotal;
	private JLabel lblNewLabel_1_3_1_1_2_1_1;

	
	//CheckedOutForm out = new CheckedOutForm();
	String in = CheckedOutForm.roomNoValue;
	
	
	// To get instance date
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	Date date = new Date();  
	String currentDate = dateFormat.format(date);
	
	
	// Default constructor to initialze the contents of the frame
	public Invoice() {
		
		// To get instance date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = new Date();  
		String currentDate = dateFormat.format(date);
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 977, 612);
		setUndecorated(true);
		// To set the center
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 936, 582);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hotel Luton Pvt. Ltd.");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(290, 38, 408, 47);
		panel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 175, 916, 9);
		panel.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Guest Name: ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(30, 190, 123, 33);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Address: ");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(30, 233, 103, 33);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Arr date:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(30, 276, 103, 33);
		panel.add(lblNewLabel_1_1_1);

		txtName = new JTextField("");
		txtName.setBorder(null);
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtName.setBounds(163, 190, 185, 33);
		panel.add(txtName);
		txtName.setColumns(10);

		txtAddress = new JTextField();
		txtAddress.setBorder(null);
		txtAddress.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtAddress.setColumns(10);
		txtAddress.setBounds(163, 233, 185, 33);
		panel.add(txtAddress);

		txtArr = new JTextField();
		txtArr.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtArr.setColumns(10);
		txtArr.setBounds(163, 276, 185, 33);
		txtArr.setBorder(null);
		panel.add(txtArr);

		JTextArea txtArea = new JTextArea();
		txtArea.setBounds(22, 629, 70, 33);
		panel.add(txtArea);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String printData = txtArea.getText();
				PrinterJob job = PrinterJob.getPrinterJob();
				boolean doPrint = job.printDialog();
				if (doPrint) {
					try {
						job.print();
					} catch (PrinterException e1) {

					}
				}
				
			}
		});
		btnPrint.setBounds(848, 34, 85, 21);
		panel.add(btnPrint);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Dep date:");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(516, 276, 103, 33);
		panel.add(lblNewLabel_1_1_1_1);

		txtDepDate = new JTextField();
		txtDepDate.setBorder(null);
		txtDepDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDepDate.setColumns(10);
		txtDepDate.setBounds(725, 276, 185, 33);
		panel.add(txtDepDate);
		txtDepDate.setText(currentDate);

		JLabel lblNewLabel_1_2 = new JLabel("Invoice No:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(516, 190, 123, 33);
		panel.add(lblNewLabel_1_2);

		txtInvoice = new JTextField();
		txtInvoice.setBorder(null);
		txtInvoice.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtInvoice.setColumns(10);
		txtInvoice.setBounds(725, 190, 185, 33);
		panel.add(txtInvoice);

		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Payment Status:");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_1_1_1.setBounds(516, 233, 159, 33);
		panel.add(lblNewLabel_1_2_1_1_1);

		txtBilldate = new JTextField();
		txtBilldate.setBorder(null);
		txtBilldate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtBilldate.setColumns(10);
		txtBilldate.setBounds(725, 233, 185, 33);
		panel.add(txtBilldate);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 319, 916, 9);
		panel.add(separator_1);

		JLabel lblNewLabel_1_3 = new JLabel("Date");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(32, 338, 85, 33);
		panel.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_3_1 = new JLabel("Description");
		lblNewLabel_1_3_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_3_1.setBounds(149, 339, 152, 33);
		panel.add(lblNewLabel_1_3_1);

		JLabel lblNewLabel_1_3_1_1 = new JLabel("SC (10%)");
		lblNewLabel_1_3_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_3_1_1.setBounds(468, 338, 85, 33);
		panel.add(lblNewLabel_1_3_1_1);

		JLabel lblNewLabel_1_3_1_1_1 = new JLabel("VAT(13%)");
		lblNewLabel_1_3_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_3_1_1_1.setBounds(622, 338, 110, 33);
		panel.add(lblNewLabel_1_3_1_1_1);

		JLabel lblNewLabel_1_3_1_1_2 = new JLabel("Total");
		lblNewLabel_1_3_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_3_1_1_2.setBounds(797, 338, 47, 33);
		panel.add(lblNewLabel_1_3_1_1_2);

		JLabel lblNewLabel_1_3_1_1_3 = new JLabel("Amount");
		lblNewLabel_1_3_1_1_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_3_1_1_3.setBounds(324, 338, 90, 33);
		panel.add(lblNewLabel_1_3_1_1_3);

		dxtDate = new JTextField();
		dxtDate.setBorder(new EmptyBorder(0, 0, 1, 0));
		dxtDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		dxtDate.setColumns(10);
		dxtDate.setBounds(30, 400, 103, 33);
		panel.add(dxtDate);
		dxtDate.setText(currentDate);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(20, 381, 916, 9);
		panel.add(separator_1_1);

		txtDesc = new JTextField();
		txtDesc.setBorder(new EmptyBorder(0, 0, 1, 0));
		txtDesc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDesc.setColumns(10);
		txtDesc.setBounds(151, 400, 135, 33);
		panel.add(txtDesc);


		txtRate = new JTextField();
		txtRate.setBorder(new EmptyBorder(0, 0, 1, 0));
		txtRate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtRate.setColumns(10);
		txtRate.setBounds(324, 400, 85, 33);
		panel.add(txtRate);
		//txtRate.setEditable(false);

		txtSC = new JTextField();
		txtSC.setBorder(new EmptyBorder(0, 0, 1, 0));
		txtSC.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSC.setColumns(10);
		txtSC.setBounds(468, 400, 70, 33);
		panel.add(txtSC);

		txtVAt = new JTextField();
		txtVAt.setBorder(new EmptyBorder(0, 0, 1, 0));
		txtVAt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtVAt.setColumns(10);
		txtVAt.setBounds(635, 400, 70, 33);
		panel.add(txtVAt);

		txtTotal = new JTextField();
		txtTotal.setBorder(new EmptyBorder(0, 0, 1, 0));
		txtTotal.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTotal.setColumns(10);
		txtTotal.setBounds(798, 400, 85, 33);
		panel.add(txtTotal);

//		lblNewLabel_1_3_1_1_2_1_1 = new JLabel("Tax Invoice");
//		lblNewLabel_1_3_1_1_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
//		lblNewLabel_1_3_1_1_2_1_1.setBounds(415, 110, 129, 33);
//		panel.add(lblNewLabel_1_3_1_1_2_1_1);

		JLabel lblNewLabel_1_3_1_1_2_1_1_1 = new JLabel("Luton, LU1 3JU United Kingdom");
		lblNewLabel_1_3_1_1_2_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_3_1_1_2_1_1_1.setBounds(327, 76, 314, 33);
		panel.add(lblNewLabel_1_3_1_1_2_1_1_1);

		JLabel lblNewLabel_1_3_1_1_2_1_1_1_1 = new JLabel("Tel: +44 (0)1234 400 400");
		lblNewLabel_1_3_1_1_2_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_3_1_1_2_1_1_1_1.setBounds(360, 95, 223, 33);
		panel.add(lblNewLabel_1_3_1_1_2_1_1_1_1);

		JLabel lblNewLabel_1_3_1_1_2_1_1_1_1_1 = new JLabel("VAT No: 987654321");
		lblNewLabel_1_3_1_1_2_1_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_3_1_1_2_1_1_1_1_1.setBounds(378, 119, 223, 33);
		panel.add(lblNewLabel_1_3_1_1_2_1_1_1_1_1);
		
		JLabel lblNewLabel_1_4 = new JLabel("Thank you visit again");
		lblNewLabel_1_4.setFont(new Font("Arial", Font.ITALIC, 15));
		lblNewLabel_1_4.setBounds(429, 519, 190, 33);
		panel.add(lblNewLabel_1_4);
		
		
		// try-catch block
		try {
			
			// Create connection Object
			Connection con = DatabaseConnectionUtil.ConnectDB();
			
			// Query to get room id 
			String query1 = "select RoomID, RoomType, RoomPrice from RoomInfo where RoomNo='"+in+"'";
			PreparedStatement pst1 = con.prepareStatement(query1);
			ResultSet rst1 = pst1.executeQuery();
			
			int rid = 0;
			String roomType = "";
			int price = 0;
			
			while(rst1.next()) {
				rid = rst1.getInt(1);
				roomType = rst1.getString(2);
				price = rst1.getInt(3);
			}
			
			String add = roomType + " ( " + in + " )";
			txtDesc.setText(add);
			
			System.out.println("The extracted room id is: " + rid);
			
			
			// Query to get booking id and customer id 
			String query2 = "select BookingID, CustomerID from Booking where RoomID='"+rid+"'";
			PreparedStatement pst2 = con.prepareStatement(query2);
			ResultSet rst2 = pst2.executeQuery();
						
			int bid = 0;
			int cid = 0;
			
			while(rst2.next()) {
					bid = rst2.getInt(1);
					cid = rst2.getInt(2);
			}
			
			System.out.println("The extracted booking id is: " + bid);
			System.out.println("The extracted customer id is: " + cid);
			
			// Query to get customer name
			String query3 = "select Fullname, Address, CustomerType from Customer where CustomerID='"+cid+"'";
			PreparedStatement pst3 = con.prepareStatement(query3);
			ResultSet rst3 = pst3.executeQuery();
			
			String name = "";
			String address = "";
			String type = "";
			
			while(rst3.next()) {
				name = rst3.getString(1);
				address = rst3.getString(2);
				type = rst3.getString(3);
			}
			
			txtName.setText(name);
			txtAddress.setText(address);
			
			
			
			// Query to get arrival date 
			String query4 = "select ArrivalDate, Status from Booking where BookingID='"+bid+"'";
			PreparedStatement pst4 = con.prepareStatement(query4);
			ResultSet rst4 = pst4.executeQuery();
			
			String arrival = "";
			String stat = "";
			
			while(rst4.next()) {
				arrival = rst4.getString(1);
				stat = rst4.getString(2);
			}
			
			txtArr.setText(arrival);
			
			
			// Query to get amount from  table 
			String query5 = "select sum(Total) from Service where BookingID='"+bid+"'";
			PreparedStatement pst5 = con.prepareStatement(query5);
			ResultSet rst5 = pst5.executeQuery();
			
			float amount = 0.0f;
			float sum = 0.0f;
			
			while(rst5.next()) {
				amount = rst5.getFloat(1);
				sum = sum + amount;
			}
			
			
			System.out.println("The total amount is " + sum);
			System.out.println("The room price is " + price);
			
			float fPrice = price;
			
			// Service price plus room price
			float totalAmt = fPrice+sum;
			System.out.println("The total amount of customer " + totalAmt);
			String tot = String.format("%.2f", totalAmt);
			//String tot = Float.toString(totalAmt);
			txtRate.setText(tot);
			
			
			// Calculate service charge
			float sc = (10 * totalAmt)/100;
			String sc1 = String.format("%.2f", sc);
			txtSC.setText(sc1);
			
			// Calcualte vat 
			float vat = (13 * totalAmt)/100;
			String vat1 = String.format("%.2f", vat);
			txtVAt.setText(vat1);
			
			//Add all the amount
			float totalAmount = (totalAmt + sc + vat);
			String t22 = String.format("%.2f", totalAmount);
			txtTotal.setText(t22);
			
			JPanel panel_1_1 = new JPanel();
			panel_1_1.setLayout(null);
			panel_1_1.setBackground(new Color(255, 140, 0));
			panel_1_1.setBounds(-9, -1, 955, 34);
			panel.add(panel_1_1);
			
			JLabel lblPaymentReceipt = new JLabel("Payment Receipt");
			lblPaymentReceipt.setForeground(new Color(255, 255, 240));
			lblPaymentReceipt.setFont(new Font("Tahoma", Font.BOLD, 22));
			lblPaymentReceipt.setBounds(10, 0, 335, 34);
			panel_1_1.add(lblPaymentReceipt);
			
			JLabel lblexit = new JLabel("X");
			lblexit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
					//CheckedOutForm out = new CheckedOutForm();
					//out.showTable();
				}
			});
			lblexit.setForeground(new Color(255, 255, 240));
			lblexit.setFont(new Font("Tahoma", Font.BOLD, 22));
			lblexit.setBounds(915, 0, 30, 34);
			panel_1_1.add(lblexit);
			
			JLabel lblMin = new JLabel("-");
			lblMin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setState(Frame.ICONIFIED);
				}
			});
			lblMin.setForeground(new Color(255, 255, 240));
			lblMin.setFont(new Font("Tahoma", Font.BOLD, 40));
			lblMin.setBounds(885, 4, 20, 21);
			panel_1_1.add(lblMin);
			
			
			
			if(stat.equals("Checked In")) {
				
				lblNewLabel_1_3_1_1_2_1_1 = new JLabel("Provisional");
				lblNewLabel_1_3_1_1_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblNewLabel_1_3_1_1_2_1_1.setBounds(415, 140, 129, 33);
				panel.add(lblNewLabel_1_3_1_1_2_1_1);
				
				txtInvoice.setText("");
				txtBilldate.setText("Unclear");
				
			}else {
				
				lblNewLabel_1_3_1_1_2_1_1 = new JLabel("Tax Invoice");
				lblNewLabel_1_3_1_1_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblNewLabel_1_3_1_1_2_1_1.setBounds(415, 140, 129, 33);
				panel.add(lblNewLabel_1_3_1_1_2_1_1);
				
				// Query to insert data into Receipt table;
				String insertQuery = "insert into Receipt(BookingID, Amount, ServiceCharge, VATCharge, GrandTotal) values(?,?,?,?,?)";
				PreparedStatement insertPst = con.prepareStatement(insertQuery);
				
				insertPst.setInt(1, bid);
				insertPst.setFloat(2, totalAmt);
				insertPst.setFloat(3, sc);
				insertPst.setFloat(4, vat);
				insertPst.setFloat(5, totalAmount);
				insertPst.execute();
				
				if(type.equals("Normal Customer")) {
					
					// Ouery to update payment status
					String q1 = "update Receipt set PaymentStatus=? where BookingID='"+bid+"'";
					PreparedStatement pst6 = con.prepareStatement(q1);
					pst6.setString(1, "Cleared");
					pst6.executeUpdate();
					txtBilldate.setText("Cleared");
				}
				
				else {
					// Ouery to update payment status
					String q1 = "update Receipt set PaymentStatus=? where BookingID='"+bid+"'";
					PreparedStatement pst6 = con.prepareStatement(q1);
					pst6.setString(1, "Credit");
					pst6.executeUpdate();
					txtBilldate.setText("Credit");
				}
				
				String s1 = Integer.toString(bid);
				txtInvoice.setText(s1);
			}
			
		}
		
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error!!!");
		}
		
		
		setVisible(true);
	}
	
	
	// Main method to run the application
//		public static void main(String[] args) {
//		      Invoice frame = new Invoice();
//		}
}
