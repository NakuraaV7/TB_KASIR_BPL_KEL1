package tb_kasir;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JList;

public class Laporan {

	JFrame frame;
	private JTable table;
	private JTable table_1;

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
		
		table = new JTable();
		table.setBounds(10, 75, 175, 158);
		frame.getContentPane().add(table);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 36, 98, 22);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(326, 36, 84, 22);
		frame.getContentPane().add(comboBox_1);
		
		table_1 = new JTable();
		table_1.setBounds(237, 75, 187, 158);
		frame.getContentPane().add(table_1);
	}
}
