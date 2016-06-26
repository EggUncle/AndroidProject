package uncle.egg.getvcf;

import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * Created by egguncle on 16.6.19.
 */
public class FileUnit {
    File mSDFile;
    String filePath;
    String fileStr;

    public FileUnit() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)) {
            //  Toast.makeText(MainActivity.this , "沒有SD卡!!!" , Toast.LENGTH_SHORT ).show();
            //  return null;
        } else {
            //取得SD卡儲存路徑
            mSDFile = Environment.getExternalStorageDirectory();
        }
        filePath = mSDFile.getParent() + "/" + mSDFile.getName() + "/MyAndroid";
        //建立文件檔儲存路徑
        File mFile = new File(filePath);

        //若沒有檔案儲存路徑時則建立此檔案路徑
        if (!mFile.exists()) {
            mFile.mkdirs();
        }

        fileStr = mSDFile.getParent() + "/" + mSDFile.getName() + "/MyAndroid/Pubby.txt";
    }

    public void WirteFile(String str) {
        try {

            //取得mEdit文字並儲存寫入至SD卡文件裡
            FileWriter mFileWriter = new FileWriter(fileStr,true);

            mFileWriter.write(str);
            mFileWriter.close();
            //    Toast.makeText(MainActivity.this, "已儲存文字", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
        }
    }

}



