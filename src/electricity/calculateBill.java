package electricity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
public class calculateBill extends JFrame implements ActionListener {
    JLabel lblname,labeladress;
    JTextField tfname,tfaddress,tfstate,tfunits,tfmail,tfphone,tfstatus;
    JButton next ,cancel;
    Choice meternumber,cmonth;
    calculateBill(){
        setSize(700,500);
        setLocation(400,150);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(100,10,400,35);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(100,80,100,20);
        p.add(lblmeternumber);

        meternumber = new Choice();
        try{
          conn c = new conn();
          ResultSet rs =  c.s.executeQuery("select * from customer");
          while(rs.next()){
              meternumber.add(rs.getString("meter_no"));
          }
        }catch(Exception e){
            e.printStackTrace();
        }
        meternumber.setBounds(240,80,200,20);
        p.add(meternumber);

        JLabel lblmeterno = new JLabel("Name");
        lblmeterno.setBounds(100,120,100,20);
        p.add(lblmeterno);

        lblname = new JLabel("");
        lblname.setBounds(240,120,100,20);
        p.add(lblname);

        JLabel lbladress = new JLabel("Address");
        lbladress.setBounds(100,160,100,20);
        p.add(lbladress);



        labeladress = new JLabel();
        labeladress.setBounds(240,160,200,20);
        p.add(labeladress);

        try{
            conn c = new conn();
            ResultSet rs =  c.s.executeQuery("select * from customer where meter_no='"+meternumber.getSelectedItem()+"'");
            while(rs.next()){
                lblname.setText(rs.getString("name"));
                labeladress.setText(rs.getString("Address"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        meternumber.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try{
                    conn c = new conn();
                    ResultSet rs =  c.s.executeQuery("select * from customer where meter_no='"+meternumber.getSelectedItem()+"'");
                    while(rs.next()){
                        lblname.setText(rs.getString("name"));
                        labeladress.setText(rs.getString("Address"));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JLabel lblcity = new JLabel("Units Consumed");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);

        tfunits = new JTextField();
        tfunits.setBounds(240,200,200,20);
        p.add(tfunits);

        JLabel lblstate = new JLabel("Month");
        lblstate.setBounds(100,240,100,20);
        p.add(lblstate);

        cmonth = new Choice();
        cmonth.setBounds(240,240,200,20);
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
        p.add(cmonth);

        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(100,280,100,20);
        p.add(lblstatus);

        tfstatus = new JTextField();
        tfstatus.setBounds(240,280,200,20);
        p.add(tfstatus);

        next = new JButton("Submit");
        next.setBounds(120,350,100,25);
        next.setBackground(Color.black);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(250,350,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);

        setLayout(new BorderLayout());
        add(p,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image  = new JLabel(i3);
        add(image,"West");

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==next){
            String meter = meternumber.getSelectedItem();
            String units = tfunits.getText();
            String month = cmonth.getSelectedItem();
            String status = tfstatus.getText();

            int totalBill=0;
            int unit_consumed = Integer.parseInt(units);
            String query= " select * from tax";

            try{
                conn c = new conn();
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    totalBill += unit_consumed *Integer.parseInt(rs.getString("cost_per_unit"));
                    totalBill+=Integer.parseInt(rs.getString("meter_rent"));
                    totalBill+=Integer.parseInt(rs.getString("service_charge"));
                    totalBill+=Integer.parseInt(rs.getString("service_tax"));
                    totalBill+=Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalBill+=Integer.parseInt(rs.getString("fixed_tax"));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            String query2= "insert into bill values('"+meter+"','"+month+"','"+units+"','"+totalBill+"','"+status+"')";
            try{
                conn c = new conn();
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully");
                setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }
    public static void main(String[]args){
        new calculateBill();
    }
}
