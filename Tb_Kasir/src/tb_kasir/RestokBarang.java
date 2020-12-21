package tb_kasir;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import tb_kasir.databaseHandler;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RestokBarang {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestokBarang window = new RestokBarang();
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
	public RestokBarang() {
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
	        res = ps.executeQuery();
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Tambah Barang");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection con;
				Statement stat = null;
				ResultSet res;
				
				String sku = textField_1.getText();
				String stok = textField.getText();
				
				
				if(sku.equals("") || stok.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Silahkan isi data dengan lengkap");
				}
				else
				{
					try
					{
						String query = "UPDATE barang SET stock = '"+stok+"' WHERE sku = '"+sku+"'";
						databaseHandler database = new databaseHandler();
						con = database.getConnect();
						PreparedStatement prt = con.prepareStatement(query);
						prt.execute();
						
						textField.setText("");
						textField_1.setText("");
						JOptionPane.showMessageDialog(null,"Berhasil mengupdate stok barang");
						loadData();
					}
					catch(SQLException ex)
					{
						JOptionPane.showMessageDialog(null,"Terjadi kesalahan, silahkan lakukan input ulang");
					}					
				}
				
			}
		});
		btnNewButton.setBounds(39, 174, 117, 23);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(39, 128, 117, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Restok Barang");
		lblNewLabel.setBounds(10, 11, 117, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Jumlah Barang");
		lblNewLabel_1.setBounds(39, 103, 84, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(37, 73, 119, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("SKU Barang");
		lblNewLabel_2.setBounds(39, 48, 117, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		table = new JTable();
		table.setBounds(202, 49, 194, 159);
		frame.getContentPane().add(table);
	}

}
