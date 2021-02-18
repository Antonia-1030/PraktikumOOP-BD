package proekt;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyFrame extends JFrame{
	Connection conn=null;
	PreparedStatement state=null;
	ResultSet result;
	
	JPanel upPanel=new JPanel();
	JPanel midPanel=new JPanel();
	JPanel dounPanel=new JPanel();

	//up Panel
	JLabel fnameL=new JLabel("  Name:");
	JLabel lnameL=new JLabel("  Family name:");
	JLabel adresL=new JLabel("  Adress:");
	JLabel numberL=new JLabel("  Phone:");
	
	JTextField fnameTF=new JTextField();
	JTextField lnameTF=new JTextField();
	JTextField adresTF=new JTextField();
	JTextField numberTF=new JTextField();
	
	//middle panel
	JButton addBt=new JButton("Add");
	JButton deleteBt=new JButton("Del");
	JButton editBt=new JButton("Redact");
	
	//bottom panel
	JTable table=new JTable();
	JScrollPane myScroll=new JScrollPane(table);
	
	public MyFrame() {
		this.setSize(400, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(3,1));
		
		upPanel.setLayout(new GridLayout(5,2));
		
		upPanel.add(fnameL);
		upPanel.add(fnameTF);
		upPanel.add(lnameL);
		upPanel.add(lnameTF);
		upPanel.add(adresL);
		upPanel.add(adresTF);
		upPanel.add(numberL);
		upPanel.add(numberTF);
		this.add(upPanel);
		
		midPanel.add(addBt);
		midPanel.add(deleteBt);
		midPanel.add(editBt);
		this.add(midPanel);
		
		myScroll.setPreferredSize(new Dimension(350, 150));	
		dounPanel.add(myScroll);
		this.add(dounPanel);
		refreshTable("OWNER");
		
		
		addBt.addActionListener(new AddAction());
		
		this.setVisible(true);
	}
	
	public void refreshTable(String mytable) {
		conn=ConnectDB.getConnection();
		
		try {
			state=conn.prepareStatement("select * from "+mytable);
			result=state.executeQuery();
			table.setModel(new Model(result));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class AddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn=ConnectDB.getConnection();
			String sql="insert into OWNER values(null, ?,?,?,?)";
			
			try {
				state=conn.prepareStatement(sql);
				state.setString(1, fnameTF.getText());
				state.setString(2, lnameTF.getText());
				state.setString(3, adresTF.getText());
				state.setString(4, numberTF.getText());
				
				state.execute();
				refreshTable("OWNER");
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
}
