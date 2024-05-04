package electricity;
import jdk.nashorn.internal.scripts.JO;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;

public class updateInformation extends JFrame implements ActionListener {
    JButton update,cancel;
    JLabel name,meternumber;
    String meter;
    JTextField tfaddress,tfcity,tfstate,tfmail,tfphone;
    updateInformation(String meter){
        this.meter=meter;
        setBounds(200,150,1050,450);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(110, 0, 400, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        JLabel jblname = new JLabel("Name");
        jblname.setBounds(30, 70, 100, 20);
        add(jblname);

        name = new JLabel("");
        name.setBounds(230, 70, 200, 20);
        add(name);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(30, 110, 100, 20);
        add(lblmeternumber);

        meternumber = new JLabel("");
        meternumber.setBounds(230, 110, 200, 20);
        add(meternumber);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(30, 150, 100, 20);
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(230, 150, 200, 20);
        add(tfaddress);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(30, 190, 100, 20);
        add(lblcity);

        tfcity = new JTextField();
        tfcity.setBounds(230, 190, 200, 20);
        add(tfcity);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(30, 230, 200, 20);
        add(lblstate);

        tfstate = new JTextField();
        tfstate.setBounds(230, 230, 200, 20);
        add(tfstate);

        JLabel lblmail = new JLabel("Mail");
        lblmail.setBounds(30, 270, 100, 20);
        add(lblmail);

         tfmail = new JTextField();
        tfmail.setBounds(230, 270, 200, 20);
        add(tfmail);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(30, 310, 100, 20);
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(230, 310, 200, 20);
        add(tfphone);

        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(70, 360, 100, 25);
        add(update);
        update.addActionListener(this);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(230, 360, 100, 25);
        add(cancel);
        cancel.addActionListener(this);


        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no='" + meter + "'");
            if (rs.next()) {
                name.setText(rs.getString("name"));
                meternumber.setText(rs.getString("meter_no"));
                tfaddress.setText(rs.getString("address"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfmail.setText(rs.getString("mail"));
                tfphone.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==update){
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String mail = tfmail.getText();
            String phone = tfphone.getText();

            try{
                conn c = new conn();
                c.s.executeUpdate("update customer set address='"+address+"',city='"+city+"',state='"+state+"',mail='"+mail+"', phone='"+phone+"' where meter_no='"+meter+"'");
                JOptionPane.showMessageDialog(null,"User Information Updated Successfully");
                setVisible(false);
            }catch(Exception e) {
                e.printStackTrace();
            }

        }else{
            setVisible(false);
        }
    }
    public static void main(String[]args){
        new updateInformation("");
    }
}
