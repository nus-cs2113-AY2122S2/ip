import Managers.BaoUI;

public class Main {
    public static void main(String[] args){
        BaoUI bao = new BaoUI("./data", "./data/baoTaskList.txt");
        bao.serveUser();
    }
}
