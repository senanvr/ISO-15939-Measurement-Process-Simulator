package view;

import controller.AppController;
import model.*;
import javax.swing.*;
import java.awt.*;

public class DefinePanel extends BasePanel {

    private JRadioButton productRadio;
    private JRadioButton processRadio;
    private JRadioButton educationRadio;
    private JRadioButton healthRadio;
    private JComboBox<String> scenarioCombo;

    public DefinePanel(AppController controller) {

        super(controller);
        initializeUI();

    }

    private void initializeUI() {

        setLayout(new BorderLayout());

        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Step 2: Define Quality Dimensions", SwingConstants.CENTER);

        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        add(titleLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());

        mainPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(15, 15, 15, 15);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;

        mainPanel.add(new JLabel("Quality Type:"), gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;

        JPanel qualityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));

        qualityPanel.setBackground(Color.WHITE);

        ButtonGroup qualityGroup = new ButtonGroup();

        productRadio = new JRadioButton("Product Quality");

        processRadio = new JRadioButton("Process Quality");

        productRadio.setSelected(true);

        qualityGroup.add(productRadio);
        qualityGroup.add(processRadio);
        qualityPanel.add(productRadio);
        qualityPanel.add(processRadio);
        mainPanel.add(qualityPanel, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        mainPanel.add(new JLabel("Mode:"), gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        JPanel modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));

        modePanel.setBackground(Color.WHITE);
        ButtonGroup modeGroup = new ButtonGroup();
        educationRadio = new JRadioButton("Education");
        healthRadio = new JRadioButton("Health");
        educationRadio.setSelected(true);

        modeGroup.add(educationRadio);
        modeGroup.add(healthRadio);
        modePanel.add(educationRadio);
        modePanel.add(healthRadio);
        mainPanel.add(modePanel, gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;

        mainPanel.add(new JLabel("Scenario:"), gbc);

        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;

        scenarioCombo = new JComboBox<>();

        scenarioCombo.setPreferredSize(new java.awt.Dimension(350, 35));

        mainPanel.add(scenarioCombo, gbc);

        educationRadio.addActionListener(e -> updateScenarios());

        healthRadio.addActionListener(e -> updateScenarios());

        add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton("← Back");

        backButton.addActionListener(e -> controller.previousStep());

        JButton nextButton = new JButton("Next →");
        nextButton.setBackground(new Color(70, 130, 200));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(e -> {

            if (validateInput()) {

                controller.setQualityType(productRadio.isSelected() ? QualityType.PRODUCT_QUALITY : QualityType.PROCESS_QUALITY);

                Mode selectedMode = educationRadio.isSelected() ? Mode.EDUCATION : Mode.HEALTH;

                String selectedScenario = (String) scenarioCombo.getSelectedItem();

                controller.setMode(selectedMode);

                controller.setScenarioByModeAndName(selectedMode, selectedScenario);

                controller.nextStep();

            }

        });

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);
        updateScenarios();

    }

    private void updateScenarios() {

        scenarioCombo.removeAllItems();
        Mode mode = educationRadio.isSelected() ? Mode.EDUCATION : Mode.HEALTH;

        for (String name : controller.getScenarioManager().getScenarioNamesByMode(mode)) {
            scenarioCombo.addItem(name);

        }

    }

    @Override
    public boolean validateInput() {

        if (scenarioCombo.getItemCount() == 0 || scenarioCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select a scenario to continue.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;

        }
        return true;

    }


    @Override
    public void refreshData() {
        updateScenarios();

    }

}