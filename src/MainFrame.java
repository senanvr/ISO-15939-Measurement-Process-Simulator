import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private CardLayout  cardLayout;
    private JPanel cards;

    private int currentStep = 0;

    public MainFrame(AppController controller) {

        setTitle("ISO 15939 Simulator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(new ProfilePanel(controller), "0");

        cards.add(new DefinePanel(controller), "1");
        cards.add(new PlanPanel(controller), "2");
        cards.add(new CollectPanel(controller), "3");
        // cards.add(new AnalysePanel(controller), "4");
        add(cards);

        setVisible(true);
    }


    public void showNext() {
        if (currentStep < 3) {

            currentStep++;
            cardLayout.show(cards, String.valueOf(currentStep));
        }

    }


    public void showPrevious(){
        currentStep--;

        cardLayout.show(cards, String.valueOf(currentStep));
    }
}