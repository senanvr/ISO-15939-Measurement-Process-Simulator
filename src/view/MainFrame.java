package view;

import controller.AppController;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private StepIndicatorPanel stepIndicator;
    private AppController controller;
    private ProfilePanel profilePanel;
    private DefinePanel definePanel;
    private PlanPanel planPanel;
    private CollectPanel collectPanel;
    private AnalysePanel analysePanel;

    public MainFrame(AppController controller) {

        this.controller = controller;

        initializeUI();

    }

    private void initializeUI() {

        setTitle("ISO/IEC 15939 - Software Measurement Process Simulation");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1100, 750);

        setLocationRelativeTo(null);

        setMinimumSize(new Dimension(900, 600));

        setLayout(new BorderLayout(0, 0));

        stepIndicator = new StepIndicatorPanel();

        add(stepIndicator, BorderLayout.NORTH);

        cardLayout = new CardLayout();

        cardsPanel = new JPanel(cardLayout);
        cardsPanel.setBackground(Color.WHITE);

        profilePanel = new ProfilePanel(controller);

        definePanel = new DefinePanel(controller);

        planPanel = new PlanPanel(controller);

        collectPanel = new CollectPanel(controller);

        analysePanel = new AnalysePanel(controller);

        cardsPanel.add(profilePanel, "step0");
        cardsPanel.add(definePanel, "step1");
        cardsPanel.add(planPanel, "step2");
        cardsPanel.add(collectPanel, "step3");
        cardsPanel.add(analysePanel, "step4");

        add(cardsPanel, BorderLayout.CENTER);

        showStep(0);

    }

    public void showStep(int step) {

        if (step >= 0 && step <= 4) {
            cardLayout.show(cardsPanel, "step" + step);
            stepIndicator.setCurrentStep(step);
            refreshCurrentPanel(step);

        }

    }

    private void refreshCurrentPanel(int step) {

        switch (step) {

            case 2: planPanel.refreshData(); break;
            case 3: collectPanel.refreshData(); break;
            case 4: analysePanel.refreshData(); break;

            default: break;

        }

    }

    public void nextStep() {

        int currentStep = stepIndicator.getCurrentStep();

        if (!validateCurrentStep(currentStep)) {
            return;
        }

        if (currentStep < 4) {
            markStepCompleted(currentStep);
            showStep(currentStep + 1);

        }

    }

    public void previousStep() {

        int currentStep = stepIndicator.getCurrentStep();

        if (currentStep > 0) {
            showStep(currentStep - 1);

        }

    }

    private boolean validateCurrentStep(int step) {

        switch (step) {

            case 0: return profilePanel.validateInput();
            case 1: return definePanel.validateInput();

            default: return true;

        }

    }

    public void markStepCompleted(int step) {
        stepIndicator.markCompleted(step);

    }

    public void resetSteps() {
        stepIndicator.reset();

    }

}