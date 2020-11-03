package com.example.studentsproject3intentsandlisteners;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyVocationalStudiesActivity extends MainMenu {
    private ListView listView;
    private MyAdapter asignaturas_adapter;
    private List<HashMap<String,Object>> asignaturas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_vocational_studies);

        listView = findViewById(R.id.asignaturas_listview);
        asignaturas = new ArrayList<>();

        /*asignaturas_adapter = new SimpleAdapter(MyVocationalStudiesActivity.this,
                                                asignaturas,
                                                R.layout.item_list_my_vocational,
                                                new String[] {"asignatura"},
                                                new int[] {R.id.asignatura_check_box} );*/
        Intent i = getIntent();

        asignaturas_adapter = new MyAdapter(MyVocationalStudiesActivity.this,i.getExtras().getStringArray("asignaturas"));
        listView.setAdapter(asignaturas_adapter);


        try {
            String[] asignaturas = i.getExtras().getStringArray("asignaturas");
            for(String asignatura : asignaturas) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("asignatura",asignatura);
                this.asignaturas.add(map);
            }
            //asignaturas_adapter.notifyDataSetChanged();
        } catch(NullPointerException e) {
            finish();
        }

        findViewById(R.id.button_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String[] array = asignaturas_adapter.getChecked();
                intent.putExtra("elecciones",array);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        System.out.println(listView.getAdapter().getCount());
        for (int n = 0 ; n < listView.getAdapter().getCount() ; n++) {
            Object v = asignaturas_adapter.getItem(n);
           // asignaturas_adapter.getView(i,)
            System.out.println(v.getClass().getCanonicalName());
        }
    }

}