package Superhero.wordlist;

import java.util.ArrayList;

/**
 * Represents list of Vocab (vList) as well as its associated methods
 */
public class VocabList {
    private ArrayList<Vocabulary> vList = new ArrayList<Vocabulary>();
    private final String dottedLine = "________________________________________________________________________________";
    private final String tildeLine = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    /**
     * Add Vocab to vList of VocabList
     * @param input Vocab to be added
     */
    public void appendList(Vocabulary input){
        vList.add(input);
        System.out.println("You've just stored \"" + input + "\" in your list!\n" +
                dottedLine);
    }

    /**
     * Print vList of VocabList
     */
    public void printList(){
        System.out.println(tildeLine);
        if (vList.size() == 0) {
            System.out.println(" You have not input any words yet!");
        }
        else {
            System.out.println(" These are the words you want to learn!");
            for (int i = 0; i < vList.size(); i++) {
                System.out.println(vList.get(i).toString());
            }
        }
        System.out.println(tildeLine);
    }

    /**
     * Get vList from VocabList
     * @return vList of VocabList
     */
    public ArrayList<Vocabulary> getList() {
        return vList;
    }
}
