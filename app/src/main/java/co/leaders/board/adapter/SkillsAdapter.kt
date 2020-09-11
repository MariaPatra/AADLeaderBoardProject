package co.leaders.board.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.leaders.board.R
import co.leaders.board.model.SkillModel
import kotlinx.android.synthetic.main.item_leaders_learning.view.txvName
import kotlinx.android.synthetic.main.item_skills_iq.view.*

class SkillsAdapter :
    ListAdapter<SkillModel, SkillsAdapter.SkillsViewHolder>(DIFF_CALLBACK) {

    class SkillsViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

        var txvName: TextView = itemView.txvName
        var txvScoreCountry: TextView = itemView.txvScoreCountry

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_skills_iq, parent, false)

        return SkillsViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {

        val skillsItems = getItem(position)
        val context = holder.itemView.context

        holder.txvName.text = skillsItems.name
        holder.txvScoreCountry.text = context.getString(
            R.string.label_skills_country,
            skillsItems.score.toString(), skillsItems.country
        )

    }

    companion object {

        //its static bcs it has to be called within the supper class of LeadersLearningAdapter
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<SkillModel> =
            object : DiffUtil.ItemCallback<SkillModel>() {
                override fun areItemsTheSame(
                    oldItem: SkillModel,
                    newItem: SkillModel
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: SkillModel,
                    newItem: SkillModel
                ): Boolean {
                    return oldItem.score == newItem.score
                }
            }

    }

}