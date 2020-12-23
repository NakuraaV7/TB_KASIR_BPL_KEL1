package tb_kasir;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import tb_kasir.databaseHandler;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class KelolaBarang {

	JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KelolaBarang window = new KelolaBarang();
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
	public KelolaBarang() {
		initialize();
		loadData();
	}

	public void loadData()
	{
		List<Barang> barangs=new ArrayList<Barang>();
		databaseHandler database = new databaseHandler();
		database.getConnect();
		
		Connection con;
		ResultSet res;
		con = database.conn;

		try{
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
            Barang_model tabelmodel = new Barang_model(barangs);
            table.setModel(tabelmodel);
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 584, 348);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Tambah");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection con;
				Statement stat;
				ResultSet res;
				databaseHandler database = new databaseHandler();
				
				con = database.conn;
				stat = database.stat;
				
				String sku = textField.getText();
				String nama = textField_1.getText();
				String stok = textField_2.getText();
				String harga_beli = textField_3.getText();
				String harga_jual = textField_4.getText();

				if(sku.equals("") || nama.equals("") || stok.equals("") || harga_beli.equals("") || harga_jual.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Silahkan isi data dengan lengkap");
				}
				else
				{
					try
					{
						String query = "INSERT INTO barang (sku,nama,stock,harga_beli,harga_jual) VALUES ('"+sku+"','"+nama+"','"+stok+"','"+harga_beli+"','"+harga_jual+"')";
						stat.execute(query);
						JOptionPane.showMessageDialog(null,"Data berhasil ditambahkan");
						
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
						loadData();
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,e.getMessage(),"warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		});
		btnNewButton.setBounds(370, 275, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Hapus");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				Statement stat;
				ResultSet res;
				String sku = textField.getText();
				databaseHandler database = new databaseHandler();
				
				con = database.conn;
				stat = database.stat;
				
				try
				{
					String query = "DELETE FROM barang WHERE sku = '"+sku+"'";
					stat.execute(query);
					JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
					loadData();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Terjadi kesalahan silahkan lakukan input data ulang");
				}
			}
		});
		btnNewButton_1.setBounds(271, 275, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				Statement stat;
				ResultSet res;
				
				String sku = textField.getText();
				String nama = textField_1.getText();
				String stok = textField_2.getText();
				String harga_beli = textField_3.getText();
				String harga_jual = textField_4.getText();

				try
				{
					databaseHandler database = new databaseHandler();
					con = database.getConnect();
					String query = "UPDATE barang SET sku = '"+sku+"', nama = '"+nama+"' , stock = '"+stok+"', harga_jual = '"+harga_jual+"', harga_beli = '"+harga_beli+"' WHERE sku = '"+sku+"' ";
					
					PreparedStatement prt = con.prepareStatement(query);
					prt.execute();
					
					JOptionPane.showMessageDialog(null,"Data barang berhasil diperbaharui");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");

					loadData();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e.getMessage(),"warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton_2.setBounds(172, 275, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cari");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				Statement stat;
				ResultSet res;
				
				String cari = textField_5.getText();
				
				try
				{
					databaseHandler database = new databaseHandler();
					con = database.getConnect();
					String query = "SELECT * FROM barang WHERE sku = '"+cari+"'";
					
					PreparedStatement prt = con.prepareStatement(query);
					res = prt.executeQuery();
					
					while(res.next())
					{
						JOptionPane.showMessageDialog(null,"Data ditemukan");
						textField.setText(res.getString("sku"));
						textField_1.setText(res.getString("nama"));
						textField_2.setText(res.getString("stock"));
						textField_3.setText(res.getString("harga_beli"));
						textField_4.setText(res.getString("harga_jual"));
					}
					
					textField_5.setText("");
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e.getMessage(),"warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton_3.setBounds(466, 10, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"sku", "nama", "stok", "harga_beli", "harga_jual"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(171, 72, 387, 192);
		frame.getContentPane().add(table);
		
		JLabel lblNewLabel = new JLabel("Pengelolaan Data Master Barang");
		lblNewLabel.setBounds(25, 2, 220, 23);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(25, 58, 122, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("SKU");
		lblNewLabel_1.setBounds(25, 38, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(25, 105, 122, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nama");
		lblNewLabel_2.setBounds(25, 89, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(26, 153, 121, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Stock");
		lblNewLabel_3.setBounds(25, 136, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(25, 205, 122, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Harga Beli");
		lblNewLabel_4.setBounds(25, 184, 74, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(25, 246, 122, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Harga Jual");
		lblNewLabel_5.setBounds(25, 229, 74, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(370, 11, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("SKU");
		lblNewLabel_6.setBounds(183, 47, 70, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Nama");
		lblNewLabel_7.setBounds(256, 47, 46, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Stock");
		lblNewLabel_8.setBounds(333, 47, 46, 14);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Harga Beli");
		lblNewLabel_9.setBounds(400, 47, 76, 14);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Harga Jual");
		lblNewLabel_10.setBounds(488, 47, 67, 14);
		frame.getContentPane().add(lblNewLabel_10);
	}
}
