package freesoftdev.demielf.niko.labwork1;

import android.content.ClipData;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import org.w3c.dom.Text;

public class MainFrame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        (findViewById(R.id.clear_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((EditText) findViewById(R.id.output)).setText("");
                ((EditText) findViewById(R.id.input)).setText("");
            }
        });

        (findViewById(R.id.transform_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText output =  findViewById(R.id.output);

                String text = ((EditText) findViewById(R.id.input)).getText().toString();
                int type = 0;
                if (((CheckBox) findViewById(R.id.bold_check)).isChecked()) {
                    type += 1;
                }
                if (((CheckBox) findViewById(R.id.italic_check)).isChecked()) {
                    type += 2;
                }
                String style = ((Spinner) findViewById(R.id.type_select)).getSelectedItem().toString();
                switch(style){
                    case "none":{
                        output.setTypeface(Typeface.create( "",type));
                        break;
                    }
                    case "Font 1": {
                        output.setTypeface(Typeface.create( "sans-serif-condensed",type));
                        break;
                    }
                    case "Font 2":{
                        output.setTypeface(Typeface.create( "sans-serif-thin",type));
                        break;
                    }
                    case "Font 3":{
                        output.setTypeface(Typeface.create( "sans-serif-medium",type));
                        break;
                    }
                }
                output.setText(text);
            }
        });
    }
}
