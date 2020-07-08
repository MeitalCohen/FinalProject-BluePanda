import entities.BookStock;
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
import java.awt.*;
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

    private JTable libraryBooksTable()
    {
        GetBooksRequest request = new GetBooksRequest(BooksFilter.AvailableOnly);
        GetBooksResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            Vector<BookStock> books = new Vector<>();
            books.addAll(response.getBooks());
            ManageBooksModel manageBooksModel = new ManageBooksModel(books);
            JTable booksTable = new JTable(convert(manageBooksModel.getBooks()), manageBooksModel.getColumns().toArray()) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

            booksTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    String bookId = booksTable.getValueAt(booksTable.getSelectedRow(), 0).toString();
                    userChoseAvailableBook(bookId);
                }
            });

            return booksTable;
        }
        return new JTable();
    }

    public JFrame libraryBooksPanel() {

        JFrame f = new JFrame();

        final JTable table = libraryBooksTable();
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel topBtnPnl = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        borrowBookBtn = new JButton("Borrow");
        borrowBookBtn.setEnabled(false);

        viewRecommendations = new JButton("View Recommendations");
        viewRecommendations.setEnabled(false);

        topBtnPnl.add(new JButton("Select All"));
        bottombtnPnl.add(borrowBookBtn);
        bottombtnPnl.add(viewRecommendations);

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

        viewRecommendations.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnPnl.add(topBtnPnl, BorderLayout.NORTH);
        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        table.getTableHeader().setReorderingAllowed(false);

        f.add(table.getTableHeader(), BorderLayout.NORTH);
        f.add(table, BorderLayout.CENTER);
        f.add(btnPnl, BorderLayout.SOUTH);

        f.setPreferredSize(new Dimension(1100,600));
        f.setTitle("Library Book Stock");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(false);
        return f;
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
