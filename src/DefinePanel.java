import javax.swing.*;
import java.awt.*;

public class DefinePanel extends JPanel {

    private JRadioButton productBtn;
    private JRadioButton  processBtn;

    private JRadioButton  healthBtn;
    private JRadioButton educationBtn;
    private JRadioButton customBtn;

    private JComboBox<String> scenarioBox;

    public DefinePanel(AppController controller) {

        setLayout(new GridLayout(6, 1));


        productBtn =new JRadioButton("Product Quality");
        processBtn = new JRadioButton("Process Quality");

        ButtonGroup qualityGroup =new ButtonGroup();

        qualityGroup.add(productBtn);
        qualityGroup.add(processBtn);

        JPanel qualityPanel = new JPanel();
        qualityPanel.add(productBtn);
        qualityPanel.add(processBtn);

        healthBtn = new JRadioButton("Health");

        educationBtn = new JRadioButton("Education");

        customBtn = new JRadioButton("Custom");

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(healthBtn);
        modeGroup.add(educationBtn);
        modeGroup.add(customBtn);

        JPanel modePanel = new JPanel();
        modePanel.add(healthBtn);
        modePanel.add(educationBtn);
        modePanel.add(customBtn);

        scenarioBox = new JComboBox<>();
        scenarioBox.addItem("Select Scenario");

        healthBtn.addActionListener(e -> updateScenarios("Health"));
        educationBtn.addActionListener(e -> updateScenarios("Education"));
        customBtn.addActionListener(e -> updateScenarios("Custom"));

        JPanel scenarioPanel = new JPanel();
        scenarioPanel.add(new JLabel("Scenario:"));
        scenarioPanel.add(scenarioBox);


        JButton back =new JButton("Back");
        JButton next= new JButton("Next");

        back.addActionListener(e -> controller.previousStep());

        next.addActionListener(e -> {
            if (!validateInput()) return;

            controller.nextStep();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(back);
        buttonPanel.add(next);


        add(new JLabel("Select Quality Type"));
        add(qualityPanel);

        add(new JLabel("Select Mode"));
        add(modePanel);
        add(scenarioPanel);
        add(buttonPanel);
    }

    private void updateScenarios(String mode) {
        scenarioBox.removeAllItems();

        if (mode.equals("Health")) {
            scenarioBox.addItem("Fitness Tracking");
            scenarioBox.addItem("Diet Monitoring");
        }

        else if (mode.equals("Education")) {
            scenarioBox.addItem("Exam Performance");
            scenarioBox.addItem("Attendance Analysis");
        }


        else {
            scenarioBox.addItem("Custom Scenario 1");
            scenarioBox.addItem("Custom Scenario 2");
        }
    }

    private boolean validateInput() {
        if (!productBtn.isSelected() && !processBtn.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a quality type.");
            return false;
        }

        if (!healthBtn.isSelected() && !educationBtn.isSelected() && !customBtn.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a mode.");

            return false;

        }

        if (scenarioBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a scenario.");

            return false;
        }

        return true;



    }





}