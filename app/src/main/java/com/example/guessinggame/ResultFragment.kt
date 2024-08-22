package com.example.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.guessinggame.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    lateinit var viewModel: ResultViewModel
    lateinit var viewModelFactory: ResultViewModelFactory
    private var _binding: FragmentResultBinding? = null
    private val binding get()= _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false).apply {
            composeView.setContent {
                MaterialTheme{
                    Surface {
                        ResultFragmentContent()
                    }
                }
            }
        }
        val view = binding.root
        val result = ResultFragmentArgs.fromBundle(requireArguments()).result
        viewModelFactory = ResultViewModelFactory(result)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)
        binding.resultViewModel = viewModel

        binding.newGameButton.setOnClickListener{
            view.findNavController().navigate(R.id.action_resultFragment_to_gameFragment)}
    return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Composable
    fun NewGameButton(clicked: () -> Unit){
        Button(onClick = clicked) {
            Text("Start New Game")
        }
    }

    @Composable
    fun ResultFragmentContent(){

    }
}