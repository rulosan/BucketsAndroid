package android.curso.buckets;

import android.app.Activity;
import android.curso.buckets.thread.Bucket;
import android.curso.buckets.thread.BucketCallback;
import android.curso.buckets.thread.BucketManager;
import android.curso.buckets.thread.Job;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;
import android.os.Handler;


public class BucketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket);

        Button buttonStart = (Button) this.findViewById(R.id.button_start);
        Button buttonStop = (Button) this.findViewById(R.id.button_stop);
        Button sendJob = (Button) this.findViewById(R.id.send_job);

        final Handler h = new Handler(getApplicationContext().getMainLooper());

        final Job myJob = new Job(UUID.randomUUID().toString(), new BucketCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("FINALICE", String.format("Finalice: %s" , result));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Termine",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(String error) {

            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BucketManager.getInstance().start(h);
            }
        });

        sendJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BucketManager.getInstance().send(myJob);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BucketManager.getInstance().stop();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //BucketManager.getInstance().stop();
    }

    public void onPause(){
        super.onPause();
        BucketManager.getInstance().stop();
    }

    public void onRestart()
    {
        super.onRestart();
        BucketManager.getInstance().start();
    }
}
