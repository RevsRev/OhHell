import javax.swing.*;

public class OhHell {

  private JFrame ohHellFrame = new JFrame("Oh Hell!");
  private OhHellPanel ohHellPanel;

  public OhHell(int numPlayers, int numRounds) {
    ohHellPanel = new OhHellPanel(numPlayers, numRounds);
    ohHellFrame.setSize(1000,1000);
    ohHellFrame.add(ohHellPanel);
    ohHellFrame.setLayout(null);
    ohHellFrame.setVisible(true);
    ohHellFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String []args) {
    OhHell ohHell = new OhHell(4,12);
    ohHell.ohHellPanel.play();
  }

}
