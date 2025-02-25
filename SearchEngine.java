import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> list = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            String returned = "";
            for (String curr:list)
            {
                returned += curr + "\n";
            }
            return String.format("List is: %s", returned);
        } 
        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    list.add(parameters[1]);
                    return String.format("String %s successful added to list.", parameters[1]);
                }
            return String.format("404 Not Found");
        } 
        else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    String result = "";
                    for (String curr:list)
                    {
                        if (curr.contains(parameters[1]))
                        {
                            result += curr + "\n";
                        }
                    }
                    return String.format("String returned \n %s", result);
                }
            return String.format("404 Not Found");
        }
        else {
            return "404 Not Found!";
        }
        
        //return "1";
    }
}

public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
    
}
