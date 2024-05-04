package electricity;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class paytm extends JFrame implements ActionListener {
    String meter;
    JButton back;

    paytm(String meter) {
        this.meter = meter;
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JFXPanel fxPanel = new JFXPanel();
        add(fxPanel,BorderLayout.CENTER);


        Platform.runLater(() -> {
            WebView webView = new WebView();
            webView.getEngine().load("https://paytm.com/online-payments");

            fxPanel.setScene(new Scene(webView));
        });

        back = new JButton("Back");
        back.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(back);
        add(buttonPanel, BorderLayout.NORTH);

        setSize(1300, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new payBill(meter).setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new paytm("12345"));
    }
}
