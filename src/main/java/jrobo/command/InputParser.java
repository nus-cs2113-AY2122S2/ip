package jrobo.command;

import jrobo.exception.InvalidFormatException;

public class InputParser {
    protected String[] args;
    protected static String[] validCommands = {"mark", "m", "unmark", "um", "todo", "t", "deadline", "d", "event", "e", "list", "ls", "bye", "exit", "quit", "q", "delete", "del"};

    public InputParser(String input) {
        this.args = input.split(" ");
    }

    public String getPrefix() {
        for (String s : validCommands) {
            String command = args[0];
            if (command.equalsIgnoreCase(s)) {
                return command.toLowerCase();
            }
        }
        return null;
    }

    public String getBody() {
        int suffixIndex = findSuffixIndex();

        StringBuilder description = new StringBuilder();
        int n = suffixIndex == -1 ? args.length : suffixIndex;
        for (int i = 1; i < n; i++) {
            description.append(" ").append(args[i]);
        }
        return description.toString();
    }

    public String getSuffix() {
        int suffixIndex = findSuffixIndex();
        if (suffixIndex == -1) {
            return null;
        }

        StringBuilder detail = new StringBuilder();
        for (int i = suffixIndex + 1; i < args.length; i++) {
            detail.append(" ").append(args[i]);
        }
        return detail.toString();
    }

    public boolean isValidCommand() {
        return !(getPrefix() == null || ((getPrefix().equals("list") || getPrefix().equals("ls"))
                && !getBody().equals("")));
    }

    public String getType() throws InvalidFormatException {
        boolean isEvent = false;
        boolean isDeadline = false;
        for (String s : args) {
            if (s.equals("/at")) {
                isEvent = true;
            }
            if (s.equals("/by")) {
                isDeadline = true;
            }
        }

        if (!isDeadline && !isEvent && (getPrefix().equals("todo") || getPrefix().equals("t"))) {
            return "todo";
        }
        if (isDeadline && (getPrefix().equals("deadline") || getPrefix().equals("d"))) {
            return "deadline";
        }
        if (isEvent && (getPrefix().equals("event") || getPrefix().equals("e"))) {
            return "event";
        }
        throw new InvalidFormatException("Invalid command format!");
    }

    public String[] loadParse(String taskStr) {
        int index = taskStr.lastIndexOf(']');
        String prefix = taskStr.substring(0, index);
        String body;
        String suffix;
        boolean isDeadline = prefix.contains("D");
        boolean isEvent = prefix.contains("E");
        if (isEvent) {
            suffix = taskStr.substring(taskStr.indexOf("at:") + 3, taskStr.length() - 1);
            body = taskStr.substring(index + 1, taskStr.indexOf('(') - 1);
            return new String[] {body, suffix, "event"};
        } else if (isDeadline) {
            suffix = taskStr.substring(taskStr.indexOf("by:") + 3, taskStr.length() - 1);
            body = taskStr.substring(index + 1, taskStr.indexOf('(') - 1);
            return new String[] {body, suffix, "deadline"};
        } else {
            body = taskStr.substring(index + 1);
            return new String[] {body, "", "todo"};
        }
    }

    private int findSuffixIndex() {
        int found = -1;
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (s.equals("/at") || s.equals("/by")) {
                found = i;
                break;
            }
        }
        return found;
    }
}
