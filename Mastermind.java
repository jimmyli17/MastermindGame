/*
Author: Jimmy Li, #29903320, NetID: jli119
Date: February 18, 2017
Course: CSC172, Professor Ted Pawlicki
Assignment: Project1 - Mastermind
Title: Mastermind.java
*/

import java.util.*;

public class Mastermind {
  private String[] colors; // An array of the colors which can be used in the code
  private ArrayList<String> allCombos; // An arraylist of strings which includes all the combonations of colors and positions in the code
  private int positions; // The number of positions in the code
  private int numColors; // The number of different colors that may be used in the code
  private String usercode; // The code that the user creates
  private boolean gameOver;
  private boolean playAgain;

  public Mastermind(String[] tokencolors, int pos) {
    colors = tokencolors;
    numColors = tokencolors.length;
    positions = pos;
    int arraySize = (int)(Math.pow((double)tokencolors.length, (double)pos));
    allCombos = new ArrayList<String>();
    usercode = "999";
    gameOver = false;
    playAgain = true;
  }

  public String createCode(Scanner scanner){
    String userinput;
    String code = "";
    boolean repeat = false;
    userinput = scanner.nextLine();

    for (int count = 1; count <= positions; count++){
      do {
        switch (numColors) {
          case 1:
            System.out.print("\nPlease enter a color for POSITION NUMBER " + count + " of your code (Possible inputs: 'RED'): ");
            userinput = scanner.nextLine();
            repeat = !(userinput.equals(colors[0]));
            break;
          case 2:
            System.out.print("\nPlease enter a color for POSITION NUMBER " + count + " of your code (Possible inputs: 'RED' & 'BLUE'): ");
            userinput = scanner.nextLine();
            repeat = !((userinput.equals(colors[0])) || (userinput.equals(colors[1])));
            break;
          case 3:
            System.out.print("\nPlease enter a color for POSITION NUMBER " + count + " of your code (Possible inputs: 'RED', 'BLUE', & 'GREEN'): ");
            userinput = scanner.nextLine();
            repeat = !((userinput.equals(colors[0])) || (userinput.equals(colors[1])) || (userinput.equals(colors[2])));
            break;
          case 4:
            System.out.print("\nPlease enter a color for POSITION NUMBER " + count + " of your code (Possible inputs: 'RED', 'BLUE', 'GREEN', & 'YELLOW'): ");
            userinput = scanner.nextLine();
            repeat = !((userinput.equals(colors[0])) || (userinput.equals(colors[1])) || (userinput.equals(colors[2])) || (userinput.equals(colors[3])));
            break;
          case 5:
            System.out.print("\nPlease enter a color for POSITION NUMBER " + count + " of your code (Possible inputs: 'RED', 'BLUE', 'GREEN', 'YELLOW', & 'ORANGE'): ");
            userinput = scanner.nextLine();
            repeat = !((userinput.equals(colors[0])) || (userinput.equals(colors[1])) || (userinput.equals(colors[2])) || (userinput.equals(colors[3])) || (userinput.equals(colors[4])));
            break;
          case 6:
            System.out.print("\nPlease enter a color for POSITION NUMBER " + count + " of your code (Possible inputs: 'RED', 'BLUE', 'GREEN', 'YELLOW', 'ORANGE', 'PURPLE'): ");
            userinput = scanner.nextLine();
            repeat = !((userinput.equals(colors[0])) || (userinput.equals(colors[1])) || (userinput.equals(colors[2])) || (userinput.equals(colors[3])) || (userinput.equals(colors[4])) || (userinput.equals(colors[5])));
            break;
          default:
        }
      } while (repeat);
      switch (userinput) {
        case "RED":
          code += "0";
          break;
        case "BLUE":
          code += "1";
          break;
        case "GREEN":
          code += "2";
          break;
        case "YELLOW":
          code += "3";
          break;
        case "ORANGE":
          code += "4";
          break;
        case "PURPLE":
          code += "5";
        default:
      }

    }
    usercode = code;
    return code;
  }

  public void createAllCombos(String[] encoder){
    for (int i = 0; i < numColors; i++) {
      String combo1 = "";
      combo1 += encoder[i];
      if (positions > 1) {
        for (int j = 0; j < numColors; j++){
          String combo2 = combo1;
          combo2 += encoder[j];
          if (positions > 2) {
            for (int k = 0; k < numColors; k++){
              String combo3 = combo2;
              combo3 += encoder[k];
              if (positions > 3){
                for (int l = 0; l < numColors; l++){
                  String combo4 = combo3;
                  combo4 += encoder[l];
                  if (positions > 4){
                    for (int m = 0; m < numColors; m++){
                      String combo5 = combo4;
                      combo5 += encoder[m];
                      if (positions > 5){
                        for (int n = 0; n < numColors; n++){
                          String combo6 = combo5;
                          combo6 += encoder[n];
                          allCombos.add(combo6);
                        }
                      }
                      else {
                        allCombos.add(combo5);
                      }
                    }
                  }
                  else {
                    allCombos.add(combo4);
                  }
                }
              }
              else {
                allCombos.add(combo3);
              }
            }
          }
          else {
            allCombos.add(combo2);
          }
        }
      }
      else {
        allCombos.add(combo1);
      }
    }
  }

