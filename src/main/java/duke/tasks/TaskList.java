package duke.tasks;

import java.util.ArrayList;

public class TaskList<E> extends ArrayList<E> {
    private boolean listHasChanged;

    public TaskList() {
        super();
        this.listHasChanged = false;
    }

    @Override
    public boolean add(E element) {
        this.listHasChanged = true;
        return super.add(element);
    }

    @Override
    public boolean remove(Object o) {
        this.listHasChanged = true;
        return super.remove(o);
    }

    @Override
    public E remove(int index) {
        this.listHasChanged = true;
        return super.remove(index);
    }

    public boolean getListHasChanged() {
        return this.listHasChanged;
    }

    public void setListHasChanged(boolean changed) {
        this.listHasChanged = changed;
    }
}
