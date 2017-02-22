package tictactoe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TicTacToe {

    private final int layoutSize = 690;

    protected static JTextField turnStatus;
    
    public TicTacToe() {
        JFrame frame = new JFrame("TicTacToe");
        ActionListener listener = new EventListener();

        TicTacToePanel ticTacToePanel = new TicTacToePanel(layoutSize, listener);

        frame.setLayout(new BorderLayout());
        frame.setSize(layoutSize, layoutSize);

        turnStatus = new JTextField("Player 1's Turn");
        turnStatus.setEditable(false);
        frame.add(turnStatus, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.add(ticTacToePanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TicTacToe ticTacToe = new TicTacToe();
            }
        });
    }
}