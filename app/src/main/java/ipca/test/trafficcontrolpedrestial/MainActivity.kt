package ipca.test.trafficcontrolpedrestial

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val trafficControlPedrestial = findViewById<TrafficControlPedrestial>(R.id.trafficControl)
        trafficControlPedrestial.setOnValueChangeListener {
            // do something
        }

        val button = findViewById<Button>(R.id.buttonStart)
        button.setOnClickListener {
            trafficControlPedrestial.onButtonClick()
            button.isEnabled = false
            val handler = Handler()
            handler.postDelayed({
                button.isEnabled = true
            }, 35000)
        }
    }
}

