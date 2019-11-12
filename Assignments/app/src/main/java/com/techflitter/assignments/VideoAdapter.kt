package com.techflitter.assignments

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techflitter.assignments.models.Content
import kotlinx.android.synthetic.main.item_video.view.*

class VideoAdapter internal constructor(private val videoModelList: List<Content>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private var callback: Callback? = null

    interface Callback {
        fun onUpVote(position: Int)

        fun onDownVote(position: Int)

        fun onItemClick(position: Int)
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_video,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        try {
            val videoModel = videoModelList[position]

            holder.itemView.tv_upvote.text = holder.itemView.context.getString(
                R.string.zero_up_vote,
                videoModel.upVote.toString()
            )
            holder.itemView.tv_downvote.text = holder.itemView.context.getString(
                R.string.zero_down_vote,
                videoModel.downVote.toString()
            )

            holder.bind(videoModel)
        } catch (e: Exception) {
            Log.e("Exception: ", e.message)
        }

    }

    override fun getItemCount(): Int {
        return videoModelList.size
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

            itemView.setOnClickListener {
                if (callback != null) {
                    callback!!.onItemClick(adapterPosition)
                }
            }

            itemView.tv_upvote.setOnClickListener {
                upVote(videoModelList[adapterPosition])
                notifyItemChanged(adapterPosition)
            }

            itemView.tv_downvote.setOnClickListener {
                downVote(videoModelList[adapterPosition])
                notifyItemChanged(adapterPosition)
            }
        }

        private fun upVote(content: Content) {
            var currentCount = content.upVote;
            currentCount++
            videoModelList[adapterPosition].upVote = currentCount
            itemView.tv_downvote.text = itemView.context.getString(
                R.string.zero_up_vote,
                currentCount.toString()
            )
        }

        private fun downVote(content: Content) {
            var currentCount = content.upVote;
            if (currentCount == 0)
                return
            currentCount--
            videoModelList[adapterPosition].downVote = currentCount
            itemView.tv_downvote.text = itemView.context.getString(
                R.string.zero_down_vote,
                currentCount.toString()
            )
        }


        fun bind(content: Content) {
            Glide.with(itemView.context).load(Uri.parse(content.images.original.url))
                .into(itemView.img_thumbnail)
        }
    }
}
