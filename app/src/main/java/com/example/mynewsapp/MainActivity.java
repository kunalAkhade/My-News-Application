package com.example.mynewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mynewsapp.topheadlines.TopHeadlinesAdapter;
import com.example.mynewsapp.topheadlines.TopHeadlinesModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
private RecyclerView topHeadlinesRecyclerView;
private TopHeadlinesAdapter topHeadlinesAdapter;
private List<TopHeadlinesModel> topHeadlinesModelList =new ArrayList<>();

private String apiKey="695137f546454d4494eb7b448f2acd2c";

//private String topheadlinesURL="https://newsapi.org/v2/everything?q=tesla&from=2021-11-01&sortBy=publishedAt&apiKey="+apiKey;
private String topheadlinesURL="https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey="+apiKey;
private int per_page=100;
private int pageNumber=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topHeadlinesRecyclerView=findViewById(R.id.top_headlines_recyclerview);

        //topHeadlinesModelList.add(new TopHeadlinesModel("Fresh from a $10M round, Plan A launches SaaS tool for ESG reporting aimed at startups/VCs","With key ESG reporting regulations such as the EU Taxonomy and the Sustainable Finance Disclosure Regulation (SFDR) covering more than 75 percent of European companies, across the EU and the UK, the regulatory environment is evolving fast. Non-financial data,…","IndiaNews"));
        //topHeadlinesModelList.add(new TopHeadlinesModel("Ex-Apple / Tesla Leaders Raise $1,000,000 in 1 Hour From Retail Investors, On Track to Release World’s First Artificial Human Arm","SAN FRANCISCO--(BUSINESS WIRE)--Ex-Apple / Tesla Leaders Raise $1,000,000 in 1 Hour From Retail Investors, On Track to Release World’s First Artificial Human Arm.","TechCrunch"));
        //topHeadlinesModelList.add(new TopHeadlinesModel("Stocks Fall After Powell's Taper Comments - The New York Times","The Federal Reserve chair, Jerome Powell, said persistent inflation might require a more aggressive approach by the central bank. Wall Street was already uneasy.","TOI"));
        //topHeadlinesModelList.add(new TopHeadlinesModel(" SaaS tool for ESG reporting aimed at startups/VCs"," regulations such as the EU Taxonomy and the Sustainable Finance Disclosure Regulation (SFDR) covering more than 75 percent of European companies, across the EU and the UK, the regulatory environment is evolving fast. Non-financial data,…","TechCrunch"));
        //topHeadlinesModelList.add(new TopHeadlinesModel(" Round Plan A launches SaaS tool for ESG reporting aimed at startups/VCs","With key ESG reporting regulations such as the EU Taxonomy and the Sustainable Finance Disclosure Regulation (SFDR) covering more than 75 percent of European companies, across the EU and the UK, the regulatory environment is evolving fast. Non-financial data,…","TOI"));
        //topHeadlinesModelList.add(new TopHeadlinesModel("Fresh from a $10M round, Plan A launches SaaS tool for ESG reporting aimed at startups/VCs"," EU Taxonomy and the Sustainable Finance Disclosure Regulation (SFDR) covering more than 75 percent of European companies, across the EU and the UK, the regulatory environment is evolving fast. Non-financial data,…","TIEE"));
         fetchData();
        topHeadlinesAdapter =new TopHeadlinesAdapter(topHeadlinesModelList,this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        topHeadlinesRecyclerView.setLayoutManager(layoutManager);
        topHeadlinesRecyclerView.setAdapter(topHeadlinesAdapter);
        topHeadlinesAdapter.notifyDataSetChanged();



    }

    private void fetchData() {
        StringRequest request = new StringRequest(Request.Method.GET, topheadlinesURL+"&pageSize="+per_page,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        JSONArray jsonArray = jsonObject.getJSONArray("articles");
                        int length = jsonArray.length();

                        for (int i = 0; i < length; i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            JSONObject s1 = jsonObject1.getJSONObject("source");
                            String source = s1.getString("name");


                            String title = jsonObject1.getString("title");

                            String desp = jsonObject1.getString("description");

                            String Link= jsonObject1.getString("url");

                            topHeadlinesModelList.add(new TopHeadlinesModel(title, desp, source,Link));
                        }
                        topHeadlinesAdapter.notifyDataSetChanged();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(MainActivity.this, "Data Not Found !!!", Toast.LENGTH_SHORT).show()) {
              @Override
              public Map<String,String> getHeaders() {
                  Map<String,String> map=new HashMap<>();
                  map.put("User-Agent","Mozilla/5.0");
                  return map;
              }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

}