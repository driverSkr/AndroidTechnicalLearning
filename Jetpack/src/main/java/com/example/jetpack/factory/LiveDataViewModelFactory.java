package com.example.jetpack.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpack.ViewModel.LiveDataViewModel;
import com.example.jetpack.ViewModel.ParametersViewModel;

/*
* activity中借助ViewModelProvider.Factory将参数传递给ViewModel
* */
public class LiveDataViewModelFactory implements ViewModelProvider.Factory {

    private int countReserved;

    public LiveDataViewModelFactory(int countReserved){
        this.countReserved = countReserved;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LiveDataViewModel.class)){
            return (T) new LiveDataViewModel(countReserved);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
