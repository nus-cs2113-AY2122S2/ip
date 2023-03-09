# Project Cheems
### Project Cheems is a CLI todo application.

## Prerequisites
### Ensure that you have JRE on your device to run the .JAR file.

## Symbols & Meanings
| Symbols | Meaning  |
|---------|----------|
| [D]     | Deadline |
| [E]     | Event    |
| [T]     | ToDo     |
| [ ]     | Not Done |
| [X]     | Done     |

Deadline, Event, and Todo are the types of the Task while Not Done and Done are the status of the Task.

## Commands
### Add Deadline
Adds a Deadline to your list. Format: `deadline <task-description> /<deadline>`. Example:
```
deadline CS2113 iP /tonight 2359
-------------------------------------------
I hamve addemd: 
[D][ ] CS2113 iP  due: (tonight 2359)
-------------------------------------------
```

### Add Event
Adds an Event to your list. Format: `event <task-description> /<timing>`. Example:
```
event CS2113 lecture /Friday 1600 - 1800
-------------------------------------------
I hamve addemd: 
[E][ ] CS2113 lecture  on: (Friday 1600 - 1800)
-------------------------------------------
```

### Add ToDo
Adds a ToDo to your list. Format: `todo <task-description>`. Example: 
```
todo Drink water
-------------------------------------------
I hamve addemd: 
[T][ ] Drink water
-------------------------------------------
```

### List all tasks
Lists all the tasks that you have. Format: `list`.
```
list
-------------------------------------------
Hemre are the tamsks im youmr limst:
1. [D][ ] CS2113 iP  due: (tonight 2359)
2. [E][ ] CS2113 lecture  on: (Friday 1600 - 1800)
3. [T][ ] Drink water
-------------------------------------------
```

### Delete Task
Deletes the Task on the specified index. Format: `delete <task-number>`. Example:
```
delete 1
-------------------------------------------
Succesfully removed: 
1. [D][ ] CS2113 iP  due: (tonight 2359)
-------------------------------------------
```

### Mark Task as done
Marks the Task as done. Format: `mark <task-number>`. Example:
```
mark 1
-------------------------------------------
Ok! I hamve markemd the tamsk:
[E][X] CS2113 lecture  on: (Friday 1600 - 1800)
-------------------------------------------
```

### Mark Task as not done.
Marks the Task as not done. Format: `unmark <task-number>`. Example:
```
unmark 1
-------------------------------------------
Oof! I hamve unmarkemd the tamsk: 
[E][ ] CS2113 lecture  on: (Friday 1600 - 1800)
-------------------------------------------
```

### Find tasks
Filters the task list to match with your keyword. Format: `find <key-word>`. Example:
```
find CS2113
-------------------------------------------
Hemre are the matchimng tamsks in youmr limst: 
1. [E][ ] CS2113 lecture  on: (Friday 1600 - 1800)
-------------------------------------------
```

### Exit
Exits the application. Format: `bye`.
```
bye
-------------------------------------------
Goomdbye. See you nemxt time frem!
-------------------------------------------
```
