package cl.accedo.accedotest;

import java.util.ArrayList;
import java.util.List;

import cl.accedo.accedotest.adapter.RankingAdapter;
import cl.accedo.accedotest.database.DataBaseClass;
import cl.accedo.accedotest.models.RankingModel;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

public class RankingActivity extends ActionBarActivity {
	
	private AsyncTask<Void, Void, List<RankingModel>> dbTask;
	private DataBaseClass dbInstance;
	private ProgressBar progressBar;
	private ListView listView;
	
	private List<RankingModel> rankingList;
	
	private String name;
	private int score;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		listView = (ListView) findViewById(R.id.rankingList);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		name = (String) getIntent().getStringExtra("name");
		score = (int) getIntent().getIntExtra("score", 0);
		
		dbInstance = new DataBaseClass(this);
		rankingList = new ArrayList<RankingModel>();
		
		dbTask = new AsyncTask<Void, Void, List<RankingModel>>(){
			
			@Override
			protected void onPreExecute(){
				if(progressBar.getVisibility() == View.GONE)
					progressBar.setVisibility(View.VISIBLE);
			}

			@Override
			protected List<RankingModel> doInBackground(Void... params) {
				return saveToDataBaseAndRetrieveData(name, score);
			}
			
			@Override
		    protected void onPostExecute(List<RankingModel> result) {
				if(progressBar.getVisibility() == View.VISIBLE)
					progressBar.setVisibility(View.GONE);
				RankingAdapter adapter = new RankingAdapter(getApplicationContext(), 
															R.id.title, 
															result);
				listView.setAdapter(adapter);
		    }
			
		};
		
		dbTask.execute();
	}
	
	private List<RankingModel> saveToDataBaseAndRetrieveData(String name, int score){
		SQLiteDatabase db = dbInstance.getWritableDatabase();
		if(db != null){
			if(name != null){
				db.beginTransaction();
				try{
					db.execSQL("INSERT INTO ranking (name, score) " +
													"VALUES ('" + name + "', '" + 
																  score + "')");
				} finally {
					db.setTransactionSuccessful();
				}
				db.endTransaction();
			}
			
			Cursor c = db.rawQuery("SELECT * FROM ranking ORDER BY score DESC", null);
			if(c.moveToFirst()){
				do {
					RankingModel model = new RankingModel();
					model.setName(c.getString(1));
					model.setScore(c.getInt(2));
					
					rankingList.add(model);
				} while (c.moveToNext());
			}
			c.close();
			
		    db.close();
		}
		
		return rankingList;
	}

}
