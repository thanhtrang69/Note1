package com.example.trang.note.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.trang.note.R;
import com.example.trang.note.activity.MainActivity;
import com.example.trang.note.note.Note;

import java.util.ArrayList;

/**
 * Created by Trang on 5/5/2017.
 */

public class ListNoteFragment extends Fragment implements View.OnClickListener {
    private View view;
    private EditText edtWriteContent;
    private Button btRed;
    private Button btGreen;
    private Button btPurple;
    private Button btOrange;
    private Button btBlue;
    private RelativeLayout relativeLayout;
    private String color;
    private DatePicker datePicker;
    private Note note;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_note, container, false);
        initView();
        return view;
    }

    private void initView() {
        color = "#ff669900";
        edtWriteContent = (EditText) view.findViewById(R.id.edt_write_content);

        note = new Note();

        btRed = (Button) view.findViewById(R.id.bt_red);
        btBlue = (Button) view.findViewById(R.id.bt_blue);
        btGreen = (Button) view.findViewById(R.id.bt_green);
        btOrange = (Button) view.findViewById(R.id.bt_orange);
        btPurple = (Button) view.findViewById(R.id.bt_purple);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.ll_register);

        btRed.setOnClickListener(this);
        btGreen.setOnClickListener(this);
        btOrange.setOnClickListener(this);
        btBlue.setOnClickListener(this);
        btPurple.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);

        datePicker = (DatePicker) view.findViewById(R.id.datepicker);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_red:
                color = btRed.getContentDescription().toString();
                relativeLayout.setBackgroundColor(Color.parseColor(color));
                note.setColor(color);

                break;
            case R.id.bt_green:
                color = btGreen.getContentDescription().toString();
                relativeLayout.setBackgroundColor(Color.parseColor(color));
                note.setColor(color);
                break;
            case R.id.bt_orange:
                color = btOrange.getContentDescription().toString();
                relativeLayout.setBackgroundColor(Color.parseColor(color));
                note.setColor(color);
                break;
            case R.id.bt_purple:
                color = btPurple.getContentDescription().toString();
                relativeLayout.setBackgroundColor(Color.parseColor(color));
                note.setColor(color);
                break;
            case R.id.bt_blue:
                color = btBlue.getContentDescription().toString();
                relativeLayout.setBackgroundColor(Color.parseColor(color));
                note.setColor(color);
                break;
            case R.id.ll_register:
                String conten = edtWriteContent.getText().toString();
                if (conten.isEmpty()) {
                    Toast.makeText(getActivity(), "Chưa Nhập Nội Dung", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (color.isEmpty()) {
                    Toast.makeText(getActivity(), "Chưa Trọn Màu", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String date = datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear();
                    ((MainActivity) getActivity()).insertData(conten, color, date);
                    ((MainActivity) getActivity()).showCreateFragment();

                }

        }
    }
}
