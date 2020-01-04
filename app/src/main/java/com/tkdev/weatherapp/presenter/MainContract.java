package com.tkdev.weatherapp.presenter;

import com.tkdev.weatherapp.Weather;

public interface MainContract {
    interface Presenter extends BasePresenter {
        Weather onViewCreated();
    }


}
