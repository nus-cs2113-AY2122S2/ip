package WordList;

public class VocabList {
    private Vocabulary[] vList = new Vocabulary[100];
    private int listLength = 0;

    public void appendList(Vocabulary input){
        vList[listLength] = input;
        listLength++;
        System.out.println("You've just stored \"" + input + "\" in your list!");
        System.out.println("____________________________________________________________");
    }

    public void printList(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if (listLength == 0) {
            System.out.println(" You have not input any words yet!");
        }
        else {
            System.out.println(" These are the words you want to learn!");
            for (int i = 0; i < listLength; i++) {
                System.out.println(vList[i].toString());
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    public Vocabulary[] getList(){
        return vList;
    }
}
