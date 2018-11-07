package com.example.manohar.drioditor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manohar.drioditor.R;
import com.example.manohar.drioditor.callbacks.CodeEventListener;
import com.example.manohar.drioditor.model.codes;
import com.example.manohar.drioditor.utils.CodeUtils;

import java.util.ArrayList;

public class codesAdapter extends RecyclerView.Adapter<codesAdapter.codeHolder> {


    public codesAdapter(Context context, ArrayList<com.example.manohar.drioditor.model.codes> codes) {
        this.context = context;
        this.codes = codes;
    }

    private Context context;
    private ArrayList<codes> codes;

    private CodeEventListener listener;

    @NonNull
    @Override
    public codeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(context).inflate(R.layout.editor, viewGroup, false);
        return new codeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull codeHolder codeHolder, int i) {
         final codes code=getCode(i);
         if (code!=null){
             codeHolder.codeText.setText(code.getCodeText());
             codeHolder.codeDate.setText(CodeUtils.dateFromLong(code.getCodeDate()));
             codeHolder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     listener.onCodeClick(code);
                 }
             });

             codeHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                 @Override
                 public boolean onLongClick(View v) {
                     listener.onCodeLongClick(code);
                     return false;
                 }
             });
         }


    }

    @Override
    public int getItemCount() {
        return codes.size();
    }

    private codes getCode(int position){
        return codes.get(position);
    }

    class codeHolder extends RecyclerView.ViewHolder {
        TextView codeText, codeDate;

        public codeHolder(@NonNull View itemView) {

            super(itemView);
            codeDate=itemView.findViewById(R.id.code_date);
            codeText=itemView.findViewById(R.id.code);
        }
    }

    public void setListener(CodeEventListener listener) {
        this.listener = listener;
    }
}
