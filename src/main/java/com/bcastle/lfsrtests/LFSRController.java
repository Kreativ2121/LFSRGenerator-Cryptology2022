package com.bcastle.lfsrtests;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.security.SecureRandom;
import java.util.*;

import static java.lang.Math.pow;

@SuppressWarnings("ALL")
public class LFSRController {
    @FXML
    private TextField const1;

    @FXML
    private TextField const2;

    @FXML
    private TextField const3;

    @FXML
    private TextField function1;

    @FXML
    private TextField function2;

    @FXML
    private TextField function3;

    @FXML
    private TextField length1;

    @FXML
    private TextField length2;

    @FXML
    private TextField length3;

    @FXML
    private TextField register1;

    @FXML
    private TextField register2;

    @FXML
    private TextField register3;

    @FXML
    private TextArea output1;

    @FXML
    private TextArea output2;

    @FXML
    private TextArea output3;

    @FXML
    private TextArea geffe;

    @FXML
    private TextArea shrinking;

    @FXML
    private TextArea stopAndGo;

    @FXML
    private Label labelLongRuns1;

    @FXML
    private Label labelLongRuns2;

    @FXML
    private Label labelLongRuns3;

    @FXML
    private Label labelPoker1;

    @FXML
    private Label labelPoker2;

    @FXML
    private Label labelPoker3;

    @FXML
    private Label labelRuns1;

    @FXML
    private Label labelRuns2;

    @FXML
    private Label labelRuns3;

    int shuffleAmount = 20000;
    @FXML
    void onButtonGenerate1(ActionEvent event) {
        SecureRandom rand = new SecureRandom();

        String str1 = length1.getText();
        int co1 = Integer.parseInt(str1);

        String str2 = const1.getText();
        int con1 = Integer.parseInt(str2);

        String str3 = function1.getText();
        char[] charArray1 = stringToCharArray(str3);
        int[] fun1 = charArrayToIntArray(charArray1);

        String str4 = register1.getText();
        char[] charArray2 = stringToCharArray(str4);
        int[] reg1 = charArrayToIntArray(charArray2);

        //Counting LFSR
        int[] output = new int[shuffleAmount];
        Arrays.fill(output,-1);

        List<Integer> regList = new ArrayList<>();
        for (int i = 0; i < reg1.length; i++) {
            regList.add(reg1[i]);
        }

        for(int i=0;i<shuffleAmount;i++)
        {
            //Calculating next value
            int nextVal = LFSR_XOR(fun1, con1, regList);
            output[i] = nextVal;

            regList.remove(0);
            regList.add(nextVal);
        }

        output1.setText(Arrays.toString(output));

        //DEBUG
//        System.out.println(co1);
//        System.out.println(con1);
//        System.out.println(Arrays.toString(fun1));
//        System.out.println(Arrays.toString(reg1));
    }

    @FXML
    void onButtonGenerate2(ActionEvent event) {
        SecureRandom rand = new SecureRandom();

        String str1 = length2.getText();
        int co2 = Integer.parseInt(str1);

        String str2 = const2.getText();
        int con2 = Integer.parseInt(str2);

        String str3 = function2.getText();
        char[] charArray1 = stringToCharArray(str3);
        int[] fun2 = charArrayToIntArray(charArray1);

        String str4 = register2.getText();
        char[] charArray2 = stringToCharArray(str4);
        int[] reg2 = charArrayToIntArray(charArray2);

        //Counting LFSR
        int[] output = new int[shuffleAmount];
        Arrays.fill(output,-1);

        List<Integer> regList = new ArrayList<>();
        for (int i = 0; i < reg2.length; i++) {
            regList.add(reg2[i]);
        }

        for(int i=0;i<shuffleAmount;i++)
        {
            //Calculating next value
            int nextVal = LFSR_XOR(fun2, con2, regList);
            output[i] = nextVal;

            regList.remove(0);
            regList.add(nextVal);
        }

        output2.setText(Arrays.toString(output));
    }

