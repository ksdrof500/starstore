package br.com.starstore.util.textwatcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by filipenunes on 12/26/17.
 */

public class FreeFormatWatcher implements TextWatcher {

    boolean isUpdating;
    String old = "";
    String mask;
    EditText editText;

    public FreeFormatWatcher(String mask, EditText editText) {
        this.mask = mask;
        this.editText = editText;
    }

    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String str = unmask(s.toString());

        String currentMask = mask;

        StringBuilder mascara = new StringBuilder();
        if (isUpdating) {
            old = str;
            isUpdating = false;
            return;
        }
        int i = 0;
        for (char m : currentMask.toCharArray()) {
            if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                mascara.append(m);
                continue;
            }

            try {
                mascara.append(str.charAt(i));
            } catch (Exception e) {
                break;
            }
            i++;
        }
        isUpdating = true;
        editText.setText(mascara.toString());
        editText.setSelection(mascara.length());
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void afterTextChanged(Editable s) {
    }


}
