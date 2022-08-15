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

class RegisterFragment : Fragment() {
    lateinit var firebaseAuth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        firebaseAuth = FirebaseAuth.getInstance()
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val emailCampo = view.findViewById<EditText>(R.id.emailRegistro)
        val senhaCampo = view.findViewById<EditText>(R.id.senhaRegistro)
        val registerButton = view.findViewById<Button>(R.id.registerButton)
        val toLoginButton = view.findViewById<Button>(R.id.toLoginButton)

        registerButton.setOnClickListener {
            val email = emailCampo.text.toString()
            val senha = senhaCampo.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)){
                Toast.makeText(activity, "Email ou Senha não podem ser vazios.", Toast.LENGTH_SHORT).show()
            }else{
                firebaseAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(activity, "Cadastrado com sucesso!!!", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(activity, "Erro!"  + (it.exception?.message ?: "Erro desconhecido"), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        toLoginButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.loginFragment)
        }

        return view

    }


}