    @FXML
    void onButtonGenerate3(ActionEvent event) {
        SecureRandom rand = new SecureRandom();

        String str1 = length3.getText();
        int co3 = Integer.parseInt(str1);

        String str2 = const3.getText();
        int con3 = Integer.parseInt(str2);

        String str3 = function3.getText();
        char[] charArray1 = stringToCharArray(str3);
        int[] fun3 = charArrayToIntArray(charArray1);

        String str4 = register3.getText();
        char[] charArray2 = stringToCharArray(str4);
        int[] reg3 = charArrayToIntArray(charArray2);

        //Counting LFSR
        int[] output = new int[shuffleAmount];
        Arrays.fill(output,-1);

        List<Integer> regList = new ArrayList<>();
        for(int i = 0; i < reg3.length; i++) {
            regList.add(reg3[i]);
        }

        for(int i=0;i<shuffleAmount;i++)
        {
            //Calculating next value
            int nextVal = LFSR_XOR(fun3, con3, regList);
            output[i] = nextVal;

            regList.remove(0);
            regList.add(nextVal);
        }

        output3.setText(Arrays.toString(output));
    }

    @FXML
    void onButtonSetPlain(ActionEvent event) {
        length1.setText("5");
        length2.setText("5");
        length3.setText("5");
        const1.setText("0");
        const2.setText("1");
        const3.setText("1");
        function1.setText("01101");
        function2.setText("10011");
        function3.setText("00111");
        register1.setText("11010");
        register2.setText("01110");
        register3.setText("01011");
    }

    @FXML
    void onButtonSetComplex(ActionEvent event) {
        length1.setText("16");
        length2.setText("16");
        length3.setText("16");
        const1.setText("1");
        const2.setText("1");
        const3.setText("0");
        function1.setText("0100110101001110");
        function2.setText("1001100011011001");
        function3.setText("0011111010101110");
        register1.setText("1101011011011010");
        register2.setText("0111011010101101");
        register3.setText("0101011010111010");
    }

    @FXML
    void onButtonRunGenerators(ActionEvent event) {
        //INFO Reading data from LFSR1, LFSR2 and LFSR3
        String in1 = output1.getText();
        String in2 = output2.getText();
        String in3 = output3.getText();
        //regex removing special characters from Arrays.toString
        in1 = in1.replaceAll("[^a-zA-Z0-9]","");
        in2 = in2.replaceAll("[^a-zA-Z0-9]","");
        in3 = in3.replaceAll("[^a-zA-Z0-9]","");
        char[] inch1 = stringToCharArray(in1);
        char[] inch2 = stringToCharArray(in2);
        char[] inch3 = stringToCharArray(in3);
        int[] x1 = charArrayToIntArray(inch1);
        int[] x2 = charArrayToIntArray(inch2);
        int[] x3 = charArrayToIntArray(inch3);

        //INFO Geffe Generator
        int[] gef = new int[shuffleAmount];
        for (int i = 0; i < x1.length; i++) {
            int temp;
            if(x2[i] == 0){
                temp = (x1[i]*x2[i])^(1*x3[i]);
            }else{
                temp = (x1[i]*x2[i])^(0*x3[i]);
            }
            gef[i] = temp;
        }
        geffe.setText(Arrays.toString(gef));

        //INFO Stop-And-Go
        int[] sng = new int[shuffleAmount];
        for (int i = 0; i < x1.length; i++) {
            int temp = x1[i]*x2[i];
            if(temp == 1){
                sng[i] = x2[i]^x3[i];
            }else{
                sng[i] = x3[i];
            }
        }
        stopAndGo.setText(Arrays.toString(sng));

        //INFO Shrinking Generator
        int[] shr = new int[shuffleAmount];
        int count = 0;
        for (int i = 0; i < x1.length; i++) {
            if(x1[i] == 1){
                shr[count]=x2[i];
                count++;
            }
        }
        for (int i = 0; i < x1.length; i++) {
            if(x1[i] == 1){
                shr[count]=x2[i];
                count++;
            }
            if(count >= shuffleAmount){
                break;
            }
        }
        shrinking.setText(Arrays.toString(shr));
    }

