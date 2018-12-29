package barcodescanner20.noriantech.com.mx.barcodescanner20;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView scannerView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScannerQR(v);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    private void ScannerQR(View v) {
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(result.getText().contains("https") || result.getText().contains("http"))
            openURL(result.getText());
        else {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("result", result.getText());
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);
        scannerView.stopCamera();
    }

    public void openURL(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }
}
