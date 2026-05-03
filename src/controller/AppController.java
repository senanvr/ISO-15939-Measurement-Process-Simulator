package controller;

import model.*;
import view.MainFrame;

public class AppController {

    private int currentStep;
    private User user;
    private QualityType selectedQualityType;
    private Mode selectedMode;
    private Scenario selectedScenario;
    private MainFrame mainFrame;
    private ScenarioManager scenarioManager;

    public AppController() {

        this.currentStep = 0;

        this.user = new User();

        this.scenarioManager = new ScenarioManager();

        this.mainFrame = new MainFrame(this);

        this.mainFrame.setVisible(true);

    }

    public void nextStep() {

        if (currentStep < 4) {

            mainFrame.markStepCompleted(currentStep);
            currentStep++;

            mainFrame.showStep(currentStep);

        }

    }

    public void previousStep() {

        if (currentStep > 0) {

            currentStep--;

            mainFrame.showStep(currentStep);

        }

    }

    public void goToStep(int step) {

        if (step >= 0 && step <= 4) {

            currentStep = step;

            mainFrame.showStep(currentStep);

        }

    }

    public void setProfileData(String username, String school, String sessionName) {

        user.setUsername(username);
        user.setSchool(school);
        user.setSessionName(sessionName);

    }

    public User getUser() {

        return user;

    }

    public void setQualityType(QualityType type) {

        this.selectedQualityType = type;

    }

    public QualityType getQualityType() {

        return selectedQualityType;
    }
    public void setMode(Mode mode) {

        this.selectedMode = mode;

    }

    public Mode getMode() {

        return selectedMode;
    }

    public void setScenario(Scenario scenario) {
        this.selectedScenario = scenario;

    }

    public void setScenarioByModeAndName(Mode mode, String scenarioName) {
        this.selectedMode = mode;

        this.selectedScenario = scenarioManager.getScenario(mode, scenarioName);

    }

    public Scenario   getCurrentScenario() {

        return selectedScenario;

    }

    public ScenarioManager getScenarioManager() {

        return scenarioManager;

    }

    public int getCurrentStep() {

        return currentStep;

    }

    public boolean isProfileComplete() {
        return user.isComplete();

    }

}