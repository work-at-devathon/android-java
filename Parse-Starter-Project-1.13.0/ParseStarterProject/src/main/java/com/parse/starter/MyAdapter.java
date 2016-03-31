package com.parse.starter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MyAdapter extends BaseExpandableListAdapter implements Filterable {

    TreeMap<String, String> hm;
    TreeMap<String, ArrayList<ParseObject>> child;
    TreeMap<String, ArrayList<ParseObject>> child2;
    Context context;
    CutsomFilter c = new CutsomFilter();
    protected List<String> countries;
    protected List<String> countries2;

    MyAdapter(Context context, TreeMap<String, ArrayList<ParseObject>> child) {
        this.context = context;
        this.child = child;
        countries = new ArrayList<>();
        countries.addAll(child.keySet());
        countries2 = countries;
        this.child2 = child;
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

        }//view.setTag();
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

//            String filterString = charSequence.toString().toLowerCase();
//
            FilterResults results = new FilterResults();
//            final List<String> list = new ArrayList<>();
//            ArrayList<ParseObject> a = new ArrayList<>();
//            Set keys = child.keySet();
//            Iterator itr = keys.iterator();
//            String key;
//            while (itr.hasNext()) {
//                key = (String) itr.next();
//                a.addAll(child.get(key));
//            }
//            for (ParseObject p : a) {
//                String s = (String) p.get("city");
//                list.add(s);
//            }
//
//            int count = list.size();
//            final ArrayList<String> nlist = new ArrayList<String>(count);
//
//            String filterableString;
//
//            for (int i = 0; i < count; i++) {
//                filterableString = list.get(i);
//                if (filterableString.toLowerCase().startsWith(filterString)) {
//                    nlist.add(filterableString);
//                }
//            }
//            hm = new TreeMap<>();
//
//            // for (String s:nlist)
//            // {for (Object o : child.keySet()) {
//            //  if (child.get(o).equals(s)) {
//            //  String s1=child.get(o).toString();
//            //     hm.put(s1,s);
//            //}
//            // }}
//            Iterator iterator = keys.iterator();
//            String res;
//            ArrayList<ParseObject> a1 = new ArrayList<>();
//            ArrayList<String> r = new ArrayList<>();
//            for (String s : nlist) {
//                while (iterator.hasNext()) {
//                    res = (String) iterator.next();
//                    a1.addAll(child.get(res));
//                    for (ParseObject p : a1) {
//                        String s2 = (String) p.get("city");
//                        r.add(s2);
//                        if (r.contains(s)) {
//                            hm.put(res, s);
//                            a1.clear();
//                        }
//                    }
//                }
//            }//notifyDataSetChanged();
//
//
//            results.values = hm;
//            results.count = hm.size();
            TreeMap<String, ArrayList<ParseObject>> t = new TreeMap<String, ArrayList<ParseObject>>();
            for (ArrayList<ParseObject> obj : child2.values()) {
                for (ParseObject obj2 : obj) {
                    if (obj2.getString("city").toLowerCase().startsWith(charSequence.toString())) {
                        String s = (String) obj2.getString("country");
                        ArrayList<ParseObject> temp = t.get(s);
                        if (temp == null) {
                            ArrayList<ParseObject> a = new ArrayList<ParseObject>();
                            a.add(obj2);
                            t.put(s, a);
                            Log.d("map", "loaded");
                        } else {
                            temp.add(obj2);

                        }
                    }
                }
            }
            results.values = t;
            results.count = t.keySet().size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {


            if (filterResults.count > 0) {
                Log.println(Log.INFO, "Results", "FOUND");
                child = (TreeMap<String, ArrayList<ParseObject>>) filterResults.values;
                countries = new ArrayList<>();
                countries.addAll(child.keySet());
                notifyDataSetChanged();
            } else {
                Log.println(Log.INFO, "Results", "-");
                child.clear();
                countries.clear();
                notifyDataSetInvalidated();
            }
        }
    }
}
