package view;

import controller.AppController;
import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends BasePanel {
    private JTextField usernameField;
    private JTextField schoolField;
    private JTextField sessionNameField;

    public ProfilePanel(AppController controller) {

        super(controller);

        initializeUI();

    }

    private void initializeUI() {

        setLayout(new BorderLayout());

        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Step 1: User Profile", SwingConstants.CENTER);

        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());

        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(12, 12, 12, 12);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;

        formPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;

        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(250, 30));

        formPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;

        formPanel.add(new JLabel("School/University:"), gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;

        schoolField = new JTextField(20);
        schoolField.setPreferredSize(new Dimension(250, 30));

        formPanel.add(schoolField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;

        formPanel.add(new JLabel("Session Name:"), gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;

        sessionNameField = new JTextField(20);
        sessionNameField.setPreferredSize(new Dimension(250, 30));

        formPanel.add(sessionNameField, gbc);

        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(formPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        buttonPanel.setBackground(Color.WHITE);

        JButton nextButton = new JButton("Next →");

        nextButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        nextButton.setBackground(new Color(70, 130, 200));
        nextButton.setForeground(Color.WHITE);
        nextButton.setPreferredSize(new Dimension(100, 35));
        nextButton.addActionListener(e -> {

            if (validateInput()) {

                controller.setProfileData(
                        usernameField.getText().trim(),
                        schoolField.getText().trim(),
                        sessionNameField.getText().trim()
                );

                controller.nextStep();

            }

        });

        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    @Override
    public boolean validateInput() {

        if (usernameField.getText().trim().isEmpty()) {
            showWarning("Please enter your username to continue.");

            return false;

        }

        if (schoolField.getText().trim().isEmpty()) {
            showWarning("Please enter your school/university to continue.");

            return false;

        }

        if (sessionNameField.getText().trim().isEmpty()) {
            showWarning("Please enter a session name to continue.");
            return false;

        }
        return true;

    }

    private void showWarning(String message) {

        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void refreshData() {}

}