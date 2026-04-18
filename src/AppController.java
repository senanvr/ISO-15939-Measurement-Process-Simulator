import javax.swing.*;

    public class AppController {

        private MainFrame mainFrame;

        public AppController() {

            mainFrame = new MainFrame(this);

        }

        public void nextStep() {
            mainFrame.showNext();

        }

        public void previousStep() {
            mainFrame.showPrevious();
        }
    }

