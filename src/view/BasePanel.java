package view;

import controller.AppController;
import javax.swing.JPanel;

public abstract class BasePanel extends JPanel {

    protected AppController controller;

    public BasePanel(AppController controller) {
        this.controller = controller;

    }

    public abstract void refreshData();
    public boolean validateInput() {
        return true;

    }

}