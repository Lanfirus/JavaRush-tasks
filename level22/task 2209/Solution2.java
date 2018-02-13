package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Make a chain from words
From initial array of cities program should make a chain where last character of previous city name
should equals with the first character of next city name
*/
public class Solution2 {
    //Static array that allows to keep results from the initial run of the program
    private static int[] max;

    public static void main(String[] args) throws IOException {

        //According to the initial conditions for this task, city names should be read from some file
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Preparing to read text from the file. For cyrillic text encoding "cp1251" is used that could lead
        //to strange and incorrect results for other non latin encoding. Correct respectively if needed.
        BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine()), "cp1251"));
        reader.close();
        StringBuilder sb = new StringBuilder();

        while (fr.ready()) {
            sb.append(fr.readLine());
            sb.append(" ");
        }
        fr.close();

        String[] allWords = sb.toString().split(" ");

        System.out.println(getLine(allWords));
    }

    /**
     * Get String array as input, process it using another method, choose the longest chain from the initial run
     * and get final chain as result of another call of method "buildStringSet".
     * @param words - String[] from the read file. It's a requirement of initial rules for this task. Change if needed.
     * @return StringBuilder object that consist of the longest chain
     */
    public static StringBuilder getLine(String... words) {
        //Check for zero sized input String array. According to initial rules method should return an empty string.
        //Correct return statement if needed
        if (words.length == 0) {
            return new StringBuilder();
        }

        //Converting String[] to ArrayList<String> for easier processing
        List<String> list = new ArrayList<>();
        Arrays.stream(words).forEach(x -> list.add(x));

        //Initial run of processing data to get possible chains
        StringBuilder tmp = buildStringSet(list, 0, list.size());

        //Define longest chain and index of starting word
        int maximum = 0;
        int index = 0;
        for (int i = 0; i < max.length; i++) {
            if (max[i] > maximum) {
                maximum = max[i];
                index = i;
            }
        }
        //Second run with defined starting word to get the longest chain as a result
        tmp = buildStringSet(list, index, index + 1);
        return tmp;
    }

    /**
     * Process words to build the longest possible chain from them. Try all words as starting ones
     * to define the longest chain
     * @param list
     * @param indexOfStart - first word from input array to start processing from
     * @param indexOfEnd - the first word in input array that will be ignored for processing
     *                   as well as all further words
     * @return - StringBuilder with the chain of words. If indexOfEnd - indexOfStart > 1 then the last chain
     * of words will be used as a result.
     */
    public static StringBuilder buildStringSet(List<String> list, int indexOfStart, int indexOfEnd) {

        //Here lengths of chains will be stored. Index of max corresponds to the index of starting word in list array
        max = new int[list.size()];

        //For further check of useless intermediate chain - if adding particular word prohibits to add anything else to it
        int check = 0;

        //To temporary store current word we check for usefullness in our chain
        String used;

        //This array will keep current chain
        List<String> workingList = null;

        //First cycle. Choose starting word and continuously use them trying to build the longest possible chain.
        for (int m = indexOfStart; m < indexOfEnd; m++) {

            //New array to be used in chain processing (adding/removing elements). So, initial array can't be used
            //for more than one run.
            List<String> listTmp = new ArrayList<>();

            //Copy initial array to temporary one
            list.stream().forEach(x -> listTmp.add(x));

            //Need to use empty array each time we start new chain, so previous chains will not influence to next runs
            workingList = new ArrayList<>();

            //Put starting word into chain
            workingList.add(listTmp.get(m));

            //Remove starting chain from city name's array to avoid doubling
            listTmp.remove(m);

            //As chain now consists of 1 element, increment counter
            max[m]++;

            //Next cycle of processing. Start to build chain of words that have the same character as
            // ending and beginning (e.g. Paris Stockholm). Should ignore character's cases.
            for (int i = 0; i < listTmp.size(); i++) {

                //Check ending of the last word in chain (workingList) and beginning of current word in word array (listTmp).
                //If equals then add current word to the chain and go further into the cycle body.
                //If not - goes to next round of for cycle.
                if (workingList.get(workingList.size() - 1).charAt(workingList.get(workingList.size() - 1).length() - 1) == listTmp.get(i).toLowerCase().charAt(0)) {
                    workingList.add(listTmp.get(i));
                } else {
                    continue;
                }

                //As current word has been used for chain creation, remove it from word array to avoid duplicates.
                //Temporary store removed word in case it's a dead end of chain using this word. So this word
                //can be returned to word array back and possibly used in some other chain
                used = listTmp.get(i);
                listTmp.remove(i);

                //Increment length of current chain
                max[m]++;

                //Check to avoid incorrect result if word array has only 2 words. First has been used in first "for",
                //second one - in this "for" slightly above, so nothing stays in word array.
                if (listTmp.size() == 0) {
                    break;
                }

                //To remember length of chain before next round of chaining. Will be used as a dead end recognition and
                //flag to fallback this step.
                check = max[m];

                //Next round of chaining
                for (int j = 0; j < listTmp.size(); j++) {
                    if (workingList.get(workingList.size() - 1).charAt(workingList.get(workingList.size() - 1).length() - 1) == listTmp.get(j).toLowerCase().charAt(0)) {
                        workingList.add(listTmp.get(j));
                        listTmp.remove(j);
                        max[m]++;
                        //If new word has been added to the chain - start chaining from the beginning, so all words
                        // could be checked in new conditions
                        // j = -1 and j++ leads to j actually gains 0 value corresponding to first word in array
                        j = -1;
                    }
                }
                //Check whether any word has been added to the chain in this round - dead lock protection
                //If fails - fallback to the previous state of chain and return used word to the word array
                //Decrement length of chain
                //Otherwise, reset index of starting word for the previous step of chain processing, so
                //all words will be checked in new conditions
                // i = -1 and i++ leads to i actually gains 0 value corresponding to first word in array
                if (check == max[m]) {
                    workingList.remove(workingList.size() - 1);
                    listTmp.add(used);
                    max[m]--;
                } else {
                    i = -1;
                }
            }
        }

        //According to rules for this task chain should be printed as city names with spaces between them
        StringBuilder key = new StringBuilder(workingList.toString().replaceAll("[\\[\\],]", ""));
        return key;
    }
}
