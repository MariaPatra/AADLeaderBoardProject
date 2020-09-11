package co.leaders.board.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.leaders.board.R
import co.leaders.board.model.LearningLeaderModel
import kotlinx.android.synthetic.main.item_leaders_learning.view.*

class LeadersLearningAdapter :
    ListAdapter<LearningLeaderModel, LeadersLearningAdapter.LeadersViewHolder>(DIFF_CALLBACK) {

    class LeadersViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

        var txvName: TextView = itemView.txvName
        var txvHourCountry: TextView = itemView.txvHoursCountry

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadersViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_leaders_learning, parent, false)

        return LeadersViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: LeadersViewHolder, position: Int) {

        val learningItems = getItem(position)
        val context = holder.itemView.context

        holder.txvName.text = learningItems.name
        holder.txvHourCountry.text = context.getString(
            R.string.label_hour_country,
            learningItems.hours.toString(), learningItems.country
        )

    }


    companion object {

        //its static bcs it has to be called within the supper class of LeadersLearningAdapter
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<LearningLeaderModel> =
            object : DiffUtil.ItemCallback<LearningLeaderModel>() {
                override fun areItemsTheSame(
                    oldItem: LearningLeaderModel,
                    newItem: LearningLeaderModel
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: LearningLeaderModel,
                    newItem: LearningLeaderModel
                ): Boolean {
                    return oldItem.hours == newItem.hours
                }
            }

    }

}