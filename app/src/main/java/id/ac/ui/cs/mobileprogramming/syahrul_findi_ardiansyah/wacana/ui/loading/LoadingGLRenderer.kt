package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.loading

import android.opengl.GLES32
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.os.SystemClock
import android.util.Log
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class LoadingGLRenderer : GLSurfaceView.Renderer {

    private lateinit var mSquare: Square

    // vPMatrix is an abbreviation for "Model View Projection Matrix"
    private val vPMatrix = FloatArray(16)
    private val projectionMatrix = FloatArray(16)
    private val viewMatrix = FloatArray(16)
    private val rotationMatrix = FloatArray(16)

    override fun onDrawFrame(p0: GL10?) {
        val scratch = FloatArray(16)

        GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT or GLES32.GL_DEPTH_BUFFER_BIT)

        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0)

        // Create a rotation transformation for the triangle
        val time = SystemClock.uptimeMillis() % 4000L
        val angle = 0.3f * time.toInt()
        Matrix.setRotateM(rotationMatrix, 0, angle, 0f, 0f, -1.0f)

        // Combine the rotation matrix with the projection and camera view
        // Note that the vPMatrix factor *must be first* in order
        // for the matrix multiplication product to be correct.
        Matrix.multiplyMM(scratch, 0, vPMatrix, 0, rotationMatrix, 0)

        mSquare.draw(scratch)
    }

    override fun onSurfaceChanged(p0: GL10?, p1: Int, p2: Int) {
        GLES32.glViewport(0, 0, p1, p2)

        val ratio: Float = p1.toFloat() / p2.toFloat()

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
    }

    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        GLES32.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)

        mSquare = Square()
    }

}

fun checkGlError(glOperation: String) {
    var error: Int
    while (GLES32.glGetError().also { error = it } != GLES32.GL_NO_ERROR) {
        Log.e("Renderer", "$glOperation: glError $error")
        throw RuntimeException("$glOperation: glError $error")
    }
}

fun loadShader(type: Int, shaderCode: String): Int {

    return GLES32.glCreateShader(type).also { shader ->

        GLES32.glShaderSource(shader, shaderCode)
        GLES32.glCompileShader(shader)
    }
}