package com.demo.leetcode;

import java.util.*;

/**
 * <p><b>Description:</b>
 * 给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词，
 * 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。
 *
 * 编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:54 on 2021/3/22
 * @version V0.1
 * @classNmae FindLaddersSolution
 */
public class FindLaddersSolution {



    public static void main(String[] args) {
        FindLaddersSolution solution = new FindLaddersSolution();
//        String[] words= {"hot","dot","dog","lot","log","cog"};
//        String beginWord = "hit";
//        String endWord = "cog";
//        String[] words=  {"hot","dog"};
//        String beginWord = "hot";
//        String endWord = "dog";
//        String[] words={"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
//        String[] words={"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
//
//        String beginWord = "qa";
//        String endWord = "sq";
        String[] words={"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","mapper","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
        String beginWord = "cet";
        String endWord = "ism";
        List<String> wordList = new ArrayList<>();
        for (String word:words){
            wordList.add(word);
        }
        List<String> routes = solution.findLadders(beginWord, endWord, wordList);
        for (String route:routes){
            System.out.print(route + " ");
        }
    }

    /**
     * ["hot","dot","dog","lot","log","cog"]
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList){
        List<String> resultList = new ArrayList<>();
        if(!wordList.contains(endWord)){
            return   resultList;
        }

        Map<String,List<String>> listMap = new HashMap<>();
        wordList.add(beginWord);
        for (int i=0;i<wordList.size();i++){
            String key = wordList.get(i);
            List<String> list ;
            if(listMap.containsKey(key)){
                list = listMap.get(key);

            }else{
                list =  new ArrayList<>();
                listMap.put(key,list);
            }
            for (int j=0;j<wordList.size();j++){
                String word = wordList.get(j);
                if(isOneWordDiff(key,word)){
                    list.add(word);
                }
            }
        }

        List routList =new ArrayList();
        Set<String> sets = new HashSet<>();
        findRoute(listMap,sets,routList,beginWord,endWord);
        return routList;
    }

    private void findRoute(Map<String,List<String>> listMap, Set<String> sets, List path, String beginWord, String endWord){
        //其实 word 就是需要转换的word 则直接返回
        if(beginWord.equals(endWord)){
            path.add(endWord);
            return ;
        }

        System.out.println("beginWord="+beginWord);
        //当前路径包含beginWord,避免路径死循环，直接返回
        if(sets.contains(beginWord)){
            return ;
        }
        if(isOneWordDiff(beginWord,endWord)){
            path.add(beginWord);
            path.add(endWord);
            return ;
        }
        sets.add(beginWord);
        path.add(beginWord);
        //可选路径
        List<String> routes = listMap.get(beginWord);
        for (String word:routes){

            findRoute(listMap,sets,path,word,endWord);

            if(path.contains(endWord)){
                return;
            }
        }
        path.remove(path.size()-1);
    }




    public boolean isOneWordDiff(String word1,String word2 ){
        int count = 0;
        int len = word1.length();
        for(int i = 0;i<len;i++){
            if(word1.charAt(i)!=word2.charAt(i)){
                count++;
            }
        }
        return count==1;
    }
}