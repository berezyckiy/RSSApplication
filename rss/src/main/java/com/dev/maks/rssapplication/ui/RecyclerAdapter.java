package com.dev.maks.rssapplication.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.maks.rssapplication.R;
import com.dev.maks.rssapplication.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий адаптер для списков {@link RecyclerView}
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Item> itemsList = new ArrayList<>();
    private OnNewsClickListener listener;

    RecyclerAdapter(OnNewsClickListener listener) {
        this.listener = listener;
    }

    /**
     * Метод для инициализация списка данных, в случае отстутсвия данных, отображение View
     *
     * @param itemsList список
     */
    public void setData(List<Item> itemsList) {
        this.itemsList.clear();
        this.itemsList.addAll(itemsList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHolder(inflateView(parent), listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    private Item getItem(int position) {
        return itemsList.get(position);
    }

    private View inflateView(ViewGroup view) {
        return LayoutInflater.from(view.getContext()).inflate(R.layout.news_item, view, false);
    }

    /**
     * Метод вызывается , когда необходим новый ViewHolder дял отображения элемента
     *
     * @param view представление элемента
     * @return ViewHolder
     */
    private ViewHolder createViewHolder(View view, OnNewsClickListener listener) {
        return new ViewHolder(view, listener);
    }

    /**
     * Класс представляющий все компоненты одного элементы списка
     * Предоставляет метод для заполнения данными
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        private OnNewsClickListener listener;
        private TextView title;
        private TextView date;

        ViewHolder(View itemView, OnNewsClickListener listener) {
            super(itemView);

            this.listener = listener;
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
        }

        /**
         * Метод для заполнения данными компонентов элемента экрана
         *
         * @param news объект с данными
         */
        void bind(final Item news) {
            title.setText(news.getTitle());
            date.setText(news.getPubDate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(news);
                    }
                }
            });
        }
    }
}
