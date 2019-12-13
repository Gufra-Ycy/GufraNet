package com.gufra.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gufra.net.rxnetimp.IResultCallback;
import com.gufra.net.rxnetimp.NetManager;
import com.gufra.net.rxnetimp.R;
import com.google.android.gms.plus.PlusOneButton;

/**
 *
 */
public class JokeFragment extends Fragment {
    private static String TAG = "gufra.JokeFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int MSG_JOKE = 1001;

    private static Message msg;
    private static TextView mTxtJoke;
    private static final String JOKE_URL = "https://api.lovelive.tools/api/SweetNothings/Serialization/:serializationType";

    public JokeFragment() {
        // Required empty public constructor
    }

    public static JokeFragment newInstance(String param1, String param2) {
        JokeFragment fragment = new JokeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        new mThead().run();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onActivityCreated");
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach");
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.e(TAG,"msg"+msg.what+msg.obj);

            mTxtJoke = (TextView)getActivity().findViewById(R.id.txt_joke);
            switch (msg.what){
                case MSG_JOKE:
                    Log.e(TAG,"消息"+msg.obj.toString());
                    mTxtJoke.setText(msg.obj.toString());
                    break;
                    default:break;
            }
        }
    };

    private class mThead extends Thread{

        @Override
        public void run() {
            NetManager.getInstance(getActivity()).get2Volley(JOKE_URL, null, new IResultCallback() {
                @Override
                public void successed(String request) {
                    msg = mHandler.obtainMessage();
                    msg.what = MSG_JOKE;
                    msg.obj = request;
                    msg.sendToTarget();
                }

                @Override
                public void failed(String message) {
                    msg = mHandler.obtainMessage();
                    msg.what = MSG_JOKE;
                    msg.obj = message;
                    msg.sendToTarget();
                }
            });
        }
    }
}
