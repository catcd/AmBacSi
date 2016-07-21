package com.ahay.ambacsi.api.ambacsi.auth;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by SONY on 21-Jul-16.
 */
public abstract class Task<TResult> extends AsyncTask<Void, Void, TResult> {
    private Object lock = new Object();

    private OnCompleteListener<TResult> mOnCompleteListener;
    private OnSuccessListener<TResult> mOnSuccessListener;
    private OnFailureListener<TResult> mOnFailureListener;
    private TResult mResult;

    private boolean isComplete;
    private Exception mException;

    public boolean isComplete() {
        synchronized (this.lock) {
            return this.isComplete;
        }
    }

    public boolean isSuccessful() {
        synchronized (this.lock) {
            return this.isComplete && this.mException == null;
        }
    }

    public TResult getResult() {
        return mResult;
    }

    @Nullable
    public Exception getException() {
        synchronized(this.lock) {
            return this.mException;
        }
    }

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> var1) {
        this.mOnCompleteListener = var1;
        return this;
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<TResult> var1) {
        this.mOnSuccessListener = var1;
        return this;
    }

    @NonNull
    public Task<TResult> addOnFailureListener(@NonNull OnFailureListener<TResult> var1) {
        this.mOnFailureListener = var1;
        return this;
    }

    @Override
    protected void onPostExecute(TResult mResult) {
        this.mResult = mResult;

        if (this.isComplete()) {
            if (this.mOnCompleteListener != null) {
                this.mOnCompleteListener.onComplete(this);
            }

            if(this.isSuccessful()) {
                if (this.mOnSuccessListener != null) {
                    this.mOnSuccessListener.onSuccess(this);
                }
            } else {
                if (this.mOnFailureListener != null) {
                    this.mOnFailureListener.onFailure(this);
                }
            }
        }
    }
}
