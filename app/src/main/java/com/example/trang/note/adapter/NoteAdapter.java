package com.example.trang.note.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trang.note.R;
import com.example.trang.note.note.Note;

import java.util.ArrayList;

/**
 * Created by Trang on 5/5/2017.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder>  {
    private Context mContext;
    private ArrayList<Note> arrayList;
    private OnItemClickListner onItemClickListner;

    public void setOnItemClickListner(OnItemClickListner ClickListner) {
        this.onItemClickListner = ClickListner;
    }

    public NoteAdapter(Context mContext, ArrayList<Note> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;

    }

    @Override
    public NoteAdapter.NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, null));
    }

    @Override
    public void onBindViewHolder(final NoteAdapter.NoteHolder holder, final int position) {
        holder.tvDate.setText(arrayList.get(position).getDate());
        if (arrayList.get(position).getColor() != null) {
            holder.linearLayout.setBackgroundColor(Color.parseColor(arrayList.get(position).getColor()));
        }
        holder.imgBtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListner.OnCLick(arrayList.get(position).getDate());
            }
        });
        holder.tvContent.setText(arrayList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null) ? arrayList.size() : 0;
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        private TextView tvDate;
        private ImageButton imgBtDelete;
        private TextView tvContent;
        private LinearLayout linearLayout;

        public NoteHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            imgBtDelete = (ImageButton) itemView.findViewById(R.id.img_bt_delete);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_item_note);

        }
    }

    public interface OnItemClickListner {
        void OnCLick(String posisson);
    }
}
