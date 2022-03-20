package controller;

import exception.DukeException;
import exception.InvalidIndexException;

public class Helper {
    public static void checkIndex(String idxInString, TaskList tasks) throws DukeException {
        if(idxInString.isEmpty()){
            throw new InvalidIndexException();
        }
        int idx = 0;

        try {
            idx = Integer.parseInt(idxInString)-1;
        }catch (NumberFormatException e){
            throw new InvalidIndexException();
        }

        if(idx >= tasks.getCount() || idx < 0){
            throw new InvalidIndexException();
        }
    }
}
