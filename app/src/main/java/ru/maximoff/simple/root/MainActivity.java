package ru.maximoff.simple.root;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		TextView textView = findViewById(R.id.mainTextView1);
		try {
			Process suProcess = Runtime.getRuntime().exec("su");
			DataOutputStream outStream = new DataOutputStream(suProcess.getOutputStream());
            outStream.writeBytes("exit\n");
            outStream.flush();
			int suReturnValue = suProcess.waitFor();
			if (suReturnValue == 0) {
				textView.setText("Root access granted (code " + suReturnValue + ")");
			} else {
				textView.setText("Root access denied (code " + suReturnValue + ")");
			}
		} catch (IOException e) {
			textView.setText("IOException: " + e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			textView.setText("InterruptedException: " + e.getMessage());
			e.printStackTrace();
		}
    }
}
