# DUKE  User Guide
Project DUKE is an iwishbot which help keep tracks of wish task. It is a Chatbot assistant that helps a person to keep track and update various wish task.

## Contents
1. [Features](#Features)
    - [Add Todo Wish Task](#Add-Todo-Wish-Task)
    - [Add Event Wish Task](#Add-Event-Wish-Task)
    - [Add Deadline Wish Task](#Add-Deadline-Wish-Task)
    - [List of Wish Task](#List-of-Wish-Task)
    - [Tick Completed Wish Task](#Tick-Completed-Wish-Task)
    - [UnTick Completed Wish Task](#UnTick-Completed-Wish-Task)
    - [Delete Wish Task](#Delete-Wish-Task)
    - [Find Wish Task](#Find-Wish-Task)
    - [Terminate ChatBot](#Terminate-ChatBot)
2. [Command Summary](#Command-Summary)

## Features
### Add Todo Wish Task
Adds a to do wish to be fulfil.

Format: ```todo: description ```

Examples:
- ```todo: Get a car```
- ```todo: Buy a house```

### Add Event Wish Task
Add an Event wish task  that begin and conclude at a certain time.

Format: ```event: Event description @ Date TimeStart-TimeEnd ```

Examples:
- ```event: Go Macdonald @ Mon 12-2pm```
- ```event: Go Channel @ Tues 7-9pm```

### Add Deadline Wish Task
Add a Deadline wish task that must be completed by a certain date/time.

Format: ```deadline: deadline description ~ Time/Date ```

Examples:
- ```deadline: Submit CS2113 ~ 11:59pm```
- ```deadline: Pay Susan ~ Tues 7-9pm```

### List of Wish Task
Display the type of wishes keyed in and their completion status.

Format: ```list ```

Output:
```
[iWish]:  ** These are your wishes **
1. [T][ ] Get a car
2. [T][ ] Buy a house
3. [E][ ] Go Macdonald (at: Mon 12-2pm)
4. [E][ ] Go Channel (at: Tues 7-9pm)
5. [D][ ] Submit CS2113 (by: 11:59pm)
6. [D][ ] Pay Susan (by: Tues 7-9pm)
We reached the end of the list. Anymore wish?
```

### Tick Completed Wish Task
Tick completed wishes by selecting their number in the list of wish task.

Format: ```tick index number of wish in list ```

Examples:
- ```tick 6```
- ```tick 1```

Output:
```
[iWish]:  ** These are your wishes **
1. [T][X] Get a car
2. [T][ ] Buy a house
3. [E][ ] Go Macdonald (at: Mon 12-2pm)
4. [E][ ] Go Channel (at: Tues 7-9pm)
5. [D][ ] Submit CS2113 (by: 11:59pm)
6. [D][X] Pay Susan (by: Tues 7-9pm)
We reached the end of the list. Anymore wish?
```

### UnTick Completed Wish Task
untick completed wishes by selecting their number in the list of wish task.

Format: ```untick index number of wish in list ```

Examples:
- ```untick 6```
- ```untick 1```

Output:
```
[iWish]:  ** These are your wishes **
1. [T][ ] Get a car
2. [T][ ] Buy a house
3. [E][ ] Go Macdonald (at: Mon 12-2pm)
4. [E][ ] Go Channel (at: Tues 7-9pm)
5. [D][ ] Submit CS2113 (by: 11:59pm)
6. [D][ ] Pay Susan (by: Tues 7-9pm)
We reached the end of the list. Anymore wish?
```
### Delete Wish Task
Removing non-essential wish tasks by selecting their number in the list of wish task.

Format: ```delete index number of wish in list ```

Examples:
- ```delete 1```
- ```delete 5```

### Find Wish Task
Find wish task that are in the list.

Format: ``` find Word to be found```

Examples:
- ```find go```
- ```find eat```

Assume full wish task list consist:
```
[iWish]:  ** These are your wishes **
1. [T][ ] Buy a house
2. [E][ ] Go Macdonald (at: Mon 12-2pm)
3. [E][ ] Go Channel (at: Tues 7-9pm)
4. [D][ ] Submit CS2113 (by: 11:59pm)
5. [D][ ] Pay Susan (by: Tues 7-9pm)
We reached the end of the list. Anymore wish?
```

#### If search is successful:

Input: ``` find go```

Output:
```
[iWish]:  ** These are your wishes **
1. [E][ ] Go Macdonald (at: Mon 12-2pm)
2. [E][ ] Go Channel (at: Tues 7-9pm)
We reached the end of the list. Anymore wish?
```

#### If search is unsuccessful:

Input: ``` find eat```

Output:
```
[iWish]: Opps! There is no search result found in wishlist
```

### Terminate ChatBot
No more wishes to be added or updated. Exits program.

Format: ```bye ```

## Command Summary
| Command | Format, Example |
| --- | --- |
| todo | ```todo: description```<br/>eg. ```todo: Get a car```|
| event | ```event: Event description @ Date TimeStart-TimeEnd```<br/>eg.```event: Go Macdonald @ Mon 12-2pm```|
| deadline | ```deadline: deadline description ~ Time/Date```<br/>eg.```deadline: deadline description ~ Time/Date ```|
| list | ```list``` |
| tick | ```tick index number of wish in list ```<br/>eg.```tick 6``` |
| untick | ```untick index number of wish in list ```<br/>eg.```untick 6``` |
| delete | ```delete index number of wish in list ```<br/>eg. ```delete 1``` |
| find | ``` find Word to be found``` <br/>eg. ``` find go``` |
| bye | ```bye``` |