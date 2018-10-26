package com.thesmader.intelligenttrader


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_register.*
import java.io.Serializable

class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        regBtn.setOnClickListener {
            regUser(it)
            saveUserToFirebasedatabase()
        }

        text_to_login.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.reg_to_login))

        /*profile_pic.setOnClickListener {
            Log.d("prof_pic","select photo")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,0)
        }*/*/
    }

    private fun saveUserToFirebasedatabase() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("users/$uid")

        val user = User(usrname_input.text.toString())
        ref.setValue(user)
                .addOnSuccessListener {
                    Log.d("saveUser","User Saved Successfully") }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==0 && resultCode == Activity.RESULT_OK && data != null){
            Log.d("prof_pic","select photo activity")

            val uri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(, uri)
        }
    }*/

    private fun regUser(regBtn: View) {
        val username = usrname_input.text.toString()
        val email = email_input.text.toString()
        val pswrd = password_input.text.toString()

        if(email.isEmpty()||pswrd.isEmpty()||username.isEmpty())
            Toast.makeText(regBtn.context, "Please fill int the fields",Toast.LENGTH_SHORT).show()

        //Create user with email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pswrd)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    else{
                        Log.d("Auth", "User registered successfully with uid: ${it.result?.user?.uid}")
                        regBtn.findNavController().navigate(R.id.reg_to_chatList)
                    }
                }
                .addOnFailureListener {
                    Log.d("AuthError","Failed to register: ${it.message}")
                    Toast.makeText(regBtn.context,"Check your internet connection",Toast.LENGTH_SHORT).show()
                }
    }

    class User(username: String) : Serializable{
        public var userName:String = username
    }
}
