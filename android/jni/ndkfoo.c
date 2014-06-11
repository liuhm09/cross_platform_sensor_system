#include <string.h>
#include <jni.h>
#include <time.h>

jstring Java_com_geekerchina_feeltheworld_MainActivity_invokeNativeFunction(JNIEnv* env, jobject javaThis) {
	char tmp[64];
	time_t t = time(0);
	strftime( tmp, sizeof(tmp), "%Y/%m/%d %X %A NO.%j th DayOfYear %z",localtime(&t));
	return (*env)->NewStringUTF(env, tmp);//"Hello from native code!");
}
