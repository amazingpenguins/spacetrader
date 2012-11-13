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
        newGame.setBorder(BorderFactory.createEmptyBorder());
        newGame.setContentAreaFilled(false);
        newGame.setFocusable(false);
        newGame.setMaximumSize(new Dimension(100, 20));
        NewGameListener ngl = new NewGameListener();
        newGame.addActionListener(ngl);

        /* Saved Button */
        JButton savedGame = new JButton("Load Game");
        savedGame.addActionListener(new LoadGameListener());
        savedGame.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 38));
        savedGame.setForeground(Color.WHITE);
        savedGame.setBorder(BorderFactory.createEmptyBorder());
        savedGame.setFocusable(false);
        savedGame.setContentAreaFilled(false);
        savedGame.setMaximumSize(new Dimension(100, 20));

        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.setOpaque(false);
        buttonPanel.add(new JLabel());
        buttonPanel.add(newGame);
        buttonPanel.add(savedGame);
        buttonPanel.add(new JLabel());
        this.add(buttonPanel, BorderLayout.CENTER);
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