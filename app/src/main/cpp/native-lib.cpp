#include <jni.h>
#include <string.h>
#include <android/log.h>
#include <string>

#define TAG "native_tag"

using namespace std;


extern "C"
JNIEXPORT void JNICALL
Java_cn_shanghai_jnidemo_GetTextHelper_judgeStrLenWithCallback(JNIEnv *env, jobject instance,
                                                               jstring str_) {
    __android_log_print(ANDROID_LOG_ERROR, TAG, "判断通过Callback");
    jsize stringLength = env->GetStringLength(str_);
    int code = 0;
    string errorMsg;

    __android_log_print(ANDROID_LOG_ERROR, TAG, "strLength%d",stringLength);

    if (stringLength > 4) {
        errorMsg = "不是一般人名";
        code = 1;
    } else {
        errorMsg = "是一般人名";
    }
    jclass clazz = env->GetObjectClass(instance);
    //I-->int
    //Ljava/lang/String-->String;
    //V-->void
    jmethodID methodID = env->GetMethodID(clazz, "onError", "(ILjava/lang/String;)V");
    jstring jErrorMsg = env->NewStringUTF(errorMsg.c_str());
    env->CallVoidMethod(instance, methodID, code, jErrorMsg);
    env->DeleteLocalRef(jErrorMsg);
}


extern "C"
JNIEXPORT void JNICALL
Java_cn_shanghai_jnidemo_GetTextHelper_judgeStrLenWithException(JNIEnv *env, jobject instance,
                                                                jstring str_) {
    __android_log_print(ANDROID_LOG_ERROR, TAG, "判断通过Exception");
    jsize stringLength = env->GetStringLength(str_);
    string errorMsg;
    if (stringLength > 4) {
        errorMsg = "不是一般人名";
//        jclass clazz = env->FindClass("cn/shanghai/jnidemo/NativeException");
        jclass clazz = env->FindClass("java/lang/Exception");
        if (clazz == NULL) {
            __android_log_print(ANDROID_LOG_ERROR, TAG, "找不到NativeException");
        } else {
            env->ThrowNew(clazz, errorMsg.c_str());
        }
    } else {
        errorMsg = "是一般人名";
    }
}