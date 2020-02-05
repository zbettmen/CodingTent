package se.ecutb.data;

import org.springframework.stereotype.Component;

@Component

public class idIMPL implements IdSequencers {
    int counter = 0;
    int todo = 0;
    @Override
    public int nextPersonId() {
        counter = 0;
        return ++counter;
    }

    @Override
    public int nextTodoId() {
        todo = 0;
        return ++todo;
    }

    @Override
    public void clearPersonId() {
        counter = 0;

    }

    @Override
    public void clearTodoId() {
        todo = 0;
    }
}
