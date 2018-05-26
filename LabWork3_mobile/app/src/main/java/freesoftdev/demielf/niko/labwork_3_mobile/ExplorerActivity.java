package freesoftdev.demielf.niko.labwork_3_mobile;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ExplorerActivity extends AppCompatActivity{

    File[] files = new File[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            setContentView(R.layout.fragment_explorer);

            (findViewById(R.id.go_back_button)).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ChangeToMainView();
                        }
                    }
            );
            getSavedFiles();
        }
        catch (Exception e) {
            String exc = e.toString();
        }
    }

    public void getSavedFiles(){
        File directory = this.getBaseContext().getFilesDir();
        files = directory.listFiles();
        ArrayList<TextView> list = new ArrayList<TextView>();
        for (int i = 0; i < files.length; i++)
        {
            TextView tv = new TextView(this);
            tv.setText(files[i].getName());
            tv.setHeight(100);
            tv.setPadding(3,3,3,3);
            tv.setTextSize(20);
            tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int position = 0;
                    boolean bold = true, italic = true;
                    String text = "";

                    String filename = ((TextView) v).getText().toString();

                    FileInputStream is;
                    BufferedReader reader;
                    File file = null;
                    for (File f:files) {
                        if (filename.equals(f.getName()))
                            file = f;
                    }

                    try {
                        is = new FileInputStream(file);
                        reader = new BufferedReader(new InputStreamReader(is));
                        String line = reader.readLine();
                        int i = 0;
                        while (line != null) {
                            switch (i) {
                                case 0: {
                                    position = Integer.parseInt(line);
                                    break;
                                }
                                case 1: {
                                    if (line.equals("true"))
                                        bold = true;
                                    else
                                        bold = false;
                                    break;
                                }
                                case 2: {
                                    if (line.equals("true"))
                                        italic = true;
                                    else
                                        italic = false;
                                    break;
                                }
                                case 3: {
                                    text = line;
                                    break;
                                }
                                default: {
                                    text += line;
                                    break;
                                }
                            }
                            line = reader.readLine();
                            i++;
                        }
                    }
                    catch (Exception e) {
                        String exc = e.toString();
                    }

                    Save_Open_Activity.PassArguments(position, bold, italic, text);
                    ChangeToMainView();
                }
            });
            try {
            ((LinearLayout) findViewById(R.id.file_holder)).addView(tv);
            }
            catch (Exception e) {
                String exc = e.toString();
            }
        }
    }

    public void ChangeToMainView() {
        Intent intentApp = new Intent(ExplorerActivity.this,
                Save_Open_Activity.class);

        ExplorerActivity.this.startActivity(intentApp);
        ExplorerActivity.this.finish();

        Log.i("Content ", " App layout ");
    }

}