  public void response(int colorsRightPositionWrong, int positionsAndColorRight) {
    int count = 0;
    if (positionsAndColorRight < positions){
      ArrayList<String> newCombos = new ArrayList<String>();
      for (int i = 1; i < allCombos.size(); i++){
        int[] comboResponse = checkCombos(allCombos.get(i));
        if (comboResponse[1] == positionsAndColorRight && comboResponse[0] == colorsRightPositionWrong){
          newCombos.add(allCombos.get(i));

          count++;
        }
      }
      allCombos = newCombos;
    }
    else {
      gameOver = true;
      newGame();
    }
  }

  public boolean isGameOver(){
    return gameOver;
  }

  public boolean playAgain(){
    return playAgain;
  }

  public void newGame() {
    Scanner sc = new Scanner(System.in);
    String answer = "";
    System.out.println("Cody broke your code. Your code is: " + PlayMastermind.decode(usercode, colors));
    do {
      System.out.println("\nWould you like to play again? (Enter 'Y' for YES and 'N' for NO) ");
      answer = sc.nextLine();
      if (answer.equals("N")){
        playAgain = false;
      }
      else {
        playAgain = true;
      }
    } while (!(answer.equals("N") || answer.equals("Y")));


  } // Reset the game

  public String nextMove() {
    if (allCombos.size() > 0){
      return allCombos.get(0);
    }
    return null;
  }

  public int[] checkCombos (String s) {
    int[] results = new int[2];
    String combo = s;
    String guess = nextMove();
    String temp;
    int positionsAndColorRight = 0;
    int colorsRightPositionWrong = 0;
    int i = 0;
    while (combo.length() > i && guess.length() > i){
      if (combo.charAt(i) == guess.charAt(i)){
        positionsAndColorRight++;
        results[1] = positionsAndColorRight;
        temp = (combo.substring(0, i) + combo.substring(i+1));
        combo = temp;
        temp = (guess.substring(0,i) + guess.substring(i+1));
        guess = temp;
        i--;
      }
      i++;
    }
    for (int j = 0; j < guess.length(); j++){
      for (int k = 0; k < combo.length(); k++){
        if (combo.charAt(k) == guess.charAt(j)) {
          colorsRightPositionWrong++;
          results[0] = colorsRightPositionWrong;
          combo = (combo.substring(0, k) + combo.substring(k+1));
          guess = (guess.substring(0, j) + guess.substring(j+1));
          if ((combo.length() == 1) && (guess.length() == 1)){
            if (combo.charAt(0) == guess.charAt(0)) {
              colorsRightPositionWrong++;
              results[0] = colorsRightPositionWrong;
            }
          }
          else {
            k--;
            if (j > 0) {
              j--;
            }
          }
        }
      }
    }
    return results;
  }

  public int[] checkGuess(String s) {
    int[] results = new int[2];
    String guess = s;
    String temp;
    int positionsAndColorRight = 0;
    int colorsRightPositionWrong = 0;
    int i = 0;
    String usercodecopy = usercode.substring(0,1) + usercode.substring(1);
    while (guess.length() > i && usercodecopy.length() > i){
      if (guess.charAt(i) == usercodecopy.charAt(i)){
        positionsAndColorRight++;
        results[1] = positionsAndColorRight;
        temp = (guess.substring(0, i) + guess.substring(i+1));
        guess = temp;
        temp = (usercodecopy.substring(0,i) + usercodecopy.substring(i+1));
        usercodecopy = temp;
        i--;
      }
      i++;
    }
    for (int j = 0; j < usercodecopy.length(); j++){
      for (int k = 0; k < guess.length(); k++){
        if (guess.charAt(k) == usercodecopy.charAt(j)) {
          colorsRightPositionWrong++;
          results[0] = colorsRightPositionWrong;
          guess = (guess.substring(0, k) + guess.substring(k+1));
          usercodecopy = (usercodecopy.substring(0, j) + usercodecopy.substring(j+1));
          if ((guess.length() == 1) && (usercodecopy.length() == 1)){
            if (guess.charAt(0) == usercodecopy.charAt(0)) {
            colorsRightPositionWrong++;
            results[0] = colorsRightPositionWrong;
            }
          }
          else {
            k--;
            if (j > 0) {
              j--;
            }
          }
        }
      }
    }
    return results;
  }

}
