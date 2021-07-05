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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class CheckOutList extends JFrame{

	private JTable table;
	private JTextField txtDate;

	
	// To get instance date
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	Date date = new Date();  
	String currentDate = dateFormat.format(date);

	
    public CheckOutList() {

		getContentPane().setBackground(SystemColor.activeCaption);
		setBounds(100, 100, 755, 483);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);

		// To set the center
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(0, 0, 755, 34);
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(255, 140, 0));
		getContentPane().add(panel_1_1);
		
		JLabel lblGuestCheckedin = new JLabel("Checked-Out Guest List");
		lblGuestCheckedin.setForeground(new Color(255, 255, 240));
		lblGuestCheckedin.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblGuestCheckedin.setBounds(10, 0, 299, 34);
		panel_1_1.add(lblGuestCheckedin);
		
		JLabel lblexit = new JLabel("X");
		lblexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
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
				setState(Frame.ICONIFIED);
			}
		});
		lblMin.setForeground(new Color(255, 255, 240));
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblMin.setBounds(685, 4, 20, 21);
		panel_1_1.add(lblMin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 733, 395);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblRoomType = new JLabel("Checked-Out List");
		lblRoomType.setBounds(420, 26, 146, 61);
		getContentPane().add(lblRoomType);
		lblRoomType.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		txtDate = new JTextField();
		txtDate.setBounds(586, 44, 157, 27);
		getContentPane().add(txtDate);
		txtDate.setEnabled(false);
		txtDate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtDate.setColumns(10);
		setVisible(true);
		
		txtDate.setText(currentDate);
	
	}
	
	public void showTable() {
		try {
			
			Connection conn = DatabaseConnectionUtil.ConnectDB();
			
			String query1 = "select c.Fullname, c.Email, c.Address, b.ArrivalDate, b.DepartureDate, r.RoomType, r.RoomNo ,\r\n"
					+ " sum(coalesce(s.Total, 0))+ r.RoomPrice   AS TotalAmount, b.Status\r\n"
					+ "					from Booking b\r\n"
					+ "					inner join Customer c\r\n"
					+ "					on b.CustomerID=c.CustomerID\r\n"
					+ "					inner join RoomInfo r\r\n"
					+ "					on r.RoomID = b.RoomID\r\n"
					+ "					left join Service s\r\n"
					+ "					on s.BookingID = b.BookingID\r\n"
					+ "					where b.DepartureDate='"+currentDate+"' and b.Status='Checked-Out'"
					+ " group by c.Email";
			
			PreparedStatement pst1 = conn.prepareStatement(query1);
			ResultSet rst1 = pst1.executeQuery();
			
			
			
			table.setModel(DbUtils.resultSetToTableModel(rst1));
			
			conn.close();
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	
	}


}
