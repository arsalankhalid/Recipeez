package btp400.assignment1client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.database.sqlite.*;
import android.app.ActionBar;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	
	private Socket clientSocket;
	private ObjectOutputStream dosToServer;
	private ObjectInputStream  disFromServer;

    @Override //create layout and iniate main activity 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    //    new OpenConnectionTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
 /*   private class OpenConnectionTask extends AsyncTask<Void, Void, Void>
    {
    	 @Override
    	 protected Void doInBackground(Void... arg0)
    	 {    		 
    		 try {
        		 clientSocket = new Socket(InetAddress.getByName("localhost"), 8000 );
				dosToServer = new ObjectOutputStream(clientSocket.getOutputStream());
				disFromServer = new ObjectInputStream( clientSocket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 
    		 return null;
    	 }
    	
    }    */
    
}
