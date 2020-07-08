import javax.swing.*;

public class EventsPage {

    public static JScrollPane events()
    {
        String data[][]={ {"101","Amit","670000","1","1","1","1","1"},
                {"102","Jai","780000","1","1","1","1","1"},
                {"101","Sachin","700000","1","1","1","1","1"}};
        String column[]={"borrowID","bookID","isExtended","startBorrowRequest","finalBorrowDate","endBorrowRequest","endBorrowOfficial", "status"};

        JTable jt=new JTable(data,column) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //jt.setBounds(30,40,500,500);
        JScrollPane sp=new JScrollPane(jt);

        return sp;
    }
}