package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MainTest {

  static Spreadsheet spreadsheet;

  @BeforeEach
  void setUp() {
    // Creates a blank 3x4 spreadsheet
    spreadsheet = new Spreadsheet(3, 'D');

    // Fill with data
    spreadsheet.setCellValue("A1", 1);
    spreadsheet.setCellValue("A2", 1);
    spreadsheet.setCellValue("A3", 1);

    spreadsheet.setCellValue("B1", 1);
    spreadsheet.setCellValue("B2", 1);
    spreadsheet.setCellValue("B3", 1);

    spreadsheet.setCellValue("C1", 1);
    spreadsheet.setCellValue("C2", 1);
    spreadsheet.setCellValue("C3", 1);
  }

  @DisplayName("Compute based on given parameters")
  @ParameterizedTest
  @ValueSource(strings = {"A1:A3", "B1:B3", "C1:C3"})
  void edge_should_add_all_rows_based_on_params(String param) {
    String[] strings = new String[1];
    strings[0] = param;
    spreadsheet.setCellValue("D1", strings);
    Assertions.assertEquals(3, spreadsheet.getCellValue("D1"));
  }

  @DisplayName("Compute based on multiple parameters")
  @Test
  void edge_should_add_based_on_params() {
    String[] strings = new String[3];
    strings[0] = "A1";
    strings[1] = "B1:B3";
    strings[2] = "C2";
    spreadsheet.setCellValue("D1", strings);
    Assertions.assertEquals(5, spreadsheet.getCellValue("D1"));
  }

  @DisplayName("Compute all data in spreadsheet")
  @Test
  void edge_should_add_all_A_to_C_rows() {
    String[] strings = new String[1];
    strings[0] = "A1:C3";
    spreadsheet.setCellValue("D1", strings);
    Assertions.assertEquals(9, spreadsheet.getCellValue("D1"));
  }

  @DisplayName("Tests for the coding questions")
  @Test
  void given_tests() {
    Spreadsheet sheet = new Spreadsheet(4, 'A');
    sheet.setCellValue("A1", 13);
    Assertions.assertEquals(13, sheet.getCellValue("A1"));

    sheet.setCellValue("A2", 14);

    String[] strings = new String[1];
    strings[0] = "A1:A2";
    sheet.setCellValue("A3", strings);
    Assertions.assertEquals(27, sheet.getCellValue("A3"));

    // Commenting below line will follow the test case but will have an exception
    sheet.setCellValue("A3", sheet.getCellValue("A3"));
    strings[0] = "A1:A3";
    sheet.setCellValue("A4", strings);
    Assertions.assertEquals(54, sheet.getCellValue("A4"));
  }
}