    @FXML
    void onButtonRunTests(ActionEvent event) {
        //INFO Reading generator arrays
        String gef = geffe.getText();
        String sng = stopAndGo.getText();
        String shr = shrinking.getText();
        //regex removing special characters from Arrays.toString
        gef = gef.replaceAll("[^a-zA-Z0-9]","");
        sng = sng.replaceAll("[^a-zA-Z0-9]","");
        shr = shr.replaceAll("[^a-zA-Z0-9]","");
        char[] inch1 = stringToCharArray(gef);
        char[] inch2 = stringToCharArray(sng);
        char[] inch3 = stringToCharArray(shr);
        int[] x1 = charArrayToIntArray(inch1);
        int[] x2 = charArrayToIntArray(inch2);
        int[] x3 = charArrayToIntArray(inch3);

        //INFO Tests
        pokerTest(labelPoker1,x1);
        pokerTest(labelPoker2,x2);
        pokerTest(labelPoker3,x3);
        longRunsTest(labelLongRuns1,x1);
        longRunsTest(labelLongRuns2,x2);
        longRunsTest(labelLongRuns3,x3);
        runsTest(labelRuns1,x1);
        runsTest(labelRuns2,x2);
        runsTest(labelRuns3,x3);
    }

    void pokerTest(Label label, int[] array)
    {
        //INFO Separation into 4-digit arrays
        List<Integer>[] lists = new ArrayList[shuffleAmount/4];

        //Initialization
        for (int i = 0; i < shuffleAmount/4; i++) {
            lists[i] = new ArrayList<Integer>();
        }

        //Appending values
        int counter = 0;
        for(int i = 0; i < shuffleAmount; i = i+4)
        {
            lists[counter].add(array[i]);
            lists[counter].add(array[i+1]);
            lists[counter].add(array[i+2]);
            lists[counter].add(array[i+3]);
            counter++;
        }

        //INFO Counting distinctive elements
        Map<List<Integer>, Integer> mp = new HashMap<>();
        for (int i = 0; i < shuffleAmount/4; i++)
        {
            if (mp.containsKey(lists[i]))
            {
                mp.put(lists[i], mp.get(lists[i]) + 1);
            }
            else
            {
                mp.put(lists[i], 1);
            }
        }

        //INFO Counting X
        double X = 0;
        for (Integer value : mp.values()) {
            X = X + pow(value,2);
        }
        X = X*((double)16/5000);
        X = X-5000;

        //INFO Displaying Result
        boolean result;
        System.out.println("Poker Test X value: " + String.valueOf(X));
        if((X < 46.17) && (X > 2.16)){
            result = true;
        }
        else{
            result = false;
        }

        if(result){
            label.setText("PASSED");
            label.setTextFill(Color.color(0,1,0));
        }else{
            label.setText("FAILED");
            label.setTextFill(Color.color(1,0,0));
        }
    }

    void longRunsTest(Label label, int[] array)
    {
        int count, max = 1;
        boolean result = true;

        outerloop:
        for(int i = 0; i < shuffleAmount-26 ; i++)
        {
            count = 1;
            for(int j = 1; j <= 26; j++)
            {
                if(array[i] != array[i+j])
                {
                    count = 1;
                    break;
                }else{
                    count++;
                    if(count > max)
                    {
                        max = count;
                    }
                }
            }
            if(count >= 26){
                result = false;
                break outerloop;
            }
        }

        System.out.println("Long Runs Test max streak: " + String.valueOf(max));
        if(result){
            label.setText("PASSED");
            label.setTextFill(Color.color(0,1,0));
        }else{
            label.setText("FAILED");
            label.setTextFill(Color.color(1,0,0));
        }
    }

