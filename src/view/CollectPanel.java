package view;

import controller.AppController;

import model.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CollectPanel extends BasePanel {

    private CollectTableModel tableModel;
    private JTable metricsTable;

    public CollectPanel(AppController controller) {

        super(controller);

        initializeUI();

    }

    private void initializeUI() {

        setLayout(new BorderLayout());

        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Step 4: Collect Data", SwingConstants.CENTER);

        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        add(titleLabel, BorderLayout.NORTH);


        tableModel = new CollectTableModel();

        metricsTable = new JTable(tableModel);

        metricsTable.setRowHeight(28);

        metricsTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JTextField()));

        JScrollPane scrollPane = new JScrollPane(metricsTable);

        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton("← Back");

        backButton.addActionListener(e -> controller.previousStep());

        JButton recalcButton = new JButton("⟳ Recalculate Scores");

        recalcButton.addActionListener(e -> {

            tableModel.recalculateAllScores();
            metricsTable.repaint();

        });

        JButton nextButton = new JButton("Next →");
        nextButton.setBackground(new Color(70, 130, 200));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(e -> controller.nextStep());

        buttonPanel.add(backButton);
        buttonPanel.add(recalcButton);
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);

    }

    @Override

    public void refreshData() {

        tableModel.refreshData(controller.getCurrentScenario());
        metricsTable.repaint();

    }

    class CollectTableModel extends AbstractTableModel {

        private List<model.Metric> metrics = new ArrayList<>();
        private String[] columns = {"Metric", "Value", "Score (1-5)", "Range", "Unit", "Direction"};

        void refreshData(model.Scenario scenario) {

            metrics.clear();
            if (scenario != null) metrics = scenario.getAllMetrics();
            fireTableDataChanged();

        }

        @Override
        public int getRowCount() {

            return metrics.size();

        }

        @Override
        public int getColumnCount() {

            return 6;

        }

        @Override
        public String getColumnName(int col) {
            return columns[col];

        }

        @Override
        public boolean isCellEditable(int row, int col) {

            return col == 1;

        }

        @Override
        public void setValueAt(Object value, int row, int col) {

            if (col == 1 && row < metrics.size()) {

                try {
                    metrics.get(row).setRawValue(Double.parseDouble(value.toString()));
                    fireTableRowsUpdated(row, row);

                } catch (NumberFormatException e) {}

            }

        }

        @Override
        public Object getValueAt(int row, int col) {

            model.Metric m = metrics.get(row);

            switch (col) {
                case 0: return m.getName();
                case 1: return m.getRawValue();
                case 2: return String.format("%.1f", m.getCalculatedScore());
                case 3: return m.getMinRange() + " - " + m.getMaxRange();
                case 4: return m.getUnit();
                case 5: return m.getDirection().getDisplayName();

                default: return "";

            }

        }

        void recalculateAllScores() {

            for (model.Metric m : metrics) m.calculateScore();
            fireTableDataChanged();
        }
    }

}