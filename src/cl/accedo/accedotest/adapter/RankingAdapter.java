package cl.accedo.accedotest.adapter;

import java.util.List;

import cl.accedo.accedotest.R;
import cl.accedo.accedotest.models.RankingModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RankingAdapter extends ArrayAdapter<RankingModel> {
	
	LayoutInflater mInflater;
	int mCount;

	public RankingAdapter(Context context, int textViewResourceId,
			List<RankingModel> objects) {
		super(context, textViewResourceId, objects);
		mInflater = LayoutInflater.from(context);
		mCount = objects.size();
	}
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		TextView rankingText = null;
	    TextView nameText = null;
	    TextView scoreText = null;
		
		final RankingModel model = (RankingModel) getItem(position);
	
		if(convertView == null){
	         convertView = mInflater.inflate(R.layout.ranking_item, null);
	         holder = new ViewHolder(convertView);
	         convertView.setTag(holder);
	    } else {
	    	holder = (ViewHolder) convertView.getTag();
	    }
		
		rankingText = holder.getRankingText();
		nameText = holder.getNameText();
		scoreText = holder.getScoreText();
		
		rankingText.setText(String.valueOf(position + 1));
		nameText.setText(model.getName());
		scoreText.setText(String.valueOf(model.getScore()));
		
		return convertView;
	}
	
	@Override
    public int getCount() {
    	return mCount;
    }
	
	private class ViewHolder {
	    private View mRow;
	    private TextView rankingText = null;
	    private TextView nameText = null;
	    private TextView scoreText = null;
	    
	    public ViewHolder(View row) {
	          mRow = row;
	    }
	    
	    public TextView getRankingText(){
	    	if(rankingText == null){
	    		rankingText = (TextView) mRow.findViewById(R.id.rankingText);
	    	}
	    	return rankingText;
	    }
	    
	    public TextView getNameText(){
	    	if(nameText == null){
	    		nameText = (TextView) mRow.findViewById(R.id.nameText);
	    	}
	    	return nameText;
	    }
	    
	    public TextView getScoreText(){
	    	if(scoreText == null){
	    		scoreText = (TextView) mRow.findViewById(R.id.scoreText);
	    	}
	    	return scoreText;
	    }
	    
	}

}
