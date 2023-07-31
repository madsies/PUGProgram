
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
    private boolean active;
    private JPanel leaderboardPanel;
    private JPanel leaderboardInner;
    private boolean leaderboard;

    private boolean main;
    private JPanel mainPanel;
    private JPanel mainPanelLeft;


    private AppController controller;
    int tick = 0;


    public WindowController(AppController ac) {

        // Basic Initialisation
        controller = ac;

        active = true;
        main = true;
        frame = new Frame();

        frame.setTitle("Overwatch PUG tool");
        frame.setSize(1280, 720);
        frame.setPreferredSize(new Dimension(1280, 720));

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                active = false;
                frame.dispose();
            }
        });

        setUpMain();
        setUpLeaderboard();
        frame.setVisible(true);
    }

    public void setUpLeaderboard(){
        leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        leaderboardPanel.setPreferredSize(new Dimension(1280, 720));
        leaderboardPanel.add(new Label("Leaderboard"){{setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));}});
        ScrollPane pane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        pane.setPreferredSize(new Dimension(400, 720));
        leaderboardInner = new JPanel();
        leaderboardInner.setLayout(new BoxLayout(leaderboardInner, BoxLayout.Y_AXIS));

        pane.add(leaderboardInner);
        ArrayList<Player> seeded = controller.seedPlayers();
        for (Player p:
             seeded) {
            JLabel label = new JLabel(p.getName()+" - "+p.getMMR());
            EmptyBorder insets = new EmptyBorder(50, 175, 50, 175);
            label.setBorder(insets);
            label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
            leaderboardInner.add(label);
        }
        leaderboardPanel.setVisible(leaderboard);
        leaderboardPanel.add(pane);
        frame.add(leaderboardPanel);
    }

    public void setUpMain(){

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setPreferredSize(new Dimension(1280, 720));

        mainPanelLeft = new JPanel();
        mainPanelLeft.setLayout(new BoxLayout(mainPanelLeft, BoxLayout.Y_AXIS));
        mainPanelLeft.setPreferredSize(new Dimension(400, 720));

        JButton leaderboardButton = new JButton("Test"){{setBackground(Color.BLACK);}};
        leaderboardButton.setPreferredSize(new Dimension(300, 100));
        mainPanelLeft.add(leaderboardButton);

        mainPanel.add(mainPanelLeft);
        mainPanel.setVisible(main);
        frame.add(mainPanel);
    }

    /*

    Does not work, unsure how i'll ever make it work
    help

     */
    public void updateLeaderboard(){
        leaderboardInner.removeAll();
        leaderboardInner.setLayout(new BoxLayout(leaderboardInner, BoxLayout.Y_AXIS));
        ArrayList<Player> seeded = controller.seedPlayers();
        for (Player p:
                seeded) {
            JLabel label = new JLabel(p.getName()+" - "+p.getMMR());
            EmptyBorder insets = new EmptyBorder(50, 175, 50, 175);
            label.setBorder(insets);
            label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
            leaderboardInner.add(label);
            leaderboardInner.setVisible(true);
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void updateMain(){

    }

    public boolean isActive(){
        return active;
    }

    public void toggleLeaderboard(){
        leaderboard = !leaderboard;
        updateLeaderboard();
        leaderboardPanel.setVisible(leaderboard);
    }

    public void toggleMain(){
        main = !main;
        updateMain();
    }

    public void update() {
        tick++;
        if (tick % 300 == 0) updateLeaderboard();
        if (tick % 500 == 0){
            toggleLeaderboard();
            toggleMain();
        }
    }
}
