# User Guide of Duke

## Outline
* [Outline](#outline)
* [Setup Guide](#setup)
* [Features](#features)
  * [Task Type](#feature-type)
    * [Event](#feature-type-event)
    * [Deadline](#feature-type-deadline)
    * [Todo](#feature-type-todo)
  * [Add](#feature-add)
  * [Delete](#feature-delete)
  * [Mark/Unmark](#feature-mark-unmark)
  * [Search](#feature-search)
  * [List](#feature-list)
* [Tutorial](#tutorial)
  * [Add Event](#tutorial-add-event)
  * [Add Deadline](#tutorial-add-deadline)
  * [Add Todo](#tutorial-add-todo)
  * [Delete Task](#tutorial-delete-task)
  * [Mark/Unmark Task](#tutorial-update-task)
  * [Find Task](#tutorial-find-task)
  * [List Task](#tutorial-list-task)
* [Command Summary](#command)

##Introduction
##Setup Guide
Prerequisite: JDK 11
1. Please ensure that JDK 11 is installed before running Duke. You can check it in terminal by typing 
   ````shell
   java -v
   ````
   and you should see something similar as following:
   ````shell
   openjdk version "11.0.13" 2021-10-19 LTS
   OpenJDK Runtime Environment Corretto-11.0.13.8.1 (build 11.0.13+8-LTS)
   OpenJDK 64-Bit Server VM Corretto-11.0.13.8.1 (build 11.0.13+8-LTS, mixed mode)
   ````
2. Download the latest release. You can use command to download it or simply download it from the website:
   ````
   https://github.com/hlwang56/ip/releases
   ````
3. Open a terminal in the folder when the jar file is placed and run following command
   ````shell
   java -jar ip.jar
   ````
##Features
##Tutorial
##Command Summary

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
