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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MyAdapter extends BaseExpandableListAdapter implements Filterable {


    HashMap<String, ArrayList<ParseObject>> child;
    Context context;
    CutsomFilter c = new CutsomFilter();
    protected List<String> countries;

    MyAdapter(Context context, HashMap<String, ArrayList<ParseObject>> child) {
        this.context = context;
        this.child = child;
        countries = new ArrayList<>();
        countries.addAll(child.keySet());


    }

    @Override
    public int getGroupCount() {

        return child.size();
    }

    @Override
    public int getChildrenCount(int i) {
        String key = countries.get(i);
        return child.get(key).size();
    }

    @Override
    public Object getGroup(int i) {
        String key = countries.get(i);
        return key;
    }

    @Override
    public Object getChild(int i, int i1) {

        return child.get(countries.get(i)).get(i1);
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
        String title = (String) this.getGroup(i);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.parent, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ParseObject parseObject = (ParseObject) this.getChild(i, i1);
        String title = (String) parseObject.get("city");


        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child, null);

        }
        TextView textView = (TextView) view.findViewById(R.id.text1);
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

    class CutsomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toLowerCase();

            FilterResults results = new FilterResults();
            final List<String> list = new ArrayList<>();
            ArrayList<ParseObject> a = new ArrayList<>();
            Set keys = child.keySet();
            Iterator itr = keys.iterator();
            String key;
            while (itr.hasNext()) {
                key = (String) itr.next();
                a.addAll(child.get(key));
            }
            for (ParseObject p : a) {
                String s = (String) p.get("city");
                list.add(s);
            }

            int count = list.size();
            final ArrayList<String> nlist = new ArrayList<String>(count);

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            List<String> l = new ArrayList<>();
            l = (ArrayList<String>) filterResults.values;
           // notifyDataSetChanged();
        }
    }
}
