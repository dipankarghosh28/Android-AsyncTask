package com.example.dipankarghosh.threads;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    TextView txtDisplay;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button)findViewById(R.id.Simulate);
        //Button linkage with the button with id - button
        mButton.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.editText);
        txtDisplay = (TextView) findViewById(R.id.txtDisplay);

    }

   /* public void go(View view){
    int numFiles = Integer.parseInt(editText.getText().toString());
    txtDisplay.setText("Starting the simulation");
    for(int i=0;i< numFiles; i++)
    {
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            Log.wtf("MainActivity", e.getMessage());

    }
        txtDisplay.setText("Done processing" + numFiles + " files. ");
    }
    }
    */

    @Override
    public void onClick(View v) {
        int numFiles = Integer.parseInt(editText.getText().toString());

        //txtDisplay.setText("Starting the simulation");
        MyTask myTask = new MyTask();
        myTask.execute(numFiles, 4000);

        /*for(int i=0;i< numFiles; i++)
        {
            try{
                Thread.sleep(3000);
            }
            catch(InterruptedException e){
                Log.wtf("MainActivity", e.getMessage());

            }
            txtDisplay.setText((i+1) + " files processed. ");

           // txtDisplay.setText("Done processing " + numFiles + "  files. ");
        }
        */
    }

    private class MyTask extends AsyncTask<Integer, Integer, String>
    {
        @Override
        protected void onPreExecute() {
            txtDisplay.setText("Started the simulation");
        }

        @Override
        protected String doInBackground(Integer... integers) {
            for(int i=0;i< integers[0]; i++)
            {
                try{
                    Thread.sleep(integers[1]); //second parameter is being accessed at this point
                    publishProgress(i + 1);

                }
                catch(InterruptedException e){
                    Log.wtf("MainActivity", e.getMessage());
                    //Any exception is being caught here
                }

                // txtDisplay.setText("Done processing " + numFiles + "  files. ");
            }
            return "Done processing " + integers[0] + "  files. ";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            txtDisplay.setText(values[0] + " Files Processed. ");
        }

        @Override
        protected void onPostExecute(String message) {
            txtDisplay.setText(message);
        }
    }
}
