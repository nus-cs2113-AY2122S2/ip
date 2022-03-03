# Duke - A Command-Line Task Manager

Duke is a desktop app meant to help users organise their tasks. 

- [Quick Start](https://github.com/hearobe/ip/blob/master/docs/README.md#quick-start)
- [Features](https://github.com/hearobe/ip/blob/master/docs/README.md#features)
  - Create a Task
    - [Add a todo](https://github.com/hearobe/ip/blob/master/docs/README.md#add-a-todo-todo)
    - [Add a deadline](https://github.com/hearobe/ip/blob/master/docs/README.md#add-a-deadline-deadline)
    - [Add an event](https://github.com/hearobe/ip/blob/master/docs/README.md#add-an-event-event)
  - [Display your Tasks](https://github.com/hearobe/ip/blob/master/docs/README.md#display-your-tasks-list)
  - [Mark a Task as Completed](https://github.com/hearobe/ip/blob/master/docs/README.md#mark-a-task-as-completed-mark)
  - [Mark a Task as Incomplete](https://github.com/hearobe/ip/blob/master/docs/README.md#mark-a-task-as-incomplete-unmark)
  - [Delete a Task](https://github.com/hearobe/ip/blob/master/docs/README.md#delete-a-task-delete)
  - [Search for a Task](https://github.com/hearobe/ip/blob/master/docs/README.md#search-for-a-task-find)
  - [Save your Tasks](https://github.com/hearobe/ip/blob/master/docs/README.md#save-your-tasks-save)
  - [Quit](https://github.com/hearobe/ip/blob/master/docs/README.md#quit-bye)
 - [Command Summary](https://github.com/hearobe/ip/blob/master/docs/README.md#command-summary)

## Quick Start

1. Ensure you have Java 11 installed on your computer
2. Download the latest version of ```ip.jar```
3. Copy the file to your preferred folder as the home folder for Duke
4. Double click the file to start the app. Your terminal or command prompt should open
5. Type ```java -jar ip.jar``` and press Enter
6. Type any command to start using the app!

## Features 

### Add a todo: ```todo```

Adds a new todo to the app

Format: ```todo [TASK DESCRIPTION]```

Example: ```todo read a book```

### Add a deadline: ```deadline```

Adds a new deadline to the app

Format: ```deadline [TASK DESCRIPTION] /by [DUE DATE]```
- If ```[DUE DATE]``` is written in this date and time format, 26/12/2022 1600, it will be saved and displayed as follows: Dec 26 2022, 04:00 PM
- Otherwise, ```[DUE DATE]``` will be saved in the same format as is input

Example 1: 
```
deadline return book /by Tuesday

Got it. I've added this task:
	[D][ ] return book (by: Tuesday)
```

Example 2: 
```
deadline return book /by 26/12/2022 1600

Got it. I've added this task:
	[D][ ] return book (by: Dec 26 2022, 04:00 PM)
```

### Add an event: ```event```

Adds a new event to the app

Format: ```event [TASK DESCRIPTION] /at [EVENT DATE]```
- If ```[EVENT DATE]``` is written in this date and time format, 26/12/2022 1600, it will be saved and displayed as follows: Dec 26 2022, 04:00 PM
- Otherwise, ```[EVENT DATE]``` will be saved in the same format as is input

Example 1: 
```
event book fair /at Tuesday

Got it. I've added this task:
	[D][ ] book fair (at: Tuesday)
```

Example 2: 
```
event book fair /by 26/12/2022 1600

Got it. I've added this task:
	[D][ ] book fair (at: Dec 26 2022, 04:00 PM)
```

### Display your Tasks: ```list```

Show all tasks

Format: ```list```

### Mark a Task as Completed: ```mark```

Marks a task as completed

Format: ```mark [TASK INDEX]```

Example: ```mark 2```

### Mark a Task as Incomplete: ```unmark```

Marks a task as not yet completed

Format: ```unmark [TASK INDEX]```

Example: ```unmark 2```

### Delete a Task: ```delete```

Deletes a task

Format: ```delete [TASK INDEX]```

Example: 
```
delete 2

I've deleted this task:
	[D][ ] return book (by: Tuesday)
Now you have 1 in the list.
```
### Search for a Task: ```find```

Searches for a task based on its description

Format: ```find [SEARCH TERM]```

Example: ```find book```

### Save your Tasks: ```save```

Saves all tasks to a text file that can be read from the next time Duke is started

Format: ```save```

Example: ```save```

### Quit: ```bye```

Exits the Duke application

Format: ```bye```

Example: ```bye```

## Command Summary

| Action  | Format |
| ------------- | ------------- |
| Add a todo  | ```todo [TASK DESCRIPTION]``` |
| Add a deadline  | ```deadline [TASK DESCRIPTION]``` |
| Add an event  | ```event [TASK DESCRIPTION]``` |
| List all tasks  | ```list```  |
| Mark a task as completed | ```mark [TASK INDEX]``` |
| Mark a task as incomplete | ```unmark [TASK INDEX]``` |
| Delete a Task  | ```delete```  |
| Search all tasks  | ```find [SEARCH TERM]```  |
| Save all tasks  | ```save```  |
| Quit  | ```bye```  |
