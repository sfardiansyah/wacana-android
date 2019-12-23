package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana

import android.app.Activity
import android.content.Intent
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.loading.LoadingGLSurfaceView

class LoadingActivity : FragmentActivity() {

    private lateinit var gLView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gLView = LoadingGLSurfaceView(this)
        setContentView(gLView)

        Handler().postDelayed({
            if (!isFinishing) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                Toast.makeText(this, "Transaction created!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }, 2000)
    }
}