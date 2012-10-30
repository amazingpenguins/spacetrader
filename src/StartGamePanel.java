import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartGamePanel extends JPanel {
    protected GameController gc;
    
    public StartGamePanel(GameController gc) {
        this.gc = gc;

        /* New Game Button */
        JButton newGame = new JButton("New Game");
        NewGameListener ngl = new NewGameListener();
        newGame.addActionListener(ngl);

        /* Saved Button */
        JButton savedGame = new JButton("Load Game");

        this.setLayout(new BorderLayout());
        this.add(newGame, BorderLayout.NORTH);
        this.add(savedGame, BorderLayout.CENTER);
    }

    private class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            gc.goToState(GameController.State.NEWPLAYER);
        }
    }
}