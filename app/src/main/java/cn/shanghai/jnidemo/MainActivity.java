package cn.shanghai.jnidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GetTextHelper.onTextErrorListener {
    private GetTextHelper getTextHelper;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.judgeEditText);
        if (getTextHelper == null) {
            getTextHelper = new GetTextHelper();
            getTextHelper.setOnTextErrorListener(this);
        }
    }

    public void throwException(View view)  {
        String trim = editText.getText().toString().trim();
        if (judgeStrNotEmpty(trim)) {
            boolean isNormal=true;
            try {
                getTextHelper.judgeStrLenWithException(trim);
            } catch (Exception e) {
                e.printStackTrace();
                isNormal = false;
            }
            if (isNormal) {
                Toast.makeText(this, "是正常人名", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "不是正常人名", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void throwCallback(View view) {
        String trim = editText.getText().toString().trim();
        if (judgeStrNotEmpty(trim)) {
            getTextHelper.judgeStrLenWithCallback(trim);
        }
    }

    private boolean judgeStrNotEmpty(String str) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, "请输入人名", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    public void onError(int code, String msg) {
        Toast.makeText(this, String.format("code is %d\n errorMsg is %s", code, msg), Toast.LENGTH_LONG).show();
    }
}
