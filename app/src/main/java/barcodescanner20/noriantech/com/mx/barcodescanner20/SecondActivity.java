package barcodescanner20.noriantech.com.mx.barcodescanner20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        resultText = (TextView) findViewById(R.id.result_text);
        String scanResult = getIntent().getStringExtra("result");
        resultText.setText(scanResult);
    }
}
