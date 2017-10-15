package cn.edu.sysu.lab2;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.app.AlertDialog.Builder;
import java.lang.String;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    ImageView mImage;
    TextInputLayout mUser;
    TextInputLayout mPassword;
    RadioGroup mRadioG;
    RadioButton mStudent;
    RadioButton mTeacher;
    Button mLogin;
    Button mRegist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImage  = (ImageView) findViewById(R.id.imageView);
        mUser   = (TextInputLayout)findViewById(R.id.editText_layout);
        mPassword=(TextInputLayout)findViewById(R.id.editText2_layout);
        mRadioG = (RadioGroup)findViewById(R.id.radiogroup);
        mStudent= (RadioButton)findViewById(R.id.button1);
        mTeacher= (RadioButton)findViewById(R.id.button2);
        mLogin  = (Button)findViewById(R.id.button3);
        mRegist = (Button)findViewById(R.id.button4);

        mUser.setHint("请输入学号");
        mPassword.setHint("请输入密码");

        //图片信息
        //创建AlertDialog.Builder对象
        final AlertDialog.Builder mbuilder = new AlertDialog.Builder(this);
        //设置对话框项目
        final String[] items = new String[]{"从相册上传","拍照"};
        //对mImage事件监听
        mImage.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v){
                mbuilder.setTitle("上传头像")
                        .setItems(items,new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface,int i){
                                Toast.makeText(MainActivity.this,"您选择了"+items[i],Toast.LENGTH_SHORT)
                                .show();
                            }
                        })
                        .setNegativeButton("取消",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface,int i){
                                Toast.makeText(MainActivity.this,"您选择了取消",Toast.LENGTH_SHORT)
                                 .show();
                            }
                        });
                mbuilder.create().show();
            }
        });

    }

}








