import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlanPanel extends JPanel {

    private JTable table;
    private DefaultTableModel  model;

    public PlanPanel(AppController controller) {

        setLayout(new BorderLayout());

        String[] columns = {
                "Metric",
                "Coefficient",
                "Direction",
                "Range",
                "Unit"
        };

        model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);


        JScrollPane scrollPane =new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        JButton back= new JButton("Back");
        JButton next = new JButton("Next");

        back.addActionListener(e -> controller.previousStep());
        next.addActionListener(e -> controller.nextStep());

        JPanel bottom = new JPanel();
        bottom.add(back);
        bottom.add(next);

        add(bottom, BorderLayout.SOUTH);


        loadDummyData();
    }

    private void loadDummyData() {
        model.addRow(new Object[]{
                "Steps per day", 3, "Higher ↑", "0-20000", "steps"
        });

        model.addRow(new Object[]{
                "Sleep hours", 2, "Higher ↑", "0-12", "hours"
        });

        model.addRow(new Object[]{
                "Calories", 4, "Lower ↓", "0-4000", "kcal"
        });
    }
}
