package com.example.trang.note.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.trang.note.R;
import com.example.trang.note.adapter.NoteAdapter;
import com.example.trang.note.fragment.CreateFragment;
import com.example.trang.note.fragment.ListNoteFragment;
import com.example.trang.note.manager.NoteManager;
import com.example.trang.note.note.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CreateFragment createFragment;
    private ListNoteFragment listNoteFragment;
    private NoteManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showCreateFragment();
        manager = new NoteManager();
        manager.copyDatabase(this);
    }

    public void showCreateFragment() {
        if (createFragment == null) {
            createFragment = new CreateFragment();
        }
        getFragmentManager().beginTransaction().replace(android.R.id.content, createFragment)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public void showListNoteFragment() {
        if (listNoteFragment == null) {
            listNoteFragment = new ListNoteFragment();
        }
        getFragmentManager().beginTransaction().replace(android.R.id.content, listNoteFragment)
                .addToBackStack(null)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public void insertData(String content, String color, String date) {
        manager.insertDatabase(content, color, date);
    }

    public ArrayList<Note> getAllItemNote() {
        return manager.getAllNote(this);
    }

    public void deleteItemNote(int pos) {
        manager.deleteId(pos);
    }
}
