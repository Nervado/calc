package br.com.agencia34.calc.model;

@FunctionalInterface
public interface MemoryObserver {
  public void valueHasChange(String newValue);
}
