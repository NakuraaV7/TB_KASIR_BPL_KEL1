package tb_kasir;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KelolaUser extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
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

	public String get_SHA_512_SecurePassword(String passwordToHash, String salt){
	    String generatedPassword = null;
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-512");
	        md.update(salt.getBytes(StandardCharsets.UTF_8));
	        byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++){
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        generatedPassword = sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return generatedPassword;
	}
	
	/**
	 * Create the frame.
	 */
	public KelolaUser() {
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
		table.setBounds(0, 70, 276, 153);
		panel_1.add(table);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 139, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Cari");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(155, 10, 121, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setBounds(10, 36, 139, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBounds(155, 36, 121, 23);
		panel_1.add(btnNewButton_2);
		
		JLabel label = new JLabel("Username");
		label.setBounds(22, 22, 103, 14);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("Login Terakhir");
		lblNewLabel.setBounds(22, 62, 79, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(22, 98, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(22, 133, 46, 14);
		panel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(22, 40, 150, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(22, 77, 150, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(22, 113, 150, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(22, 147, 150, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Tambah");
		btnNewButton_3.setBounds(53, 178, 89, 23);
		panel.add(btnNewButton_3);
		
	}
}
