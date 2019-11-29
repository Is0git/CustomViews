package com.example.graphview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import kotlin.math.roundToInt

class CounterView : View {
    var textColor: Int = 0
    lateinit var textPaint: Paint
    lateinit var rect: Rect
    lateinit var rectPaint : Paint
    constructor(context: Context?) : super(context) {init(null)}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {init(attrs)}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {init(attrs)}

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    fun init(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.CounterView)
        textColor = ta.getColor(R.styleable.CounterView_counter_color, Color.BLACK)
        textPaint = Paint().apply {
            color = textColor
            textSize = 280f
        }
        ta.recycle()
        rectPaint = Paint().apply {
            color = Color.BLACK
        }
        rect = Rect()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val maxTextWidth = textPaint.measureText("0000").roundToInt()
        val maxTextHeight = textPaint.fontMetrics.let { (it.bottom - it.top).roundToInt() }

        val width = resolveSize(maxTextWidth, widthMeasureSpec)
        val height = resolveSize(maxTextHeight, heightMeasureSpec)

        setMeasuredDimension(width, height)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rect.set(0, 0, width, height)
        canvas?.drawRect(rect, rectPaint )
        val center = width * 0.5
        val textWidth = textPaint.measureText("0000")
        val textX = center - textWidth * 0.5
        canvas?.drawText("0000",  textX.toFloat(), height.toFloat(),  textPaint)


    }
}