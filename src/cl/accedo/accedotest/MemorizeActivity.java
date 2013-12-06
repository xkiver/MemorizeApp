package cl.accedo.accedotest;

import java.util.List;

import cl.accedo.accedotest.adapter.GridAdapter;
import cl.accedo.accedotest.interfaces.CardSelectedListener;
import cl.accedo.accedotest.interfaces.DialogListener;
import cl.accedo.accedotest.models.CardModel;
import cl.accedo.accedotest.utils.MessageFactory;
import cl.accedo.accedotest.utils.Utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MemorizeActivity extends ActionBarActivity implements CardSelectedListener {
	
	private GridView gridView;
	private TextView scoreText;
	private List<CardModel> cards;
	private int counter = 0;
	private int lastPosition = 0;
	private int pairsCounter = 0;
	private int score = 0;
	private CardModel lastCard;
	private GridAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memorize);
		
		createVariables();
		
		setGridData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.memorize, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.scoreButton:
	        	Intent intent = new Intent(MemorizeActivity.this, RankingActivity.class);
				startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void createVariables(){
		gridView = (GridView) findViewById(R.id.cardsGrid);
		scoreText = (TextView) findViewById(R.id.scoreText);
	}
	
	private void setGridData(){
		cards = Utils.createCards();
		
		adapter = new GridAdapter(getApplicationContext(), 
											  R.id.scoreText, 
											  cards,
											  this);
		gridView.setAdapter(adapter);
		
		String scoreString = String.format(getString(R.string.score_title), 
									 	   String.valueOf(score));
		
		scoreText.setText(scoreString);
	}

	@Override
	public void onCardSelected(CardModel card, int position) {
		if(++counter == 2 && lastCard != null){
			if(lastCard.getCard() == card.getCard()){
				if(++pairsCounter == 8){
					Dialog dialog = MessageFactory.createDialog(MemorizeActivity.this, new DialogListener() {
						
						@Override
						public void onCancelPressed(DialogInterface dialog) {
							dialog.dismiss();
							setGridData();
						}
						
						@Override
						public void onAcceptPressed(DialogInterface dialog, String name) {
							dialog.dismiss();
							Intent intent = new Intent(MemorizeActivity.this, RankingActivity.class);
							intent.putExtra("name", name);
							intent.putExtra("score", score);
							startActivity(intent);
						}
					});
					dialog.show();
				} else {
					Toast.makeText(getApplicationContext(), 
								   getString(R.string.winned_text), 
								   Toast.LENGTH_SHORT).show();
				}
				score = score + 2;
				counter = 0;
				lastCard = null;
				
				String scoreString = String.format(getString(R.string.score_title), 
									 	   		   String.valueOf(score));
				
				scoreText.setText(scoreString);
			} else {
				scoreText.setText(getString(R.string.bad_text));
				executePostDelayed(card, position);
			}
		} else {
			lastCard = card;
			lastPosition = position;
		}
	}
	
	private void executePostDelayed(final CardModel card, final int position){
		Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				lastCard.setSelected(false);
				card.setSelected(false);
				
				cards.set(lastPosition, lastCard);
				cards.set(position, card);
				
				adapter.notifyDataSetChanged();
				
				counter = 0;
				lastCard = null;
				
				String scoreString = String.format(getString(R.string.score_title), 
							 	   		   		   String.valueOf(--score));
				
				scoreText.setText(scoreString);
			}
		}, 500);
	}

}
