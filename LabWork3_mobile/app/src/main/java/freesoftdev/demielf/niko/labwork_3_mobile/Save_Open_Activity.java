package freesoftdev.demielf.niko.labwork_3_mobile;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class Save_Open_Activity extends AppCompatActivity {

    private static Button transform;
    private static Button clean;
    private static Button open;
    private static TextView input;
    private static TextView output;

    private static boolean Changed = false;
    private static int Position;
    private static boolean Bold, Italic;
    private static String Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save__open_);

        transform = findViewById(R.id.transform_button);
        clean = findViewById(R.id.clear_button);
        open = findViewById(R.id.open);

        (open).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChangeToExplorer();
            }
        });

        transform.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Transform();
                Save();
            }
        });

        (clean).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((Spinner) findViewById(R.id.type_select)).setSelection(0);
                ((CheckBox) findViewById(R.id.bold_check)).setChecked(false);
                ((CheckBox) findViewById(R.id.italic_check)).setChecked(false);
                ((EditText) findViewById(R.id.output)).setText("");
                ((EditText) findViewById(R.id.input)).setText("");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Changed) {
            Set(Position, Bold, Italic, Text);
        }
    }

    private void Transform() {
        final EditText output = findViewById(R.id.output);
        String text = "";

        text = ((EditText) findViewById(R.id.input)).getText().toString();
        int type = 0;
        if (((CheckBox) findViewById(R.id.bold_check)).isChecked()) {
            type += 1;
        }
        if (((CheckBox) findViewById(R.id.italic_check)).isChecked()) {
            type += 2;
        }
        String style = ((Spinner) findViewById(R.id.type_select)).getSelectedItem().toString();
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

    private void Set(int position, boolean bold, boolean italic, String text){
        ((Spinner) findViewById(R.id.type_select)).setSelection(position);
        ((CheckBox) findViewById(R.id.bold_check)).setChecked(bold);
        ((CheckBox) findViewById(R.id.italic_check)).setChecked(italic);
        ((EditText) findViewById(R.id.input)).setText(text);
        Transform();
        Changed = false;
    }

    private void Save(){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(Calendar.getInstance().getTime().toString()+".txt", Context.MODE_PRIVATE));
            String all = ((Spinner) findViewById(R.id.type_select)).getSelectedItemId() + "\n" +
                    String.valueOf (((CheckBox) findViewById(R.id.bold_check)).isChecked()) + "\n" +
                    String.valueOf(((CheckBox) findViewById(R.id.italic_check)).isChecked()) + "\n" +
                    ((EditText) findViewById(R.id.output)).getText();
            outputStreamWriter.write(all);
            outputStreamWriter.close();
            Toast.makeText(getBaseContext(), "Successfuly saved!", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Toast.makeText(getBaseContext(), "Error with saveing..", Toast.LENGTH_SHORT).show();
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void ChangeToExplorer() {
        Intent intentApp = new Intent(Save_Open_Activity.this,
                ExplorerActivity.class);
        Save_Open_Activity.this.startActivity(intentApp);
        Save_Open_Activity.this.finish();
        Log.i("Content ", " App layout ");
    }

    public static void PassArguments(int position, boolean bold, boolean italic, String text){
        Changed = true;
        Position = position;
        Bold = bold;
        Italic = italic;
        Text = text;
        //Set(position,bold,italic,text);
    }


}
