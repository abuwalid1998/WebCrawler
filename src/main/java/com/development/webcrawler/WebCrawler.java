package com.development.webcrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;

public class WebCrawler {

    private Queue<String> urlQueue;
    private List<String> visitedURLs;

   private FileWriter myWriter;



    public WebCrawler() {
        urlQueue = new LinkedList<>();
        visitedURLs = new ArrayList<>();
    }

    public void crawl(String rootURL, int breakpoint) throws IOException {
        urlQueue.add(rootURL);
        visitedURLs.add(rootURL);



        while(!urlQueue.isEmpty()){

            // remove the next url string from the queue to begin traverse.
            String s = urlQueue.remove();
            String rawHTML = "";
            try{
                // create url with the string.
                URL url = new URL(s);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String inputLine = in.readLine();

                // read every line of the HTML content in the URL
                // and concat each line to the rawHTML string until every line is read.
                while(inputLine  != null){
                    rawHTML += inputLine;

                    inputLine = in.readLine();
                }
                in.close();
            } catch (Exception e){
                e.printStackTrace();
            }

            // create a regex pattern matching a URL
            // that will validate the content of HTML in search of a URL.
            String urlPattern = "(www|http:|https:)+[^\s]+[\\w]";
            Pattern pattern = Pattern.compile(urlPattern);
            Matcher matcher = pattern.matcher(rawHTML);

            // Each time the regex matches a URL in the HTML,
            // add it to the queue for the next traverse and the list of visited URLs.
            breakpoint = getBreakpoint(breakpoint, matcher);

            // exit the outermost loop if it reaches the breakpoint.
            if(breakpoint == 0){
                break;
            }
        }
    }

    private int getBreakpoint(int breakpoint, Matcher matcher) throws IOException {
        myWriter = new FileWriter("log.txt");
        while(matcher.find()){
            String actualURL = matcher.group();

            if(!visitedURLs.contains(actualURL)){
                visitedURLs.add(actualURL);

                System.out.println("Website found with URL " + actualURL);
                myWriter.write("Website found with URL " + actualURL);
                myWriter.write("\n");

                urlQueue.add(actualURL);
            }

            // exit the loop if it reaches the breakpoint.
            if(breakpoint == 0){
                break;
            }
            breakpoint--;
        }
        myWriter.close();
        return breakpoint;
    }

}