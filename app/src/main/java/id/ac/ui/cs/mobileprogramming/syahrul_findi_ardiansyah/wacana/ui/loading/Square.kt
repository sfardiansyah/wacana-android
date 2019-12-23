package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.loading

import android.opengl.GLES32
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

const val COORDS_PER_VERTEX = 3
var squareCoords = floatArrayOf(
    -0.3f, 0.3f, 0.0f,      // top left
    -0.3f, -0.3f, 0.0f,      // bottom left
    0.3f, -0.3f, 0.0f,      // bottom right
    0.3f, 0.3f, 0.0f       // top right
)

class Square {

    private val vertexShaderCode =
        // This matrix member variable provides a hook to manipulate
        // the coordinates of the objects that use this vertex shader
        "uniform mat4 uMVPMatrix;" +
        "attribute vec4 vPosition;" +
        "void main() {" +
        // The matrix must be included as a modifier of gl_Position.
        // Note that the uMVPMatrix factor *must be first* in order
        // for the matrix multiplication product to be correct.
        "  gl_Position = uMVPMatrix * vPosition;" +
        "}"

    private val fragmentShaderCode =
        "precision mediump float;" +
        "uniform vec4 vColor;" +
        "void main() {" +
        "  gl_FragColor = vColor;" +
        "}"

    // Use to access and set the view transformation
    private var vPMatrixHandle: Int = 0

    private var mProgram: Int
    private var positionHandle: Int = 0
    private var mColorHandle: Int = 0

    private val vertexStride: Int = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

    private val color = floatArrayOf(1f, 1f, 1f, 1.0f)

    private val drawOrder = shortArrayOf(0, 1, 2, 0, 2, 3) // order to draw vertices

    // initialize vertex byte buffer for shape coordinates
    private val vertexBuffer: FloatBuffer =
        // (# of coordinate values * 4 bytes per float)
        ByteBuffer.allocateDirect(squareCoords.size * 4).run {
            order(ByteOrder.nativeOrder())
            asFloatBuffer().apply {
                put(squareCoords)
                position(0)
            }
        }

    // initialize byte buffer for the draw list
    private val drawListBuffer: ShortBuffer =
        // (# of coordinate values * 2 bytes per short)
        ByteBuffer.allocateDirect(drawOrder.size * 2).run {
            order(ByteOrder.nativeOrder())
            asShortBuffer().apply {
                put(drawOrder)
                position(0)
            }
        }

    fun draw(mvpMatrix: FloatArray) {
        // Add program to OpenGL ES environment
        GLES32.glUseProgram(mProgram)

        // get handle to vertex shader's vPosition member
        positionHandle = GLES32.glGetAttribLocation(mProgram, "vPosition").also {

            // Enable a handle to the triangle vertices
            GLES32.glEnableVertexAttribArray(it)

            // Prepare the triangle coordinate data
            GLES32.glVertexAttribPointer(
                it,
                COORDS_PER_VERTEX,
                GLES32.GL_FLOAT,
                false,
                vertexStride,
                vertexBuffer
            )

            // get handle to fragment shader's vColor member
            mColorHandle = GLES32.glGetUniformLocation(mProgram, "vColor").also { colorHandle ->

                // Set color for drawing the triangle
                GLES32.glUniform4fv(colorHandle, 1, color, 0)
            }

            // get handle to shape's transformation matrix
            vPMatrixHandle = GLES32.glGetUniformLocation(mProgram, "uMVPMatrix")
            checkGlError("glGetUniformLocation")

            // Apply the projection and view transformation
            GLES32.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0)
            checkGlError("glUniformMatrix4fv")

            // Draw the square
            GLES32.glDrawElements(GLES32.GL_TRIANGLES, drawOrder.size, GLES32.GL_UNSIGNED_SHORT, drawListBuffer)

            // Disable vertex array
            GLES32.glDisableVertexAttribArray(it)
        }
    }

    init {

        val vertexShader: Int = loadShader(GLES32.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader: Int = loadShader(GLES32.GL_FRAGMENT_SHADER, fragmentShaderCode)

        // create empty OpenGL ES Program
        mProgram = GLES32.glCreateProgram().also {

            // add the vertex shader to program
            GLES32.glAttachShader(it, vertexShader)

            // add the fragment shader to program
            GLES32.glAttachShader(it, fragmentShader)

            // creates OpenGL ES program executables
            GLES32.glLinkProgram(it)
        }
    }
}