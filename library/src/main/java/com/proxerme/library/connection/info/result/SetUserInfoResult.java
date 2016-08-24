package com.proxerme.library.connection.info.result;

import android.support.annotation.Nullable;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.request.SetUserInfoRequest;

/**
 * The class that represents the result of {@link com.proxerme.library.connection.info.request.SetUserInfoRequest}.
 *
 * @author Desnoo
 */
public class SetUserInfoResult extends ProxerResult<Void> {

    protected SetUserInfoResult(){
    }

    @Override
    @Nullable
    public Void getData() {
        return null;
    }
}
