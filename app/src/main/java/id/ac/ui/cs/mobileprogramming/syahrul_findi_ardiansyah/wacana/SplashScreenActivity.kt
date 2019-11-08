package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)

        Handler().postDelayed(Runnable {
            if (!isFinishing) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        }, 2000)
    }
}