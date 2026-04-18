import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {

    public ProfilePanel(AppController controller) {

        setLayout(new GridLayout(4, 2));

        JTextField username = new JTextField();
        JTextField school = new JTextField();
        JTextField session = new JTextField();

        JButton next = new JButton("Next");

        next.addActionListener(e -> {
            if (username.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter your username to continue.");
                return;
            }
            controller.nextStep();
        });

        add(new JLabel("Username:"));
        add(username);

        add(new JLabel("School:"));
        add(school);

        add(new JLabel("Session:"));
        add(session);

        add(new JLabel());
        add(next);
    }
}