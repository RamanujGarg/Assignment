package com.techflitter.assignments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.techflitter.assignments.models.Container
import com.techflitter.assignments.models.Content
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity(), VideoAdapter.Callback {

    private val contents = ArrayList<Content>()
    private var videoAdapter: VideoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()


//        boxStore = MyObjectBox.builder().androidContext(this).build();

        search_view.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                executeQuery(search_view.text.toString())
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun initAdapter() {
        videoAdapter = VideoAdapter(contents)
        recycler_view.adapter = videoAdapter
        videoAdapter!!.setCallback(this)
    }

    override fun onUpVote(position: Int) {
        /*val currentVote = videoModels[position].upVoteCount
        videoModels[position].upVoteCount = currentVote + 1
        videoAdapter!!.notifyItemChanged(position)*/
    }

    override fun onDownVote(position: Int) {
        /*val currentVote = videoModels[position].downVoteCount
        videoModels[position].downVoteCount = currentVote + 1
        videoAdapter!!.notifyItemChanged(position)*/
    }

    override fun onItemClick(position: Int) {
        /*val videoModel = VideoModel(
            1, videoModels[position].itemName
            , videoModels[position].upVoteCount,
            videoModels[position].downVoteCount
        )*/
    }

    private fun executeQuery(query: String) {
        dismissKeyboard()

        AssignRequestHandler.clearInstance()

        val parse = HashMap<String, String>()
        parse[AppConstant.QUERY] = query
        parse[AppConstant.LIMIT] = AppConstant.LIMIT_COUNT
        parse[AppConstant.API_KEY] = AppConstant.GIPHY_KEY
        AssignRequestHandler.getInstance(object : INetwork<Container> {
            override fun onResponse(response: Response<Container>) {
                contents.clear()
                if (response.isSuccessful) {
                    contents.addAll(response.body()!!.contents)
                    videoAdapter?.notifyDataSetChanged()
                }
                Log.e("message; ", "message;")
            }

            override fun onError(response: Response<Container>) {
                Log.e("message; ", "message;")
            }

            override fun onNullResponse() {
                Log.e("message; ", "message;")
            }

            override fun onFailure(t: Throwable) {
                Log.e("message; ", "message;")
            }
        }).getSearchedContent(parse)
    }

    private fun dismissKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(search_view.windowToken, 0)
    }
}
