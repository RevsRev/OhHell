import javax.swing.*;
import java.awt.*; //for the grid layout.

public class OhHellRow extends JPanel {
  private int numPeople;
  private int roundNumber;

  private JLabel roundLabel;
  private SpinnerModel spinnerModel;
  private JSpinner[] roundScores;

  public OhHellRow(int numPeople, int roundNumber) {
    super();
    this.numPeople = numPeople;
    this.roundNumber = roundNumber;
    initialisePanel();
  }

  private void initialiseLabels() {
    roundLabel = new JLabel(Integer.toString(roundNumber));
    
    roundScores = new JSpinner[numPeople];

    for (int i=0; i<numPeople; i++) {
      spinnerModel = new SpinnerNumberModel(0,0,10,1);
      roundScores[i] = new JSpinner(spinnerModel);
    }
  }

  private void initialisePanel() {

    initialiseLabels();
    setLayout(new GridLayout(1, numPeople, 10, 10));

    add(roundLabel);

    for (int i=0; i<numPeople; i++) {
      add(roundScores[i]);
    }

    setSize(60*numPeople, 40);
    setVisible(true);
  }
}
