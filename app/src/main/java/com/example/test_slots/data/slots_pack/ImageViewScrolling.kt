package com.example.test_slots.data.slots_pack

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.test_slots.databinding.ImageViewScrollingBinding
import kotlin.random.Random

class ImageViewScrolling @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {


    companion object {
        private const val ANIMATION_DURATION = 50
    }

    private lateinit var binding: ImageViewScrollingBinding
    private lateinit var eventEnd: IEventEnd
    private var listOfOldValues: MutableList<Int>

    init {
        binding = ImageViewScrollingBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
        binding.reelOld.reel.translationY = height.toFloat()

        val itemsNext = listOf(binding.reelNext.img1, binding.reelNext.img2, binding.reelNext.img3)
        val itemsOld = listOf(binding.reelOld.img1, binding.reelOld.img2, binding.reelOld.img3)
        val randomList = listOf(Random.nextInt(9), Random.nextInt(9), Random.nextInt(9))

        setImages(itemsNext, randomList)
        setImages(itemsOld, randomList)
        listOfOldValues = randomList as MutableList<Int>
    }

    private var numOfRotations = 0

    val value
        get() = listOf(Integer.parseInt(binding.reelNext.img1.tag.toString()),
            Integer.parseInt(binding.reelNext.img2.tag.toString()),
            Integer.parseInt(binding.reelNext.img3.tag.toString()))

    fun setEventEnd(eventEnd: IEventEnd) {
        this.eventEnd = eventEnd
    }


    fun setImages(listOfImages: List<ImageView?>, values: List<Int>) {
        for (i in listOfImages.indices) {
            val element = SlotElement.getFromCode(values[i])
            listOfImages[i]?.setImageResource(element.imgRes)
            listOfImages[i]?.tag = values[i]
        }
    }

    fun setValueRandom(maxRotations: Int) {
        val itemsNext = listOf(binding.reelNext.img1, binding.reelNext.img2, binding.reelNext.img3)
        val itemsOld = listOf(binding.reelOld.img1, binding.reelOld.img2, binding.reelOld.img3)

        //Уход старого списка из вью.
        binding.reelOld.reel
            .animate()
            .translationY((-height).toFloat())
            .setDuration(ANIMATION_DURATION.toLong())
            .start()

        //Выставление нового списка на исходную позицию.
        binding.reelNext.reel.translationY = binding.reelNext.reel.height.toFloat()

        //Выезд нового списка на вью.
        binding.reelNext.reel
            .animate()
            .translationY(0f)
            .setDuration(ANIMATION_DURATION.toLong())
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    //Формируем 3 новых значения.
                    val nextValues = listOf(
                        Random.nextInt(9),
                        Random.nextInt(9),
                        Random.nextInt(9))

                    //Заполнеяем старый список нынешними значениями.
                    setImages(itemsOld, listOfOldValues)
                    //Заполнеяем новый список новыми значениями.
                    setImages(itemsNext, nextValues)

                    binding.reelOld.reel.translationY = 0f

                    //Проверяем сколько выполнено круток.
                    if (numOfRotations != maxRotations) {
                        listOfOldValues = nextValues as MutableList<Int>
                        setValueRandom(maxRotations)
                        numOfRotations++
                    } else {
                        numOfRotations = 0
                        setImages(itemsNext, listOfOldValues)
                        eventEnd.onEventFinishedCallback(listOfOldValues, maxRotations)
                    }
                }

                override fun onAnimationCancel(animation: Animator?) {}

                override fun onAnimationRepeat(animation: Animator?) {}

            }).start()
    }
}
