package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class MeterInfo extends JFrame implements ActionListener {
    JLabel lblmeter;
    Choice meterlocation,metertype,phasecode,billtype;
    JTextField tfname,tfaddress,tfstate,tfcity,tfmail,tfphone;
    JButton next ,cancel;
    String meternumber;
    MeterInfo(String meternumber){
        this.meternumber=meternumber;
        setSize(700,500);
        setLocation(400,150);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(100,10,200,20);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);

        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);

        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setBounds(240,80,100,20);
        p.add(lblmeternumber);


        JLabel lblmeterno = new JLabel("Meter Location");
        lblmeterno.setBounds(100,120,100,20);
        p.add(lblmeterno);

        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240,120,200,20);
        p.add(meterlocation);

        JLabel lbladress = new JLabel("Meter Type");
        lbladress.setBounds(100,160,100,20);
        p.add(lbladress);

        metertype = new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.setBounds(240,160,200,20);
        p.add(metertype);

        JLabel lblcity = new JLabel("Phase Code");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);

        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(240,200,200,20);
        p.add(phasecode);

        JLabel lblstate = new JLabel("Bill Type");
        lblstate.setBounds(100,240,100,20);
        p.add(lblstate);

        billtype = new Choice();
        billtype.add("Normal");
        billtype.add("Commercial");
        billtype.setBounds(240,240,200,20);
        p.add(billtype);

        JLabel lblmail = new JLabel("Days");
        lblmail.setBounds(100,280,100,20);
        p.add(lblmail);

        JLabel lblmails = new JLabel("30 Days");
        lblmails.setBounds(240,280,100,20);
        p.add(lblmails);



        JLabel lblphone = new JLabel("Note");
        lblphone.setBounds(100,320,100,20);
        p.add(lblphone);

        JLabel lblphones = new JLabel("By Default Bill is Calculated for 30 Days only");
        lblphones.setBounds(240,320,500,20);
        p.add(lblphones);

        next = new JButton("Submit");
        next.setBounds(220,390,100,25);
        next.setBackground(Color.black);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);


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
            String meter = meternumber;
            String location =meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days = "30";

            String query1 = "insert into meter_info values('"+meter+"','"+location+"','"+type+"','"+code+"','"+typebill+"','"+days+"')";


            try{
                conn c = new conn();
                c.s.executeUpdate(query1);

                JOptionPane.showMessageDialog(null,"Meter Information Added  Succesfully");
                setVisible(false);

            }catch(Exception e){
                e.printStackTrace();
            }


        }else{
            setVisible(false);
        }
    }
    public static void main(String[]args){

        new MeterInfo("");
    }
}
