package com.example.viniauthenticator

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    lateinit var firebaseAuth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        firebaseAuth = FirebaseAuth.getInstance()
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val emailCampo = view.findViewById<EditText>(R.id.emailLogin)
        val senhaCampo = view.findViewById<EditText>(R.id.senhaLogin)
        val loginButton = view.findViewById<Button>(R.id.loginButton)
        val toRegisterButton = view.findViewById<Button>(R.id.toRegisterButton)

        loginButton.setOnClickListener{
            val email = emailCampo.text.toString()
            val senha = senhaCampo.text.toString()

            if(TextUtils.isEmpty(email)||TextUtils.isEmpty(senha)){
                Toast.makeText(activity, "Email ou Senha não podem ser vazios.", Toast.LENGTH_SHORT).show()
            }else{
                firebaseAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(activity, "Logado com sucesso!!!", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(view).navigate(R.id.appFragment)
                    }else{
                        Toast.makeText(activity, "Erro!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        toRegisterButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.registerFragment)
        }

        return view
    }

}