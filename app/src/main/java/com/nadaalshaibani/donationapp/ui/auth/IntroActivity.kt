package com.nadaalshaibani.donationapp.ui.auth

import android.os.Bundle
import com.nadaalshaibani.donationapp.R

class IntroActivity: MaterialIntroActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(SlideFragmentBuilder()
            .title("Blood App")
            .image(R.drawable.blood)
            .buttonsColor(R.color.colorPrimary)
            .backgroundColor(R.color.black)
            .build())

        addSlide(SlideFragmentBuilder()
            .title("Developer Alamin")
            .image(R.drawable.ic_website)
            .buttonsColor(R.color.colorPrimary)
            .backgroundColor(R.color.black)
            .build())

        addSlide(SlideFragmentBuilder()
            .title("Website")
            .image(R.drawable.ic_website)
            .buttonsColor(R.color.colorPrimary)
            .backgroundColor(R.color.black)
            .build())
    }

}