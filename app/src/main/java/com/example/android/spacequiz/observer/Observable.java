package com.example.android.spacequiz.observer;

/**
 * @author Alessandro
 */
public interface Observable {
    void addObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObserver();
}
