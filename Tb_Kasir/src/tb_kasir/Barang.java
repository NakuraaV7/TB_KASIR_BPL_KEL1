package tb_kasir;

public class Barang {
	private String sku;
	private String nama;
	private int stok;
	private int harga_jual;
	private int harga_beli;
	
	public String getSku()
	{
		return sku;
	}
	
	public void setSku(String sku)
	{
		this.sku = sku;
	}
	
	public String getNama()
	{
		return nama;
	}
	
	public void setNama(String nama)
	{
		this.nama = nama;
	}
	
	public int getStok()
	{
		return stok;
	}
	
	public void setStok(int stok)
	{
		this.stok = stok;
	}
	
	public int getHargaBeli()
	{
		return harga_beli;
	}
	
	public void setHargaBeli(int harga_beli)
	{
		this.harga_beli = harga_beli;
	}
	
	public int getHargaJual()
	{
		return harga_jual;
	}
	
	public void setHargaJual(int harga_jual)
	{
		this.harga_jual = harga_jual;
	}
}
