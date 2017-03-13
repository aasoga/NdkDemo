#include <jni.h>
#include <string>

JNIEXPORT jboolean JNICALL
Java_com_example_v_1yanligang_ndkdemo1_AudioPlayer_createStreamingMediaPlayer(JNIEnv *env,
                                                                              jclass type,
                                                                              jobject assetMg,
                                                                              jstring fileName_) {
    const char *fileName = env->GetStringUTFChars(fileName_, 0);

    // TODO

    env->ReleaseStringUTFChars(fileName_, fileName);
}

extern "C" {
#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"
#include "libswscale/swscale.h"
#include "libavutil/imgutils.h"
#include "libavutil/avutil.h"
};
using namespace std;

extern "C"
jstring
Java_com_example_v_1yanligang_ndkdemo1_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    av_register_all();
    return env->NewStringUTF(hello.c_str());
}
