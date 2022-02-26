# Solana

Solana is a Personal Assistant Chatbot that helps manage tasks.

This project was done as part of NUS CS2113T AY21/22 S2.

## Setting Up in IntelliJ

Prerequisites: JDK 11

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
   <br/>In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/solana/Solana.java` file, right-click it, and choose `Run Solana.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
```
  __|   _ \  |       \     \ |    \
\__ \  (   | |      _ \   .  |   _ \
____/ \___/ ____| _/  _\ _|\_| _/  _\

Hi, I'm Solana
What can I do for you?

>
 ```
