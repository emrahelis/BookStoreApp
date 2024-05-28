package com.example.bookstoreapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtKitapAdi;
    private Button btnEkle;
    private DatabaseManager dbManager;

    private ListView listView;

    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtKitapAdi = findViewById(R.id.edtKitapAdi);
        btnEkle = findViewById(R.id.btnEkle);
        listView = findViewById(R.id.listView);
        dbManager = new DatabaseManager(this);
        dbManager.open();

        List<String> kitaplar = dbManager.getAllKitaplar();
        dbManager.close();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, kitaplar);
        listView.setAdapter(adapter);

        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kitapAdi = edtKitapAdi.getText().toString();
                if (!kitapAdi.isEmpty()) {
                    dbManager.open();
                    dbManager.addKitap(kitapAdi);
                    dbManager.close();
                    edtKitapAdi.setText("");
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}