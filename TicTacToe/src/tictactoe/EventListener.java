package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static tictactoe.TicTacToePanel.buttons;

public class EventListener implements ActionListener {

    private int turn = 1;
    private int turnCount = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton)
            nextMove((JButton) e.getSource());
    }

    private void nextMove(JButton button) {
        button.putClientProperty("OWNER", turn);
        Integer[] index = (Integer[]) button.getClientProperty("INDEX");

        Icon turnIcon = new ImageIcon(getClass().getResource(turn+".png"));
        button.setIcon(turnIcon);
        button.setDisabledIcon(turnIcon);
        
        button.setEnabled(false);

        turnCount++;

        if (checkResult(index)){
            JOptionPane.showMessageDialog(null, "Player " + turn + " Wins..!!");
            initLayout();
            return;
        }
        else {
            if (9 == turnCount) {
                JOptionPane.showMessageDialog(null, "It's a Draw..!!");
                initLayout();
                return;
            }
        }

        turn = (1 == turn) ? 2 : 1;
        TicTacToe.turnStatus.setText("Player " + turn + "'s Turn");
    }

    private boolean checkResult(Integer[] index) {
        Integer firstIndex = index[0];
        Integer secondIndex = index[1];
        int i;

        //Checking for a row victory
        for (i = 0; i < 3; i++)
            if (getOwner(buttons[firstIndex][secondIndex]) != getOwner(buttons[firstIndex][i]))
                break;

        if (3 == i) return true;
        
        //Checking for a column victory
        for (i = 0; i < 3; i++)
            if (getOwner(buttons[firstIndex][secondIndex]) != getOwner(buttons[i][secondIndex]))
                break;

        if (3 == i) return true;

        //Checking for a left diagonal victory
        if ((0 == firstIndex && 0 == secondIndex) || (1 == firstIndex && 1 == secondIndex) || (2 == firstIndex && 2 == secondIndex))
            for (i = 0; i < 3; i++)
                if (getOwner(buttons[firstIndex][secondIndex]) != getOwner(buttons[i][i]))
                    break;
                    
        if (3 == i) return true;

        //Checking for a right diagonal victory
        return (getOwner(buttons[firstIndex][secondIndex]) == getOwner(buttons[0][2]))
                && (getOwner(buttons[firstIndex][secondIndex]) == getOwner(buttons[1][1]))
                && (getOwner(buttons[firstIndex][secondIndex]) == getOwner(buttons[2][0]));
    }

    private int getOwner(JButton button) {
        return (Integer) button.getClientProperty("OWNER");
    }
    
    protected void initLayout(){
        
        String confirmationMessage = "Wanna Play Again..??";
        String title = "Play Or Quit";
        int reply = JOptionPane.showConfirmDialog(null, confirmationMessage, title, JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.NO_OPTION)
            System.exit(0);
        
        Random random = new Random();
        
        turn = 1;
        turnCount = 0;
        TicTacToe.turnStatus.setText("Player " + turn + "'s Turn");
        
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                buttons[i][j].putClientProperty("OWNER", random.nextInt());
                buttons[i][j].setEnabled(true);
                buttons[i][j].setIcon(null);
            }
        }
    }
}