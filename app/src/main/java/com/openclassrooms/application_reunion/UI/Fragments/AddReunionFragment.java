package com.openclassrooms.application_reunion.UI.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.openclassrooms.application_reunion.DI.DI;
import com.openclassrooms.application_reunion.R;
import com.openclassrooms.application_reunion.databinding.ActivityMainBinding;
import com.openclassrooms.application_reunion.databinding.FragmentAddReunionBinding;
import com.openclassrooms.application_reunion.databinding.ParticipantElementBinding;
import com.openclassrooms.application_reunion.model.Reunion;
import com.openclassrooms.application_reunion.service.ReunionApiService;

import java.util.Arrays;

public class AddReunionFragment extends DialogFragment {
    /** The system calls this to get the DialogFragment's layout, regardless
     of whether it's being displayed as a dialog or an embedded fragment. */

    private ReunionApiService mApiService;
    private  FragmentAddReunionBinding fragmentAddReunionBinding;
    public interface AddreunionDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
    }
    AddreunionDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            listener = (AddreunionDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement AddReunionDialogListener");
        }
    }

    /** The system calls this only when creating the layout in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        mApiService = DI.getReunionApiService();
        fragmentAddReunionBinding = FragmentAddReunionBinding.inflate( inflater);
        builder.setView(fragmentAddReunionBinding.getRoot())
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
                .setTitle("Ajouter un nouveau évènement")
                // Add action buttons
                .setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Reunion reunion = new Reunion(
                                System.currentTimeMillis(),
                                fragmentAddReunionBinding.tilDate.getEditText().getText().toString(),
                                fragmentAddReunionBinding.tilLieu.getEditText().getText().toString(),
                                fragmentAddReunionBinding.tilSujet.getEditText().getText().toString(),
                                Arrays.asList(fragmentAddReunionBinding.tilListedesparticipants.getEditText().getText().toString().split(","))
                        );
                        mApiService.createReunion(reunion);
                        listener.onDialogPositiveClick(AddReunionFragment.this);
                        
                    }
                })
                .setNegativeButton("Abondonner", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddReunionFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

}
