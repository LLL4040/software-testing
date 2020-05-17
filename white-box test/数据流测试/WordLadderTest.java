package WordLadder;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.HashSet;
import java.util.LinkedList;

import static WordLadder.WordLadder.readDict;

/**
* WordLadder Tester.
*
* @author <Authors name>
* @since <pre>May 16, 2020</pre>
* @version 1.0
*/
public class WordLadderTest {

@Before
public void before() throws Exception {
}

@After
public void after() throws Exception {
}

/**
*
* Method: main(String args[])
*
*/
@Test
public void testMain() throws Exception {
//TODO: Test goes here...
    String args[]={"dictionary.txt","code","data"};
    WordLadder.main(args);
    String args1[]={"dictionary.txt","hghghg","data"};
    WordLadder.main(args1);
    String path = "dictionary.txt";
    HashSet<String> dict = readDict(path);
    WordLadder wl1 = new WordLadder(dict,"code","data");
    WordLadder wl2 = new WordLadder(dict,"hghghg","data");
}

/**
*
* Method: readDict(String fileName)
*
*/
@Test
public void testReadDict() throws Exception {
//TODO: Test goes here...
    //filename = null
    String filename = null;
    HashSet<String> dict = new HashSet<>();
    HashSet<String> result = WordLadder.readDict(filename);
    Assert.assertEquals(dict,result);

    //filename is wrong
    String filename1 = "read.txt";
    HashSet<String> dict1 = new HashSet<>();
    HashSet<String> result1 = WordLadder.readDict(filename1);
    Assert.assertEquals(dict1,result1);

    //filename is right
    String filename2 = "dictionary.txt";
    HashSet<String> dict2 = new HashSet<>();
    dict2.add("code");
    dict2.add("cade");
    dict2.add("dade");
    dict2.add("date");
    dict2.add("data");
    dict2.add("doto");
    HashSet<String> result2 = WordLadder.readDict(filename2);
    Assert.assertEquals(dict2,result2);
}

/**
*
* Method: hasOneDif(String a, String b)
*
*/
@Test
public void testHasOneDif() throws Exception {
//TODO: Test goes here...
    //a=null,b=null
    String a= null;String b=null;
    Assert.assertEquals(false,WordLadder.hasOneDif(a,b));
    //a=hot b=data
    String a1= "hot";String b1= "data";
    Assert.assertEquals(false,WordLadder.hasOneDif(a1,b1));
    //a=code b=data
    String a2= "code";String b2= "data";
    Assert.assertEquals(false,WordLadder.hasOneDif(a2,b2));
}

/**
*
* Method: replaceOneChar(String s, int pos, String newChar)
*
*/
@Test
public void testReplaceOneChar() throws Exception {
//TODO: Test goes here...
    //s="" pos=0 newChar=""
    String s = "";
    int pos = 0;
    String newChar = "";
    String result = WordLadder.replaceOneChar(s,pos,newChar);
    Assert.assertEquals("",result);
    //s="code" pos=2 newChar="t"
    String s1 = "code";
    int pos1 = 2;
    String newChar1 = "t";
    String result1 = WordLadder.replaceOneChar(s1,pos1,newChar1);
    Assert.assertEquals("cote",result1);
}

/**
*
* Method: searchLayer(LinkedList<String> wordQueue, String endWord, HashSet<String> wordDict)
*
*/
@Test
public void testSearchLayer() throws Exception {
//TODO: Test goes here...

    //wordQueue=null endWord=code wordDict=null
    LinkedList<String> wordQueue = new LinkedList<>();
    String endWord = "code";
    HashSet<String> wordDict = new HashSet<>();
    LinkedList<LinkedList<String>> ladder = new LinkedList<>();
    LinkedList<LinkedList<String>> result = WordLadder.searchLayer(wordQueue,endWord,wordDict);
    Assert.assertEquals(ladder,result);

    //wordQueue=null endWord=code wordDict=readdict("dictionary.txt")
    LinkedList<String> wordQueue1 = new LinkedList<>();
    String endWord1 = "code";
    HashSet<String> wordDict1 = new HashSet<>();
    wordDict1.add("code");
    wordDict1.add("cade");
    wordDict1.add("dade");
    wordDict1.add("date");
    wordDict1.add("data");
    wordDict1.add("cote");
    LinkedList<LinkedList<String>> ladder1 = new LinkedList<>();
    LinkedList<LinkedList<String>> result1 = WordLadder.searchLayer(wordQueue1,endWord1,wordDict1);
    Assert.assertEquals(ladder1,result1);

    //wordQueue={cote} endWord=code wordDict=readdict("dictionary.txt")
    LinkedList<String> wordQueue2 = new LinkedList<>();
    wordQueue2.add("cote");
    String endWord2 = "code";
    HashSet<String> wordDict2 = new HashSet<>();
    wordDict2.add("code");
    wordDict2.add("cade");
    wordDict2.add("dade");
    wordDict2.add("date");
    wordDict2.add("data");
    wordDict2.add("cote");
    LinkedList<LinkedList<String>> ladder2 = new LinkedList<>();
    LinkedList<String> ladder2_1 = new LinkedList<>();
    ladder2_1.add("cote");
    ladder2.add(ladder2_1);
    LinkedList<LinkedList<String>> result2 = WordLadder.searchLayer(wordQueue2,endWord2,wordDict2);
    Assert.assertEquals(ladder2,result2);

    //wordQueue={data} endWord=code wordDict=readdict("dictionary.txt")
    LinkedList<String> wordQueue3 = new LinkedList<>();
    wordQueue3.add("data");
    String endWord3 = "code";
    HashSet<String> wordDict3 = new HashSet<>();
    wordDict3.add("code");
    wordDict3.add("cade");
    wordDict3.add("dade");
    wordDict3.add("date");
    wordDict3.add("data");
    wordDict3.add("cote");
    LinkedList<LinkedList<String>> ladder3 = new LinkedList<>();
    LinkedList<String> ladder3_1 = new LinkedList<>();
    ladder3_1.add("data");
    ladder3.add(ladder3_1);
    LinkedList<String> ladder3_2 = new LinkedList<>();
    ladder3_2.add("data");
    ladder3_2.add("date");
    ladder3.add(ladder3_2);
    LinkedList<String> ladder3_3 = new LinkedList<>();
    ladder3_3.add("dade");
    ladder3.add(ladder3_3);
    LinkedList<String> ladder3_4 = new LinkedList<>();
    ladder3_4.add("cade");
    ladder3.add(ladder3_4);
    LinkedList<LinkedList<String>> result3 = WordLadder.searchLayer(wordQueue3,endWord3,wordDict3);
    //System.out.println(result3);
    Assert.assertEquals(ladder3,result3);
}

/**
*
* Method: findLadder(HashSet<String> dict, String beg, String end)
*
*/
@Test
public void testFindLadder() throws Exception {
//TODO: Test goes here...

    //dict,beg=hghghg,end=data hghghg is not in dict
    HashSet<String> dict = new HashSet<>();
    dict.add("data");
    LinkedList<String> output = new LinkedList<>();
    LinkedList<String> result = WordLadder.findLadder(dict,"hghghg","data");
    Assert.assertEquals(output,result);
    //dict,beg=data,end=data
    HashSet<String> dict1 = new HashSet<>();
    dict1.add("data");
    LinkedList<String> output1 = new LinkedList<>();
    LinkedList<String> result1 = WordLadder.findLadder(dict1,"data","data");
    Assert.assertEquals(output1,result1);
    //dict,beg=dat,end=data
    HashSet<String> dict2 = new HashSet<>();
    dict2.add("dat");
    dict2.add("data");
    LinkedList<String> output2 = new LinkedList<>();
    LinkedList<String> result2 = WordLadder.findLadder(dict2,"dat","data");
    Assert.assertEquals(output2,result2);
    //dict,beg=cote,end=data
    HashSet<String> dict3 = new HashSet<>();
    dict3.add("code");
    dict3.add("cade");
    dict3.add("dade");
    dict3.add("date");
    dict3.add("data");
    LinkedList<String> output3 = new LinkedList<>();
    output3.add("data");
    output3.add("date");
    output3.add("dade");
    output3.add("cade");
    output3.add("code");
    LinkedList<String> result3 = WordLadder.findLadder(dict3,"code","data");
    //System.out.println(result3);
    Assert.assertEquals(output3,result3);

    //dict,beg=zymometers,end=zygomatics
    HashSet<String> dict4 = new HashSet<>();
    dict4.add("zymometers");
    dict4.add("zygomatics");
    LinkedList<String> output4 = new LinkedList<>();
    LinkedList<String> result4 = WordLadder.findLadder(dict4,"zymometers","zygomatics");
    Assert.assertEquals(output4,result4);

    //dict,beg=code,end=cade
    HashSet<String> dict5 = new HashSet<>();
    dict5.add("code");
    dict5.add("cade");
    LinkedList<String> output5 = new LinkedList<>();
    output5.add("cade");
    output5.add("code");
    LinkedList<String> result5 = WordLadder.findLadder(dict5,"code","cade");
    System.out.println(result5);
    Assert.assertEquals(output5,result5);
}


}
