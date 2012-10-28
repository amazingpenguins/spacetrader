import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;

public class StartGamePanel extends JPanel {
    private JButton newGame, savedGame, howToPlay, marketButton;
    
    private CountDownLatch latch;

    private GameController gameController;

    public StartGamePanel(GameController gc, CountDownLatch latch) {
        this.latch = latch;

        newGame = new JButton("New Game");
        NewGameListener ngl = new NewGameListener();
        ngl.gc = gc;
        newGame.addActionListener(ngl);
        savedGame = new JButton("Load Game");
        howToPlay = new JButton("How To Play");

        this.setLayout(new BorderLayout());
        this.add(newGame, BorderLayout.NORTH);
        this.add(savedGame, BorderLayout.CENTER);
        this.add(howToPlay, BorderLayout.SOUTH);
    }      

    private class NewGameListener implements ActionListener {
        protected GameController gc;

        public void actionPerformed(ActionEvent event) {
            gc.goToState(GameController.State.NEWPLAYER);
        }
    }
    private class OtherListener implements ActionListener {
        protected GameController gc;

        public void actionPerformed(ActionEvent event) {

        }
    }
    private class Other1Listener implements ActionListener {
        protected GameController gc;

        public void actionPerformed(ActionEvent event) {

        }
    }
    private class Other2Listener implements ActionListener {
        protected GameController gc;

        public void actionPerformed(ActionEvent event) {

        }
    }
}