package com.nextstep;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.nextstep.constant.Constans;
import com.nextstep.entity.BalanceEntity;
import com.nextstep.entity.PersonEntity;
import com.nextstep.entity.TaskEntity;
import com.nextstep.service.AdapterTasks;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected List<TaskEntity> taskEntityList = new ArrayList<>();
    PersonEntity ilya = new PersonEntity(1, "Valya", "ilyavanavara@mail.com");
    protected String login = "Valya";
    private Date currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new HttpRequestTasks().execute();
    }

    public void fillListView(){
        ListView listView = (ListView) findViewById(R.id.tasksList);
        AdapterTasks adapterTasks = new AdapterTasks(this, taskEntityList);
        listView.setAdapter(adapterTasks);
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
                    .queryParam("personEntity", ilya)
                    .build()
                    .toUri();
            System.out.println(url.toString());
            TaskEntity[] tasks = restTemplate.getForObject(url, TaskEntity[].class);

            return tasks;
        }

        @Override
        protected void onPostExecute(TaskEntity[] task) {
            taskEntityList = Arrays.asList(task);
            fillListView();
        }
    }

    /**
     * Get Balance
     */
/*    public class HttpRequestBalance extends AsyncTask<Void, Void, BalanceEntity>{
        @Override
        protected BalanceEntity doInBackground(Void... voids) {
            currentDate = getCurrentDate();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            URI url = UriComponentsBuilder.fromUriString(Constans.HOST)
                    .path(Constans.BALANCE)
                    .queryParam(balance, )
            return null;
        }
    }
*/
    protected Date getCurrentDate(){
        return new Date();
    }
}
