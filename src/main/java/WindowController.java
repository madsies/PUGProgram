
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowController {

    private Frame frame;
    private JPanel sidePanel;
    private int activeScreen;
    private boolean active;

    public WindowController() {

        // Basic Initialisation

        active = true;

        frame = new Frame();
        sidePanel = new JPanel();
        sidePanel.add(new TextArea() {{
            setText("Test!");
        }});
        sidePanel.setVisible(true);

        frame.setTitle("Overwatch PUG tool");
        frame.setSize(1280, 720);

        frame.add(sidePanel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                active = false;
                frame.dispose();
            }
        });
        activeScreen = 1;
        frame.setVisible(true);
    }

    public boolean isActive(){
        return active;
    }


    public void update() {

    }
}
