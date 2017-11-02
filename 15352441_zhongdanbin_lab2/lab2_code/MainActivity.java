package cn.edu.sysu.lab2;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.app.AlertDialog.Builder;
import java.lang.String;
import android.os.Handler;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends AppCompatActivity {
    ImageView mImage;
    TextInputLayout mUser;
    TextInputLayout mPassword;
    RadioGroup mRadioG;
    RadioButton mStudent;
    RadioButton mTeacher;
    Button mLogin;
    Button mRegist;
    EditText mUser_edit;
    EditText mPassword_edit;


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
        mUser_edit = (EditText)findViewById(R.id.editText);
        mPassword_edit = (EditText)findViewById(R.id.editText2);


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

        //输入学号、密码提示
        mUser.setHint("请输入学号");
        mPassword.setHint("请输入密码");

        //学生、教职工选择 ,radioGroup用setOnCheckedChangeListener
        mRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup RG,int id){
                if(id == mStudent.getId()){
                    Snackbar.make(mRadioG,"您选择了学生",Snackbar.LENGTH_SHORT)
                            .setAction("确定",new View.OnClickListener(){
                                @Override
                                public void onClick(View view){
                                    Toast.makeText(MainActivity.this,"Snackbar 的确定按钮被点击了",Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                    mRegist.setOnClickListener(new Button.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Snackbar.make(mRegist,"学生注册功能尚未启用",Snackbar.LENGTH_SHORT)
                                    .setAction("确定",new View.OnClickListener(){
                                        @Override
                                        public void onClick(View view){}
                                    })
                                    .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                                    .setDuration(5000)
                                    .show();
                        }
                    });
                }
                else if(id == mTeacher.getId()){
                    Snackbar.make(mRadioG,"您选择了教职工",Snackbar.LENGTH_SHORT)
                            .setAction("确定",new View.OnClickListener(){
                                @Override
                                public void onClick(View view){
                                    Toast.makeText(MainActivity.this,"Snackbar 的确定按钮被点击了",Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                    mRegist.setOnClickListener(new Button.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Toast.makeText(MainActivity.this,"教职工注册功能尚未启用",Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
                }
            }
        });

        //点击登录按钮，依次判断学号是否为空、密码是否为空、学号密码是否正确（123456，6666）
        mLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String number = mUser_edit.getText().toString();
                String password = mPassword_edit.getText().toString();
                if(TextUtils.isEmpty(number)){
                    mUser.setErrorEnabled(true);
                    mUser.setError("学号不能为空");
                }
                else if(TextUtils.isEmpty(password)){
                    mPassword.setErrorEnabled(true);
                    mPassword.setError("密码不能为空");
                    mUser.setErrorEnabled(false);
                }
                else if(number.equals("123456") && password.equals("6666")){
                    mPassword.setErrorEnabled(false);
                    Snackbar.make(mLogin,"登录成功",Snackbar.LENGTH_SHORT)
                            .setAction("确定",new View.OnClickListener(){
                                @Override
                                public void onClick(View view){}
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }
                else{
                    mPassword.setErrorEnabled(false);
                    Snackbar.make(mLogin,"学号或密码错误",Snackbar.LENGTH_SHORT)
                            .setAction("确定",new View.OnClickListener(){
                                @Override
                                public void onClick(View view){}
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }
            }
        });
    }

}








