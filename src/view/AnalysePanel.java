package view;

import controller.AppController;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AnalysePanel extends BasePanel {
    private JPanel resultsPanel;
    private RadarPanel radarPanel;
    private JPanel gapPanel;

    public AnalysePanel(AppController controller) {

        super(controller);

        initializeUI();

    }

    private void initializeUI() {

        setLayout(new BorderLayout());

        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Step 5: Analysis Results", SwingConstants.CENTER);

        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        add(titleLabel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();

        resultsPanel = new JPanel();

        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        resultsPanel.setBackground(Color.WHITE);

        radarPanel = new RadarPanel();

        gapPanel = new JPanel();

        gapPanel.setLayout(new BoxLayout(gapPanel, BoxLayout.Y_AXIS));

        gapPanel.setBackground(Color.WHITE);

        tabbedPane.addTab("Dimension Scores", new JScrollPane(resultsPanel));

        tabbedPane.addTab("Radar Chart", radarPanel);

        tabbedPane.addTab("Gap Analysis", new JScrollPane(gapPanel));

        add(tabbedPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton("← Back");

        backButton.addActionListener(e -> controller.previousStep());

        JButton refreshButton = new JButton("⟳ Refresh Analysis");

        refreshButton.addActionListener(e -> refreshData());

        buttonPanel.add(backButton);

        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);

    }

    @Override
    public void refreshData() {
        displayDimensionScores();
        displayRadarChart();
        displayGapAnalysis();

    }

    private void displayDimensionScores() {

        resultsPanel.removeAll();

        model.Scenario scenario = controller.getCurrentScenario();

        if (scenario == null) return;

        Map<String, Double> scoresMap = new HashMap<>();

        for (model.Dimension dim : scenario.getDimensions()) {
            double score = dim.calculateDimensionScore();

            scoresMap.put(dim.getName(), score);

            JPanel dimPanel = new JPanel(new BorderLayout());

            dimPanel.setBackground(Color.WHITE);

            dimPanel.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

            JLabel nameLabel = new JLabel(dim.getName() + " (Coefficient: " + dim.getCoefficient() + ") - Score: " + String.format("%.2f", score));

            nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));

            dimPanel.add(nameLabel, BorderLayout.NORTH);

            JProgressBar progressBar = new JProgressBar(0, 50);

            progressBar.setValue((int)(score * 10));

            progressBar.setString(String.format("%.2f / 5.00", score));

            progressBar.setStringPainted(true);

            progressBar.setForeground(getProgressColor(score));

            dimPanel.add(progressBar, BorderLayout.CENTER);

            resultsPanel.add(dimPanel);

        }

        resultsPanel.revalidate();

        resultsPanel.repaint();

    }

    private Color getProgressColor(double score) {
        if (score >= 4.0) return new Color(0, 150, 0);
        if (score >= 3.0) return new Color(100, 180, 0);
        if (score >= 2.0) return new Color(255, 140, 0);

        return Color.RED;

    }

    private void displayRadarChart() {

        model.Scenario scenario = controller.getCurrentScenario();

        if (scenario == null) return;
        Map<String, Double> scoresMap = new HashMap<>();

        for (model.Dimension dim : scenario.getDimensions()) {
            scoresMap.put(dim.getName(), dim.calculateDimensionScore());

        }

        radarPanel.setData(scoresMap);
        radarPanel.refreshData();

    }

    private void displayGapAnalysis() {
        gapPanel.removeAll();

        model.Scenario scenario = controller.getCurrentScenario();

        if (scenario == null) return;

        model.Dimension lowestDim = scenario.getLowestScoreDimension();

        if (lowestDim == null) return;

        double lowestScore = lowestDim.calculateDimensionScore();
        double gap = 5.0 - lowestScore;

        model.QualityLevel qualityLevel = model.QualityLevel.fromScore(lowestScore);

        JPanel infoPanel = new JPanel();

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        infoPanel.setBackground(new Color(255, 255, 220));

        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 140, 0), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        addGapRow(infoPanel, "Dimension with Lowest Score:", lowestDim.getName());

        addGapRow(infoPanel, "Current Score:", String.format("%.2f / 5.00", lowestScore));

        addGapRow(infoPanel, "Gap to Target (5.0):", String.format("%.2f", gap));

        JLabel qualityLabel = new JLabel("Quality Level: " + qualityLevel.getDisplayName());

        qualityLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        qualityLabel.setForeground(qualityLevel.getColor());
        qualityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(qualityLabel);

        infoPanel.add(Box.createVerticalStrut(15));
        JLabel recommendationLabel = new JLabel("<html><i>This dimension has the lowest score and requires the most improvement.</i></html>");
        recommendationLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        recommendationLabel.setForeground(Color.DARK_GRAY);
        recommendationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(recommendationLabel);

        gapPanel.add(infoPanel);
        gapPanel.add(Box.createVerticalGlue());
        gapPanel.revalidate();
        gapPanel.repaint();

    }

    private void addGapRow(JPanel panel, String label, String value) {

        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(new Color(255, 255, 220));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel labelLabel = new JLabel(label);
        labelLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        row.add(labelLabel, BorderLayout.WEST);
        row.add(valueLabel, BorderLayout.EAST);

        panel.add(row);
        panel.add(Box.createVerticalStrut(10));

    }



}