import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CollectPanel extends JPanel {

    private JTable table;
    private MetricTableModel tableModel;
    private AppController controller;

    public CollectPanel(AppController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Step 4: Collect Data", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        tableModel =  new MetricTableModel();
        table = new JTable(tableModel);
        table.setRowHeight(30);

        JScrollPane scrollPane =new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        JButton back =new JButton("← Back");
        JButton next =new JButton("Next →");

        back.addActionListener(e -> controller.previousStep());
        next.addActionListener(e -> {
            tableModel.updateAllScores();
            controller.nextStep();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(back);
        bottomPanel.add(next);
        add(bottomPanel, BorderLayout.SOUTH);

        loadDemoData();
    }

    private void loadDemoData() {
        tableModel.addMetric(new DemoMetric("Steps per day", 3, "Higher ↑", 0, 20000, "steps", 8500));
        tableModel.addMetric(new DemoMetric("Sleep hours", 2, "Higher ↑", 0, 12, "hours", 7.5));
        tableModel.addMetric(new DemoMetric("Calories", 4, "Lower ↓", 0, 4000, "kcal", 2200));
    }

    class DemoMetric {
        String name;
        int coeff;
        String direction;
        double min, max, value;
        String unit;

        DemoMetric(String name, int coeff, String dir, double min, double max, String unit, double value) {
            this.name = name;
            this.coeff = coeff;
            this.direction = dir;
            this.min = min;
            this.max = max;
            this.unit = unit;
            this.value = value;
        }


    }


    class MetricTableModel extends AbstractTableModel {
        private String[] columns = {"Metric", "Coeff", "Direction", "Range", "Raw Value", "Score (1-5)", "Unit"};
        private List<DemoMetric> metrics = new ArrayList<>();

        void addMetric(DemoMetric m){

            metrics.add(m);
            fireTableRowsInserted(metrics.size() - 1, metrics.size() - 1);
        }

        void updateAllScores() {
            fireTableDataChanged();
        }

        private double calculateScore(DemoMetric m) {
            double normalized = (m.value - m.min) / (m.max - m.min);
            double rawScore;

            if (m.direction.equals("Higher ↑")) {
                rawScore = 1 + (normalized * 4);
            }
            else {
                rawScore = 5 - (normalized * 4);
            }

            rawScore = Math.max(1.0, Math.min(5.0, rawScore));


            return Math.round(rawScore * 2) / 2.0;
        }

        @Override
        public int getRowCount(){
            return metrics.size();
        }

        @Override
        public int getColumnCount(){
            return columns.length;
        }

        @Override
        public String getColumnName(int col) {
            return columns[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            DemoMetric m = metrics.get(row);
            switch (col) {
                case 0: return m.name;
                case 1: return m.coeff;
                case 2: return m.direction;
                case 3: return (int)m.min + " – " + (int)m.max;
                case 4: return m.value;
                case 5: return calculateScore(m);
                case 6: return m.unit;
                default: return null;
            }
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return col == 4;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            if (col == 4) {
                try {
                    double val = Double.parseDouble(value.toString());
                    DemoMetric m = metrics.get(row);
                    m.value = Math.max(m.min, Math.min(m.max, val));
                    fireTableCellUpdated(row, col);
                    fireTableCellUpdated(row, 5);
                } catch (NumberFormatException e) {

                }
            }
        }


    }


}