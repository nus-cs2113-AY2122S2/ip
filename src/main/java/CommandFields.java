import java.util.Map;
import java.util.regex.Pattern;

import static java.util.Map.entry;

public class CommandFields {
    public static final Map<String, String> ByeFields= Map.ofEntries(
            entry("regex", "^$"));

    public static final Map<String, String> ListFields= Map.ofEntries(
            entry("regex", ""));

    public static final Map<String, String> MarkFields= Map.ofEntries(
            entry("regex", ""));

    public static final Map<String, String> UnmarkFields= Map.ofEntries(
            entry("regex", ""));

    public static final Map<String, String> TodoFields= Map.ofEntries(
            entry("regex", ""));

    public static final Map<String, String> EventFields= Map.ofEntries(
            entry("regex", ""));

}
