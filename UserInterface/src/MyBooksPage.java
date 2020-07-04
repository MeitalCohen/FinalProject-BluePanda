import javax.swing.*;

public class MyBooksPage {

    public static void myBooks()
    {
        JFrame f = new JFrame("My Books");//creating instance of JFrame

        String data[][]={ {"101","Amit","670000","1","1","1","1","1"},
                {"102","Jai","780000","1","1","1","1","1"},
                {"101","Sachin","700000","1","1","1","1","1"}};
        String column[]={"borrowID","bookID","isExtended","startBorrowRequest","finalBorrowDate","endBorrowRequest","endBorrowOfficial", "status"};

        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,500,500);
        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);

        // f.setSize(700,600);//400 width and 500 height
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //f.setUndecorated(true);
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);
    }
}
