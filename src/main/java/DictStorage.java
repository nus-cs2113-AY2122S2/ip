public class DictStorage {
    private Vocabulary[] dict = new Vocabulary[100];
    private int dictLength = 0;

    public void appendDict(String input){
        dict[dictLength] =new Vocabulary(input);
        dictLength++;
        System.out.println("You've just stored \"" + input + "\" in your dictionary!");
        System.out.println("____________________________________________________________");
    }

    public void printDict(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if (dictLength == 0) {
            System.out.println(" You have not input any words yet!");
        }
        else {
            System.out.println(" These are the words you claim to know!");
            for (int i = 0; i < dictLength; i++) {
                String mark;
                if (dict[i].isConfirmed()) {
                    mark = "X";
                }
                else {
                    mark = " ";
                }
                System.out.println((i + 1) + ". [" + mark + "] " + dict[i].getWord());
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    public Vocabulary[] getDict(){
        return this.dict;
    }
}
