package com.example.demo.utility.extracting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class ExtractBadWords implements ExtractSimpleData {

    private Set<String> listaParolacceFrontEnd;
    private Set<String> listaParolacceFiles;
    private static final Logger logger = LoggerFactory.getLogger(ExtractBadWords.class);


    //construtt
    public ExtractBadWords() {
        this.listaParolacceFrontEnd = new HashSet<>();
        this.listaParolacceFiles = new HashSet<>();
        this.CheckFilesBadWords();
    }


    /**
     * Reads multiple language-specific bad words files from the specified folder within the resources directory
     * and populates the `listaParolacceFiles` set with the words contained in these files.
     * <p>
     * Files to be read are predefined and hardcoded as: "de.txt", "en.txt", "fr.txt", "hu.txt", and "it.txt".
     * Each file is expected to contain bad words separated by whitespace.
     * <p>
     * If an error occurs while reading a file or if the file is not found, the method logs the error and proceeds
     * to process the remaining files. Logging is handled using the `logger` instance.
     * <p>
     * Any successfully read bad words are added to the internal `listaParolacceFiles` set for later use in
     * bad word detection processes.
     * <p>
     * Exceptions handled:
     * - IOException: For errors during file reading operations.
     * - NullPointerException: For cases where a file is not found or an InputStream is null.
     */
    private void CheckFilesBadWords() {

        String folderPath = "badwords_folder/";
        List<String> fileNames = List.of(
                "de.txt",
                "en.txt",
                "fr.txt",
                "hu.txt",
                "it.txt"); // hardcoded

        ClassLoader classLoader = getClass().getClassLoader();

        for (String fileName : fileNames) {
            try (InputStream is = classLoader.getResourceAsStream(folderPath + fileName);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parole = line.split("\\s+");
                    this.listaParolacceFiles.addAll(Arrays.asList(parole));
                }
            } catch (IOException | NullPointerException e) {
                logger.atError().log("errore durante la lettura dei file badWords -- resources/badwords_folder" +
                        ": " + e.getMessage());
                System.err.println(e.getMessage());
            }
        }

    }

    /**
     * Processes a given text input from the front-end to extract words and adds them
     * to the internal list of bad words from front-end (`listaParolacceFrontEnd`).
     * Each word in the input text is separated by spaces.
     * If an error occurs during processing, it logs the error and outputs an error message.
     *
     * @param text the input text received from the front-end, which is split into words
     *             and added to the `listaParolacceFrontEnd` list for further processing.
     */
    private void CheckBadWordsFromFrontEnd(String text) {

        try {
            String[] arr = text.split(" ");
            this.listaParolacceFrontEnd.addAll(Arrays.stream(arr).toList());
        } catch (RuntimeException ex) {
            logger.atError().log("errore durante la lettura del testo dal front end: ricerca badwords" +
                    ": " + ex.getMessage());
            System.err.println(ex.getMessage());
        }

    }

    //punto partenza
    @Override
    public boolean checkIfBadWordsInMessage(String textReceivedfront) {

        this.CheckBadWordsFromFrontEnd(textReceivedfront);

        return this.listaParolacceFrontEnd.stream()
                .map(String::toLowerCase)
                .anyMatch(word -> {
                    if (this.listaParolacceFiles.contains(word)) {
                        logger.atError().log(String.format("parolaccia trovata: %s", word));
                        return true;
                    }

                    return false;
                });


    }

    public void resetSetParolacceFrontEnd() {
        this.listaParolacceFrontEnd.clear();
    }


    /**
     * Validates if the given email string is in a valid email format.
     * <p>
     * The method uses a regular expression to determine if the provided email
     * string adheres to a standard email structure. The email must include
     * a local part, an "@" symbol, and a domain part.
     *
     * @param email the email address in string format that needs to be validated.
     * @return true if the provided email string matches the valid email format;
     * false otherwise.
     */
    @Override
    public boolean checkIfValidEmailFormat(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
