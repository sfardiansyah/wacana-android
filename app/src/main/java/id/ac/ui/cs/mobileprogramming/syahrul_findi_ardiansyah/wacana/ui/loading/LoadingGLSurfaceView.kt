package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.loading

import android.content.Context
import android.opengl.GLSurfaceView

class LoadingGLSurfaceView(context: Context) : GLSurfaceView(context) {

    private val renderer: LoadingGLRenderer

    init {
        setEGLContextClientVersion(3)

        renderer = LoadingGLRenderer()

        setRenderer(renderer)
    }
}