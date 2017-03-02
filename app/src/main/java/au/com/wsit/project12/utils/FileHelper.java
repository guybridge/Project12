package au.com.wsit.project12.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by guyb on 2/03/17.
 */

public class FileHelper
{
    private static final String TAG = FileHelper.class.getSimpleName();
    private Context context;
    public static final int SHORT_SIDE_TARGET = 256;

    public interface SaveFileCallback
    {
        void onSuccess(Uri uri);
        void onFail(String errorMessage);
    }

    public FileHelper(Context context)
    {
        this.context = context;
    }

    public File saveFile(Bitmap bitmap, String fileName)
    {
        try {
            File file = new File(context.getExternalCacheDir(), fileName + ".jpg");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            Log.i(TAG, "Saving file to: " + file.getAbsoluteFile().toString());
            return file;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }


}
