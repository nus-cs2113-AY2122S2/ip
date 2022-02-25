package Duke;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public static void userInterface() throws DukeException {
        ArrayList<Task> userLists = new ArrayList<>();
        Storage.loadSaveFile(userLists);
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        //main handler for receiving input
        while (!userInput.equals("bye")) {
            String[] tokens = userInput.split(" ");
            try {
                Parser userInputTokens = new Parser(userInput);
                //change user input into an array of tokens
                switch (userInputTokens.getTokens()[0]) {
                case "list":
                    String allTasks = "Here are the tasks in your list:\n" +
                            TaskList.listTask(userLists);
                    allTasks = Wrapper.wrapMessage(allTasks);
                    System.out.println(allTasks);
                    break;
                case "todo":
                    Todo newTodo = new Todo(userInputTokens.getDescription());
                    TaskList.addTask(newTodo, userLists);
                    break;
                case "deadline":
                    Deadline newDeadline = new Deadline(
                            userInputTokens.getDescription(),
                            userInputTokens.getTime());
                    TaskList.addTask(newDeadline, userLists);
                    break;
                case "event":
                    //find index in user input tokens which contains the time separator
                    Event newEvent = new Event(
                            userInputTokens.getDescription(),
                            userInputTokens.getTime());
                    TaskList.addTask(newEvent, userLists);
                    break;
                case "mark":
                    int markIndex = userInputTokens.getMarkIndex();
                    if (markIndex > userLists.size()) {
                        throw new DukeExceptionMarkBounds();
                    }
                    userLists.get(markIndex).setMark();
                    System.out.println(
                            Wrapper.wrapMessage("Nice! I've marked this task as done:\n " +
                                    userLists.get(userInputTokens.getMarkIndex()).toString()
                            ));
                    break;
                case "unmark":
                    markIndex = userInputTokens.getMarkIndex();
                    if (markIndex > userLists.size()) {
                        throw new DukeExceptionMarkBounds();
                    }
                    userLists.get(userInputTokens.getMarkIndex()).unMark();
                    System.out.println(
                            Wrapper.wrapMessage("OK, I've marked this task as not done yet:\n " +
                                    userLists.get(userInputTokens.getMarkIndex()).toString()
                            ));
                    break;
                case "delete":
                    int deleteIndex = userInputTokens.getMarkIndex();
                    TaskList.deleteTask(userLists, deleteIndex);
                    break;
                case "find":
                    String foundTasks = "Here are the matching tasks in your list:\n" +
                            TaskList.findTask(userLists, userInputTokens.getDescription());
                    foundTasks = Wrapper.wrapMessage(foundTasks);
                    System.out.println(foundTasks);
                    break;
                default:
                }
            } catch (DukeExceptionCommand e) {
                System.out.println(
                        Wrapper.wrapMessage("OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
            } catch (DukeExceptionDescription e) {
                System.out.printf(
                        Wrapper.wrapMessage("OOPS!!! The description of a %s cannot be empty!\n"),
                        tokens[0]);
            } catch (DukeExceptionTiming e) {
                System.out.printf(
                        Wrapper.wrapMessage("OOPS!!! Please add the date and time for your %s" +
                                "in this format yyyy-mm-dd hh:mm!\n"),
                        tokens[0]);
            } catch (DukeExceptionList e) {
                System.out.println(
                        Wrapper.wrapMessage("OOPS!!! List should not have any other text after!\n"));
            } catch (DukeExceptionMark e) {
                System.out.printf(
                        Wrapper.wrapMessage("%s needs a number as an input\n"), tokens[0]);
            } catch (DukeExceptionMarkBounds e) {
                System.out.println(
                        Wrapper.wrapMessage("Number provided is not in the list\n"));
            } catch (DukeExceptionFind e) {
                System.out.println(
                        Wrapper.wrapMessage("Please add the words you want to search for" +
                                "eg: find book\n"));
            }
            userInput = input.nextLine();
        }
        Storage.saveList(userLists);
    }
}
