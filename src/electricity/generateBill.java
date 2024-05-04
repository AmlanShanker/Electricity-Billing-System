package electricity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class generateBill extends JFrame implements ActionListener {
    String meter;
    JButton bill;
    Choice cmonth;
    JTextArea area;
    generateBill(String meter){
        this.meter=meter;
        setSize(500,600);
        setLocation(550,30);
        setLayout(new BorderLayout());
        Panel panel = new Panel();
        JLabel heading = new JLabel("Generate Bill");
        JLabel meternumber = new JLabel(meter);

        cmonth = new Choice();
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");

        area = new JTextArea(50,15);
        area.setText("\n\n\t--------Click on the ---------\n\t Generate Bill Button to get \n\t the bill of the selected month");
        area.setFont(new Font("Senserif",Font.ITALIC,19));
        JScrollPane pane = new JScrollPane(area);
        bill = new JButton("Generate Bill");
        bill.addActionListener(this);
        panel.add(heading);
        panel.add(meternumber);
        panel.add(cmonth);
        add(panel,"North");
        add(pane,"Center");
        add(bill,"South");
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            conn c = new conn();
            String month = cmonth.getSelectedItem();
            area.setText("\tAmlan Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH\n\tOF "+month+",2024\n\n\n");
            ResultSet rs=c.s.executeQuery("select * from customer where meter_no='"+meter+"'");
            if(rs.next()){
                area.append("\n    Customer Name: "+rs.getString("name"));
                area.append("\n    Meter Number: "+rs.getString("meter_no"));
                area.append("\n    Address: "+rs.getString("address"));
                area.append("\n    City: "+rs.getString("city"));
                area.append("\n    State: "+rs.getString("state"));
                area.append("\n    E-mail: "+rs.getString("mail"));
                area.append("\n    Phone no: "+rs.getString("phone"));
                area.append("\n-----------------------------------------------");
                area.append("\n");
            }

            rs=c.s.executeQuery("select * from meter_info where meter_no='"+meter+"'");
            if(rs.next()){
                area.append("\n    Meter Location: "+rs.getString("meter_location"));
                area.append("\n    Meter Type: "+rs.getString("meter_type"));
                area.append("\n    Phase Code: "+rs.getString("phase_code"));
                area.append("\n    Bill Type: "+rs.getString("bill_type"));
                area.append("\n    Days: "+rs.getString("days"));
                area.append("\n-----------------------------------------------");
                area.append("\n");

            }

            rs=c.s.executeQuery("select * from tax");
            if(rs.next()){

                area.append("\n    Cost Per Unit: "+rs.getString("cost_per_unit"));
                area.append("\n    Meter Rent: "+rs.getString("meter_rent"));
                area.append("\n    Service Charge: "+rs.getString("service_charge"));
                area.append("\n    Service Tax: "+rs.getString("service_tax"));
                area.append("\n    Swacch Bharat Tax: "+rs.getString("swacch_bharat_cess"));
                area.append("\n    Fixed Tax: "+rs.getString("fixed_tax"));
                area.append("\n-----------------------------------------------");
                area.append("\n");

            }

            rs=c.s.executeQuery("select * from bill where meter_no='"+meter+"' AND month='"+month+"'");
            if(rs.next()){

                area.append("\n    Current Month: "+rs.getString("month"));
                area.append("\n    Units Consumed: "+rs.getString("units"));
                area.append("\n    Total Charges: "+rs.getString("totalbill"));
                area.append("\n-----------------------------------------------");
                area.append("\n    Total Payable: "+rs.getString("totalbill"));

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[]args){

        new generateBill("");
    }
}
