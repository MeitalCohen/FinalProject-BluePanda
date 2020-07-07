package jtableModel;

import entities.BorrowedBook;
import entities.UserLending;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.Vector;

public class ManageBorrowsModel extends AbstractTableModel {
    private final static String[] columnNames = {"User ID", "User Name", "Book ID", "Book Name", "Is Extended", "Start Borrow", "Expiration Date", "Status"};
    private Vector<UserLending> userLending;

    private ManageBorrowsModel() {
        userLending = new Vector<UserLending>();
    }

    public Vector<String> getColumns()
    {
        return  new Vector<String>(Arrays.asList(columnNames));
    }

    public Vector<UserLending> getUserLending()
    {
        return this.userLending;
    }

    public ManageBorrowsModel(Vector<UserLending> data) {
        userLending = data;
        fireTableRowsInserted(userLending.size()-1, userLending.size()-1);
    }

    public void addElement(UserLending e) {
        // Adds the element in the last position in the list
        userLending.add(e);
        fireTableRowsInserted(userLending.size()-1, userLending.size()-1);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return userLending.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return userLending.get(rowIndex).getUserId();
            case 1: return userLending.get(rowIndex).getUserName();
            case 2: return userLending.get(rowIndex).getBookId();
            case 3: return userLending.get(rowIndex).getBookName();
            case 4: return userLending.get(rowIndex).isExtended();
            case 5: return userLending.get(rowIndex).getStartBorrowRequest();
            case 6: return userLending.get(rowIndex).getFinalBorrowDate();
            case 7: return userLending.get(rowIndex).getStatus();

        }
        return null;
    }
}
