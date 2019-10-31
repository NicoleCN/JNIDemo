package cn.shanghai.jnidemo;

/***
 * @date 2019-10-31 09:57
 * @author BoXun.Zhao
 * @description
 */
public class GetTextHelper {
    static {
        System.loadLibrary("native-lib");
    }

    private onTextErrorListener onTextErrorListener;

    public interface onTextErrorListener {
        void onError(int code, String msg);
    }

    public void setOnTextErrorListener(GetTextHelper.onTextErrorListener onTextErrorListener) {
        this.onTextErrorListener = onTextErrorListener;
    }

    /**
     * call from jni
     *
     * @param code 0正确  1有误
     * @param msg  error msg
     */
    public void onError(int code, String msg) {
        if (onTextErrorListener != null) {
            onTextErrorListener.onError(code, msg);
        }
    }

    public native void judgeStrLenWithException(String str) throws Exception;

    public native void judgeStrLenWithCallback(String str);
}
