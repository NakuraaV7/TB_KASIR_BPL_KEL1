package tb_kasir;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KelolaUser extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KelolaUser frame = new KelolaUser();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void loadData()
	{
		List<User> users=new ArrayList<User>();
		databaseHandler database = new databaseHandler();
		database.getConnect();
		
		Connection con;
		ResultSet res;
		con = database.conn;

		try{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM user");
	        res = ps.executeQuery();
	        while(res.next())
	        {
	        	User user = new User();
	        	user.setUsername(res.getString("username"));
	        	user.setEmail(res.getString("email"));
	        	users.add(user);
	        }
	        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
	}
	
	/**
	 * Create the frame.
	 */
	public KelolaUser() {
		loadData();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Kelola User", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(199, 0, 286, 223);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 95, 276, 128);
		panel_1.add(table);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 139, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Cari");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				Statement stat;
				ResultSet res;
				
				String username = textField.getText();
				
				try
				{
					databaseHandler database = new databaseHandler();
					con = database.getConnect();
					String query = "SELECT * FROM user WHERE username = '"+username+"'";
					
					PreparedStatement prt = con.prepareStatement(query);
					res = prt.executeQuery();
					
					while(res.next())
					{
						textField_1.setText(res.getString("username"));
						textField_3.setText(res.getString("email"));
						textField_4.setText(res.getString("password"));
					}

					JOptionPane.showMessageDialog(null,"Data ditemukan");
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e.getMessage(),"warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btnNewButton.setBounds(155, 10, 121, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = textField_1.getText();
				String email = textField_3.getText();
				String password = textField_4.getText();
				
				Connection con;
				Statement stat;
				ResultSet res;
				
				if(username.equals("") || email.equals("") || password.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Silahkan isi data dengan lengkap");
				}
				else
				{
					try
					{
						databaseHandler database = new databaseHandler();
						con = database.getConnect();
						String query = "UPDATE user SET username = '"+username+"', email = '"+email+"', password = '"+password+"' WHERE username = '"+username+"'";
						
						PreparedStatement prt = con.prepareStatement(query);
						prt.execute();
						
						JOptionPane.showMessageDialog(null,"Data user berhasil diperbaharui");
						
						textField_1.setText("");
						textField_3.setText("");
						textField_4.setText("");
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,e.getMessage(),"warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnNewButton_1.setBounds(10, 36, 139, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				Statement stat;
				ResultSet res;
				
				String username = textField.getText();
				
				if(username.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Silahkan isi username yang ingin dihapus");
				}
				else
				{
					try
					{
						databaseHandler database = new databaseHandler();
						con = database.getConnect();
						String query = "DELETE FROM user WHERE username = '"+username+"'";
						
						PreparedStatement prt = con.prepareStatement(query);
						prt.execute();
						
						JOptionPane.showMessageDialog(null,"Data user berhasil dihapus");
						
						textField.setText("");
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,e.getMessage(),"warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnNewButton_2.setBounds(155, 36, 121, 23);
		panel_1.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(20, 70, 83, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(119, 70, 46, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setBounds(202, 70, 63, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel label = new JLabel("Username");
		label.setBounds(22, 22, 103, 14);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(22, 65, 65, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(22, 122, 81, 14);
		panel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(22, 40, 150, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(22, 90, 150, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(22, 147, 150, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Tambah");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection con;
				Statement stat;
				ResultSet res;
				
				String username = textField_1.getText();
				String email = textField_3.getText();
				String password = textField_4.getText();

				if(username.equals("") || email.equals("") || password.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Silahkan isi data dengan lengkap");
				}
				else
				{
					try
					{
						databaseHandler database = new databaseHandler();
						con = database.getConnect();
						String query = "INSERT INTO user (username,email,password) VALUES ('"+username+"','"+email+"','"+password+"')";
						
						PreparedStatement prt = con.prepareStatement(query);
						prt.execute();
						
						JOptionPane.showMessageDialog(null,"Data user berhasil ditambahkan");
						
						textField_1.setText("");
						textField_3.setText("");
						textField_4.setText("");
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,e.getMessage(),"warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		});
		btnNewButton_3.setBounds(53, 178, 89, 23);
		panel.add(btnNewButton_3);
		
	}
}
