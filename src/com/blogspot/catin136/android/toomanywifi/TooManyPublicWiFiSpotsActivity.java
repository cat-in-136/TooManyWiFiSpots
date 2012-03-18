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

import android.app.Activity;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

/**
 * The main activity of the application.
 * 
 * @author cat_in_136
 */
public class TooManyPublicWiFiSpotsActivity extends Activity {
    /** A list of scan-result Wi-Fi AP information. */
    private List<WiFiSpotsInfo> spotsInfos = null;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        updateSpotsListView();
    }
    
    /** Called when the menu button is pushed. */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.mainmenu, menu);
	return true;
    }
    
    /** Called when the menu item is selected. */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
	case R.id.updatemenuitem:
	    updateSpotsListView();
	    break;
	case R.id.copymenuitem:
	    copyDataToClipboard();
	    break;
	default:
	    break;
	}
        return false;
    }

    /** Updates Wi-Fi AP list and its View. */
    private void updateSpotsListView() {
	spotsInfos = WiFiSpotsInfo.getScanResults(this);
	assert(spotsInfos != null);

	// Update the ListView.
	WiFiSpotsInfoArrayAdapter adapter = new WiFiSpotsInfoArrayAdapter(this, spotsInfos);
	ListView spotsListView = (ListView) findViewById(R.id.spotsListView);
	spotsListView.setAdapter(adapter);

	// No AP indicates an error message with Toast.
	if (spotsInfos.size() == 0) {
	    int messageId = R.string.not_found_wifi_ap;
	    
	    if (! WiFiSpotsInfo.isWiFiEnabled(this)) {
		messageId = R.string.wifi_disabled;
	    }
	    Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
	}
    }
    
    /** Copy Wi-Fi AP list data to the clip board. */
    private void copyDataToClipboard() {
        ClipboardManager board = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
	StringBuilder sb = new StringBuilder();
	
	if (spotsInfos != null) {
	    for (WiFiSpotsInfo info : spotsInfos) {
		// "(" COUNT ")" SSID LF
		sb.append("(");
		sb.append(info.getCount());
		sb.append(") ");
		sb.append(info.getSSID());
		sb.append("\n");
	    }
	}
	
	board.setText(sb.toString());
    }
}