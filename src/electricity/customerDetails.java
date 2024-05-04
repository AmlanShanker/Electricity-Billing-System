package electricity;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;
public class customerDetails extends JFrame implements ActionListener {
    Choice meternumber,cmonth;
    JTable table;
    JButton search,print;
    customerDetails(){
        super("Customer Details");
        setSize(1100,400);
        setLocation(100,150);

        table = new JTable();
        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        add(sp);

        print = new JButton("Print");
        print.addActionListener(this);
        add(print,"South");

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){

            try{
                table.print();
            }catch(Exception e){
                e.printStackTrace();
            }

    }

    public static void main(String[]args){

        new customerDetails();
    }
}
