package com.advancedprograming.dictionaryserver.server;

import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component

public class BookScrabbleRestHandler {
    public String HandleRequest(String request){
        String[] args = request.split(",");
        System.out.println(request);
        boolean answer = handleArgs(args);
        System.out.println(answer);
        return answer ? "true" : "false";
    }
    private boolean handleArgs(String[] args)
    {
        // The minimum args length is 3 because it consists of one command ["C", "Q"], at least one book, and one word
        if (args.length < 3) {
            System.err.println("Invalid input from client: \"" + args + "\"");
            return false;
        }

        String command = args[0];
        String[] booksAndWord = Arrays.copyOfRange(args, 1, args.length);

        if (command.equals("C"))
            return DictionaryManager.get().challenge(booksAndWord);
        else if (command.equals("Q"))
            return DictionaryManager.get().query(booksAndWord);

        System.err.println("Invalid command from client: \"" + command + "\"");
        return false;
    }
}
