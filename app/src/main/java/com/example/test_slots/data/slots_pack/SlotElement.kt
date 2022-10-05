package com.example.test_slots.data.slots_pack

import androidx.annotation.DrawableRes
import com.example.test_slots.R

sealed class SlotElement(
    val id: Int,
    @DrawableRes val imgRes: Int,
) {
    object ElementA : SlotElement(
        id = 0,
        imgRes = R.drawable.img_element_a
    )

    object ElementBug : SlotElement(
        id = 1,
        imgRes = R.drawable.img_element_bug
    )

    object ElementDig : SlotElement(
        id = 2,
        imgRes = R.drawable.img_element_dig
    )

    object ElementK : SlotElement(
        id = 3,
        imgRes = R.drawable.img_element_k
    )

    object ElementMan : SlotElement(
        id = 4,
        imgRes = R.drawable.img_element_man
    )

    object ElementO : SlotElement(
        id = 5,
        imgRes = R.drawable.img_element_o
    )

    object ElementPot : SlotElement(
        id = 6,
        imgRes = R.drawable.img_element_pot
    )

    object ElementS : SlotElement(
        id = 7,
        imgRes = R.drawable.img_element_s
    )

    object ElementSerp : SlotElement(
        id = 8,
        imgRes = R.drawable.img_element_serp
    )

    object ElementStaff : SlotElement(
        id = 9,
        imgRes = R.drawable.img_element_staff
    )

    companion object {

        const val NUMBER_OF_SLOTS = 5

        fun getFromCode(code:Int):SlotElement {
            return when (code) {
                ElementA.id -> ElementA
                ElementBug.id -> ElementBug
                ElementDig.id -> ElementDig
                ElementK.id -> ElementK
                ElementMan.id -> ElementMan
                ElementO.id -> ElementO
                ElementPot.id -> ElementPot
                ElementS.id -> ElementS
                ElementSerp.id -> ElementSerp
                else -> ElementStaff
            }
        }
    }
}
