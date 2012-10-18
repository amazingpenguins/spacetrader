import javax.swing.*;
import java.awt.*;

public class StartGamePanel extends JPanel{
    private JButton newGame, savedGame, howToPlay;
    
    public StartGamePanel(){
        newGame = new JButton("New Game");
        savedGame = new JButton("Load Game");
        howToPlay = new JButton("How To Play");
        this.setLayout(new BorderLayout());
        this.add(newGame, BorderLayout.NORTH);
        this.add(savedGame, BorderLayout.CENTER);
        this.add(howToPlay, BorderLayout.SOUTH);
    }
}