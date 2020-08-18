import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class OhHellPanel extends JPanel {
  //the pannel within which we store the data for the OhHellGame.
  private int numPlayers;
  private int numRounds;
  private OhHellRow round;
  private OhHellRow[] rounds;

  private Random rand = new Random();

  public OhHellPanel(int numPlayers, int numRounds) {
    super();
    this.numPlayers = numPlayers;
    this.numRounds = numRounds;
    rounds = new OhHellRow[numRounds];

    /*rounds[0] = new OhHellRow(4,1,12,"scoring");
    rounds[0].setDealer(2);
    add(rounds[0]); */

    /*round = new OhHellRow(4, 1, 12, "scoring");
    round.setDealer(2);
    rounds[0] = round;
    add(rounds[0]); */

    setSize((numPlayers + 2)*200, numRounds*40 + 40);
    setVisible(true);
    setLayout(new GridLayout(numRounds, 1)); //change this later.
  }

  public void play() {

    int dealerIndex = rand.nextInt(numPlayers);

    for (int roundCounter =0; roundCounter< numRounds; roundCounter++) {

      rounds[roundCounter] = new OhHellRow(numPlayers, roundCounter, numRounds - roundCounter, "betting");
      rounds[roundCounter].setDealer((dealerIndex + roundCounter )%numPlayers);
      add(rounds[roundCounter]);

      this.revalidate();
      this.repaint();

      while (!rounds[roundCounter].submitted) {
        continue;
      }

    }

  }

}
