package com.example.examantoniopicornell;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class TextFragments extends AppCompatActivity implements FirstFragment.OnTextChangedListener, ThirdFragment.OnColorChangedListener {

    private SecondFragment secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_fragments);

        // Inicio los fragmentos:
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Crear instancia del segundo fragmento
        secondFragment = new SecondFragment();

        // Agrego los 3 fragmentos:
        fragmentManager.beginTransaction().replace(R.id.fragment_first, new FirstFragment()).commit();
        fragmentManager.beginTransaction().replace(R.id.fragment_second, secondFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.fragment_third, new ThirdFragment()).commit();
    }

    @Override
    public void onTextChanged(String newText) {
        // Llamo a la funcion del segundo fragmento para cambiar el texto con el edittext:
        if (secondFragment != null) {
            secondFragment.updateTextView(newText);
        }
    }

    @Override
    public void onSeekBarChanged(int textSize) {
        // Llamo a la funcion del segundo fragmento para cambiar el tamaño del texto
        if (secondFragment != null) {
            secondFragment.updateTextSize(textSize);
        }
    }

    @Override
    public void onColorChanged(int red, int green, int blue) {
        // llamo al segundo fragmento para cambiar el color del texto:
        if (secondFragment != null) {
            secondFragment.updateTextColor(red, green, blue);
        }
    }
}
