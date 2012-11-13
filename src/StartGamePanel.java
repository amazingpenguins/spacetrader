import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartGamePanel extends JPanel {
    private final GameController gc;
    private BufferedImage bg;
    
    public StartGamePanel(GameController gc) {
        this.gc = gc;
        try {
            bg = ImageIO.read(new File("images/MainBackground.png"));
        } catch(IOException ioe) {
            System.err.println("Error loading images/MainBackground.png");
            bg = null;
        }
        /* New Game Button */
        JButton newGame = new JButton("New Game");
        newGame.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 38));
        newGame.setForeground(Color.WHITE);
        newGame.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        newGame.setBackground(new Color(255, 255, 255, 85));
        newGame.setFocusable(false);
        newGame.setRolloverEnabled(false);
        newGame.setMaximumSize(new Dimension(50, 20));
        NewGameListener ngl = new NewGameListener();
        newGame.addActionListener(ngl);

        /* Saved Button */
        JButton savedGame = new JButton("Load Game");
        savedGame.addActionListener(new LoadGameListener());
        savedGame.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 38));
        savedGame.setForeground(Color.WHITE);
        savedGame.setFocusable(false);
        savedGame.setRolloverEnabled(false);
        savedGame.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        savedGame.setBackground(new Color(255, 255, 255, 85));
        savedGame.setMaximumSize(new Dimension(0, 0));

        this.setLayout(null);
        newGame.setBounds(290, 200, 200, 50);
        savedGame.setBounds(285, 280, 211, 50);
        this.add(newGame);
        this.add(savedGame);
    }

    private class NewGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            gc.goToState(GameController.State.NEWPLAYER);
        }
    }

    private class LoadGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            gc.loadGame();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), null);
    }

}