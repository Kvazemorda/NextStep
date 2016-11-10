package com.nextstep;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.nextstep.entity.PersonEntity;
import com.nextstep.service.AdapterPerson;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<PersonEntity> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new HttpRequestPerson().execute();
    }


    public void fillListView(PersonEntity personEntity){
        persons.add(personEntity);
        ListView listView = (ListView) findViewById(R.id.tasksList);
        AdapterPerson adapterPerson = new AdapterPerson(this, persons);
        listView.setAdapter(adapterPerson);
    }

    /**
     * Get object from service
     */
    public class HttpRequestPerson extends AsyncTask<Void, Void, PersonEntity> {
        @Override
        protected PersonEntity doInBackground(Void... params) {
            final String url = "http://192.168.0.105:8080/persons";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            PersonEntity personEntity = restTemplate.getForObject(url, PersonEntity.class);

            return personEntity;
        }

        @Override
        protected void onPostExecute(PersonEntity personEntity) {
            fillListView(personEntity);
        }
    }
}
