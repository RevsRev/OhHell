import javax.swing.*;
public class OhHellPanel extends JPanel {
  //the pannel within which we store the data for the OhHellGame.
  private int numPlayers;
  private int numRounds;
  private OhHellRow round;
  private OhHellRow[] rounds;

  public OhHellPanel(int numPlayers, int numRounds) {
    super();
    this.numPlayers = numPlayers;
    this.numRounds = numRounds;
    rounds = new OhHellRow[numRounds];

    round = new OhHellRow(4,12);
    rounds[0] = round;
    add(rounds[0]);

    setSize(numPlayers*80 + 80, numRounds*40 + 40);
    setVisible(true);
    setLayout(null); //change this later.
  }

}
