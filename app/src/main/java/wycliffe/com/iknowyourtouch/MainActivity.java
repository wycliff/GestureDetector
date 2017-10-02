package wycliffe.com.iknowyourtouch;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener
//        GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
{


    private TextToSpeech tts;
    private Button btnSpeak;
    private TextView txtText;
    private GestureDetector mGestureDetector;

    // Strings
    String response1 = "You have touched me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // I can call the onInit  in longpress

        // Instamce of the text to speech.
        tts = new TextToSpeech(this, this);

       // btnSpeak = (Button) findViewById(R.id.btnSpeak);

        txtText = (TextView) findViewById(R.id.txtText);

        // Bind the gestureDetector to GestureListener
        mGestureDetector = new GestureDetector(this, new GestureListener());

        // button on click event
//        btnSpeak.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                speakOut();
//            }
//
//        });
    }


    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }


    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US); // setting the language

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.i("TTS", "This Language is not supported");
            } else {
//                btnSpeak.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }


    private void speakOut() {

        //String text = txtText.getText().toString();
        //tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        //tts.setPitch(0.6);
        tts.setSpeechRate(2);
        tts.speak(response1, TextToSpeech.QUEUE_FLUSH, null);
    }

    private  void writeOut(){

        // later we will have a condition for every
        txtText.setText(response1);
    }


    // onTouch() method gets called each time you perform any touch event with screen
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //method onTouchEvent of GestureDetector class Analyzes the given motion event
        //and if applicable triggers the appropriate callbacks on the GestureDetector.OnGestureListener supplied.
        //Returns true if the GestureDetector.OnGestureListener consumed the event, else false.

        boolean eventConsumed=mGestureDetector.onTouchEvent(event);
        if (eventConsumed)
        {
//            Toast.makeText(this,GestureListener.currentGestureDetected, Toast.LENGTH_LONG).show();
            speakOut();

            writeOut();
            return true;
        }
        else
            return false;
    }

//
//    public boolean onDoubleTapper(MotionEvent event){
//        boolean eventConsumed=mGestureDetector.setOnDoubleTapListener(event);
//        if (eventConsumed)
//        {
////            Toast.makeText(this,GestureListener.currentGestureDetected, Toast.LENGTH_LONG).show();
//            speakOut();
//            return true;
//        }
//        else
//            return false;
//    }

}//end Class

//    @Override
//    public boolean onSingleTapConfirmed(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public boolean onDoubleTap(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public boolean onDoubleTapEvent(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public boolean onDown(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public void onShowPress(MotionEvent e) {
//
//    }
//
//    @Override
//    public boolean onSingleTapUp(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        return false;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent e) {
//
//    }
//
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        return false;

