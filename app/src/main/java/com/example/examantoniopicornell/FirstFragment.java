package com.example.examantoniopicornell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    private EditText editText;
    private SeekBar seekBar;
    private Button buttonChangeText;

    // Definir el listener que la actividad implementará
    private OnTextChangedListener listener;

    public interface OnTextChangedListener {
        void onTextChanged(String text);
        void onSeekBarChanged(int textSize);  // Método para recibir el valor del SeekBar
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // Variables del activity.xml:
        editText = view.findViewById(R.id.editFragmentOne);
        seekBar = view.findViewById(R.id.seekBar);
        buttonChangeText = view.findViewById(R.id.buttonChangeText);

        // Configurar el listener para el botón
        buttonChangeText.setOnClickListener(v -> {
            String newText = editText.getText().toString();
            if (!newText.isEmpty()) {
                Toast.makeText(getActivity(), "Texto cambiado a: " + newText, Toast.LENGTH_SHORT).show();
                // Llamar al listener de la actividad
                if (listener != null) {
                    listener.onTextChanged(newText);
                }
            } else {
                Toast.makeText(getActivity(), "Por favor, introduce un texto", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el listener para el SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Llamar al listener de la actividad cuando cambia el valor del SeekBar
                if (listener != null) {
                    listener.onSeekBarChanged(progress + 10);  // Ajustamos el valor para no hacer el texto demasiado pequeño
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        return view;
    }

    // Aseguro que la actividad implementa el listener
    @Override
    public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
        if (context instanceof OnTextChangedListener) {
            listener = (OnTextChangedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnTextChangedListener");
        }
    }
}
