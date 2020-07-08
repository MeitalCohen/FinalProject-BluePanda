import entities.BookStock;
import entities.BorrowedBook;
import entities.User;
import enums.BooksFilter;
import enums.ResponseStatus;
import jtableModel.ManageBooksModel;
import serviceHost.ServiceCommand;
import services.requests.BookLendingRequest;
import services.requests.GetBooksRequest;
import services.responses.BookLendingResponse;
import services.responses.GetBooksResponse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class LibraryBooksPage
{
    private User user;
    private JButton borrowBookBtn;
    private JButton viewRecommendations;
    private ServiceCommand sc;
    private String chosenBookId;

    public LibraryBooksPage(User user) {
        this.user = user;
        sc = ServiceCommand.getInstance();
        chosenBookId = "";
    }


    private JScrollPane libraryBooksTable()
    {
        GetBooksRequest request = new GetBooksRequest(BooksFilter.AvailableOnly);
        GetBooksResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            Vector<BookStock> books = new Vector<>();
            books.addAll(response.getBooks());
            ManageBooksModel manageBooksModel = new ManageBooksModel(books);
            JTable booksTable = new JTable(convert(manageBooksModel.getBooks()), manageBooksModel.getColumns().toArray());

            booksTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    String bookId = booksTable.getValueAt(booksTable.getSelectedRow(), 0).toString();
                    userChoseAvailableBook(bookId);
                }
            });

            JScrollPane sp = new JScrollPane(booksTable);
            return sp;
        }

        return new JScrollPane();
    }




    public JPanel libraryBooksPanel() {
        JScrollPane scrollPane = libraryBooksTable();

        borrowBookBtn = new JButton("Borrow");//creating instance of JButton for Login Button
        borrowBookBtn.setEnabled(false);
        borrowBookBtn.setBounds(400, 100, 100,30); //x axis, y axis, width, height
        borrowBookBtn.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                BookLendingRequest request = new BookLendingRequest(user.getId(), chosenBookId);
                BookLendingResponse response = sc.execute(request);
                if(response.getStatus() != ResponseStatus.OK.errorCode())
                {
                    JOptionPane.showMessageDialog(null,response.getErrorMessage()); //Display Message
                }
                else {
                    JOptionPane.showMessageDialog(null,"Borrowed Successfully"); //Display Message
                    //update list
                }
            }
        });


        viewRecommendations = new JButton("View Recommendations");//creating instance of JButton for Login Button
        viewRecommendations.setEnabled(false);
        viewRecommendations.setBounds(700, 700, 100,30); //x axis, y axis, width, height
        viewRecommendations.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {

            }
        });

        JPanel p = new JPanel();
        p.add(scrollPane);
        p.add(borrowBookBtn);
        p.add(viewRecommendations);

        return p;
    }

    private String [] [] convert(Vector<BookStock> books)
    {
        String [][] stringM = new String[books.size()][5];
        for (int i = 0; i < books.size(); i ++){
            BookStock book = books.get(i);
            String [] booksArray = new String[7];
            booksArray[0] = book.getId();
            booksArray[1] = book.getBookName();
            booksArray[2] = book.getAuthorName();
            booksArray[3] = String.valueOf(book.getQuantity());
            //booksArray[4] = String.valueOf(book.getBarcode());
            booksArray[4] = book.getCategory();
            //booksArray[6] = String.valueOf(book.getBookCode());
            stringM[i] = booksArray;
        }
        return  stringM;
    }

    public void userChoseAvailableBook(String bookId) {
        if (bookId.equals("")) return;

        this.chosenBookId = bookId;

        if (borrowBookBtn != null)
            this.borrowBookBtn.setEnabled(true);

        if(viewRecommendations != null)
            this.viewRecommendations.setEnabled(true);
    }

}
