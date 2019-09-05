package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColorInt
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.extensions.convertSpToPx
import kotlin.math.min
import kotlin.random.Random

class AvatarImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
//    private var avatarSize: Int = 0
//    private var rect: Rect = Rect()
//    private var pathR: Path = Path()
//    private val bgrBounds = RectF()
//    private lateinit var paintText: Paint
//    private lateinit var paintBorder: Paint
//    private var borderWidth: Float = DEFAULT_BORDER_WIDTH
//    private var borderColor: Int = DEFAULT_BORDER_COLOR
//    private var initials: String? = null
//    private lateinit var bitmap: Bitmap
//    private lateinit var canvas: Canvas

    private val bgColor = arrayOf(
        "#7BC862",
        "#E17076",
        "#FAA774",
        "#6EC9CB",
        "#65AADD",
        "#A695E7",
        "#EE7AAE"
    )

    companion object{
        private const val DEFAULT_BORDER_WIDTH = 2f
        private const val DEFAULT_BORDER_COLOR = Color.TRANSPARENT
        private val BITMAP_CONFIG = Bitmap.Config.ARGB_8888
        private val SCALE_TYPE = ScaleType.CENTER_CROP
    }

    private var mBitmapShader: BitmapShader? = null
    private var mBitmap: Bitmap? = null
    private var mBorderBounds: RectF
    private var mBitmapDrawBounds: RectF
    private var paintBitmap: Paint
    private var paintBorder: Paint
    private var mShaderMatrix: Matrix
    private var borderWidth = DEFAULT_BORDER_WIDTH
    private var borderColor = DEFAULT_BORDER_COLOR
    private var initials: String? = null



    init {
        if (attrs != null){
            val a = context.obtainStyledAttributes(attrs, R.styleable.AvatarImageView, 0, 0)

            borderColor = a.getColor(R.styleable.AvatarImageView_aiv_borderColor, DEFAULT_BORDER_COLOR)
            borderWidth = a.getDimension(
                R.styleable.AvatarImageView_aiv_borderWidth,
                DEFAULT_BORDER_WIDTH
            )
            a.recycle()
        }
        mBorderBounds = RectF()
        mBitmapDrawBounds = RectF()
        mShaderMatrix = Matrix()
        paintBitmap = Paint(Paint.ANTI_ALIAS_FLAG)
        paintBorder = Paint(Paint.ANTI_ALIAS_FLAG)
    }

    fun setInitials(initials: String){
        this.initials = initials
        setImageDrawable(getTextAvatar(initials))
    }

    override fun setImageResource(@DrawableRes resId: Int) {
        super.setImageResource(resId)
        setupBitmap()
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        setupBitmap()
    }

    override fun setImageBitmap(bitmap: Bitmap?) {
        super.setImageBitmap(bitmap)
        setupBitmap()
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        setupBitmap()
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        val halfBorderWidth = paintBorder.strokeWidth/2f
        updateCircleDrawBounds(mBitmapDrawBounds)
        mBorderBounds.set(mBitmapDrawBounds)
        mBorderBounds.inset(halfBorderWidth, halfBorderWidth)

        updateBitmap()
    }

    private fun updateCircleDrawBounds(bounds: RectF) {
        val contentWidth = (width - paddingLeft - paddingRight).toFloat()
        val contentHeight = (height - paddingTop - paddingBottom).toFloat()

        var left = paddingLeft.toFloat()
        var top = paddingTop.toFloat()

        if (contentWidth > contentHeight){
            left += (contentWidth - contentHeight)/2f
        }else{
            top += (contentHeight - contentWidth)/2f
        }

        val diameter = min(contentWidth, contentHeight)
        bounds.set(left, top, left + diameter, top + diameter)
    }

    private fun setupBitmap() {
        super.setScaleType(SCALE_TYPE)

        mBitmap = getBitmapFromDrawabla(drawable)
        if (mBitmap == null){
            return
        }

        mBitmapShader = BitmapShader(mBitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paintBitmap.shader = mBitmapShader

        updateBitmap()
    }

    private fun updateBitmap() {
        if (mBitmap == null) return

        val dX: Float
        val dY: Float
        val scale: Float

        paintBorder.color = borderColor
        paintBorder.style = Paint.Style.STROKE
        paintBorder.strokeWidth = borderWidth

        if (mBitmap!!.width < mBitmap!!.height){
            scale = mBitmapDrawBounds.width()/mBitmap!!.width
            dX = mBitmapDrawBounds.left
            dY = mBitmapDrawBounds.top - mBitmap!!.height * scale/2f + mBitmapDrawBounds.width()/2f
        }else{
            scale = mBitmapDrawBounds.height()/mBitmap!!.height
            dX = mBitmapDrawBounds.left - mBitmap!!.width * scale/2f + mBitmapDrawBounds.width()/2f
            dY = mBitmapDrawBounds.top
        }

        mShaderMatrix.setScale(scale, scale)
        mShaderMatrix.postTranslate(dX, dY)
        mBitmapShader?.setLocalMatrix(mShaderMatrix)
    }

    private fun getBitmapFromDrawabla(drawable: Drawable?): Bitmap? {
        if (drawable == null){
            return null
        }

        if (drawable is BitmapDrawable){
            return drawable.bitmap
        }

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            BITMAP_CONFIG
        )

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    private fun getTextAvatar(text: String): Drawable {
        val size = resources.getDimensionPixelSize(R.dimen.avatar_item_size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)

        val c = Canvas()
        c.setBitmap(bitmap)

        val halfSize = (size/2).toFloat()

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        paint.color = bgColor[Random.nextInt(bgColor.size)].toColorInt()

        c.drawPaint(paint)

        val bounds = Rect()

        paint.textSize = context.convertSpToPx(18f)
        paint.color = resources.getColor(android.R.color.white, context.theme)
        paint.getTextBounds(text, 0, text.length, bounds)

        c.drawText(text, halfSize - paint.measureText(text)/2, halfSize + bounds.height()/2, paint)

        return bitmap.toDrawable(resources)
    }


    private fun getColorFormInitials(initials: String): Int {
        return when(initials.hashCode()%10){
            0 -> resources.getColor(R.color.color_avatar1, context.theme)
            1 -> resources.getColor(R.color.color_avatar2, context.theme)
            2 -> resources.getColor(R.color.color_avatar3, context.theme)
            3 -> resources.getColor(R.color.color_avatar4, context.theme)
            4 -> resources.getColor(R.color.color_avatar5, context.theme)
            5 -> resources.getColor(R.color.color_avatar6, context.theme)
            6 -> resources.getColor(R.color.color_avatar7, context.theme)
            7 -> resources.getColor(R.color.color_avatar8, context.theme)
            8 -> resources.getColor(R.color.color_avatar9, context.theme)
            else -> resources.getColor(R.color.color_avatar10, context.theme)
        }
    }

}