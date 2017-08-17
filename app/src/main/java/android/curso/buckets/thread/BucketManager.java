package android.curso.buckets.thread;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

/**
 * Created by rulosan on 8/16/17.
 */

public class BucketManager {

    private static BucketManager _instance;

    private boolean isStarted = false;
    private Bucket [] threads;
    private static int BUCKET_SIZE = 5;

    public static BucketManager getInstance()
    {
        Object object = new Object();
        synchronized (object)
        {
            if(_instance == null)
                _instance = new BucketManager();
        }
        return  _instance;
    }

    private BucketManager()
    {
        this.threads = new Bucket[BUCKET_SIZE];
        this.isStarted = false;
        this.start();

    }

    public void start()
    {
        if(this.isStarted)
        {
            Log.d("BUCKET","YA esta iniciada");
            return;
        }

        for(int i=0 ; i < BUCKET_SIZE; i++)
        {
            String name = String.format("bucket_%s",String.valueOf(i));
            Log.d("BUCKET",name);
            this.threads[i] = new Bucket();
            this.threads[i].setName(name);
            this.threads[i].start();
        }
        this.isStarted = true;
    }

    public void start(Handler handler)
    {
        if(this.isStarted)
        {
            Log.d("BUCKET","YA esta iniciada");
            return;
        }

        for(int i=0 ; i < BUCKET_SIZE; i++)
        {
            String name = String.format("bucket_%s",String.valueOf(i));
            Log.d("BUCKET",name);
            this.threads[i] = new Bucket();
            handler.postDelayed(this.threads[i],2000);
            this.threads[i].setName(name);
            this.threads[i].start();
        }
        this.isStarted = true;
    }

    public void stop()
    {
        if(!isStarted) {
            Log.d("BUCKET","Ya esta parada");
            return;
        }
        for (int i = 0; i < BUCKET_SIZE; i++) {
            Log.d("BUCKET_MANAGER", String.format("Matando %s", this.threads[i].getName()));
            this.threads[i].finish();
        }
        this.isStarted =false;
    }

    public void send(JobInterface job)
    {
        int assignedIndex = Math.abs(job.hashCode()) % BUCKET_SIZE;
        Log.d("BUCKET_MANAGER", String.format("Index = %s",assignedIndex));
        this.threads[assignedIndex].addingJob(job);
    }
}
