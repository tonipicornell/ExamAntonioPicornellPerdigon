package com.example.examantoniopicornell;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        // Asociar el TextView
        textView = view.findViewById(R.id.textView);

        // Texto por defecto
        textView.setText("Fragment Two");
        textView.setTextColor(getResources().getColor(android.R.color.holo_blue_light));

        return view;
    }

    // Método para actualizar el tamaño del texto
    public void updateTextSize(int textSize) {
        if (textView != null) {
            textView.setTextSize(textSize);  // Cambiar el tamaño del texto
        }
    }

    // Método para actualizar el texto
    public void updateTextView(String newText) {
        if (textView != null) {
            textView.setText(newText);
        }
    }

    // Método para actualizar el color del texto
    public void updateTextColor(int red, int green, int blue) {
        if (textView != null) {
            textView.setTextColor(Color.rgb(red, green, blue));
        }
    }
}
