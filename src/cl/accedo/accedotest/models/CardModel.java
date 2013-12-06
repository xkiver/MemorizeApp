package cl.accedo.accedotest.models;

public class CardModel {
	
	private int def_bg;
	private int card;
	private boolean selected;
	private boolean winned;
	
	public int getDefBg() {
		return def_bg;
	}
	
	public void setDefBg(int def_bg) {
		this.def_bg = def_bg;
	}
	
	public int getCard() {
		return card;
	}
	
	public void setCard(int card) {
		this.card = card;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isWinned() {
		return winned;
	}

	public void setWinned(boolean winned) {
		this.winned = winned;
	}

}
