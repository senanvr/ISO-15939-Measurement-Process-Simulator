package view;

import javax.swing.*;
import java.awt.*;

public class StepIndicatorPanel extends JPanel {

    private JLabel[] stepLabels;
    private boolean[] completedSteps;
    private int currentStep;
    private final String[] stepNames = {"Profile", "Define", "Plan", "Collect", "Analyse"};

    public StepIndicatorPanel() {
        setLayout(new GridLayout(1, 5, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(240, 248, 255));

        stepLabels = new JLabel[5];
        completedSteps = new boolean[5];
        currentStep = 0;

        for (int i = 0; i < 5; i++) {

            stepLabels[i] = new JLabel((i + 1) + ". " + stepNames[i], SwingConstants.CENTER);
            stepLabels[i].setOpaque(true);
            stepLabels[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY, 1),
                    BorderFactory.createEmptyBorder(8, 5, 8, 5)
            ));

            stepLabels[i].setFont(new Font("Segoe UI", Font.PLAIN, 12));

            add(stepLabels[i]);

        }

        updateDisplay();

    }

    private void updateDisplay() {

        for (int i = 0; i < 5; i++) {

            if (completedSteps[i]) {

                stepLabels[i].setText("✓ " + (i + 1) + ". " + stepNames[i]);
                stepLabels[i].setBackground(new Color(144, 238, 144));
                stepLabels[i].setForeground(Color.BLACK);
                stepLabels[i].setFont(new Font("Segoe UI", Font.PLAIN, 12));

            } else if (i == currentStep) {

                stepLabels[i].setText((i + 1) + ". " + stepNames[i]);
                stepLabels[i].setBackground(new Color(135, 206, 235));
                stepLabels[i].setForeground(Color.BLACK);
                stepLabels[i].setFont(new Font("Segoe UI", Font.BOLD, 12));

            } else {

                stepLabels[i].setText((i + 1) + ". " + stepNames[i]);
                stepLabels[i].setBackground(new Color(240, 240, 240));
                stepLabels[i].setForeground(Color.DARK_GRAY);
                stepLabels[i].setFont(new Font("Segoe UI", Font.PLAIN, 12));

            }

        }

    }

    public void setCurrentStep(int step) {
        this.currentStep = step;

        updateDisplay();

    }

    public int getCurrentStep() {
        return currentStep;

    }

    public void markCompleted(int step) {

        if (step >= 0 && step < 5) {
            completedSteps[step] = true;
            updateDisplay();

        }

    }

    public void reset() {

        for (int i = 0; i < 5; i++) {
            completedSteps[i] = false;

        }
        currentStep = 0;
        updateDisplay();

    }

    public boolean isStepCompleted(int step) {
        return step >= 0 && step < 5 && completedSteps[step];

    }

}