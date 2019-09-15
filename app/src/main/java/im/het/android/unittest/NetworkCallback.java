package im.het.android.unittest;

public interface NetworkCallback {
    void onSuccess(Object data);
    void onFailure(int code, String msg);
}
