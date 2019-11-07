package com.github.thenicholaschen.meeting08_trackmymoviequality.movietracker


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.github.acailuv.meeting08_trackmymoviequality.R
import com.github.thenicholaschen.meeting08_trackmymoviequality.database.MovieDatabase
import com.github.acailuv.meeting08_trackmymoviequality.databinding.FragmentMovieTrackerBinding
import com.github.acailuv.meeting08_trackmymoviequality.movietracker.MovieTrackerFragmentDirections

/**
 * A simple [Fragment] subclass.
 */
class MovieTrackerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMovieTrackerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_tracker, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = MovieDatabase.getInstance(application).movieDatabaseDao

        val viewModelFactory =
            MovieTrackerViewModelFactory(
                dataSource,
                application
            )
        val movieTrackerViewModel = ViewModelProviders
                                        .of(this, viewModelFactory)
                                        .get(MovieTrackerViewModel::class.java)

        movieTrackerViewModel.navigateToMovieDetails.observe(this, Observer {
            movie -> movie?.let {
                this.findNavController().navigate(
                    MovieTrackerFragmentDirections.actionMovieTrackerFragmentToMovieDetailsFragment(
                        movie.movieId
                    )
                )
                movieTrackerViewModel.doneNavigating()
            }
        })

        movieTrackerViewModel.navigateToMovieUpdate.observe(this, Observer {
            movie -> movie?.let {
                this.findNavController().navigate(MovieTrackerFragmentDirections.actionMovieTrackerFragmentToMovieUpdateFragment())
                movieTrackerViewModel.doneNavigating()
            }
        })

        binding.setLifecycleOwner(this)
        binding.movieTrackerViewModel = movieTrackerViewModel

        // Inflate the layout for this fragment
        return binding.root
    }

}