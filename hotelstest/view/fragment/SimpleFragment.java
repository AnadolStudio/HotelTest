package com.anadolstudio.hotelstest.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class SimpleFragment extends Fragment {

    private LoadingView mLoadingView;

    protected void showLoadingDialog() {
        FragmentManager fm = getFragmentManager();
        if (fm == null) return;

        mLoadingView = LoadingDialog.view(getFragmentManager());
        mLoadingView.showLoadingIndicator();
    }

    protected void hideLoadingDialog() {
        if (mLoadingView != null) mLoadingView.hideLoadingIndicator();
    }

}
