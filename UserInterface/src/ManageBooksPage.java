import entities.BookStock;
import entities.User;
import enums.BooksFilter;
import enums.ResponseStatus;
import jtableModel.ManageBooksModel;
import serviceHost.ServiceCommand;
import services.requests.GetBooksRequest;
import services.responses.GetBooksResponse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ManageBooksPage {
    private User user;
    private ServiceCommand sc;
    private JButton addNewOrderBtn;
    private JButton removeBookQuantity;
    private BookStock chosenBook;

    public ManageBooksPage(User user)
    {
        this.user = user;
        sc = ServiceCommand.getInstance();
    }

    private JTable manageBooksTable() {

        GetBooksRequest request = new GetBooksRequest(BooksFilter.All);
        GetBooksResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            ManageBooksModel manageBooksModel = new ManageBooksModel(response.getBooks());
            JTable booksTable = new JTable(convert(manageBooksModel.getBooks()), manageBooksModel.getColumns().toArray()) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            booksTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    int selectedRow = booksTable.getSelectedRow();
                    BookStock book = manageBooksModel.getBooks().get(selectedRow);
                    managerChoseUser(book);
                }
            });
            return booksTable;
        }
        return new JTable();
    }

    private void managerChoseUser(BookStock book)
    {
        if (book == null) return;
        this.chosenBook = book;
    }
    public JFrame manageBooksPanel()
    {
        JFrame f = new JFrame();

        final JTable table = manageBooksTable();
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        addNewOrderBtn = new JButton("Add new order");

        bottombtnPnl.add(addNewOrderBtn);

        addNewOrderBtn.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                AddOrder.AddOrder(user.getId());

                //update list
                //refreshTable();
            }
        });

        removeBookQuantity = new JButton("Remove");
        bottombtnPnl.add(removeBookQuantity);
        removeBookQuantity.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                AddOrder.AddOrder(user.getId());
                RemoveBookPage.RemoveBook(user.getId(), chosenBook.getId(), chosenBook.getBookName(), chosenBook.getQuantity(), chosenBook.getAuthorName());
                //update list
                //refreshTable();
            }
        });

        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        // Force the scrollbars to always be displayed
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        f.add(table.getTableHeader(), BorderLayout.NORTH);
        f.add(scrollPane, BorderLayout.CENTER);
        f.add(btnPnl, BorderLayout.SOUTH);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(false);
        return f;
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
