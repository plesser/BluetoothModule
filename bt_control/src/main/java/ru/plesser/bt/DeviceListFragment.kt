package ru.plesser.bt

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import ru.plesser.bt.databinding.FragmentListBinding


class DeviceListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private var btAdapter: BluetoothAdapter? = null
    private lateinit var btLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imBluetoothOn.setOnClickListener{
            btLauncher.launch(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
        }
        registerBtLauncher()
        initBtAdapter()
        bluetoothState()

    }


    private fun registerBtLauncher(){
        btLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if (it.resultCode == Activity.RESULT_OK){
                changeButtonColor(binding.imBluetoothOn, Color.GREEN)
                Snackbar.make(binding.root, getString(R.string.bluetooth_is_on), Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(binding.root, getString(R.string.bluetooth_is_off), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun initBtAdapter(){
        val btManager = activity?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        btAdapter = btManager.adapter
    }

    private fun bluetoothState(){
        if (btAdapter?.isEnabled == true){
            changeButtonColor(binding.imBluetoothOn, Color.GREEN)
        } else {
            changeButtonColor(binding.imBluetoothOn, Color.RED)
        }

    }


}