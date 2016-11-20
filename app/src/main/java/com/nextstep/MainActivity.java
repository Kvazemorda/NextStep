package com.nextstep;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.nextstep.activity.NewTaskActivity;
import com.nextstep.constant.Constans;
import com.nextstep.data.ContractData;
import com.nextstep.entity.BalanceEntity;
import com.nextstep.entity.PersonEntity;
import com.nextstep.entity.TaskEntity;
import com.nextstep.service.AdapterTasks;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected List<TaskEntity> taskEntityList = new ArrayList<>();
    PersonEntity personEntity = new PersonEntity(1, "Valya", "ilyavanavara@mail.com");
    protected Long id = 1l;
    private Long currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContractData contractData = new ContractData();
        new HttpRequestTasks().execute();
        new HttpRequestBalance().execute();
    }

    public void fillListView(){
        ListView listView = (ListView) findViewById(R.id.tasksList);
        AdapterTasks adapterTasks = new AdapterTasks(this, taskEntityList);
        listView.setAdapter(adapterTasks);
    }

    public void fillCFTextView(String text){
        TextView textView = (TextView) findViewById(R.id.cashflow);
        textView.setText(text);
    }

    /**
     * Get object from service
     */
    public class HttpRequestTasks extends AsyncTask<Void, Void, TaskEntity[]> {
        @Override
        protected TaskEntity[] doInBackground(Void... params) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            URI url = UriComponentsBuilder.fromUriString(Constans.HOST)
                    .path(Constans.LIST_TASKS)
                    .queryParam("login", id)
                    .build()
                    .toUri();

            TaskEntity[] tasks = restTemplate.getForObject(url, TaskEntity[].class);

            return tasks;
        }

        @Override
        protected void onPostExecute(TaskEntity[] task) {
            //Обязательно передать в встроенную базу данных и потом из нее брать данные для приложения
            taskEntityList = Arrays.asList(task);
            fillListView();
        }
    }

    /**
     * Get Balance
     */
    public class HttpRequestBalance extends AsyncTask<Void, Void, BalanceEntity>{
        @Override
        protected BalanceEntity doInBackground(Void... voids) {
            currentDate = getCurrentDate();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            URI url = UriComponentsBuilder.fromUriString(Constans.HOST)
                    .path(Constans.BALANCE)
                    .queryParam("login",id)
                    .queryParam("date", getCurrentDate())
                    .build()
                    .toUri();
            System.out.println(url.toString());
            BalanceEntity balanceEntity = restTemplate.getForObject(url, BalanceEntity.class);
            return balanceEntity;
        }

        @Override
        protected void onPostExecute(BalanceEntity balanceEntity) {
            fillCFTextView(String.valueOf(balanceEntity.getBalance()));
        }
    }


    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);
        startActivity(intent);
    }

    protected long getCurrentDate(){
        return new Date().getTime();
    }
}
