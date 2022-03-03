package duke.commands;

import duke.exceptions.InvalidCommandException;

public enum CommandType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    FIND("find"),
    DELETE("delete"),
    HELP("help"),
    BYE("bye");

    private String name;

    /**
     * Give each CommnadType enum an associated name
     *
     * @param name Name of command for some enum
     */
    CommandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the proper Enum for the given name
     *
     * @param text the command name
     * @return the Enum representing the full name of the type of task
     */
    public static CommandType fromString(String text) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.getName().equals(text)) {
                return commandType;
            }
        }
        throw new IllegalArgumentException();
    }
}
