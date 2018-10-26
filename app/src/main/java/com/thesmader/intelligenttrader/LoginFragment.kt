package com.thesmader.intelligenttrader


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.setOnClickListener{
            loginUser(it)
        }
    }

    private fun loginUser(loginBtn: View) {
        val email = email_input_login.text.toString()
        val pswrd = password_input_login.text.toString()

        if (!email.isEmpty()&& !pswrd.isEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pswrd).addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                else{
                    Log.d("AuthLogin","User logged in successfully")
                }
            }
            loginBtn.findNavController().navigate(R.id.action_loginFragment_to_chatList)
        }
        else Toast.makeText(loginBtn.context,"Fill in the fields", Toast.LENGTH_SHORT).show()
    }
}
