package controllers;

public interface FirebaseObserable {
    void regisObserver(FirebaseObserver observer);
    void removeObserver(FirebaseObserver observer);
}
