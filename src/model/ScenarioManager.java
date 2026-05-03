package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenarioManager {

    private Map<Mode, List<Scenario>> scenariosByMode;
    private Map<String, Scenario> scenariosByName;

    public ScenarioManager() {

        scenariosByMode = new HashMap<>();
        scenariosByName = new HashMap<>();

        loadHardcodedData();

    }

    private void loadHardcodedData() {

        List<Scenario> educationScenarios = new ArrayList<>();
        Scenario scenarioC = createEducationScenarioC();

        educationScenarios.add(scenarioC);

        scenariosByName.put(scenarioC.getName(), scenarioC);

        Scenario scenarioD = createEducationScenarioD();

        educationScenarios.add(scenarioD);

        scenariosByName.put(scenarioD.getName(), scenarioD);
        scenariosByMode.put(Mode.EDUCATION, educationScenarios);

        List<Scenario> healthScenarios = new ArrayList<>();

        Scenario scenarioA = createHealthScenarioA();

        healthScenarios.add(scenarioA);

        scenariosByName.put(scenarioA.getName(), scenarioA);

        Scenario scenarioB = createHealthScenarioB();

        healthScenarios.add(scenarioB);

        scenariosByName.put(scenarioB.getName(), scenarioB);
        scenariosByMode.put(Mode.HEALTH, healthScenarios);

        List<Scenario> customScenarios = new ArrayList<>();

        scenariosByMode.put(Mode.CUSTOM, customScenarios);

    }

    private Scenario createEducationScenarioC() {
        Scenario s = new Scenario("Scenario C - Team Alpha", "Education LMS Team Alpha");

        Dimension usability = new Dimension("Usability", 25);

        usability.addMetric(new Metric("SUS score", 50, Direction.HIGHER_IS_BETTER, 0, 100, "points", 85));

        usability.addMetric(new Metric("Onboarding time", 50, Direction.LOWER_IS_BETTER, 0, 60, "min", 10));

        s.addDimension(usability);

        Dimension performance = new Dimension("Performance Efficiency", 20);

        performance.addMetric(new Metric("Video start time", 50, Direction.LOWER_IS_BETTER, 0, 15, "sec", 3));

        performance.addMetric(new Metric("Concurrent exams", 50, Direction.HIGHER_IS_BETTER, 0, 600, "users", 450));

        s.addDimension(performance);

        Dimension accessibility = new Dimension("Accessibility", 20);

        accessibility.addMetric(new Metric("WCAG compliance", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 78));

        accessibility.addMetric(new Metric("Screen reader score", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 82));

        s.addDimension(accessibility);

        Dimension reliability = new Dimension("Reliability", 20);

        reliability.addMetric(new Metric("Uptime", 50, Direction.HIGHER_IS_BETTER, 95, 100, "%", 99.5));

        reliability.addMetric(new Metric("MTTR", 50, Direction.LOWER_IS_BETTER, 0, 120, "min", 45));

        s.addDimension(reliability);

        Dimension suitability = new Dimension("Functional Suitability", 15);

        suitability.addMetric(new Metric("Feature completion", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 88));
        suitability.addMetric(new Metric("Assignment submit rate", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 76));

        s.addDimension(suitability);

        return s;

    }

    private Scenario createEducationScenarioD() {

        Scenario s = new Scenario("Scenario D - Team Beta", "Education LMS Team Beta");

        Dimension learning = new Dimension("Learning Effectiveness", 30);

        learning.addMetric(new Metric("Student Engagement", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 72));
        learning.addMetric(new Metric("Course Completion Rate", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 68));

        s.addDimension(learning);

        Dimension usability = new Dimension("System Usability", 25);

        usability.addMetric(new Metric("Navigation Ease", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 85));

        usability.addMetric(new Metric("Mobile App Rating", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 79));

        s.addDimension(usability);

        Dimension technical = new Dimension("Technical Quality", 25);

        technical.addMetric(new Metric("Page Load Time", 50, Direction.LOWER_IS_BETTER, 0, 5, "sec", 2.5));

        technical.addMetric(new Metric("Error Rate", 50, Direction.LOWER_IS_BETTER, 0, 10, "%", 3.2));

        s.addDimension(technical);

        Dimension support = new Dimension("Support Quality", 20);

        support.addMetric(new Metric("Ticket Resolution Time", 50, Direction.LOWER_IS_BETTER, 0, 48, "hours", 12));

        support.addMetric(new Metric("User Satisfaction", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 81));

        s.addDimension(support);

        return s;

    }

    private Scenario createHealthScenarioA() {

        Scenario s = new Scenario("Scenario A - City Hospital", "Hospital Management System");

        Dimension safety = new Dimension("Patient Safety", 30);

        safety.addMetric(new Metric("Medication Error Rate", 40, Direction.LOWER_IS_BETTER, 0, 10, "%", 2.5));

        safety.addMetric(new Metric("Fall Rate", 30, Direction.LOWER_IS_BETTER, 0, 5, "%", 1.2));

        safety.addMetric(new Metric("Infection Rate", 30, Direction.LOWER_IS_BETTER, 0, 8, "%", 3.1));

        s.addDimension(safety);

        Dimension performance = new Dimension("System Performance", 25);

        performance.addMetric(new Metric("Response Time", 50, Direction.LOWER_IS_BETTER, 0, 10, "sec", 2.8));

        performance.addMetric(new Metric("Uptime", 50, Direction.HIGHER_IS_BETTER, 95, 100, "%", 99.2));
        s.addDimension(performance);

        Dimension security = new Dimension("Data Security", 25);

        security.addMetric(new Metric("Access Control Score", 50, Direction.HIGHER_IS_BETTER, 0, 100, "points", 88));

        security.addMetric(new Metric("Encryption Rate", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 92));

        s.addDimension(security);

        Dimension satisfaction = new Dimension("User Satisfaction", 20);

        satisfaction.addMetric(new Metric("Staff Satisfaction", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 76));

        satisfaction.addMetric(new Metric("Patient Feedback Score", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 82));



        s.addDimension(satisfaction);

        return s;

    }

    private Scenario createHealthScenarioB() {

        Scenario s = new Scenario("Scenario B - Wellness Center", "Private Clinic System");

        Dimension clinical = new Dimension("Clinical Quality", 35);

        clinical.addMetric(new Metric("Diagnosis Accuracy", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 91));

        clinical.addMetric(new Metric("Treatment Success Rate", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 87));

        s.addDimension(clinical);

        Dimension efficiency = new Dimension("Operational Efficiency", 30);

        efficiency.addMetric(new Metric("Patient Wait Time", 50, Direction.LOWER_IS_BETTER, 0, 60, "min", 15));

        efficiency.addMetric(new Metric("Staff Utilization", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 78));

        s.addDimension(efficiency);

        Dimension financial = new Dimension("Financial Performance", 20);

        financial.addMetric(new Metric("Revenue per Patient", 50, Direction.HIGHER_IS_BETTER, 0, 500, "USD", 320));

        financial.addMetric(new Metric("Cost Efficiency", 50, Direction.LOWER_IS_BETTER, 0, 100, "%", 65));

        s.addDimension(financial);

        Dimension compliance = new Dimension("Compliance", 15);

        compliance.addMetric(new Metric("HIPAA Compliance", 100, Direction.HIGHER_IS_BETTER, 0, 100, "%", 96));

        s.addDimension(compliance);

        return s;

    }

    public List<Scenario> getScenariosByMode(Mode mode) {
        return scenariosByMode.getOrDefault(mode, new ArrayList<>());

    }

    public Scenario getScenario(Mode mode, String scenarioName) {

        List<Scenario> scenarios = scenariosByMode.get(mode);

        if (scenarios != null) {

            for (Scenario s : scenarios) {

                if (s.getName().equals(scenarioName)) {
                    return s;
                }

            }

        }

        return scenariosByName.get(scenarioName);

    }

    public List<Mode> getAvailableModes() {

        List<Mode> modes = new ArrayList<>();

        for (Mode mode : Mode.values()) {

            if (mode != Mode.CUSTOM || !scenariosByMode.get(mode).isEmpty()) {
                modes.add(mode);

            }

        }

        return modes;

    }

    public List<String> getScenarioNamesByMode(Mode mode) {

        List<String> names = new ArrayList<>();
        List<Scenario> scenarios = scenariosByMode.get(mode);

        if (scenarios != null) {

            for (Scenario s : scenarios) {
                names.add(s.getName());

            }

        }

        return names;

    }

}