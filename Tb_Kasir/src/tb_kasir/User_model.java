package tb_kasir;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import tb_kasir.Barang;

public class User_model extends AbstractTableModel{

	List<User> users = new ArrayList<User>();
	
	private final String HEADER[]={"username","email","password"};
	
	 public User_model(List<User> users){
		 this.users=users;
	 }
	 
	 public void saveUser(User user){
        users.add(user);
        fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
	 }
	 
	 public void updateUser(int index, User user){
        users.set(index, user);
        fireTableRowsUpdated(index, index);
	 }
	 
	 public void deleteUser(int index){
        users.remove(index);
        fireTableRowsDeleted(index, index);
	 }
	 
	 public User getUser(int index){
        return users.get(index);
	 }
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return HEADER.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return users.size();
	}
	
	public String getColumnName(int column)
	{
		return HEADER[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		User user = users.get(rowIndex);
		switch(columnIndex) {
			case 0: return user.getUsername();
			case 1: return user.getEmail();
			case 2: return user.getPassword();
			default: return null;
		}
	}

}
