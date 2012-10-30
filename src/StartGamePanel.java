import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;

public class StartGamePanel extends JPanel {
    private JButton newGame, savedGame, howToPlay;
    public JButton marketButton;
    
    private CountDownLatch latch;

    private GameController gameController;

    public StartGamePanel(GameController gc, CountDownLatch latch) {
        this.latch = latch;

        newGame = new JButton("New Game");
        NewGameListener ngl = new NewGameListener();
        ngl.gc = gc;
        newGame.addActionListener(ngl);
        savedGame = new JButton("Load Game");
        OtherListener listener = new OtherListener();
        listener.gc = gc;
        savedGame.addActionListener(listener);
        marketButton = new JButton("Market Test");
        TestListener test = new TestListener();
        test.gc = gc;
        marketButton.addActionListener(test);

        this.setLayout(new BorderLayout());
        this.add(newGame, BorderLayout.NORTH);
        this.add(savedGame, BorderLayout.CENTER);
        this.add(marketButton, BorderLayout.SOUTH);

        if (gc.getPlayer() == null) {
            marketButton.setEnabled(false);
        }
        else {
            marketButton.setEnabled(true);
        }
    }      

    private class NewGameListener implements ActionListener {
        protected GameController gc;

        public void actionPerformed(ActionEvent event) {
            gc.goToState(GameController.State.NEWPLAYER);
        }
    }

    
    // copy and paste and rename new class to write a new ActionListener
    private class OtherListener implements ActionListener {
        protected GameController gc;

        public void actionPerformed(ActionEvent event) {
            gc.goToState(GameController.State.GAMEPANEL);
        }
    }

    private class TestListener implements ActionListener {
        protected GameController gc;

        public void actionPerformed(ActionEvent event) {
            gc.goToState(GameController.State.MARKETPANEL);
        }
    }
}