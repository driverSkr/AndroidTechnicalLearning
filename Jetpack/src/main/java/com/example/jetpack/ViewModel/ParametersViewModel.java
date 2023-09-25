package com.example.jetpack.ViewModel;

import androidx.lifecycle.ViewModel;

/*
* 向ViewModel 传递参数
* */
public class ParametersViewModel extends ViewModel {

    public int counter;

    //用于记录之前保存的计数值，并在初始化的时候赋值给counter变量
    public ParametersViewModel(int countReserved) {
        counter = countReserved;
    }
}
