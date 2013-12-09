package cl.accedo.accedotest.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.accedo.accedotest.R;
import cl.accedo.accedotest.models.CardModel;

public class Utils {
	
	public static final int RANKING_RESULT = 1;
	
	public static List<CardModel> createCards(){
		List<CardModel> cardList = new ArrayList<CardModel>();
		int maxSize = 16;
		for(int i = 0; i < maxSize; i++){
			CardModel model = new CardModel();
			model.setDefBg(R.drawable.card_bg);
			model.setSelected(false);
			model.setWinned(false);
			model.setCard(getCardDrawable(i));
			cardList.add(model);
		}
		Collections.shuffle(cardList);
		return cardList;
	}
	
	private static int getCardDrawable(int value){
		if(value == 0 || value == 1)
			return R.drawable.colour1;
		else if(value == 2 || value == 3)
			return R.drawable.colour2;
		else if(value == 4 || value == 5)
			return R.drawable.colour3;
		else if(value == 6 || value == 7)
			return R.drawable.colour4;
		else if(value == 8 || value == 9)
			return R.drawable.colour5;
		else if(value == 10 || value == 11)
			return R.drawable.colour6;
		else if(value == 12 || value == 13)
			return R.drawable.colour7;
		else if(value == 14 || value == 15)
			return R.drawable.colour8;
		
		return R.drawable.card_bg;
	}
	
	public static boolean isEmptyOrNull(String var){
		if(var == null || var.equals(""))
			return true;
		else
			return false;
	}

}
