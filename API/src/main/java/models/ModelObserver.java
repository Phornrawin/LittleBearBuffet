package models;

public interface ModelObserver<T> {
    void onModelChange(T model);
}
