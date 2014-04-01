package btp400.assignment1client;

import java.util.Hashtable;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class Searchactivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		setContentView(R.layout.activity_searchactivity);
		
		 Map<String,String> map = new Hashtable<String,String>();
         map.put("bananna", "Bananna Mix");
         map.put("Cereal", "How to create Nesquick Cereal");
         map.put("Apple", "This is an Apple Mix");

         String result ="found nothing!";
 		super.onCreate(savedInstanceState);
         Intent intent = getIntent();
         
         if(Intent.ACTION_SEARCH.equals(intent.getAction()))
         {
        String query = intent.getStringExtra(SearchManager.QUERY);
        Log.i("search","query="+query);
        String str=map.get(query);
        
        	if(str!=null)
        	{
        		result="result: " + query + " is " + str;
        	}
        }
       TextView text = new TextView(this);
       text.setText(result);
       setContentView(text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searchactivity, menu);
		return true;
	}

}
