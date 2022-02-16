package util.miscellaneous;

public interface Chatbot {
    String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    String HORIZONTAL_LINE = "───────────────────────────────────────────────────────────────────────";
    String GREETING_MSG_01 = " I have been waiting for you, Mister Winters.";
    String GREETING_MSG_02 = " Forgive my manners, call me the Duke. Now to business.";
    String ITEM_NOT_EXIST_MSG = "Ah...The task you choose doesn't exist on your list.";
    String MARKED_MSG = " Aha! An interesting selection!";
    String UNMARKED_MSG = " Ah... What a shame!";
    String DELETE_MSG = " So you have chosen to delete this task!";
    String GOODBYE_MSG = " Good day, then!";
    String NO_TASK_MSG = " Sorry but you haven't input a task here.";
    String NO_DATE_MSG = " Sorry but you haven't input a date here.";
    String IO_ERROR_MSG = " Oh no! File IO error just occurred.";
    String NO_PREVIOUS_RECORD = " There is currently no save file.";


    String ADD_TODO_CMD = "Todo ";
    String ADD_DEADLINE_CMD = "Deadline ";
    String ADD_EVENT_CMD = "Event ";
    String MARK_TASK_CMD = "Mark ";
    String UNMARK_TASK_CMD = "Unmark ";
    String LIST_TASKS_CMD = "List";
    String DEADLINE_OF_TASK_CMD = "/by";
    String DURATION_OF_EVENT_CMD = "/at";
    String DELETE_CMD = "Delete ";
    String SAVE_CMD = "Save";

    String FILEPATH = "data/data.txt";

    String TODO_TYPE = "[T]";
    String DEADLINE_TYPE = "[D]";
    String EVENT_TYPE = "[E]";
    String DONE_STATUS = "[X]";
    
    int DELETE_INDEX = 7;
    int TODO_TASK_INDEX = 4;
    int DEADLINE_TASK_INDEX = 8;
    int EVENT_TASK_INDEX = 5;
    int TIME_INDEX = 4;
    int MARKED_ITEM_INDEX = 5;
    int UNMARKED_ITEM_INDEX = 7;
    int ERROR_INDICATION_NUMBER = 1;
    int TASK_DETAIL = 6;
    int TASK_STATUS_INDEX = 3;
}
