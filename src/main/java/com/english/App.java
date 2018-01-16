package com.english;

import java.io.File;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("*** type \"exit\" to quit ***\n\n");

        String filePath = "files" + File.separator + "sentences.txt";

        try (Scanner userInput = new Scanner(System.in)) {

            MatchSentences matchSentences = new MatchSentences();
            matchSentences.readSentencesFromFileToElementList(filePath);

            if (args.length >= 2 && args[1].equalsIgnoreCase("phrasalMode"))
                matchSentences.runAsDisplayPhrasalVerb();
            else
                matchSentences.runAsDisplayStandardForm();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
