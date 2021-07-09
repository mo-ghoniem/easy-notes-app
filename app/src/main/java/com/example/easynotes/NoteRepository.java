package com.example.easynotes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;


public class NoteRepository {
    private NoteDao noteDao;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
    }

    public Completable insert(Note note) {
        return noteDao.insertNote(note);
    }

    public void update(Note note) {
        noteDao.updateNote(note);
    }

    public void delete(Note note){
        noteDao.deleteNote(note);
    }

    public void deleteNotes() {
        noteDao.deleteAll();
    }

    public Observable<List<Note>> getNotes(){
        return  noteDao.getAllNotes();
    }
}


