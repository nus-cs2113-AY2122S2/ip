/**
 * <h1>Duke</h1>
 * The Duke program is a iWish ChatBot that implements an WishList application that
 * simply adds different type of wish task into the wish tasklist.
 * It is also capable of finding all your different wish task type!
 * <p>
 *
 * @author  Ong Siying Falicia
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println("[iWish]: Hello I am iWish, what can i do for you?");
        TaskManager iWish = new TaskManager();
        iWish.start();
    }
}
