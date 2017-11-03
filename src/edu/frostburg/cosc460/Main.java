package edu.frostburg.cosc460;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

/**
 * Created by Patrick
 */
public class Main {

    public static void main(String args[]) throws FileNotFoundException {
        //String[] referenceString = {"7","0","1","2","0","3","0","4","2","3","0","3","0","3","2","1","2","0","1","7","0","1"};

        File file = new File("output.txt");
        FileOutputStream output = new FileOutputStream(file);
        PrintStream out = new PrintStream(output);
        System.setOut(out); //all output will be sent to an output text file for easier readability

        Tester testClass = new Tester();

        String referenceString1 = "7,0,1,2,0,3,0,4,2,3,0,3,0,3,2,1,2,0,1,7,0,1"; //the shorter of the two strings in the powerpoint
        String referenceString2 = "2,2,2,2,2,3,3,3,4,4,2,2,2,5,5,5,5,9,3,2";    // custom string

        //test 1: reference string from the powerpoint slides, frame size of 3
        System.out.println("~~~~~TEST 1~~~~~");
        testClass.Test(referenceString1, 3);
        System.out.println("~~~~~END TEST 1~~~~~");
        System.out.println(" ");

        //test 2: a string from the powerpoint, frame size of 4
        System.out.println("~~~~~TEST 2~~~~~");
        testClass.Test(referenceString1, 4);
        System.out.println("~~~~~END TEST 2~~~~~");
        System.out.println(" ");

        //test 3: custom string, frame size of 5
        System.out.println("~~~~~TEST 3~~~~~");
        testClass.Test(referenceString2, 5);
        System.out.println("~~~~~END TEST 3~~~~~");
        System.out.println(" ");

        //test 4: Randomized String
        Random intGenerator = new Random(System.currentTimeMillis());
        String referenceString3 = "";
        for(int i = 0; i < 19; i++){
            referenceString3 = referenceString3 + intGenerator.nextInt(10) + ",";
        }
        referenceString3 = referenceString3 + intGenerator.nextInt(10);

        System.out.println("~~~~~TEST 4~~~~~");
        testClass.Test(referenceString3, 3);
        System.out.println("~~~~~END TEST 4~~~~~");
        System.out.println(" ");

        //test 5: user input
        //Warning: This does not have proper error checking to prevent the entry of letters or anything else other than what is shown by the example strings above
        //The only error checking it does is trimming off extra white space before sending the string off through the tester.
        System.out.println("~~~~~TEST 5~~~~~");
        String refString = JOptionPane.showInputDialog("Please input the reference string, separating each page number with a comma:");
        String frameSize = JOptionPane.showInputDialog("Please input the frame size. No decimals, please.");

        testClass.Test(refString.trim(), Integer.parseInt(frameSize.trim()));
        System.out.println("~~~~~END TEST 5~~~~~");
        System.out.println(" ");
    }

}
