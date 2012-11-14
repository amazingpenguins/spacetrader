/**
 * Start Game Panel
 */
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class StartGamePanel extends JPanel {
    /**
     * Field serialVersionUID.
     * (value is -3274961450138057043)
     */
    private static final long serialVersionUID = -3274961450138057043L;

    /**
     * Field gc.
     */
    private final GameController gc;

    /**
     * Field bg.
     */
    private BufferedImage bg;
    
    /**
     * Constructor for StartGamePanel.
     * @param gc GameController
     */
    public StartGamePanel(GameController gc) {
        this.gc = gc;
        setupPanel();
    }

    /**
     * Setup this Panel.
     */
    private void setupPanel() {
        try {
            bg = ImageIO.read(new File("images/MainBackground.png"));
        } catch(IOException ioe) {
            System.err.println("Error loading images/MainBackground.png");
            bg = null;
            ioe.printStackTrace();
        }
        /* New Game Button */
        final JButton newGame = new JButton("New Game");
        newGame.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 38));
        newGame.setForeground(Color.WHITE);
        newGame.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        newGame.setBackground(new Color(255, 255, 255, 85));
        newGame.setFocusable(false);
        newGame.setRolloverEnabled(false);
        newGame.setMaximumSize(new Dimension(50, 20));
        final NewGameListener ngl = new NewGameListener();
        newGame.addActionListener(ngl);

        /* Saved Button */
        final JButton savedGame = new JButton("Load Game");
        savedGame.addActionListener(new LoadGameListener());
        savedGame.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 38));
        savedGame.setForeground(Color.WHITE);
        savedGame.setFocusable(false);
        savedGame.setRolloverEnabled(false);
        savedGame.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        savedGame.setBackground(new Color(255, 255, 255, 85));
        savedGame.setMaximumSize(new Dimension(0, 0));

        setLayout(null);
        newGame.setBounds(290, 200, 200, 50);
        savedGame.setBounds(285, 280, 211, 50);
        add(newGame);
        add(savedGame);
    }
    
    /**
     * @author AmazingPenguins
     * @version 0.01
     */
    private class NewGameListener implements ActionListener {
        /**
         * Method actionPerformed.
         * @param event ActionEvent
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            gc.goToState(GameController.State.NEWPLAYER);
        }
        
        /**
         * toString
         * @return String
         */
        @Override
        public String toString() {
            return "New Game Listener";
        }
    }

    /**
     * @author AmazingPenguins
     * @version 0.01
     */
    private class LoadGameListener implements ActionListener {
        /**
         * Method actionPerformed.
         * @param event ActionEvent
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            gc.loadGame();
        }
        
        /**
         * toString
         * @return String
         */
        @Override
        public String toString() {
            return "Load Game Listener";
        }
    }

    /**
     * Method paintComponent.
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "Start Game Panel";
    }
}