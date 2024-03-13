package kr.loner.feature.photo.search

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.loner.feature.photo.R
import kr.loner.feature.photo.databinding.ItemPhotoBinding
import kr.loner.willog.model.Photo


class PhotoSearchAdapter(
    private val itemHeight: Int,
    private val onPhotoClick: (Photo) -> Unit
) :
    PagingDataAdapter<Photo, PhotoSearchAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }) {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val binding = ItemPhotoBinding.bind(v)
        fun bind(photo: Photo) {
            with(binding) {
                Glide.with(imageViewPhoto).load(photo.urls.thumb).centerCrop().into(imageViewPhoto)
                imageViewBookmark.setImageResource(
                    if (photo.isBookmark) {
                        R.drawable.ic_heart
                    } else {
                        R.drawable.ic_heart_outline
                    }
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo, parent, false)
        ).apply {
            val layoutParams = itemView.layoutParams.apply {
                height = itemHeight
            }
            itemView.layoutParams = layoutParams
            itemView.setOnClickListener {
                onPhotoClick(
                    getItem(bindingAdapterPosition) ?: return@setOnClickListener
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    class GridSpacingItemDecoration(
        private val spanCount: Int,
        private val spacing: Int,
    ) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
            outRect.right =
                spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing // item top
            }
        }
    }
}


