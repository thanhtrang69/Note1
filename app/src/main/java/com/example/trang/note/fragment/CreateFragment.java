package com.example.trang.note.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trang.note.R;
import com.example.trang.note.activity.MainActivity;
import com.example.trang.note.adapter.NoteAdapter;
import com.example.trang.note.manager.NoteManager;

/**
 * Created by Trang on 5/5/2017.
 */

public class CreateFragment extends Fragment implements View.OnClickListener, NoteAdapter.OnItemClickListner {
    private View view;
    private TextView tvCreateNote;
    private NoteAdapter adapter;
    private RecyclerView recyclerView;
    private NoteManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_note, container, false);
        initView();
        return view;
    }

    private void initView() {
        manager = new NoteManager();
        manager.copyDatabase(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        tvCreateNote = (TextView) view.findViewById(R.id.tv_create_note);
        adapter = new NoteAdapter(getActivity(), ((MainActivity) getActivity()).getAllItemNote());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        tvCreateNote.setOnClickListener(this);
        adapter.setOnItemClickListner(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_create_note:
                ((MainActivity) getActivity()).showListNoteFragment();
                break;
            default:
                break;
        }
    }


    @Override
    public void OnCLick(final String posisson) {
        manager.deleteDate(posisson);
        initView();
    }


}
