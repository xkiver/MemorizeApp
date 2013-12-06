package cl.accedo.accedotest.interfaces;

import android.content.DialogInterface;

public interface DialogListener {
	
	public void onAcceptPressed(DialogInterface dialog, String name);
	
	public void onCancelPressed(DialogInterface dialog);

}
