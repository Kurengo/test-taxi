package com.example.test_task.repository;

public interface RepositoryCallback<T> {
    void onSuccess(T value);

    void onError(RepositoryException repositoryException);
}