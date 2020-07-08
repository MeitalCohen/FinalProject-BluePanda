import entities.BookStock;
import entities.User;
import enums.BooksFilter;
import enums.ResponseStatus;
import jtableModel.ManageBooksModel;
import serviceHost.ServiceCommand;
import services.requests.GetBooksRequest;
import services.responses.GetBooksResponse;

import javax.swing.*;
import java.awt.*;
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
            return booksTable;
        }
        return new JTable();
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

        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        table.getTableHeader().setReorderingAllowed(false);

        f.add(table.getTableHeader(), BorderLayout.NORTH);
        f.add(table, BorderLayout.CENTER);
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
