package com.tkdev.weatherapp.presenter;

import android.view.View;
import android.widget.TextView;

import com.tkdev.weatherapp.model.Weather;

import java.util.ArrayList;

public interface MainContract {
    interface Presenter extends BasePresenter {
        void onWeatherCreated();
    }

    interface View extends BaseView<Presenter>{
        void refreshViews();
    }

}
