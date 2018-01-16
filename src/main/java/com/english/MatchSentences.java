package com.english;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class MatchSentences {

    private List<Element> listOfElements;

    private enum Display {
        STANDARD,
        PHRASAL
    }

    public void readSentencesFromFileToElementList(String filePath) {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            List<String> allSentences = stream.collect(Collectors.toList());

            listOfElements = IntStream.iterate(0, i -> i + 2)
                    .limit(allSentences.size() / 2)     // 0,2,4,6...
                    .mapToObj(i -> new Element(allSentences.get(i), allSentences.get(i + 1)))
                    .collect(Collectors.toList());

            Collections.shuffle(listOfElements);    // random order

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runAsDisplayStandardForm() {
        go(Display.STANDARD);  // getStandardSentence == question
    }

    public void runAsDisplayPhrasalVerb() {
        go(Display.PHRASAL);    // getPhrasalSentence == answer
    }

    public void go(Display displayMode) {
        try (Scanner userInput = new Scanner(System.in)) {

            long startMeasureTimeElapsed = System.nanoTime();

            long pointsCount =
                    IntStream.range(0, listOfElements.size())
                            .mapToObj(i -> {
                                System.out.print("== " + (displayMode.equals(Display.STANDARD) ? getStandardSentence(i) : getPhrasalSentence(i)) + "\n-> ");
                                String expectedSentence = (displayMode.equals(Display.STANDARD) ? getPhrasalSentence(i) : getStandardSentence(i));
                                String userInputString = userInput.nextLine();

                                if (userInputString.equalsIgnoreCase("exit"))
                                    System.exit(0);

                                boolean compareUserInputWithExpectedSentence = (userInputString.trim()).equalsIgnoreCase(expectedSentence);
                                System.out.println(compareUserInputWithExpectedSentence ? "++ Great\n" : ("-- " + expectedSentence + "\n"));
                                return compareUserInputWithExpectedSentence;
                            })
                            .filter(booleanObj -> booleanObj)
                            .count();

            long estimatedTime = ((System.nanoTime() - startMeasureTimeElapsed) / 1000000000) / 60;     // seconds / minutes

            System.out.println("END - " + pointsCount + " / " + listOfElements.size() + " points.");
            System.out.println("Running time: " + estimatedTime + " min.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPhrasalSentence(int i) {
        return listOfElements.get(i).getPhrasal();
    }

    public String getStandardSentence(int i) {
        return listOfElements.get(i).getStandard();
    }

}