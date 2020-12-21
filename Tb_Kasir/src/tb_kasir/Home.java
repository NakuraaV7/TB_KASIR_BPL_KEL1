package tb_kasir;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

public class Home {

	private JFrame frmFormHome;

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
		frmFormHome.setBounds(100, 100, 450, 300);
		frmFormHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFormHome.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Kelola User");
		btnNewButton.setBounds(50, 11, 132, 67);
		frmFormHome.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Pengelolaan Barang");
		btnNewButton_1.setBounds(50, 89, 132, 67);
		frmFormHome.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Restock Barang");
		btnNewButton_2.setBounds(50, 167, 132, 72);
		frmFormHome.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Pengelolaan Transaksi");
		btnNewButton_3.setBounds(235, 11, 141, 67);
		frmFormHome.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Laporan");
		btnNewButton_3_1.setBounds(235, 89, 141, 67);
		frmFormHome.getContentPane().add(btnNewButton_3_1);
		
		JButton button = new JButton("New button");
		button.setBounds(278, 133, 89, 23);
		frmFormHome.getContentPane().add(button);
		
		JButton btnNewButton_3_1_1 = new JButton("Log Out");
		btnNewButton_3_1_1.setBounds(235, 170, 141, 67);
		frmFormHome.getContentPane().add(btnNewButton_3_1_1);
	}

}
