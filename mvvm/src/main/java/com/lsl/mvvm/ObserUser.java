package com.lsl.mvvm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.lsl.mvvm.BR;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/6/6.
 */

public class ObserUser extends BaseObservable {

    private String frName;
    private String lsName;

    @Bindable
    public String getFrName() {
        return frName;
    }

    public void setFrName(String frName) {
        this.frName = frName;
        notifyPropertyChanged(BR.frName);
    }
    @Bindable
    public String getLsName() {
        return lsName;
    }

    public void setLsName(String lsName) {
        this.lsName = lsName;
        notifyPropertyChanged(BR.lsName);
    }
}
