package tb_kasir;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import tb_kasir.databaseHandler;
import tb_kasir.KelolaUser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPasswordField;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	public String usernames;
	static Login frame = new Login();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"gagal");
				}
				
				try {
					databaseHandler database = new databaseHandler();
					database.getConnect();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"gagal");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(74, 97, 89, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(200, 94, 142, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(74, 136, 76, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				Statement stat;
				ResultSet res;
				
				String username = textField.getText();
				String password = new String(passwordField.getPassword());
				Boolean result = false;
				databaseHandler database = new databaseHandler();
				Home home = new Home();
				
				con = database.conn;
				stat = database.stat;

				
				if(username.equals("") || password.equals(""))
				{
					JOptionPane.showMessageDialog(null,"silahkan isi username dan password","warning", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					String query = "select * from user where username = '"+username+"' and password = '"+password+"'";
					try {
						res = stat.executeQuery(query);
						if(res.next())
						{
							if(username.equals(res.getString("username")) && password.equals(res.getString("password")))
							{
								JOptionPane.showMessageDialog(null,"Selamat datang");
								home.frmFormHome.setVisible(true);
								dispose();
								home.frmFormHome.setLocationRelativeTo(null);
								frame.setVisible(false);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null,"username atau password salah","warning", JOptionPane.WARNING_MESSAGE);
						}
					}
					catch(Exception ex)
					{
						
					}
				}
				
			}
		});
		btnNewButton.setBounds(189, 184, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("LOGIN APLIKASI KASIR");
		lblNewLabel_2.setBounds(163, 38, 158, 14);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 133, 142, 20);
		contentPane.add(passwordField);
	}

}