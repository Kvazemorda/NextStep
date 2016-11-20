package com.nextstep.data;


import android.os.Bundle;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.nextstep.R;
import com.nextstep.entity.PersonEntity;

import java.util.List;

public class ContractData extends OrmLiteBaseActivity<DatabaseHelper> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doSomething();
    }

    private void doSomething(){
        RuntimeExceptionDao<PersonEntity, Integer> person = getHelper().getSimpleDataDao();
        List<PersonEntity> listPerson = person.queryForAll();
        TextView textView = (TextView) findViewById(R.id.cashflow);
        textView.setText(listPerson.get(0) + "");
    }
}
