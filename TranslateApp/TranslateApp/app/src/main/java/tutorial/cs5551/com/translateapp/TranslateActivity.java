package tutorial.cs5551.com.translateapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.AdapterView.OnItemSelectedListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TranslateActivity extends AppCompatActivity implements OnItemSelectedListener{

    String API_URL = "https://api.fullcontact.com/v2/person.json?";
    String API_KEY = "b29103a702edd6a";
    String sourceText;
    TextView outputTextView;
    String x,y;
    Context mContext;
    HashMap<String,String> languagelist=new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        outputTextView = (TextView) findViewById(R.id.txt_Result);
        languagelist.put("telugu","te");languagelist.put("azerbaijan","az");languagelist.put("german","ge");languagelist.put("arabic","ar");languagelist.put("dutch","nl");languagelist.put("bengali","bn");languagelist.put("greek","el");languagelist.put("irish","ga");languagelist.put("russian","ru");languagelist.put("italian","it");
        languagelist.put("thai","th");languagelist.put("chinese","zh");languagelist.put("gujarati","gu");languagelist.put("spanish","es");languagelist.put("french","fr");
        List<String> items= new ArrayList<String>(languagelist.keySet());
        Spinner menu=(Spinner) findViewById(R.id.selectlanguage);
        ArrayAdapter<String> languages=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,items);
        languages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu.setAdapter(languages);
        menu.setOnItemSelectedListener(this);
    }

    public void translateText(View v) {
        TextView sourceTextView = (TextView) findViewById(R.id.txt_Email);

        sourceText = sourceTextView.getText().toString();
        String getURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?" +
                "key=trnsl.1.1.20151023T145251Z.bf1ca7097253ff7e." +
                "c0b0a88bea31ba51f72504cc0cc42cf891ed90d2&text=" + sourceText +"&" +
                    "lang=en-"+y+"&[format=plain]&[options=1]&[callback=set]";//The API service URL
        final String response1 = "";
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder()
                    .url(getURL)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JSONObject jsonResult;
                    final String result = response.body().string();
                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray convertedTextArray = jsonResult.getJSONArray("text");
                        final String convertedText = convertedTextArray.get(0).toString();
                        Log.d("okHttp", jsonResult.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                outputTextView.setText(convertedText);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception ex) {
            outputTextView.setText(ex.getMessage());

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        x=adapterView.getItemAtPosition(i).toString();
        y=languagelist.get(x).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
public void logout(View v){
        Intent back=new Intent(TranslateActivity.this,LoginActivity.class);
        startActivity(back);
}
}
