/*
Author: Jimmy Li, #29903320, NetID: jli119
Date: February 18, 2017
Course: CSC172, Professor Ted Pawlicki
Assignment: Project1 - Mastermind
Title: PlayMastermind.java
*/

import java.util.*;

public class PlayMastermind {

  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    String[] defaultArr = {""};
    PlayMastermind play = new PlayMastermind();
    Mastermind mm = new Mastermind(defaultArr, 0);
    do {
      String[] tokencolors = {"RED", "BLUE", "GREEN", "YELLOW", "ORANGE", "PURPLE"};
      System.out.println("\nWELCOME, CODEMAKER. MY NAME IS CODY THE CODEBREAKER. LET'S PLAY MASTERMIND.\n");
      int numberOfColors = play.getColors(scanner);
      int numberOfPositions = play.getPositions(scanner);
      tokencolors = Arrays.copyOfRange(tokencolors, 0, numberOfColors);
      mm = new Mastermind(tokencolors, numberOfPositions);
      mm.createAllCombos(play.encode(numberOfColors));
      int[] moveResponse = {0,0};
      System.out.println("\nYour code: " + play.decode(mm.createCode(scanner), tokencolors));
      while (!mm.isGameOver()){
          System.out.println("\nCody's guess: " + play.decode(mm.nextMove(), tokencolors));
          moveResponse = mm.checkGuess(mm.nextMove());
          System.out.println("YOUR RESPONSE:");
          System.out.println("Black Pegs (Correct Color, Correct Position): " + (moveResponse[1]));
          System.out.println("White Pegs (Correct Color, Incorrect Position): " + (moveResponse[0]));
          System.out.println();
          mm.response(moveResponse[0],moveResponse[1]);
      }
    } while (mm.playAgain());
    scanner.close();
  }

  public int getColors(Scanner sc) {
    int colors; // The number of types of colors possibly in the code
    do {
      System.out.print("Please enter the number of colors you would like in your code (Minimum: 1, Maximum: 6): ");
      colors = sc.nextInt();
    } while ((colors > 6) || (colors < 1));
    return colors;
  }

  public int getPositions(Scanner sc) {
    int positions; // The number of positions in the code
    do {
      System.out.print("\nPlease enter the number of positions you would like in your code (Minimum: 1, Maximum: 6): ");
      positions = sc.nextInt();
    } while ((positions > 6) || (positions < 1));
    return positions;
  }

  public String[] encode(int numColors){
    String[] numberCodes = new String[numColors];
    for (int h = 0; h < numColors; h++) {
      numberCodes[h] = "" + h;
    }
    return numberCodes;
  }

  public static String decode(String encoded, String[] tokencolors){
    for (int i = 0; i < encoded.length(); i++){
      switch (encoded.charAt(i)) {
        case '0':
          encoded = encoded.substring(0,i) + tokencolors[0] + " " + encoded.substring(i+1);
          i--;
          break;
        case '1':
          encoded = encoded.substring(0,i) + tokencolors[1] + " " + encoded.substring(i+1);
          i--;
          break;
        case '2':
          encoded = encoded.substring(0,i) + tokencolors[2] + " " + encoded.substring(i+1);
          i--;
          break;
        case '3':
          encoded = encoded.substring(0,i) + tokencolors[3] + " " + encoded.substring(i+1);
          i--;
          break;
        case '4':
          encoded = encoded.substring(0,i) + tokencolors[4] + " " + encoded.substring(i+1);
          i--;
          break;
        case '5':
          encoded = encoded.substring(0,i) + tokencolors[5] + " " + encoded.substring(i+1);
          i--;
          break;
        default:
      }
    }
    return encoded;
  }

}
