package cn.airportweather;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

public class AddLocDialogFrag extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the builder class for dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // inflate and set the layout
        builder.setView(inflater.inflate(R.layout.dialog_latlon, null))
                // add button functionality
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // send the positive button event back to the host activity
                        listener.onDialogPositiveClick(AddLocDialogFrag.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // send the negative button event back to the host activity
                        listener.onDialogNegativeClick(AddLocDialogFrag.this);
                    }
                });

        return builder.create();
    }

    // interface to handle dialog callbacks
    public interface AddLocDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // create instance of listener to send events
    AddLocDialogListener listener;

    // override method to instantiate the listener
    @Override
    public void onAttach(Activity activity){
        // call the original function
        super.onAttach(activity);

        // verify that the host activity implements the callback interface
        try{
            // instantiate the listener for event handling
            listener = (AddLocDialogListener)activity;
        } catch(ClassCastException e){
            // the activity doesn't implement the interface
            throw new ClassCastException(activity.toString() +
                    " must implement AddLocDialogListener");
        }
    }
}