package com.example.graphview

import android.content.ContentResolver
import android.content.Context
import android.graphics.*
import android.provider.MediaStore
import android.util.AttributeSet
import android.view.View
import androidx.core.net.toUri
import java.io.File

class GraphView : View, GraphActions {
    lateinit var rect: Rect
    lateinit var paint: Paint
    lateinit var linePaint: Paint
    lateinit var baseLine: Paint
    lateinit var circle: RectF
    lateinit var bg: Bitmap
    lateinit var bgPaint: Paint
    var max: Float = 0f
    var min: Float = 0f
    val linesList = mutableListOf<Lines>(Lines(800), Lines(600), Lines(800), Lines(200), Lines(100), Lines(0)
        , Lines(600)
        , Lines(200)
        , Lines(500)
        , Lines(100)
        , Lines(100)
        ,Lines(200))

    var lineMaxHeight = 350f
    var spaceSize = 100f

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        init()
    }

    fun init() {
        rect = Rect()
        paint = Paint(SCROLL_AXIS_VERTICAL)
        linePaint = Paint().apply {
            color = Color.RED
            strokeWidth = 15f
            alpha = 95
            isAntiAlias = true
        }
        baseLine = Paint().apply {
            color = Color.BLACK
            strokeWidth = 5f
            isAntiAlias = true
        }
        paint.apply {
            color = Color.rgb(186, 20, 70)
        }

        circle = RectF()

        bg = MediaStore.Images.Media.getBitmap(context.contentResolver, "android.resource://com.example.graphview/drawable/bg".toUri())

        bgPaint = Paint().apply {

        }
    }


    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(rect, paint)
        max = getMax(linesList)?.value?.toFloat()!!
        rect.set(
            0,
            0,
            width * resources.displayMetrics.widthPixels,
            max.toInt()
        )
//        canvas?.drawRect(rect, paint)
        var height = max/2
        var width = spaceSize
        canvas?.drawLine(0f, max, resources.displayMetrics.widthPixels.toFloat(), max, baseLine )
        canvas?.drawLine(0f, 0f, resources.displayMetrics.widthPixels.toFloat(), 0f, baseLine )
        canvas?.drawBitmap(bg, 0f, 0f, bgPaint.apply {
            isAntiAlias = true

        })
            linesList.forEach {

                canvas?.drawLine(width, height, width + spaceSize, max - it.value.toFloat(), linePaint)
                canvas?.drawCircle(width, height, 10f, paint)
                height = max - it.value.toFloat()
                width += 100f
        }

    }

    override fun addLines(lines: Lines) {
        linesList.add(lines)
    }

    override fun getMax(linesList: List<Lines>) = linesList.maxBy { it.value }


    override fun getMin(linesList: List<Lines>) = linesList.minBy { it.value }

}