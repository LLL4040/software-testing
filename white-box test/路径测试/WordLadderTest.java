package WordLadder;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class WordLadderTest {

    @Test
    public void mainTest() throws IOException {
        // normal path
        String args[] = new String[]{"dictionary.txt", "hghghg", "data"};
        WordLadder.main(args);
    }

    @Test
    public void WordLadder1Test() {
        // no ladder path
        String args1[] = new String[]{"dictionary.txt", "hghghg", "data"};
        WordLadder wordLadder1 = new WordLadder(args1);

        // ladder path
        String args2[] = new String[]{"dictionary.txt", "code", "data"};
        WordLadder wordLadder2 = new WordLadder(args2);
    }

    @Test
    public void WordLadder2Test() {
        // no ladder path
        HashSet<String> dict = new HashSet<>();
        dict.add("code");
        dict.add("cade");
        dict.add("dade");
        dict.add("date");
        dict.add("data");
        String begword1 = "data";
        String endword1 = "code";
        WordLadder wordLadder1 = new WordLadder(dict, begword1, endword1);

        // ladder path
        String begword2 = "hghghg";
        String endword2 = "code";
        WordLadder wordLadder2 = new WordLadder(dict, begword2, endword2);
    }

    @Test
    public void readDictTest() {
        // try-catch path
        WordLadder.readDict("hhh.txt");

        // null path
        String filename1 = null;
        HashSet<String> set = new HashSet<>();
        Assert.assertEquals(set, WordLadder.readDict(filename1));

        // normal path
        HashSet<String> dict = new HashSet<>();
        dict.add("code");
        dict.add("cade");
        dict.add("dade");
        dict.add("date");
        dict.add("data");
        dict.add("doto");
        String filename2 = "dictionary.txt";
        Assert.assertEquals(dict, WordLadder.readDict(filename2));

        // empty file path
        String filename3 = "dictionarytest.txt";
        Assert.assertEquals(set, WordLadder.readDict(filename3));
    }

    @Test
    public void hasOneDifTest() {
        // null value path 1
        String a1 = null;
        String b1 = "aaa";
        Assert.assertFalse(WordLadder.hasOneDif(a1, b1));

        // null value path 2
        String a2 = "bbb";
        String b2 = null;
        Assert.assertFalse(WordLadder.hasOneDif(a2, b2));

        // not equal length or equal totally 1
        String a3 = "c";
        String b3 = "ccc";
        Assert.assertFalse(WordLadder.hasOneDif(a3, b3));

        // not equal length or equal totally 2
        String a4 = "ddd";
        String b4 = "ddd";
        Assert.assertFalse(WordLadder.hasOneDif(a4, b4));

        // normal path
        String a5 = "dcd";
        String b5 = "ddd";
        Assert.assertTrue(WordLadder.hasOneDif(a5, b5));

        // one word normal path
        String a6 = "a";
        String b6 = "b";
        Assert.assertTrue(WordLadder.hasOneDif(a6, b6));

        // bigger than one word differ
        String a7 = "eee";
        String b7 = "fff";
        Assert.assertFalse(WordLadder.hasOneDif(a7, b7));
    }

    @Test
    public void replaceOneCharTest() {
        // normal path
        String s1 = "aaa";
        int pos1 = 0;
        String newChar1 = "b";
        Assert.assertEquals("baa", WordLadder.replaceOneChar(s1, pos1, newChar1));

        // empty s path
        String s2 = "";
        int pos2 = 0;
        String newChar2 = "s";
        Assert.assertEquals("", WordLadder.replaceOneChar(s2, pos2, newChar2));
    }

    @Test
    public void searchLayerTest() {
        // empty dict path
        LinkedList<String> wordQueue1 = new LinkedList<>();
        String endWord1 = "";
        HashSet<String> wordDict1 = new HashSet<>();
        LinkedList<LinkedList<String>> ladder1 = new LinkedList<>();
        Assert.assertEquals(ladder1, WordLadder.searchLayer(wordQueue1, endWord1, wordDict1));

        // empty word queue path
        LinkedList<String> wordQueue2 = new LinkedList<>();
        String endWord2 = "";
        HashSet<String> wordDict2 = new HashSet<>();
        wordDict2.add("data");
        LinkedList<LinkedList<String>> ladder2 = new LinkedList<>();
        Assert.assertEquals(ladder2, WordLadder.searchLayer(wordQueue2, endWord2, wordDict2));

        // one differ path
        LinkedList<String> wordQueue3 = new LinkedList<>();
        wordQueue3.add("data");
        String endWord3 = "date";
        HashSet<String> wordDict3 = new HashSet<>();
        wordDict3.add("data");
        LinkedList<LinkedList<String>> ladder3 = new LinkedList<>();
        LinkedList<String> layer3_1 = new LinkedList<>();
        layer3_1.add("data");
        ladder3.add(layer3_1);
        Assert.assertEquals(ladder3, WordLadder.searchLayer(wordQueue3, endWord3, wordDict3));

        // empty word queue path
        LinkedList<String> wordQueue4 = new LinkedList<>();
        wordQueue4.add("data");
        String endWord4 = "code";
        HashSet<String> wordDict4 = new HashSet<>();
        wordDict4.add("date");
        wordDict4.add("dade");
        LinkedList<LinkedList<String>> ladder4 = new LinkedList<>();
        LinkedList<String> layer4_1 = new LinkedList<>();
        layer4_1.add("data");
        LinkedList<String> layer4_2 = new LinkedList<>();
        layer4_2.add("date");
        LinkedList<String> layer4_3 = new LinkedList<>();
        layer4_3.add("dade");
        ladder4.add(layer4_1);
        ladder4.add(layer4_2);
        ladder4.add(layer4_3);
        Assert.assertEquals(ladder4, WordLadder.searchLayer(wordQueue4, endWord4, wordDict4));

        // normal path
        LinkedList<String> wordQueue5 = new LinkedList<>();
        wordQueue5.add("data");
        String endWord5 = "code";
        HashSet<String> wordDict5 = new HashSet<>();
        wordDict5.add("date");
        wordDict5.add("dade");
        wordDict5.add("cade");
        wordDict5.add("doto");
        LinkedList<LinkedList<String>> ladder5 = new LinkedList<>();
        LinkedList<String> layer5_1 = new LinkedList<>();
        layer5_1.add("data");
        LinkedList<String> layer5_2 = new LinkedList<>();
        layer5_2.add("date");
        LinkedList<String> layer5_3 = new LinkedList<>();
        layer5_3.add("dade");
        LinkedList<String> layer5_4 = new LinkedList<>();
        layer5_4.add("cade");
        ladder5.add(layer5_1);
        ladder5.add(layer5_2);
        ladder5.add(layer5_3);
        ladder5.add(layer5_4);
        Assert.assertEquals(ladder5, WordLadder.searchLayer(wordQueue5, endWord5, wordDict5));
    }

    @Test
    public void findLadderTest() {
        // not in dictionary path 1
        HashSet<String> dict1 = new HashSet<>();
        String beg1 = "beg";
        String end1 = "end";
        dict1.add("end");
        LinkedList<String> output1 = new LinkedList<>();
        Assert.assertEquals(output1, WordLadder.findLadder(dict1, beg1, end1));

        // not in dictionary path 2
        HashSet<String> dict2 = new HashSet<>();
        String beg2 = "beg";
        String end2 = "end";
        dict2.add("beg");
        LinkedList<String> output2 = new LinkedList<>();
        Assert.assertEquals(output2, WordLadder.findLadder(dict2, beg2, end2));

        // beg equals end path
        HashSet<String> dict3 = new HashSet<>();
        String beg3 = "beg";
        String end3 = "beg";
        dict3.add("beg");
        LinkedList<String> output3 = new LinkedList<>();
        Assert.assertEquals(output3, WordLadder.findLadder(dict3, beg3, end3));

        // length not equals path
        HashSet<String> dict4 = new HashSet<>();
        String beg4 = "begin";
        String end4 = "end";
        dict4.add("begin");
        dict4.add("end");
        LinkedList<String> output4 = new LinkedList<>();
        Assert.assertEquals(output4, WordLadder.findLadder(dict4, beg4, end4));

        // has one differ path
        HashSet<String> dict5 = new HashSet<>();
        String beg5 = "eed";
        String end5 = "end";
        dict5.add("eed");
        dict5.add("end");
        LinkedList<String> output5 = new LinkedList<>();
        output5.add(end5);
        output5.add(beg5);
        Assert.assertEquals(output5, WordLadder.findLadder(dict5, beg5, end5));

        // ladder not exist path
        HashSet<String> dict6 = new HashSet<>();
        String beg6 = "data";
        String end6 = "code";
        dict6.add("data");
        dict6.add("code");
        dict6.add("date");
        dict6.add("dade");
        LinkedList<String> output6 = new LinkedList<>();
        Assert.assertEquals(output6, WordLadder.findLadder(dict6, beg6, end6));

        //normal path
        HashSet<String> dict7 = new HashSet<>();
        String beg7 = "data";
        String end7 = "code";
        dict7.add("data");
        dict7.add("code");
        dict7.add("date");
        dict7.add("dade");
        dict7.add("cade");
        dict7.add("doto");
        LinkedList<String> output7 = new LinkedList<>();
        output7.add("code");
        output7.add("cade");
        output7.add("dade");
        output7.add("date");
        output7.add("data");
        Assert.assertEquals(output7, WordLadder.findLadder(dict7, beg7, end7));

    }

}
