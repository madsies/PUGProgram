
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BoxView;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class WindowController {

    private Frame frame;
    private JPanel sidePanel;
    private int activeScreen;
    private boolean active;
    private JPanel leaderboardPanel;
    private AppController controller;

    public WindowController(AppController ac) {

        // Basic Initialisation
        controller = ac;

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
        setUpLeaderboard();
        activeScreen = 1;
        frame.setVisible(true);
    }

    public void setUpLeaderboard(){
        leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        leaderboardPanel.setPreferredSize(new Dimension(1280, 720));
        leaderboardPanel.add(new Label("Leaderboard"){{setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));}});
        ScrollPane pane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        pane.setPreferredSize(new Dimension(400, 720));
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        pane.add(innerPanel);
        ArrayList<Player> seeded = controller.seedPlayers();
        for (Player p:
             seeded) {
            JLabel label = new JLabel(p.getName()+" - "+p.getMMR());
            EmptyBorder insets = new EmptyBorder(50, 175, 50, 175);
            label.setBorder(insets);
            label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
            innerPanel.add(label);
            innerPanel.setVisible(true);
        }
        leaderboardPanel.setVisible(true);
        leaderboardPanel.add(pane);
        frame.add(leaderboardPanel);

    }

    public boolean isActive(){
        return active;
    }


    public void update() {

    }
}
