package tb_kasir;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Transaksi {

	JFrame frame;
	private JComboBox comboBox;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transaksi window = new Transaksi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Transaksi() {
		initialize();
		loadData();
	}
	
	public List<Barang> getAllBarang() throws SQLException
	{
		List<Barang> barangs=new ArrayList<Barang>();
		databaseHandler database = new databaseHandler();
		database.getConnect();
		
		Connection con;
		ResultSet res;
		con = database.conn;

		PreparedStatement ps = con.prepareStatement("SELECT * FROM barang");
        res = ps.executeQuery("select * from barang");
        while(res.next())
        {
        	Barang barang = new Barang();
        	barang.setSku(res.getString("sku"));
        	barang.setNama(res.getString("nama"));
        	barang.setStok(res.getInt("stock"));
        	barang.setHargaBeli(res.getInt("harga_beli"));
        	barang.setHargaJual(res.getInt("harga_jual"));
        	barangs.add(barang);	        	
        }
        
        return barangs;
        
	}
	
	public void loadData()
	{
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

		frame = new JFrame();
		frame.setBounds(100, 100, 667, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("SKU");
		lblNewLabel_1.setBounds(10, 97, 59, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Noresi");
		lblNewLabel_2.setBounds(595, 28, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(450, 25, 135, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Jumlah");
		lblNewLabel_3.setBounds(10, 155, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 180, 135, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		table = new JTable();
		table.setBounds(167, 56, 474, 193);
		frame.getContentPane().add(table);
		
		JButton btnNewButton = new JButton("Tambah");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection con;
				Statement stat;
				ResultSet res;
				
				String sku = (String) comboBox.getSelectedItem().toString();
				String no_resi = textField_1.getText();
				String jumlah = textField_2.getText();
				
				if(no_resi.equals("") || jumlah.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Silahkan isi data dengan lengkap");
				}
				else
				{
					try
					{
						databaseHandler database = new databaseHandler();
						con = database.getConnect();
						String query = "INSERT INTO detail_transaksi (noresi,sku,jumlah) VALUES ('"+no_resi+"','"+sku+"','"+jumlah+"')";
						
						PreparedStatement prt = con.prepareStatement(query);
						prt.execute();
												
						textField_1.setText("");
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,e.getMessage(),"warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setBounds(167, 260, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Hapus");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(298, 260, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Edit");
		btnNewButton_2.setBounds(430, 260, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cari");
		btnNewButton_3.setBounds(552, 260, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JComboBox comboBox = new JComboBox();
		databaseHandler database = new databaseHandler();
		database.getConnect();
		
		Connection con;
		ResultSet res;
		con = database.conn;
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT sku FROM barang");
	        res = ps.executeQuery();
	        while(res.next())
	        {
	        	String item = res.getString("sku");
	    		comboBox.setModel(new DefaultComboBoxModel(new String[] {item}));
	        }	
		}catch(Exception ex){
			
		}
		
		comboBox.setBounds(10, 122, 135, 22);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton_4 = new JButton("Mulai transaksi");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				Statement stat;
				ResultSet res;
				
				String no_resi = textField_1.getText();
				String username = textField.getText();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				String tanggal = dtf.format(now);
				
				
				if(no_resi.equals(""))
				{
					JOptionPane.showMessageDialog(null,"silahkan isi data dengan lengkap");
				}
				else
				{
					try
					{
						databaseHandler database = new databaseHandler();
						con = database.getConnect();
						String query = "INSERT INTO transaksi (noresi,username,tanggal) VALUES ('"+no_resi+"','"+username+"','"+tanggal+"')";
						
						PreparedStatement prt = con.prepareStatement(query);
						prt.execute();
												
						textField_1.setText("");
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,e.getMessage(),"warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnNewButton_4.setBounds(298, 24, 142, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		textField = new JTextField();
		textField.setBounds(107, 25, 79, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nama kasir");
		lblNewLabel.setBounds(10, 28, 87, 14);
		frame.getContentPane().add(lblNewLabel);
	}
}
