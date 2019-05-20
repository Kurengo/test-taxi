package com.example.test_task.ui.fragments;


import androidx.fragment.app.Fragment;

import com.example.test_task.App;
import com.example.test_task.repository.RepositoryManager;

abstract class BaseFragment extends Fragment {

    protected RepositoryManager getRepositoryManager() {
        return getActivity() != null ? ((App) getActivity().getApplicationContext()).getRepositoryManager() : null;
    }

    protected boolean wasInBackground() {
        return getActivity() != null && ((App) getActivity().getApplicationContext()).wasInBackground();
    }
}