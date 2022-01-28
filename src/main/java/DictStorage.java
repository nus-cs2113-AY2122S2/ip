public class DictStorage {
    private String[] dict = new String[100];
    private int dictLength = 0;

    public void appendDict(String input){
        dict[dictLength] = input;
        dictLength++;
        System.out.println("You've just stored \"" + input + "\" in your dictionary!");
        System.out.println("____________________________________________________________");
    }

    public void printDict(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if (dictLength == 0) {
            System.out.println(" You have not input any words yet!");
        }
        else {
            System.out.println("These are the words you claim to know!");
            for (int i = 0; i < dictLength; i++) {
                System.out.println((i + 1) + ". " + dict[i]);
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
