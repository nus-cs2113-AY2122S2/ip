# JRobo | Personal Todo App

## User Guide

JRobo is a command line application that you can use as your personal task tracker assistant.

JRobo consists of various different commands and three type of tasks, which are **todo**, **deadline**, and **event**.
<br/><br/>
**Todos** are tasks that do not have any specific time or duration.
<br/>
**Deadlines** are tasks that have a specific deadline that can be specified while adding.
<br/>
**Events** are tasks that have a specific time and duration that can be specified while adding.

Note that, even if the application is terminated, JRobo will keep all tasks for you in his memory.

## Commands

### Add Task

Tasks can be added using **todo**, **deadline**, **event** commands, or with their shortcut notations.

#### Command format:

`todo {description}`<br>
`deadline {description}/by {any time}`<br>
`event {description} /at {any time}`

#### Shortcuts:

`t {description}`<br>
`d {description} /by {any time}`<br>
`e {description} /at {any time}`

### Remove Task

Tasks can be removed using **delete** command, or with its shortcut **del**.

#### Command format:

`delete {task_number}`

#### Shortcuts:

`del {task_number}`

### List All Tasks

All tasks can be listed using **list** command, or with its shortcut **ls**.

#### Command format:

`list`

#### Shortcuts:

`ls`

### Check Task

Tasks can be marked as done using **mark** command, or with its shortcut **m**.

#### Command format:

`mark {task_number}`

#### Shortcuts:

`m {task_number}`

### Uncheck Task

Tasks can be marked as done using **unmark** command, or with its shortcut **um**.

#### Command format:

`unmark {task_number}`

#### Shortcuts:

`um {task_number}`

### Find Specific Task

All tasks can be searched and filtered using **find** command, or with its shortcut **f**.

#### Command format:

`find {search_key`

#### Shortcuts:

`f {search_key}`

### Termination

Application can be quited using **quit** command, or with its shortcut **q**.

#### Command format:

`quit`

#### Shortcuts:

`q`
