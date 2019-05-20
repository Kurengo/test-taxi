package com.example.test_task.ui.activities;


import androidx.appcompat.app.AppCompatActivity;

import com.example.test_task.App;
import com.example.test_task.repository.RepositoryManager;

abstract class BaseActivity extends AppCompatActivity {

    protected RepositoryManager getRepositoryManager() {
        return ((App) getApplicationContext()).getRepositoryManager();
    }
}