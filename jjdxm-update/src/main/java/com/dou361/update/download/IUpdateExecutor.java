package com.dou361.update.download;

/**
 */
public interface IUpdateExecutor {
    /**
     * check if is new version exist;
     */
    void check(UpdateWorker worker);
    void checkNoUrl(UpdateNoUrlWorker worker);
}
