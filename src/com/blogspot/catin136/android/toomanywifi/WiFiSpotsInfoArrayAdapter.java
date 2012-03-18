/* Copyright (c) 2012 cat_in_136
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.blogspot.catin136.android.toomanywifi;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/** A list adapter class for the <code>spotsListView</code>. */
public class WiFiSpotsInfoArrayAdapter extends ArrayAdapter<WiFiSpotsInfo> {

    /** Constructor with the context and Wi-Fi AP list. */
    public WiFiSpotsInfoArrayAdapter(Context context, List<WiFiSpotsInfo> list) {
	super(context, 0, list);
    }
    
    /** Get view of the row. */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.spotslistitem, null);
	}
        assert(convertView != null);
        
        WiFiSpotsInfo item = getItem(position);
        if (item != null) {
            TextView spotsCount = (TextView) convertView.findViewById(R.id.spotsCounttext);
            spotsCount.setText(Integer.toString(item.getCount()));
            
	    TextView spotsSSID = (TextView) convertView.findViewById(R.id.spotsSSIDtext);
	    spotsSSID.setText(item.getSSID());
	    
	    convertView.setClickable(false);
	} else {
	    // do nothing.
	}
        return convertView;
    }
}
