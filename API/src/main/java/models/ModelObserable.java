package models;

public interface ModelObserable<T> {

    void regisObserver(ModelObserver<T> observer);
    void removeObserver(ModelObserver<T> observer);
}
