package com.halo.dictionary.rcclrview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.halo.dictionary.R;
import com.halo.dictionary.WordEntry;
import com.halo.dictionary.temp.WordsListPresenter;

import java.util.HashSet;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Адаптер для отображения переданных слов
 *
 * Created by halo on 08.09.2017.
 */

public class WordsRcclrViewAdapter
        extends RecyclerView.Adapter<WordsRcclrViewAdapter.EntryViewHolder> {

    private WordsListPresenter presenter;

    /**
     * Создание адаптера для отображения переданных слов
     *
     * @param     - курсов базы данных для отображения данных
     * @param    - вызывающий контекст
     */
    public WordsRcclrViewAdapter(@NonNull WordsListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View wordView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_rcclr_view_words, parent, false);
        return new EntryViewHolder(wordView, this.presenter);
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        final WordEntry wordEntry = this.presenter.getEntryForPosition(position);
        holder.tvWord.setText(wordEntry.getWord());
        holder.tvTranslation.setText(wordEntry.getTranslation());
        holder.tvId.setText(String.valueOf(wordEntry.getId()));
        holder.tvTranslation.setVisibility(this.presenter.isTranslationVisible(wordEntry.getId()) ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return this.presenter.getEntriesAmount();
    }

    /**
     * View for each separate data item
     */
    static class EntryViewHolder extends RecyclerView.ViewHolder {

        TextView tvId;
        TextView tvWord;
        TextView tvTranslation;
        WordsListPresenter presenter;

        EntryViewHolder(View view, WordsListPresenter presenter) {
            super(view);
            this.tvId = view.findViewById(R.id.tv_c_rcclr_view_words_id);
            this.tvWord = view.findViewById(R.id.tv_c_rcclr_view_words_word);
            this.tvTranslation = view.findViewById(R.id.tv_c_rcclr_view_words_translation);
            this.presenter = presenter;

            view.setOnClickListener(tvView -> {
                final Long id = Long.parseLong(tvId.getText().toString());
                this.presenter.onClickEntry(id);
                this.tvId.setVisibility(this.presenter.isTranslationVisible(id) ? View.VISIBLE : View.INVISIBLE);
            });
        }
    }

}
