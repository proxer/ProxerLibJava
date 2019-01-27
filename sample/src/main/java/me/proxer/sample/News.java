package me.proxer.sample;

import me.proxer.library.ProxerApi;
import me.proxer.library.ProxerException;

import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Ruben Gees
 */
public class News {

    public static void main(String[] args) {
        ProxerApi api = new ProxerApi.Builder(readApiKey()).build();

        try {
            final String news = api.notifications().news()
                    .build()
                    .safeExecute()
                    .stream()
                    .map(newsArticle -> newsArticle.getSubject() + " written by " + newsArticle.getAuthor())
                    .collect(Collectors.joining("\n"));

            System.out.println();
            System.out.println("These are the latest news:\n" + news);
        } catch (ProxerException exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }
    }

    private static String readApiKey() {
        System.out.print("Please pass your api key: ");

        try (final Scanner scanner = new Scanner(System.in)) {
            return scanner.next();
        }
    }
}
