package jtableModel;

import entities.BorrowedBook;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.Vector;

public class ManageBorrowsModel extends AbstractTableModel {
    private final static String[] columnNames = {"User ID", "User Name", "Book ID", "Book Name", "Is Extended", "Start Borrow", "Expiration Date", "Status"};
    private Vector<BorrowedBook> borrowedBooks;

    private ManageBorrowsModel() {
        borrowedBooks = new Vector<BorrowedBook>();
    }

    public Vector<String> getColumns()
    {
        return  new Vector<String>(Arrays.asList(columnNames));
    }

    public Vector<BorrowedBook> getBorrowedBooks()
    {
        return this.borrowedBooks;
    }

    public ManageBorrowsModel(Vector<BorrowedBook> data) {
        borrowedBooks = data;
        fireTableRowsInserted(borrowedBooks.size()-1, borrowedBooks.size()-1);
    }

    public void addElement(BorrowedBook e) {
        // Adds the element in the last position in the list
        borrowedBooks.add(e);
        fireTableRowsInserted(borrowedBooks.size()-1, borrowedBooks.size()-1);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return borrowedBooks.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return borrowedBooks.get(rowIndex).getUserID();
            //case 1: return borrowedBooks.get(rowIndex).getB();
            case 2: return borrowedBooks.get(rowIndex).getBookID();
            //case 3: return borrowedBooks.get(rowIndex).getB();
            case 4: return borrowedBooks.get(rowIndex).isExtended();
            case 5: return borrowedBooks.get(rowIndex).getStartBorrowRequest();
            case 6: return borrowedBooks.get(rowIndex).getFinalBorrowDate();
            case 7: return borrowedBooks.get(rowIndex).getStatus();

        }
        return null;
    }
}
