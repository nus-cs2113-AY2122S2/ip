# Vocabulary Superhero User Guide
The Vocabulary learning manager application to ensure your Vocabulary continues to expand!

## Features 

### Add a Vocabulary
Categorizes Vocabulary into 3 categories and adds them into the Vocabulary list, depending on when the word should be learnt.

**ToLearn** are words that can be learnt at any time.<br>
**Deadline** are words to be learnt by a certain date.<br>
**Event** are words to be learnt within a specified time frame.

#### Commands:

-Add `ToLearn` Vocabulary: `tolearn {vocabulary}`<br>
-Add `Deadline` Vocabulary: `deadline {vocabulary}/{date/time}`<br>
-Add `Event` Vocabulary: `event {vocabulary}/{date/time}`

Examples:

-`tolearn abrogate`<br>
-`event conduit/8pm-9pm`


### List all Vocabulary
List all Vocabulary in the Vocabulary list, allowing user to see all Vocabulary at one glance.

#### Commands:

-`list`


### Mark/Unmark a Vocabulary
Allows the user to mark a Vocabulary when it has been learnt or not.

**Mark** is used to represent that a Vocabulary has been learnt.<br>
**Unmark** is used to represent that a Vocabulary has been forgotten and needs to be relearnt.

#### Commands:

-`mark {vocabulary_index}`<br>
-`unmark {vocabulary_index}

Examples:

-`mark 1`<br>
-`unmark 1`


### Delete a Vocabulary
Allows the user to delete a Vocabulary by index.

#### Commands:

-`delete {vocabulary_index}`

Examples:

-`delete 1`


### Find a Vocabulary
Allows the user to find a Vocabulary by index.

#### Commands:

-`find {vocabulary_index}`

Examples:

-`find 1`


### Store changes and terminate application
Store all changes into a txt file and terminate the application.

#### Commands:

-`bye`
