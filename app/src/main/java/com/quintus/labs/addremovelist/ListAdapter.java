package com.quintus.labs.addremovelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by Quintus Labs on 15/11/18.
 * www.quintuslabs.com
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    Context context;
    ArrayList<String> steps;

    public ListAdapter(ArrayList<String> steps, Context context) {
        this.steps = steps;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {

        int x = holder.getLayoutPosition();

        if (steps.get(x).length() > 0) {
            holder.step.setText(steps.get(x));
        } else {
            holder.step.setText(null);
            holder.step.setHint("Next Step");
            holder.step.requestFocus();
        }

    }

    public ArrayList<String> getStepList() {
        return steps;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton add, remove;
        EditText step;

        public ViewHolder(View itemView) {
            super(itemView);
            add = itemView.findViewById(R.id.add);
            remove = itemView.findViewById(R.id.remove);
            step = itemView.findViewById(R.id.step);

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    try {
                        steps.remove(position);
                        notifyItemRemoved(position);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    try {
                        steps.add(position + 1, "");
                        notifyItemInserted(position + 1);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            });

            step.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    steps.set(getAdapterPosition(), s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }
}
