package com.example.eltonjosedesouza.json;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;


public class MainActivity extends Activity {

    public Spinner estado;
    public AutoCompleteTextView cidade;
    CidadeEstadoModel cidadeEstadoModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        estado = (Spinner) findViewById(R.id.estado);
        cidade = (AutoCompleteTextView) findViewById(R.id.cidade);
        CustomRequest request = new CustomRequest(Request.Method.GET,
                "http://api.myjson.com/bins/4d80x",
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        cidadeEstadoModel = new Gson().fromJson(response.toString(),
                                CidadeEstadoModel.class);
                        CustomArrayAdapter customArrayAdapter =
                                new CustomArrayAdapter(MainActivity.this,
                                        R.layout.list_item_estado,
                                        cidadeEstadoModel.getEstados());
                        estado.setAdapter(customArrayAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        cidadeEstadoModel.getEstados().get(position).getCidades());
                cidade.setAdapter(arrayAdapter);
                cidade.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cidade.setEnabled(false);
            }
        });
        MyVolley.getRequestQueue().add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
