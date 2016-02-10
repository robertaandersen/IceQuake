package is.robertreynisson.icequake.presenter_layer.quakeActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import is.robertreynisson.icequake.R;
import is.robertreynisson.icequake.domain_layer.QuakeViewModel;
import is.robertreynisson.icequake.utils.Utils;

/**
 * Created by robert on 9.2.2016.
 */
public class QuakeFragment extends Fragment {
    //Main purpose of Fragments is to maintain reference
    //to the View and View Model and react to OS events
    private static final String TAG = QuakeFragment.class.getSimpleName();

    //The parentView Model provides Data
    private QuakeViewModel viewModel;

    //The View contains a reference to the Model
    //Handles input/output from/to UI
    private QuakeView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize the fragments model
        viewModel = new QuakeViewModel();
        Utils.logger(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quake_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialize the fragments parentView
        this.view = (QuakeView) view.findViewById(R.id.quake_view);

        //Set the RxAndroid reactive subscribers
        //Subscriptions to Observable objects
        //are handled by the ViewModel and AbstractViewModel class
        //ensuring that each fragment is properly subscribed / un-subscribed
        //to prevent memory leaks
        viewModel.subscribeToDataStore();
    }

    @Override
    public void onResume() {
        super.onResume();

        //Connect the model to the parentView
        view.setViewModel(viewModel);
    }

    @Override
    public void onPause() {
        super.onPause();
        view.setViewModel(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.unsubscribeFromDataStore();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.dispose();
        viewModel = null;
    }

}
