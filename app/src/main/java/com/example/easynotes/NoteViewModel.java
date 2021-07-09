package com.example.easynotes;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class NoteViewModel extends AndroidViewModel {

    private static final String TAG = "NoteViewModel";

    private NoteRepository noteRepository;
    public MutableLiveData<List<Note>> listNotes = new MutableLiveData<>();

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
    }

    public void insert(Note note) {
        noteRepository.insert(note).subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {

            }
        });
    }
    public void update(Note note) {
        noteRepository.update(note);
    }
    public void delete(Note note) {
        noteRepository.delete(note);
    }
    public void deleteAllNotes() {
        noteRepository.deleteNotes();
    }

    public void getAllNotes() {
        noteRepository.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> listNotes.setValue(result), error -> Log.d(TAG, "getAllNotes: ",error));
    }
}
