package electricity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class viewInformation extends JFrame implements ActionListener {
    JButton cancel;

    viewInformation(String meter) {
        setBounds(350, 100, 850, 550);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        JLabel jblname = new JLabel("Name");
        jblname.setBounds(70, 80, 100, 20);
        add(jblname);

        JLabel name = new JLabel("");
        name.setBounds(250, 80, 100, 20);
        add(name);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(70, 140, 100, 20);
        add(lblmeternumber);

        JLabel meternumber = new JLabel("");
        meternumber.setBounds(250, 140, 100, 20);
        add(meternumber);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(70, 200, 100, 20);
        add(lbladdress);

        JLabel address = new JLabel("");
        address.setBounds(250, 200, 100, 20);
        add(address);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(70, 260, 100, 20);
        add(lblcity);

        JLabel city = new JLabel("");
        city.setBounds(250, 260, 100, 20);
        add(city);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(70, 320, 100, 20);
        add(lblstate);

        JLabel state = new JLabel("");
        state.setBounds(250, 320, 100, 20);
        add(state);

        JLabel lblmail = new JLabel("Mail");
        lblmail.setBounds(500, 80, 100, 20);
        add(lblmail);

        JLabel mail = new JLabel("");
        mail.setBounds(650, 80, 100, 20);
        add(mail);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(500, 140, 100, 20);
        add(lblphone);

        JLabel phone = new JLabel("");
        phone.setBounds(650, 140, 100, 20);
        add(phone);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(250, 380, 100, 25);
        add(cancel);
        cancel.addActionListener(this);

        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no='" + meter + "'");
            if (rs.next()) {
                name.setText(rs.getString("name"));
                meternumber.setText(rs.getString("meter_no"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                mail.setText(rs.getString("mail"));
                phone.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
    }
    public static void main(String[] args) {
        new viewInformation("");
    }
}
