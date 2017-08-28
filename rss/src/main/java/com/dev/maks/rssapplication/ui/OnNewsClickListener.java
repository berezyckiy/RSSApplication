package com.dev.maks.rssapplication.ui;

import com.dev.maks.rssapplication.model.Item;

/**
 * Created by berezyckiy on 27.08.2017.
 */

public interface OnNewsClickListener {

    /**
     * Метод вызывается при нажатии на элемент списка RecyclerView
     * @param news объект элемента списка
     */
    void onItemClick(Item news);
}
