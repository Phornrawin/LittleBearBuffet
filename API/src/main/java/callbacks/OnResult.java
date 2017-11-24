package callbacks;

public interface OnResult<T> {
    void onComplete(T obj);
    void onFailure(T obj);
}
