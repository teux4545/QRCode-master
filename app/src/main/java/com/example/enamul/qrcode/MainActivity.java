package com.example.enamul.qrcode;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.enamul.getDati.Dati;
import com.example.enamul.getDati.asyncTaskDeleteTemp;
import com.example.enamul.getDati.asyncTaskGetDati;
import com.example.enamul.getDati.asyncTaskPutTemporaryList;
import com.example.enamul.listStructure.listStruct;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button btnScan;
    Button btnEmpty;

    TextView tv_qr_readTxt;
    TextView tv_qr_readTxt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new asyncTaskGetDati().execute();

        btnScan = (Button)findViewById(R.id.btnScan);
        btnEmpty = (Button)findViewById(R.id.btnEmpty);
        tv_qr_readTxt = (TextView) findViewById(R.id.tv_qr_readTxt);
        tv_qr_readTxt2 = (TextView) findViewById(R.id.tv_qr_readTxt2);


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

        btnEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setPrompt("Empty");
                new asyncTaskDeleteTemp().execute();
                Toast.makeText(getApplicationContext(), "Tabella svuotata Correttamente!", Toast.LENGTH_LONG).show();

            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        listStruct control = new listStruct();
        Dati dates = new Dati();
        int cnt = 1;

        if(result != null) {
            if(result.getContents() == null) {
                Log.e("Scan*******", "Cancelled scan");

            }
            else {
                Log.e("Scan", "Scanned"); //OMETTIBILE

                LinearLayout bgElement = findViewById(R.id.margin);
                String f = (String)result.getContents();
                boolean check = true;

                    if (control.contain(f, dates.getDati())) {
                        //list.add(f);
                            new asyncTaskPutTemporaryList().execute(f);

                        for(String elem : dates.getDatiTemp()){

                            if(elem!=null && elem.equals(f)) {
                            cnt += 1;
                            }

                            if(cnt>=2){
                            String[] currentElement = dates.getDati();
                            String element = currentElement[control.getI()];
                            String[] parts = element.split(" ");

                            tv_qr_readTxt.setText("Nome: " + parts[1] + "\nCognome: " + parts[2] + "\nGIA' SCANSIONATO!");
                            bgElement.setBackgroundColor(Color.parseColor("#ffcc00"));
                            Toast.makeText(this, "Esito: " + "Verificare", Toast.LENGTH_LONG).show();
                            tv_qr_readTxt2.setText("");
                            tv_qr_readTxt2.setBackgroundColor(Color.parseColor("#ffcc00"));
                            check = false;
                            break;
                            }
                        }
                    }

                if(cnt==1){
                    String[] currentElement = dates.getDati();
                    String element = currentElement[control.getI()];
                    String[] parts = element.split(" ");
                    Integer n = 0;
                    n = Integer.parseInt(parts[3]);
                    bgElement.setBackgroundColor(Color.parseColor("#00e600"));

                    switch(n){
                        case 60:
                            tv_qr_readTxt2.setText("Pagamento totale avvenuto!");
                            tv_qr_readTxt2.setBackgroundColor(Color.parseColor("#00e600"));
                            break;
                        case 10:
                            tv_qr_readTxt2.setText("Parzialmente pagato");
                            tv_qr_readTxt2.setBackgroundColor(Color.parseColor("#ffcc00"));
                            break;
                        case 0:
                            tv_qr_readTxt2.setText("Pagamento non pervenuto!");
                            tv_qr_readTxt2.setBackgroundColor(Color.parseColor("#ff1a1a"));
                            break;
                    }

                    tv_qr_readTxt.setText("CODICE TROVATO!\nNome: " + parts[1] + "\nCognome: " + parts[2]);
                    Toast.makeText(this, "Esito: " + "PRESENTE IN LISTA!", Toast.LENGTH_LONG).show();
                    check = false;
                }

                if(check) {
                    tv_qr_readTxt2.setText("");
                    tv_qr_readTxt2.setBackgroundColor(Color.parseColor("#ff1a1a"));
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
