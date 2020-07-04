import javax.swing.*;

public class LibraryBooksPage
{
    public static void libraryBooks()
    {
        JFrame f = new JFrame("Library");//creating instance of JFrame

        String data[][]={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};
        String column[]={"ID","NAME","SALARY"};

        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,400,500);
        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);

        f.setSize(1100,600);
        //f.setUndecorated(true);
        // f.setSize(500,500);//400 width and 500 height
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);
    }
}
