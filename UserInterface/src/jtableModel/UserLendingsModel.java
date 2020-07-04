package jtableModel;

import entities.UserLending;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

public class UserLendingsModel extends AbstractTableModel {
    private final static String[] columnNames = {"Book Name", "Author Name", "Category", "Is Extended", "Start Borrow", "Expiration Data", "Status"};
    private Vector<UserLending> userLending;

    private UserLendingsModel() {
        userLending = new Vector<UserLending>();
    }

    public Vector<String> getColumns()
    {
        return  new Vector<String>(Arrays.asList(columnNames));
    }

    public Vector<UserLending> getUserLending()
    {
        return this.getUserLending();
    }

    public UserLendingsModel(Vector<UserLending> data) {
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
            case 0: return userLending.get(rowIndex).getBookName();
            case 1: return userLending.get(rowIndex).getAuthorName();
            case 2: return userLending.get(rowIndex).getCategory();
            case 3: return userLending.get(rowIndex).isExtended();
            case 4: return userLending.get(rowIndex).getStartBorrowRequest();
            case 5: return userLending.get(rowIndex).getFinalBorrowDate();
            case 6: return userLending.get(rowIndex).getStatus();

        }
        return null;
    }
}