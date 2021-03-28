package br.com.agencia34.calc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calc extends JFrame {

  public Calc() {

    adjustLayout();

    setSize(232, 318);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);

  }

  private void adjustLayout() {
    setLayout(new BorderLayout());

    Display display = new Display();
    display.setPreferredSize(new Dimension(233, 60));
    add(display, BorderLayout.NORTH);

    Keyboard keyboard = new Keyboard();
    add(keyboard, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    new Calc();
  }
}
