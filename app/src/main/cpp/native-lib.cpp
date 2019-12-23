#include <jni.h>
#include <string>

extern "C" JNIEXPORT jlong JNICALL
Java_id_ac_ui_cs_mobileprogramming_syahrul_1findi_1ardiansyah_wacana_ui_shoppingCart_ShoppingCartViewModel_calculateTotalPrice(
        JNIEnv *env, jobject, jlongArray arr) {

    jlong sum = 0;

    jsize len = env->GetArrayLength(arr);
    jlong *c_array = env->GetLongArrayElements(arr, nullptr);

    for (jint i = 0; i < len; ++i) {
        sum += c_array[i];
    }

    return sum;
}
