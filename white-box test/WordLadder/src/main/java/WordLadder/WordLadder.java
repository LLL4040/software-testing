package WordLadder;

import org.graalvm.compiler.word.Word;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;

public class WordLadder {
    /**
     * @param args
     */
    public static void main(String args[]) throws IOException {
        WordLadder wl = new WordLadder(args);
    }

    public WordLadder(HashSet<String> dict, String begWord, String endWord) {
        LinkedList<String> ladder = new LinkedList<>();
        // word 1
        System.out.println("Word #1 :");
        System.out.println(begWord);

        // word 2
        System.out.println("Word #2 :");
        System.out.println(endWord);

        // word ladder between word1 and word2
        ladder = findLadder(dict, begWord, endWord);
        if (!ladder.isEmpty()) {
            System.out.println("The ladder is:");
            for (int i = 0; i < ladder.size(); i++) {
                System.out.print(ladder.get(i));
                System.out.print(" ");
            }
            System.out.println("\n");
        }
        System.out.println("Have a nice day!");
    }

    public WordLadder(String args[]) {
        // 使用 System.in 创建 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String path, begWord, endWord;

        // name of dictionary file
        LinkedList<String> ladder = new LinkedList<>();
        System.out.println("Dictionary file name?");
        path = args[0];
        System.out.println(path);

        // word 1
        System.out.println("Word #1 :");
        begWord = args[1];
        System.out.println(begWord);

        // word 2
        System.out.println("Word #2 :");
        endWord = args[2];
        System.out.println(endWord);

        // word ladder between word1 and word2
        HashSet<String> dict = readDict(path);
        ladder = findLadder(dict, begWord, endWord);
        if (!ladder.isEmpty()) {
            System.out.println("The ladder is:");
            for (int i = 0; i < ladder.size(); i++) {
                System.out.print(ladder.get(i));
                System.out.print(" ");
            }
            System.out.println("\n");
        }
        System.out.println("Have a nice day!");
    }

    //从文件里读取字典
    public static HashSet<String> readDict(String fileName){
        if(fileName == null) {
            System.out.println("please input file name!");
            return new HashSet<>();
        }
        HashSet<String> dict = new HashSet<>();
        try (FileReader reader = new FileReader(fileName);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                dict.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dict;
    }

    //判断是否只有一个字母不同（不区分大小写）
    public static boolean hasOneDif(String a, String b){
        // 若a或b为null则返回false
        if(a == null || b == null) {
            return false;
        }
        //若ab不等长或者相等，直接return false
        if ((a.length() != b.length()) || a.equalsIgnoreCase(b)){
            return false;
        }

        int count = 0;
        String x = a.toUpperCase();
        String y = b.toUpperCase();
        for (int i = 0; i < x.length(); i++){
            if (x.charAt(i) != y.charAt(i)){
                count++;
            }
            if (count > 1){
                return false;
            }
        }

        return true;
    }

    //在指定位置替换一个字母
    public static String replaceOneChar(String s, int pos, String newChar){
        if (s.length() != 0){
            StringBuilder newS = new StringBuilder(s);
            int end = pos + 1;
            newS.replace(pos, end, newChar);
            return newS.toString();
        }
        else{
            return s;
        }
    }

    //bfs找到包含最短路径的所有的层
    public static LinkedList<LinkedList<String>> searchLayer(LinkedList<String> wordQueue, String endWord, HashSet<String> wordDict) {
        LinkedList<LinkedList<String>> ladder = new LinkedList<>();

        //判断字典是否为空
        if (wordDict.size() == 0) {
            return ladder;
        }
        
        while (!wordQueue.isEmpty()){
            int count = wordQueue.size();
            LinkedList<String> layer = new LinkedList<>();
            while (count-- > 0){
                String word = wordQueue.getFirst();
                wordQueue.removeFirst();
                layer.add(word);
                if (hasOneDif(word, endWord)){
                    ladder.add(layer);
                    return ladder;
                }
                for (int i = 0; i < word.length(); i++){
                    for (int j = 'a'; j <= 'z'; j++){
                        String tmp = word;
                        char ch = (char)j;
                        if (tmp.charAt(i) != ch){
                            StringBuffer sb = new StringBuffer();
                            sb.append((char)j);
                            String x = sb.toString();
                            tmp = replaceOneChar(tmp, i, x);
                        }
                        if (wordDict.contains(tmp)){
                            wordQueue.add(tmp);
                            wordDict.remove(tmp);
                        }
                    }
                }
            }
            ladder.add(layer);
        }
        return ladder;
    }

    //寻找wordLadder主函数
    public static LinkedList<String> findLadder(HashSet<String> dict, String beg, String end){
        LinkedList<String> queue = new LinkedList<>();
        LinkedList<String> output = new LinkedList<>();

        //两个单词必须都在字典里
        if (!(dict.contains(beg.toLowerCase()) && dict.contains(end.toLowerCase()))){
            System.out.println("首尾单词都必须在字典内，请重新输入");
            return output;
        }

        //两个单词不可以相同
        if (beg.equals(end)){
            System.out.println("首尾单词不可以相同，请重新输入");
            return output;
        }

        //两个单词长度必须相等
        if (beg.length() != end.length()){
            System.out.println("首尾单词长度必须相等，请重新输入");
            return output;
        }

        queue.add(beg);
        dict.remove(beg);
        output.add(end);
        boolean find = false;
        if (hasOneDif(beg, end)){
            output.add(beg);
        }
        else {
            LinkedList<LinkedList<String>> ways = searchLayer(queue, end, dict);
            String word = end;
            for (int i = ways.size() - 1; i >= 0; i--){
                LinkedList<String> layer = ways.get(i);
                for (int j = layer.size() - 1; j >= 0; j--){
                    String tmp = layer.get(j);
                    if (hasOneDif(tmp, word)){
                        output.add(tmp);
                        word = tmp;
                        find = true;
                        break;
                    }
                }
                if (!find){
                    output.removeFirst();
                    System.out.println("不存在这个ladder");
                    break;
                }
            }
        }
        return output;
    }
}
