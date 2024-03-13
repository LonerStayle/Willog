package kr.loner.willog.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kr.loner.willog.designsystem.theme.WillogTheme
import kr.loner.willog.model.Photo

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val testViewModel by viewModels<MainTestViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WillogTheme {
                MainScreen()
            }
        }
//    private val recyclerView by lazy {
//        findViewById<RecyclerView>(R.id.recyclerView)
//    }
//
//    private val refresh by lazy {
//        findViewById<SwipeRefreshLayout>(R.id.refresh)
//    }
//
//    private val editText by lazy {
//        findViewById<EditText>(R.id.editText)
//    }
//
//    private val adapter by lazy{
//        Adapter{ photo ->
//            viewModel.bookmarkToggle(photo)
//        }
//    }

//        setContentView(R.layout.test)
//        recyclerView.itemAnimator = null
//        recyclerView.adapter = adapter
//
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModel.searchPhotos.collectLatest {
//                    adapter.submitData(it)
//                }
//            }
//        }
//
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModel.bookmarkedPhotos.collectLatest {
//                    adapter.refresh()
//                }
//            }
//        }
//
//        refresh.setOnRefreshListener {
//            adapter.refresh()
//            refresh.isRefreshing = false
//        }
//
//        editText.addTextChangedListener {editable ->
//            viewModel.setQuery(editable?.toString()?:"")
//        }
    }
}

class Adapter(private val bookmarkToggle: (Photo) -> Unit) :
    PagingDataAdapter<Photo, Adapter.ViewHolder>(
        object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    ) {


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val textView by lazy {
            v.findViewById<TextView>(R.id.tv_name)
        }
        private val imageView by lazy {
            v.findViewById<ImageView>(R.id.imageView)
        }

        fun bind(photo: Photo) {
            textView.text = photo.id
            imageView.isVisible = photo.isBookmark
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.test_item, parent, false)
        ).apply {
            itemView.setOnClickListener {
                bookmarkToggle(getItem(bindingAdapterPosition) ?: return@setOnClickListener)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }
}


