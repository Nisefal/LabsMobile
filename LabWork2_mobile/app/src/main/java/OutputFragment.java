import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import freesoftdev.demielf.niko.labwork_2.R;

public class OutputFragment extends Fragment {
    public static View rootViewOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewOut = inflater.inflate(R.layout.output_fragment, container, false);

        (rootViewOut.findViewById(R.id.transform_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText output = rootViewOut.findViewById(R.id.output);

                String text = ((EditText) InputFragment.rootViewIn.findViewById(R.id.input)).getText().toString();
                int type = 0;
                if (((CheckBox) InputFragment.rootViewIn.findViewById(R.id.bold_check)).isChecked()) {
                    type += 1;
                }
                if (((CheckBox) InputFragment.rootViewIn.findViewById(R.id.italic_check)).isChecked()) {
                    type += 2;
                }
                String style = ((Spinner) InputFragment.rootViewIn.findViewById(R.id.type_select)).getSelectedItem().toString();
                switch (style) {
                    case "none": {
                        output.setTypeface(Typeface.create("", type));
                        break;
                    }
                    case "Font 1": {
                        output.setTypeface(Typeface.create("sans-serif-condensed", type));
                        break;
                    }
                    case "Font 2": {
                        output.setTypeface(Typeface.create("sans-serif-thin", type));
                        break;
                    }
                    case "Font 3": {
                        output.setTypeface(Typeface.create("sans-serif-medium", type));
                        break;
                    }
                }
                output.setText(text);
            }
        });

        (rootViewOut.findViewById(R.id.clear_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((Spinner) InputFragment.rootViewIn.findViewById(R.id.type_select)).setSelection(0);
                ((CheckBox) InputFragment.rootViewIn.findViewById(R.id.bold_check)).setChecked(false);
                ((CheckBox) InputFragment.rootViewIn.findViewById(R.id.italic_check)).setChecked(false);
                ((EditText) rootViewOut.findViewById(R.id.output)).setText("");
                ((EditText) InputFragment.rootViewIn.findViewById(R.id.input)).setText("");
            }
        });

        return rootViewOut;
    }
}
