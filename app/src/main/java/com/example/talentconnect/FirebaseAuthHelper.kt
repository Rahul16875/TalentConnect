package com.example.talentconnect

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseAuthHelper {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    // Function to register a user with email and password, and assign a role
    fun registerUser(college: String, email: String, contact: String , password: String, role: String, onComplete: (Boolean, String?) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val userId = firebaseAuth.currentUser?.uid
                if (userId != null) {
                    firestore.collection("users").document(userId).set(mapOf("role" to role))
                        .addOnSuccessListener {
                            onComplete(true, null)
                        }
                        .addOnFailureListener {
                            onComplete(false, it.localizedMessage)
                        }
                }
            }
            .addOnFailureListener {
                onComplete(false, it.localizedMessage)
            }
    }

    // Function to log in a user and get their role
    fun loginUser(email: String, password: String, callback: (Boolean, String?, String?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    // Fetching the user role from Firestore
                    user?.uid?.let { userId ->
                        firestore.collection("users").document(userId).get()
                            .addOnSuccessListener { document ->
                                val role = document.getString("role")
                                callback(true, role, null)
                            }
                            .addOnFailureListener {
                                callback(false, null, "Failed to fetch role.")
                            }
                    }
                } else {
                    callback(false, null, task.exception?.localizedMessage)
                }
            }
    }

    // Checking if the user is logged in for a specific role
    fun isUserLoggedInForRole(context: Context, role: String): Boolean {
        val sharedPreferences = context.getSharedPreferences("TalentConnectPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn_$role", false)
    }

    // Marking the user as logged in for a specific role
    fun setUserLoggedInForRole(context: Context, role: String, isLoggedIn: Boolean) {
        val sharedPreferences = context.getSharedPreferences("TalentConnectPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isLoggedIn_$role", isLoggedIn).apply()
    }

    // Function to log out the user from a specific role
    fun logoutForRole(context: Context, role: String, onComplete: () -> Unit) {
        setUserLoggedInForRole(context, role, false)
        firebaseAuth.signOut()
        onComplete()
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}

