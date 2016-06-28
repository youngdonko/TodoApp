package com.example.youngdon.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText txtItem;
    Button btn;
    ListView listView;

    ArrayList<String> todoList; //this is a data source
    ArrayAdapter<String> aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtItem = (EditText)findViewById(R.id.editText1);
        btn = (Button)findViewById(R.id.button1);
        listView = (ListView)findViewById(R.id.listView1);

        btn.setOnClickListener(this);

        // ADAPTER
        todoList = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoList);
        listView.setAdapter(aa);


        // Edit item update
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                todoList.remove(position);
                addItem(txtItem.getText().toString());
                aa.notifyDataSetChanged();

            }
        });


        // SET SELECTED ITEM (delete item with one click)
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //txtItem.setText(todoList.get(position));
//                todoList.remove(position);
//                aa.notifyDataSetChanged();
//
//            }
//        });


        // SET SELECTED ITEM (DELETE ITEM WITH ONE LONG CLICK)
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //txtItem.setText(todoList.get(position));
                todoList.remove(position);
                aa.notifyDataSetChanged();
                return true;
            }
        });


    }




    @Override
    public void onClick(View v) {
        if(v == this.btn)
        {
            // to add task to listview
            this.addItem(txtItem.getText().toString());
        }
    }
    private void addItem(String item)
    {
        if (item.length()>0)
        {
            this.todoList.add(item);
            this.aa.notifyDataSetChanged();
            this.txtItem.setText("");
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds to the action bar if it is present.

        return true;
    }





}
