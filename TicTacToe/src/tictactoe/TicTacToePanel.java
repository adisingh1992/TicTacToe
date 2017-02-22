package tictactoe;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TicTacToePanel extends JPanel{

    protected static JButton[][] buttons = new JButton[3][3];
    
    public TicTacToePanel(int buttonSize, ActionListener listener) {
        setLayout(new GridLayout(3, 3));

        Random random = new Random();
        
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setSize(buttonSize/3, buttonSize/3);
                buttons[i][j].putClientProperty("INDEX", new Integer[]{i, j});
                buttons[i][j].putClientProperty("OWNER", random.nextInt());
                buttons[i][j].addActionListener(listener);
                add(buttons[i][j]);
            }
        }
    }
}