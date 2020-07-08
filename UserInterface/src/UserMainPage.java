import entities.BookStock;
import entities.User;
import enums.BooksFilter;
import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.GetBooksRequest;
import services.responses.GetBooksResponse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserMainPage {

    public static JScrollPane userMain(User user) {
        //JFrame f = new JFrame("My Books"); //creating instance of JFrame

        GetBooksRequest request = new GetBooksRequest(BooksFilter.AvailableOnly);

        ServiceCommand sc = ServiceCommand.getInstance();
        GetBooksResponse response = sc.execute(request);
        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {

            if (response.getBooks() == null || response.getBooks().isEmpty()) {
                JOptionPane.showMessageDialog(null, "There are not available Books, Sorry :(");
            } else {

                JButton borrowBook_btn = new JButton("Borrow Book");
                borrowBook_btn.setBounds(142, 500, 100, 25);
                borrowBook_btn.setEnabled(false);

                String column[] = {"Book Name", "Author Name", "Category", "Quantity"};

                String[][] data = convert(response.getBooks());
                JTable lendingsTable = new JTable(data, column) {
                    @Override
                    public boolean isCellEditable(int row, int col) {
                        return false;
                    }
                };

                lendingsTable.setBounds(30, 15, 800, 300);

                lendingsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {

                        String bookId = lendingsTable.getValueAt(lendingsTable.getSelectedRow(), 0).toString();
                        borrowBook_btn.setEnabled(true);
                    }
                });

                borrowBook_btn.addActionListener(new ActionListener() {  //Perform action

                    public void actionPerformed(ActionEvent e) {


                    }
                });

                JScrollPane sp = new JScrollPane(lendingsTable);
                sp.add(borrowBook_btn);
                return sp;
            }
        }
        return new JScrollPane();
    }


    private static String [] [] convert(List<BookStock> books)
    {
        String [][] stringM = new String[books.size()][5];

        if (books == null)
            return stringM;

        for (int i = 0; i < books.size(); i ++){
            BookStock book = books.get(i);
            //Category enumInstance = Category.values()[book.getCategory()];
            String [] bookArray = new String[5];
            bookArray[0] = book.getId();
            bookArray[1] = book.getBookName();
            bookArray[2] = book.getAuthorName();
            //bookArray[2] = enumInstance.name();
            bookArray[3] = String.valueOf(book.getCategory());
            bookArray[4] = String.valueOf(book.getQuantity());

            stringM[i] = bookArray;
        }
        return  stringM;
    }


}
