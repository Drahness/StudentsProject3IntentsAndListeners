package com.example.studentsproject3intentsandlisteners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyIntentsActivity extends MainMenu {
    private ListView courses_view;
    private ListView seleccionados_listview;
    private SimpleAdapter adapter_courses;
    private List<HashMap<String, Object>> values_cursos;
    private List<HashMap<String, Object>> values_asignaturas;
    private SimpleAdapter adapter_asignaturas;
    private String[] cursos;
    private String[] dam1;
    private String[] dam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_intents);
        courses_view = findViewById(R.id.cursos_listview);
        seleccionados_listview = findViewById(R.id.seleccionados_listview);

        values_cursos = new ArrayList<>();
        values_asignaturas = new ArrayList<>();
        cursos = new String[]{getString(R.string.dam1), getString(R.string.dam2)};
        dam1 = getResources().getStringArray(R.array.dam1_array);
        dam2 = getResources().getStringArray(R.array.dam2_array);

        for (String value : cursos) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("curso", value);
            values_cursos.add(map);
        }

        adapter_courses = new SimpleAdapter(MyIntentsActivity.this,
                values_cursos, // El mapeado,
                R.layout.item_list, // El objeto dentro de la listview a ir clonando
                new String[]{"curso"}, // las keys dentro del mapa,
                new int[]{R.id.item_text} // el objeto del texto.
        );
        adapter_asignaturas = new SimpleAdapter(MyIntentsActivity.this,
                values_asignaturas,
                R.layout.item_list,
                new String[]{"asignatura"},
                new int[]{R.id.item_text}
        );

        courses_view.setAdapter(adapter_courses);
        seleccionados_listview.setAdapter(adapter_asignaturas);
        courses_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(view.getClass().getCanonicalName());
                System.out.println();
                String curso = cursos[(int) id];
                Bundle b = new Bundle();

                Intent i = new Intent();
                if (curso.equals(getString(R.string.dam1))) {
                    b.putStringArray("asignaturas", dam1);
                } else if (curso.equals(getString(R.string.dam2))) {
                    b.putStringArray("asignaturas", dam2);
                } else {
                    throw new RuntimeException("Not programmed.");
                }
                i.putExtras(b);
                i.setClass(MyIntentsActivity.this, MyVocationalStudiesActivity.class);
                startActivityForResult(i, 1, b);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(requestCode + " " + resultCode);
        if (requestCode == 1) { // The code sended by the other activity
            findViewById(R.id.seleccionados_listview).setVisibility(View.VISIBLE);
            findViewById(R.id.textview_seleccionados).setVisibility(View.VISIBLE);
            System.out.println("aaaa");
            if (resultCode == RESULT_OK) {
                String[] returningArray = data.getStringArrayExtra("elecciones");
                for (String eleccion : returningArray) {
                    System.out.println(eleccion);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("asignatura", eleccion);
                    if (!values_asignaturas.contains(map)) {
                        values_asignaturas.add(map);
                    }
                }
                adapter_asignaturas.notifyDataSetChanged();
            } else {
                System.out.println("looooooooooooooooooooooooool");
            }
        }
    }
}