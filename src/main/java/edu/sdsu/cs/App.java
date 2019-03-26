/*
Name: Huy Tran          Red ID: 818608122
Name: Alex Gutierrez    Red ID: 821394815
 */

package edu.sdsu.cs;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.nio.charset.Charset;
import org.apache.commons.io.FilenameUtils;

public class App
{
    private static ArrayList<String> sortToken = new ArrayList<String>();
    private static ArrayList<Integer> sortCount = new ArrayList<Integer>();

    private static List<String> readFiles(Path filePath) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(filePath, Charset.defaultCharset());
        } catch (IOException e) {
            System.out.println(e);
        }
        return lines;
    }

    private static int countLine(List<String> lines){
        int count = 0;
        String str = lines.get(0).replaceAll(" ","");
        count = str.length();
        for(int i = 1; i < lines.size(); i++) {
            str = lines.get(i).replaceAll(" ", "");
            if (str.length() > count)
                count = str.length();
        }
        return count;
    }

    private static int avgLine(List<String> lines) {
        int count = 0, avg = 0, numline = 0;
        String str;
        for (int i = 0; i < lines.size(); i++) {
            str = lines.get(i).replaceAll(" ", "");
            count += str.length();
            numline = i;
        }
        return avg = count / (numline + 1);
    }

    private static int uniqueTokenCS(List<String> lines){
        String line;
        String[] tokens;
        ArrayList<String> uniquetokens = new ArrayList<String>();

        for(int i = 0; i < lines.size(); i++){
            line = lines.get(i);
            tokens = line.split(" ");
            for(int j = 0; j < tokens.length; j++){
                if(!uniquetokens.contains(tokens[j])){
                    uniquetokens.add(tokens[j]);
                }
            }
        }
        return uniquetokens.size();
    }

    private static ArrayList<String> uniqueTokenCiS(List<String> lines){
        String line;
        String[] tokens;
        ArrayList<String> uniquetokens = new ArrayList<String>();

        for(int i = 0; i < lines.size(); i++){
            line = lines.get(i);
            tokens = line.split(" ");
            for(int j = 0; j < tokens.length; j++){
                if(!uniquetokens.contains(tokens[j].toLowerCase())){
                    uniquetokens.add(tokens[j].toLowerCase());
                }
            }
        }
        return uniquetokens;
    }

    private static ArrayList<String> allToken(List<String> lines){
        String line;
        String[] tokens;
        ArrayList<String> alltokens = new ArrayList<String>();

        for(int i = 0; i < lines.size(); i++){
            line = lines.get(i);
            tokens = line.split(" ");
            for(int j = 0; j < tokens.length; j++){
                alltokens.add(tokens[j]);
            }
        }
        return alltokens;
    }

    private static ArrayList<Integer> Count(ArrayList<String> unique, ArrayList<String> all){
        ArrayList<Integer> count = new ArrayList<Integer>();
        for(int i = 0; i < unique.size(); i++){
            int n = 0;
            for(int j = 0; j < all.size(); j++){
                if(unique.get(i).equals(all.get(j).toLowerCase())) {
                    n++;
                }
            }
            count.add(n);
        }
        return count;
    }

    private static void Sort(ArrayList<String> unique, ArrayList<Integer> count){

        while(!count.isEmpty()){
            int n = count.get(0);

            for(int i = 0; i < count.size(); i++){
                if(count.get(i) >= n){
                    n = count.get(i);
                }
            }

            for(int i = 0; i < count.size(); i++){
                if(count.get(i) == n){
                    sortCount.add(count.get(i));
                    sortToken.add(unique.get(i));
                    count.remove(i);
                    unique.remove(i);
                }
            }
        }
    }

    private static void writeToFile(String filePath, int length, int avg, int uniquetokencs, int uniquetokencis, int totaltoken){
        int mostfreqtoken = sortCount.get(0);
        int x = sortCount.get(0);
        ArrayList<String> freqtokens = new ArrayList<String>();
        for(int i = 0; i < sortCount.size(); i++){
            if(sortCount.get(i) == x){
                freqtokens.add(sortToken.get(i));
            }
        }

        ArrayList<String> most10token = new ArrayList<String>();
        ArrayList<Integer> most10count = new ArrayList<Integer>();
        for(int j = 0; j < 10; j++){
            most10token.add(sortToken.get(j));
            most10count.add(sortCount.get(j));
        }

        ArrayList<String> least10token = new ArrayList<String>();
        ArrayList<Integer> least10count = new ArrayList<Integer>();
        for(int k = sortCount.size() - 1; k >=  sortCount.size() - 10; k--){
            least10token.add(sortToken.get(k));
            least10count.add(sortCount.get(k));
        }

        ArrayList<String> lines = new ArrayList<String>(Arrays.asList(
                "Length of longest line in file: " + length, "\n",
                "Average line length: " + avg, "\n",
                "Number of unique space-delineated tokens (case-sensitive): " + uniquetokencs, "\n",
                "Number of unique space-delineated tokens (case-insensitive): " + uniquetokencis, "\n",
                "Number of all space-delineated tokens in file: " + totaltoken, "\n",
                "Most frequently occurring token(s): ",
                "    - "+ freqtokens, "\n",
                "Count of most frequently occurring token (case-insensitive): " + mostfreqtoken, "\n",
                "10 most frequent tokens with their counts, respectively (case-insensitive): ",
                "    - " + most10token, "    - " + most10count, "\n",
                "10 least frequent tokens with their counts, respectively (case-insensitive): ",
                "    - " + least10token, "    - " + least10count));

        Path location = Paths.get(filePath + ".stats");
        try {
            Files.write(location, lines, Charset.defaultCharset());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void ioFiles(File folder){
        for(File fileEntry : folder.listFiles()){
            if(fileEntry.isDirectory())
                ioFiles(fileEntry);
            else{
                String ext = FilenameUtils.getExtension(fileEntry.getPath());
                if(ext.equals("java") || ext.equals("txt")){
                    System.out.println(fileEntry.getName());
                    Path filePath = Paths.get(fileEntry.getPath());

                    List<String> list = readFiles(filePath);

                    ArrayList<Integer> countToken = Count(uniqueTokenCiS(list),allToken(list));
                    Sort(uniqueTokenCiS(list),countToken);
                    int length = countLine(list);
                    int avg = avgLine(list);
                    int uniquetokencs = uniqueTokenCS(list);
                    int totaltoken = allToken(list).size();
                    int uniquetokencis = uniqueTokenCiS(list).size();
                    writeToFile(fileEntry.getPath(), length, avg, uniquetokencs, uniquetokencis, totaltoken);
                }
            }
        }
    }
    /*
    for the "ioFiles" method, I implemented and used the code from the following website.
    Professor Healey already approved of this.
    https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
     */

    public static void main( String[] args ) {

        System.out.println("If you would like to use the default folder, enter '1'" +
                         "\nIf you would like specify a file path, enter '2'");
        System.out.print("You choose: ");
        Scanner sc1 = new Scanner(System.in);
        int user = sc1.nextInt();
        if(user == 1){
            File folder = new File("src/main/java/edu/sdsu/cs");
            ioFiles(folder);
        } else{
            System.out.println("\nPlease specify your desired file path: ");
            Scanner sc2 = new Scanner(System.in);
            String path = sc2.nextLine();
            File folder = new File(path);
            ioFiles(folder);
        }
        sc1.close();
    }
}
