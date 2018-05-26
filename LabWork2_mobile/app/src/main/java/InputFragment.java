import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import freesoftdev.demielf.niko.labwork_2.R;

public class InputFragment extends Fragment {
    public static View rootViewIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewIn = inflater.inflate(R.layout.input_fragment, container, false);

        return rootViewIn;
    }
}
