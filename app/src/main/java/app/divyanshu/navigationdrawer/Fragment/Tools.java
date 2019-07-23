package app.divyanshu.navigationdrawer.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import app.divyanshu.navigationdrawer.MainActivity;
import app.divyanshu.navigationdrawer.R;

public class Tools extends Fragment   {
    TextView text;
    Bundle bundle;
    View view;
    private boolean mReturningWithResult = false;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mReturningWithResult = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tools, container, false);
        text = view.findViewById(R.id.text1);

         bundle  =getArguments();
        if (bundle != null && bundle.containsKey("name") ) {
            String name = getArguments().getString("name");
            Log.e("Tools", "name from bundle: " + name);
            text.setText(name);

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        bundle.clear();
        getActivity().startActivity(new Intent(getContext(), MainActivity.class));
    }


    @Override
    public void onResume() {
        super.onResume();
        mReturningWithResult = false;


    }
}
