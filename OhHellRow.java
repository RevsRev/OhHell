import javax.swing.*;
import java.awt.*; //for the grid layout.
import java.awt.event.*; //for event handling.

public class OhHellRow extends JPanel {
  private int numPeople;
  private int roundNumber;
  private int roundCards; //the number of cards dealt to each player in the round.

  private JLabel roundLabel;
  private SpinnerModel spinnerModel;
  private JSpinner[] roundScores;
  private JButton submitButton = new JButton("Submit Bets");

  public OhHellRow(int numPeople, int roundNumber, int roundCards) {
    super();
    this.numPeople = numPeople;
    this.roundNumber = roundNumber;
    this.roundCards = roundCards;
    initialisePanel();
  }

  public void setDealer(int dealerIndex) {
    roundScores[dealerIndex].getEditor().getComponent(0).setBackground(Color.green);
  }

  private void initialiseLabels() {
    roundLabel = new JLabel(Integer.toString(roundNumber));

    submitButton.setSize(80,40);

    submitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        submitRound();
      }
    });

    roundScores = new JSpinner[numPeople];

    for (int i=0; i<numPeople; i++) {
      spinnerModel = new SpinnerNumberModel(0,0,roundCards,1);
      roundScores[i] = new JSpinner(spinnerModel);
    }
  }

  public void submitRound() {
    int counter = 0;
    for (int i=0; i<numPeople; i++) {
      counter += (Integer)roundScores[i].getValue();
    }
    if (counter == roundCards) {
      //code here to have popup box.
    } else {
      for (int i=0; i<numPeople; i++) {
        //we lock the row for editing once the bets have been placed.
        counter = (Integer)roundScores[i].getValue();
        roundScores[i].setEditor(new JSpinner.DefaultEditor(roundScores[i]));
        spinnerModel = new SpinnerNumberModel(counter,0,roundCards,0);
        roundScores[i].setModel(spinnerModel);
      }
    }
  }

  private void initialisePanel() {

    initialiseLabels();
    setLayout(new GridLayout(1, numPeople+2, 10, 10));

    add(roundLabel);

    for (int i=0; i<numPeople; i++) {
      add(roundScores[i]);
    }

    add(submitButton);

    setSize(200*numPeople, 40);
    setVisible(true);
  }
}
