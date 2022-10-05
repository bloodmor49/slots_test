package com.example.test_slots.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.test_slots.data.slots_pack.IEventEnd
import com.example.test_slots.data.slots_pack.SlotElement.Companion.NUMBER_OF_SLOTS
import com.example.test_slots.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IEventEnd {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initializeCoins()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListeners()
        subscribeToObservers()
        setUpEvents()
    }

    override fun onEventFinishedCallback(result: List<Int>, count: Int) {
        if (viewModel.countOfFinishedSpins < NUMBER_OF_SLOTS) viewModel.countOfFinishedSpins++
        else {
            viewModel.countOfFinishedSpins = 0
        }
        binding.bottomElements.btnSpin.visibility = View.VISIBLE

        val listOfResult = listOf(
            binding.spinElements.reel1.value,
            binding.spinElements.reel2.value,
            binding.spinElements.reel3.value,
            binding.spinElements.reel4.value,
            binding.spinElements.reel5.value
        )
        viewModel.spin(listOfResult)
    }

    private fun setUpListeners() {
        binding.bottomElements.btnAdd.setOnClickListener {
            viewModel.addBet()
        }
        binding.bottomElements.btnRemove.setOnClickListener {
            viewModel.removeBet()
        }
        binding.bottomElements.btnSpin.setOnClickListener {
            if (viewModel.currentBet.value != 0) {
                binding.bottomElements.btnSpin.visibility = View.GONE
                with(binding.spinElements) {
                    reel1.setValueRandom(10)
                    reel2.setValueRandom(10)
                    reel3.setValueRandom(10)
                    reel4.setValueRandom(10)
                    reel5.setValueRandom(10)
                }
            }
        }
    }

    private fun subscribeToObservers() {
        viewModel.coins.observe(this) {
            binding.headElements.tvCoins.text = it?.number.toString()
        }
        viewModel.currentBet.observe(this) {
            binding.bottomElements.tvBetNum.text = it.toString()
        }
    }

    private fun setUpEvents() {
        with(binding.spinElements) {
            reel1.setEventEnd(this@MainActivity)
            reel2.setEventEnd(this@MainActivity)
            reel3.setEventEnd(this@MainActivity)
            reel4.setEventEnd(this@MainActivity)
            reel5.setEventEnd(this@MainActivity)
        }
    }

}
