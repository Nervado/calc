package br.com.agencia34.calc.model;

import java.util.ArrayList;
import java.util.List;

public class Memory {
  private static final Memory instance = new Memory();

  private enum OperationTypes {
    AC, PLUSMINUS, PERCENT, DIV, MULT, MINUS, PLUS, EQUALS, COMA, NUMBER
  }

  private final List<MemoryObserver> observers = new ArrayList<>();

  private OperationTypes last = null;
  private String currentText = "";
  private String buffer = "";
  private boolean replace = false;

  private Memory() {
  }

  public static Memory getInstance() {
    return instance;
  }

  public String getCurrentText() {
    return currentText.isEmpty() ? "0" : currentText;
  }

  public void addObserver(MemoryObserver mo) {
    observers.add(mo);
  }

  public void processCommand(String text) {
    OperationTypes type = detectCommandType(text);
    if (type == null) {
      return;
    } else if (type == OperationTypes.AC) {
      currentText = "";
      buffer = "";
      replace = false;
      last = null;
    } else if (type == OperationTypes.PLUSMINUS && currentText.contains("-")) {
      currentText = currentText.substring(1);
    } else if (type == OperationTypes.PLUSMINUS && !currentText.contains("-")) {
      currentText = "-" + currentText;
    } else if (type == OperationTypes.NUMBER || type == OperationTypes.COMA) {
      if (type == OperationTypes.COMA && currentText == "") {
        currentText = "0";
      }
      currentText = replace ? text : currentText + text;
      replace = false;
    } else {
      replace = true;
      currentText = getResultOfOperation();
      buffer = currentText;
      last = type;
    }

    observers.forEach(o -> o.valueHasChange(getCurrentText()));
  }

  private String getResultOfOperation() {
    if (last == null || last == OperationTypes.EQUALS) {
      return currentText;
    }

    double bufferNumber = Double.parseDouble(buffer.replace(",", "."));
    double currentNumber = Double.parseDouble(currentText.replace(",", "."));

    double result = 0;

    if (last == OperationTypes.PLUS) {
      result = bufferNumber + currentNumber;
    } else if (last == OperationTypes.MINUS) {
      result = bufferNumber - currentNumber;
    } else if (last == OperationTypes.MULT) {
      result = bufferNumber * currentNumber;
    } else if (last == OperationTypes.DIV) {
      result = bufferNumber / currentNumber;
    }

    String r = Double.toString(result).replace(".", ",");
    boolean inter = r.endsWith(",0");

    return inter ? r.replace(",0", "") : r;
  }

  private OperationTypes detectCommandType(String text) {

    if (currentText.isEmpty() && text == "0") {
      return null;
    }

    try {
      Integer.parseInt(text);
      return OperationTypes.NUMBER;
    } catch (NumberFormatException n) {
      if ("AC".equals(text)) {
        return OperationTypes.AC;
      } else if ("±".equals(text)) {
        return OperationTypes.PLUSMINUS;
      } else if ("÷".equals(text)) {
        return OperationTypes.DIV;
      } else if ("×".equals(text)) {
        return OperationTypes.MULT;
      } else if ("+".equals(text)) {
        return OperationTypes.PLUS;
      } else if ("-".equals(text)) {
        return OperationTypes.MINUS;
      } else if ("=".equals(text)) {
        return OperationTypes.EQUALS;
      } else if (",".equals(text) && !currentText.contains(",")) {
        return OperationTypes.COMA;
      } else if ("%".equals(text)) {
        return OperationTypes.PERCENT;
      }

    }
    return null;
  }
}
