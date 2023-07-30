
import javax.swing.*;
import java.awt.*;

public class WindowController {

    private Frame frame;
    private JPanel sidePanel;
    private int activeScreen;


    public WindowController() {

        // Basic Initialisation

        frame = new Frame();
        sidePanel = new JPanel();
        AppController test = new AppController();
        sidePanel.add(new TextArea() {{
            setText("Test!");
        }});
        sidePanel.setVisible(true);

        frame.setTitle("Overwatch PUG tool");
        frame.setSize(1280, 720);

        frame.add(sidePanel);
        activeScreen = 1;
        frame.setVisible(true);
    }


    public void update() {
    }
}
