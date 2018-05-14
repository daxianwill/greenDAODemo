package top.daxianwill.greendaodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.greendao.gen.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 */
public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<User> mUserList = new ArrayList<>();
    private MyAdapter mAdapter;
    private User mUser;
    private UserDao mUserDao;
    private long id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.list_view);
        mAdapter = new MyAdapter(this,mUserList);
        mListView.setAdapter(mAdapter);
        mUserDao = MyApplication.getInstances().getDaoSession().getUserDao();
        mUserDao.deleteAll();
    }

    /**
     * 增
     * @param view view
     */
    public void insert(View view){
        mUser = new User(id++,"any"+id);
        mUserDao.insert(mUser);
        notifyListView();
    }

    /**
     * 删
     * @param view view
     */
    public void delete(View view){
        long l = mUserDao.count() - 1;
        mUserDao.deleteByKey(l);
        notifyListView();
    }

    /**
     * 改
     * @param view view
     */
    public void update(View view){
        mUser = new User((long)3,"any0803");
        mUserDao.update(mUser);
        notifyListView();
    }

    /**
     * 查
     * @param view view
     */
    public void loadAll(View view){
        mUserList = mUserDao.loadAll();
        notifyListView();
    }

    /**
     * 跟新ListView,不知为啥adapter.notifyDataSetChanged()没反应
     */
    public void notifyListView(){
        mUserList.clear();
        mUserList = mUserDao.loadAll();
        mAdapter = new MyAdapter(MainActivity.this,mUserList);
        mListView.setAdapter(mAdapter);
    }

}
