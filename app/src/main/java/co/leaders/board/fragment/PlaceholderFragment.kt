package co.leaders.board.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.leaders.board.R
import co.leaders.board.adapter.LeadersLearningAdapter
import co.leaders.board.adapter.SkillsAdapter
import co.leaders.board.api.ApiService
import co.leaders.board.extensions.executeAsync
import co.leaders.board.extensions.hide
import co.leaders.board.extensions.show
import co.leaders.board.model.PageViewModel
import co.leaders.board.util.AppConstants
import co.leaders.board.util.AppConstants.Companion.DEFAULT_GET_URL
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var mTabPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
        mTabPosition = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(activity)

        when (mTabPosition) {
            0 -> {
                loadLeaning()
            }
            1 -> {
                loadSkills()
            }
            else -> return
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    private fun loadLeaning() {

        progress.show()

        executeAsync({

            ApiService.getInstance(DEFAULT_GET_URL).leaningApiCall().execute()

        }, {

            progress.hide()

            it.body()?.let { data ->
                val leaningAdapter = LeadersLearningAdapter()
                leaningAdapter.submitList(data)
                recyclerView.adapter = leaningAdapter
            }

        })

    }

    private fun loadSkills() {

        progress.show()

        executeAsync({

            ApiService.getInstance(DEFAULT_GET_URL).skillsApiCall().execute()

        }, {

            progress.hide()

            it.body()?.let { data ->
                val skillsAdapter = SkillsAdapter()
                skillsAdapter.submitList(data)
                recyclerView.adapter = skillsAdapter

            }

        })

    }
}