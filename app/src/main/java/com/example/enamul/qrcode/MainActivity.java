package com.example.enamul.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enamul.listStructure.listStruct;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;
    Button btnScan;
    EditText editText;
    String EditTextValue ;
    public final static int QRcodeWidth = 350 ;
    Bitmap bitmap ;

    listStruct codes = new listStruct();
    ArrayList<String> list = new ArrayList<>();
    int cnt=0;

    TextView tv_qr_readTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        btnScan = (Button)findViewById(R.id.btnScan);
        tv_qr_readTxt = (TextView) findViewById(R.id.tv_qr_readTxt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(!editText.getText().toString().isEmpty()){
                    EditTextValue = editText.getText().toString();

                    try {
                        bitmap = TextToImageEncode(EditTextValue);

                        imageView.setImageBitmap(bitmap);

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    editText.requestFocus();
                    Toast.makeText(MainActivity.this, "Please Enter Your Scanned Test" , Toast.LENGTH_LONG).show();
                }

            }
        });


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });
    }


    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 350, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.e("Scan*******", "Cancelled scan");

            }
            else {
                Log.e("Scan", "Scanned"); //OMETTIBILE

                LinearLayout bgElement = findViewById(R.id.margin);
                String f = (String)result.getContents();
                boolean check = true;

                if (codes.contain(f)) {
                        list.add(f);
                    for(String elem : list){
                        if(elem.equals(f)) {
                            cnt += 1;
                        }
                        if(cnt>1){
                            tv_qr_readTxt.setText("UTENTE GIA' SCANSIONATO!");
                            bgElement.setBackgroundColor(Color.parseColor("#ffcc00"));
                            Toast.makeText(this, "Esito: " + "Verificare", Toast.LENGTH_LONG).show();
                            check = false;
                            break;
                        }
                    }
                }

                if(cnt==1){
                    tv_qr_readTxt.setText("CODICE TROVATO!");
                    bgElement.setBackgroundColor(Color.parseColor("#00e600"));
                    Toast.makeText(this, "Esito: " + "PRESENTE IN LISTA!", Toast.LENGTH_LONG).show();
                    check = false;
                }

                if(check) {
                    tv_qr_readTxt.setText("CODICE NON TROVATO!");
                    bgElement.setBackgroundColor(Color.parseColor("#ff1a1a"));
                    Toast.makeText(this, "Esito: " + "Utente non presente in lista", Toast.LENGTH_LONG).show();
                }
            }
        }
        else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
