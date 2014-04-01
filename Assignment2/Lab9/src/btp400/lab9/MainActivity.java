package btp400.lab9;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
	private EditText inputRadius;
	private Button sendButton;
	private TextView displayResult;
	private String radiusString;
	private Socket clientSocket;
	private DataOutputStream dosToServer;
	private DataInputStream  disFromServer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        // 1.a get the references to the Android UI components (i.e. views)
        inputRadius = (EditText) findViewById(R.id.inputRadius);
        sendButton = (Button) findViewById(R.id.sendButton);
        displayResult = (TextView) findViewById(R.id.displayResult);
		
         // 1.b execute an AsyncTask object to open a socket connection
         new OpenConnectionTask().execute();
		
         // 1.c event handling code for the button here.
         sendButton.setOnClickListener( new View.OnClickListener() {
			
		@Override
		public void onClick(View v) {
				
		   radiusString = inputRadius.getText().toString();
				
		   // run AsyncTask to do network connection and 
                   // data communication
		   new HandleConnectionTask().execute();	
		}// end method
	  } );
	}
	//-----------------------------------------------------------------------------------------------------
	
	// a private inner class
    private class OpenConnectionTask extends AsyncTask <Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
		
		   try {
			// IPC address for running localhost on the emulator:
              //             "10.0.2.2"
		       clientSocket = 
		    	  new Socket( InetAddress.getByName("localhost"), 8000 );
		       
		       dosToServer = new DataOutputStream(
                       clientSocket.getOutputStream() );
		       disFromServer= new DataInputStream(
                       clientSocket.getInputStream() );
		   }
		   catch( Exception ex1 ){ }
		   
		   return null;
		}
    		
    }// end class
    
    // a private inner class: privilege granted to access 
    // private instance variables in the outer class
    private class HandleConnectionTask extends AsyncTask <Void, Void, Double> {

		@Override
		protected Double doInBackground( Void... arg0 ) {
			
			   double area =0.0;
				 
		            try {
			          if (radiusString == null) 
			              radiusString = "0";
			             
			          double radius = 
                                          Double.parseDouble( radiusString );
			             
			             dosToServer.writeDouble(radius);
			             dosToServer.flush();
			             
			            area = disFromServer.readDouble();
			  
				     }
				     catch( EOFException eof ) {}
				     catch(IOException e ) { } 
                                     
 		/* DO NOT CLOSE  the streams and the socket connection here! 
                   Reason: The event handler will be invoked many times! 
	           - cf the onStop() method */
				    
				
		return Double.valueOf( area );	
				
              } // end doInBackground
		
		// the postExecute method
		@Override
		protected void onPostExecute(Double result){
		
                        // Android TextView object
			displayResult.setText( result.toString());
		}

    }// end class
	
	
	//------------------------------------------------------------------------------------------------------

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
