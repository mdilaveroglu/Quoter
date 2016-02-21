/*
 * Copyright 2016 Mehmet Dilaveroğlu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.dilaver.quoter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.dilaver.quoter.R;
import co.dilaver.quoter.models.Quote;

import java.util.Collections;
import java.util.List;


public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.MyViewHolder> {

    private static final String TAG = QuotesAdapter.class.getSimpleName();
    private LayoutInflater inflater;
    private List<Quote> data = Collections.emptyList();
    Context ctx;
    LongClickListener clickListener;

    public QuotesAdapter(Context context) {
        ctx = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<Quote> items) {
        this.data = items;
        notifyItemRangeChanged(0, items.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_popular_quotes, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.quote.setText(data.get(position).getQuoteText());
        holder.author.setText(data.get(position).getQuoteAuthor());
    }

    public void setLongClickListener(LongClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {


        TextView quote;
        TextView author;

        MyViewHolder(View itemView) {
            super(itemView);

            quote = (TextView) itemView.findViewById(R.id.tvPopularQuotesQuote);
            author = (TextView) itemView.findViewById(R.id.tvPopularQuotesAuthor);


            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (clickListener != null) {
                clickListener.longClicked(v, getPosition());
            }

            return false;
        }
    }

    public interface LongClickListener {
        void longClicked(View view, int position);
    }
}