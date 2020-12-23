package tb_kasir;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home {

	JFrame frmFormHome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frmFormHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFormHome = new JFrame();
		frmFormHome.setTitle("HomePage");
		frmFormHome.setBounds(100, 100, 534, 300);
		frmFormHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFormHome.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Kelola User");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(50, 11, 163, 67);
		frmFormHome.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Pengelolaan Barang");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KelolaBarang kelolabarang = new KelolaBarang();
				kelolabarang.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(50, 89, 163, 67);
		frmFormHome.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Restock Barang");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestokBarang restokbarang = new RestokBarang();
				restokbarang.frame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(50, 167, 163, 72);
		frmFormHome.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Pengelolaan Transaksi");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Transaksi transaksi = new Transaksi();
				transaksi.frame.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(276, 11, 182, 67);
		frmFormHome.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Laporan");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Laporan laporan = new Laporan();
				laporan.frame.setVisible(true);
			}
		});
		btnNewButton_3_1.setBounds(276, 89, 182, 67);
		frmFormHome.getContentPane().add(btnNewButton_3_1);
		
		JButton btnNewButton_3_1_1 = new JButton("Log Out");
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_3_1_1.setBounds(276, 170, 182, 67);
		frmFormHome.getContentPane().add(btnNewButton_3_1_1);
	}

}
