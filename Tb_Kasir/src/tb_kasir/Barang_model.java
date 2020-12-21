package tb_kasir;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import tb_kasir.Barang;

public class Barang_model extends AbstractTableModel{

	List<Barang> barangs = new ArrayList<Barang>();
	
	private final String HEADER[]={"sku","nama","stok","harga_beli","harga_jual"};
	
	 public Barang_model(List<Barang> barangs){
		 this.barangs=barangs;
	 }
	 
	 public void saveBarang(Barang barang){
        barangs.add(barang);
        fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
	 }
	 
	 public void updateBarang(int index, Barang barang){
        barangs.set(index, barang);
        fireTableRowsUpdated(index, index);
	 }
	 
	 public void deleteBarang(int index){
        barangs.remove(index);
        fireTableRowsDeleted(index, index);
	 }
	 
	 public Barang getBarang(int index){
        return barangs.get(index);
	 }
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return HEADER.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return barangs.size();
	}
	
	public String getColumnName(int column)
	{
		return HEADER[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Barang barang = barangs.get(rowIndex);
		switch(columnIndex) {
			case 0: return barang.getSku();
			case 1: return barang.getNama();
			case 2: return barang.getStok();
			case 3: return barang.getHargaBeli();
			case 4: return barang.getHargaJual();
			default: return null;
		}
	}

}
