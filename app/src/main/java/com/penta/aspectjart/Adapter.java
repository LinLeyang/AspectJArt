package com.penta.aspectjart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.penta.aspectjart.business.DetailBean;
import com.penta.library.annotation.ApjLog;

import java.util.List;

public class Adapter extends BaseAdapter {

    List<DetailBean> detailBeanList;
    Context context;

    public Adapter(Context context, List<DetailBean> detailBeanList) {
        this.context = context;
        this.detailBeanList = detailBeanList;
    }

    @Override
    public int getCount() {
        if (null != detailBeanList)
            return detailBeanList.size();
        return 0;
    }

    @Override
    public DetailBean getItem(int position) {
        if (null != detailBeanList)
            return detailBeanList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }
        ViewHolder viewHolder;
        if (convertView.getTag() == null) {
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final DetailBean detailBean = getItem(position);

        viewHolder.id.setText("ID:" + detailBean.getId());
        viewHolder.title.setText("title:" + detailBean.getTitle());
        viewHolder.content.setText("content:" + detailBean.getContent());

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onItemClick(position, detailBean);
            }
        });

        return convertView;
    }

    @ApjLog
    public void onItemClick(int position, DetailBean detailBean) {
        context.startActivity(new Intent(context, DetailActivity.class));
    }


    static class ViewHolder {

        TextView title;
        TextView id;
        TextView content;

        ViewHolder(View view) {
            title = view.findViewById(R.id.tv_title);
            id = view.findViewById(R.id.tv_id);
            content = view.findViewById(R.id.tv_content);
        }
    }
}