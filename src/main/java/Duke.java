public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Ui ui = new Ui();

        System.out.println(logo);
        System.out.println(ui.drawLine());
        System.out.println(ui.greet());
        System.out.println(ui.drawLine());
        System.out.println(ui.goodbye());
        System.out.println(ui.drawLine());
    }
}
