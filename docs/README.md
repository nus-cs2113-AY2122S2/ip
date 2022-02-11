<div align="center">

# Sailfish

The minimalist task manager app for your daily needs.

</div>

## Usage
To run the application, enter the following command in the terminal:
```cmd
$ java -jar ip.jar
```

## Features
The following subsections highlight the main features of the application and 
how to use them.

### Add Tasks
The application provides multiple support for storing multiple task types.
- Add a `Todo` task.
  - `>>> todo description`
- Add a `Deadline` task.
  - `>>> deadline description /by by_description`
- Add an `Event` task.
  - `>>> event description /at at_description`

### List Tasks
To list all stored tasks, simply input the following command:
- `list`

This command is useful for figuring out the index of the task, which is required
for many other features.

### Mark as Done/Undone
Tasks can be marked as done or undone by specifying their index.
- Marking as done
  - `>>> mark task_index`
- Marking as undone
  - `>>> unmark task_index`

### Delete Tasks
Users may delete any task they need by specifying the index to delete:
- `>>> delete task_index`