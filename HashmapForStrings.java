import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class HashmapForStrings {

    public HashmapForStrings(int m) {
        this.HashList = new Node[m];
        for (int i=0; i<m; i++) {
            HashList[i] = new Node(i);
        }
    }

    public class Node {
        int hashkey;
        LinkedList<String> list = new LinkedList<String>();

        public Node (int hashkey) {
            this.hashkey = hashkey;
        }
    }
    Node[] HashList;

    public static int hashing(String s, int m) {
        char[] chars = s.toCharArray();
        int hash = 0;
        for (char a : chars) {
            hash += (int)a;
        }
        return hash%m;
    }

    public static void insert(HashmapForStrings T, String s) {
        int m = T.HashList.length;
        T.HashList[hashing(s,m)].list.add(s);
    }

    public static String search(HashmapForStrings T, String s) {
        int m = T.HashList.length;
        String res = null;
        for (String str : T.HashList[hashing(s,m)].list) {
            if (str.equals(s)) {
                res = str;
            }
        }
        return res;
    }

    public static void delete(HashmapForStrings T, String s) {
        int m = T.HashList.length;
        T.HashList[hashing(s,m)].list.remove(s);
    }

    public static void main(String[] args) throws FileNotFoundException {
        HashmapForStrings t = new HashmapForStrings(1000);
        HashmapForStrings tt = new HashmapForStrings(10000);

        Scanner scan = new Scanner(new File("src/OfficialScrabbleWordListGerman.txt"));
        long begin = System.nanoTime();
        while (scan.hasNext()) {
            insert(tt,scan.nextLine());                                              // change table here
        }
        long end = System.nanoTime();
        long velocity = end - begin;
        System.out.println("time to insert: " + velocity);

        // Dauer insert in t:  235850800
        // Dauer insert in tt: 281678400

        scan = new Scanner(new File("src/AreMyFriendsCheating.txt"));
        begin = System.nanoTime();
        int errors = 0;
        while (scan.hasNext()) {
            String txt = search(tt,scan.nextLine());                                 // change table here
            if (txt == null) {
                errors += 1;
            }
        }
        end = System.nanoTime();
        velocity = end - begin;
        System.out.println("time to search: " + velocity);
        System.out.println("errors: " + errors);

        // Dauer search in t:  82179300
        // Dauer search in tt: 80750300

        // erfolgelose Suchen in t:  1814
        // erfolgelose Suchen in tt: 1814

    }
}
