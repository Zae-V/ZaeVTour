package com.example.zaevtour.ui.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class SharedViewModel extends ViewModel {
    @NotNull
    private final MutableLiveData message = new MutableLiveData();

    @NotNull
    public final MutableLiveData getMessage() {
        return this.message;
    }

    public final void sendMessage(@NotNull String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.message.setValue(text);
    }
}