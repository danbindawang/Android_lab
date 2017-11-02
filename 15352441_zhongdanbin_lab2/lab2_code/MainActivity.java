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


        //ͼƬ��Ϣ
        //����AlertDialog.Builder����
        final AlertDialog.Builder mbuilder = new AlertDialog.Builder(this);
        //���öԻ�����Ŀ
        final String[] items = new String[]{"������ϴ�","����"};
        //��mImage�¼�����
        mImage.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v){
                mbuilder.setTitle("�ϴ�ͷ��")
                        .setItems(items,new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface,int i){
                                Toast.makeText(MainActivity.this,"��ѡ����"+items[i],Toast.LENGTH_SHORT)
                                .show();
                            }
                        })
                        .setNegativeButton("ȡ��",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface,int i){
                                Toast.makeText(MainActivity.this,"��ѡ����ȡ��",Toast.LENGTH_SHORT)
                                 .show();
                            }
                        });
                mbuilder.create().show();
            }
        });

        //����ѧ�š�������ʾ
        mUser.setHint("������ѧ��");
        mPassword.setHint("����������");

        //ѧ������ְ��ѡ�� ,radioGroup��setOnCheckedChangeListener
        mRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup RG,int id){
                if(id == mStudent.getId()){
                    Snackbar.make(mRadioG,"��ѡ����ѧ��",Snackbar.LENGTH_SHORT)
                            .setAction("ȷ��",new View.OnClickListener(){
                                @Override
                                public void onClick(View view){
                                    Toast.makeText(MainActivity.this,"Snackbar ��ȷ����ť�������",Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                    mRegist.setOnClickListener(new Button.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Snackbar.make(mRegist,"ѧ��ע�Ṧ����δ����",Snackbar.LENGTH_SHORT)
                                    .setAction("ȷ��",new View.OnClickListener(){
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
                    Snackbar.make(mRadioG,"��ѡ���˽�ְ��",Snackbar.LENGTH_SHORT)
                            .setAction("ȷ��",new View.OnClickListener(){
                                @Override
                                public void onClick(View view){
                                    Toast.makeText(MainActivity.this,"Snackbar ��ȷ����ť�������",Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                    mRegist.setOnClickListener(new Button.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Toast.makeText(MainActivity.this,"��ְ��ע�Ṧ����δ����",Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
                }
            }
        });

        //�����¼��ť�������ж�ѧ���Ƿ�Ϊ�ա������Ƿ�Ϊ�ա�ѧ�������Ƿ���ȷ��123456��6666��
        mLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String number = mUser_edit.getText().toString();
                String password = mPassword_edit.getText().toString();
                if(TextUtils.isEmpty(number)){
                    mUser.setErrorEnabled(true);
                    mUser.setError("ѧ�Ų���Ϊ��");
                }
                else if(TextUtils.isEmpty(password)){
                    mPassword.setErrorEnabled(true);
                    mPassword.setError("���벻��Ϊ��");
                    mUser.setErrorEnabled(false);
                }
                else if(number.equals("123456") && password.equals("6666")){
                    mPassword.setErrorEnabled(false);
                    Snackbar.make(mLogin,"��¼�ɹ�",Snackbar.LENGTH_SHORT)
                            .setAction("ȷ��",new View.OnClickListener(){
                                @Override
                                public void onClick(View view){}
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }
                else{
                    mPassword.setErrorEnabled(false);
                    Snackbar.make(mLogin,"ѧ�Ż��������",Snackbar.LENGTH_SHORT)
                            .setAction("ȷ��",new View.OnClickListener(){
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








