package br.com.agencia34.calc.view;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.agencia34.calc.model.Memory;

@SuppressWarnings("serial")
public class Keyboard extends JPanel implements ActionListener {
  private final Color GRAY = new Color(68, 68, 68);
  private final Color GRAY_WHITE = new Color(99, 99, 99);
  private final Color ORANGE = new Color(243, 163, 60);

  public Keyboard() {

    GridBagLayout laytout = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    setLayout(laytout);

    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;

    // line 01

    addButtons("AC", GRAY, c, 0, 0);
    addButtons("±", GRAY, c, 1, 0);
    addButtons("%", GRAY, c, 2, 0);
    addButtons("÷", ORANGE, c, 3, 0);

    // line 02
    addButtons("7", GRAY_WHITE, c, 0, 1);
    addButtons("8", GRAY_WHITE, c, 1, 1);
    addButtons("9", GRAY_WHITE, c, 2, 1);
    addButtons("×", ORANGE, c, 3, 1);

    // line 03
    addButtons("4", GRAY_WHITE, c, 0, 2);
    addButtons("5", GRAY_WHITE, c, 1, 2);
    addButtons("6", GRAY_WHITE, c, 2, 2);
    addButtons("-", ORANGE, c, 3, 2);

    // line 04
    addButtons("1", GRAY_WHITE, c, 0, 3);
    addButtons("2", GRAY_WHITE, c, 1, 3);
    addButtons("3", GRAY_WHITE, c, 2, 3);
    addButtons("+", ORANGE, c, 3, 3);

    // line 05
    c.gridwidth = 2;
    addButtons("0", GRAY_WHITE, c, 0, 4);
    c.gridwidth = 1;
    addButtons(",", GRAY_WHITE, c, 2, 4);
    addButtons("=", ORANGE, c, 3, 4);

  }

  private void addButtons(String text, Color color, GridBagConstraints c, int x, int y) {
    c.gridx = x;
    c.gridy = y;
    Button button = new Button(text, color);
    button.addActionListener(this);
    add(button, c);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof JButton) {
      JButton button = (JButton) e.getSource();
      Memory.getInstance().processCommand(button.getText());
    }
  }
}
