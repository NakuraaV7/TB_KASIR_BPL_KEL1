package tb_kasir;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Laporan {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Laporan window = new Laporan();
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
	public Laporan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Laporan Penjualan");
		lblNewLabel.setBounds(10, 11, 98, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Laporan Keuntungan");
		lblNewLabel_1.setBounds(316, 11, 108, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}

}
