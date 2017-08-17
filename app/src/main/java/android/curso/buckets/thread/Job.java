package android.curso.buckets.thread;

import android.util.Log;

/**
 * Created by rulosan on 8/17/17.
 */

public class Job implements JobInterface {

    private String jobName;
    private BucketCallback callback;

    public Job(String jobName, BucketCallback callback)
    {
        this.jobName = jobName;
        this.callback = callback;
    }



    @Override
    public void insertDatabase() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.callback.onSuccess(String.format("Termine %s", jobName));
    }
}
