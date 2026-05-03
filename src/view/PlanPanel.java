package view;

import controller.AppController;

import model.*;

import javax.swing.*;

import javax.swing.table.AbstractTableModel;

import java.awt.*;

import java.util.ArrayList;

import java.util.List;

public class PlanPanel extends BasePanel {

    private JTable metricsTable;

    private PlanTableModel tableModel;

    public PlanPanel(AppController controller) {
        super(controller);

        initializeUI();
    }

    private void initializeUI() {

        setLayout(new BorderLayout());

        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Step 3: Plan Measurement", SwingConstants.CENTER);

        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        add(titleLabel, BorderLayout.NORTH);

        tableModel = new PlanTableModel();

        metricsTable = new JTable(tableModel);

        metricsTable.setRowHeight(28);

        metricsTable.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(metricsTable);

        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton("← Back");

        backButton.addActionListener(e -> controller.previousStep());

        JButton nextButton = new JButton("Next →");

        nextButton.setBackground(new Color(70, 130, 200));

        nextButton.setForeground(Color.WHITE);

        nextButton.addActionListener(e -> controller.nextStep());

        buttonPanel.add(backButton);

        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);

    }

    @Override

    public void refreshData() {

        tableModel.refreshData(controller.getCurrentScenario());

        metricsTable.repaint();

    }

    class PlanTableModel extends AbstractTableModel {

        private List<Object[]> data = new ArrayList<>();

        private String[] columns = {"Metric", "Coefficient", "Direction", "Range", "Unit", "Dimension"};

        void refreshData(model.Scenario scenario) {

            data.clear();

            if (scenario != null) {

                for (model.Dimension dim : scenario.getDimensions()) {

                    for (model.Metric m : dim.getMetrics()) {

                        data.add(new Object[]{
                                m.getName(), m.getCoefficient(),
                                m.getDirection().getDisplayName(),
                                m.getMinRange() + " - " + m.getMaxRange(),
                                m.getUnit(), dim.getName()
                        });
                    }
                }
            }

            fireTableDataChanged();

        }

        @Override

        public int getRowCount() {

            return data.size();

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

        public Object getValueAt(int row, int col) {

            return data.get(row)[col];

        }

        @Override

        public boolean isCellEditable(int row, int col) {

            return false;

        }

    }

}