package android.curso.buckets.thread;

import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by rulosan on 8/16/17.
 */

public class Bucket extends Thread {

    private boolean isRunning;
    private Queue<JobInterface> colaTrabajo;

    public Bucket()
    {
        this.isRunning = true;
        this.colaTrabajo = new LinkedList<JobInterface>();
    }

    @Override
    public void run(){
        while(this.isRunning){
            try {
                Object result = null;
                synchronized (this.colaTrabajo) {
                    if (!this.colaTrabajo.isEmpty()) {
                        JobInterface selectedJob = this.colaTrabajo.poll();
                        this.process(selectedJob);
                    }
                    this.colaTrabajo.wait();
                }
            }
            catch(Exception e)
            {
                Log.d("",e.getMessage());
            }
        }
    }

    public void addingJob(JobInterface job)
    {
        synchronized (this.colaTrabajo) {
            this.colaTrabajo.add(job);
            this.colaTrabajo.notify();
        }
    }

    public void finish()
    {
        this.isRunning = false;
        synchronized (this.colaTrabajo)
        {
            this.colaTrabajo.notify();
        }

    }

    private void process(JobInterface job)
    {
        job.insertDatabase();
    }
}
