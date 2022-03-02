/**
 * <h1>Duke</h1>
 * The Duke program implements an WishList application that
 * simply adds different type of wishes into the wish list.
 * It is also capable of finding all your different wishes!
 * <p>
 *
 * @author  Ong Siying Falicia
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println("[iWish]: Hello I am iWish, what can i do for you?");
        TaskManager iWish = new TaskManager();
        iWish.startUp();
    }
}
