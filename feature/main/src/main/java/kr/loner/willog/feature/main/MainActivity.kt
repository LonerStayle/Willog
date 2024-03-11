package kr.loner.willog.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.loner.willog.model.Photo

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }
    private val refresh by lazy {
        findViewById<SwipeRefreshLayout>(R.id.refresh)
    }
    private val button by lazy {
        findViewById<Button>(R.id.button)
    }
    private val adapter by lazy{
        Adapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.dlist.collectLatest {
                    adapter.submitData(it)
                }
            }
        }

        refresh.setOnRefreshListener {
            adapter.refresh()
            refresh.isRefreshing = false
        }

        button.setOnClickListener {
            viewModel.asd("b")
        }
    }
}

class Adapter(): PagingDataAdapter<Photo, Adapter.ViewHolder>(
    object :DiffUtil.ItemCallback<Photo>(){
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }
){


    inner class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        private val textView by lazy {
            v.findViewById<TextView>(R.id.tv_name)
        }
        fun bind(photo: Photo){
            textView.text = photo.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.test_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)?:return)
    }
}


