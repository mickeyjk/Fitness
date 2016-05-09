package orozdevelopment.fitness.weight_tracking;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import orozdevelopment.fitness.R;


/**
 * Created by michael on 5/7/16.
 */
public class AddWeightDialogFragment extends DialogFragment {

    public interface AddWeightDialogListener{
        public void onDialogPositiveClick(AddWeightDialogFragment dialog);
        public void onDialogNegativeClick(AddWeightDialogFragment dialog);
    }

    AddWeightDialogListener mListener;
    EditText weightInput;

    public AddWeightDialogFragment(){
        //empty
    }

    public static AddWeightDialogFragment newInstance(AddWeightDialogListener listener){
        AddWeightDialogFragment fragment = new AddWeightDialogFragment();
        fragment.mListener = listener;
        return fragment;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.weight_tracking_add_dialog, null);
        this.weightInput = (EditText)v.findViewById(R.id.add_weight_edit_text);

        this.weightInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                }
            }
        });

        builder.setView(v)
                .setTitle(R.string.add_weight_title)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().getWindow().setSoftInputMode(
                                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        mListener.onDialogNegativeClick(AddWeightDialogFragment.this);

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().getWindow().setSoftInputMode(
                                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        mListener.onDialogPositiveClick(AddWeightDialogFragment.this);
                    }
                });

        return builder.create();
    }

}
