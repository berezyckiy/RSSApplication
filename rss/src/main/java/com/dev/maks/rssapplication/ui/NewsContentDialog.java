package com.dev.maks.rssapplication.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dev.maks.rssapplication.R;

/**
 * Класс, представляющий диалог с выводимыми подробностями новости
 */

public class NewsContentDialog extends DialogFragment {

    private static final String ARGS_KEY_TITLE = "title";
    private static final String ARGS_KEY_CONTENT = "content";

    private String title;
    private String content;

    public static NewsContentDialog newInstance(String title, String content) {
        NewsContentDialog contentDialog = new NewsContentDialog();
        Bundle args = new Bundle();

        args.putString(ARGS_KEY_TITLE, title);
        args.putString(ARGS_KEY_CONTENT, content);
        contentDialog.setArguments(args);

        return contentDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(ARGS_KEY_TITLE, "");
        content = getArguments().getString(ARGS_KEY_CONTENT, "");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton(R.string.close, null);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.news_content_dialog, null);
        TextView description = (TextView) rootView.findViewById(R.id.content);

        builder.setTitle(title);
        builder.setView(rootView);
        description.setText(Html.fromHtml(content));

        return builder.create();
    }
}
