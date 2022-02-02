public class ToDo extends Task{

    public ToDo(String name){
        super(name);
    }

    @Override
    public String getListName(){
        return "[T]" + super.getListName();
    }
}
