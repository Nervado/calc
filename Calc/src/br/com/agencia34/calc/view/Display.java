package br.com.agencia34.calc.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.agencia34.calc.model.Memory;
import br.com.agencia34.calc.model.MemoryObserver;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoryObserver {

  private Color BG = new Color(46, 49, 50);

  private JLabel label;

  public Display() {
    setBackground(BG);
    Memory.getInstance().addObserver(this);

    label = new JLabel(Memory.getInstance().getCurrentText());
    label.setForeground(Color.WHITE);
    label.setFont(new Font("courier", Font.PLAIN, 30));

    setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));

    add(label);
  }

  @Override
  public void valueHasChange(String newValue) {

    label.setText(newValue);
  }

}
