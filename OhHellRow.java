import javax.swing.*;
import java.awt.*; //for the grid layout.
import java.awt.event.*; //for event handling.

public class OhHellRow extends JPanel {
  private int numPeople;
  private int roundNumber;
  private int roundCards; //the number of cards dealt to each player in the round.
  private String type; //the type of row we consider - either placing bets or submitting scores.
  public boolean submitted;

  private JLabel roundLabel;
  private SpinnerModel spinnerModel;
  private JSpinner[] roundScores;
  private JButton submitButton;

  public OhHellRow(int numPeople, int roundNumber, int roundCards, String type) {
    super();
    this.type = type;
    this.numPeople = numPeople;
    this.roundNumber = roundNumber;
    this.roundCards = roundCards;
    this.submitted = false;
    initialisePanel();
  }

  public void setDealer(int dealerIndex) {
    roundScores[dealerIndex].getEditor().getComponent(0).setBackground(Color.gray);
  }

  private void initialiseLabels() throws InvalidRowTypeException {

    try {
      if (type == "betting") {
        submitButton = new JButton("Submit Bets");
      } else if (type == "scoring") {
        submitButton = new JButton("Submit Scores");
      } else {
        throw new InvalidRowTypeException("\" " + type + " \" " + "is not a valid row type");
      }
    } catch(NullPointerException e) {
      throw new InvalidRowTypeException("Row type was null");
    }
    submitButton.setSize(80,40);

    submitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        submitRound();
      }
    });

    if (type == "betting") {
      roundLabel = new JLabel(Integer.toString(roundNumber));
    } else if (type == "scoring") {
      roundLabel = new JLabel();
    }

    roundScores = new JSpinner[numPeople];

    for (int i=0; i<numPeople; i++) {
      spinnerModel = new SpinnerNumberModel(0,0,roundCards,1);
      roundScores[i] = new JSpinner(spinnerModel);
    }
  }

  public void submitRound() {
    int counter = 0;

    if (submitted) { return; }

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
      submitted = true; //once the round is classed as submitted, we may not go back.
    }
  }

  public int[] getRoundScores() {
    int[] scores = new int[roundScores.length];

    for (int i=0; i < scores.length; i++) {
      scores[i] = (Integer)roundScores[i].getValue();
    }
    return scores;
  }

  private void initialisePanel() {

    try {
      initialiseLabels();
    } catch (InvalidRowTypeException e) {
      e.printStackTrace();
      System.exit(0);
    }
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
