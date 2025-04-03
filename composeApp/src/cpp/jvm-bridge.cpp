#include "include/math-util.hpp"
#include <jni.h>
#include <string>

using std::string;

extern "C" JNIEXPORT jstring JNICALL
Java_com_payam1991gr_kmp_playground_data_Native_primeFactors(JNIEnv *env, jobject, jstring n) {
    const char *chars = env->GetStringUTFChars(n, nullptr);
    string text = string(chars, env->GetStringUTFLength(n));
    env->ReleaseStringUTFChars(n, chars);
    return env->NewStringUTF(MathUtil::primeFactors(text).c_str());
}
