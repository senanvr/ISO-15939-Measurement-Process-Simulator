package view;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class RadarPanel extends JPanel {

    private Map<String, Double> dimensionScores;
    private String[] dimensionNames;
    private double[] scores;

    public RadarPanel() {

        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

    }

    public void setData(Map<String, Double> scoresMap) {
        this.dimensionScores = scoresMap;

        if (scoresMap != null && !scoresMap.isEmpty()) {

            dimensionNames = scoresMap.keySet().toArray(new String[0]);
            scores = new double[dimensionNames.length];

            for (int i = 0; i < dimensionNames.length; i++) {

                scores[i] = scoresMap.get(dimensionNames[i]);

            }

        }

        repaint();

    }

    public void refreshData() {

        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (dimensionNames == null || dimensionNames.length < 3) {
            g.setColor(Color.GRAY);
            g.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            g.drawString("Need at least 3 dimensions for radar chart", getWidth() / 2 - 150, getHeight() / 2);

            return;

        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        int radius = Math.min(cx, cy) - 50;
        int n = dimensionNames.length;

        double angleStep = 2 * Math.PI / n;

        for (int level = 1; level <= 4; level++) {
            int r = radius * level / 4;

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawOval(cx - r, cy - r, 2 * r, 2 * r);

        }

        for (int i = 0; i < n; i++) {
            double angle = angleStep * i - Math.PI / 2;
            int x = cx + (int)(radius * Math.cos(angle));
            int y = cy + (int)(radius * Math.sin(angle));

            g2d.setColor(Color.GRAY);
            g2d.drawLine(cx, cy, x, y);

            int labelX = cx + (int)((radius + 15) * Math.cos(angle));
            int labelY = cy + (int)((radius + 15) * Math.sin(angle));

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Segoe UI", Font.PLAIN, 10));

            String name = dimensionNames[i];
            int fw = g2d.getFontMetrics().stringWidth(name);

            g2d.drawString(name, labelX - fw / 2, labelY);

        }

        int[] xPoints = new int[n];
        int[] yPoints = new int[n];

        for (int i = 0; i < n; i++) {

            double angle = angleStep * i - Math.PI / 2;
            double value = Math.min(1.0, scores[i] / 5.0);
            int r = (int)(radius * value);

            xPoints[i] = cx + (int)(r * Math.cos(angle));
            yPoints[i] = cy + (int)(r * Math.sin(angle));

        }

        g2d.setColor(new Color(70, 130, 200, 100));
        g2d.fillPolygon(xPoints, yPoints, n);
        g2d.setColor(new Color(70, 130, 200));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolygon(xPoints, yPoints, n);

    }

}