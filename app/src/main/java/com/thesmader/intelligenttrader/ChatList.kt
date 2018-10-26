package com.thesmader.intelligenttrader


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_chat_list.*

class ChatList : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //verifyUserLogin()
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun verifyUserLogin() {
        val uid = FirebaseAuth.getInstance().uid
        Log.d("UID",uid)
        if (uid == null){
            findNavController().navigate(R.id.user_logged_out)
        }

        FirebaseAuth.getInstance().addAuthStateListener {
            if (it.currentUser == null)
                hello.findNavController().navigate(R.id.user_logged_out)
        }
    }
}
