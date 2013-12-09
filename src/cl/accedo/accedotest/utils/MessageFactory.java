package cl.accedo.accedotest.utils;

import cl.accedo.accedotest.R;
import cl.accedo.accedotest.interfaces.DialogListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.os.Build;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MessageFactory {
	
	@SuppressLint({ "NewApi", "InlinedApi" })
	public static Dialog createDialog(final Context context, 
												final DialogListener listener){
		AlertDialog.Builder builder;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			builder = new AlertDialog.Builder(new ContextThemeWrapper(context, android.R.style.Theme_Holo_Dialog));
		} else {
			builder = new AlertDialog.Builder(context);
		}
		
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View v = inflater.inflate(R.layout.dialog, null);
		
		final EditText nameEdit = (EditText) v.findViewById(R.id.username);
		
		builder.setView(v);
	    builder.setTitle(context.getString(R.string.dialog_title));
	    builder.setPositiveButton(context.getString(R.string.accept_button), null);
	    builder.setNegativeButton(context.getString(R.string.cancel_button), null);
	    
	    builder.setCancelable(false);
	    
	    final AlertDialog dialogMessage = builder.create();
	    
	    dialogMessage.setOnShowListener(new OnShowListener() {
			
			@Override
			public void onShow(final DialogInterface dialog) {
				Button positive = dialogMessage.getButton(AlertDialog.BUTTON_POSITIVE);
				positive.setOnClickListener(new View.OnClickListener() {

		            @Override
		            public void onClick(View view) {
		            	String name = nameEdit.getText().toString();
		            	listener.onAcceptPressed(dialog, name);
		            }
		        });
		        
		        Button negative = dialogMessage.getButton(AlertDialog.BUTTON_NEGATIVE);
		        negative.setOnClickListener(new View.OnClickListener() {

		            @Override
		            public void onClick(View view) {
		            	listener.onCancelPressed(dialog);
		            }
		        });
			}
		});
	    
	    return dialogMessage;
	}

}
