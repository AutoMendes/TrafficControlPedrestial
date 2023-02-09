package ipca.test.trafficcontrolpedrestial

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Handler
import android.util.AttributeSet
import android.view.View

class TrafficControlPedrestial : View {

    var active = 0
    var timer = 30

    private var onValueChange : ((Int)->Unit)? = null

    fun setOnValueChangeListener (callback :(Int)->Unit ) {
        onValueChange = callback
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE

        //create a rectangle that surrounds the traffic light
        val rect = Rect(width/2-250,height/2-700,width/2+250,height/2+700)
        canvas?.drawRect(rect, paint)

        if (active == 0) {
            paint.color = Color.RED
            paint.style = Paint.Style.FILL
            canvas?.drawCircle((width / 2).toFloat(), (height / 2 - 250).toFloat(), 150F, paint)

            paint.color = Color.GREEN
            paint.style = Paint.Style.STROKE
            canvas?.drawCircle((width / 2).toFloat(), (height / 2 + 250).toFloat(), 150F, paint)
        } else if (active == 1) {

            paint.color = Color.RED
            paint.style = Paint.Style.STROKE
            canvas?.drawCircle((width / 2).toFloat(), (height / 2 - 250).toFloat(), 150F, paint)


            paint.color = Color.GREEN
            paint.style = Paint.Style.STROKE
            canvas?.drawCircle((width / 2).toFloat(), (height / 2 + 250).toFloat(), 150F, paint)

            if (timer == 0) {
                active = 0
                onValueChange?.invoke(active)
                invalidate()
            } else {

                paint.color = Color.GREEN
                paint.style = Paint.Style.FILL
                canvas?.drawCircle((width / 2).toFloat(), (height / 2 + 250).toFloat(), 150F, paint)

                paint.color = Color.BLACK
                paint.style = Paint.Style.FILL
                paint.textSize = 90f
                val textWidth = paint.measureText(timer.toString())
                canvas?.drawText(timer.toString(), (width / 2 - textWidth / 2).toFloat(), (height / 2 + 270).toFloat(), paint)

            }

        }
    }

    fun onButtonClick() {
        active = 1
        onValueChange?.invoke(active)
        invalidate()

        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                if (timer > 0) {
                    timer--
                    invalidate()
                    handler.postDelayed(this, 1000)
                } else {
                    timer = 30
                    invalidate()
                }
            }
        }
        handler.postDelayed(runnable, 1000)
    }

}