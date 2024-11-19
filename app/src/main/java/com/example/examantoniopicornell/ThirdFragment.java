package com.example.examantoniopicornell;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {

    private SeekBar seekBarRed, seekBarGreen, seekBarBlue;
    private Button buttonChangeColor;
    private TextView colorPreview; // Para mostrar el color resultante (opcional)

    // Definir el listener que la actividad implementará
    private OnColorChangedListener listener;

    public interface OnColorChangedListener {
        void onColorChanged(int red, int green, int blue);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        seekBarRed = view.findViewById(R.id.seekBarRed);
        seekBarGreen = view.findViewById(R.id.seekBarGreen);
        seekBarBlue = view.findViewById(R.id.seekBarBlue);
        buttonChangeColor = view.findViewById(R.id.buttonChangeColor);

        colorPreview = new TextView(getActivity());
        colorPreview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
        colorPreview.setBackgroundColor(Color.rgb(128, 128, 128)); // Color gris como valor inicial
        ((ViewGroup) view).addView(colorPreview);

        // Configuracion de los seekBar:
        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColorPreview();
                notifyColorChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColorPreview();
                notifyColorChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColorPreview();
                notifyColorChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Configuracion del boton:
        buttonChangeColor.setOnClickListener(v -> {
            // Obtener los valores de los SeekBars y notificar el cambio de color
            notifyColorChanged();
        });

        return view;
    }

    private void updateColorPreview() {
        // Obtener los valores de los SeekBars
        int red = seekBarRed.getProgress();
        int green = seekBarGreen.getProgress();
        int blue = seekBarBlue.getProgress();

        // Actualizar el color del TextView:
        int color = Color.rgb(red, green, blue);
        colorPreview.setBackgroundColor(color);
    }

    private void notifyColorChanged() {
        // Valores:
        int red = seekBarRed.getProgress();
        int green = seekBarGreen.getProgress();
        int blue = seekBarBlue.getProgress();

        // Notificar a la actividad para que cambie el color del texto en SecondFragment
        if (listener != null) {
            listener.onColorChanged(red, green, blue);
        }
    }

    // Comprobar que la segunda actividad implementa el listener
    @Override
    public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
        if (context instanceof OnColorChangedListener) {
            listener = (OnColorChangedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnColorChangedListener");
        }
    }
}
