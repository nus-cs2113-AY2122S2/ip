# Project Jarvis

Jarvis is a Personal Assistant Chat bot that helps manage tasks. User guide [here](https://glendonnotglen.github.io/ip/)

This project was done as part of CS2113T Individual Project.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/jarvis/Jarvis.java` file, right-click it, and choose `Run Jarvis.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
    _    _      _                          
   | |  | |    | |                         
   | |  | | ___| | ___ ___  _ __ ___   ___
   | |/\| |/ _ \ |/ __/ _ \| '_ ` _ \ / _ \
   \  /\  /  __/ | (_| (_) | | | | | |  __/
   \/  \/ \___|_|\___\___/|_| |_| |_|\___
    _            ___                  _     
   | |          |_  |                (_)    
   | |_ ___       | | __ _ _ ____   ___ ___
   | __/ _ \      | |/ _` | '__\ \ / / / __|
   | || (_) | /\__/ / (_| | |   \ V /| \__ \
   \__\___/  \____/ \__,_|_|    \_/ |_|___/
   
   > 
   ```