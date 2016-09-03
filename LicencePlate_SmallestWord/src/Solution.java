
import java.io.*;
import java.util.*;


/*
Complexity:

n - words, O(n)
m - wordlen, 

5BCAC57 -> TAB, sort - > ABT



1ZZZ111
a. O(m) , Z3  - A2F1C1D2Z4
b. O(nm)

z1
z2
z3 - > []

A,
A1
A2 -> [
A3


, 
APP, A1P2

LI LBOY -> BILLOY

O(


BAT - ABT
PAP

TAB


PCCCCCACCCCCCP

PCCCCCACCOODFDCCCCP

BAC

CAB        X
CABBAGE    
..
..
..


 */

class Solution {
  Map<String, List<String>> dictMap = new HashMap<String, List<String>>();
  
 static  String [] words = {"cat", "app", "add"};
  
  public static void main(String[] args) {
    Solution sol = new Solution();
    String st = "AABCCC";
    
    sol.charFreq(st.toCharArray());
    sol.processDict(words);
    
    st = "pap";
    String result = sol.findWord(st);
    System.out.println("result: " + result);
  }
  
  public String findWord(String licence) {
    if (licence == null || licence.length() < 1) {
      return null;
    }
    
    char [] chArr = licence.toCharArray();
    Arrays.sort(chArr);
    
    List<String> freqList = charFreq(chArr);
    System.out.println(freqList.toString());
    
    int size = freqList.size();
    if (size < 1) {
      return null;
    }
    System.out.println("freq list: " + freqList.toString());
    String key = freqList.get(0);
      List<String> prev = dictMap.get(key);
   
    for (int i=1; i<size; i++) {
      key = freqList.get(i);
      List<String> list = dictMap.get(key);
      System.out.println("list: " + list.toString());
      prev.retainAll (list);
      System.out.println("prev: " + prev.toString());
      
    }
    if (prev.size() > 0) {
      return prev.get(0);
    }
    
    return null;
    
  }
  

  
  private List<String> charFreq(char [] data) {
    List<String> list = new ArrayList<String>();
    
    char prev = data[0];
    int count = 1;
    
    int i = 1;
    int len = data.length;
    String key = "";
    while(i < len) {
      if (data[i] == prev) {
        ++count;
      } else {
        key = prev + String.valueOf(count);
        list.add(key);
        prev = data[i];
        count = 1;
      }
      ++i;
    }
    if (prev != data[len-1]) {
      key = prev + String.valueOf(count);
      list.add(key);
    }
    if (count > 1) {
      key = prev + String.valueOf(count);
      list.add(key);
    }
    
    
    
    return list;
    
  }
  
  public void processDict(String [] words) {
    if (words == null || words.length < 1) {
      return;
    }
    
    for (String word: words) {
      char [] chArr = word.toCharArray();
      Arrays.sort(chArr);
      
      StringBuffer sb = new StringBuffer();
      int count = 1;
      char prev = chArr[0];
      String key = prev + String.valueOf(count);
      List<String> list = new ArrayList<String>();
      if (dictMap.containsKey(key)) {
              list = dictMap.get(key);
      }
      list.add(word);
      dictMap.put(key, list); 
      
      for (int i=1; i<chArr.length; i++) {
          if (chArr[i] == prev) {
            ++count;
            
          } else {
            prev = chArr[i];
            count = 1;
             
          }
             key = prev + String.valueOf(count);
            list = new ArrayList<String>();
            if (dictMap.containsKey(key)) {
              list = dictMap.get(key);
            }
            list.add(word);
            dictMap.put(key, list);
      }
    }
    
    System.out.println(dictMap.toString());
  }
}

