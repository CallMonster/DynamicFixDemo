package tjabc.tj.com.dynamicfixdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import tjabc.tj.com.dynamicfixdemo.R;
import tjabc.tj.com.dynamicfixdemo.mtest.T_Input_New;
import tjabc.tj.com.dynamicfixdemo.mtest.T_Input_Old;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "euler";
    @BindView(R.id.testBtn) Button testBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        testBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.testBtn:
                Toast.makeText(this, T_Input_New.old("已经修复"),Toast.LENGTH_SHORT).show();
                break;
        }
    }



}
