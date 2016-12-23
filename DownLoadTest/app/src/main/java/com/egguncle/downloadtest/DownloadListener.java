package com.egguncle.downloadtest;

/**
 * Created by egguncle on 16.12.22.
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
