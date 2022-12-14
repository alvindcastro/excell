package org.example;

public class Spreadsheet {

  CellValue[][] cells;

  public Spreadsheet(int H, char W) {
    cells = new CellValue[H][W - 'A' + 1];
  }

  public int getCellValue(String item) {
    char col = item.charAt(0);
    int row = Integer.parseInt(item.substring(1));
    return cells[row - 1][col - 'A'] == null ? 0 : cells[row - 1][col - 'A'].getIntValue();
  }

  private int get(int row, char col) {
    return cells[row - 1][col - 'A'] == null ? 0 : cells[row - 1][col - 'A'].getIntValue();
  }

  private void sum(int row, char col, String[] strs) {
    CellValue val = new CellSummation(this, strs);
    cells[row - 1][col - 'A'] = val;
    val.getIntValue();
  }

  public void setCellValue(String item, Object obj) {
    char col = item.charAt(0);
    int row = Integer.parseInt(item.substring(1));

    if (obj instanceof Integer) {
      int v = (Integer) obj;
      cells[row - 1][col - 'A'] = new StaticValue(v);
      cells[row - 1][col - 'A'].getIntValue();
    } else {
      sum(row, col, (String[]) obj);
    }
  }

  static class StaticValue implements CellValue {

    private final int value;

    StaticValue(int value) {
      this.value = value;
    }

    public int getIntValue() {
      return value;
    }

  }

  static class CellSummation implements CellValue {

    String[] items;
    Spreadsheet e;

    CellSummation(Spreadsheet e, String[] items) {
      this.e = e;
      this.items = items;
    }

    public int getIntValue() {
      int sum = 0;
      for (String item : items) {
        if (item.contains(":")) {
          String[] parts = item.split(":");

          char col1 = parts[0].charAt(0);
          int row1 = Integer.parseInt(parts[0].substring(1));
          char col2 = parts[1].charAt(0);
          int row2 = Integer.parseInt(parts[1].substring(1));

          sum = getSumFromRowsAndCols(sum, col1, row1, col2, row2);
        } else {
          char col = item.charAt(0);
          int row = Integer.parseInt(item.substring(1));

          sum += e.get(row, col);
        }
      }
      return sum;
    }

    private int getSumFromRowsAndCols(int sum, char col1, int row1, char col2, int row2) {
      if (col2 >= col1 && row2 >= row1) {
        for (int row = row1; row <= row2; row++) {
          for (char col = col1; col <= col2; col++) {
            sum += e.get(row, col);
          }
        }
      }
      return sum;
    }
  }
}
