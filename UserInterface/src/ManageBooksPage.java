import entities.BookStock;
import entities.BorrowedBook;
import entities.User;
import enums.BooksFilter;
import enums.ResponseStatus;
import jtableModel.ManageBooksModel;
import jtableModel.ManageUsersModel;
import serviceHost.ServiceCommand;
import services.requests.ExtendLendingRequest;
import services.requests.GetBooksRequest;
import services.requests.GetUsersRequest;
import services.requests.ReturnBookRequest;
import services.responses.ExtendLendingResponse;
import services.responses.GetBooksResponse;
import services.responses.GetUsersResponse;
import services.responses.ReturnBookResponse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ManageBooksPage {
    private User user;
    private ServiceCommand sc;
    private JButton addNewOrderBtn;

    public ManageBooksPage(User user)
    {
        this.user = user;
        sc = ServiceCommand.getInstance();
    }

    private JScrollPane manageBooksTable() {

        GetBooksRequest request = new GetBooksRequest(BooksFilter.All);
        GetBooksResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            ManageBooksModel manageBooksModel = new ManageBooksModel(response.getBooks());
            JTable booksTable = new JTable(convert(manageBooksModel.getBooks()), manageBooksModel.getColumns().toArray());
            JScrollPane sp = new JScrollPane(booksTable);
            return sp;
        }
        return new JScrollPane();
    }

    public JPanel manageBooksPanel()
    {
        JScrollPane scrollPane = manageBooksTable();
        addNewOrderBtn = new JButton("Add new order");//creating instance of JButton for Login Button
        addNewOrderBtn.setBounds(400, 100, 100,30); //x axis, y axis, width, height
        addNewOrderBtn.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                AddOrder.AddOrder(user.getId());

                    //update list
                    //refreshTable();
            }
        });

        JPanel p = new JPanel();
        p.add(scrollPane);
        p.add(addNewOrderBtn);
        return p;
    }



    private static String [] [] convert(Vector<BookStock> books)
    {
        String [][] stringM = new String[books.size()][5];
        for (int i = 0; i < books.size(); i ++){
            BookStock book = books.get(i);
            String [] booksArray = new String[5];
            booksArray[0] = book.getId();
            booksArray[1] = book.getBookName();
            booksArray[2] = book.getAuthorName();
            booksArray[3] = String.valueOf(book.getQuantity());
            booksArray[4] = book.getCategory();
            stringM[i] = booksArray;
        }
        return  stringM;
    }
}
