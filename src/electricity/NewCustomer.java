package electricity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class NewCustomer extends JFrame implements ActionListener {
    JLabel lblmeter;
    JTextField tfname,tfaddress,tfstate,tfcity,tfmail,tfphone;
    JButton next ,cancel;
    NewCustomer(){
        setSize(700,500);
        setLocation(400,150);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);

        JLabel heading = new JLabel("New Customer");
        heading.setBounds(100,10,200,20);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);

        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);

        tfname = new JTextField();
        tfname.setBounds(240,80,200,20);
        p.add(tfname);

        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setBounds(100,120,100,20);
        p.add(lblmeterno);

        lblmeter = new JLabel("");
        lblmeter.setBounds(240,120,100,20);
        p.add(lblmeter);

        Random ran = new Random();
        long number = ran.nextLong()%100000;
        lblmeter.setText(""+Math.abs(number));

        JLabel lbladress = new JLabel("Address");
        lbladress.setBounds(100,160,100,20);
        p.add(lbladress);

        tfaddress = new JTextField();
        tfaddress.setBounds(240,160,200,20);
        p.add(tfaddress);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);

        tfcity = new JTextField();
        tfcity.setBounds(240,200,200,20);
        p.add(tfcity);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(100,240,100,20);
        p.add(lblstate);

        tfstate = new JTextField();
        tfstate.setBounds(240,240,200,20);
        p.add(tfstate);

        JLabel lblmail = new JLabel("Mail");
        lblmail.setBounds(100,280,100,20);
        p.add(lblmail);

        tfmail = new JTextField();
        tfmail.setBounds(240,280,200,20);
        p.add(tfmail);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(100,320,100,20);
        p.add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(240,320,200,20);
        p.add(tfphone);

        next = new JButton("Next");
        next.setBounds(120,390,100,25);
        next.setBackground(Color.black);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(250,390,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);

        setLayout(new BorderLayout());
        add(p,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image  = new JLabel(i3);
        add(image,"West");

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==next){
        String name = tfname.getText();
        String meter = lblmeter.getText();
        String address = tfaddress.getText();
        String city = tfcity.getText();
        String state = tfstate.getText();
        String mail = tfmail.getText();
        String phone = tfphone.getText();

        String query1 = "insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+state+"','"+mail+"','"+phone+"')";
        String query2 = "insert into login values('"+meter+"','','"+name+"','','')";

        try{
            conn c = new conn();
            c.s.executeUpdate(query1);
            c.s.executeUpdate(query2);
            JOptionPane.showMessageDialog(null,"Customer Details Entered Succesfully");
            setVisible(false);
            new MeterInfo(meter);
        }catch(Exception e){
            e.printStackTrace();
        }


        }else{
           setVisible(false);
        }
    }
    public static void main(String[]args){
        new NewCustomer();
    }
}
