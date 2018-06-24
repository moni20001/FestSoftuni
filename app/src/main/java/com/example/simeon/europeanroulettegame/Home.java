package com.example.simeon.europeanroulettegame;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simeon.europeanroulettegame.entities.models.User;
import com.example.simeon.europeanroulettegame.sockets.ServerClient;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final TextView text = (TextView) findViewById(R.id.textView);
        final TextView bettingAmountText = (TextView) findViewById(R.id.textView8);
        final TextView balanceText = (TextView) findViewById(R.id.textView10);
        final TextView timeText = (TextView) findViewById(R.id.textView13);
        final int[] bettingAmount = {0};
        final ImageButton bet5 = (ImageButton) findViewById(R.id.imageButton);
        final ImageButton bet10 = (ImageButton) findViewById(R.id.imageButton2);
        final ImageButton bet25 = (ImageButton) findViewById(R.id.imageButton3);
        final ImageButton bet50 = (ImageButton) findViewById(R.id.imageButton4);
        final ImageButton bet100 = (ImageButton) findViewById(R.id.imageButton5);

        final Button button = (Button) findViewById(R.id.button2);
        bet5.setOnClickListener(betChip);
        bet10.setOnClickListener(betChip);
        bet25.setOnClickListener(betChip);
        bet50.setOnClickListener(betChip);
        bet100.setOnClickListener(betChip);

        User user = new User("user");

        balanceText.setText(user.getBalance().toString());

           final CountDownTimer timer = new CountDownTimer(10000, 1000) {
               public void onTick(long millisUntilFinished) {
                   timeText.setText(String.valueOf(millisUntilFinished / 1000));
               }
               public void onFinish() {
                   //newSpin();
               }
           };

        timer.start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bettingAmountText.setText(String.valueOf(0));
            }
        });


        try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ServerClient client = new ServerClient("192.168.166.18", 3523);
                            String debug = "";
                            while(true){
                                debug = client.numberInputStream();
                                String arr[] = debug.split("\n");
                                int number = -1;
                                for (int i = 0; i < arr.length; i++) {
                                    if(!(arr[i]==null)){
                                        Integer numb = Integer.valueOf(arr[i]);
                                        number = numb;
                                        System.out.println(numb);
                                    }

                                }
                                //timer.start();
                                text.setText(String.valueOf(number));
                                rouletteRotateAnimation(number);
                                Thread.sleep(100);
                            }
                        } catch (Exception e) {
                        }
                    }
                }).start();
        }  catch (Exception e){}
   }


   public void rouletteRotateAnimation(Integer number){
       CalculateDegree calculateDegree = new CalculateDegree();
       final TextView text = (TextView) findViewById(R.id.textView);
       ImageView rouletteImage = (ImageView) findViewById(R.id.imageView4);
       text.setText(number.toString());
       RotateAnimation rotate = new RotateAnimation(0,calculateDegree.getDegreeByNumber(number)+7200, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
       rotate.setDuration(10000);
      rotate.setFillAfter(true);
      rotate.setFillEnabled(true);
      rouletteImage.startAnimation(rotate);


   }

    private View.OnClickListener betChip = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            TextView bettingAmountTextTemp = (TextView) findViewById(R.id.textView8);
            int temp = 0;
             try {
                temp = Integer.parseInt(bettingAmountTextTemp.getText().toString());
            }catch (Exception e){}
           switch (v.getId()){
               case R.id.imageButton:
                   temp+=5;
                   break;
               case R.id.imageButton2:
                   temp+=10;
                   break;
               case R.id.imageButton3:
                   temp+=25;
                   break;
               case R.id.imageButton4:
                   temp+=50;
                   break;
               case R.id.imageButton5:
                   temp+=100;
                   break;
           }
           bettingAmountTextTemp.setText(String.valueOf(temp));
        }
    };
}
