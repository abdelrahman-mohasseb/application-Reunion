package com.openclassrooms.application_reunion.UI.Fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.openclassrooms.application_reunion.DI.DI;
import com.openclassrooms.application_reunion.R;
import com.openclassrooms.application_reunion.databinding.FragmentAddReunionBinding;
import com.openclassrooms.application_reunion.databinding.FragmentFilterReunionBinding;
import com.openclassrooms.application_reunion.model.Reunion;
import com.openclassrooms.application_reunion.service.ReunionApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class FilterReunionFragment extends DialogFragment{

    private ReunionApiService mApiService;
    private FragmentFilterReunionBinding fragmentFilterReunionBinding;
    public interface FilterReunionDialogListener {
        public void onFilterDialogPositiveClick(DialogFragment dialog);
    }
    FilterReunionFragment.FilterReunionDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            listener = (FilterReunionFragment.FilterReunionDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement FilterReunionDialogListener");
        }
    }

    /** The system calls this only when creating the layout in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        mApiService = DI.getReunionApiService();
        fragmentFilterReunionBinding = FragmentFilterReunionBinding.inflate( inflater);
        fragmentFilterReunionBinding.checkboxDate.setOnClickListener(this::onCheckboxClicked);
        fragmentFilterReunionBinding.checkboxLieu.setOnClickListener(this::onCheckboxClicked);
        fragmentFilterReunionBinding.checkboxSujet.setOnClickListener(this::onCheckboxClicked);
        fragmentFilterReunionBinding.tilDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDialog();
            }
        });
        builder.setView(fragmentFilterReunionBinding.getRoot())
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                .setTitle("Filtrer la liste d'évènement par :")
                // Add action buttons
                .setPositiveButton("Filter la liste", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String date = "" ;
                        String lieu = "";
                        String sujet = "";
                        List<Reunion> mReunions = new ArrayList<>();
                        if(fragmentFilterReunionBinding.checkboxDate.isChecked()) {
                             date = fragmentFilterReunionBinding.tilDate.getEditText().getText().toString().trim().toLowerCase();
                        }
                        if(fragmentFilterReunionBinding.checkboxLieu.isChecked()) {
                             lieu = fragmentFilterReunionBinding.tilLieu.getEditText().getText().toString().trim().toLowerCase();
                        }
                        if(fragmentFilterReunionBinding.checkboxSujet.isChecked()) {
                             sujet = fragmentFilterReunionBinding.tilSujet.getEditText().getText().toString().trim().toLowerCase();
                        }
                        mReunions= mApiService.getReunions();
                        mApiService.deleteAllFromFilteredlist();
                        for (Reunion reunion: mReunions) {

                            if (date !="" && reunion.getHeure().contains(date)) {
                                mApiService.addToFilteredlist(reunion);
                            }
                            if (lieu !="" && reunion.getLieu().contains(lieu)) {
                                mApiService.addToFilteredlist(reunion);
                            }
                            if (sujet !="" && reunion.getSujet().contains(sujet)) {
                                mApiService.addToFilteredlist(reunion);
                            }
                        }
                        listener.onFilterDialogPositiveClick(FilterReunionFragment.this);
                    }
                })
                .setNegativeButton("Abondonner", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FilterReunionFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
    public void onCheckboxClicked(View v) {
        boolean checked = ((CheckBox) v).isChecked();
        switch(((CheckBox) v).getId()) {
            case R.id.checkbox_date:
                if (checked) {
                    fragmentFilterReunionBinding.tilDate.setVisibility(View.VISIBLE);
                    fragmentFilterReunionBinding.checkboxLieu.setVisibility(View.GONE);
                    fragmentFilterReunionBinding.checkboxSujet.setVisibility(View.GONE);

                }
                else {
                    fragmentFilterReunionBinding.tilDate.setVisibility(View.GONE);
                    fragmentFilterReunionBinding.checkboxLieu.setVisibility(View.VISIBLE);
                    fragmentFilterReunionBinding.checkboxSujet.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.checkbox_Lieu:
                if (checked) {
                    fragmentFilterReunionBinding.tilLieu.setVisibility(View.VISIBLE);
                    fragmentFilterReunionBinding.checkboxDate.setVisibility(View.GONE);
                    fragmentFilterReunionBinding.checkboxSujet.setVisibility(View.GONE);
                }
                else {
                    fragmentFilterReunionBinding.tilLieu.setVisibility(View.GONE);
                    fragmentFilterReunionBinding.checkboxDate.setVisibility(View.VISIBLE);
                    fragmentFilterReunionBinding.checkboxSujet.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.checkbox_sujet:
                if (checked) {
                    fragmentFilterReunionBinding.tilSujet.setVisibility(View.VISIBLE);
                    fragmentFilterReunionBinding.checkboxLieu.setVisibility(View.GONE);
                    fragmentFilterReunionBinding.checkboxDate.setVisibility(View.GONE);
                }
                else {
                    fragmentFilterReunionBinding.tilSujet.setVisibility(View.GONE);
                    fragmentFilterReunionBinding.checkboxLieu.setVisibility(View.VISIBLE);
                    fragmentFilterReunionBinding.checkboxDate.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
    public void timeDialog() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String hourString = "";
                String minuteString = "";
                if (hourOfDay < 10) {
                    hourString = "0" + hourOfDay;
                }
                else {
                    hourString = "" + hourOfDay;
                }
                if (minute < 10) {
                    minuteString = "0" + minute;
                }
                else {
                    minuteString = "" + minute;
                }
                fragmentFilterReunionBinding.tilDate1.setText( hourString + "h" + minuteString);
            }
        }, hour, minute, true);
        mTimePicker.setTitle("Selectionnez une date");
        mTimePicker.show();
    }

}
