# User Guide

## Features 

### 3 types of task
- `event`: Add a task of what you want to do.
- `deadline`: Add a task of what you have to do with a corresponding deadline.
- `event`: Add a task of what you will do with a corresponding schedule.

### Mark as done
You can mark the task as done and remove them later. And if you accidentally mark the wrong task, it's okay to unmark it.

### Find
Too many tasks? No worry, use find to filter your tasks.

### On disk storage
- Your tasks are still there even you quit the program.
- The tasks are stored in flat file (.txt), you can easily check and modify them.
- The status of tasks (is done?) is also stored.

### 
## Usage
Note: String in quotes are recognized as a single token, but if there are no quotes, it will be multiple tokens. By default, we wish all strings are quoted.

### `todo` Add multiple todos.

Usage:
`todo {task1} {task2} {task3} ...`

Example of usage: 

`todo "good morning" "good afternoon" good evening `

Expected outcome:
```
Added: [T][ ] good morning
Added: [T][ ] good afternoon
Added: [T][ ] good
Added: [T][ ] evening
Now you have 4 task(s) in the list.
```

Description of the outcome.

Since `good evening` is not quoted in command, it is recognized as two todos: `good` and `evening`.

### `deadline` Add multiple deadlines
Usage: `deadline {ddl 1} {ddl 2} ... /by {ddl time for 1, 2, ...} {ddl a} {ddl b} ... /by {ddl time for a, b, ...} ...`

Example of usage:
`deadline eat_Jan_01_2022 "dance on Jan 01 2022" /by 2022-01-01 "sleep Mar 01 2022" /by 2020-03-01`

Expected outcome:
```
Added: [D][ ] eat_Jan_01_2022 (by: Jan 01 2022)
Added: [D][ ] dance on Jan 01 2022 (by: Jan 01 2022)
Added: [D][ ] sleep Mar 01 2022 (by: Mar 01 2020)
Now you have 3 task(s) in the list.
```

Description of the outcome.

`eat` and `dance` are followed by `/by 2022-01-01`, so the deadline for these two tasks is 2022-01-01.
`sleep` is followed by `/by 2022-03-01'` so the deadline for this one is 2022-03-01

### `event`
Add multiple events

Usage: `event {event 1} {event 2} ... /at {event time for 1, 2, ...} {event a} {event b} ... /by {event time for a, b, ...} ...`

Example of usage:
`event eat_Jan_01_2022 "dance on Jan 01 2022" /at 2022-01-01 "sleep Mar 01 2022" /at 2020-03-01`

Expected outcome:
```
Added: [E][ ] eat_Jan_01_2022 (at: Jan 01 2022)
Added: [E][ ] dance on Jan 01 2022 (at: Jan 01 2022)
Added: [E][ ] sleep Mar 01 2022 (at: Mar 01 2020)
```

Description of the outcome.

`eat` and `dance` are followed by `/at 2022-01-01`, so the schedule for these two tasks is 2022-01-01.
`sleep` is followed by `/at 2022-03-01`, so the event for this one is 2022-03-01

### `list` List all tasks' info, containing type, isDone, content, and ddl/schedule.

Usage `list`

Example of usage:

```
todo "todo a" "todo b"
deadline ddl1 ddl2 ddl3 /by 2022-01-01
event "event 1" "event 2" /at 2022-02-02
```
```
list
```

Expected outcome:
```
1. [T][ ] todo a
2. [T][ ] todo b
3. [D][ ] ddl1 (by: Jan 01 2022)
4. [D][ ] ddl2 (by: Jan 01 2022)
5. [D][ ] ddl3 (by: Jan 01 2022)
6. [E][ ] event 1 (at: Feb 02 2022)
7. [E][ ] event 2 (at: Feb 02 2022)
```

### `mark` Mark one task as done.

Usage `mark {task number} {task number} {task number}`

Example of usage:
```
todo "todo a" "todo b"
deadline ddl1 ddl2 ddl3 /by 2022-01-01
event "event 1" "event 2" /at 2022-02-02
```
`mark 6 3 7 1`

Expected outcome:
`mark` outcome
```
[E][X] event 1 (at: Feb 02 2022)
[D][X] ddl1 (by: Jan 01 2022)
[E][X] event 2 (at: Feb 02 2022)
[T][X] todo a
Nice! I've marked those valid tasks as done
```

`list` outcome
```=
1. [T][X] todo a
2. [T][ ] todo b
3. [D][X] ddl1 (by: Jan 01 2022)
4. [D][ ] ddl2 (by: Jan 01 2022)
5. [D][ ] ddl3 (by: Jan 01 2022)
6. [E][X] event 1 (at: Feb 02 2022)
7. [E][X] event 2 (at: Feb 02 2022)
```


### `unmark` Remove the isDone mark of one task. Usage is same as `mark`.

### `delete` Delete multiple tasks

Usage `delete {task number} {task number} {task number}`

Example of usage:
```
todo "todo a" "todo b"
deadline ddl1 ddl2 ddl3 /by 2022-01-01
event "event 1" "event 2" /at 2022-02-02
```
`delete 5 7 3 2`

Expected outcome:

`delete` outcome
```
Noted. I've removed this task:
	[D][ ] ddl3 (by: Jan 01 2022)
Noted. I've removed this task:
	[E][ ] event 2 (at: Feb 02 2022)
Noted. I've removed this task:
	[D][ ] ddl1 (by: Jan 01 2022)
Noted. I've removed this task:
	[T][ ] todo b
```

`list` outcome
```
1. [T][ ] todo a
2. [D][ ] ddl2 (by: Jan 01 2022)
3. [E][ ] event 1 (at: Feb 02 2022)
```

### `find`
Find tasks by multiple keywords (if one of them is contained).

Usage: `find keyword_1 keyword_2 ...`

Example of usage:

`todo "key but no keyword" "keyword_1 here" "here keyword_2" "hahaha" "I don't have keyword"`

`find keyword_1 keyword_2 keyword_3`

Expected outcome:
```
2. [T][ ] keyword_1 here
3. [T][ ] here keyword_2
```