    void runsTest(Label label, int[] array)
    {
        //INFO Counting
        int i = 0;
        int streak = 1;
        //length of streak: 1,2,3,4,5,6+,1,2,3,4,5,6+ (0 first, then 1)
        int[] streaksCounter = {0,0,0,0,0,0,0,0,0,0,0,0};

        while(i < shuffleAmount - 1)
        {
            if(array[i] == array[i+1])
            {
                streak++;
                if(streak > 6)
                {
                    streak = 6;
                }
                if(i == shuffleAmount - 2)
                {
                    if(array[i] == 1)
                    {
                        streaksCounter[streak-1+6]++;
                    }else{
                        streaksCounter[streak-1]++;
                    }
                    streak = 1;
                }
            }else{
                if(array[i] == 1)
                {
                    streaksCounter[streak-1+6]++;
                }else{
                    streaksCounter[streak-1]++;
                }
                streak = 1;
            }
            i++;
        }

        //INFO Checking results
        boolean result = true;

        if((streaksCounter[0] < 2315) || (streaksCounter[0] > 2685) || (streaksCounter[6] < 2315) || (streaksCounter[6] > 2685))
        {
            result = false;
        }
        else if((streaksCounter[1] < 1114) || (streaksCounter[1] > 1386) || (streaksCounter[7] < 1114) || (streaksCounter[7] > 1386))
        {
            result = false;
        }
        else if((streaksCounter[2] < 527) || (streaksCounter[2] > 723) || (streaksCounter[8] < 527) || (streaksCounter[8] > 723))
        {
            result = false;
        }
        else if((streaksCounter[3] < 240) || (streaksCounter[3] > 384) || (streaksCounter[9] < 240) || (streaksCounter[9] > 384))
        {
            result = false;
        }
        else if((streaksCounter[4] < 103) || (streaksCounter[4] > 209) || (streaksCounter[10] < 103) || (streaksCounter[10] > 209))
        {
            result = false;
        }
        else if((streaksCounter[5] < 103) || (streaksCounter[5] > 209) || (streaksCounter[11] < 103) || (streaksCounter[11] > 209))
        {
            result = false;
        }

        System.out.println("Runs test results: zeros: " + String.valueOf(streaksCounter[0]) + ", " +
                String.valueOf(streaksCounter[1]) + ", " + String.valueOf(streaksCounter[2]) + ", " +
                String.valueOf(streaksCounter[3]) + ", " + String.valueOf(streaksCounter[4]) + ", " +
                String.valueOf(streaksCounter[5]) + ". ones: " + String.valueOf(streaksCounter[6]) + ", " +
                String.valueOf(streaksCounter[7]) + ", " + String.valueOf(streaksCounter[8]) + ", " +
                String.valueOf(streaksCounter[9]) + ", " + String.valueOf(streaksCounter[10]) + ", " +
                String.valueOf(streaksCounter[11]));
        ;
        //INFO Printing result
        if(result){
            label.setText("PASSED");
            label.setTextFill(Color.color(0,1,0));
        }else{
            label.setText("FAILED");
            label.setTextFill(Color.color(1,0,0));
        }
    }

    int LFSR_XOR(int[] chosenValues, int constantVal, List<Integer> array)
    {
        int val = constantVal;
        for(int i=0;i<chosenValues.length;i++)
        {
            val = val ^ (chosenValues[i]*array.get(i));
        }

        return val;
    }
    int[] charArrayToIntArray(char[] charArray)
    {
        int[] ints = new int[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            ints[i] = Character.getNumericValue(charArray[i]);
        }

        return ints;
    }
    boolean[] charArrayToBooleanArray(char[] charArray)
    {
        boolean[] bools = new boolean[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            int temp = Character.getNumericValue(charArray[i]);
            bools[i] = temp != 0;
        }

        return bools;
    }
    char[] stringToCharArray(String str)
    {
        // Creating array of string length
        char[] ch = new char[str.length()];

        // Copy character by character into array
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }

        // Printing content of array
//        for (char c : ch) {
//            System.out.println(c);
//        }

        return ch;
    }

    int charToInt(char ch)
    {
        return Character.getNumericValue(ch);
    }

}