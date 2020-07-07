import entities.BookStock;
import entities.User;
import enums.BooksFilter;
import enums.ResponseStatus;
import jtableModel.ManageBooksModel;
import jtableModel.ManageUsersModel;
import serviceHost.ServiceCommand;
import services.requests.GetBooksRequest;
import services.requests.GetUsersRequest;
import services.responses.GetBooksResponse;
import services.responses.GetUsersResponse;

import javax.swing.*;
import java.util.Vector;

public class ManageBooksPage {

    public static JScrollPane manageBooks(User user) {

        GetBooksRequest request = new GetBooksRequest(BooksFilter.All);
        ServiceCommand sc = ServiceCommand.getInstance();
        GetBooksResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            ManageBooksModel manageBooksModel = new ManageBooksModel();
            JTable booksTable = new JTable(convert(manageBooksModel.getBooks()), manageBooksModel.getColumns().toArray());
            JScrollPane sp = new JScrollPane(booksTable);
            return sp;
        }
        return new JScrollPane();
    }

    private static String [] [] convert(Vector<BookStock> books)
    {
        String [][] stringM = new String[books.size()][10];
        for (int i = 0; i < books.size(); i ++){
            BookStock book = books.get(i);
            String [] booksArray = new String[10];
            booksArray[0] = book.getId();
            booksArray[1] = book.getBookName();
            booksArray[2] = book.getAuthorName();
            booksArray[3] = String.valueOf(book.getQuantity());
            booksArray[4] = String.valueOf(book.getBarcode());
            booksArray[5] = book.getCategory();
            booksArray[6] = String.valueOf(book.getBookCode());
            stringM[i] = booksArray;
        }
        return  stringM;
    }
}
