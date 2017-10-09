package wycliffe.com.iknowyourtouch;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private TextView txtText;
    private GestureDetector mGestureDetector;

    // Strings
    String responseSingleTouch = "You have touched me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // I can call the onInit  in longpress
        // Instance of the text to speech.
        tts = new TextToSpeech(this, this);
        txtText = (TextView) findViewById(R.id.txtText);
        // Bind the gestureDetector to GestureListener
        mGestureDetector = new GestureDetector(this, new GestureListener());
    }


    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }


    //listens if TextToSpeech is initialized successfully
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US); // setting the language
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.i("TTS", "This Language is not supported");
            } else {
                speakOut();
            }
        } else {
            Log.e("TTS", "Initialization Failed!");
        }

    }


    private void speakOut() {
        tts.setSpeechRate(2);
        tts.speak(responseSingleTouch, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void writeOut() {
        txtText.setText(responseSingleTouch);
    }

    // onTouch() method gets called each time you perform any touch event with screen
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //method onTouchEvent of GestureDetector class Analyzes the given motion event
        //and if applicable triggers the appropriate callbacks on the GestureDetector.OnGestureListener supplied.
        //Returns true if the GestureDetector.OnGestureListener consumed the event, else false.

        boolean eventConsumed = mGestureDetector.onTouchEvent(event);
        if (eventConsumed) {
            speakOut();
            writeOut();
            return true;
        }
        return false;
    }
}
