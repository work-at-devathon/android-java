package com.parse.starter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAdapter extends  BaseExpandableListAdapter implements Filterable{

    List<String> parent=new ArrayList<>();
    HashMap<String,ArrayList<ParseObject>> child;
    Context context;
    CutsomFilter c = new CutsomFilter();
    MyAdapter(Context context,HashMap<String,ArrayList<ParseObject>> child)
    {
        this.context=context;
        this.child = child;
    }
    @Override
    public int getGroupCount() {

        return child.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return child.get(parent.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return parent.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return child.get(parent.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String title=(String)this.getGroup(i);
        if (view==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.parent,null);
        }
        TextView textView=(TextView)view.findViewById(R.id.text);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String title = (String) this.getChild(i, i1);
        if (view == null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.child,null);

        }
        TextView textView=(TextView)view.findViewById(R.id.text1);
        textView.setText(title);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public Filter getFilter() {
        return c;
    }

    class CutsomFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toLowerCase();

            FilterResults results = new FilterResults();
            return null;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        }
    }
}
