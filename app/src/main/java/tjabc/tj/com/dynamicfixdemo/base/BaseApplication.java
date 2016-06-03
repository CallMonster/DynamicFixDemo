package tjabc.tj.com.dynamicfixdemo.base;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;

/**
 * Created by Lee on 2016/5/19.
 */
public class BaseApplication extends Application {
    private static final String TAG = "andfix";

    private static final String APATCH_PATH = "/out.apatch";
    /**
     * patch manager
     */
    private PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mPatchManager = new PatchManager(this);
        mPatchManager.init("1.0");
        Log.d(TAG, "inited.");

        // load patch
        mPatchManager.loadPatch();
        Log.d(TAG, "apatch loaded.");

        // add patch at runtime
        try {
//            String patchFileString = Environment.getExternalStorageDirectory()
//                    .getAbsolutePath() + APATCH_PATH;

            String patchFileString="/sdcard/out.apatch";

            Log.i(TAG,"路径："+patchFileString);
            mPatchManager.addPatch(patchFileString);
            Log.d(TAG, "apatch:" + patchFileString + " added.");
        } catch (IOException e) {
            Log.e(TAG, "io:"+e);
        }

    }
}
