import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final UI ui;

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** format in which datetime is read from command line */
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");

    /** format for printing datetime to string and reading from text file */
    private static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("LLL dd uuuu, hh:mm a");

    public TaskList(UI ui){
        tasks = new ArrayList<>();
        this.ui = ui;
    }

    public void mark(String info){
        int index;
        try{
            index = Integer.parseInt(info);
            tasks.get(index-DISPLAYED_INDEX_OFFSET).setDone(true);
        } catch (NumberFormatException e){
            ui.showIncorrectFormatMsg();
            return;
        } catch (IndexOutOfBoundsException f){
            ui.showNonexistentTaskMsg();
            return;
        }
        ui.showSuccessfulMark(tasks.get(index-DISPLAYED_INDEX_OFFSET));
    }

    public void unmark(String info){
        int index;
        try{
            index = Integer.parseInt(info);
            tasks.get(index-DISPLAYED_INDEX_OFFSET).setDone(false);
        } catch (NumberFormatException e){
            ui.showIncorrectFormatMsg();
            return;
        } catch (IndexOutOfBoundsException f){
            ui.showNonexistentTaskMsg();
            return;
        }
        ui.showSuccessfulUnmark(tasks.get(index-DISPLAYED_INDEX_OFFSET));
    }

    public LocalDateTime parseDateTime(String rawDate){
        try{
            return LocalDateTime.parse(rawDate, DATE_TIME_INPUT_FORMATTER);
        } catch (DateTimeParseException e){
            return null;
        }
    }

    public LocalDateTime parseDateTimeFromText(String rawDate){
        try{
            return LocalDateTime.parse(rawDate, DATE_TIME_OUTPUT_FORMATTER);
        } catch (DateTimeParseException e){
            return null;
        }
    }

    public void addTodo(String info){
        if(info == null){
            ui.showIncorrectFormatMsg();
            return;
        }
        Task newTask = new Task(info);
        tasks.add(newTask);
        ui.showSuccessfulTaskAdded(newTask);
    }

    public void addDeadline(String info){
        if(info == null || !info.contains("/by")){
            ui.showIncorrectFormatMsg();
            return;
        }

        String by, task;
        LocalDateTime byDate;
        try{
            by = info.substring(info.indexOf("/by")+4);
            byDate = parseDateTime(by);
            task = info.substring(0, info.indexOf("/by")-1);
        }catch (StringIndexOutOfBoundsException e){
            ui.showIncorrectFormatMsg();
            return;
        }

        Deadline newDeadline = new Deadline(task, by, byDate);
        tasks.add(newDeadline);
        ui.showSuccessfulTaskAdded(newDeadline);
    }

    public void addEvent(String info){
        if(info == null || !info.contains("/at")){
            ui.showIncorrectFormatMsg();
            return;
        }
        String at, task;
        LocalDateTime atDate;
        try{
            at = info.substring(info.indexOf("/at")+4);
            atDate = parseDateTime(at);
            task = info.substring(0, info.indexOf("/at")-1);
        }catch (StringIndexOutOfBoundsException e){
            ui.showIncorrectFormatMsg();
            return;
        }

        Event newEvent = new Event(task, at, atDate);
        tasks.add(newEvent);
        ui.showSuccessfulTaskAdded(newEvent);
    }

    public void delete(String info){
        int index;
        Task removedTask;
        try{
            index = Integer.parseInt(info);
            removedTask = tasks.remove(index-DISPLAYED_INDEX_OFFSET);
        } catch (NumberFormatException e){
            ui.showIncorrectFormatMsg();
            return;
        }catch (IndexOutOfBoundsException f){
            ui.showNonexistentTaskMsg();
            return;
        }
        ui.showSuccessfulDelete(removedTask, tasks.size());
    }

    public void addEventFromText(String info){
        String description, at;
        LocalDateTime atDate;
        try{
            description = info.substring(0,info.indexOf("(at:")-1);
            at = info.substring(info.indexOf("(at:")+5, info.length()-DISPLAYED_INDEX_OFFSET);
            atDate = parseDateTimeFromText(at);
        }catch(IndexOutOfBoundsException e){
            ui.showDecodeErrorMsg();
            return;
        }
        if(at.equals("")){
            ui.showDecodeErrorMsg();
            return;
        }

        Event newEvent = new Event(description, at, atDate);
        tasks.add(newEvent);
    }

    public void addDeadlineFromText(String info){
        String description, by;
        LocalDateTime byDate;
        try{
            description = info.substring(0,info.indexOf("(by:")-1);
            by = info.substring(info.indexOf("(by:")+5, info.length()-DISPLAYED_INDEX_OFFSET);
            byDate = parseDateTimeFromText(by);
        }catch(IndexOutOfBoundsException e){
            ui.showDecodeErrorMsg();
            return;
        }
        if(by.equals("")){
            ui.showDecodeErrorMsg();
            return;
        }
        Deadline newDeadline = new Deadline(description, by, byDate);
        tasks.add(newDeadline);
    }

    public void addTodofromText(String info){
        Task newTodo = new Task(info);
        tasks.add(newTodo);
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }
}
